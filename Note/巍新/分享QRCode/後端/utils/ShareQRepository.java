package com.example.shareq.utils;

import com.example.shareq.models.ShareQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareQRepository extends JpaRepository<ShareQ, Long> {

    public ShareQ getByShortUrl(String shortUrl);
    public ShareQ getByIdno(String idno);
}
