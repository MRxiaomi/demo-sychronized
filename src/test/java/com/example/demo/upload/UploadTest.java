package com.example.demo.upload;

import com.google.common.base.Charsets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by liuyumeng on 2018/12/13.
 */
public class UploadTest {
    public static void main(String[] args) {
        try {
            File file = new File(UploadTest.class.getResource("/tmp.csv").toURI());
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int size;
            while ((size = fileInputStream.read(buffer)) != -1) {//read from the file on server
                System.out.printf(new String(buffer, Charsets.ISO_8859_1));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
