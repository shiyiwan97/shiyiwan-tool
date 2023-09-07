package com.shiyiwan.shiyiwan_tool.enums;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BorderConst {
    public static final Border TOP_AND_BOTTOM_ONE_MATTE;

    static {
        TOP_AND_BOTTOM_ONE_MATTE = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(48, 50, 52));
    }

}
