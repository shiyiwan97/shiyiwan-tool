package com.shiyiwan.shiyiwan_tool.component.bottomBar;

import com.shiyiwan.shiyiwan_tool.enums.BorderConst;

import javax.swing.*;
import java.awt.*;

/**
 * 底部状态栏
 */
public class BottomBarPanel extends JPanel {

    public BottomBarPanel() {
        super();
        this.setPreferredSize(new Dimension(0, 25));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 设置右边面板靠右对齐
        gbc.anchor = GridBagConstraints.EAST;

        this.add(new MemoryMonitorPanel(),gbc);
        this.setBorder(BorderConst.BOTTOM_TOP_MATTE);
    }
}
