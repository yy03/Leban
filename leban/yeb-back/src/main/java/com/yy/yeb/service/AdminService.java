package com.yy.yeb.service;

import com.yy.yeb.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.entity.Menu;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
public interface AdminService extends IService<Admin> {

//    登录之后返回token
    RespBean login(String username, String password, String code, HttpServletRequest request);

//    根据用户名获取用户
    Admin getAdminByUserName(String username);

    //根据用户id查询角色列表
    List<Role> getRolesByAdminId(Integer adminId);

//    获取所有操作员
    List<Admin> getAllAdmins(String keyWords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
