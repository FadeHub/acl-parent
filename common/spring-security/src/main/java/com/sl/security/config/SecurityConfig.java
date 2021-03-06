package com.sl.security.config;

import com.sl.security.filter.TokenAuthFilter;
import com.sl.security.filter.TokenLoginFilter;
import com.sl.security.security.DefaultPasswordEncoder;
import com.sl.security.security.TokenLogoutHandler;
import com.sl.security.security.TokenManager;
import com.sl.security.security.UnAuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author shuliangzhao
 * @Title: SecurityConfig
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 21:54
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    private DefaultPasswordEncoder defaultPasswordEncoder;

    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(TokenManager tokenManager, RedisTemplate redisTemplate, DefaultPasswordEncoder defaultPasswordEncoder, UserDetailsService userDetailsService) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnAuthEntryPoint()) //?????????????????????
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .logout().logoutUrl("/admin/acl/index/logout")//????????????
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate))
                .and()
                .addFilter(new TokenAuthFilter(authenticationManager(),tokenManager,redisTemplate))
                .addFilter(new TokenLoginFilter(tokenManager,redisTemplate,authenticationManager()))
                .httpBasic();
    }

    //??????userDetailsService???????????????
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
    //?????????????????????????????????????????????
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }
}
