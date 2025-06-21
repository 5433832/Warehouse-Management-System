package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.Result;
import com.wms.common.查询分页参数;
import com.wms.entity.合作伙伴;
import com.wms.entity.用户数据;
import com.wms.service.合作伙伴Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/合作伙伴")
public class 合作伙伴Controller {
    @Autowired
    private 合作伙伴Service 合作伙伴Service;

    @GetMapping("/list")
    public List<合作伙伴> list() {
        return 合作伙伴Service.list();
    }

    @PostMapping("/插入")
//    @RequestBody 表示将 HTTP 请求的主体内容转换为指定类型的对象（用户数据）
    public Result 插入(@RequestBody 合作伙伴 user) {//将获取到的客户端主体数据转换为用户数据类型赋给user
        //调用userService中的save方法将转换好的数据交给save提交如果提交成功返回true否则false
        return 合作伙伴Service.save(user) ? Result.成功() : Result.失败();
    }

    //删除
    @GetMapping("/删除")
    public Result 删除(Integer id) {
        return 合作伙伴Service.removeById(id) ? Result.成功() : Result.失败();
    }

    @PostMapping("/update")
    public Result update(@RequestBody 合作伙伴 user) {
        return 合作伙伴Service.updateById(user) ? Result.成功() : Result.失败();
    }

    @PostMapping("/模糊查询并分页进行数据封装")
    //模糊查询并手写分页方法并调用封装方法
    public Result 模糊查询并分进行数据封装(@RequestBody 查询分页参数 分页参数) {
        //声明键值对类变量"分页数据封装"并获取"分页参数"类的"分页数据封装"变量
        HashMap 分页数据封装 = 分页参数.get分页数据封装();

        //声明字符串类的变量"用户名称"并获取"分页数据封装"变量的”用户名称“属性(此时用户传入的数据已经存储到分页参数实体类内)
        String 搜索 = (String) 分页数据封装.get("搜索");

        //声明Page类并传入用户数据类作为泛型操作的Page对象，名为分页
        Page<合作伙伴> 分页 = new Page();

        //设置分页对象的页数为获取分页参数类的页数
        分页.setCurrent(分页参数.get页数());

        //设置分页对象的每页数据为获取分页参数类的每页数据
        分页.setSize(分页参数.get每页数据());


        //设置当前页页数，不设置默认最后一页
//        分页.setPages(2);
        //声明LambdaQueryWrapper查询构造器对象，利用LambdaQueryWrapper查询构造器通过Lambda表达式来构建查询条件
        LambdaQueryWrapper<合作伙伴> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        //判断是否不为空并且也不等于null字符
        if (StringUtils.isNotBlank(搜索) && !"null".equals(搜索)) {
            //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
            lambdaQueryWrapper.like(合作伙伴::getName, 搜索);
        }

        //IPage为封装分页查询结果的接口，用来表示分页查询的结果集；调用userService接口的pageCC方法（将模糊查询的条件和获取的分页参数传入）
        IPage 分页结果 = 合作伙伴Service.pageCC(分页, lambdaQueryWrapper);//声明“分页查询接口”的变量并将模糊查询的结果使用”分页“变量进行分页的结果赋值
        System.out.println("总=" + 分页结果.getTotal());
        System.out.println(分页结果.getRecords());
        //调用Result类的“成功”方法并传入（分页结果的当前页数据，分页结果的数据总数）
        return Result.成功(分页结果.getRecords(), 分页结果.getTotal());
    }

}
