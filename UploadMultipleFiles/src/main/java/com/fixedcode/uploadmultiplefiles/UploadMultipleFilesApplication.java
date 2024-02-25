package com.fixedcode.uploadmultiplefiles;

import com.fixedcode.uploadmultiplefiles.Upload.IFileUploadService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UploadMultipleFilesApplication implements CommandLineRunner {

    @Resource
    private IFileUploadService fileUploadService;
    public static void main(String[] args) {
        SpringApplication.run(UploadMultipleFilesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        fileUploadService.init();//new directory created with name "uploads"
    }
}
