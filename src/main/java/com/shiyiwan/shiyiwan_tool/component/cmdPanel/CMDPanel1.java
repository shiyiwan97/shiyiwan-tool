package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;

public class CMDPanel1 extends JPanel {

    public CMDPanel1() {
        super();
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(500, 500));


        CMDDisplayTextArea cmdDisplayTextArea = new CMDDisplayTextArea();
        JTextField cmdInputTextArea = new JTextField();

        cmdInputTextArea.addActionListener(e -> {
            String cmd = cmdInputTextArea.getText();
            cmdDisplayTextArea.updateContent(cmd);
            cmdInputTextArea.setText("");
            int perfectWidth = getParent().getWidth();
            cmdInputTextArea.setPreferredSize(new Dimension(perfectWidth, cmdInputTextArea.getHeight()));
            this.setPreferredSize(new Dimension(500, cmdInputTextArea.getHeight() + cmdDisplayTextArea.getHeight() + 100));
            System.out.println(cmdInputTextArea.getSize().toString());
        });
        this.add(cmdDisplayTextArea);
        this.add(cmdInputTextArea);

    }

    public static void main(String[] args) {
        CMDPanel1 cmdPanel1 = new CMDPanel1();
        JScrollPane jScrollPane = new JScrollPane(cmdPanel1);
        TestComponentUtil.wrapComponent(jScrollPane);
    }

}
