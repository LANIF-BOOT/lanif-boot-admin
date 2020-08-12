
package com.github.lanif.sys.modular.file.enums;

import com.github.lanif.core.annotion.ExpEnumType;
import com.github.lanif.core.consts.ExpEnumConstant;
import com.github.lanif.core.exception.enums.abs.AbstractBaseExceptionEnum;
import com.github.lanif.core.factory.ExpEnumCodeFactory;
import com.github.lanif.sys.core.consts.SysExpEnumConstant;

/**
 * 文件信息表相关枚举
 *
 * @author
 * @date 2020/6/7 22:15
 */
@ExpEnumType(module = SysExpEnumConstant.LANIF_SYS_MODULE_EXP_CODE, kind = SysExpEnumConstant.SYS_FILE_INFO_EXCEPTION_ENUM)
public enum SysFileInfoExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 该条记录不存在
     */
    NOT_EXISTED(1, "您查询的该条记录不存在"),

    /**
     * 获取文件流错误
     */
    FILE_STREAM_ERROR(2, "获取文件流错误"),

    /**
     * 文件不存在
     */
    NOT_EXISTED_FILE(3, "文件不存在"),

    /**
     * 获取上传文件异常
     */
    ERROR_FILE(4, "获取上传文件异常"),

    /**
     * 下载文件错误
     */
    DOWNLOAD_FILE_ERROR(5, "下载文件错误"),

    /**
     * 预览文件异常
     */
    PREVIEW_ERROR_NOT_SUPPORT(6, "预览文件异常，您预览的文件类型不支持或文件出现错误");

    private final Integer code;

    private final String message;

    SysFileInfoExceptionEnum(int code, String message) {
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