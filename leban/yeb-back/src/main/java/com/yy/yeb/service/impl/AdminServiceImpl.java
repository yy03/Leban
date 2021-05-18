package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.yeb.config.JwtTokenUtils;
import com.yy.yeb.entity.*;
import com.yy.yeb.mapper.AdminMapper;
import com.yy.yeb.mapper.AdminRoleMapper;
import com.yy.yeb.mapper.MenuMapper;
import com.yy.yeb.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码错误，请重新输入");
        }
//        登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员！");
        }
//        更新用户登录对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

//        生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);

    }

    @Override
    public Admin getAdminByUserName(String username) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
        return admin;

    }

    @Override
    public List<Role> getRolesByAdminId(Integer adminId) {
        return menuMapper.getRolesByAdminId(adminId);
    }

    @Override
    public List<Admin> getAllAdmins(String keyWords) {
        Admin principal = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminMapper.getAllAdmins(principal.getId(), keyWords);
    }

    @Transactional
    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (result == rids.length) {
            return RespBean.success("修改成功！");
        }
        return RespBean.error("修改失败！");

    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        if (passwordEncoder.matches(oldPass, admin.getPassword())) {
            String encode = passwordEncoder.encode(pass);
            admin.setPassword(encode);
            int i = adminMapper.updateById(admin);
            System.out.println("i = " + i);
            if (i == 1) {
                return RespBean.success("修改成功！");
            }
        }
        return RespBean.error("修改失败！");
    }


}
