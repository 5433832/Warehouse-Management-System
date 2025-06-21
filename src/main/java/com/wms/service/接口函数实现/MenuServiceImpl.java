package com.wms.service.接口函数实现;

import com.wms.entity.Menu;
import com.wms.mapper.MenuMapper;
import com.wms.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LHX
 * @since 2023-12-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
