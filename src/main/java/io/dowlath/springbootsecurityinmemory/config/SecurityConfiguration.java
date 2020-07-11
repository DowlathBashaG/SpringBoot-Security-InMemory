package io.dowlath.springbootsecurityinmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Dowlath
 * @create 7/12/2020 1:39 AM
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set your configuration on the auth object
        auth.inMemoryAuthentication()
            .withUser("Dowlath")
            .password("dowlath")
            .roles("USER")
            .and()
            .withUser("Basha")
            .password("basha")
            .roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
          .antMatchers("/","static/css","static/js").permitAll()
          .antMatchers("/**").hasRole("ADMIN")
          .antMatchers("/user").hasAnyRole("USER","ADMIN")
          .and().formLogin();

    }

    // always deal with hashed passwords !!!
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
