
package com.github.lanif.sys.modular.monitor.service;

import com.github.lanif.sys.modular.monitor.result.SysMachineResult;

/**
 * 系统属性监控service接口
 *
 * @author 
 * @date 2020/6/5 14:39
 */
public interface SysMachineService {
    
    /**
     * 系统属性监控
     *
     * @return 系统属性结果集
     * @author 
     * @date 2020/6/5 14:45
     */
    SysMachineResult query();
}
