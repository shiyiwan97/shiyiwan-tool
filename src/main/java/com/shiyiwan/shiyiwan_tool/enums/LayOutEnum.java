package com.shiyiwan.shiyiwan_tool.enums;

import java.awt.*;

public enum LayOutEnum {

    OneRowGridLayout(new GridLayout(2, 1));


    private final LayoutManager layoutManager;

    LayOutEnum(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public LayoutManager getLayout() {
        return layoutManager;
    }

    private static LayoutManager getOneRowGridLayout() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(1);
        return gridLayout;
    }
}
