package org.javaboy.meeting.service;

import org.javaboy.meeting.mapper.EmployeeMapper;
import org.javaboy.meeting.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {
    //注入DAO层
    @Autowired
    EmployeeMapper employeeMapper;

    public Employee doLogin(String username, String password) {

        Employee employee = employeeMapper.loadEmpByUsername(username);
        //当用户名不存在或者密码不对时就返回null
        if (employee == null || !employee.getPassword().equals(password)) {
            return null;
        }
        //否则返回
        return employee;
    }

    public Integer doReg(Employee employee) {
        //确保用户名不重复
        Employee emp = employeeMapper.loadEmpByUsername(employee.getUsername());
        if (emp != null) {
            return -1;
        }

        employee.setRole(1);
        employee.setStatus(0);
        return employeeMapper.doReg(employee);
    }

    //根据状态获取没有审批全部用户
    public List<Employee> getAllEmpsByStatus(Integer status) {
        return employeeMapper.getAllEmpsByStatus(status);
    }

    //更新用户申请会议室的状态
    public Integer updatestatus(Integer employeeid, Integer status) {
        return employeeMapper.approveaccount(employeeid,status);
    }

    public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        return employeeMapper.getAllEmps(employee, page, pageSize);
    }

    public Long getTotal(Employee employee) {
        return employeeMapper.getTotal(employee);
    }

    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeMapper.getEmpsByDepId(depId);
    }
}
