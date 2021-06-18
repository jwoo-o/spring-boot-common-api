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
 * Time: 오후 4:57
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_agent_policy")
@Table(name = "tb_agent_policy", indexes = {
        @Index(name = "i_tb_agent_policy_pc_id_created_at", columnList = "pc_id,created_at"),
        @Index(name = "i_tb_agent_policy_created_at", columnList = "created_at")
})
public class AgentPolicy extends BaseEntity {

    /**
     * 정책
     */
    @Column(name = "policy", length = 4096, nullable = false)
    private String policy;

    @Column(name = "pc_id", length = 200, unique = true, nullable = false)
    private String pcId;

    @Builder
    public AgentPolicy(String policy, String pcId) {
        this.policy = policy;
        this.pcId = pcId;
    }

    public void update(String policy) {
        this.policy = policy;
    }
}
