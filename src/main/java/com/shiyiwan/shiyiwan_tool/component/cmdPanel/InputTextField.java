package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 命令窗口输入框
 */
public class InputTextField extends JTextArea {

    private int lineHeight;
    public InputTextField() {
        super();
        lineHeight = this.getFontMetrics(this.getFont()).getHeight();
        this.setPreferredSize(new Dimension(0, lineHeight));

        KeyAdapter watchEnter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    adjustPerfectHeight();
                }
            }
        };
        this.addKeyListener(watchEnter);

    }

    private void adjustPerfectHeight(){
        int height = this.getLineCount() * lineHeight;
        this.setPreferredSize(new Dimension(0, height));
    }

}
