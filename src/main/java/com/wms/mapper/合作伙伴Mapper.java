package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.合作伙伴;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.用户数据;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LHX
 * @since 2023-12-28
 */
@Mapper
public interface 合作伙伴Mapper extends BaseMapper<合作伙伴> {
    IPage pageCC(Page<合作伙伴> 分页, @Param(Constants.WRAPPER) Wrapper wrapper);

}
