
package com.github.lanif.core.validation.unique;

import com.github.lanif.core.context.system.SystemContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证表的的某个字段值是否在是唯一值
 *
 * @author
 * @date 2020/4/14 23:49
 */
public class TableUniqueValueValidator implements ConstraintValidator<TableUniqueValue, String> {

    private String tableName;

    private String columnName;

    @Override
    public void initialize(TableUniqueValue constraintAnnotation) {
        this.tableName = constraintAnnotation.tableName();
        this.columnName = constraintAnnotation.columnName();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
        return SystemContextHolder.me().tableUniValueFlag(tableName, columnName, fieldValue);
    }
}
