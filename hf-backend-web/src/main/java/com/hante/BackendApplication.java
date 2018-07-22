package com.hante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//开启JPA SQL增强
@EnableJpaAuditing
public class BackendApplication {
    public static void main(String[] args) {
        //注意在配置文件里面查看启动端口
        SpringApplication.run(BackendApplication.class, args);
    }
}
