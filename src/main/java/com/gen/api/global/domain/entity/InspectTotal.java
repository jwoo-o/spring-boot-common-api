package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseTimeEntity;
import com.gen.api.global.domain.entity.id.InspectTotalId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2021-01-14
 * Time: 오후 12:28
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_inspect_total")
@Table(name = "tb_inspect_total")
public class InspectTotal extends BaseTimeEntity {

    @EmbeddedId
    private InspectTotalId id;

    /**
     * 전체 PC
     */
    @Column(name = "total_pc_count")
    private int totalPcCount;

    /**
     * 검사된 PC
     */
    @Column(name = "result_pc_count")
    private int resultPcCount;

    /**
     * 검출 파일 갯수
     */
    @Column(name = "result_file_count")
    private int resultFileCount;

    /**
     * 전체 파일 갯수
     */
    @Column(name = "total_file_count")
    private int totalFileCount;

    /**
     * 집계 내용
     */
    @Column(name = "count_content",length = 1024)
    private String countContent;
}
