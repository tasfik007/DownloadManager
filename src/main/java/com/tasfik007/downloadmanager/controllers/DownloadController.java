package com.tasfik007.downloadmanager.controllers;

import com.tasfik007.downloadmanager.services.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/download")
public class DownloadController {
    private final DownloadService downloadService;

    @GetMapping("/ping")
    public String pingDownloadService() {
        return downloadService.ping();
    }

}
