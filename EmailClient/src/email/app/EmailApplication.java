package email.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("email.entity")
@EnableJpaRepositories("email.repository")
@ComponentScan(basePackages = {"email.controller", "email.service", "email.repository"})
@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class EmailApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmailApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EmailApplication.class, args);
	}  
}
