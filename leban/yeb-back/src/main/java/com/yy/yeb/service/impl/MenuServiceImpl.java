package com.yy.yeb.service.impl;

import com.yy.yeb.entity.Admin;
import com.yy.yeb.entity.Menu;
import com.yy.yeb.mapper.MenuMapper;
import com.yy.yeb.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public List<Menu> getMenuByAdminId() {

        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        ValueOperations operations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) operations.get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenuByAdminId(adminId);
            operations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    //获取菜单列表和角色
    @Override
    public List<Menu> getMenusWithRole() {
       return menuMapper.getMenusWithRole();
    }

    //查询所有菜单
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();

    }
}
