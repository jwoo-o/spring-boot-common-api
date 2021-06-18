package com.gen.api.global.interceptor;

import com.gen.api.global.contant.RedisContant;
import com.gen.api.global.util.CommonUtil;
import com.gen.api.global.util.JsonUtil;
import com.gen.api.global.domain.entity.License;
import com.gen.api.global.domain.entity.repositorys.LicenseRepository;
import com.gen.api.global.exception.NotFoundLicenseException;
import com.gen.bluexray.server.login.dto.LicenseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-21
 * Time: 오후 3:57
 */
@RequiredArgsConstructor
@Configuration
public class LicenseInterceptor extends HandlerInterceptorAdapter {

    private final LicenseRepository licenseRepository;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean check = false;
        String cash = redisTemplate.opsForValue().get(RedisContant.LICENSE_KEY);
        LicenseResponseDto dto = (LicenseResponseDto) JsonUtil.stringToClass(CommonUtil.checkDefaultNull(cash, ""), LicenseResponseDto.class);
        if (dto == null) {
            License license = licenseRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedAt")).stream().findFirst().orElseThrow(() -> new NotFoundLicenseException());
            dto = new LicenseResponseDto(license);
        }

        if (dto.getExpiredAt() != null) {
            LocalDate now = LocalDate.now();
            LocalDate expiredAt = CommonUtil.stringToLocalDate(dto.getExpiredAt());
            if (expiredAt.isAfter(now)) {
                check = true;
            }
        } else {
            check = true;
        }

        if (!check) {
            throw new NotFoundLicenseException();
        }
        redisTemplate.opsForValue().set(RedisContant.LICENSE_KEY, JsonUtil.dtoToString(dto), 1800, TimeUnit.SECONDS);

        return super.preHandle(request, response, handler);
    }
}
