package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.Result;
import com.wms.common.查询分页参数;
import com.wms.entity.Menu;
import com.wms.entity.Storage;
import com.wms.service.StorageService;
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
 * @since 2023-12-25
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

  @Autowired
  private StorageService storageService;

  @GetMapping("/list")
  public List<Storage> list() {
    return storageService.list();
  }

  //新增
  //处理post（接受）请求路径为"/插入"
  @PostMapping("/插入")
//    @RequestBody 表示将 HTTP 请求的主体内容转换为指定类型的对象（Storage）
  public Result 插入(@RequestBody Storage storage) {//将获取到的客户端主体数据转换为Storage类型赋给storage
    //调用storageService中的save方法将转换好的数据交给save提交如果提交成功返回true否则false
    return storageService.save(storage) ? Result.成功() : Result.失败();
  }

  @PostMapping("/update")
  public Result update(@RequestBody Storage storage) {
    return storageService.updateById(storage) ? Result.成功() : Result.失败();
  }


  //删除
  @GetMapping("/删除")
  public Result 删除(Integer uid) {
    return storageService.removeById(uid) ? Result.成功() : Result.失败();
  }

  @PostMapping("/模糊查询并分页进行数据封装")
  //模糊查询并手写分页方法并调用封装方法
  public Result 模糊查询并分进行数据封装(@RequestBody 查询分页参数 分页参数) {
    //声明键值对类变量"分页数据封装"并获取"分页参数"类的"分页数据封装"变量
    HashMap 分页数据封装 = 分页参数.get分页数据封装();

    //声明字符串类的变量"用户名称"并获取"分页数据封装"变量的”用户名称“属性(此时用户传入的数据已经存储到分页参数实体类内)
    String 搜索 = (String) 分页数据封装.get("搜索");
    String 地区 = (String) 分页数据封装.get("地区");
    String 状态 = (String) 分页数据封装.get("状态");

    //声明Page类并传入Storage类作为泛型操作的Page对象，名为分页
    Page<Storage> 分页 = new Page();

    //设置分页对象的页数为获取分页参数类的页数
    分页.setCurrent(分页参数.get页数());

    //设置分页对象的每页数据为获取分页参数类的每页数据
    分页.setSize(分页参数.get每页数据());


    //设置当前页页数，不设置默认最后一页
//        分页.setPages(2);
    //声明LambdaQueryWrapper查询构造器对象，利用LambdaQueryWrapper查询构造器通过Lambda表达式来构建查询条件
    LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();

    //判断是否不为空并且也不等于null字符
    if (StringUtils.isNotBlank(搜索) && !"null".equals(搜索)) {
      //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
      lambdaQueryWrapper.like(Storage::get仓库名称, 搜索).or().like(Storage::get仓库编号, 搜索).or().like(Storage::get仓库管理者uid, 搜索);
    }

    if (StringUtils.isNotBlank(地区) && !"null".equals(地区)) {
      //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
      lambdaQueryWrapper.like(Storage::get仓库地区, 地区);
    }

    if (StringUtils.isNotBlank(状态) && !"null".equals(状态)) {
      //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
      lambdaQueryWrapper.like(Storage::get仓库状态, 状态);
    }


    //IPage为封装分页查询结果的接口，用来表示分页查询的结果集；调用storageService接口的pageCC方法（将模糊查询的条件和获取的分页参数传入）
    IPage 分页结果 = storageService.storageCC(分页, lambdaQueryWrapper);//声明“分页查询接口”的变量并将模糊查询的结果使用”分页“变量进行分页的结果赋值
    System.out.println("总=" + 分页结果.getTotal());
    System.out.println(分页结果.getRecords());
    //调用Result类的“成功”方法并传入（分页结果的当前页数据，分页结果的数据总数）
    return Result.成功(分页结果.getRecords(), 分页结果.getTotal());
  }


}
