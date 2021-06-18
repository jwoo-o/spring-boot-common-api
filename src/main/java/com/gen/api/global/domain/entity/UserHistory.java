package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "tb_user_history")
public class UserHistory extends BaseEntity {

    @Column(name = "table_name", length = 100)
    private String tableName;

    /**
     * 사용자 아이디
     */
    @Column(name = "user_id", length = 20)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    /**
     * 요청 내용
     */
    @Column(name = "message", length = 512)
    private String message;
}
