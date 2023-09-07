package com.shiyiwan.shiyiwan_tool.entity;

import com.shiyiwan.shiyiwan_tool.service.IService;
import lombok.Data;
import lombok.Getter;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ApplicationContainer {

    public static List<SettingGroupEntity> settingGroupEntityList;

    public static JFrame root;

    @Getter
    public static Map<String, IService> serviceMap = new HashMap<>();

//    public static List<SettingGroupEntity> getSettingGroupEntityList() {
//        return settingGroupEntityList;
//    }
//
//    public static void setSettingGroupEntityList(List<SettingGroupEntity> settingGroupEntityList) {
//        ApplicationContainer.settingGroupEntityList = settingGroupEntityList;
//    }
//
//    public static JFrame getRoot() {
//        return root;
//    }
//
//    public static void setRoot(JFrame root) {
//        ApplicationContainer.root = root;
//    }
//
//    public static Map<String, IService> getServiceMap() {
//        return serviceMap;
//    }
//
//    public static void setServiceMap(Map<String, IService> serviceMap) {
//        ApplicationContainer.serviceMap = serviceMap;
//    }
}
