package com.shiyiwan.shiyiwan_tool.entity;

import lombok.Data;

/**
 * 虚拟机的一些数据
 */
@Data
public class VMDataEntity {

    private int usedMemory;

    private int allocatedMemory;

    private int maxMemory;

}
