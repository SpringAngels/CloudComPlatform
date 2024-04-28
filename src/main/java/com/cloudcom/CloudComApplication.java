package com.cloudcom;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CloudComApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CloudComApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}
}