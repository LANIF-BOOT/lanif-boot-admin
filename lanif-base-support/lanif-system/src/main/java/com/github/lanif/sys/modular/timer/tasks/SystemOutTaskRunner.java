
package com.github.lanif.sys.modular.timer.tasks;

import com.github.lanif.core.timer.TimerTaskRunner;
import org.springframework.stereotype.Component;

/**
 * 这是一个定时任务的示例程序
 *
 * @author
 * @date 2020/6/30 22:09
 */
@Component
public class SystemOutTaskRunner implements TimerTaskRunner {

    @Override
    public void action() {
        System.out.println("一直往南方开！一直往南方开！");
    }

}
