package com.example.taxtool.common;

import lombok.Getter;

public enum ActivityProcessEnum {

    //签到
    SIGN_IN(1),

    //直播
    LIVE_BROADCAST(2),

    //考试
    EXAMINATION(3),

    //问卷
    QUESTIONNAIRE(4),

    //总结
    SUMMARY(5),

    //视频
    VIDEO(6),

    //文档
    DOCUMENT(7),

    //课程
    COURSE(8);

    @Getter
    private int process;

    ActivityProcessEnum(int process) {
        this.process = process;
    }


    public static ActivityProcessEnum formByProcess(int process) {
        for (ActivityProcessEnum activityProcess : ActivityProcessEnum.values()) {
            if (process == activityProcess.getProcess()) {
                return activityProcess;
            }
        }
        throw new RuntimeException("ActivityProcessEnum formByProcess Unexpected value: " + process);
    }
}
