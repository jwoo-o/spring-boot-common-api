package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseTimeEntity;
import com.gen.api.global.domain.entity.id.CommonCodeDetailId;
import com.gen.bluexray.server.cmmn.dto.CommonDetailCodeRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오전 10:35
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_code_detail")
//@IdClass(CommonCodeDetailId.class)
public class CommonCodeDetail extends BaseTimeEntity implements Persistable<CommonCodeDetailId> {

    @EmbeddedId
    private CommonCodeDetailId id;

    @Column(name = "detail_name", nullable = false)
    private String detailName;

    @Column(name = "detail_desc", nullable = false)
    private String detailDesc;

    @Column(name = "order")
    private int order;

    @MapsId("group_code")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_code", updatable = false, insertable = false)
    private CommonCodeGroup commonCodeGroup;

    @Builder
    public CommonCodeDetail(CommonCodeDetailId id, String detailName, int order, String detailDesc) {
        this.detailName = detailName;
        this.id = id;
        this.detailDesc = detailDesc;
        this.order = order;
    }


    public void update(CommonDetailCodeRequestDto requestDto) {

        this.detailName = requestDto.getDetailName();
        this.detailDesc = requestDto.getDetailDesc();
        this.order = requestDto.getOrder();
    }

    @Override
    public CommonCodeDetailId getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
