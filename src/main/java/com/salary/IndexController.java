package com.salary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "hello world";
    }

    @GetMapping("health-check")
    public String checkHealth(){
        return "health is ok";
    }
}
