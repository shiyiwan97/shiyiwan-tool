package com.shiyiwan.shiyiwan_tool.component.mock;

import com.shiyiwan.shiyiwan_tool.component.entity.SettingGroupEntity;

import java.util.ArrayList;
import java.util.List;

public class MockService {

    public static List<SettingGroupEntity> mockMenuGroupEntityList() {

//        MenuGroupEntity menuGroupEntity0 = new MenuGroupEntity("00","root",0,"0");
        SettingGroupEntity settingGroupEntity1 = new SettingGroupEntity("01","01",1,"00");
        SettingGroupEntity settingGroupEntity2 = new SettingGroupEntity("02","02",1,"00");
        SettingGroupEntity settingGroupEntity3 = new SettingGroupEntity("03","03",1,"00");
        SettingGroupEntity settingGroupEntity4 = new SettingGroupEntity("0101","0101",2,"01");
        SettingGroupEntity settingGroupEntity5 = new SettingGroupEntity("0102","0102",2,"01");
        SettingGroupEntity settingGroupEntity6 = new SettingGroupEntity("0201","0201",2,"02");

        List<SettingGroupEntity> settingGroupEntityList = new ArrayList<>();
//        menuGroupEntityList.add(menuGroupEntity0);
        settingGroupEntityList.add(settingGroupEntity1);
        settingGroupEntityList.add(settingGroupEntity2);
        settingGroupEntityList.add(settingGroupEntity3);
        settingGroupEntityList.add(settingGroupEntity4);
        settingGroupEntityList.add(settingGroupEntity5);
        settingGroupEntityList.add(settingGroupEntity6);

        return settingGroupEntityList;
    }

}
