package com.psunix.tutorial.baeldung.springcloud.service.books;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
@EnableCircuitBreaker
public class BooksApplication {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${server.port}")
	private int port;

	@Value("${book.name: Harry Potter}")
	private String bookName;

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@HystrixCommand(fallbackMethod = "defaultBookName")
	@GetMapping("/")
	public String getBookName(@RequestHeader("x-location") String location){
		final String bookRatting = restTemplate.getForEntity("http://ratting-service", String.class).getBody();
		return "Here is the book from " + port + " with name " + bookName + ". Its ratting is "+ bookRatting +" You can by " +
				"it" +
				" in " +
				"" +
				location;
	}

	public String defaultBookName(@RequestHeader("x-location") String location){
		return "Magic Of Thinking big";
	}
}
