package com.shiyiwan.shiyiwan_tool.component.bottomBar;

import com.shiyiwan.shiyiwan_tool.entity.VMDataEntity;
import com.shiyiwan.shiyiwan_tool.service.MonitorService;
import com.shiyiwan.shiyiwan_tool.util.CalculateLocationUtil;
import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;

import javax.swing.*;
import java.awt.*;

public class MemoryMonitorPanel extends JComponent {

    private VMDataEntity vmDataEntity;

    private double usedMemory;

    private double allocatedMemory;

    private double maxMemory;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        usedMemory = vmDataEntity.getUsedMemory();
        allocatedMemory = vmDataEntity.getAllocatedMemory();
//        double maxMemory = vmDataEntity.getMaxMemory();
        maxMemory = 550;

        int height = getHeight();
        int usedMemoryWidth = (int) (usedMemory / maxMemory * getWidth());
        int allocatedMemoryWidth = (int) (allocatedMemory / maxMemory * getWidth());
        int maxMemoryWidth = getWidth();

        g.setColor(new Color(110, 110, 110));
        g.fillRect(0, 0, usedMemoryWidth, height);
        g.setColor(new Color(90, 90, 90));
        g.fillRect(usedMemoryWidth, 0, allocatedMemoryWidth - usedMemoryWidth, height);
        g.setColor(new Color(60, 63, 65));
        g.fillRect(allocatedMemoryWidth, 0, maxMemoryWidth - allocatedMemoryWidth, height);

        // todo 设置自定义显示 {usedMemory} 自定义显示 {maxMemory} => 1657 自定义显示 4078 表情支持 优先级非常低
        String displayText = (int) usedMemory + " of " + (int) maxMemory + "M";

//        g.setFont(ApplicationContainer.getFont("Inter Regular", 5f));
        g.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(displayText);
        int textHeight = fontMetrics.getHeight();
        Point point = CalculateLocationUtil.textCenterLocation(textWidth, textHeight, getWidth(), getHeight());

        g.setColor(new Color(187, 187, 187));
//        g.drawString(displayText, 15, 20);
        g.drawString(displayText, point.x, 18);

    }

    public MemoryMonitorPanel() {
        super();
        vmDataEntity = new VMDataEntity();
        VMDataEntity[] vMDataEntityWrapper = new VMDataEntity[1];
        vMDataEntityWrapper[0] = vmDataEntity;
        // todo 考虑是否放入容器
        new MonitorService().startMonitorMemory(vMDataEntityWrapper);
        this.setPreferredSize(new Dimension(115, 0));

        this.setToolTipText("Max Heap Size:" + maxMemory + "\nAllocated:    " + allocatedMemory +
                "\nUsed:         " + usedMemory);
    }


    public Component getComponent() {
        throw new RuntimeException("MemoryMonitorPanel类请使用getPanel()");
    }


    public JPanel getComplexPanel() {
        JProgressBar allocatedProgressBar = new JProgressBar();
        JProgressBar usedProgressBar = new JProgressBar();
        allocatedProgressBar.setStringPainted(true);
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(1, 2));
        panelContainer.add(allocatedProgressBar);
        panelContainer.add(usedProgressBar);
        panelContainer.setSize(120, 30);
        return panelContainer;
    }

    public static void main(String[] args) {
        TestComponentUtil.wrapComponent(new MemoryMonitorPanel());
    }
}
