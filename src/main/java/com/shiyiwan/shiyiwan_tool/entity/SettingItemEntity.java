package com.shiyiwan.shiyiwan_tool.entity;

import lombok.Data;

import java.awt.*;

/**
 * 设置菜单的设置项（右侧面板里的具体设置项或分割线）
 */
@Data
public class SettingItemEntity {

    private String itemId;

    private String itemName;

    private Integer itemType;

    private Integer locationX;

    private Integer locationY;

    private Component itemComponent;
}
