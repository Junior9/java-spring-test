package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootConfiguration {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootConfiguration.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return  applicationBuilder.sources(SpringBootConfiguration.class);}
	
}
