package com.example.demo.utils;

import com.example.demo.models.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface AtmRepository extends JpaRepository<Atm,Long> {

    public List<Atm> getByIdno(String idno);

}

