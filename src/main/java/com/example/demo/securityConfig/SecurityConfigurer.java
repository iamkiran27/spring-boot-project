package com.example.demo.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String role = "ADMIN";
http.authorizeRequests()
        .antMatchers("/signup").permitAll()
        .antMatchers("/admin/**").hasRole("admin")
        .antMatchers("/admin").hasRole("admin")
        .antMatchers("/cart", "/cart/**").hasRole("user")
        .antMatchers("/").permitAll()
        .and().formLogin()
        .loginPage("/signin")
        .defaultSuccessUrl("/",true)
        .loginProcessingUrl("/processLogin")
        .permitAll()
        .and().logout().permitAll();
    }
}
