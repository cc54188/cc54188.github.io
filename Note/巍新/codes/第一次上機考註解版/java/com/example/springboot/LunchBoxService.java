package com.example.springboot;

import java.util.List;

public interface LunchBoxService {
    List<LunchBox> getAll();
    void saveLunchBox(LunchBox lunchBox);
    LunchBox getById(Long id);
    void putById(Long id, int amount);
    void deleteById(Long id);
}
