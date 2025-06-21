package com.wms.common;

import lombok.Data;

@Data
public class Result {
    private int 状态码;//返回执行结果的状态码0/1
    private String 获取数据结果;//返回结果消息成功or失败
    //Long表示长整型
    private Long 数据总和;
    //对于不知道类型的数据可以使用Object类，Object类是所有类型的基类
    private Object 数据;

    //    声明“失败”方法用来作为用户查询失败的返回信息
    public static Result 失败() {
        return 数据封装(1, "失败", 0L, null);
    }

    public static Result 成功() {
        return 数据封装(0, "成功", 0L, 0);
    }
    public static Result 成功(Object data) {
        return 数据封装(0, "成功", 0L, data);
    }

    public static Result 成功(Object data, Long 记录总和) {
        return 数据封装(0, "成功", 记录总和, data);
    }

    //    定义result方法对数据进行封装并声明多个形参
    private static Result 数据封装(int 状态码, String 获取数据结果, Long 数据总和, Object 数据) {
//        新建一个Result对象命名为res
        Result res = new Result();
//        将传入的状态码设置到res对象
        res.set状态码(状态码);
        res.set获取数据结果(获取数据结果);
        res.set数据总和(数据总和);
        res.set数据(数据);
//        返回res对象
        return res;
    }
}
