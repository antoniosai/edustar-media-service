package com.edustar.media;

import com.edustar.media.domains.media.MediaService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(value = {
	"com.edustar.core",
	"com.edustar.media"
})
@EnableDiscoveryClient
public class MediaServiceApplication implements CommandLineRunner {

	@Resource
	MediaService mediaService;

	public static void main(String[] args) {
		SpringApplication.run(MediaServiceApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
//    mediaService.deleteAll();
		mediaService.init();
	}
}

