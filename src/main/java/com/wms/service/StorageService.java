package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.用户数据;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LHX
 * @since 2023-12-25
 */
public interface StorageService extends IService<Storage> {

    IPage storageCC(Page<Storage> 分页, Wrapper wrapper);

}
