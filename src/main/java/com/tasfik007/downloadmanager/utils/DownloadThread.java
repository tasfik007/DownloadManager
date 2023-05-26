package com.tasfik007.downloadmanager.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread extends Thread {
    private final String url;
    private final int startByte;
    private final int endByte;
    private final File tempFile;

    public DownloadThread(String url, int startByte, int endByte) {
        this.url = url;
        this.startByte = startByte;
        this.endByte = endByte;
        this.tempFile = new File("temp-" + startByte + "-" + endByte);
    }

    public File getTempFile() {
        return tempFile;
    }

    @Override
    public void run() {
        try {
            URL downloadUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            connection.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte);

            try (BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tempFile))) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
