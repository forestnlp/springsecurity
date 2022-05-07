package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @RequestMapping("/")
    @ResponseBody
    public String demo(){
        return "demo";
    }

    @RequestMapping("/showLogin")
    public String showlogin(){
        return "login";
    }

    @RequestMapping("/showMain")
    public String showLogin(){
        return "main";
    }

    @RequestMapping("/showFail")
    public String showFail(){
        return "fail";
    }

    @RequestMapping("/showAccessDenied")
    public String showAccessDenied(){
        return "accessdenied";
    }

    @RequestMapping("/jczl")
    @ResponseBody
    public String jczl(){
        return "jczl";
    }

}
