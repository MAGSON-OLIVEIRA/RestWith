package br.com.restwith;

import br.com.restwith.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties({
		FileStorageConfig.class
})
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("123");
		System.out.print("123>>>>>>>>>>>>>>>>>>>>> "+result);
		
		
	}

}
