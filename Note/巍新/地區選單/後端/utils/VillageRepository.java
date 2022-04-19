package com.example.city_picker.utils;

import com.example.city_picker.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VillageRepository extends JpaRepository<Village, Long> {

    public List<Village> getByHsnCd(String hsnCd);
}
