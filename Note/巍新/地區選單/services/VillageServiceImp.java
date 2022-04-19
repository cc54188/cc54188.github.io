package com.example.city_picker.services;

import com.example.city_picker.models.Village;
import com.example.city_picker.utils.VillageRepository;
import com.example.city_picker.utils.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VillageServiceImp implements VillageService{

    @Autowired
    private VillageRepository villageRepository;

    @Override
    public List<Village> getVillages(String hsnCd, String townCd) {
        List<Village> villages = villageRepository.getByHsnCd(hsnCd);
        List<Village> villageList = new ArrayList<Village>();
        for (int i = 0; i < villages.size(); i++) {
            if (villages.get(i).getTownCd().equals(townCd)) {
                villageList.add(villages.get(i));
            }
        }
        return villageList;
    }
}
