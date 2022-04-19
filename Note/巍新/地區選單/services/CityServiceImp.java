package com.example.city_picker.services;

import com.example.city_picker.models.City;
import com.example.city_picker.utils.CityRepository;
import com.example.city_picker.utils.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        List<City> cityList = cityRepository.findAll();
//        System.out.println("imp:" + cityList.toString());
        return cityList;
    }
}
