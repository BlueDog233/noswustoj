package cn.bluedog233.noswustoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.bluedog233.noswustoj.mapper")
@SpringBootApplication
public class NoswustojApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoswustojApplication.class, args);
    }

}
