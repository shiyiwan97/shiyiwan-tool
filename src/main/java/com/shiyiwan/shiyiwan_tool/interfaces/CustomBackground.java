package com.shiyiwan.shiyiwan_tool.interfaces;

import java.awt.*;

public interface CustomBackground {

    Color getBackgroundColor();

    void setBackgroundColor(Color color);

    // todo 背景材质
    String getBackgroundTexture();

    void setBackgroundTexture(String texture);

    // todo 透明度
    int getBackgroundOpacity();

    void setBackgroundOpacity(int opacity);

}
