
package com.github.lanif.sys.core.context;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;
import com.github.lanif.core.context.system.SystemContext;
import com.github.lanif.core.pojo.login.SysLoginUser;
import com.github.lanif.sys.modular.auth.service.AuthService;
import com.github.lanif.sys.modular.dict.service.SysDictDataService;
import com.github.lanif.sys.modular.role.entity.SysRole;
import com.github.lanif.sys.modular.role.param.SysRoleParam;
import com.github.lanif.sys.modular.role.service.SysRoleService;
import com.github.lanif.sys.modular.user.entity.SysUser;
import com.github.lanif.sys.modular.user.param.SysUserParam;
import com.github.lanif.sys.modular.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统相关上下文接口实现类
 *
 * @author 
 * @date 2020/5/6 14:59
 */
@Component
public class SystemContextImpl implements SystemContext {

    Log log = Log.get();

    @Resource
    private AuthService authService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysDictDataService sysDictDataService;

    @Override
    public String getNameByUserId(Long userId) {
        return sysUserService.getNameByUserId(userId);
    }

    @Override
    public String getNameByRoleId(Long roleId) {
        return sysRoleService.getNameByRoleId(roleId);
    }

    @Override
    public SysLoginUser getLoginUserByToken(String token) {
        return authService.getLoginUserByToken(token);
    }

    @Override
    public List<Dict> listUser(String account) {
        SysUserParam sysUserParam = new SysUserParam();
        if (ObjectUtil.isNotEmpty(account)) {
            sysUserParam.setAccount(account);
        }
        return sysUserService.list(sysUserParam);
    }

    @Override
    public List<Dict> listRole(String name) {
        SysRoleParam sysRoleParam = new SysRoleParam();
        if (ObjectUtil.isNotEmpty(name)) {
            sysRoleParam.setName(name);
        }
        return sysRoleService.list(sysRoleParam);
    }

    @Override
    public boolean isUser(Long userOrRoleId) {
        SysUser sysUser = sysUserService.getById(userOrRoleId);
        return !ObjectUtil.isNull(sysUser);
    }

    @Override
    public boolean isRole(Long userOrRoleId) {
        SysRole sysRole = sysRoleService.getById(userOrRoleId);
        return !ObjectUtil.isNull(sysRole);
    }

    @Override
    public List<String> getDictCodesByDictTypeCode(String... dictTypeCodes) {
        return sysDictDataService.getDictCodesByDictTypeCode(dictTypeCodes);
    }

    @Override
    public boolean tableUniValueFlag(String tableName, String columnName, String value) {
        List<Map<String, Object>> maps = SqlRunner.db().selectList(
                "select " + columnName + " from " + tableName + " where " + columnName + " = {0}", value);
        if (maps != null && maps.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

}
