package com.yy.yeb.mapper;

import com.yy.yeb.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.entity.RespBean;
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
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
