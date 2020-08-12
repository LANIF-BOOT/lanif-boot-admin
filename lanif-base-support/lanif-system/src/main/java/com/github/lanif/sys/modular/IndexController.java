
package com.github.lanif.sys.modular;

import com.github.lanif.core.consts.CommonConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 *
 * @author 
 * @date 2020/3/18 11:20
 */
@RestController
public class IndexController {

    /**
     * 访问首页，提示语
     *
     * @author 
     * @date 2020/4/8 19:27
     */
    @RequestMapping("/")
    public String index() {
        return CommonConstant.INDEX_TIPS;
    }
}
