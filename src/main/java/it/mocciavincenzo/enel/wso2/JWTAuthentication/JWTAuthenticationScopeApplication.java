package it.mocciavincenzo.enel.wso2.JWTAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class JWTAuthenticationScopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JWTAuthenticationScopeApplication.class, args);
	}

}
