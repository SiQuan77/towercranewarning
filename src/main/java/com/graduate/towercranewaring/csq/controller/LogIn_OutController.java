package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.service.DriverServiceImpl;
import com.graduate.towercranewaring.csq.service.EquipmentService;
import com.graduate.towercranewaring.csq.service.EquipmentServiceImpl;
import com.graduate.towercranewaring.csq.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: LogIn_OutController
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Controller
public class LogIn_OutController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DriverServiceImpl driverService;

    @Autowired
    private EquipmentServiceImpl equipmentService;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password
            , Model model, HttpSession session){
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();

        //封装用户的登录数据进入token中
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);

        try {
            subject.login(usernamePasswordToken);//执行登录方法，如果没有异常就说明ok了！
            //具体的登录方法在UserRealm中的认证方法中
            session.setAttribute("currentUser",subject.getPrincipal());
            session.setAttribute("sum_of_equip",equipmentService.getAllEquip().size());
            session.setAttribute("sum_of_driver",driverService.getAllDriver().size());
            return "index";
        }catch (UnknownAccountException e){//说明用户名不存在
            model.addAttribute("msg","用户名不存在！");
            return "login";
        }catch (IncorrectCredentialsException e){//说明密码错误！
            model.addAttribute("msg","密码错误！");
            return "login";
        }

    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject=SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();//session会销毁
        }
        return "/login";
    }


}
