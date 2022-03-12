package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 把傳入的參數與資料庫做連結
@Service  // 位於controller和model之間，解耦用
public class LunchBoxServiceImp implements LunchBoxService{ // 實現該介面

    @Autowired  // 通過@Autowired自動產生物件實體(不用我們new一個實體)
    private LunchBoxRepository lunchBoxRepository;

    @Override  // 一般為覆寫父類別方法，這邊是實現介面的抽象方法
    public List<LunchBox> getAll() {
        return this.lunchBoxRepository.findAll();
    }

    @Override  // 把傳入的lunchBox存入資料庫
    public void saveLunchBox(LunchBox lunchBox) {
        lunchBoxRepository.save(lunchBox);
    }

    @Override
    public LunchBox getById(Long id) {
        // 用Optional包裝是為了防止開發者對null操作
        Optional<LunchBox> optionalLunchBox = lunchBoxRepository.findById(id);
        LunchBox lunchBox = null;  // 初始化
        if(optionalLunchBox.isPresent()) {  // 如果這個包裝下的物件不是null
            lunchBox = optionalLunchBox.get();  // 才get此物件存給lunchBox變數
        }
        return lunchBox;
    }

    @Override
    public void putById(Long id, int buyAmount) {
        Optional<LunchBox> optionalLunchBox = lunchBoxRepository.findById(id);
        LunchBox lunchBox = null;
        if(optionalLunchBox.isPresent()) {
            lunchBox = optionalLunchBox.get();  // 同上
            // 設定此物件的amount為(原amount - 傳入的amount(購買數量))
            lunchBox.setAmount(lunchBox.getAmount() - buyAmount);
            lunchBoxRepository.save(lunchBox);  // 改完後，在對應回資料庫
        }
    }

    @Override
    public void deleteById(Long id) {
        this.lunchBoxRepository.deleteById(id);
    }


}
