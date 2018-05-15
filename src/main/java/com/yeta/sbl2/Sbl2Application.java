package com.yeta.sbl2;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
@Configuration
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

	/**
	 * 设置跨域
	 * @return
	 */
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");	// 1允许任何域名使用
		corsConfiguration.addAllowedHeader("*");	// 2允许任何头
		corsConfiguration.addAllowedMethod("*");	// 3允许任何方法（post、get等）
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}
}
