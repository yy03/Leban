package com.yy.yeb;

import com.yy.yeb.entity.Menu;
import com.yy.yeb.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMenu {
    @Autowired
    MenuMapper menuMapper;
    @Test
    void testGetMenuByAdminId() {
        List<Menu> menuList = menuMapper.getMenuByAdminId(1);
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }

    @Test
    void test2() {
        List<Menu> menusWithRole = menuMapper.getMenusWithRole();
        for (Menu menu : menusWithRole) {
            System.out.println(menu);

        }
    }

}
