package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import com.gen.api.global.domain.common.BooleanToYNConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2021-02-19
 * Time: 오후 3:07
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_update_history")
public class UpdateHistory extends BaseEntity {

    @Column(length = 255,nullable = false,unique = true)
    private String version;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn", length = 2, nullable = false)
    private boolean useYn;

    @Column(length = 512)
    private String reason;

    /**
     * 파일이름
     */
    @Column
    private String fileName;

    /**
     * 업로드된  파일이름
     */
    @Column
    private String key;

    /**
     * 파일 사이즈
     */
    @Column
    private Long fileSize;

    @Builder
    public UpdateHistory(String version, boolean useYn, String reason, String fileName, String key, Long fileSize) {
        this.version = version;
        this.useYn = useYn;
        this.reason = reason;
        this.fileName = fileName;
        this.key = key;
        this.fileSize = fileSize;
    }

    public void update() {
        this.useYn = true;
    }
}
