package cn.com.megalith.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @Description: 配置授权认证服务类
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 13:41
 */
@Configuration
@EnableAuthorizationServer //授权认证中心
public class CustomAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 权限管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 数据源，保存token的时候需要
     */
    @Autowired
    private DataSource dataSource;
    /**
     * 设置保存token的方式，一共有五种，这里采用数据库的方式
     */
    @Autowired
    private TokenStore tokenStore;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 自定义登录或者鉴权失败时的返回信息
     */
    @Resource(name = "webResponseExceptionTranslator")
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Resource(name = "signUserDetailService")
    private UserDetailsService userDetailsService;


    /******************配置区域**********************/


    /**
     * 用来配置授权（authorizatio）以及令牌（token）的访问端点和令牌服务   核心配置  在启动时就会进行配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //开启密码授权类型
        endpoints.authenticationManager(authenticationManager);
        //配置token存储方式
        endpoints.tokenStore(tokenStore);
        //自定义登录或者鉴权失败时的返回信息
        endpoints.exceptionTranslator(webResponseExceptionTranslator);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /**
         * 配置oauth2服务跨域
         */
        CorsConfigurationSource source = new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedHeader("*");
                corsConfiguration.addAllowedOrigin(request.getHeader(HttpHeaders.ORIGIN));
                corsConfiguration.addAllowedMethod("*");
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setMaxAge(3600L);
                return corsConfiguration;
            }
        };

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients()
                .addTokenEndpointAuthenticationFilter(new CorsFilter(source));
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，  数据库在进行client_id 与 client_secret验证时   会使用这个service进行验证
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
}
