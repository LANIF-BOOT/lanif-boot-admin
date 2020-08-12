
package com.github.lanif.sys.core.aop;

import cn.hutool.log.Log;
import com.github.lanif.core.annotion.BusinessLog;
import com.github.lanif.core.annotion.Permission;
import com.github.lanif.core.consts.AopSortConstant;
import com.github.lanif.core.context.login.LoginContextHolder;
import com.github.lanif.core.enums.LogicTypeEnum;
import com.github.lanif.core.exception.PermissionException;
import com.github.lanif.core.exception.enums.PermissionExceptionEnum;
import com.github.lanif.core.util.HttpServletUtil;
import com.github.lanif.sys.core.log.LogManager;
import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 权限过滤Aop切面
 *
 * @author 
 * @date 2020/3/23 17:09
 */
@Aspect
@Order(AopSortConstant.PERMISSION_AOP)
public class PermissionAop {

    private static final Log log = Log.get();

    /**
     * 权限切入点
     *
     * @author 
     * @date 2020/3/23 17:10
     */
    @Pointcut("@annotation(com.github.lanif.core.annotion.Permission)")
    private void getPermissionPointCut() {
    }

    /**
     * 执行权限过滤
     *
     * @author 
     * @date 2020/3/23 17:14
     */
    @Before("getPermissionPointCut()")
    public void doPermission(JoinPoint joinPoint) {

        //如果当前登录用户是超级管理员则不校验权限
        boolean isSuperAdmin = LoginContextHolder.me().isSuperAdmin();
        if (!isSuperAdmin) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Permission permission = method.getAnnotation(Permission.class);

            //当前方法需要的角色集合
            String[] requireRoles = permission.value();

            //逻辑类型
            LogicTypeEnum logicTypeEnum = permission.logicType();

            //如果不需要特别的角色，则判断用户所属角色是否有当前访问的url的权限
            if (requireRoles.length == 0) {
                HttpServletRequest request = HttpServletUtil.getRequest();
                boolean flag = LoginContextHolder.me().hasPermission(request.getRequestURI());
                if (!flag) {
                    this.executeNoPermissionExceptionLog(joinPoint, new PermissionException(PermissionExceptionEnum.NO_PERMISSION));
                    throw new PermissionException(PermissionExceptionEnum.NO_PERMISSION);
                }
            } else {
                //当前方法的权限需要一些特别的角色
                boolean flag = true;
                if (LogicTypeEnum.AND.equals(logicTypeEnum)) {
                    flag = LoginContextHolder.me().hasAllRole(StringUtils.join(requireRoles));
                } else if (LogicTypeEnum.OR.equals(logicTypeEnum)) {
                    flag = LoginContextHolder.me().hasAnyRole(StringUtils.join(requireRoles));
                } else {
                    log.error(">>> permission注解逻辑枚举错误");
                }
                if (!flag) {
                    this.executeNoPermissionExceptionLog(joinPoint, new PermissionException(PermissionExceptionEnum.NO_PERMISSION));
                    throw new PermissionException(PermissionExceptionEnum.NO_PERMISSION);
                }
            }
        }
    }

    /**
     * 记录无权限异常日志
     *
     * @author 
     * @date 2020/3/24 11:14
     */
    private void executeNoPermissionExceptionLog(JoinPoint joinPoint, Exception exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        BusinessLog businessLog = method.getAnnotation(BusinessLog.class);

        //异步记录日志
        LogManager.me().executeExceptionLog(
                businessLog, LoginContextHolder.me().getSysLoginUserAccount(), joinPoint, exception);
    }

}
