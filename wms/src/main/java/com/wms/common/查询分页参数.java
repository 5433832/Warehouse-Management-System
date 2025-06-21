package com.wms.common;

import lombok.Data;

import java.util.HashMap;

@Data//自动生成Getter、Setter、equals()、hashCode()、toString() 等方法
public class 查询分页参数 {
    private static int 默认每页数据 = 14;
    private static int 默认页数 = 1;

    private int 每页数据 = 默认每页数据;
    private int 页数 = 默认页数;
    // HashMap用于存储键值对的类
    private HashMap 分页数据封装 = new HashMap();
}
