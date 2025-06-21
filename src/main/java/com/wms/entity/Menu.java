package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "菜单编码")
    private String 菜单编码;

    @ApiModelProperty(value = "菜单名字")
    private String 菜单名称;

    @ApiModelProperty(value = "菜单级别")
    private String 菜单级别;

    @ApiModelProperty(value = "菜单的父code")
    @TableField("menuParentCode")
    private String menuparentcode;

    @ApiModelProperty(value = "点击触发的函数")
    private String 菜单函数;

    @ApiModelProperty(value = "权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用")
    private String 所需权限;

    private String 菜单路径;

    private String 菜单图标;


}
