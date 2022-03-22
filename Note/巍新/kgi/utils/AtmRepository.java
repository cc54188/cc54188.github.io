package com.example.kgi.utils;

import com.example.kgi.models.Atm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<Atm, Long> {
}
