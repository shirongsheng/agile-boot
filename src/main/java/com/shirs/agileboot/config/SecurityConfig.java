package com.shirs.agileboot.config;

import com.shirs.agileboot.common.filter.JwtAuthenticationTokenFilter;
import com.shirs.agileboot.config.handler.PathAccessDecisionManager;
import com.shirs.agileboot.modules.system.securityService.CustomSecurityMetadataSource;
import com.shirs.agileboot.modules.system.securityService.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;

    @Autowired
    private PathAccessDecisionManager pathAccessDecisionManager;

    /*@Autowired
    @Qualifier("customSecurityMetadataSource")
    private AccessDecisionManager accessDecisionManager;*/

    /**
     * 注入到容器，进行用户认证
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider())
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //不通过session获取SecurityContext，前后端分离禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //放行接口
                //对于登录接口，允许匿名访问
                .antMatchers("/auth/login").anonymous()
                //.antMatchers("/auth/logout").anonymous()
                .antMatchers("/websocket/*").permitAll()
                //除上面外的所有请求全部都需要鉴权认证
                .anyRequest().authenticated().withObjectPostProcessor(filterSecurityInterceptorObjectPostProcessor()) //必须授权才能范围
                .and()
                .formLogin() //使用自带的登录
                //指定登录地址，此处定义为前端的登录地址
                .loginPage("http://localhost:8081")
                //指定自定义登录页面的用户名、密码参数
                .usernameParameter("username")
                .passwordParameter("password")
                //登录处理方法,如不指定，则使用spring security自带的登录页，200会转为302
                .loginProcessingUrl("/login")
                .permitAll()
                //登录失败处理器
                .failureHandler(failureHandler)
                //登录成功处理器
                .successHandler(successHandler)
                .and()
                .exceptionHandling()
                //没有权限处理器
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout()
                //退出登录成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        //开启跨域访问,如果时http.cors().disable();则会出现跨域问题，应该是配置了.loginProcessingUrl("login");在前端
        //登录时会出现跨域问题。
        http.cors();/*
        ApplicationContext applicationContext =
                http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(applicationContext))
                .withObjectPostProcessor(new
                                                 ObjectPostProcessor<FilterSecurityInterceptor>() {
                                                     @Override
                                                     public <O extends FilterSecurityInterceptor> O
                                                     postProcess(O object) {
                                                         object.setSecurityMetadataSource(customSecurityMetadataSource);
                                                         return object;
                                                     }
                                                 });*/

        http.csrf().disable();

        // 禁用缓存
        http.headers().cacheControl();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/*");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

    private ObjectPostProcessor<FilterSecurityInterceptor> filterSecurityInterceptorObjectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(pathAccessDecisionManager);
                object.setSecurityMetadataSource(customSecurityMetadataSource);
                return object;
            }
        };
    }
}

