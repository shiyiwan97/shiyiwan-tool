package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import lombok.Setter;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * CMD提示区域
 */
public class ReadErrorWorker extends SwingWorker<String, String> {

    @Setter
    private boolean work = true;

    private final CMDInputTextArea cmdInputTextArea;

    private final CMDDisplayTextArea cmdDisplayTextArea;

    private final InputStream errorStream;

    public ReadErrorWorker(CMDInputTextArea cmdInputTextArea, CMDDisplayTextArea cmdDisplayTextArea, InputStream errorStream) {
        super();
        this.cmdInputTextArea = cmdInputTextArea;
        this.errorStream = errorStream;
        this.cmdDisplayTextArea = cmdDisplayTextArea;

    }

    @Override
    protected String doInBackground() {
        while (work) {
            readResult();
        }
        return null;
    }

    public void readResult() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(errorStream, "GBK");
            char[] buf = new char[1024];
            int size;
            while ((size = inputStreamReader.read(buf)) != -1) {
                String result = new String(buf, 0, size);
                String[] resultArray = result.split("\n");
                String[] appendBlank = Arrays.copyOf(resultArray, resultArray.length + 1);
                appendBlank[resultArray.length] = " \r";
                System.out.println("error: " + result);
                publish(appendBlank);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取cmd执行结果失败");
        }
    }

    @Override
    protected void process(List<String> chunks) {
        for (String result : chunks) {
            cmdDisplayTextArea.updateContent(result, true);
        }
    }

}
