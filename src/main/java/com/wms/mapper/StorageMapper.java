package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
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
 * @since 2023-12-25
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
    IPage storageCC(Page<Storage> 分页, @Param(Constants.WRAPPER) Wrapper wrapper);

}
