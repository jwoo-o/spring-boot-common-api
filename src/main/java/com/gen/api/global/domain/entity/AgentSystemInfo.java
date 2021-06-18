package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2021-01-20
 * Time: 오후 4:03
 */
@NoArgsConstructor
@Getter
@ToString
@Entity(name = "tb_agent_system_info")
public class AgentSystemInfo extends BaseTimeEntity {

    /**
     * pc id
     */
    @Id
    @Column(name = "pc_id", length = 200)
    private String pcId;

    @Column(name = "programs",length = 4096)
    private String programs;

    @Column(name = "pc_info",length = 4096)
    private String pcInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id", insertable = false, updatable = false)
    private Agent agent;

    public void update(String programs, String pcInfo) {
        this.programs = programs;
        this.pcInfo = pcInfo;
    }


    @Builder
    public AgentSystemInfo(String pcId, String programs, String pcInfo) {
        this.pcId = pcId;
        this.programs = programs;
        this.pcInfo = pcInfo;
    }
}
