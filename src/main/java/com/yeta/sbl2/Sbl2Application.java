package com.yeta.sbl2;

import com.yeta.sbl2.filter.MyFilter;
import com.yeta.sbl2.interceptor.MyInterceptor;
import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

//标识程序启动类
@SpringBootApplication

//扫描Mybatis mapper包路径
@MapperScan(basePackages = "com.yeta.sbl2.mapper")

//开启定时任务
@EnableScheduling

//开启异步任务
@EnableAsync

//导入dwr的配置文件
@ImportResource(locations = "classpath:config/dwrConfig.xml")

//配置bean时需要加入这个注解
@Configuration
public class Sbl2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbl2Application.class, args);
	}

	/**
	 * 配置dwr Servlet
	 * 重点注意取名
	 * @return
	 */
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
	 * 配置跨域过滤器
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/*", buildConfig());
		return new CorsFilter(source);
	}

	public CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");	// 1允许任何域名使用
		corsConfiguration.addAllowedHeader("*");	// 2允许任何头
		corsConfiguration.addAllowedMethod("*");	// 3允许任何方法（post、get等）
		return corsConfiguration;
	}

	/**
	 * 这种方式用于配置第三方过滤器
	 * 配置自定义过滤器只需加上@Component和@WebFilter注解
	 * @return
	 */
	/*@Bean
	public MyFilter myFilter() {
		return new MyFilter();
	}

	public FilterRegistrationBean filterRegistrationBean1() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(myFilter());
		bean.setName("MyFilter");
		bean.addUrlPatterns("/*");
		//bean.addInitParameter("", "");
		bean.setOrder(Integer.MAX_VALUE);		//执行顺序，从小到大
		return bean;
	}*/

	/**
	 * 配置拦截器
	 * 由于添加拦截器需要继承WebMvcConfigurerAdapter类
	 * 需要用@Bean将MyInterceptor注入容器，才能在拦截器中注入，否为注入为空
	 * 或者在自定义拦截器类上加上@Component注解，通过注入方式配置
	 */
	@Configuration
	class WebMvcConfigurer extends WebMvcConfigurerAdapter {
		/*@Bean
		public MyInterceptor myInterceptor() {
			return new MyInterceptor();
		}*/

		@Autowired
		private MyInterceptor myInterceptor;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(myInterceptor).addPathPatterns("/*");
			super.addInterceptors(registry);
		}
	}
}
