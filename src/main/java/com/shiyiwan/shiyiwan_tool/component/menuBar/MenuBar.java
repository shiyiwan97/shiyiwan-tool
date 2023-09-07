package com.shiyiwan.shiyiwan_tool.component.menuBar;

import com.shiyiwan.shiyiwan_tool.enums.BorderConst;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {

    JMenu fileMenu;
    JMenu editMenu;

    JMenu viewMenu;
    JMenu helpMenu;

    public JMenuItem settingMenu;

    public JMenuItem click;

    public MenuBar() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");
        settingMenu = new JMenuItem("Settings...");
        settingMenu.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
        click = new JMenuItem("Click");
        click.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        add(fileMenu);
        add(editMenu);
        add(viewMenu);
        add(helpMenu);
        fileMenu.add(settingMenu);
        fileMenu.add(click);
        setBackground(new Color(60, 63, 65));
        setBorder(BorderConst.TOP_AND_BOTTOM_ONE_MATTE);
    }


}
