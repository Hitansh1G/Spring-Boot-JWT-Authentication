package security.example.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security.example.security.model.Role;
import security.example.security.model.User;
import security.example.security.service.UserService;

import java.util.HashSet;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER ", "THIS IS THE USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN ", "THIS IS THE USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER ", "THIS IS THE USER"));

			userService.saveUser(new User("89980908", "omkar" , "omprakash@gmail.com", "pass",new HashSet<>()));
			userService.saveUser(new User("89980908", "omkar1" , "omprakash@gmail.com", "pass",new HashSet<>()));
			userService.saveUser(new User("89980908", "omkar2" , "omprakash@gmail.com", "pass",new HashSet<>()));

		};
	}

}
