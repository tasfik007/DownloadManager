package com.tasfik007.downloadmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadService {
    public String ping() {
        return "packets received successfully!";
    }
}
