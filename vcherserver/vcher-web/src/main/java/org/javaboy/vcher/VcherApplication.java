package org.javaboy.vcher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "org.javaboy.vcher.mapper")
@EnableScheduling
public class VcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(VcherApplication.class, args);
    }

}