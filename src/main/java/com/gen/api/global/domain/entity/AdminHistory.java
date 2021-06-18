package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "tb_admin_history")
@Table(name = "tb_admin_history", indexes = {
        @Index(name = "i_tb_admin_history_created_at", columnList = "created_at"),
        @Index(name = "i_tb_admin_history_admin_id_created_at", columnList = "admin_id,created_at")
})
public class AdminHistory extends BaseEntity {

    @Column(name = "table_name", length = 100)
    private String tableName;

    /**
     * 관리자 아이디
     */
    @Column(name = "admin_id", length = 20)
    private String adminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", updatable = false, insertable = false)
    private Admin admin;

    /**
     * 요청 내용
     */
    @Column(name = "message", length = 512)
    private String message;

    @Column(name = "data", length = 4096)
    private String data;

    @Builder
    public AdminHistory(String tableName, String adminId, String message, String data) {
        this.tableName = tableName;
        this.adminId = adminId;
        this.message = message;
        this.data = data;
    }
}
