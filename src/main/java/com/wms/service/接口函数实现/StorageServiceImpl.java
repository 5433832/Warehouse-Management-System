package com.wms.service.接口函数实现;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
import com.wms.entity.用户数据;
import com.wms.mapper.StorageMapper;
import com.wms.mapper.用户数据Mapper;
import com.wms.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LHX
 * @since 2023-12-25
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public IPage storageCC(Page<Storage> 分页, Wrapper wrapper) {
        //调用userMapper对象的pageCC方法
        return storageMapper.storageCC(分页, wrapper);
    }


}
