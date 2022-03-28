package com.example.demo.controllers;

import com.example.demo.models.Atm;
import com.example.demo.utils.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AtmController {

    @Autowired
    private AtmService atmService;

    @PostMapping("/atm/getByName")
    public ResponseEntity<List<Atm>> getByName(@RequestBody Atm atm) {
        try{
            List<Atm> atmList = atmService.getByremitterIdno(atm.getRemitterIdno());
            if (atmList == null) {
                return new ResponseEntity<List<Atm>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Atm>>(atmList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<List<Atm>>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/atm/add")
    public ResponseEntity<Atm[]> add(@RequestBody Atm[] atms) {
        try {
            if(atms == null){
                return new ResponseEntity<Atm[]>(HttpStatus.NOT_FOUND);
            }
            atmService.saveAtm(atms);
            return new ResponseEntity<Atm[]>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Atm[]>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/atm/{id}")
    public void delete(@PathVariable Long id) {
        atmService.delete(id);
    }


    @GetMapping("/allAtm")
    public List<Atm> getAll(){
        return atmService.getAll();
    }


}