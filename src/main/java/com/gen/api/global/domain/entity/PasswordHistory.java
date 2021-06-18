package com.gen.api.global.domain.entity;

import com.gen.api.global.Enum.LoginHistoryEnum;
import com.gen.api.global.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Entity(name = "tb_password_history")
public class PasswordHistory extends BaseEntity {

    /**
     * 로그인 아이디
     */
    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    /**
     * 로그인 분류(사용자[agent],관리자)
     */
    @Column(name = "login_type")
    @Enumerated(EnumType.STRING)
    private LoginHistoryEnum loginType;


    @Builder
    public PasswordHistory(String loginId, String password, LoginHistoryEnum loginType) {
        this.loginId = loginId;
        this.password = password;
        this.loginType = loginType;
    }
}
