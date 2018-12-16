package org.emartos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//Indicates that this class can define other beans
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    //Define type of authentication: jdbc auth
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                //?->placeholder for the email submiited in the log in form
                //spring sec. requires the true variable for the user to be auth.
                .usersByUsernameQuery("select email as principal, password as credentials, true from user where " +
                        "email=?").
                authoritiesByUsernameQuery("select email as principal, role as role from user " +
                        "where email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Authorize the Request that go to pages (register, index, about)
        // and resources (css & webjars) (non authentication needed) (get requests)
        //After the user is correctly logged in, he will se the profile page (post request of the log in form)
        //After the user is correctly logged out, he will se the log in page (get request)
        http.authorizeRequests().antMatchers("/signup", "/", "/about", "/login",
                "/css/**", "/webjars/**").permitAll()
                //.anyRequest().authenticated()
                .antMatchers("/profile").hasAnyRole("USER,ADMIN")
                .antMatchers("/user","/task/**", "/project/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/profile")
                .and().logout().logoutSuccessUrl("/login");
    }


}
