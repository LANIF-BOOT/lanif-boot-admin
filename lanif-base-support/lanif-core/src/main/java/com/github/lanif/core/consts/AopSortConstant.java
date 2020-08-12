
package com.github.lanif.core.consts;

/**
 * aop顺序的常量
 * <p>
 * 顺序越小越靠前
 *
 * @author
 * @date 2020/4/10 15:33
 */
public interface AopSortConstant {

    /**
     * 全局异常拦截器
     */
    int GLOBAL_EXP_HANDLER_AOP = -120;

    /**
     * 结果包装的aop
     */
    int WRAPPER_AOP = -110;

    /**
     * 接口资源权限校验
     */
    int PERMISSION_AOP = -100;

    /**
     * 数据范围AOP
     */
    int DATA_SCOPE_AOP = -50;

    /**
     * 业务日志的AOP
     */
    int BUSINESS_LOG_AOP = 100;

}
