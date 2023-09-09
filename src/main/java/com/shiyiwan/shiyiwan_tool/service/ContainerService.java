package com.shiyiwan.shiyiwan_tool.service;

import com.shiyiwan.shiyiwan_tool.entity.ApplicationContainer;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContainerService {

    private static ApplicationContainer applicationContainer;

    // 初始化容器
    public static void initContainer() {
//        applicationContainer = new ApplicationContainer();
        registerFont();
    }

    // 初始化IService并放入容器
    public static void registerService(){
        ApplicationContainer.getServiceMap().put("MonitorService", new MonitorService());
    }

    // 初始化字体
    private static void registerFont(){
        // todo 扫描字体文件夹
        String fontPath = "src\\main\\resources\\font\\Inter-Regular.otf";
        File fontFile = new File(fontPath);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            ApplicationContainer.getFontMap().put("Inter Regular", font);
        } catch (FontFormatException e) {
            throw new RuntimeException("初始化字体失败");
        } catch (IOException e) {
            throw new RuntimeException("io异常");
        }

    }

    // 初始化各个Bean
    private void initBeans() {

    }

    // 初始化菜单Bean
    private void initMenuBeans() {

    }

    private void mockMenuBeans() {

    }

}
