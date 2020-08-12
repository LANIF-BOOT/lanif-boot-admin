
package com.github.lanif.sys.modular.user.factory;

import cn.hutool.core.util.ObjectUtil;
import com.github.lanif.core.context.constant.ConstantContextHolder;
import com.github.lanif.sys.core.enums.AdminTypeEnum;
import com.github.lanif.core.enums.CommonStatusEnum;
import com.github.lanif.sys.core.enums.SexEnum;
import com.github.lanif.sys.modular.user.entity.SysUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 填充用户附加信息工厂
 *
 * @author 
 * @date 2020/3/23 16:40
 */
public class SysUserFactory {

    /**
     * 管理员类型（1超级管理员 2非管理员）
     * 新增普通用户时填充相关信息
     *
     * @author 
     * @date 2020/3/23 16:41
     */
    public static void fillAddCommonUserInfo(SysUser sysUser) {
        fillBaseUserInfo(sysUser);
        sysUser.setAdminType(AdminTypeEnum.NONE.getCode());
    }

    /**
     * 填充用户基本字段
     *
     * @author 
     * @date 2020/3/23 16:50
     */
    public static void fillBaseUserInfo(SysUser sysUser) {
        //密码为空则设置密码
        if(ObjectUtil.isEmpty(sysUser.getPassword())) {
            //没有密码则设置默认密码
            String password = ConstantContextHolder.getDefaultPassWord();
            //设置密码为Md5加密后的密码
            sysUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        }

        if(ObjectUtil.isEmpty(sysUser.getAvatar())) {
            sysUser.setAvatar(null);
        }

        if(ObjectUtil.isEmpty(sysUser.getSex())) {
            sysUser.setSex(SexEnum.NONE.getCode());
        }

        sysUser.setStatus(CommonStatusEnum.ENABLE.getCode());
    }
}
