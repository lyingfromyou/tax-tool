package com.example.taxtool.task;

import cn.hutool.core.util.StrUtil;
import com.example.taxtool.entity.InputUserInfo;
import com.example.taxtool.entity.OutputUserInfo;
import com.example.taxtool.entity.UserInfo;
import com.example.taxtool.utils.TaxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author by Lying
 * @Date 2019/8/18
 */
public class GetUserInfoTask implements Callable<List<OutputUserInfo>> {

    private int addSuccess = 0;
    private int addError = 0;

    private String cookie;
    private Integer task;
    private List<InputUserInfo> inputUserInfos;

    public GetUserInfoTask(String cookie, List<InputUserInfo> inputUserInfos, Integer task) {
        this.cookie = cookie;
        this.inputUserInfos = inputUserInfos;
        this.task = task;
    }

    @Override
    public List<OutputUserInfo> call() {
        List<OutputUserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < this.inputUserInfos.size(); i++) {
            InputUserInfo inputUserInfo = inputUserInfos.get(i);
            if (TaxUtil.create(cookie, inputUserInfo.getXm(), inputUserInfo.getSfz())) {
                System.err.println("task " + this.task + ": " + inputUserInfo.getXm() + " -- 添加成功");
                UserInfo userInfo = TaxUtil.query(cookie, inputUserInfo.getXm());
                if (null != userInfo &&
                        StrUtil.isNotBlank(userInfo.getXm())
                        && StrUtil.isNotBlank(userInfo.getSfzjhm())) {
                    System.err.println("task " + this.task + ": " + inputUserInfo.getXm() + " -- 查询成功");
                    userInfos.add(new OutputUserInfo(userInfo));
                    addSuccess = addSuccess + 1;
                    if (TaxUtil.remove(cookie, userInfo)) {
                        System.err.println("task " + this.task + ": " + userInfo.getXm() + " -- 解除授权成功");
                        if (TaxUtil.delete(cookie, userInfo)) {
                            System.err.println("task " + this.task + ": " + userInfo.getXm() + " -- 删除用户成功");
                        } else {
                            System.err.println("task " + this.task + ": " + userInfo.getXm() + " -- 删除用户失败");
                        }
                    } else {
                        System.err.println("task " + this.task + ": " + userInfo.getXm() + " -- 解除授权失败");
                    }
                } else System.err.println("task " + this.task + ": " + inputUserInfo.getXm() + " -- 查询失败");
            } else {
                addError = addError + 1;
                System.err.println("task " + this.task + ": " + inputUserInfo.getXm() + " -- 添加失败");
            }
        }
        System.err.println(String.format("task: %s, add success: %s , error: %s", this.task, addSuccess, addError));
        return userInfos;
    }


}
