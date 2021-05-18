package com.yy.yeb.service;

import com.yy.yeb.entity.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
public interface MenuRoleService extends IService<MenuRole> {

    //更新菜单
    RespBean updateMenuRoel(Integer rid, Integer[] mids);
}
