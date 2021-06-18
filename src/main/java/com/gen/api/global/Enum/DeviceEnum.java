package com.gen.api.global.Enum;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-08-26
 * Time: 오후 1:00
 */
public enum DeviceEnum {

    USB(0, "USB"), OPTICAL(1, "CD/DVD"), EXTERNAL(2, "EXTERNAL"), MTP_PTP(3, "MTP/PTP"), NETWORK(4, "NETWORK"), PRINT(5, "PRINT"), UNKNOWN(6, "UNKNOWN"),
    BROWSER(7, "BROWSER"), MAIL(8, "MAIL"), MESSENGER(9, "MESSENGER");
    private int value;
    private String label;

    DeviceEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getKey() {
        return name();
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
