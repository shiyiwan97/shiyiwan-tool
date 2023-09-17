package com.shiyiwan.shiyiwan_tool.component;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.shiyiwan.shiyiwan_tool.component.bottomBar.BottomBarPanel;
import com.shiyiwan.shiyiwan_tool.component.menuBar.MyMenuBar;
import com.shiyiwan.shiyiwan_tool.component.popMenu.WorkSpacePopupMenu;
import com.shiyiwan.shiyiwan_tool.entity.ApplicationContainer;
import com.shiyiwan.shiyiwan_tool.component.settingDialog.SettingDialog;
import com.shiyiwan.shiyiwan_tool.component.settingDialog.TestDialog;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.*;

public class UIDemo {

    static {
        try {
            FlatDarculaLaf.setup();
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public UIDemo() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        Rectangle settingDialogBounds = new Rectangle(0, 0, 800, 600);

        JFrame frame = new JFrame("UIDemo");
        frame.setLayout(new GridBagLayout());

        frame.setBounds(1150, 80, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 工作区
//        JSplitPane splitPane = new JSplitPane();
//        splitPane.setUI(new BasicSplitPaneUI());
////        splitPane.setBounds(0, 0, 800, 600);
//
//        // 工作区左侧面板
//        JPanel leftJPanel = new JPanel();
//        leftJPanel.setBackground(new Color(60, 63, 65));
////        leftJPanel.setBackground(Color.BLUE);
//        leftJPanel.setBorder(BorderConst.RIGHT_MATTE);
//
//        JPanel rightJPanel = new JPanel();
////        rightJPanel.setBackground(new Color(43, 43, 43));
//        rightJPanel.setBackground(Color.white);
//        rightJPanel.setMinimumSize(new Dimension(0,0));
//        splitPane.setLeftComponent(leftJPanel);
//        splitPane.setRightComponent(rightJPanel);
        WorkSpaceSplitPane splitPane = new WorkSpaceSplitPane();

        SettingDialog settingDialog = new SettingDialog(frame,settingDialogBounds);
        settingDialog.setAlwaysOnTop(false);

        // esc关闭面板
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                settingDialog.setVisible(false);
            }
        };
        settingDialog.getRootPane().registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

        // 退出时确认的弹框
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showOptionDialog(frame,"Confirm Exit\nAre you sure you want to exit",null,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            }
        });

        TestDialog testDialog = new TestDialog(settingDialogBounds);
        testDialog.setAlwaysOnTop(false);


        MyMenuBar myMenuBar = new MyMenuBar();
        myMenuBar.settingMenu.addActionListener(e -> {
            settingDialog.setVisible(true);
        });

        myMenuBar.click.addActionListener(e -> {
            testDialog.setVisible(true);
        });

        // 底部状态栏
        BottomBarPanel bottomBarPanel = new BottomBarPanel();

        ApplicationContainer.root = frame;
        // frame.add
        frame.add(myMenuBar, gbc);
        gbc.gridy = 2;
        frame.add(bottomBarPanel, gbc);
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(splitPane, gbc);
        frame.setVisible(true);

        // ui检视，展示所有组件大小，背景颜色等
        new Thread(() ->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public static void main(String[] args) {
        new UIDemo();
    }


}
