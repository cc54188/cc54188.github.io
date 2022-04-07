package com.example.shortcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> link(@RequestBody String shortUrl) {
        System.out.println("con收到");
        try {
            if (shortUrl.length() <= 8) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>(shortcutService.link(shortUrl), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
