package com.shiyiwan.shiyiwan_tool.util;

import java.lang.annotation.*;

/**
 * 用于标记需要删除的东西，方便后期搜索删除
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface NeedDelete {
}
