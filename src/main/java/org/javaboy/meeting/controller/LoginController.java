package org.javaboy.meeting.controller;

import org.javaboy.meeting.model.Department;
import org.javaboy.meeting.model.Employee;
import org.javaboy.meeting.service.DepartmentService;
import org.javaboy.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model, HttpSession httpSession) {

        Employee employee = employeeService.doLogin(username, password);

        if (employee == null) {
            model.addAttribute("error", "用户名或密码输入错误，登录失败");
            //请求转发
            return "forward:/";
        } else {
            //来到这里说明由这个用户，但是要看状态
            if (employee.getStatus() == 0) {
                model.addAttribute("error", "用户待审批");
                //重定向和服务器跳转
                return "forward:/";
            } else if (employee.getStatus() == 2) {
                model.addAttribute("error", "用户审批未通过");
                return "forward:/";
            } else {
                //ok,把用户的登陆信息存放在session里面
                httpSession.setAttribute("currentuser", employee);
                //登陆成功就重定向到notifications页面
                return "redirect:/notifications";
            }
        }
    }

    @RequestMapping("/register")
    public String register(Model model) {
        List<Department> deps = departmentService.getAllDeps();
        model.addAttribute("deps", deps);
        return "register";
    }

    @RequestMapping("/doReg")
    public String doReg(Employee employee,Model model) {

        Integer result = employeeService.doReg(employee);
        if (result == 1) {
            //注册成功就去登陆页面
            return "redirect:/";
        } else {
            //回带信息
            model.addAttribute("error", "注册失败");
            model.addAttribute("employee", employee);
            //失败回显一些数据到注册页面
            return "forward:/register";
        }
    }

}
