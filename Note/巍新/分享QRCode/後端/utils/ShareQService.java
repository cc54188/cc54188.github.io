package com.example.shareq.utils;

import com.example.shareq.models.ShareQ;

public interface ShareQService {

    public ShareQ create(ShareQ shareQ);
    public ShareQ add(ShareQ shareQ);
    public ShareQ getByIdno(String idno);
}
