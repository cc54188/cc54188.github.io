package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// 負責接收前端的請求，並把參數處理好後丟給service
// 這題寫法讓這類別處理前端和後端接口
@RestController
public class LunchBoxController {

    @Autowired  // 通過@Autowired自動產生物件(不用我們new一個實體)
    private LunchBoxService lunchBoxService;

    @GetMapping("/lunch/getAll") // 處理get方法的傳入請求
    public List<LunchBox> list() {  // 取得全部的便當
        return lunchBoxService.getAll();  // 取得全部
    }

    @GetMapping("/lunch/get/{id}") // get, 帶參數id
    // ＠PathVariable是以變數作為mapping URL
    public ResponseEntity<LunchBox> getById(@PathVariable(value = "id")Long id) {
        try{  // 抓出錯誤，
            LunchBox lunchBox = lunchBoxService.getById(id);
            if(lunchBox == null) {  // null的話回傳NOT_FOUND
                return new ResponseEntity<LunchBox>(HttpStatus.NOT_FOUND);
            }    // 使用包裝用的通用處理類"ResponseEntity"處理http的返回請求
            return new ResponseEntity<LunchBox>(lunchBox, HttpStatus.OK);
        } catch (Exception e) {  // 錯誤的話也回傳NOT_FOUND
            return new ResponseEntity<LunchBox>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/lunch/add")  // 處理post方法的傳入請求
    public void add(@RequestBody LunchBox lunchBox){  // 新增便當
        lunchBoxService.saveLunchBox(lunchBox);
    }

    @PutMapping("/lunch/buy/{id}")  // 處理put方法的傳入請求
    public ResponseEntity<?> update(@RequestBody LunchBox lunchBox, @PathVariable(value = "id")Long id) {
        // @RequestBody: 讀取Http的body請求數據，並綁定到參數上(lunchBox)(所以他的"amount"是購買量)
        try{
            LunchBox existLunchBox = lunchBoxService.getById(id);
            if(existLunchBox == null) {
                return new ResponseEntity<LunchBox>(HttpStatus.NOT_FOUND);
            }  // 使用service的putById方法，並傳入前端body請求的id, "amount"(購買數量)
            lunchBoxService.putById(id, lunchBox.getAmount());
            return new ResponseEntity<LunchBox>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<LunchBox>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/lunch/delete/{id}")  // 處理delete方法的傳入請求
    public void delete(@PathVariable(value = "id")Long id) {
        lunchBoxService.deleteById(id);
    }
}
