package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//todo 自定义选中高亮模拟select行为，使得选中不会影响光标位置
//todo 换成JTextPanel
//todo 双击选中


/**
 * CMD输入区域
 */
public class CMDInputTextArea extends JTextArea {

    private CMDService cmdService;

    private String prompt = "";

    public CMDInputTextArea(CMDDisplayTextArea cmdDisplayTextArea, CMDPanel1 cmdPanel) {

        setCaretPosition(getText().length());

        Font font = getFont();
        FontMetrics fontMetrics = getFontMetrics(font);
        int height = fontMetrics.getHeight();
        Insets insets = getInsets();
        int verticalInsets = insets.top + insets.bottom;
        int perfectHeight = height + verticalInsets;
        setPreferredSize(new Dimension(cmdPanel.getPreferredSize().width, perfectHeight));
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
                // 不能改动prompt
                if (getCaretPosition() <= prompt.length()) {
                    e.consume();
                    setCaretPosition(getText().length());
                    setEditable(true);
                }

                if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '\n') {
                    e.consume();
                    String cmd = getText();
                    cmd = cmd.substring(prompt.length());
                    if (cmd.length() == 1) return;
                    cmdService.sendCmd(cmd);
                    setText(prompt);
                    int perfectWidth = getParent().getWidth();
                    setPreferredSize(new Dimension(perfectWidth, getHeight()));
                    cmdPanel.setPreferredSize(new Dimension(500, getHeight() + cmdDisplayTextArea.getHeight() + 100));
                    setCaretPosition(getText().length());
                    requestFocusInWindow();
                }
            }

        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setEditable(getCaretPosition() >= prompt.length());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setEditable(getCaretPosition() >= prompt.length());
            }
        });

    }

    public void setPrompt(String prompt){
        this.prompt = prompt;
        setText(prompt);
        setCaretPosition(getText().length());
    }

    public void setCmdService(CMDService cmdService){
        this.cmdService = cmdService;
    }

    public static void main(String[] args) {
        TestComponentUtil.wrapComponent(new CMDInputTextArea(new CMDDisplayTextArea(), new CMDPanel1()));
    }
}
