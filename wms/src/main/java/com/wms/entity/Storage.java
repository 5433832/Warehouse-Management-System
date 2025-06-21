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
 * @since 2023-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Storage对象", description = "")
public class Storage implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "主键")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String 仓库编号;

  @ApiModelProperty(value = "仓库名")
  private String 仓库名称;

  private String 仓库地区;

  private Integer 仓库管理者uid;

  private String 仓库类别;

  private String 仓库状态;

  @ApiModelProperty(value = "备注")
  private String 备注;

  private String 仓库公告;

  private String 仓库优点;

  private String 仓库缺点;

  private String 租赁开始时间;

  private String 租赁结束时间;

  private String img0;

  private String img1;
}
