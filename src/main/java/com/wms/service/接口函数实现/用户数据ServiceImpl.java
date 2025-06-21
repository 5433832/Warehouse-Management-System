package com.wms.service.接口函数实现;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.用户数据;
import com.wms.mapper.用户数据Mapper;
import com.wms.service.用户数据Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LHX
 * @since 2023-12-14
 */
@Service
public class 用户数据ServiceImpl extends ServiceImpl<用户数据Mapper, 用户数据> implements 用户数据Service {
    @Autowired
    private 用户数据Mapper userMapper;

    @Override
    public IPage pageC(Page<用户数据> 分页) {
        return userMapper.pageC(分页);
    }

    @Override
    public IPage pageCC(Page<用户数据> 分页, Wrapper wrapper) {
        //调用userMapper对象的pageCC方法
        return userMapper.pageCC(分页, wrapper);
    }
}
