package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseTimeEntity;
import com.gen.api.global.domain.common.BooleanToYNConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-24
 * Time: 오전 9:20
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_admin")
@Table(name = "tb_admin",
        indexes = {
                @Index(name = "i_tb_admin_use_yn_created_at", columnList = "use_yn,created_at"),
                @Index(name = "i_tb_admin_use_yn_mgr_org_code_created_at", columnList = "use_yn,mgr_org_code,created_at"),
                @Index(name = "i_tb_admin_name_use_yn_mgr_org_code_created_at", columnList = "name,use_yn,mgr_org_code,created_at")
        })
public class Admin extends BaseTimeEntity implements Persistable<String> {

    /**
     * 관리자계정
     */
    @Id
    @Column(length = 20, name = "admin_id")
    private String adminId;

    /**
     * 패스워드
     */
    @Column(length = 100)
    private String password;

    /**
     * 암호화 유무
     */
    @Convert(converter = BooleanToYNConverter.class)
    @Column(length = 2)
    private boolean salt;

    /**
     * 이름
     */
    @Column(length = 30)
    private String name;

    /**
     * 부서코드
     */
    @Column(name = "org_code", nullable = false,length = 200)
    private String orgCode;

    /**
     * 관리부서 코드
     */
    @Column(name = "mgr_org_code", nullable = false,length = 200)
    private String mgrOrgCode;

    /**
     * 접속 IP
     */
    @Column(name = "con_ip")
    private String conIp;

    /**
     * 이메일
     */
    @Column(name = "email")
    private String email;

    /**
     * 전화번호
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 계정 사용 여부
     */
    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn", length = 2, nullable = false)
    private boolean useYn;

    @OneToMany(mappedBy = "admin")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "admin")
    private List<AdminHistory> adminHistories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_code", updatable = false, insertable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mgr_org_code", updatable = false, insertable = false)
    private Organization mgrOrganization;

    /**
     * 권한
     */
    @Column(name = "role_code", nullable = false)
    private Long roleCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_code", updatable = false, insertable = false)
    private Role role;

    /**
     * 최종로그인시간
     */
    @Column
    private LocalDateTime lastLoginTime;

    @Builder
    public Admin(String adminId, String password, String name, String orgCode, String mgrOrgCode, String conIp, String email, String tel, Long roleCode) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.orgCode = orgCode;
        this.mgrOrgCode = mgrOrgCode;
        this.conIp = conIp;
        this.email = email;
        this.tel = tel;
        this.roleCode = roleCode;
        this.useYn = true;
    }

    public void changePassword(String encPwd, boolean salt) {

        this.password = encPwd;
        this.salt = salt;
    }


    public void delete() {
        this.useYn = false;
    }

    public void login() {

        this.lastLoginTime = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return adminId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
