package com.wms.controller;


import com.wms.entity.用户数据;
import com.wms.entity.解决方案;
import com.wms.service.解决方案Service;
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
 * @since 2023-12-27
 */
@RestController
@RequestMapping("/解决方案")
public class 解决方案Controller {

    @Autowired
    private 解决方案Service 解决方案;

    //Get请求为获取
    @GetMapping("/查询所有")
    public List<解决方案> list() {
        return 解决方案.list();
    }

}
