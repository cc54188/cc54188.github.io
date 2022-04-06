package com.example.shortcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortcutServiceImp implements ShortcutService{

    @Autowired
    ShortcutRepository shortcutRepository;

    char[] chars = new char[36];  // 建一個空字元陣列備用

    public ShortcutServiceImp() {   // 利用ascii code 65~90 把A~Z存入陣列
        for(int i = 0; i < 26; i++) {
            this.chars[i] = (char)(65 + i);
        }
        for (int i = 26; i < 36; i++) {  // 再繼續存0~9(ascii 48~57)
            this.chars[i] = (char)(22 + i);
        }
    }

    // 傳入長，產生短，並回傳
    @Override
    public String createUrl(String longUrl) {
        List<Shortcut> shortcutList = shortcutRepository.findAll();
        for (int i = 0; i < shortcutList.size(); i++) {  // 已產生過短網址，就直接回傳
            if(shortcutList.get(i).getLongUrl().equals(longUrl)) {
                return "已有短網址: " + shortcutList.get(i).getShortUrl();
            }
        }
        String url = "";  // 預設短網址為空字串
        while (url == "") {
            int random;
            for (int i = 0; i < 8; i++) {    // 8個字元組成字串
                random = (int)(Math.random()*36);  // 0~35間隨機int
                url = url + chars[random];
            }
            for(int i = 0; i < shortcutList.size(); i++) {
                if(shortcutList.get(i).getShortUrl().equals(url)) {  // 找到相同短網址
                    System.out.println("相同:" + url);
                    url = "";       // 就清空
                    break;
                }
            }
        }
        shortcutRepository.save(new Shortcut(longUrl, url));
        return url;
    }

    // 傳入短，回傳長
    @Override
    public String link(String shortUrl) {
        System.out.println("ser收到");
        Shortcut shortcut = shortcutRepository.getByShortUrl(shortUrl);
        System.out.println();
        return shortcut.getLongUrl();

    }
}
