package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.潜在用户细腻;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.用户数据;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LHX
 * @since 2023-12-27
 */
public interface 潜在用户细腻Service extends IService<潜在用户细腻> {

    IPage pageCC(Page<潜在用户细腻> 分页, Wrapper wrapper);
}
