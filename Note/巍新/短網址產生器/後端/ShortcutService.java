package com.example.shortcut;

import java.util.List;

public interface ShortcutService {

    public Shortcut createUrl(String longUrl);

    public Shortcut link(String shortUrl);

    public List<Shortcut> getAll();

    public Shortcut getByShortUrl(String shortUrl);
}
