package cn.com.megalith.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Description: 资源提供端的配置  目前和授权认证端处于同一应用
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 16:58
 */
@Configuration
@EnableResourceServer //开启资源提供服务的配置  是默认情况下spring security oauth2的http配置   会被WebSecurityConfigurerAdapter的配置覆盖
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();

        http
                .authorizeRequests()//定义哪些url需要被保护  哪些不需要保护
                .antMatchers("/oauth/token", "oauth/check_token").permitAll()//定义这两个链接不需要登录可访问
                //.antMatchers("/**").permitAll() //定义所有的都不需要登录  目前是测试需要
                .anyRequest().authenticated() //其他的都需要登录
                .antMatchers("/sys/**").hasRole("admin")///sys/**下的请求 需要有admin的角色
                .and()
                .formLogin().loginPage("/login")//如果未登录则跳转登录的页面   这儿可以控制登录成功和登录失败跳转的页面
                .usernameParameter("username").passwordParameter("password").permitAll()//定义号码与密码的parameter
                .and()
                .csrf().disable();//防止跨站请求  spring security中默认开启
    }


}
