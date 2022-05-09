package com.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    //@Secured("ROLE_管理员")
    //@PreAuthorize("hasRole('ROLE_管理员')")
    @PreAuthorize("hasAuthority('demo:update')")
    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    @RequestMapping("/showLogin")
    public String showlogin() {
        return "login";
    }

    @RequestMapping("/showMain")
    public String showLogin() {
        return "main";
    }

    @RequestMapping("/showFail")
    public String showFail() {
        return "fail";
    }

    @RequestMapping("/showAccessDenied")
    public String showAccessDenied() {
        return "accessdenied";
    }

    @RequestMapping("/jczl")
    @ResponseBody
    public String jczl() {
        return "jczl";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

}
