package com.shiyiwan.shiyiwan_tool.interfaces;

import javax.swing.*;
import java.awt.*;

public interface ComplexComponentGroup {

    Component getComponent();

    default JPanel getPanel(){
        throw new RuntimeException("未实现getPanel方法！");
    };

}
