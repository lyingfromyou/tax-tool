package com.example.taxtool.task;

import cn.hutool.core.collection.CollUtil;
import com.example.taxtool.entity.UserInfo;
import com.example.taxtool.utils.BalanceUtil;
import com.example.taxtool.utils.TaxUtil;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/8/18
 */
public class RemoveAndDeleteUser implements Runnable {

    private final ThreadPoolExecutor executor;

    private int removeSuccess = 0;
    private int removeError = 0;
    private int deleteSuccess = 0;
    private int deleteError = 0;

    private String cookie;

    public RemoveAndDeleteUser(String cookie, ThreadPoolExecutor executor) {
        this.executor = executor;
        this.cookie = cookie;
    }

    public void run() {
        List<UserInfo> queryUsers = TaxUtil.queryList(cookie);
        if (CollUtil.isNotEmpty(queryUsers)) {
            int totalSize = queryUsers.size();
            queryUsers = queryUsers.stream().filter(user -> !"聂峥勤".equals(user.getXm())).collect(Collectors.toList());

            List<List<UserInfo>> splitList = BalanceUtil.balance(queryUsers);
            for (List<UserInfo> list : splitList) {
                executor.execute(() -> {
                    int currentSize = list.size();
                    int i = 0;

                    for (UserInfo userInfo : list) {
                        String msg = "一共: " + totalSize + " 个; 当前线程: " + Thread.currentThread().getName()
                                + "; 当前线程分配: " + currentSize + " 个, 当前执行到: " + (++i) + "个";
                        System.err.println(msg);

                        if ("已解除".equals(userInfo.getBssqztMc())) {
                            if (TaxUtil.delete(cookie, userInfo)) {
                                deleteSuccess = deleteSuccess + 1;
                                System.err.println(userInfo.getXm() + " -- 删除用户成功");
                            } else {
                                deleteError = deleteError + 1;
                                System.err.println(userInfo.getXm() + " -- 删除用户失败");
                            }
                        } else {
                            if (TaxUtil.remove(cookie, userInfo)) {
                                removeSuccess = removeSuccess + 1;
                                System.err.println(userInfo.getXm() + " -- 解除授权成功");
                            } else {
                                System.err.println(userInfo.getXm() + " -- 解除授权失败");
                                removeError = removeError + 1;
                            }
                        }
                    }
                });
            }
        }
    }
}
