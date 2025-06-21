package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.用户数据;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LHX
 * @since 2023-12-14
 */
@Mapper
public interface 用户数据Mapper extends BaseMapper<用户数据> {

    IPage pageC(Page<用户数据> 分页);


    //@Param(Constants.WRAPPER) 表示将方法中的 wrapper 参数与 MyBatis-Plus 中的 Constants.WRAPPER 对象进行映射。Constants.WRAPPER 是一个常量，用于表示查询条件的封装对象。
    //通过使用该注解，可以使得 SQL 语句中的参数名与方法参数名一致，从而避免由于参数名不一致导致的错误。例如，如果 SQL 语句中的参数名是 ${wrapper}, 那么使用该注解后，方法中的 wrapper 参数就会自动映射为 ${wrapper}。
    IPage pageCC(Page<用户数据> 分页, @Param(Constants.WRAPPER) Wrapper wrapper);


}
