package com.yy.yeb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.yeb.entity.Menu;
import com.yy.yeb.entity.MenuRole;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.entity.Role;
import com.yy.yeb.service.MenuRoleService;
import com.yy.yeb.service.MenuService;
import com.yy.yeb.service.RoleService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
    权限组
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiModelProperty("删除角色")
    @DeleteMapping("/role/{id}")
    public RespBean delRole(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid)).stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation("更新角色菜单")
    @PutMapping("/")
    public RespBean udpateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRoel(rid, mids);
    }
}
