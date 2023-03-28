package ru.dinerik.Chat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}password")
                .roles("GUEST")
                .and()
                .withUser("user2")
                .password("{noop}password")
                .roles("GUEST")
                .and()
                .withUser("user3")
                .password("{noop}password")
                .roles("GUEST");
    }
}