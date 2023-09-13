package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import lombok.Setter;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * CMD提示区域
 */
public class ReadResultWorker extends SwingWorker<String, String> {

    @Setter
    private boolean work = true;

    private CMDInputTextArea cmdInputTextArea;

    private CMDDisplayTextArea cmdDisplayTextArea;

    private InputStream inputStream;

    boolean first = true;

    public ReadResultWorker(CMDInputTextArea cmdInputTextArea, CMDDisplayTextArea cmdDisplayTextArea, InputStream inputStream) {
        super();
        this.cmdInputTextArea = cmdInputTextArea;
        this.inputStream = inputStream;
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
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
            char[] buf = new char[1024];
            int size;
            while ((size = inputStreamReader.read(buf)) != -1) {
                String result = new String(buf, 0, size);
                System.out.println("get =========== " + result);
                String[] resultArray = result.split("\n");
                publish(resultArray);
                if (first) {
                    first = false;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("读取cmd执行结果失败");
        }
    }

    @Override
    protected void process(List<String> chunks) {
        for (String result : chunks) {
            if (isWorkDirectory(result)) {
                cmdInputTextArea.setPrompt(result);
            } else {
                cmdDisplayTextArea.updateContent(result,true);
            }
        }
    }

    private boolean isWorkDirectory(String text) {
        return Character.isLetter(text.charAt(0)) && text.charAt(1) == ':';
    }


}
