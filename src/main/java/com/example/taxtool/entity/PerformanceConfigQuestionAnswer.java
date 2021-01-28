package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PerformanceConfigQuestionAnswer implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("提交记录id")
    private Long recordId;

    @ApiModelProperty("绩效管理配置id")
    private Long performanceConfigId;

    @ApiModelProperty("问题id")
    private Long questionId;

    @ApiModelProperty("选项id, 逗号分隔")
    private String optionIds;

    @ApiModelProperty("创建时间")
    private Date createTime;

}

