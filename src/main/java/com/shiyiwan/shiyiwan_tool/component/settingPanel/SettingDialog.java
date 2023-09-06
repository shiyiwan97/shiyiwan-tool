package com.shiyiwan.shiyiwan_tool.component.settingPanel;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.shiyiwan.shiyiwan_tool.component.entity.ApplicationContainer;
import com.shiyiwan.shiyiwan_tool.component.entity.SettingGroupEntity;
import com.shiyiwan.shiyiwan_tool.component.mock.MockService;
import com.shiyiwan.shiyiwan_tool.component.service.MenuService;
import com.shiyiwan.shiyiwan_tool.component.wrapper.JPanelWrapper;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.util.List;

public class SettingDialog extends JDialog {

    public SettingDialog(Frame owner, Rectangle bounds) {
        super(owner, "Settings");
        setModal(true);
        setLayout(new BorderLayout());
        setBounds(bounds);
        add(new SettingPanel());

    }

    class SettingPanel extends JSplitPane {

        public SettingPanel() {
            setUI(new BasicSplitPaneUI());
            setDividerLocation(200);

            JScrollPane jScrollPane = new JScrollPane(getMockTree());
//            jScrollPane.setLayout(new BorderLayout());
//            jScrollPane.getViewport().setBackground(Color.yellow);
//            jScrollPane.add(getMockTree());
////            jScrollPane.setBounds(0, 0, 200, 500);

            List<SettingGroupEntity> settingGroupEntityList = MockService.mockMenuGroupEntityList();
            TreeModel treeModel = MenuService.generateMenuTreeModel2(settingGroupEntityList);
            JTree jTree = new JTree(treeModel);

            this.setLeftComponent(jTree);


            RightScrollPane rightScrollPane = new RightScrollPane();
            JPanel rightJPanel = rightScrollPane.wrappedWithJPanel(new Color(60, 63, 65));
            rightJPanel.setLayout(new FlowLayout());
            rightJPanel.add(getComboBox());
            this.setRightComponent(rightJPanel);

        }

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
            jScrollPane.setBounds(0, 0, 200, 500);

            // 老方式
            jPanel.add(getMockTree());

            List<SettingGroupEntity> settingGroupEntityList = MockService.mockMenuGroupEntityList();
            TreeModel treeModel = MenuService.generateMenuTreeModel(settingGroupEntityList);
            JTree jTree = new JTree(treeModel);
            jScrollPane.add(jTree);

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
//            DefaultMutableTreeNode root = new DefaultMutableTreeNode("23333");
//            DefaultMutableTreeNode levelOneNode = new DefaultMutableTreeNode("Appearance & Behavior");
//            DefaultMutableTreeNode levelTwoNode1 = new DefaultMutableTreeNode("Appearance");
//            DefaultMutableTreeNode levelTwoNode2 = new DefaultMutableTreeNode("New UI");
//            DefaultMutableTreeNode levelTwoNode3 = new DefaultMutableTreeNode("System Settings");
//            DefaultMutableTreeNode levelThreeNode31 = new DefaultMutableTreeNode("Passwords");
//            DefaultMutableTreeNode levelThreeNode32 = new DefaultMutableTreeNode("HTTP Proxy");
//
//            levelTwoNode3.add(levelThreeNode31);
//            levelTwoNode3.add(levelThreeNode32);
//            levelOneNode.add(levelTwoNode1);
//            levelOneNode.add(levelTwoNode2);
//            levelOneNode.add(levelTwoNode3);
//            root.add(levelOneNode);
            List<SettingGroupEntity> settingGroupEntityList = MockService.mockMenuGroupEntityList();
            TreeModel root = MenuService.generateMenuTreeModel(settingGroupEntityList);
//            JTree jTree = new JTree(treeModel);
            this.setModel(root);
        }
    }

    abstract class SettingGroup extends JPanel {
        public SettingGroup(Rectangle bounds) {
            setBounds(bounds);
        }

    }

    public JTree getMockTree() {
        JTree jTree = new JTree();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
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
        root.add(levelOneNode);
        jTree.setModel(new DefaultTreeModel(root));
        return jTree;
    }

    public JComboBox getComboBox() {
        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.addItem("FlatDarculaLaf");
        jComboBox.addItem("FlatIntelliJLaf");
        jComboBox.addItem("FlatLightLaf");
        jComboBox.addItem("FlatDarkLaf");

        jComboBox.addActionListener(e -> {
            JComboBox<String> jComboBox1 = (JComboBox<String>) e.getSource();
//            String item = (String) jComboBox1.getSelectedItem();
            int index = jComboBox1.getSelectedIndex();
            changeLaf(index);
        });

        return jComboBox;
    }

    public void changeLaf(int index) {
        FlatDarculaLaf.setup();
        FlatIntelliJLaf.setup();
        FlatLightLaf.setup();
        FlatDarkLaf.setup();
        try {
            switch (index) {
                case 0:
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    break;
                case 1:
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    break;
                case 2:
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                case 3:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
            }
            // todo 皮肤还得适配背景色
            SwingUtilities.updateComponentTreeUI(ApplicationContainer.root);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }
    }
}



