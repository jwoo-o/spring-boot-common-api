package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-24
 * Time: 오후 4:58
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_user_policy")
@Table(name = "tb_user_policy", indexes = {@Index(name = "i_tb_user_policy_user_id_modified_at", columnList = "user_id,modified_at")})
public class UserPolicy extends BaseEntity {

    /**
     * 정책
     */
    @Column(name = "policy", length = 4096)
    private String policy;


    @Column(name = "user_id", unique = true, length = 20)
    private String userId;


    @Builder
    public UserPolicy(String policy, String userId) {
        this.policy = policy;
        this.userId = userId;
    }

    public void update(String policy) {
        this.policy = policy;
    }
}
