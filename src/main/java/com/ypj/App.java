package com.ypj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
// 扫描组件和mapper映射文件
@SpringBootApplication(scanBasePackages = "com.ypj")
@MapperScan("com.ypj.dao")
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // 启动SpringBoot
        SpringApplication.run(App.class, args);
    }
}
