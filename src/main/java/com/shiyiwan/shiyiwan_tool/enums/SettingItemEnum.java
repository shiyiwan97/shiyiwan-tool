package com.shiyiwan.shiyiwan_tool.enums;

public enum SettingItemEnum {

    INPUT(1),
    RADIO(2),
    CHECKBOX(3),
    SELECT(4),
    SPLIT_LINE(0);


    private final int id;

    SettingItemEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
