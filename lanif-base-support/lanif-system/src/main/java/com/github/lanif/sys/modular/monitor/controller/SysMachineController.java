
package com.github.lanif.sys.modular.monitor.controller;

import com.github.lanif.core.annotion.BusinessLog;
import com.github.lanif.core.enums.LogAnnotionOpTypeEnum;
import com.github.lanif.core.pojo.response.ResponseData;
import com.github.lanif.core.pojo.response.SuccessResponseData;
import com.github.lanif.sys.modular.monitor.service.SysMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统属性监控控制器
 *
 * @author 
 * @date 2020/6/5 14:36
 */
@RestController
public class SysMachineController {

    @Resource
    private SysMachineService sysMachineService;

    /**
     * 系统属性监控
     *
     * @author 
     * @date 2020/6/5 14:38
     */
    @GetMapping("/sysMachine/query")
    @BusinessLog(title = "系统属性监控_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData query() {
        return new SuccessResponseData(sysMachineService.query());
    }
}
