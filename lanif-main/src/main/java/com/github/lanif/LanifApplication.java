
package com.github.lanif;

import cn.hutool.log.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot方式启动类
 *
 * @author
 * @date 2017/5/21 12:06
 */
@SpringBootApplication
public class LanifApplication {

    private static final Log log = Log.get();

    public static void main(String[] args) {
        SpringApplication.run(LanifApplication.class, args);
        log.info(">>> " + LanifApplication.class.getSimpleName() + " is success!");
    }

}