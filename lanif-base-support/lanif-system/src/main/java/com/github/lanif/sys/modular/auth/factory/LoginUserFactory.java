
package com.github.lanif.sys.modular.auth.factory;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.github.lanif.core.consts.CommonConstant;
import com.github.lanif.core.exception.ServiceException;
import com.github.lanif.core.exception.enums.ServerExceptionEnum;
import com.github.lanif.core.pojo.login.LoginEmpInfo;
import com.github.lanif.core.pojo.login.SysLoginUser;
import com.github.lanif.core.util.HttpServletUtil;
import com.github.lanif.core.util.IpAddressUtil;
import com.github.lanif.core.util.UaUtil;
import com.github.lanif.sys.modular.app.service.SysAppService;
import com.github.lanif.sys.modular.emp.service.SysEmpService;
import com.github.lanif.sys.modular.menu.service.SysMenuService;
import com.github.lanif.sys.modular.role.service.SysRoleService;
import com.github.lanif.sys.modular.user.service.SysUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录用户工厂类
 *
 * @author 
 * @date 2020/3/13 14:58
 */
public class LoginUserFactory {

    private static final SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);

    private static final SysEmpService sysEmpService = SpringUtil.getBean(SysEmpService.class);

    private static final SysAppService sysAppService = SpringUtil.getBean(SysAppService.class);

    private static final SysMenuService sysMenuService = SpringUtil.getBean(SysMenuService.class);

    private static final SysRoleService sysRoleService = SpringUtil.getBean(SysRoleService.class);

    /**
     * 填充登录用户相关信息
     *
     * @author  
     * @date 2020/3/13 15:01
     */
    public static void fillLoginUserInfo(SysLoginUser sysLoginUser) {
        HttpServletRequest request = HttpServletUtil.getRequest();
        if (ObjectUtil.isNotNull(request)) {
            sysLoginUser.setLastLoginIp(IpAddressUtil.getIp(request));
            sysLoginUser.setLastLoginTime(DateTime.now().toString());
            sysLoginUser.setLastLoginAddress(IpAddressUtil.getAddress(request));
            sysLoginUser.setLastLoginBrowser(UaUtil.getBrowser(request));
            sysLoginUser.setLastLoginOs(UaUtil.getOs(request));
            Long userId = sysLoginUser.getId();

            //员工信息
            LoginEmpInfo loginEmpInfo = sysEmpService.getLoginEmpInfo(userId);
            sysLoginUser.setLoginEmpInfo(loginEmpInfo);

            //角色信息
            List<Dict> roles = sysRoleService.getLoginRoles(userId);
            sysLoginUser.setRoles(roles);

            //权限信息
            List<String> permissions = sysMenuService.getLoginPermissions(userId);
            sysLoginUser.setPermissions(permissions);

            //数据范围信息
            List<Long> dataScopes = sysUserService.getUserDataScopeIdList(userId, loginEmpInfo.getOrgId());
            sysLoginUser.setDataScopes(dataScopes);

            //具备应用信息（多系统，默认激活一个，可根据系统切换菜单）,返回的结果中第一个为激活的系统
            List<Dict> apps = sysAppService.getLoginApps(userId);
            sysLoginUser.setApps(apps);

            //如果根本没有应用信息，则没有菜单信息
            if (ObjectUtil.isEmpty(apps)) {
                sysLoginUser.setMenus(CollectionUtil.newArrayList());
            } else {
                //AntDesign菜单信息，根据人获取，用于登录后展示菜单树，默认获取默认激活的系统的菜单
                String defaultActiveAppCode = apps.get(0).getStr(CommonConstant.CODE);
                sysLoginUser.setMenus(sysMenuService.getLoginMenusAntDesign(userId, defaultActiveAppCode));
            }
        } else {
            throw new ServiceException(ServerExceptionEnum.REQUEST_EMPTY);
        }
    }
}
