package com.local.security.common;

import com.local.security.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private  MyAuthenticationSuccessHandler successHandler;

    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        http
            .csrf().disable()//关闭csrf验证
            .authorizeRequests()//需要认证的请求
            .anyRequest().authenticated() //所有的请求都需要认证
            // 允许放行的
            //.antMatchers("/login").permitAll()
            .and()
                .formLogin().successHandler(successHandler).failureHandler(failureHandler).permitAll()
                //.loginPage("/html/login.html").permitAll()//所有用户可以访问/login路径
             .and()
             .logout().permitAll();
    }

    /**
     * 自定义UserDetailsService实现类
     * @return
     */
    @Bean
    UserDetailsService userService(){
        return new LoginServiceImpl();
    }

    /**
     * 自定义密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 使用数据库中的用户名与密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService()).passwordEncoder(passwordEncoder());
    }
}
