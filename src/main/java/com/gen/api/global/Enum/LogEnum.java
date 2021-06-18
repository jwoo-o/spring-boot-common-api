package com.gen.api.global.Enum;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-26
 * Time: 오후 1:02
 */
public enum LogEnum {

    ONLINE("1", "온라인"), OFFLINE("2", "오프라인");

    private String value;
    private String label;

    LogEnum(String value, String label) {
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
