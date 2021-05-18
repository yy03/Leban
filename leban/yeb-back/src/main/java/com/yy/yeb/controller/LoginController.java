package com.yy.yeb.controller;


import com.yy.yeb.entity.Admin;
import com.yy.yeb.entity.AdminLogin;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLogin adminLogin, HttpServletRequest request) {
        return adminService.login(adminLogin.getUsername(), adminLogin.getPassword(), adminLogin.getCode(), request);
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRolesByAdminId(admin.getId()));
        return admin;
    }


    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("退出成功");
    }

}
