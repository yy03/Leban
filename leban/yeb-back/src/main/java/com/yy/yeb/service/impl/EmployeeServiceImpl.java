package com.yy.yeb.service.impl;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.yeb.entity.*;
import com.yy.yeb.mapper.EmployeeMapper;
import com.yy.yeb.mapper.MailLogMapper;
import com.yy.yeb.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailLogMapper mailLogMapper;

    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean maxWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workId)"));
        Map<String, Object> stringObjectMap = maps.get(0);
        String workId = stringObjectMap.get("max(workId)").toString();
        Integer newWorkId = Integer.parseInt(workId) + 1;
        return RespBean.success(null, String.format("%08d", newWorkId));
    }

    @Override
    public RespBean addEmployee(Employee employee) {
        LocalDate begincontract = employee.getBegincontract();
        LocalDate endcontract = employee.getEndcontract();
        long days = begincontract.until(endcontract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractterm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (employeeMapper.insert(employee) == 1) {
            Employee emp = employeeMapper.exportEmployee(employee.getId()).get(0);
//            数据库记录发送的消息
            String msgId = UUID.randomUUID().toString();
//            String msgId = "123456";
            MailLog mailLog = new MailLog();
            mailLog.setMsgid(msgId);
            mailLog.setEid(emp.getId());
            mailLog.setStatus(0);
            mailLog.setRoutekey(MailConstants.MAIL_ROUTINGKEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTrytime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreatetime(LocalDateTime.now());
            mailLog.setUpdatetime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);

            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTINGKEY_NAME, emp, new CorrelationData(msgId));
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Override
    public List<Employee> exportEmployee(Integer id) {
        return employeeMapper.exportEmployee(id);
    }

    @Override
    public RespPageBean getEmployWithSalary(Integer current, Integer size) {
        Page<Employee> page = new Page<>(current, size);
        IPage<Employee> iPage= employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(iPage.getTotal(), iPage.getRecords());
        return respPageBean;


    }
}
