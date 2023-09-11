package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * CMD输入区域
 */
public class CMDInputTextArea extends JTextArea {

    public CMDInputTextArea(CMDDisplayTextArea cmdDisplayTextArea, CMDPanel1 cmdPanel1) {
        String prompt = "C:\\Users\\shiyiwan>";
        setText(prompt);
        setCaretPosition(getText().length());

        Font font = getFont();
        FontMetrics fontMetrics = getFontMetrics(font);
        int height = fontMetrics.getHeight();
        Insets insets = getInsets();
        int verticalInsets = insets.top + insets.bottom;
        int perfectHeight = height + verticalInsets;
        setPreferredSize(new Dimension(cmdPanel1.getPreferredSize().width, perfectHeight));
        setBorder(null);
        // 自动换行
        setLineWrap(true);
        setWrapStyleWord(true);

        setBackground(new Color(43, 43, 43));
        setForeground(Color.white);
        setCaretColor(Color.white);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '\n') {
                    e.consume();
                    String cmd = getText();
                    cmd = cmd.substring(prompt.length());
                    if (cmd.length() == 1) return;
                    cmdDisplayTextArea.updateContent(prompt + cmd);
                    setText(prompt);
                    int perfectWidth = getParent().getWidth();
                    setPreferredSize(new Dimension(perfectWidth, getHeight()));
                    cmdPanel1.setPreferredSize(new Dimension(500, getHeight() + cmdDisplayTextArea.getHeight() + 100));
                    setCaretPosition(getText().length());
                    requestFocusInWindow();
                }
            }

        });
    }

    public static void main(String[] args) {
        TestComponentUtil.wrapComponent(new CMDInputTextArea(new CMDDisplayTextArea(), new CMDPanel1()));
    }
}
