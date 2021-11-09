package com.dammak.project401.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class test {

    @GetMapping("/")
    public String getRoot() {
        return "Home";
    }
    @GetMapping("/nav")
    public String getavvt() {
        return "navBar";
    }

    @GetMapping("/footer")
    public String getasdasdt() {
        return "footer";
    }

    @GetMapping("/login")
    public String gett() {
        return "login";
    }

    @GetMapping("/signup")
    public String getsingup() {
        return "signup";
    }

    @GetMapping("/aboutus")
    public String getsssingup() {
        return "about";
    }

    @GetMapping("/dashboard")
    public String getsssinadgup() {
        return "admin";
    }

    @GetMapping("/dashboard/add")
    public String asasdasd() {
        return "addhospital";
    }

    @GetMapping("/test")
    public String asassaddasd() {
        return "test";
    }

    @GetMapping("/nearHospital")
    public String nearHospital() {
        return "nearHospital";
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "userProfile";
    }

}
