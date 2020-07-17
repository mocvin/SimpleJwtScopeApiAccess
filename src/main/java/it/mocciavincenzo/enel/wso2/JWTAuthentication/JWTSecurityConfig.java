package it.mocciavincenzo.enel.wso2.JWTAuthentication;

import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.RestAuthenticationEntryPoint;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.filter.AuthenticationFilter;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.filter.TokenAuthenticationFilter;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.providers.JWTAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("it.mocciavincenzo.enel.wso2.JWTAuthentication")
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    //@Autowired
    //private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
                //.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Custom authentication provider - Order 1
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
