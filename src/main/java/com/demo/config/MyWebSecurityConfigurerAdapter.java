package com.demo.config;

import com.demo.exception.MyAccessDeniedHandler;
import com.demo.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class MyWebSecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().
                //未登录时的地址
                loginPage("/showLogin")
                //处理登录请求的url
                .loginProcessingUrl("/login")
                //.successForwardUrl("/showMain")
                //认证成功后跳转的地址
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("/showMain");
                    }
                })
                //.failureForwardUrl("/showFail")
                //登录失败后的处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("/showFail");
                    }
                })
                //客户端的用户名参数名称
                .usernameParameter("myusername")
                //客户端的密码参数名称
                .passwordParameter("mypassword")
                ;

        http.authorizeRequests()
                //.antMatchers("/showLogin","/showFail").permitAll()
                .antMatchers("/showLogin","/showFail").access("permitAll")
                //对于静态和动态请求需要分开
                //.antMatchers("/js/**").permitAll()
                .antMatchers("/abc").denyAll()
                .antMatchers("/jczl").hasAnyAuthority("demo:update")
                //.antMatchers("/jczl").hasAnyRole("ADMIN")
                .antMatchers("/admin").access("@myServiceImpl.hasPermission(request,authentication)")
                .antMatchers("/ip").hasIpAddress("192.168.7.86")
                .antMatchers("/images/**").permitAll()
                .regexMatchers("/js/.*").permitAll()
                .antMatchers("/demo").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                //.accessDeniedHandler(accessDeniedHandler);
                //只适用于非前端框架，适用于同步请求的方式
                //如果是异步请求需要使用上一种方式。
                .accessDeniedPage("/showAccessDenied");

        http.rememberMe()
            .userDetailsService(userDetailsService)
            .tokenRepository(persistentTokenRepository)
            .tokenValiditySeconds(10*2);

        http.csrf().disable();
    }
}
