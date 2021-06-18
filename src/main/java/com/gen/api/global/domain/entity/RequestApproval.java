package com.gen.api.global.domain.entity;

import com.gen.api.global.Enum.ApprovalEnum;
import com.gen.api.global.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity(name = "tb_request_approval")
@Table(name = "", indexes = {
        @Index(name = "i_tb_request_approval_approval_type_pc_id_modified_at", columnList = "approval_type,pc_id,modified_at")
})
public class RequestApproval extends BaseEntity {

    /**
     * 등록자 pc Id
     */
    @Column(name = "pc_id", length = 200, nullable = false)
    private String pcId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id", updatable = false, insertable = false)
    private Agent agent;

    /**
     * 관리자 승인/거절 시간
     */
    @Column(name = "approval_at")
    private LocalDateTime approvalAt;

    /**
     * 파기 되는 시간
     */
    @Column(name = "expire_at", nullable = false)
    private LocalDate expireAt;

    /**
     * 파일 반출 목적
     */
    @Column(name = "export_purpose", length = 1024, nullable = false)
    private String exportPurpose;

    /**
     * 파일 반출 목적
     */
    @Column(name = "reject_reason", length = 1024)
    private String rejectReason;

    /**
     * 상태 코드
     */
    @Column(name = "approval_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApprovalEnum approvalType;

    /**
     * 관리자 승인 계정
     */
    @Column(name = "admin_id", length = 20)
    private String adminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", updatable = false, insertable = false)
    private Admin admin;

    /**
     * 등록자 아이디
     */
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;


    @OneToMany(mappedBy = "requestApproval", cascade = CascadeType.ALL)
    private List<ApprovalFile> approvalFiles = new ArrayList<>();


    @Builder
    public RequestApproval(String pcId, LocalDate expireAt, String exportPurpose, ApprovalEnum approvalType, String userId) {
        this.pcId = pcId;
        this.expireAt = expireAt;
        this.exportPurpose = exportPurpose;
        this.approvalType = approvalType;
        this.userId = userId;
    }
}
