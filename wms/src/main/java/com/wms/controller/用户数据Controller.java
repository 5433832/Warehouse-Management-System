package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.Result;
import com.wms.common.查询分页参数;
import com.wms.entity.Menu;
import com.wms.entity.用户数据;
import com.wms.service.MenuService;
import com.wms.service.用户数据Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LHX
 * @since 2023-12-14
 */
@RestController
//@CrossOrigin
//@CrossOrigin 注解用于在 Spring 框架中启用跨域资源共享
//映射路径
@RequestMapping("/用户数据")//ReequestMapping可以处理多种请求
public class 用户数据Controller {
  //自动装配系统会自动寻找匹配的Bean也就是用户数据Service属性（实例化对象）并注入userService中
  @Autowired
  private 用户数据Service userService;

  @Autowired
  private MenuService menuService;

  //Get请求为获取
  @GetMapping("/list")
  public List<用户数据> list() {
    return userService.list();
  }

  //新增
  //处理post（接受）请求路径为/插入
  @PostMapping("/插入")
//    @RequestBody 表示将 HTTP 请求的主体内容转换为指定类型的对象（用户数据）
  public Result 插入(@RequestBody 用户数据 user) {//将获取到的客户端主体数据转换为用户数据类型赋给user
    //调用userService中的save方法将转换好的数据交给save提交如果提交成功返回true否则false
    return userService.save(user) ? Result.成功() : Result.失败();
  }

  @PostMapping("/update")
  public Result update(@RequestBody 用户数据 user) {
    return userService.updateById(user) ? Result.成功() : Result.失败();
  }

  //后台登录验证
  @PostMapping("/Managelogin")
  public Result Managelogin(@RequestBody 用户数据 user) {
    List list = userService.lambdaQuery()
      .eq(用户数据::getUid, user.getUid())
      .eq(用户数据::get密码, user.get密码())
      .list();
    if (list.size() > 0) {
      用户数据 user1 = (用户数据) list.get(0);
      //对级别进行判断普通用户禁止登录后台
      if ((Objects.equals(user1.get级别(), "Root") || Objects.equals(user1.get级别(), "管理员")) && Objects.equals(user1.get账户是否有效(), "是")) {
        List menulist = menuService.lambdaQuery().like(Menu::get所需权限, user1.get级别()).list();
        HashMap res = new HashMap<>();
        res.put("user", user1);
        res.put("menu", menulist);
        return Result.成功(res);
      }
    }
    return Result.失败();
  }

  @GetMapping("/申请uid")
  public Integer 获取最大的uid() {
    List<用户数据> list = userService.list();

    Integer maxUid = null;
    for (用户数据 userData : list) {
      Integer uid = userData.getUid();
      if (uid != null && (maxUid == null || uid > maxUid)) {
        maxUid = uid;
      }
    }

    return maxUid + 1;
  }

  //前台登录验证
  @PostMapping("/login")
  public Result login(@RequestBody 用户数据 user) {
    List list = userService.lambdaQuery()
      .eq(用户数据::getUid, user.getUid())
      .eq(用户数据::get密码, user.get密码())
      .list();
    if (list.size() > 0) {
      用户数据 user1 = (用户数据) list.get(0);
      List menulist = menuService.lambdaQuery().like(Menu::get所需权限, user1.get级别()).list();
      HashMap res = new HashMap<>();
      res.put("user", user1);
      res.put("menu", menulist);
      return Result.成功(res);
    }
    return Result.失败();
  }

  //修改
  @PostMapping("/修改")
  public boolean 修改(@RequestBody 用户数据 user) {
    return userService.updateById(user);
  }


  //删除
  @GetMapping("/删除")
  public Result 删除(Integer uid) {
    return userService.removeById(uid) ? Result.成功() : Result.失败();
  }

  //查询（模糊匹配）
  @PostMapping("/模糊查询")
  //声明List类操作类型为用户数据的查询方法，将请求体的数据转为用户数据类型并赋给user
  public Result 模糊查询(@RequestBody 用户数据 user) {
    //声明LambdaQueryWrapper查询构造器对象，利用LambdaQueryWrapper查询构造器通过Lambda表达式来构建查询条件
    LambdaQueryWrapper<用户数据> lambdaQueryWrapper = new LambdaQueryWrapper();
    //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
    //eq方法为完全等于
    //对传入的参数进行非空判断
    if (StringUtils.isNotBlank(user.get用户名称())) {
      lambdaQueryWrapper.like(用户数据::get用户名称, user.get用户名称());
    }
    //利用封装类调用list方法并传入上面的查询条件进行数据封装并返回
    return Result.成功(userService.list(lambdaQueryWrapper));
  }

