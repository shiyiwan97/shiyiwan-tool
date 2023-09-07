package com.shiyiwan.shiyiwan_tool.util;

import javax.swing.*;
import java.awt.*;

public class TestComponentUtil {

    public static JFrame wrapComponent(Component component) {
        JFrame frame = new JFrame();
        frame.setLocation(1400,150);
        frame.add(component);
        frame.setLayout(null);
//        frame.pack();
        frame.setSize((int)component.getPreferredSize().getWidth() + 300,(int) component.getPreferredSize().getHeight() + 180);
        frame.setVisible(true);
        return frame;
    }

}
