package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.合作伙伴;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.用户数据;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LHX
 * @since 2023-12-28
 */
public interface 合作伙伴Service extends IService<合作伙伴> {

    IPage pageCC(Page<合作伙伴> 分页, Wrapper wrapper);

}
