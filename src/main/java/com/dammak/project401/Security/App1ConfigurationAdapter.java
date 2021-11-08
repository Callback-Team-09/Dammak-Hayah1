//package com.dammak.project401.Security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@Order(1)
//public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
//    public App1ConfigurationAdapter() {
//        super();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/admin*")
//                .authorizeRequests()
//                .anyRequest()
//                .hasRole("ADMIN")
//
//                .and()
//                .formLogin()
//                .loginPage("/loginAdmin")
//                .loginProcessingUrl("/admin_login")
//                .failureUrl("/loginAdmin?error=loginError")
//                .defaultSuccessUrl("/adminPage")
//
//                .and()
//                .logout()
//                .logoutUrl("/admin_logout")
//                .logoutSuccessUrl("/protectedLinks")
//                .deleteCookies("JSESSIONID")
//
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403")
//
//                .and()
//                .csrf().disable();
//    }
//}