package cn.com.megalith.auth.config;

import cn.com.megalith.common.ResponseData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @Description: web全局异常返回处理器
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 14:48
 */
@Configuration
public class WebResponseExceptionTranslateConfig {
    /**
     * 自定义登录或者鉴权失败时的返回信息
     */
    @Bean(name = "webResponseExceptionTranslator")
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity translate(Exception e) throws Exception {
                ResponseEntity responseEntity = super.translate(e);
                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                // do something with header or response
                if ( 400 == responseEntity.getStatusCode().value() ) {
                    System.out.println(body.getMessage());
                    if ( "Bad credentials".equals(body.getMessage()) ) {
                        return new ResponseEntity(new ResponseData("400" , "您输入的用户名或密码错误" , null) , headers , HttpStatus.OK);
                    }
                }
                return new ResponseEntity(body , headers , responseEntity.getStatusCode());

            }
        };
    }
}
