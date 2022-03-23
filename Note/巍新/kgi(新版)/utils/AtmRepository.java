package com.example.demo.utils;

import com.example.demo.models.Atm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtmRepository extends JpaRepository<Atm, Long> {

    public List<Atm> getByUserName(String userName);
}

