package com.learning.kata_pp_312.configs;

import com.learning.kata_pp_312.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity //Включаем безопасность
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserServiceImpl userService;
    private final SuccessUserHandler successUserHandler;
    @Autowired
    public SecurityConfig(UserServiceImpl userService, SuccessUserHandler successUserHandler) {
        this.userService = userService;
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/home").permitAll()
                .and()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout().logoutSuccessUrl("/home");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        dap.setUserDetailsService(userService);
        return dap;
    }

    //Authentication via jdbc
//    @Bean
//    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$XyX4Uiczs7vtH9IDuH4uuuqKj7MaHPFHTnhEyuWzxM7uVEM2CCVRW")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$XyX4Uiczs7vtH9IDuH4uuuqKj7MaHPFHTnhEyuWzxM7uVEM2CCVRW")
//                .roles("ADMIN", "USER")
//                .build();
//        JdbcUserDetailsManager jd = new JdbcUserDetailsManager(dataSource);
//        if (jd.userExists(user.getUsername())) {
//            jd.deleteUser(user.getUsername());
//        }
//        if (jd.userExists(admin.getUsername())) {
//            jd.deleteUser(admin.getUsername());
//        }
//        jd.createUser(user);
//        jd.createUser(admin);
//        return jd;
//    }

    //Хранение пользователей в памяти InMemory
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$XyX4Uiczs7vtH9IDuH4uuuqKj7MaHPFHTnhEyuWzxM7uVEM2CCVRW")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$XyX4Uiczs7vtH9IDuH4uuuqKj7MaHPFHTnhEyuWzxM7uVEM2CCVRW")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}
