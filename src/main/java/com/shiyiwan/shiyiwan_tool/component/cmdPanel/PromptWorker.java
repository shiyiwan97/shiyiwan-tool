package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * CMD提示区域
 */
public class PromptWorker extends SwingWorker<Object, Void> {

    private boolean work;

    private CMDInputTextArea cmdInputTextArea;

    public PromptWorker(CMDInputTextArea cmdInputTextArea) {
        super();
        this.cmdInputTextArea = cmdInputTextArea;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (work) {
            addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("cmd".equals(evt.getPropertyName())) {
                        cmdInputTextArea.setPrompt(evt.getNewValue().toString());
                    }
                }
            });
        }
        return null;
    }
}
