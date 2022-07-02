package cn.kj1001.khome.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * KJ1001
 *
 * @Description : run
 * @Author : AngeloDk
 * @Date : 2022/3/27 11:34
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.kj1001.khome.base.dao")
@ComponentScan(basePackages = "cn.kj1001.khome")
public class CommonApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}
}
