
package com.github.lanif.sys.modular.monitor.controller;

import com.github.lanif.core.annotion.BusinessLog;
import com.github.lanif.core.annotion.Permission;
import com.github.lanif.core.enums.LogAnnotionOpTypeEnum;
import com.github.lanif.core.pojo.response.ResponseData;
import com.github.lanif.core.pojo.response.SuccessResponseData;
import com.github.lanif.sys.modular.monitor.param.SysOnlineUserParam;
import com.github.lanif.sys.modular.monitor.service.SysOnlineUserService;
import com.github.lanif.sys.modular.org.param.SysOrgParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 在线用户控制器
 *
 * @author 
 * @date 2020/4/7 16:57
 */
@RestController
public class SysOnlineUserController {

    @Resource
    private SysOnlineUserService sysOnlineUserService;

    /**
     * 在线用户列表
     *
     * @author 
     * @date 2020/4/7 16:58
     */
    @Permission
    @GetMapping("/sysOnlineUser/list")
    @BusinessLog(title = "系统在线用户_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData list(SysOnlineUserParam sysOnlineUserParam) {
        return new SuccessResponseData(sysOnlineUserService.list(sysOnlineUserParam));
    }

    /**
     * 在线用户强退
     *
     * @author 
     * @date 2020/4/7 17:36
     */
    @Permission
    @PostMapping("/sysOnlineUser/forceExist")
    @BusinessLog(title = "系统在线用户_强退", opType = LogAnnotionOpTypeEnum.FORCE)
    public ResponseData forceExist(@RequestBody  @Validated(SysOrgParam.force.class) SysOnlineUserParam sysOnlineUserParam) {
        sysOnlineUserService.forceExist(sysOnlineUserParam);
        return new SuccessResponseData();
    }
}
