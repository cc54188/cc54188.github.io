package com.example.shareq.services;

import com.example.shareq.models.ShareQ;
import com.example.shareq.models.User;
import com.example.shareq.utils.ShareQRepository;
import com.example.shareq.utils.ShareQService;
import com.example.shareq.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShareQServiceImp implements ShareQService {

    @Autowired
    private ShareQRepository shareQRepository;

    @Autowired
    private UserRepository userRepository;

    char[] chars = new char[36];

    public ShareQServiceImp() {
        for (int i = 0; i < 26; i++) {
            this.chars[i] = (char)(65 + i);
        }
        for (int i = 26; i < 36; i++) {
            this.chars[i] = (char)(22 + i);
        }
    }

    @Override  // 建立並回傳
    public ShareQ create(ShareQ shareQ) {
        List<ShareQ> shareQList = shareQRepository.findAll();
        for (int i = 0; i < shareQList.size(); i++) {
            if (shareQList.get(i).getIdno().equals(shareQ.getIdno())) {
                return shareQList.get(i);
            }
        }
        String url = "";
        while (url == "") {
            int random;
            for (int i = 0; i < 8; i++) {
                random = (int)(Math.random()*36);
                url = url + chars[random];
            }
            for (int i = 0; i < shareQList.size(); i++) {
                if (shareQList.get(i).getShortUrl().equals(url)) {
                    url = "";
                    break;
                }
            }
        }
        ShareQ resShareQ = new ShareQ();
        resShareQ.setIdno(shareQ.getIdno());
        resShareQ.setLongUrl(shareQ.getLongUrl());
        resShareQ.setShortUrl(url);
        shareQRepository.save(resShareQ);
        return resShareQ;
    }

    @Override
    public ShareQ add(ShareQ shareQ) {
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {  // 若此idno已是銀行用戶
            if (userList.get(i).getUserIdno().equals(shareQ.getIdno())) {
                return null;                        // 就回傳null
            }
        }    // 若是新用戶，則用短網址取出此分享者
        ShareQ resShareQ = shareQRepository.getByShortUrl(shareQ.getShortUrl());
        resShareQ.setShareCount(resShareQ.getShareCount() + 1);  // 並增加1點
        shareQRepository.save(resShareQ);  // 存回去
        return resShareQ; // 回傳給controller
    }


}
