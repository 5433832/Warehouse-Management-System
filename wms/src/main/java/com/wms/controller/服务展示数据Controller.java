package com.wms.controller;


import com.wms.common.Result;
import com.wms.entity.服务展示数据;
import com.wms.entity.用户数据;
import com.wms.service.服务展示数据Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LHX
 * @since 2023-12-28
 */
@RestController
@RequestMapping("/服务展示数据")
public class 服务展示数据Controller {
    @Autowired
    private 服务展示数据Service 服务展示数据Service;

    @GetMapping("/list")
    public List<服务展示数据> list() {
        return 服务展示数据Service.list();
    }

}
