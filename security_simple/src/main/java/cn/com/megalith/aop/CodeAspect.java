package cn.com.megalith.aop;

import cn.com.megalith.vo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: zhoum
 * @Date: 2019-10-24
 * @Time: 17:11
 */
//@Aspect
//@Component
public class CodeAspect {

    @Pointcut("execution(public * org.springframework.security.oauth2.provider..*.*TokenEndpoint.postAccessToken(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        Object result = pjp.proceed();
        if ( result instanceof ResponseEntity ){
            ResponseEntity res = (ResponseEntity)result;
            if ( res.getStatusCode() .equals(HttpStatus.OK) ){
                Object body = res.getBody();
                if ( body instanceof DefaultOAuth2AccessToken ){
                    DefaultOAuth2AccessToken data = (DefaultOAuth2AccessToken)body;
                    ResponseEntity codeResult = new ResponseEntity(Result.ok(data.getValue()),HttpStatus.OK);
                    return codeResult;
                }
            }

        }
        return result;
    }
}
