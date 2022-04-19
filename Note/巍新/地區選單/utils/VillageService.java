package com.example.city_picker.utils;

import com.example.city_picker.models.Village;

import java.util.List;

public interface VillageService {

    public List<Village> getVillages(String hsnCd, String townCd);
}
