package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.user.UserServiceDetailsImpl;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceDetailsImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomSecurityConfig(UserServiceDetailsImpl userService,
        BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().permitAll().loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .rememberMe().key("remember-me").and()
            .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
            .and()
            .authorizeRequests().antMatchers("/user/**").hasRole("USER")
            .antMatchers("/static/**", "/resources/**", "/", "/404").permitAll()
            .anyRequest().permitAll();


        http.exceptionHandling().accessDeniedPage("/403");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }


}
