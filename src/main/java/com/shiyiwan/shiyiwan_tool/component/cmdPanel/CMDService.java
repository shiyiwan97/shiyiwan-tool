package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import java.io.*;

/**
 * 命令行Service
 */
public class CMDService {
    Process cmdProcess;
    InputStream inputStream;
    OutputStream outputStream;

    public CMDService() {
        try {
            cmdProcess = Runtime.getRuntime().exec("cmd");
            inputStream = cmdProcess.getInputStream();
            outputStream = cmdProcess.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException("无法启动cmd\n" + e.getStackTrace());
        }
    }

    public void sendCmd(String cmd) {
        try {
            outputStream.write((cmd + "\n").getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("无法发送命令\n" + e.getStackTrace());
        }
    }

    public void startWorkers(CMDDisplayTextArea cmdDisplayTextArea, CMDInputTextArea cmdInputTextArea) {
        new PrintWorker(cmdDisplayTextArea, inputStream).execute();
        new PromptWorker(cmdInputTextArea).execute();

        new Thread(() -> {
            while (true) {
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
                    String content;
                    if ((content = bufferedReader.readLine()) != null) {
                        System.out.println(content);
                        cmdDisplayTextArea.updateContent(content);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        CMDService cmdService = new CMDService();
        cmdService.sendCmd("dir");
    }
}