  @PostMapping("/模糊查询并手写分页")
  //模糊查询并手写分页方法
  public List 模糊查询并手写分页(@RequestBody 查询分页参数 分页参数) {
    //声明键值对类变量"分页数据封装"并获取"分页参数"类的"分页数据封装"变量
    HashMap 分页数据封装 = 分页参数.get分页数据封装();
    //声明字符串类的变量"用户名称"并获取"分页数据封装"变量的”用户名称“属性(此时用户传入的数据已经存储到分页参数实体类内)
    String 用户名称 = (String) 分页数据封装.get("用户名称");
    //声明Page类并传入用户数据类作为泛型操作的Page对象，名为分页
    Page<用户数据> 分页 = new Page();
    //设置分页对象的页数为获取分页参数类的页数
    分页.setCurrent(分页参数.get页数());
    //设置分页对象的每页数据为获取分页参数类的每页数据
    分页.setSize(分页参数.get每页数据());
    //设置当前页页数，不设置默认最后一页
//        分页.setPages(2);
    //声明LambdaQueryWrapper查询构造器对象，利用LambdaQueryWrapper查询构造器通过Lambda表达式来构建查询条件
    LambdaQueryWrapper<用户数据> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
    lambdaQueryWrapper.like(用户数据::get用户名称, 用户名称);
    //IPage为封装分页查询结果的接口，用来表示分页查询的结果集；调用userService接口的pageCC方法（将模糊查询的条件和获取的分页参数传入）
    IPage 分页结果 = userService.pageCC(分页, lambdaQueryWrapper);//声明“分页查询接口”的变量并将模糊查询的结果使用”分页“变量进行分页的结果赋值
    //调用IPage接口的getRecords方法获取”分页结果“的当前页数据
    return 分页结果.getRecords();
  }

  @PostMapping("/模糊查询并分页进行数据封装")
  //模糊查询并手写分页方法并调用封装方法
  public Result 模糊查询并分进行数据封装(@RequestBody 查询分页参数 分页参数) {
    //声明键值对类变量"分页数据封装"并获取"分页参数"类的"分页数据封装"变量
    HashMap 分页数据封装 = 分页参数.get分页数据封装();

    //声明字符串类的变量"用户名称"并获取"分页数据封装"变量的”用户名称“属性(此时用户传入的数据已经存储到分页参数实体类内)
    String 搜索 = (String) 分页数据封装.get("搜索");
    String 性别 = (String) 分页数据封装.get("性别");
    String 级别 = (String) 分页数据封装.get("级别");


    //声明Page类并传入用户数据类作为泛型操作的Page对象，名为分页
    Page<用户数据> 分页 = new Page();

    //设置分页对象的页数为获取分页参数类的页数
    分页.setCurrent(分页参数.get页数());

    //设置分页对象的每页数据为获取分页参数类的每页数据
    分页.setSize(分页参数.get每页数据());


    //设置当前页页数，不设置默认最后一页
//        分页.setPages(2);
    //声明LambdaQueryWrapper查询构造器对象，利用LambdaQueryWrapper查询构造器通过Lambda表达式来构建查询条件
    LambdaQueryWrapper<用户数据> lambdaQueryWrapper = new LambdaQueryWrapper<>();

    //判断是否不为空并且也不等于null字符
    if (StringUtils.isNotBlank(搜索) && !"null".equals(搜索)) {
      //调用LambdaQueryWrapper对象的like方法进行构造模糊查询条件，用法(实体类::get实体类的某个属性,传入的参数.传入参数的某个属性)
      lambdaQueryWrapper.like(用户数据::get用户名称, 搜索).or().like(用户数据::get电话, 搜索).or().like(用户数据::getUid, 搜索).or().like(用户数据::get年龄, 搜索);
    }

    if (StringUtils.isNotBlank(性别) && !"null".equals(性别)) {
      lambdaQueryWrapper.eq(用户数据::get性别, 性别);
    }

    if (StringUtils.isNotBlank(级别) && !"null".equals(级别)) {
      lambdaQueryWrapper.eq(用户数据::get级别, 级别);
    }

    //IPage为封装分页查询结果的接口，用来表示分页查询的结果集；调用userService接口的pageCC方法（将模糊查询的条件和获取的分页参数传入）
    IPage 分页结果 = userService.pageCC(分页, lambdaQueryWrapper);//声明“分页查询接口”的变量并将模糊查询的结果使用”分页“变量进行分页的结果赋值

    //调用Result类的“成功”方法并传入（分页结果的当前页数据，分页结果的数据总数）
    return Result.成功(分页结果.getRecords(), 分页结果.getTotal());
  }

}
