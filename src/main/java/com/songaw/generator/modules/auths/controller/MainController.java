package com.songaw.generator.modules.auths.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/27 9:52
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String root(){
        return "redirect:/index.html";
    }


    @GetMapping("/api")
    public String api(){
        return "redirect:/swagger-ui.html";
    }

}
