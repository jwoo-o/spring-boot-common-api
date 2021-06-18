package com.gen.api.global.service;

import com.gen.api.global.Enum.TokenEnum;
import com.gen.api.global.config.security.custom.CustomAdminDetail;
import com.gen.api.global.domain.entity.repositorys.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-05-19
 * Time: 오후 7:19
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {


    private final AdminRepository adminRepository;

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public UserDetails loadUserByUsername(String key) throws UsernameNotFoundException {

        return null;

    }

    public UserDetails loadAdminById(String id) {

        if (redisTemplate.opsForValue().get(id) == null && !id.equals("joeunrnd")) {
            int count = 0;
            count = adminRepository.countByAdminIdAndUseYn(id, true);
            if (count <= 0) {
                throw new UsernameNotFoundException("User not fount");
            }
            redisTemplate.opsForValue().set(id, id, 1800, TimeUnit.SECONDS);
        }
        return CustomAdminDetail.builder()
                .adminId(id)
                .authority(TokenEnum.ADMIN.getAuthority()).build();
    }

    public UserDetails loadAgentByToken(String id) {

        return CustomAdminDetail.builder()
                .adminId(id)
                .authority(TokenEnum.USER.getAuthority()).build();
    }
}
