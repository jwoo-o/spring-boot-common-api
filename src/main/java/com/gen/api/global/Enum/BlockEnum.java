package com.gen.api.global.Enum;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-26
 * Time: 오후 1:04
 */
public enum BlockEnum {

    PERMIT("1", "반출시도"), BLOCK("2", "차단");

    private String value;
    private String label;

    BlockEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
