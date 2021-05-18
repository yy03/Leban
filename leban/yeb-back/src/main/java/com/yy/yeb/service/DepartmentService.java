package com.yy.yeb.service;

import com.yy.yeb.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.entity.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
public interface DepartmentService extends IService<Department> {

    List<Department> getAllDepartments();

    RespBean addDepartment(Department department);

    RespBean delDepartment(Integer id);
}
