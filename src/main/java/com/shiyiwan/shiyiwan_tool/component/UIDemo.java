package com.shiyiwan.shiyiwan_tool.component;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.shiyiwan.shiyiwan_tool.component.menu.MenuBar;
import com.shiyiwan.shiyiwan_tool.component.settingPanel.SettingDialog;
import com.shiyiwan.shiyiwan_tool.component.settingPanel.TestDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;

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

        Rectangle settingDialogBounds = new Rectangle(0, 0, 800, 600);

        JFrame frame = new JFrame("UIDemo");
        frame.setLayout(new BorderLayout());

        frame.setBounds(1150, 80, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setUI(new BasicSplitPaneUI());
        splitPane.setBounds(0, 0, 800, 600);


        JPanel leftJPanel = new JPanel();
        leftJPanel.setBackground(new Color(60, 63, 65));
        JPanel rightJPanel = new JPanel();
        rightJPanel.setBackground(new Color(43, 43, 43));
        splitPane.setLeftComponent(leftJPanel);
        splitPane.setRightComponent(rightJPanel);

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




        TestDialog testDialog = new TestDialog(settingDialogBounds);
        testDialog.setAlwaysOnTop(false);


        MenuBar menuBar = new MenuBar();
        menuBar.settingMenu.addActionListener(e -> {
            settingDialog.setVisible(true);
        });

        menuBar.click.addActionListener(e -> {
            testDialog.setVisible(true);
        });


        // frame.add
        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(splitPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UIDemo();
    }


}
