package br.com.fiap.TrabalhoCap8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer
public class TrabalhoCap8Application {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoCap8Application.class, args);
	}


}
