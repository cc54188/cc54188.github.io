package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody  // 這個類的所有方法返回的數據直接寫給瀏覽器，若是對象則轉成json
//@Controller    // 這是一個controller類
//@RestController  // 等於上面兩個@的相加
public class HelloWorldController {

    @RequestMapping("/hello")  // 接收來自瀏覽器的"hello"請求
    public String hello() {
        return "Hello World";
    }
}
