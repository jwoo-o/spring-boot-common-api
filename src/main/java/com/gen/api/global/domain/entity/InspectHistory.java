package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2021-01-14
 * Time: 오후 12:28
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_inspect_history")
@Table(name = "tb_inspect_history",indexes = {
        @Index(name = "i_tb_inspect_history_org_code_pc_id_prd_code_agent_at",columnList = "org_code,pc_id,prd_code,agent_at"),
        @Index(name = "i_tb_inspect_history_prd_code_pc_id_agent_at",columnList = "prd_code,pc_id,agent_at")
})
public class InspectHistory extends BaseEntity {

    /**
     * pc id
     */
    @Column(name = "pc_id", length = 200, nullable = false)
    private String pcId;

    @Column(name= "prd_code", length = 10, nullable = false)
    private String prdCode;

    /**
     * pc ip
     */
    @Column(length = 100, name = "pc_ip", nullable = false)
    private String pcIp;

    /**
     * 사용자계정
     */
    @Column(length = 20, name = "user_id")
    private String userId;

    @Column(length = 30)
    private String name;

    /**
     * 부서코드
     */
    @Column(length = 200, name = "org_code", nullable = false)
    private String orgCode;

    /**
     * 사용자 pc에서 발생 시간
     */
    @Column(name = "agent_at")
    private LocalDateTime agentAt;

    /**
     * 검출된 개인정보 데이터
     */
    @Column(name = "inspect_data", length = 4096)
    private String inspectData;


}
