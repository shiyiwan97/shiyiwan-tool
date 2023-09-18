package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;

/**
 * CMD显示区域
 */
public class CMDDisplayTextArea extends JTextArea {

    public CMDDisplayTextArea() {
        super();
        setEditable(false);
        setBackground(new Color(43, 43, 43));
        setForeground(new Color(187, 187, 187));

        // 自动换行
        setLineWrap(true);
        setWrapStyleWord(true);
        // todo 改用Document去操作内容
//        getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                resize();
//            }
//        });
    }

    // 改变展示区域文本
    public void updateContent(String content, boolean wrap) {
        setEditable(true);
        append(content);
        if (wrap) append("\n");
        setEditable(false);
        resizeByContentAndWidth(null);
    }

    // 自动根据文本改变大小
    private void resize1() {
        int lineCount = getText().split("\n").length;
        Font font = getFont();
        FontMetrics fontMetrics = getFontMetrics(font);
        int lineHeight = fontMetrics.getHeight();
        Insets insets = getInsets();
        int verticalInsets = insets.top + insets.bottom;
        int perfectHeight = lineCount * lineHeight + verticalInsets;
        int perfectWidth = getParent().getWidth();
        setPreferredSize(new Dimension(perfectWidth, perfectHeight));
    }

    private void resizeByContentAndWidth(Integer width) {
        if (width == null) {
            width = getWidth();
        }
        String[] textArray = getText().split("\n");
        Font font = getFont();
        FontMetrics fontMetrics = getFontMetrics(font);

        // 单行高度，insets
        int lineHeight = fontMetrics.getHeight();
        Insets insets = getInsets();
        int verticalInsets = insets.top + insets.bottom;

        int lineCount = 0;
        for (int i = 0; i < textArray.length; i++) {
            int needWidth = SwingUtilities.computeStringWidth(fontMetrics, textArray[i]);
            int add = needWidth % width > 0 ? 1 : 0;
            lineCount += needWidth / width + add;
        }

//        setPreferredSize(new Dimension(width, lineCount * lineHeight + verticalInsets));
        setSize(new Dimension(width, lineCount * lineHeight + verticalInsets));
        repaint();
    }


    public void resizeByParentChange(int width) {
        resizeByContentAndWidth(width);
    }

    public static void main(String[] args) throws InterruptedException {
        // 注意：测试的时候把width改一下
        CMDDisplayTextArea component = new CMDDisplayTextArea();
        TestComponentUtil.wrapComponent(component);
        Thread.sleep(1000);
        component.updateContent("123", true);
        System.out.println(component.getSize().toString());
        component.resize(null);
        System.out.println(component.getPreferredSize().toString());
        Thread.sleep(3000);
        component.updateContent("12121212\ndkfjkdfjdf\ndfdf23232323\n231313133" + component.getPreferredSize().toString(), true);
        System.out.println(component.getPreferredSize().toString());
    }
}
