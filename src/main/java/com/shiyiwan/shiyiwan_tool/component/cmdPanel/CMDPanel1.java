package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;

public class CMDPanel1 extends JPanel {

    public CMDPanel1() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(43, 43, 43));

        CMDDisplayTextArea cmdDisplayTextArea = new CMDDisplayTextArea();
        CMDInputTextArea cmdInputTextArea = new CMDInputTextArea(cmdDisplayTextArea, this);

        this.add(cmdDisplayTextArea);
        this.add(cmdInputTextArea);
    }

    public static void main(String[] args) {
        CMDPanel1 cmdPanel1 = new CMDPanel1();
        JScrollPane jScrollPane = new JScrollPane(cmdPanel1);
        TestComponentUtil.wrapComponent(jScrollPane);

        JViewport viewport = jScrollPane.getViewport();
        CMDPanel1 component = ((CMDPanel1) viewport.getComponent(0));
        component.getComponent(1).requestFocus();

    }

}
