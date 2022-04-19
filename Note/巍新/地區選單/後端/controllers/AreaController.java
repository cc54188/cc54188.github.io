package com.example.city_picker.controllers;

import com.example.city_picker.models.Area;
import com.example.city_picker.models.City;
import com.example.city_picker.utils.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/city_picker/getByHsnCd")
    public ResponseEntity<List<Area>> getByHsnCd(@RequestBody City city) {
        try {
            List<Area> areaList = areaService.getByHsn_cd(city.getHsnCd());
            if (areaList == null) {
                return new ResponseEntity<List<Area>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Area>>(areaList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Area>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("city_picker/getAllAreas")
    public ResponseEntity<List<Area>> getAll() {
        try {
            List<Area> areaList = areaService.getAll();
            if (areaList == null) {
                return new ResponseEntity<List<Area>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Area>>(areaList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Area>>(HttpStatus.NOT_FOUND);
        }
    }
}
