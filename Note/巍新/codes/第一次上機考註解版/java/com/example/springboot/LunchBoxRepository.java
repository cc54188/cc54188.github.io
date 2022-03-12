package com.example.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// java object轉成資料庫格式
public interface LunchBoxRepository extends JpaRepository<LunchBox, Long> {

}
