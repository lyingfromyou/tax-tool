package com.example.taxtool.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * @author by Lying
 * @Date 2019/11/4
 */
public class BalanceUtil {

    public final static <T> List<List<T>> balance(List<T> list) {
        if (CollUtil.isNotEmpty(list)) {
            if (list.size() <= 50) {
                return CollUtil.split(list, 10);
            } else if (list.size() <= 100) {
                return CollUtil.split(list, 20);
            } else if (list.size() <= 150) {
                return CollUtil.split(list, 30);
            } else if (list.size() <= 200) {
                return CollUtil.split(list, 40);
            } else if (list.size() <= 300) {
                return CollUtil.split(list, 60);
            } else if (list.size() <= 500) {
                return CollUtil.split(list, 100);
            } else if (list.size() <= 900) {
                return CollUtil.split(list, 150);
            } else if (list.size() <= 1000) {
                return CollUtil.split(list, 180);
            }
        }
        return null;
    }
}
