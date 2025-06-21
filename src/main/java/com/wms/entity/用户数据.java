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
 * @since 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="用户数据对象", description="")
public class 用户数据 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "UID", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty(value = "名字")
    private String 用户名称;

    @ApiModelProperty(value = "密码")
    private String 密码;

    @ApiModelProperty(value = "年龄")
    private Integer 年龄;

    @ApiModelProperty(value = "性别	")
    private String 性别;

    @ApiModelProperty(value = "电话")
    private String 电话;

    @ApiModelProperty(value = "Root，管理员，普通账号")
    private String 级别;

    @ApiModelProperty(value = "账号是否有效")
    private String 账户是否有效;


}
