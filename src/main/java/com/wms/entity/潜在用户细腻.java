package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author LHX
 * @since 2023-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "潜在用户细腻对象", description = "")
public class 潜在用户细腻 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String 姓名;

    private String 电话;

    private String 租赁开始时间;

    private String 租赁结束时间;

    private String 问题;


}
