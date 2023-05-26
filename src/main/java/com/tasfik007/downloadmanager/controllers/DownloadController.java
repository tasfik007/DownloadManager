package com.tasfik007.downloadmanager.controllers;

import com.tasfik007.downloadmanager.services.MultiThreadedDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/download")
public class DownloadController {
    private final MultiThreadedDownloadService downloadService;

    @GetMapping("/")
    public ResponseEntity<String> download() {
        downloadService.download();
        return ResponseEntity.ok().body("Download Request Initiated");
    }

    @GetMapping("/ping")
    public String pingDownloadService() {
        return downloadService.ping();
    }

}
