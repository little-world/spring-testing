package com.littleworld.todo.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Configuration
public class WebDriverConfiguration {

	@Bean
	public WebDriver webDriver() {
	    return new ChromeDriver();
	}
}
