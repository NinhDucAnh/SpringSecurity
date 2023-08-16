package com.example.method_authorization.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {
    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')")
    String hello1(){
        return "Hello";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write','read')")
    String hello2(){
        return "Hello2";
    }

//    @GetMapping("/demo/{smth}")
//    @PreAuthorize("#something == authentication.name")
//    public String hello3(@PathVariable("smth") String something){
//        return "Hello3";
//    }

    @GetMapping("/demo4/{smth}")
    @PreAuthorize("@conditionEvaluator.condition()")
    public String hello3(@PathVariable("smth") String something){
        return "Hello3";
    }

    @GetMapping("/demo5")
    @PostAuthorize("returnObject != 'Hello5'")
    public String hello5(){
        System.out.println(":)))");
        return "Hello5";
    }

    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String hello6(@RequestBody List<String> values){
        System.out.println(values);
        return "Hello6";
    }


}
