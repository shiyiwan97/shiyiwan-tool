package com.shiyiwan.shiyiwan_tool.service;

import com.shiyiwan.shiyiwan_tool.entity.VMDataEntity;
import com.shiyiwan.shiyiwan_tool.util.NeedDelete;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MonitorService implements IService {

    public void startMonitorMemory(VMDataEntity[] vmDataEntityWrapper) {
        Runnable monitorTask = new Runnable() {
            @Override
            public void run() {
                VMDataEntity vmDataEntity = vmDataEntityWrapper[0];

                // 堆内存
                MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
                // 内存使用情况
                MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
                vmDataEntity.setUsedMemory((int) (heapMemoryUsage.getUsed() / 1024 / 1024));
                vmDataEntity.setAllocatedMemory((int) (heapMemoryUsage.getCommitted() / 1024 / 1024));
                vmDataEntity.setMaxMemory((int) (heapMemoryUsage.getMax() / 1024 / 1024));
            }
        };
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, r -> new Thread(monitorTask, "Memory Monitor Thread"));
        scheduler.scheduleAtFixedRate(monitorTask, 0, 1, TimeUnit.SECONDS);
    }


    @NeedDelete
    public static void main(String[] args) throws InterruptedException, IOException {


        new Thread(() -> {
            String result = "";
            for (int i = 0; i < 100000; i++) {
                result += "hello" + i;
            }
        }).start();

        while (true) {
            // 堆内存
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            // 内存使用情况
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

            long used = heapMemoryUsage.getUsed();
            long committed = heapMemoryUsage.getCommitted();
            long max = heapMemoryUsage.getMax();
            System.out.println("已使用内存：" + used / 1024 / 1024 + "MB");
            System.out.println("已分配内存：" + committed / 1024 / 1024 + "MB");
            System.out.println("最大内存：" + max / 1024 / 1024 + "MB");
            Thread.sleep(1000);
            // 随机数字转为字符串
            String s = new String(Math.random() + "");
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\shiyiwan\\Downloads\\ideaIU-2023.2.1.exe");
            byte[] bytes = new byte[1024 * 1024 * 10 * 100];
            fileInputStream.read(bytes);

            if (Math.random() > 0.5) {
                fileInputStream = null;
            }
        }
    }

}
