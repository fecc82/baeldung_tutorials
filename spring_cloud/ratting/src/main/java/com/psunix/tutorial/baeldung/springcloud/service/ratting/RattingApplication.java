package com.psunix.tutorial.baeldung.springcloud.service.ratting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
public class RattingApplication {

	@Value("${server.port}")
	private int port;

	@Value("${book.ratting: 5}")
	private int bookRatting;

	public static void main(String[] args) {
		SpringApplication.run(RattingApplication.class, args);
	}

	@GetMapping("/")
	public String getBookRatting(){
		return "Here is the book ratting from port " + port + " : " + bookRatting + " start";
	}
}
