package com.example.demo.utils;

import com.example.demo.models.Atm;

import java.util.List;

public interface AtmService {

    public List<Atm> getByUserName(String userName);
    public void saveAtm(Atm[] atms);
    public void delete(Long id);
}

