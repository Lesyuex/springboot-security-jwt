package com.jobeth.config.security;

import com.jobeth.config.security.filter.PermissionMetadataSource;
import com.jobeth.config.security.handler.*;
import com.jobeth.config.security.config.JwtConfig;
import com.jobeth.config.security.filter.JwtTokenFilter;
import com.jobeth.config.security.manager.ResourceAccessDecisionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.jobeth.config.security.provider.UserAuthenticationProvider;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * description 描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 13:24 2020/4/10
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 自定义登录成功处理器
     */
    private final UserLoginSuccessHandler userLoginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    private final UserLoginFailHandler userLoginFailHandler;
    /**
     * 自定义注销成功处理器
     */
    private final UserLogoutSuccessHandler userLogoutSuccessHandler;
    /**
     * 自定义暂无权限处理器
     */
    private final UserAccessDeniedHandler userAccessDeniedHandler;
    /**
     * 自定义未登录的处理器
     */
    private final UserEntryPointHandler userEntryPointHandler;
    /**
     * 自定义登录逻辑验证器
     */
    private final UserAuthenticationProvider userAuthenticationProvider;

    private final PermissionMetadataSource permissionMetadataSource;
    private final ResourceAccessDecisionManager resourceAccessDecisionManager;


    public SecurityConfiguration(UserAuthenticationProvider userAuthenticationProvider, UserLoginSuccessHandler userLoginSuccessHandler, UserLoginFailHandler userLoginFailHandler, UserLogoutSuccessHandler userLogoutSuccessHandler, UserAccessDeniedHandler userAccessDeniedHandler, UserEntryPointHandler userEntryPointHandler, PermissionMetadataSource permissionMetadataSource, ResourceAccessDecisionManager resourceAccessDecisionManager) {
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.userLoginSuccessHandler = userLoginSuccessHandler;
        this.userLoginFailHandler = userLoginFailHandler;
        this.userLogoutSuccessHandler = userLogoutSuccessHandler;
        this.userAccessDeniedHandler = userAccessDeniedHandler;
        this.userEntryPointHandler = userEntryPointHandler;
        this.permissionMetadataSource = permissionMetadataSource;
        this.resourceAccessDecisionManager = resourceAccessDecisionManager;
    }

    /**
     * 加密方式
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置登录验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /**
     * 配置security的控制逻辑
     *
     * @param http 请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //设置不拦截的请求或资源(从配置文件中读取)
                .antMatchers(JwtConfig.antMatchers.split(",")).permitAll()
                //其他的需要登陆后才能访问
                .anyRequest().authenticated()
                //设置自定义鉴权
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(permissionMetadataSource);
                        o.setAccessDecisionManager(resourceAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                //配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(userEntryPointHandler)
                .and()
                //配置没有权限自定义处理类
                .exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                .and()
                //配置登录地址
                .formLogin()
                .loginProcessingUrl("/login/userLogin")
                //配置登录成功自定义处理类
                .successHandler(userLoginSuccessHandler)
                //配置登录失败自定义处理类
                .failureHandler(userLoginFailHandler)
                .and()
                //配置登出地址
                .logout()
                .logoutUrl("/login/userLogout")
                //配置用户登出自定义处理类
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                //允许跨域
                .cors()
                .and()
                // 取消跨站请求伪造防护
                .csrf().disable();
        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT过滤器
        http.addFilter(new JwtTokenFilter(authenticationManager()));
    }
}
