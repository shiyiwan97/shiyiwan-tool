package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

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

    private int length;

    private final CMDPanel1 cmdPanel;

    private CMDDisplayTextArea cmdDisplayTextArea;

    private int lastLineCount;

    private int width;

    private int oneLineHeight;

    private boolean editable = true;

    public CMDInputTextArea(CMDDisplayTextArea cmdDisplayTextArea, CMDPanel1 cmdPanel) {
        this.cmdDisplayTextArea = cmdDisplayTextArea;
        this.cmdPanel = cmdPanel;
        setCaretPosition(getText().length());

        Font font = getFont();
        FontMetrics fontMetrics = getFontMetrics(font);
        int height = fontMetrics.getHeight();
        Insets insets = getInsets();
        int verticalInsets = insets.top + insets.bottom;
        int perfectHeight = height + verticalInsets;
        oneLineHeight = perfectHeight;
        setPreferredSize(new Dimension(cmdPanel.getPreferredSize().width, perfectHeight));
        setBorder(null);
        // 自动换行
        setLineWrap(true);
        setWrapStyleWord(true);

        setBackground(new Color(43, 43, 43));
        setForeground(new Color(187, 187, 187));
        setCaretColor(new Color(187, 187, 187));

        addKeyListener(new PromptReadOnlyKeyAdapter());

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

    public void setPrompt(String prompt) {
        this.prompt = prompt;
        setText(prompt);
        int newLength = getText().length();
        if (newLength != length) {
            length = newLength;
            removeKeyListener(getKeyListeners()[0]);
            addKeyListener(new PromptReadOnlyKeyAdapter());
        }
        resizeByChangeContent();
        setCaretPosition(newLength);
        setEditable(true);
    }

    public void setCmdService(CMDService cmdService) {
        this.cmdService = cmdService;
    }

    public void afterSendCmd() {
        setEditable(true);
        setText(null);
        setEditable(false);
    }

    // 更新内容后改变大小
    public void resizeByChangeContent() {
        setPreferredSize(new Dimension(getParent().getWidth(), oneLineHeight));
    }

    // 父组件大小改变/父组件的子组件数量改变时改变大小
    public void resizeByParentChange(int width) {
        setPreferredSize(new Dimension(width, oneLineHeight));
    }

//    @Override
//    public void setEditable(boolean editable) {
//        this.editable = editable;
//    }

    class PromptReadOnlyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // 不可编辑则直接禁用全部
            if(!editable) {
                e.consume();
                return;
            }

            // 左右移动、shift不影响
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_SHIFT) {
                return;
            }
            // 不能改动prompt
            if (getCaretPosition() < length) {
                e.consume();
                setCaretPosition(getText().length());
                setEditable(true);
                return;
            }
            setEditable(true);

            if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '\n') {
                e.consume();
                String cmd = getText();
                cmd = cmd.substring(prompt.length());
                if (cmd.isEmpty()) return;
                cmdService.sendCommand(prompt, cmd);
//                setText(null);
//                setEditable(false);
//                int perfectWidth = getParent().getWidth();
//                setPreferredSize(new Dimension(perfectWidth, getHeight()));
//                    cmdPanel.setPreferredSize(new Dimension(500, getHeight() + cmdDisplayTextArea.getHeight() + 100));
//                    setCaretPosition(length);
                requestFocusInWindow();
            }

            // todo shift+enter 换行，换行后改变lastLineCount
        }

    }

    public static void main(String[] args) {
//        TestComponentUtil.wrapComponent(new CMDInputTextArea(new CMDDisplayTextArea(), new CMDPanel1()));
    }
}
