import com.shiyiwan.shiyiwan_tool.component.bottomBar.MemoryMonitorPanel;
import com.shiyiwan.shiyiwan_tool.service.ContainerService;
import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class TestAllComponent {

    @Test
    public void testMemoryMonitorPanel(){
        testComponent(new MemoryMonitorPanel());
    }

    private void testComponent(Component component){
        component.setSize(120,40);
        component.setBackground(Color.red);
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.red);
        jPanel.setSize(100,100);
        TestComponentUtil.wrapComponent(component);
    }

    @Before
    public void init(){
        ContainerService.initContainer();
    }

    @After
    public void sleep(){
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void temp() {

        JFrame frame = new JFrame("JFrame Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建顶部的面板
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 50)); // 设置顶部面板高度
        topPanel.setBackground(Color.YELLOW);

        // 创建底部的面板
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 50)); // 设置底部面板高度
        bottomPanel.setBackground(Color.GREEN);

        // 创建中间的面板（填充剩余空间）
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLUE);

        // 设置 JFrame 的布局管理器为 GridBagLayout
        frame.setLayout(new GridBagLayout());

        // 创建 GridBagConstraints 对象
        GridBagConstraints gbc = new GridBagConstraints();

        // 设置约束条件
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        // 将顶部面板添加到 JFrame
        frame.add(topPanel, gbc);

        gbc.gridy = 2;
        frame.add(bottomPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(centerPanel, gbc);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

}
