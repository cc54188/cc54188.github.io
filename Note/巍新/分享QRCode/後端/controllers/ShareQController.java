package com.example.shareq.controllers;

import com.example.shareq.models.ShareQ;
import com.example.shareq.models.User;
import com.example.shareq.utils.ShareQService;
import com.example.shareq.utils.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShareQController {

    @Autowired
    private ShareQService shareQService;

    @Autowired
    private UserService userService;

    @PostMapping("/shareq/create")  // 建立並回傳短網址，讓前端用短網址製造Qrcode
    public ResponseEntity<ShareQ> create(@RequestBody ShareQ shareQ) {
        try {
            ShareQ resShareQ = shareQService.create(shareQ);
            if(shareQ == null) {
                return new ResponseEntity<ShareQ>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ShareQ>(resShareQ, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();  // 印出錯誤
            return new ResponseEntity<ShareQ>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/shareq/add")  // 前端連結後回傳加入使用者陣列，並幫分享者集一點
    public ResponseEntity<ShareQ> add(@RequestBody ShareQ shareQ) {  // 這邊傳入的其實是userIdno，而非sharerIdno
        try {
            ShareQ resShareQ = shareQService.add(shareQ);  // 回傳的resShareQ的idno才是sharer的idno
            if(shareQ == null || resShareQ == null) {  // res有可能回傳null
                return new ResponseEntity<ShareQ>(HttpStatus.NOT_FOUND);
            }
            User user = new User();
            user.setSharerIdno(resShareQ.getIdno());
            user.setUserIdno(shareQ.getIdno());
            userService.add(user);
            return new ResponseEntity<ShareQ>(resShareQ, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ShareQ>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/shareq/getCount")
    public ResponseEntity<ShareQ> getCount(@RequestBody ShareQ shareQ) {
        try {
            ShareQ resShareQ = shareQService.getByIdno(shareQ.getIdno());
            if(resShareQ == null) {
                return new ResponseEntity<ShareQ>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ShareQ>(resShareQ, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ShareQ>(HttpStatus.NOT_FOUND);
        }
    }
}
