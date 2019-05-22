package com.eureka.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;

import com.netflix.turbine.streaming.servlet.TurbineStreamServlet;


@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
public class EurekaTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaTurbineApplication.class, args);
	}

	@Bean
    public ServletRegistrationBean<TurbineStreamServlet> getServlet() {
        TurbineStreamServlet streamServlet = new TurbineStreamServlet();
        ServletRegistrationBean<TurbineStreamServlet> registrationBean = new ServletRegistrationBean<TurbineStreamServlet>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/turbine.stream");
        registrationBean.setName("TurbineStreamServlet");
        return registrationBean;
    }    
}
