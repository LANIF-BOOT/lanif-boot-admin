
package com.github.lanif.sys.modular.dict.entity;

import com.github.lanif.core.pojo.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统字典类型表
 *
 * @author 
 * @date 2020/3/11 12:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_type")
public class SysDictType extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 状态（字典 0正常 1停用 2删除）
     */
    private Integer status;

}
