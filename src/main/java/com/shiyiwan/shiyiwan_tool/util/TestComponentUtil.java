package com.shiyiwan.shiyiwan_tool.util;

import javax.swing.*;
import java.awt.*;

public class TestComponentUtil {

    public static JFrame wrapComponent(Component component) {
        JFrame frame = new JFrame();
        frame.setLocation(1400,150);
        frame.setSize((int)component.getPreferredSize().getWidth() + 300,(int) component.getPreferredSize().getHeight() + 180);
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        frame.add(component,gbc);
//        frame.pack();
        return frame;
    }

    public static void main(String[] args) {
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setPreferredSize(new Dimension(300,300));
        wrapComponent(redPanel);
    }

}
