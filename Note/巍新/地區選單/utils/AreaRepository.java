package com.example.city_picker.utils;

import com.example.city_picker.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    public List<Area> getByHsnCd(String hsnCd);
}
