package stack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

/**
 * Created by Оля on 06.07.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/login")
                    .loginPage("/")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .rememberMe()
                        .tokenRepository(new InMemoryTokenRepositoryImpl())
                        .tokenValiditySeconds(2419200)
                        .key("stackKey")
                .and()
                    .httpBasic()
                        .realmName("Stack")
                .and()
                .authorizeRequests()
                    .antMatchers("/").authenticated()
                    .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}