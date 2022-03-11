package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/getAll")
    public List<Product> list() {
        return productService.getAll();
    }

    @GetMapping("/products/get/{id}")
    public ResponseEntity<Product> getById(@PathVariable(value = "id")Long id) {
        try{
            Product product = productService.getById(id);
            if(product == null) {
                return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products/add")
    public void add(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @PutMapping("/products/put/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable(value = "id")Long id) {
        try{
            Product existProduct = productService.getById(id);
            if(existProduct == null) {
                return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
            }
            productService.saveProduct(product);
            return new ResponseEntity<Product>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/delete/{id}")
    public void delete(@PathVariable(value = "id")Long id) {
        productService.deleteById(id);
    }
}
