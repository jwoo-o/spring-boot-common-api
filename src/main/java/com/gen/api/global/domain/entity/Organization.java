package com.gen.api.global.domain.entity;


import com.gen.api.global.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-07-03
 * Time: 오후 5:46
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_organization")
@Table(name = "tb_organization", indexes = {
        @Index(name = "i_tb_organization_org_pa_code", columnList = "org_pa_code"),
        @Index(name = "i_tb_organization_org_code_order", columnList = "org_code,order")})
public class Organization extends BaseTimeEntity implements Persistable<String> {

    /**
     * 부서코드
     */
    @Id
    @Column(name = "org_code", length = 200)
    private String orgCode;

    /**
     * 상위 부서코드
     */
    @Column(name = "org_pa_code", length = 200)
    private String orgPaCode;


    /**
     * 부서이름
     */
    @Column(name = "org_name", length = 30)
    private String orgName;

    /**
     * 전체 부서이름
     */
    @Column(name = "org_full_name", length = 300)
    private String orgFullName;

    /**
     * 전체 부서이름
     */
    @Column(name = "org_full_code", length = 2048)
    private String orgFullCode;

    /**
     * 부서순서
     */
    @Column
    private int order;

    @OneToMany(mappedBy = "organization")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "mgrOrganization")
    private List<Admin> admins = new ArrayList<>();


    @Builder
    public Organization(String orgCode, String orgPaCode, String orgName, String orgFullName, int order, String orgFullCode) {
        this.orgCode = orgCode;
        this.orgPaCode = orgPaCode;
        this.orgName = orgName;
        this.orgFullName = orgFullName;
        this.order = order;
        this.orgFullCode = orgFullCode;
    }

    @Override
    public String getId() {
        return orgCode;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public void update(String orgName) {
        this.orgName = orgName;
    }
}
