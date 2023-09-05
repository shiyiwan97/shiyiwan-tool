package com.shiyiwan.shiyiwan_tool.component.wrapper;

import javax.swing.*;
import java.awt.*;

public interface JPanelWrapper {

    JPanel wrapper = new JPanel();

    Component[] innerComponentContainer = {null};

    Component getInnerComponent();
    default JPanel wrappedWithJPanel() {
        wrapper.setLayout(null);
        Component innerComponent = getInnerComponent();
        setInnerComponent(innerComponent);
        wrapper.add(innerComponent);
        return wrapper;
    }

    default JPanel wrappedWithJPanel(Color color) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(color);
        Component innerComponent = getInnerComponent();
        jPanel.add(innerComponent);
        return jPanel;
    }

    default JPanel changeInnerComponent(Component newInnerComponent) {
        wrapper.removeAll();
        wrapper.add(newInnerComponent);
        setInnerComponent(newInnerComponent);
        return wrapper;
    }

    default void setInnerComponent(Component newInnerComponent) {
        innerComponentContainer[0] = newInnerComponent;
    }

    default Component getInnerComponentContainer() {
        return innerComponentContainer[0];
    }
}
