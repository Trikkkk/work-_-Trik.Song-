package com.trik.controller;


import com.trik.dao.MsgDao;
import com.trik.entity.MsgEntity;
import com.trik.dao.UserDao;
import com.trik.entity.UserEntity;
import com.trik.service.serviceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



import javax.servlet.http.HttpServletRequest;



import java.util.List;
import java.util.Date;
/**
 * Created by Trik.Song on 2017/11/27
 */
@Controller

public class AllController {

    @Autowired
    private UserDao userDao;
    @Autowired
     MsgDao msgDao;
    @Autowired
    private serviceInterface serviceInterface;



    //注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(MD5.getMD5(password));
            userDao.save(userEntity);
            return "login";
        } else {
            return "register";
        }
    }



    //登录方法
    @RequestMapping("/login1")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserEntity userEntity = userDao.findByUsernameAndPassword(username, password);
        UserEntity usernameEntity=userDao.findByUsername(username);
        int attempt = 0 ;
        attempt = usernameEntity.getAttempt();
        System.out.println(attempt);
        String str = "";
        String password1="";
        System.out.println("2345"+password1);

        //判断用户名是否对应
        if (usernameEntity != null) {
            System.out.println("11111111111111111111"+attempt);
            password1=usernameEntity.getPassword();
            System.out.println("password"+password1);
        //判断密码是否对应
         if(password1.equals(password)){
             attempt=usernameEntity.getAttempt();
             System.out.println("2222222222222222"+attempt);
             if(attempt<3){
                 usernameEntity.setAttempt(0);
                 userDao.save(usernameEntity);
                 str="redirect:/showmessage";
             }else{

                 //如果间隔时间超过1天，attempt清零
                 Date time1=usernameEntity.getLogintime();
                 Date time2=new Date();

                 if(time2.getTime()-time1.getTime()>60*60*24*1000){
                     usernameEntity.setAttempt(0);
                     userDao.save(usernameEntity);
                     str="redirect:/showmessage";
                 }

                 usernameEntity.setAttempt(0);
                 userDao.save(usernameEntity);
                 str="redirect:/showmesasge";
             }
         }
         else {
             //密码错误时执行的方法
             attempt=usernameEntity.getAttempt();
             if (attempt <3) {
                 attempt +=1;
                 usernameEntity.setAttempt(attempt);
                 userDao.save(usernameEntity);
                 str = "redirect:/login";

             }else{
                 //把当前时间存入数据库
                 Date date1=new Date();
                 usernameEntity.setLogintime(date1);
                 userDao.save(usernameEntity);
                 str= "error";
             }
         }
        }
        return str;
    }

    //消息页面
    @RequestMapping("/showmessage")
    public String showmessage(Model model) {
        List<MsgEntity> messages = serviceInterface.getMsgEntityList();
        model.addAttribute("messages", messages);
        return "showmessage";
    }

    //删除方法
    @RequestMapping("/delete")
    public String delete(Long id) {
        serviceInterface.delete(id);
        return "redirect:/showmessage";
    }

    //传递到修改页
    @RequestMapping("/send")
    public String send(Model model,Long id){
        MsgEntity msgEntity=serviceInterface.findMsgEntityById(id);
        model.addAttribute("messages",msgEntity);
        return "message";
    }

    //修改方法
    @RequestMapping("/write")
    public String write(MsgEntity msgEntity){
        serviceInterface.save(msgEntity);
        return "redirect:/showmessage";
    }



}
