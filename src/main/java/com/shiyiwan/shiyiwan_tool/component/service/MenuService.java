package com.shiyiwan.shiyiwan_tool.component.service;

import com.shiyiwan.shiyiwan_tool.component.entity.SettingGroupEntity;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuService {

    public static TreeModel generateMenuTreeModel(List<SettingGroupEntity> settingGroupEntityList) {
        int maxLevel = settingGroupEntityList.stream().mapToInt(SettingGroupEntity::getGroupLevel).max().orElse(0);
        List<Map<String, DefaultMutableTreeNode>> idMenuNodeMapList = new ArrayList<>();
        IntStream.range(0, maxLevel + 1).forEach(i -> idMenuNodeMapList.add(new HashMap<>()));
        settingGroupEntityList.stream().forEach(menuGroupEntity -> {
            idMenuNodeMapList.get(menuGroupEntity.getGroupLevel()).put(menuGroupEntity.getGroupId(), new DefaultMutableTreeNode(menuGroupEntity.getGroupName()));
        });
        // todo 不能直接中止程序
        if(idMenuNodeMapList.get(0).size() > 1){
            throw new RuntimeException("根节点数量大于1，生成菜单失败！");
        }
        for(int i = maxLevel;i > 1;i--){
            Map<String, DefaultMutableTreeNode> idMenuNodeMap = idMenuNodeMapList.get(i);
            Map<String, DefaultMutableTreeNode> idParentMenuNodeMap = idMenuNodeMapList.get(i - 1);
            idMenuNodeMap.forEach((id, menuNode) -> {
                String parentMenuId = ((SettingGroupEntity) menuNode.getUserObject()).getParentGroupId();
                DefaultMutableTreeNode parentMenuNode = idParentMenuNodeMap.get(parentMenuId);
                parentMenuNode.add(menuNode);
            });
        }
        return new DefaultTreeModel(idMenuNodeMapList.get(0).values().iterator().next());
    }

    public static TreeModel generateMenuTreeModel2(List<SettingGroupEntity> settingGroupEntityList){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(null);
        Map<String, SettingGroupEntity> idMenuGroupMap = settingGroupEntityList.stream().collect(Collectors.toMap(SettingGroupEntity::getGroupId, SettingGroupEntity::itself));
        idMenuGroupMap.forEach((id,menuNode) -> {
            SettingGroupEntity settingGroupEntity = idMenuGroupMap.get(menuNode.getParentGroupId());
            if(null != settingGroupEntity) {
                settingGroupEntity.getGroupNode().add(menuNode.getGroupNode());
            }else {
                root.add(menuNode.getGroupNode());
            }
        });
        return new DefaultTreeModel(root);
    }





}
