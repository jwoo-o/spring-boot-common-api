package com.gen.api.global.Enum;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-21
 * Time: 오후 1:06
 */
public enum LicenseEnum {

    UNLIMITED("무제한"),NORMAL("일반"),DEMO("데모");

    private String label;

    LicenseEnum(String label) {
        this.label = label;
    }
    public String getKey() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
