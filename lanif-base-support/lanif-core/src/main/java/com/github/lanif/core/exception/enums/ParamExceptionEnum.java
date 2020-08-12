
package com.github.lanif.core.exception.enums;

import com.github.lanif.core.annotion.ExpEnumType;
import com.github.lanif.core.consts.ExpEnumConstant;
import com.github.lanif.core.exception.enums.abs.AbstractBaseExceptionEnum;
import com.github.lanif.core.factory.ExpEnumCodeFactory;

/**
 * 参数校验异常枚举
 *
 * @author 
 * @date 2020/3/25 20:11
 */
@ExpEnumType(module = ExpEnumConstant.LANIF_CORE_MODULE_EXP_CODE, kind = ExpEnumConstant.PARAM_EXCEPTION_ENUM)
public enum ParamExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 参数错误
     */
    PARAM_ERROR(1, "参数错误");

    private final Integer code;

    private final String message;

    ParamExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return ExpEnumCodeFactory.getExpEnumCode(this.getClass(), code);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
