package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseTimeEntity;
import com.gen.api.global.domain.entity.id.TotalId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2021-02-23
 * Time: 오전 11:26
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_update_history_total")
public class UpdateHistoryTotal extends BaseTimeEntity {

    @EmbeddedId
    private TotalId id;

    /**
     * 집계 내용
     */
    @Column(name = "count_content",length = 2048)
    private String countContent;
}
