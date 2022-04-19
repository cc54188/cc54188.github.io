package com.example.city_picker.controllers;

import com.example.city_picker.models.City;
import com.example.city_picker.utils.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city_picker/getAll")
    public ResponseEntity<List<City>> getAll() {
        System.out.println("這邊");
        try {
            List<City> cityList = cityService.getAll();
            if (cityList == null) {
                return new ResponseEntity<List<City>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<City>>(HttpStatus.NOT_FOUND);
        }
    }
}
