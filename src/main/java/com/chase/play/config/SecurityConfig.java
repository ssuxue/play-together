package com.chase.play.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/9/27 21:29
 */
@Configuration  // 声明为配置类
@EnableWebSecurity  // 启用 Spring Security web 安全的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //密码编码器
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/admin/login", "/admin/registry").permitAll() // 对登录注册要允许匿名访问
                .antMatchers("/**").permitAll()//测试时全部运行访问
                .anyRequest().authenticated()// 除上面外的所有请求全部需要鉴权认证
                .and()
                .csrf() // 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 禁用缓存
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }
}
