import com.shiyiwan.shiyiwan_tool.component.MemoryMonitorPanel;
import com.shiyiwan.shiyiwan_tool.util.TestComponentUtil;
import org.junit.After;
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
        TestComponentUtil.wrapComponent(jPanel);
    }

    @After
    public void sleep(){
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
