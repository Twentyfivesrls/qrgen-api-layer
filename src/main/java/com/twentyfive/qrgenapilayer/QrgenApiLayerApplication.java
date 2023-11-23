package com.twentyfive.qrgenapilayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.twentyfive.authorizationflow", "com.twentyfive.qrgenapilayer"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class QrgenApiLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrgenApiLayerApplication.class, args);
    }

}
