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
@ApiModel(value="前台用户数据对象", description="")
public class 前台用户数据 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String 编号;

    private String 姓名;

    private String 密码;


}
