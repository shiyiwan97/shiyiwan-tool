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
        setForeground(Color.white);
        setPreferredSize(new Dimension(0, 0));
    }

    public void updateContent(String content) {
        setEditable(true);
        setText(getText() + "\n" + content);
        setEditable(false);
        resize();
    }

    private void resize() {
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

    public static void main(String[] args) throws InterruptedException {
        // 注意：测试的时候把width改一下
        CMDDisplayTextArea component = new CMDDisplayTextArea();
        TestComponentUtil.wrapComponent(component);
        Thread.sleep(1000);
        component.updateContent("123");
        System.out.println(component.getSize().toString());
        component.resize();
        System.out.println(component.getPreferredSize().toString());
        Thread.sleep(3000);
        component.updateContent("12121212\ndkfjkdfjdf\ndfdf23232323\n231313133" + component.getPreferredSize().toString());
        System.out.println(component.getPreferredSize().toString());
    }

}
