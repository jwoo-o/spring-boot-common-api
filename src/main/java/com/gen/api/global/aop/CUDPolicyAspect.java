package com.gen.api.global.aop;


import com.gen.api.global.config.JwtTokenConfig;
import com.gen.bluexray.server.logs.dto.AdminHistoryRequestDto;
import com.gen.bluexray.server.logs.service.LogsService;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-24
 * Time: 오후 12:25
 */
@Slf4j
@Component
@Aspect
public class CUDPolicyAspect {

    @Autowired
    private LogsService logsService;

    @Autowired
    private JwtTokenConfig jwtTokenConfig;


    @Pointcut("execution(* com.gen.bluexray.server.*.service.*.ins*(..)) || execution(* com.gen.bluexray.server.*.service.*.upd*(..)) || execution(* com.gen.bluexray.server.*.service.*.del*(..))")
    public void onLogging() {
    }


    @AfterReturning("com.gen.api.global.aop.CUDPolicyAspect.onLogging()") // 4
    public void afterDoAdminHistory(JoinPoint joinPoint) {
        String signatureString = joinPoint.getSignature().getName();
        if (!signatureString.contains("Login") && !signatureString.contains("History") && !signatureString.contains("Batch") && !signatureString.contains("License")) {

            HttpServletRequest request = // 5
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            if (!request.getMethod().equals("PATCH")) {
                try {
                String adminId = jwtTokenConfig.getAdminId(request);
                String message = "";
                String table = "";
                String data = "";
                if (signatureString.contains("ins")) {
                    message = "등록";
                    table = signatureString.replace("ins", "tb_");
                } else if (signatureString.contains("upd")) {
                    message = "수정";
                    table = signatureString.replace("upd", "tb_");
                } else {
                    message = "삭제";
                    table = signatureString.replace("del", "tb_");
                }
                AdminHistoryRequestDto dto = new AdminHistoryRequestDto(adminId, message, table.toLowerCase(), data);
                logsService.insAdminHistory(dto);

                } catch (Exception e) {

                    log.error(e.getMessage());
                }
            }
        }
    }
}
