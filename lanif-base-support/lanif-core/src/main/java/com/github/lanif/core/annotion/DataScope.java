
package com.github.lanif.core.annotion;

import java.lang.annotation.*;

/**
 * 数据权限注解
 *
 * @author 
 * @date 2020/3/28 17:16
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataScope {
}
