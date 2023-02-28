package com.http;

/**
 * @author cls1277
 * @date 2023-02-26 18:37
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaTest {
//    public static String URL_PATH = "http://cbnxzs.com/api?id=4";
    public static String URL_PATH = "http://cbnxzs.com/api";

    public static InputStream getInputStream() {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(URL_PATH);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static void saveImageToDisk() {
        InputStream inputStream = getInputStream();
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("E:\\cls1277\\Courses\\img.jpg");
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        saveImageToDisk();
        System.out.println("done");
    }
}