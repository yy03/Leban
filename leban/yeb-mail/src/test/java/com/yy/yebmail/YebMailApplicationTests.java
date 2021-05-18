package com.yy.yebmail;

import com.yy.yeb.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YebMailApplicationTests {


    @Test
    void contextLoads() {
        Employee employee = new Employee();
        employee.setName("张三");
        System.out.println("employee = " + employee);
    }

}
