
package com.github.lanif.sys.modular.pos.enums;

import com.github.lanif.core.annotion.ExpEnumType;
import com.github.lanif.core.consts.ExpEnumConstant;
import com.github.lanif.core.exception.enums.abs.AbstractBaseExceptionEnum;
import com.github.lanif.core.factory.ExpEnumCodeFactory;
import com.github.lanif.sys.core.consts.SysExpEnumConstant;

/**
 * 系统职位相关异常枚举
 *
 * @author 
 * @date 2020/3/26 10:12
 */
@ExpEnumType(module = SysExpEnumConstant.LANIF_SYS_MODULE_EXP_CODE, kind = SysExpEnumConstant.SYS_POS_EXCEPTION_ENUM)
public enum SysPosExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 职位不存在
     */
    POS_NOT_EXIST(1, "职位不存在"),

    /**
     * 职位编码重复
     */
    POS_CODE_REPEAT(2, "职位编码重复，请检查code参数"),

    /**
     * 职位名称重复
     */
    POS_NAME_REPEAT(3, "职位名称重复，请检查name参数"),

    /**
     * 该职位下有员工
     */
    POS_CANNOT_DELETE(4, "该职位下有员工，无法删除");

    private final Integer code;

    private final String message;

    SysPosExceptionEnum(Integer code, String message) {
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
