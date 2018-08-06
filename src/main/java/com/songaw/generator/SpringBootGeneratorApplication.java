package com.songaw.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 该注解指定项目为springboot，由此类当作程序入口 自动装配 web 依赖的环境
 * 
 */
@EnableCaching
@SpringBootApplication
@EnableScheduling
@ServletComponentScan(basePackages = {"com.songaw.generator.common.servlet","com.songaw.generator.common.filter","com.songaw.generator.common.listener"})
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.songaw.generator.modules.*.mapper")
public class SpringBootGeneratorApplication /*extends SpringBootServletInitializer*/ {

	/** tomcat里面运行 **/
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootGeneratorApplication.class);
	}*/

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(SpringBootGeneratorApplication.class);

		 app.run(args);



	}

}
