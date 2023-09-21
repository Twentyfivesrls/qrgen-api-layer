package com.twentyfive.qrgenapilayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.twentyfive.authorizationcontroller", "com.twentyfive.qrgenapilayer"})
@SpringBootApplication
@EnableFeignClients
public class QrgenApiLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrgenApiLayerApplication.class, args);
    }

}
