package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PrintWorker extends SwingWorker<Object, Void> {

    private boolean work;
    private InputStream inputStream;
    private CMDDisplayTextArea cmdDisplayTextArea;

    public PrintWorker(CMDDisplayTextArea cmdDisplayTextArea, InputStream inputStream) {
        super();
        this.cmdDisplayTextArea = cmdDisplayTextArea;
        this.inputStream = inputStream;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (work) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String content;
            if ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
                cmdDisplayTextArea.append(content);
            }
        }
        return null;
    }
}
