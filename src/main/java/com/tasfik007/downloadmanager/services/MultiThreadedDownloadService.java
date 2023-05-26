package com.tasfik007.downloadmanager.services;

import com.tasfik007.downloadmanager.utils.DownloadThread;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class MultiThreadedDownloadService {

    private static final String FILE_URL = "https://speed.hetzner.de/100MB.bin";
    private static final String OUTPUT_FILE = "/home/tasfik/Downloads/100MB.bin";
    private static final int NUM_THREADS = 4;

    public String ping() {
        return "packets received successfully!";
    }

    public void download() {
        try {
            URL url = new URL(FILE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int fileSize = connection.getContentLength();

            int chunkSize = fileSize / NUM_THREADS;
            DownloadThread[] threads = new DownloadThread[NUM_THREADS];

            for (int i = 0; i < NUM_THREADS; i++) {
                int startByte = i * chunkSize;
                int endByte = (i == NUM_THREADS - 1) ? fileSize - 1 : (i + 1) * chunkSize - 1;
                threads[i] = new DownloadThread(FILE_URL, startByte, endByte);
                threads[i].start();
            }

            for (DownloadThread thread : threads) {
                thread.join();
            }

            combineChunks(OUTPUT_FILE, threads);

            System.out.println("Download completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void combineChunks(String outputFile, DownloadThread[] threads) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            for (DownloadThread thread : threads) {
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(thread.getTempFile()))) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        }
    }

}
