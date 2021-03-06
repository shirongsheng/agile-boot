package com.shirs.agileboot.config;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.shirs.agileboot.jwt.JwtAccessDeniedHandler;
import com.shirs.agileboot.jwt.JwtAuthenticationEntryPoint;
import com.shirs.agileboot.jwt.JwtAuthenticationTokenFilter;
import com.shirs.agileboot.jwt.JwtTokenUtils;
import com.shirs.agileboot.modules.system.securityService.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * ???????????????
 * https://blog.csdn.net/XlxfyzsFdblj/article/details/82083443
 * https://blog.csdn.net/lizc_lizc/article/details/84059004
 * https://blog.csdn.net/XlxfyzsFdblj/article/details/82084183
 * https://blog.csdn.net/weixin_36451151/article/details/83868891
 * ???????????????????????????????????????????????????????????????????????????
 * Security??????????????????????????????????????????
 *
 * @author ????????????
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private UserDetailsService myCustomUserService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    private JwtAccessDeniedHandler jwtAccessDeniedHandler = null;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = null;
    private JwtTokenUtils jwtTokenUtils = null;

    public SecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtTokenUtils jwtTokenUtils) {

        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtTokenUtils = jwtTokenUtils;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider())
                .httpBasic()
                //?????????????????????json??????????????????????????????????????????????????????????????????????????????
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 403);
                    map.put("message", "?????????");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })

                .and()
                .authorizeRequests()
                //????????????
                .antMatchers("/websocket/*").permitAll()
                .anyRequest().authenticated() //????????????????????????

                .and()
                .formLogin() //?????????????????????
                //?????????????????????????????????????????????????????????
                .loginPage("http://localhost:8081")
                //??????????????????????????????????????????????????????
                .usernameParameter("username")
                .passwordParameter("password")
                //??????????????????,????????????????????????spring security?????????????????????200?????????302
                .loginProcessingUrl("/login")
                .permitAll()
                //?????????????????????json
                .failureHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 401);
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        map.put("message", "????????????????????????");
                    } else if (ex instanceof DisabledException) {
                        map.put("message", "???????????????");
                    } else {
                        map.put("message", "????????????!");
                    }
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //?????????????????????json
                .successHandler((request, response, authentication) -> {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String token = jwtTokenUtil.createToken(new HashMap<>());
                    map.put("code", 200);
                    map.put("message", "????????????");
                    map.put("data", authentication);
                    map.put("token","Bearer "+token);
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .and()
                .exceptionHandling()
                //?????????????????????json
                .accessDeniedHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 403);
                    map.put("message", "????????????");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                //?????????????????????json
                .logoutSuccessHandler((request, response, authentication) -> {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 200);
                    map.put("message", "????????????");
                    map.put("data", authentication);
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .permitAll();
        //??????????????????,?????????http.cors().disable();?????????????????????????????????????????????.loginProcessingUrl("login");?????????
        //?????????????????????????????????
        http.cors();

        //???????????????????????????API POST???????????????????????????????????????API POST??????403??????
        http.csrf().disable()
                // ????????????
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

        // ????????????
        http.headers().cacheControl();

        // ??????JWT filter
        http.apply(new TokenConfigurer(jwtTokenUtils));
    }

    @Override
    public void configure(WebSecurity web) {
        //?????????header????????????token??????????????????????????????OPTIONS?????????
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/*");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //????????????UserDetailsService????????????
        authenticationProvider.setUserDetailsService(myCustomUserService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

    public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private final JwtTokenUtils jwtTokenUtils;

        public TokenConfigurer(JwtTokenUtils jwtTokenUtils) {

            this.jwtTokenUtils = jwtTokenUtils;
        }

        /*@Override
        public void configure(HttpSecurity http) {
            JwtAuthenticationTokenFilter customFilter = new JwtAuthenticationTokenFilter(jwtTokenUtils);
            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        }*/
    }
}

