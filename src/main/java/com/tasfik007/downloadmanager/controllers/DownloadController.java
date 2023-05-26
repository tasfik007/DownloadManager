package com.tasfik007.downloadmanager.controllers;

import com.tasfik007.downloadmanager.services.MultiThreadedDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Scanner;

import javax.sound.sampled.Line;

@RestController
@RequiredArgsConstructor
@RequestMapping("/download")
public class DownloadController {
    private final MultiThreadedDownloadService downloadService;

    @GetMapping("/")
    public ResponseEntity<String> download() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the URL that you would like to download: ");
        String FileUrl = keyboard.nextLine();
        keyboard.close();
        downloadService.download(FileUrl);
        return ResponseEntity.ok().body("Download Request Initiated");
    }

    @GetMapping("/ping")
    public String pingDownloadService() {
        return downloadService.ping();
    }

}
