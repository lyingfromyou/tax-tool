package com.example.taxtool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//允许基于HttpServletRequest使用限制访问
                .antMatchers(HttpMethod.POST, "/file/**").permitAll()
                .antMatchers(
                        "/",
                        "/login",
                        "/*.ico",
                        "/static/**",
                        "/img/**",
                        "/css/**",
                        "/fileUploadAndDownloadPage",
                        "/file/**"
                ).permitAll()//不需要身份认证

                .anyRequest().authenticated()//其他路径必须验证身份
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/homePage")
                .defaultSuccessUrl("/homePage", true)
                .permitAll()
                .and()
                .csrf().disable()
                .logout().permitAll().deleteCookies("JSESSIONID");//退出删除cookie

        super.configure(http);
        http.authorizeRequests().antMatchers("*").permitAll();
//        http.authorizeRequests().anyRequest().permitAll().and()
//                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(ENCODER)
                .withUser("nieqin")
                .password(ENCODER.encode("liqian520"))
                .roles("ADMIN");
    }

}
