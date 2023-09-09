package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.component.bottomBar.MemoryMonitorPanel;
import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 命令窗口面板
 */
public class CMDPanel extends JPanel{

    private JTextArea outPutTextArea;
    private JTextArea inputTextField;

    public CMDPanel() {
        super();
        outPutTextArea = new JTextArea();
        inputTextField = new InputTextField();
        JScrollPane scrollPane = new JScrollPane(outPutTextArea);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridy = 2;

        this.add(inputTextField,gbc);
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setPreferredSize(new Dimension(100,100));

        this.add(redPanel,gbc);
//        this.setLayout(null);
//        this.add(redPanel,gbc);
        this.setPreferredSize(new Dimension(300, 300));
    }

    public static void main(String[] args) {
        TestComponentUtil.wrapComponent(new CMDPanel());
    }
}
