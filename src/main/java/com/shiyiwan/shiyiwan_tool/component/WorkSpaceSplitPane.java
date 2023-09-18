package com.shiyiwan.shiyiwan_tool.component;

import com.shiyiwan.shiyiwan_tool.component.cmdPanel.CMDPanel1;
import com.shiyiwan.shiyiwan_tool.component.popMenu.WorkSpacePopupMenu;
import com.shiyiwan.shiyiwan_tool.component.quickStartPanel.QuickStartPanel;
import com.shiyiwan.shiyiwan_tool.enums.BorderConst;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 工作区分隔面板（左侧：列表；右侧：cmd界面）
 */
public class WorkSpaceSplitPane extends JSplitPane {

    private final JPanel rightPanel;

    private final JScrollPane jScrollPane;

    public WorkSpaceSplitPane() {
        super();
        // 设置分割线样式（不设置会有三个点）
        setUI(new BasicSplitPaneUI());
        // 左侧工作区面板
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(60, 63, 65));
        leftPanel.add(new QuickStartPanel());

        rightPanel = getRight();

        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(rightPanel);

        // 分割线变化实时更新布局
        continuousLayout = true;

        // 监听尺寸变化
//        rightPanel.addComponentListener(new ResizeListener());
        jScrollPane.addComponentListener(new ResizeListener());

        // 添加到自身中
        this.setLeftComponent(leftPanel);
//        this.setRightComponent(rightPanel);

        this.setRightComponent(jScrollPane);

    }

    // 右侧工作区面板
    private static JPanel getRight() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setMinimumSize(new Dimension(0, 0));
        rightPanel.setLayout(new BorderLayout());
        // 右键菜单
        WorkSpacePopupMenu workSpacePopupMenu = new WorkSpacePopupMenu(rightPanel);
        rightPanel.add(workSpacePopupMenu);
        rightPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    workSpacePopupMenu.show(rightPanel, e.getX(), e.getY());
                }
            }
        });
        return rightPanel;
    }

    class ResizeListener extends ComponentAdapter {

        public void componentResized(ComponentEvent e) {
            Component[] components = rightPanel.getComponents();
            int height = jScrollPane.getHeight();
            if (components.length != 0) {
                CMDPanel1 component = (CMDPanel1) components[0];
                System.out.println("s" + jScrollPane.getWidth());
                component.resizeByParentChange(jScrollPane.getWidth());
                height = component.getNeedSize().height;
                System.out.println("c" + component.getSize());
                System.out.println("display"+ component.getCmdDisplayTextArea().getSize());
                System.out.println("input"+ component.getCmdInputTextArea().getSize());
                System.out.println("input2"+ component.getCmdInputTextArea().getPreferredSize());
            }
            jScrollPane.getViewport().setSize(jScrollPane.getWidth(), height);
            System.out.println("v" + jScrollPane.getViewport().getSize());
        }
    }
}
