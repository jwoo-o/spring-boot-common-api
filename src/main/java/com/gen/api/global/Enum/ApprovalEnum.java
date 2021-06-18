package com.gen.api.global.Enum;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-08
 * Time: 오후 6:06
 */
public enum ApprovalEnum {

    WAIT("WAIT"), APPROVE("APPROVE"), REJECT("REJECT"), CANCEL("CANCEL");


    private String value;

    ApprovalEnum(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
