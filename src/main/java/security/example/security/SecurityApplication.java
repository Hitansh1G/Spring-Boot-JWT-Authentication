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
			userService.saveRole(new Role(null,"ROLE_USER ", "this is the User"));
			System.out.println("1");
			userService.saveRole(new Role(null,"ROLE_ADMIN ", "this is the User"));
			System.out.println("12");
			userService.saveRole(new Role(null,"ROLE_MANAGER ", "this is the User"));
			System.out.println("123");

			userService.saveUser(new User("89980908", "omkar" , "omkark@gmail.com", "pass",new HashSet<>()));
			userService.saveUser(new User("89980908", "omkar1" , "omkark1@gmail.com", "pass",new HashSet<>()));
			userService.saveUser(new User("89980908", "omkar2" , "omkark2@gmail.com", "pass",new HashSet<>()));
			System.out.println("12");


			userService.addToUser("omkark@gmail.com", "ROLE_USER");
			System.out.println("1234");
			userService.addToUser("omkark1@gmail.com", "ROLE_ADMIN");
			userService.addToUser("omkark2@gmail.com", "ROLE_MANAGER");
			System.out.println("122");

		};
	}

}
