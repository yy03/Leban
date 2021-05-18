package com.yy.yeb.service;

import com.yy.yeb.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.entity.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
public interface EmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);


    RespBean maxWorkId();


    RespBean addEmployee(Employee employee);

    List<Employee> exportEmployee(Integer id);

    RespPageBean getEmployWithSalary(Integer current, Integer size);
}
