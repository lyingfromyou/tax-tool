package com.example.taxtool.task;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.taxtool.entity.InputUserInfo;
import com.example.taxtool.entity.OutputUserInfo;
import com.example.taxtool.entity.UserInfo;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.TaxUtil;

import java.io.File;
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
    private String fileName;
    private List<InputUserInfo> inputUserInfos;

    public GetUserInfoTask(String cookie, List<InputUserInfo> inputUserInfos, Integer task, String fileName) {
        this.cookie = cookie;
        this.inputUserInfos = inputUserInfos;
        this.task = task;
        this.fileName = fileName;
    }

    @Override
    public List<OutputUserInfo> call() {
        List<OutputUserInfo> userInfos = new ArrayList<>();
        File log = FileUtil.mkdir(CommonConstants.TAX_HANDLE_LOG_PATH + fileName + StrUtil.SLASH + "log_task_" + this.task);
        String msg = "";
        for (int i = 0; i < this.inputUserInfos.size(); i++) {
            InputUserInfo inputUserInfo = inputUserInfos.get(i);
            if (TaxUtil.create(cookie, inputUserInfo.getXm(), inputUserInfo.getSfz())) {
                msg = "task " + this.task + ": " + inputUserInfo.getXm() + " -- 添加成功";
                FileUtil.appendUtf8String(msg, log);
                System.err.println(msg);
                UserInfo userInfo = TaxUtil.query(cookie, inputUserInfo.getXm());
                if (null != userInfo &&
                        StrUtil.isNotBlank(userInfo.getXm())
                        && StrUtil.isNotBlank(userInfo.getSfzjhm())) {
                    msg = "task " + this.task + ": " + inputUserInfo.getXm() + " -- 查询成功";
                    FileUtil.appendUtf8String(msg, log);
                    System.err.println(msg);
                    userInfos.add(new OutputUserInfo(userInfo));
                    addSuccess = addSuccess + 1;
                    if (TaxUtil.remove(cookie, userInfo)) {
                        msg = "task " + this.task + ": " + userInfo.getXm() + " -- 解除授权成功";
                        FileUtil.appendUtf8String(msg, log);
                        System.err.println(msg);
                        if (TaxUtil.delete(cookie, userInfo)) {
                            msg = "task " + this.task + ": " + userInfo.getXm() + " -- 删除用户成功";
                            FileUtil.appendUtf8String(msg, log);
                            System.err.println(msg);
                        } else {
                            msg = "task " + this.task + ": " + userInfo.getXm() + " -- 删除用户失败";
                            FileUtil.appendUtf8String(msg, log);
                            System.err.println(msg);
                        }
                    } else {
                        msg = "task " + this.task + ": " + userInfo.getXm() + " -- 解除授权失败";
                        FileUtil.appendUtf8String(msg, log);
                        System.err.println(msg);
                    }
                } else {
                    msg = "task " + this.task + ": " + inputUserInfo.getXm() + " -- 查询失败";
                    FileUtil.appendUtf8String(msg, log);
                    System.err.println(msg);
                }
            } else {
                addError = addError + 1;
                msg = "task " + this.task + ": " + inputUserInfo.getXm() + " -- 添加失败";
                FileUtil.appendUtf8String(msg, log);
                System.err.println(msg);
            }
        }
        msg = String.format("task: %s, add success: %s , error: %s", this.task, addSuccess, addError);
        FileUtil.appendUtf8String(msg, log);
        System.err.println(msg);
        return userInfos;
    }


}
