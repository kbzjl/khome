package cn.kj1001.khome.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/22 1:02
 */
@ComponentScan(basePackages = "cn.kj1001.khome")
@SpringBootApplication
@MapperScan(basePackages = "cn.kj1001.khome.base.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
 
