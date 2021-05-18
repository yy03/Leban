package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.yeb.entity.MenuRole;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.mapper.MenuRoleMapper;
import com.yy.yeb.service.MenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    @Transactional
    public RespBean updateMenuRoel(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (mids.length == 0 || mids == null) {
            return RespBean.success("删除成功！");
        }
        Integer insertReocrd = menuRoleMapper.insertReocrd(rid, mids);
        if (insertReocrd == mids.length) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");

    }
}
