package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.用户数据;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LHX
 * @since 2023-12-14
 */
public interface 用户数据Service extends IService<用户数据> {

    IPage pageC(Page<用户数据> 分页);


    //声明IPage类的pageCC方法（并传入Page<用户数据>对象类型的 分页，构建查询条件Wrapper对象类型的 wrapper）
    IPage pageCC(Page<用户数据> 分页, Wrapper wrapper);
}
