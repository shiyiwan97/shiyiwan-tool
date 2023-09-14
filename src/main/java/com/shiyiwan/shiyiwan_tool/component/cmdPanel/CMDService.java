package com.shiyiwan.shiyiwan_tool.component.cmdPanel;

import java.io.*;

/**
 * 命令行Service
 */
public class CMDService {
    private final Process cmdProcess;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    private final InputStream errorStream;

    private ReadResultWorker readResultWorker;

    private ReadErrorWorker readErrorWorker;

    private final CMDDisplayTextArea cmdDisplayTextArea;

    private final CMDInputTextArea cmdInputTextArea;

    public CMDService(String workDirectory, CMDDisplayTextArea cmdDisplayTextArea, CMDInputTextArea cmdInputTextArea) {
        this.cmdDisplayTextArea = cmdDisplayTextArea;
        this.cmdInputTextArea = cmdInputTextArea;
        try {
            cmdProcess = Runtime.getRuntime().exec("cmd", null, new File(workDirectory));
            inputStream = cmdProcess.getInputStream();
            outputStream = cmdProcess.getOutputStream();
            errorStream = cmdProcess.getErrorStream();
        } catch (IOException e) {
            throw new RuntimeException("无法启动cmd\n" + e.getStackTrace());
        }
    }

    public void sendCmd(String prompt, String cmd) {
        try {
            cmdDisplayTextArea.updateContent(prompt, false);
            outputStream.write((cmd + "\n").getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("无法发送命令\n" + e.getStackTrace());
        }
    }

    public void startWorkers() {
        readResultWorker = new ReadResultWorker(cmdInputTextArea, cmdDisplayTextArea, inputStream);
        readErrorWorker = new ReadErrorWorker(cmdInputTextArea, cmdDisplayTextArea, errorStream);
        readResultWorker.execute();
        readErrorWorker.execute();
    }

    public void stopWorkers() {
        cmdProcess.destroy();
        readResultWorker.setWork(false);
        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("无法关闭输入输出流\n" + e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        CMDService cmdService = new CMDService("C:\\Users\\shiyiwan", null, null);
        cmdService.sendCmd("prompt", "dir");
    }
}
