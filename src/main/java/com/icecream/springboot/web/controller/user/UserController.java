package com.icecream.springboot.web.controller.user;

import com.icecream.springboot.domain.User;
import com.icecream.springboot.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//https://blog.csdn.net/xwd718/article/details/80660100
//https://blog.csdn.net/xwd718/article/details/80682548
/*Thymeleaf 标准表达式语法
            以th 属性开头，例如 < span th : text= ”…”>
            变量表达式： $｛……｝
            选择表达式：＊｛……｝
            消息表达式：＃｛……｝
            链接表达到： ＠｛……｝
            分段表达式： ～｛……｝*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //查询所有用户
    @GetMapping("/userlist")
    public ModelAndView userList(Model model){
        model.addAttribute("userList",userRepository.userList());
        model.addAttribute("title","用户管理");
        return new ModelAndView("user/list","userModel",model);
    }
    //根据id 查询用户
    @GetMapping("{id}")
    public ModelAndView getUser(@PathVariable("id") Long id,Model model){
        User user = userRepository.getUserById(id);
        model.addAttribute("user",user);//user.get()
        model.addAttribute("title","查看用户");
        return new ModelAndView("user/view","userModel",model);
    }
    //获取创建表单页面
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new  User(null,null,null,null));
        model.addAttribute("title","创建用户");
        return new ModelAndView("user/form","userModel",model);
    }
    //保存用户
    @PostMapping
    public ModelAndView saveOrUpdateUser(User user){
        user = userRepository.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/user/userlist");
    }
    //删除用户
    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id){
        userRepository.deleteUser(id);
        return new ModelAndView("redirect:/user/userlist");
    }
    //修改用户界面
    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Long id,Model model){
        User user = userRepository.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("title","编辑用户");
        return new ModelAndView("user/form","userModel",model);
    }
}
