package com.yy.yeb.mapper;

import com.yy.yeb.entity.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertReocrd(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
