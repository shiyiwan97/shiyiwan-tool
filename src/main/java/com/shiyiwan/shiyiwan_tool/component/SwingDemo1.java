package com.shiyiwan.shiyiwan_tool.component;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SwingDemo1 {

    static {
        try {
            FlatDarculaLaf.setup();
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("test jframe");
        jFrame.setBounds(100, 100, 1000, 600);
        jFrame.setLayout(null);

        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setMaximum(1000);
        jProgressBar.setBounds(10, 10, 200, 20);
        jFrame.add(jProgressBar);

        JButton jButton = new JButton("开始");
        jButton.setBounds(10, 40, 100, 20);
        jFrame.add(jButton);

        jButton.addActionListener(e -> {
            new Thread(() -> {
                File file = new File("C:\\Users\\shiyiwan\\Downloads\\ideaIU-2023.2.1.exe");
                try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\shiyiwan\\Downloads\\ideaIU-2023.2.1.exe");
                     FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\shiyiwan\\Downloads\\ideaIU-2023.2.1.execopy")) {
                    long size = file.length();
                    long current = 0;
                    byte[] bytes = new byte[512];
                    int length;
                    while ((length = fileInputStream.read(bytes)) > 0) {
                        fileOutputStream.write(bytes, 0, length);
                        current += length;
                        jProgressBar.setValue((int) (current * 1000 / size));
                        jProgressBar.repaint();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("C:\\Users\\shiyiwan\\Downloads"));
        jFileChooser.setBounds(10, 70, 600, 300);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFrame.add(jFileChooser);

        JButton jButton1 = new JButton("打开");
        jButton1.setBounds(120, 40, 100, 20);
        jButton1.addActionListener(e -> {
            System.out.println("打开");
            jFileChooser.setVisible(true);
        });
        jFrame.add(jButton1);

        String[] filePath = {null};
        JTree jTree = new JTree();
        jTree.setBounds(10, 400, 600, 300);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Myroot");
        jFrame.add(jTree);


        jFileChooser.addActionListener(
                e -> {
                    System.out.println("选择");
                    System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
                    filePath[0] = jFileChooser.getSelectedFile().getAbsolutePath();
                    File file = new File(filePath[0]);
                    root.removeAllChildren();
                    createTree(root,file);
                    jTree.setModel(new javax.swing.tree.DefaultTreeModel(root));
                    jTree.repaint();
                });



        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void createTree(DefaultMutableTreeNode root, File file) {
        if(!file.isDirectory()){
            root.add(new DefaultMutableTreeNode(file.getName()));
        }else {
            DefaultMutableTreeNode sonNode = new DefaultMutableTreeNode(file.getName());

            File[] files = file.listFiles();
            for (File file1 : files) {
                createTree(sonNode,file1);
            }
            root.add(sonNode);
        }
    }

}
