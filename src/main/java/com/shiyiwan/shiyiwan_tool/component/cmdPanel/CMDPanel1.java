package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class CMDPanel1 extends JPanel {

    private CMDService cmdService;

    public CMDPanel1() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(43, 43, 43));

        CMDDisplayTextArea cmdDisplayTextArea = new CMDDisplayTextArea();
        CMDInputTextArea cmdInputTextArea = new CMDInputTextArea(cmdDisplayTextArea, this);

        this.add(cmdDisplayTextArea);
        this.add(cmdInputTextArea);

        cmdService = new CMDService("C:\\Users\\shiyiwan",cmdDisplayTextArea,cmdInputTextArea);
        cmdInputTextArea.setCmdService(cmdService);
        // todo 暂时这样做，后面子组件初始化完成才让SwingWorker开始工作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cmdService.startWorkers();

    }

    public static void main(String[] args) throws InterruptedException {
        CMDPanel1 cmdPanel1 = new CMDPanel1();
        JScrollPane jScrollPane = new JScrollPane(cmdPanel1);
        JFrame jFrame = TestComponentUtil.wrapComponent(jScrollPane);
        jFrame.repaint();
//        Thread.sleep(1000);
//        ((CMDDisplayTextArea) cmdPanel1.getComponent(0)).repaint();
//        ((CMDInputTextArea)cmdPanel1.getComponent(1)).repaint();

        JViewport viewport = jScrollPane.getViewport();
        CMDPanel1 component = ((CMDPanel1) viewport.getComponent(0));
        component.getComponent(1).requestFocus();

    }

}
