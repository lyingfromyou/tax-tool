package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "ValuesQuestionAnswer对象", description = "价值观问题答案")
public class ValuesQuestionAnswer implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("价值观提交记录id")
    private Long recordId;

    @ApiModelProperty("问题id")
    private Long questionId;

    @ApiModelProperty("选项id, 逗号分隔")
    private String optionIds;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
