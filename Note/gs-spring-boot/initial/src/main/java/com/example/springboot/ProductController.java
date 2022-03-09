package com.example.springboot;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

//    @GetMapping("/1")
//    public Product index() {
//        Product p1 = new Product("Tim", 100, "帥");
//        Product p2 = new Product("Peter", 200, "聰明");
//        Product p3 = new Product("Derek", 300, "酷");
//        Product p4 = new Product("Kevin", 400, "太快了");
//        Product p5 = new Product("Wilson", 500, "認真");
//        ArrayList<Product> productList = new ArrayList<Product>();
//        productList.add(p1);
//        productList.add(p2);
//        productList.add(p3);
//        productList.add(p4);
//        productList.add(p5);
//        return p1;
//    }
    @GetMapping("/1")
    public ArrayList<Product> index1() {
        Product p1 = new Product("Tim", 100, "帥");
        Product p2 = new Product("Peter", 200, "聰明");
        Product p3 = new Product("Derek", 300, "酷");
        Product p4 = new Product("Kevin", 400, "太快了");
        Product p5 = new Product("Wilson", 500, "認真");
        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        return productList;
    }

    @GetMapping("/2")
    public List<Product> index2(){
        Product p = new Product();
        p.setName("tom");
        p.setHint("特帥");
        p.setPrice(100);
        List<Product> list2 = new ArrayList<Product>();
        list2.add(p);
        return list2;
    }

}
