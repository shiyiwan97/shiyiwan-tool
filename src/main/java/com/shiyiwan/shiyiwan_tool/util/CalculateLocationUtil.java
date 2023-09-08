package com.shiyiwan.shiyiwan_tool.util;

import java.awt.*;

/**
 * 组件位置计算工具类
 */
public class CalculateLocationUtil {

    // todo 有问题
    public static Point textCenterLocation(int textWidth,int textHeight, int parentWidth, int parentHeight){
        int x = (parentWidth - textWidth) / 2;
        int y = (parentHeight - textHeight) / 2;
        return new Point(x, y);
    }


}
