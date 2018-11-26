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
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }


}
