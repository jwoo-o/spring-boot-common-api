package com.gen.api.global.domain.entity;

import com.gen.api.global.Enum.LicenseEnum;
import com.gen.api.global.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-18
 * Time: 오후 4:13
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_license")
public class License extends BaseTimeEntity {

    @Id
    @Column(name = "license_key")
    private String licenseKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_name",length = 30,nullable = false)
    private LicenseEnum licenseName;

    @Column(name = "count",nullable = false)
    private int count;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    @Builder
    public License(String licenseKey, int count, LocalDate expiredAt,LicenseEnum licenseEnum, LocalDate publishedAt) {

        this.licenseKey = licenseKey;
        this.licenseName = licenseEnum;
        this.publishedAt = publishedAt;
        this.count = count;
        this.expiredAt = expiredAt;
    }
}
