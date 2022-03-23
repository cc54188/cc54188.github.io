package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Atm;
import com.example.demo.utils.AtmRepository;
import com.example.demo.utils.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AtmServiceImp implements AtmService {

    @Autowired
    private AtmRepository atmRepository;

    // 用名字取出各項交易
    @Override
    public List<Atm> getByUserName(String userName) {
        return atmRepository.getByUserName(userName);
    }

    // 存入各項交易
    @Override
    public void saveAtm(Atm[] atms) {
        for(int i = 0; i < atms.length; i++) {
            atmRepository.save(atms[i]);
        }
    }

    // 刪除交易
    @Override
    public void delete(Long id) {
        atmRepository.deleteById(id);
    }
}
