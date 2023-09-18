package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import com.shiyiwan.shiyiwan_tool.caogao.D;
import com.shiyiwan.shiyiwan_tool.entity.ApplicationContainer;
import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class CMDPanel1 extends JPanel {

    private CMDService cmdService;

    @Getter
    private CMDDisplayTextArea cmdDisplayTextArea;

    @Getter // todo 临时添加
    private CMDInputTextArea cmdInputTextArea;

    public CMDPanel1() {
        super();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cmdDisplayTextArea = new CMDDisplayTextArea();
        cmdInputTextArea = new CMDInputTextArea(cmdDisplayTextArea, this);

        //test

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setPreferredSize(new Dimension(0,200));


        JPanel redPanel1 = new JPanel();
        redPanel1.setBackground(Color.blue);
        redPanel1.setPreferredSize(new Dimension(0,300));

        //test


        gbc.gridy = 0;
        this.add(cmdDisplayTextArea,gbc);

        gbc.gridy = 1;
        this.add(cmdInputTextArea,gbc);

        cmdService = new CMDService("C:\\Users\\shiyiwan",cmdDisplayTextArea,cmdInputTextArea);
        cmdInputTextArea.setCmdService(cmdService);

        ApplicationContainer.getCmdServerMap().put("1", cmdService);
        // todo 暂时这样做，后面子组件初始化完成才让SwingWorker开始工作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cmdService.startWorkers();

        // 点击面板获得焦点
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cmdInputTextArea.requestFocus();
            }
        });
    }

    public void resizeByParentChange(int width){
        cmdDisplayTextArea.resizeByParentChange(width - 30);
//        cmdInputTextArea.resizeByParentChange(width - 30);
//        this.setSize(new Dimension(width, cmdDisplayTextArea.getHeight() + cmdInputTextArea.getHeight()));
        this.setPreferredSize(new Dimension(width, cmdDisplayTextArea.getHeight() + cmdInputTextArea.getHeight()));
    }

    public Dimension getNeedSize(){
        return new Dimension(cmdDisplayTextArea.getWidth(), cmdDisplayTextArea.getHeight() + cmdInputTextArea.getHeight());
    }

    public static void main(String[] args) throws InterruptedException {
        CMDPanel1 cmdPanel1 = new CMDPanel1();
        JScrollPane jScrollPane = new JScrollPane(cmdPanel1);
        JFrame jFrame = TestComponentUtil.wrapComponent(jScrollPane);
        jFrame.repaint();
        JViewport viewport = jScrollPane.getViewport();
        CMDPanel1 component = ((CMDPanel1) viewport.getComponent(0));
        component.getComponent(1).requestFocus();

    }

}
