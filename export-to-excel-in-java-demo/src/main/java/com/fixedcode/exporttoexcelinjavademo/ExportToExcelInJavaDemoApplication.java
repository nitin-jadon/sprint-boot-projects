package com.fixedcode.exporttoexcelinjavademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExportToExcelInJavaDemoApplication {
//uncomment  PostConstruct annotation of Seeddata class to add some data into DB first time.
//added dependencies - lambok, spring web, spring data jpa, mysql driver
    public static void main(String[] args) {
        SpringApplication.run(ExportToExcelInJavaDemoApplication.class, args);
    }

}
