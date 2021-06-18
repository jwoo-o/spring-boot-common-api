package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-26
 * Time: 오후 1:20
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_media_file")
public class MediaFile extends BaseEntity {

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
    private int fileSize;


}
