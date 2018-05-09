package com.yeta.sbl2;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描Mybatis mapper包路径
@MapperScan(basePackages = "com.yeta.sbl2.mapper")
//开启定时任务
@EnableScheduling
//开启异步任务
@EnableAsync
//
@ImportResource(locations = "classpath:config/dwrConfig.xml")
public class Sbl2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbl2Application.class, args);
	}

	//注册SpringDwrServlet到spring容器中
	//重点注意取名...
	@Bean
	public ServletRegistrationBean dwrServlet() {
		DwrSpringServlet servlet = new DwrSpringServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/dwr/*");
		registrationBean.addInitParameter("debug", "true");
		//使用服务器反转Ajax
		registrationBean.addInitParameter("activeReverseAjaxEnabled", "true");
		//能够从其他域请求true：开启； false：关闭
		registrationBean.addInitParameter("crossDomainSessionSecurity", "false");
		//允许远程调用JS
		registrationBean.addInitParameter("allowScriptTagRemoting", "true");
		return registrationBean;
	}
}
