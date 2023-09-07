package com.shiyiwan.shiyiwan_tool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * 设置菜单的设置组（左侧树）
 */
@Data
@AllArgsConstructor
public class SettingGroupEntity {

    private String groupId;

    private String groupName;

    private Integer groupLevel;

    // todo 菜单排序
    private Integer sort;

    private String parentGroupId;

    private String menuPath;

    private List<String> childMenuIdList;

    private DefaultMutableTreeNode groupNode;

    private List<SettingItemEntity> settingItemEntityList;

    public SettingGroupEntity(String groupId, String groupName, Integer groupLevel, String parentGroupId){
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupLevel = groupLevel;
        this.parentGroupId = parentGroupId;
        this.groupNode = new DefaultMutableTreeNode(groupName);
    }

    public SettingGroupEntity itself(){
        return this;
    }


}
