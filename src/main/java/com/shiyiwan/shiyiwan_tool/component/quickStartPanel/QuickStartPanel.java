package com.shiyiwan.shiyiwan_tool.component.quickStartPanel;

import com.shiyiwan.shiyiwan_tool.entity.ApplicationContainer;

import javax.swing.*;
import java.awt.*;

/**
 * 快速启动面板
 */
public class QuickStartPanel extends JPanel {

    public QuickStartPanel() {
        setLayout(new FlowLayout());
        JButton jButton = new JButton("ping");
        jButton.setSize(60,60);
        jButton.addActionListener(e -> {
            ApplicationContainer.getCmdServerMap().get("1").sendCommand(null,"ping www.baidu.com");
        });
        add(jButton);
    }

}
