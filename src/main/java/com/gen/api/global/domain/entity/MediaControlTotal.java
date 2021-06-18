package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseTimeEntity;
import com.gen.api.global.domain.entity.id.TotalId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-15
 * Time: 오후 3:59
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_media_control_total")
public class MediaControlTotal extends BaseTimeEntity {

    @EmbeddedId
    private TotalId id;

    /**
     * 집계 총 수
     */
    @Column(name = "count")
    private int count;
    /**
     * 집계 내용
     */
    @Column(name = "count_content",length = 1024)
    private String countContent;
}
