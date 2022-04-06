package com.example.shortcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortcutController {

    @Autowired
    private ShortcutService shortcutService;

    @PostMapping("/shortcut/add")
    public ResponseEntity<String> createUrl(@RequestBody String longUrl) {
        try {
            if (longUrl.length() <= 8) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>(shortcutService.createUrl(longUrl), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shortcut/link")
    public ResponseEntity<String> link(@RequestBody String shortUrl) {
        try {
            if (shortUrl.length() != 8) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>(shortcutService.link(shortUrl), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
