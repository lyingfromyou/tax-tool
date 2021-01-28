package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="okr 对齐")
public class OkrAlign implements Serializable {

    @ApiModelProperty(value = "objectiveId")
    private Long objectiveId;

    @ApiModelProperty(value = "对齐的objectiveId")
    private Long alignId;

}
