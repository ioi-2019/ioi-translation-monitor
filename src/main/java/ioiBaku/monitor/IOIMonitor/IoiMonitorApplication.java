package ioiBaku.monitor.IOIMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@SpringBootApplication
public class IoiMonitorApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(IoiMonitorApplication.class, args);
		final String uri = "http://localhost:8080/importEx";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(uri, String.class);

	}

}
