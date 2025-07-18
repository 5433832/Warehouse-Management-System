package com.wms.controller;


import com.wms.common.Result;
import com.wms.entity.Menu;
import com.wms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LHX
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
  @Autowired
  private MenuService menuService;

  @GetMapping("/list")
  public Result list(@RequestParam String 级别) {
    List list = menuService.lambdaQuery().like(Menu::get所需权限, 级别).list();
    return Result.成功(list);
  }
}
