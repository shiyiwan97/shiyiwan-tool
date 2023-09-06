package com.shiyiwan.shiyiwan_tool.component.service;

import com.shiyiwan.shiyiwan_tool.component.entity.ApplicationContainer;

public class ContainerService {

    private static ApplicationContainer applicationContainer;

    // 初始化容器
    public static void initContainer() {
        applicationContainer = new ApplicationContainer();
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
