package com.example.city_picker.controllers;


import com.example.city_picker.models.Area;
import com.example.city_picker.models.Village;
import com.example.city_picker.utils.AreaService;
import com.example.city_picker.utils.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VillageController {

    @Autowired
    private VillageService villageService;

    @PostMapping("/city_picker/getVillages")
    public ResponseEntity<List<Village>> getByTownCd(@RequestBody Area area) {
        try {  // 先取出area
            List<Village> villageList = villageService.getVillages(area.getHsnCd(), area.getTownCd());
            if (villageList == null) {
                return new ResponseEntity<List<Village>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Village>>(villageList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Village>>(HttpStatus.NOT_FOUND);
        }
    }
}
