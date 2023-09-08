package com.shiyiwan.shiyiwan_tool.entity;

import com.shiyiwan.shiyiwan_tool.service.IService;
import lombok.Data;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class ApplicationContainer {

    public static List<SettingGroupEntity> settingGroupEntityList;

    public static JFrame root;

    @Getter
    public static Map<String, IService> serviceMap = new HashMap<>();

    @Getter
    public static Map<String, Font> fontMap = new HashMap<>();

    public static Font getFont(String fontName){
        return Optional.ofNullable(fontMap.get(fontName)).orElseThrow(() -> new RuntimeException("字体不存在"));
    }

    public static Font getFont(String fontName,float fontSize){
        Font font = Optional.ofNullable(fontMap.get(fontName)).orElseThrow(() -> new RuntimeException("字体不存在"));
        return font.deriveFont(fontSize);
    }

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
