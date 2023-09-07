package com.shiyiwan.shiyiwan_tool.component.settingDialog;

import com.shiyiwan.shiyiwan_tool.component.wrapper.JPanelWrapper;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class TestDialog extends JDialog {

    public TestDialog(Rectangle bounds) {
        setModal(true);
        setLayout(null);
        setBounds(bounds);


        this.add(new JLabel("Settings"));

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.red);
        jPanel.setBounds(0, 0, 200, 500);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setLayout(null);
        jScrollPane.getViewport().setBackground(Color.yellow);
        jScrollPane.add(getMockTree());
        jScrollPane.setBounds(0, 0, 200, 500);
        jPanel.add(getMockTree());


        this.add(jPanel);
    }

    class SettingPanel extends JSplitPane {
        public SettingPanel(Rectangle bounds) {
            this.setBounds(bounds);
            this.setUI(new BasicSplitPaneUI());

            this.setDividerLocation(200);

//            LeftScrollPane leftScrollPane = new LeftScrollPane();
//            leftScrollPane.setLayout(null);
//            leftScrollPane.add(new SettingTree());
//            JPanel leftJPanel = leftScrollPane.wrappedWithJPanel(new Color(62, 67, 76));
//            this.setLeftComponent(leftJPanel);

            JPanel jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBackground(Color.red);
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setLayout(null);
            jScrollPane.getViewport().setBackground(Color.yellow);
            jScrollPane.add(getMockTree());
            jScrollPane.setBounds(0, 0, 200, 500);
            jPanel.add(getMockTree());
            this.setLeftComponent(jScrollPane);


            JPanel rightJPanel = new RightScrollPane().wrappedWithJPanel(new Color(60, 63, 65));
            this.setRightComponent(rightJPanel);

        }
    }

    private void changeSettingGroup() {

    }


    class LeftScrollPane extends JScrollPane implements JPanelWrapper {
        @Override
        public Component getInnerComponent() {
            this.add(new SettingTree());
            return this;
        }
    }

    class RightScrollPane extends JScrollPane implements JPanelWrapper {
        @Override
        public Component getInnerComponent() {
            return this;
        }
    }

    class SettingTree extends JTree {
        public SettingTree() {
            DefaultMutableTreeNode levelOneNode = new DefaultMutableTreeNode("Appearance & Behavior");
            DefaultMutableTreeNode levelTwoNode1 = new DefaultMutableTreeNode("Appearance");
            DefaultMutableTreeNode levelTwoNode2 = new DefaultMutableTreeNode("New UI");
            DefaultMutableTreeNode levelTwoNode3 = new DefaultMutableTreeNode("System Settings");
            DefaultMutableTreeNode levelThreeNode31 = new DefaultMutableTreeNode("Passwords");
            DefaultMutableTreeNode levelThreeNode32 = new DefaultMutableTreeNode("HTTP Proxy");

            levelTwoNode3.add(levelThreeNode31);
            levelTwoNode3.add(levelThreeNode32);
            levelOneNode.add(levelTwoNode1);
            levelOneNode.add(levelTwoNode2);
            levelOneNode.add(levelTwoNode3);
            this.setModel(new DefaultTreeModel(levelOneNode));
        }
    }

    abstract class SettingGroup extends JPanel {
        public SettingGroup(Rectangle bounds) {
            setBounds(bounds);
        }

    }

    public JTree getMockTree() {
        JTree jTree = new JTree();
        DefaultMutableTreeNode levelOneNode = new DefaultMutableTreeNode("Appearance & Behavior");
        DefaultMutableTreeNode levelTwoNode1 = new DefaultMutableTreeNode("Appearance");
        DefaultMutableTreeNode levelTwoNode2 = new DefaultMutableTreeNode("New UI");
        DefaultMutableTreeNode levelTwoNode3 = new DefaultMutableTreeNode("System Settings");
        DefaultMutableTreeNode levelThreeNode31 = new DefaultMutableTreeNode("Passwords");
        DefaultMutableTreeNode levelThreeNode32 = new DefaultMutableTreeNode("HTTP Proxy");

        levelTwoNode3.add(levelThreeNode31);
        levelTwoNode3.add(levelThreeNode32);
        levelOneNode.add(levelTwoNode1);
        levelOneNode.add(levelTwoNode2);
        levelOneNode.add(levelTwoNode3);
        jTree.setModel(new DefaultTreeModel(levelOneNode));
        jTree.setBounds(0, 0, 200, 500);
        return jTree;
    }
}
