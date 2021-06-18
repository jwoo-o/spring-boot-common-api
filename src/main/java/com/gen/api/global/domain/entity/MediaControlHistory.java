package com.gen.api.global.domain.entity;

import com.gen.api.global.Enum.BlockEnum;
import com.gen.api.global.Enum.DeviceEnum;
import com.gen.api.global.Enum.LogEnum;
import com.gen.api.global.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-25
 * Time: 오후 1:55
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_media_control_history")
@Table(name = "tb_media_control_history", indexes = {
        @Index(name = "i_tb_media_control_history_pc_id_created_at", columnList = "pc_id,created_at"),
        @Index(name = "i_tb_media_control_history_org_code_pc_id_created_at", columnList = "org_code,pc_id,created_at")
})
public class MediaControlHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * pc id
     */
    @Column(name = "pc_id", length = 200, nullable = false)
    private String pcId;

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
     * 로그의 종류
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "log_type", length = 50, nullable = false)
    private LogEnum logType;

    /**
     * 장치의 종류
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", length = 50, nullable = false)
    private DeviceEnum deviceType;

    /**
     * 장치의 종류
     */
    @Column(name = "processName", length = 50)
    private String processName;

    @Column(name = "path", length = 512)
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "block_type", length = 20, nullable = false)
    private BlockEnum blockType;


    /**
     * 반출 파일 원본
     */
    @Column(name = "file_id")
    private Long fileId;

    @OneToOne
    @JoinColumn(name = "file_id", insertable = false, updatable = false)
    private MediaFile mediaFile;

    @Builder
    public MediaControlHistory(String pcId, String userId, String orgCode, LocalDateTime agentAt, LogEnum logType, DeviceEnum deviceType, String processName, String path, BlockEnum blockType, String name, String pcIp) {
        this.pcId = pcId;
        this.userId = userId;
        this.orgCode = orgCode;
        this.agentAt = agentAt;
        this.logType = logType;
        this.deviceType = deviceType;
        this.processName = processName;
        this.path = path;
        this.blockType = blockType;
        this.name = name;
        this.pcIp = pcIp;
    }
}
