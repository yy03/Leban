package com.yy.yeb.service.impl;

import com.yy.yeb.entity.Department;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.mapper.DepartmentMapper;
import com.yy.yeb.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if (1 == department.getResult()) {
            return RespBean.success("添加成功！", department);
        }
        return RespBean.error("添加失败！");
    }

    @Override
    public RespBean delDepartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.delDepartment(department);
        if (department.getResult() == -2) {
            return RespBean.error("该部门下还有子部门，删除失败！");
        }
        if (department.getResult() == -1) {
            return RespBean.error("该部门下还有员工，删除失败！");
        }
        if (department.getResult() == 1) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
