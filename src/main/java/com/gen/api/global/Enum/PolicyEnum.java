package com.gen.api.global.Enum;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-02
 * Time: 오후 3:54
 */
public enum PolicyEnum {

    ORGANIZATION("부서"), USER("개인"), AGENT("PC");

    private String value;

    PolicyEnum(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
