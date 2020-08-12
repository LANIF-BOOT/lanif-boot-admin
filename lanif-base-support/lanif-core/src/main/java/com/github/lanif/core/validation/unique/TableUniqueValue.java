
package com.github.lanif.core.validation.unique;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证表的的某个字段值是否在是唯一值
 *
 * @author
 * @date 2020/4/14 23:49
 */
@Documented
@Constraint(validatedBy = TableUniqueValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableUniqueValue {

    String message() default "库中存在重复编码，请更换该编码值";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 表名称，例如 sys_user
     */
    String tableName();

    /**
     * 列名称，例如 user_code
     */
    String columnName();

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        TableUniqueValue[] value();
    }
}
