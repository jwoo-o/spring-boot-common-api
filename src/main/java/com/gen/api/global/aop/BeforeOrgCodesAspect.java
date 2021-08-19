package com.gen.api.global.aop;

import com.gen.api.global.common.dto.SearchRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-24
 * Time: 오후 1:38
 */
@Slf4j
@Component
@Aspect
public class BeforeOrgCodesAspect {



    @Pointcut("execution(* com.gen.api.server.*.service.*.*ByOrgCodes(..))")
    public void onRequest() {
    }

    @Around("com.gen.api.global.aop.BeforeOrgCodesAspect.onRequest()") // 4
    public Object doSearch(ProceedingJoinPoint pjp) throws Throwable {

        SearchRequestDto dto = (SearchRequestDto) pjp.getArgs()[0];

        return pjp.proceed(pjp.getArgs());
    }
}
