package com.tasfik007.downloadmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultiThreadedDownloadService {
    public String ping() {
        return "packets received successfully!";
    }
}
