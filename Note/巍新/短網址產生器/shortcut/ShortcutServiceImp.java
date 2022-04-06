package com.example.shortcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortcutServiceImp implements ShortcutService{

    @Autowired
    ShortcutRepository shortcutRepository;

    char[] chars = new char[36];

    public ShortcutServiceImp() {
        for(int i = 0; i < 26; i++) {
            this.chars[i] = (char)(65 + i);
        }
        for (int i = 26; i < 36; i++) {
            this.chars[i] = (char)(22 + i);
        }
    }

    // 傳入長，產生短，並回傳
    @Override
    public String createUrl(String longUrl) {
        String url = "";
        int random;
        for (int i = 0; i < 8; i++) {
            random = (int)(Math.random()*36);
            url = url + chars[random];
        }
        shortcutRepository.save(new Shortcut(longUrl, url));
        return url;
    }

    // 傳入短，回傳長
    @Override
    public String link(String shortUrl) {
        return shortcutRepository.getByShortUrl(shortUrl).getLongUrl();
    }
}
