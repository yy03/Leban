package com.yy.yeb.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yy.yeb.entity.Employee;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.entity.RespPageBean;
import com.yy.yeb.entity.Salary;
import com.yy.yeb.service.EmployeeService;
import com.yy.yeb.service.SalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("获取所工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation("分页获取员工账套")
    @GetMapping("/")
    public RespPageBean getEmployWithSalary(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployWithSalary(current, size);
    }

    @ApiOperation("更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId", sid).eq("id", eid))) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
