package com.example.shortcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class ShortcutController {

    @Autowired
    private ShortcutService shortcutService;
//    @RequestParam
    @PostMapping("/shortcut/add")
    public ResponseEntity<Shortcut> createUrl(@RequestBody String longUrl) {
        System.out.println("長網址長度:" + longUrl.length());
        try {
            if (longUrl.length() <= 8) {
                return new ResponseEntity<Shortcut>(HttpStatus.NOT_FOUND);
            }
            Shortcut shortcut = shortcutService.createUrl(longUrl);
            System.out.println("回傳: " + shortcut);
            return new ResponseEntity<Shortcut>(shortcut, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Shortcut>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/shortcut/link")
    public ResponseEntity<Shortcut> link(@RequestBody String shortUrl) {
        System.out.println("con收到");
        try {
            if (shortUrl.length() > 8) {
                return new ResponseEntity<Shortcut>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Shortcut>(shortcutService.link(shortUrl), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Shortcut>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/shortcut/getAll")
    public List<Shortcut> getAll() {
        System.out.println("取得全部");
        return shortcutService.getAll();
    }

    @GetMapping("/link/{shortUrl}")  // 執行後，直接在網址打"localhost:8080/link/短網址"，即可連到網頁
    public RedirectView newLink(@PathVariable(value = "shortUrl") String shortUrl) {
        System.out.println("後端: " + shortUrl);
        String longUrl = shortcutService.getByShortUrl(shortUrl).getLongUrl();
        return new RedirectView(longUrl);
    }
}
