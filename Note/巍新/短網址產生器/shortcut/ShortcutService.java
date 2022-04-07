package com.example.shortcut;

public interface ShortcutService {

    public Shortcut createUrl(String longUrl);

    public String link(String shortUrl);
}
