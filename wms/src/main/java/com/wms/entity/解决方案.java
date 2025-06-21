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
@ApiModel(value = "解决方案对象", description = "")
public class 解决方案 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String dis;

    @ApiModelProperty(value = "图片")
    private String img;

}
