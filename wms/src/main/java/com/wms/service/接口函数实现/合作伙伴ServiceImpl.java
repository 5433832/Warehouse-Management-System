package com.wms.service.接口函数实现;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.合作伙伴;
import com.wms.entity.用户数据;
import com.wms.mapper.合作伙伴Mapper;
import com.wms.mapper.用户数据Mapper;
import com.wms.service.合作伙伴Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LHX
 * @since 2023-12-28
 */
@Service
public class 合作伙伴ServiceImpl extends ServiceImpl<合作伙伴Mapper, 合作伙伴> implements 合作伙伴Service {
    @Autowired
    private 合作伙伴Mapper userMapper;

    @Override
    public IPage pageCC(Page<合作伙伴> 分页, Wrapper wrapper) {
        //调用userMapper对象的pageCC方法
        return userMapper.pageCC(分页, wrapper);
    }

}
