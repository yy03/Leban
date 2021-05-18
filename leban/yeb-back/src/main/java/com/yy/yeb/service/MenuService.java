package com.yy.yeb.service;

import com.yy.yeb.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
public interface MenuService extends IService<Menu> {

    //通过用户id获取菜单列表
    List<Menu> getMenuByAdminId();

    //获取菜单列表和角色
    List<Menu> getMenusWithRole();

    //查询所有菜单
    List<Menu> getAllMenus();
}
