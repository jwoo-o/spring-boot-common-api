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
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-07-03
 * Time: 오후 4:34
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_agent")
@Table(name = "tb_agent", indexes = {
        @Index(name = "i_tb_agent_deletion_created_at", columnList = "deletion,created_at"),
        @Index(name = "i_tb_agent_deletion_org_code_created_at", columnList = "deletion,org_code,created_at"),
        @Index(name = "i_tb_agent_deletion_org_code_user_id", columnList = "deletion,org_code,user_id"),
        @Index(name = "i_tb_agent_login_yn_last_login_time", columnList = "login_yn,last_login_time"),
        @Index(name = "i_tb_agent_login_yn_org_code_last_login_time", columnList = "login_yn,org_code,last_login_time"),
        @Index(name = "i_tb_agent_org_code_version", columnList = "org_code,version")
})
public class Agent extends BaseTimeEntity implements Persistable<String> {

    /**
     * pc id
     */
    @Id
    @Column(name = "pc_id", length = 200)
    private String pcId;

    /**
     * pc ip
     */
    @Column(length = 100, name = "pc_ip")
    private String pcIp;


    /**
     * 최종로그인시간
     */
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 에이전트 버전
     */
    @Column(length = 50)
    private String version;

    /**
     * 사용자 계정
     */
    @Column(length = 30, name = "user_id")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    /**
     * 사용자 이름
     */
    @Column(name = "name", length = 30)
    private String name;

    /**
     * 부서 코드
     */
    @Column(length = 200, name = "org_code", nullable = false)
    private String orgCode;

    /**
     * 정책수신시간
     */
    @Column
    private LocalDateTime syncTime;

    /**
     * 에이전트 삭제유무
     */
    @Column(name = "deletion", length = 2, nullable = false)
    @Convert(converter = BooleanToYNConverter.class)
    private boolean deletion;


    /**
     * 검사관련 { A : 정기검사 , N : 명령없음 , C : 강제검사}
     */
    @Column(length = 2)
    private String status;

    /**
     * 로그인 유무
     */
    @Column(name = "login_yn", length = 2)
    @Convert(converter = BooleanToYNConverter.class)
    private boolean loginYn;

    @OneToMany(mappedBy = "agent")
    private List<RequestApproval> requestApprovals = new ArrayList<>();

    @OneToMany(mappedBy = "agent")
    private List<AgentSystemInfo> agentPrograms = new ArrayList<>();

    @Override
    public String getId() {
        return pcId;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public void login() {
        this.lastLoginTime = LocalDateTime.now();
        this.loginYn = true;
        this.deletion = false;
    }

    public void login(String version) {
        this.lastLoginTime = LocalDateTime.now();
        this.loginYn = true;
        this.deletion = false;
        this.version = version;
    }

    public void login(Map<String,Object> data) {
        this.lastLoginTime = LocalDateTime.now();
        this.loginYn = true;
        this.deletion = false;
        this.version = (String) data.get("version");
        this.pcIp = (String) data.get("pcIp");
    }

    public void logOut() {
        this.loginYn = false;
    }

    public void update(User user, String version) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.orgCode = user.getOrgCode();
        this.version = version;
        this.deletion = false;

    }
    public void update(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.orgCode = user.getOrgCode();
        this.deletion = false;

    }

    public void delete() {
        this.userId = null;
        this.name = null;
        this.orgCode = "-1";
    }

    @Builder
    public Agent(String pcId, String pcIp, String userId, String name, String orgCode, String version) {

        this.pcId = pcId;
        this.pcIp = pcIp;
        this.userId = userId;
        this.name = name;
        this.orgCode = orgCode;
        this.deletion = false;
        this.version = version;
    }
}
