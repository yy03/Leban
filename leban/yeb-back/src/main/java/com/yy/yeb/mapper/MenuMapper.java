package com.yy.yeb.mapper;

import com.yy.yeb.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByAdminId(Integer id);

    List<Menu> getMenusWithRole();

    List<Role> getRolesByAdminId(Integer adminId);

    List<Menu> getAllMenus();
}
