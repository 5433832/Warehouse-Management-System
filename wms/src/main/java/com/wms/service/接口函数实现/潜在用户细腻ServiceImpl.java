package com.wms.service.接口函数实现;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.潜在用户细腻;
import com.wms.entity.用户数据;
import com.wms.mapper.潜在用户细腻Mapper;
import com.wms.service.潜在用户细腻Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LHX
 * @since 2023-12-27
 */
@Service
public class 潜在用户细腻ServiceImpl extends ServiceImpl<潜在用户细腻Mapper, 潜在用户细腻> implements 潜在用户细腻Service {
    @Autowired
    private 潜在用户细腻Mapper userMapper;

    @Override
    public IPage pageCC(Page<潜在用户细腻> 分页, Wrapper wrapper) {
        //调用userMapper对象的pageCC方法
        return userMapper.pageCC(分页, wrapper);
    }
}
