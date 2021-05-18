package com.yy.yeb.mapper;

import com.yy.yeb.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keyWords") String keyWords);
}
