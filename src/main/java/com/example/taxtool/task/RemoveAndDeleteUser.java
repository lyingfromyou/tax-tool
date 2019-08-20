package com.example.taxtool.task;

import cn.hutool.core.collection.CollUtil;
import com.example.taxtool.entity.UserInfo;
import com.example.taxtool.utils.TaxUtil;

import java.util.List;

/**
 * @author by Lying
 * @Date 2019/8/18
 */
public class RemoveAndDeleteUser implements Runnable {

    private int removeSuccess = 0;
    private int removeError = 0;

    private int deleteSuccess = 0;
    private int deleteError = 0;

    private String cookie;

    public RemoveAndDeleteUser(String cookie) {
        this.cookie = cookie;
    }

    public void run(){
        List<UserInfo> queryUsers = TaxUtil.queryList(cookie);
        if (CollUtil.isNotEmpty(queryUsers)) {
            for (UserInfo userInfo : queryUsers) {
                if (TaxUtil.remove(cookie, userInfo)) {
                    removeSuccess = removeSuccess + 1;
                    System.err.println(userInfo.getXm() + " -- 解除授权成功");
                    if (TaxUtil.delete(cookie, userInfo)) {
                        deleteSuccess = deleteSuccess + 1;
                        System.err.println(userInfo.getXm() + " -- 删除用户成功");
                    } else {
                        deleteError = deleteError + 1;
                        System.err.println(userInfo.getXm() + " -- 删除用户失败");
                    }
                } else {
                    System.err.println(userInfo.getXm() + " -- 解除授权失败");
                    removeError = removeError + 1;
                }
            }
        }
    }
}
