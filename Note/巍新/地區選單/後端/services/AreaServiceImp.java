package com.example.city_picker.services;

import com.example.city_picker.models.Area;
import com.example.city_picker.utils.AreaRepository;
import com.example.city_picker.utils.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImp implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<Area> getByHsn_cd(String hsnCd) {
        return areaRepository.getByHsnCd(hsnCd);
    }

    @Override
    public List<Area> getAll() {
        return areaRepository.findAll();
    }
}
