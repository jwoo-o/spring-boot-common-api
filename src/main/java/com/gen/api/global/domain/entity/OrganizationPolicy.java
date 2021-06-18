package com.gen.api.global.domain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gen.api.global.domain.common.BaseEntity;
import com.gen.api.global.util.JsonUtil;
import com.gen.bluexray.server.policy.dto.PolicyOrgRequestDto;
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
@Entity(name = "tb_organization_policy")
public class OrganizationPolicy extends BaseEntity {


    @Column(name = "org_code", unique = true,nullable = false)
    private String orgCode;

    /**
     * 정책
     */
    @Column(name = "policy", length = 4096)
    private String policy;

    @Column(name = "setting_id", nullable = false)
    private Long settingId;

    @ManyToOne
    @JoinColumn(name = "setting_id", insertable = false, updatable = false)
    private Setting setting;


    public void update(PolicyOrgRequestDto dto) throws JsonProcessingException {

        this.policy = JsonUtil.dtoToString(dto.getPolicy());
        this.settingId = dto.getSettingId();
    }


    @Builder
    public OrganizationPolicy(String orgCode, String policy, Long settingId) {
        this.orgCode = orgCode;
        this.policy = policy;
        this.settingId = settingId;
    }
}
