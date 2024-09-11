package org.denis.coinkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EntityScan("org.denis.coinkeeper.api.entities")
public class CoinKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinKeeperApplication.class, args);
	}

}
