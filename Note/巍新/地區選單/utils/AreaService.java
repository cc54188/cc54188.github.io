package com.example.city_picker.utils;

import com.example.city_picker.models.Area;

import java.util.List;

public interface AreaService {

    public List<Area> getByHsn_cd(String hsn_sd);

    public List<Area> getAll();
}
