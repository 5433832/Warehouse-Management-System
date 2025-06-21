package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.合作伙伴;
import com.wms.entity.潜在用户细腻;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LHX
 * @since 2023-12-27
 */
@Mapper
public interface 潜在用户细腻Mapper extends BaseMapper<潜在用户细腻> {
    IPage pageCC(Page<潜在用户细腻> 分页, @Param(Constants.WRAPPER) Wrapper wrapper);

}
