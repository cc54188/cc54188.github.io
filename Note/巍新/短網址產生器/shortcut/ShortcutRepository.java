package com.example.shortcut;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {

    public Shortcut getByShortUrl(String shortUrl);
}
