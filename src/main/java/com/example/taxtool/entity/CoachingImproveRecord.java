package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "CoachingImproveRecord对象", description = "改善辅导记录")
public class CoachingImproveRecord extends BaseEntity {

    @ApiModelProperty(value = "目前存在的问题")
    private String question;

    @ApiModelProperty(value = "需要改进的点")
    private String needImprove;

    @ApiModelProperty(value = "跟进时间")
    private Date followTime;

    @ApiModelProperty(value = "改进结果")
    private String improveResults;

    @ApiModelProperty("商学院视频id")
    private Long videoId;

    @ApiModelProperty("下级的用户id")
    private Long subordinate;

    @ApiModelProperty("租户id")
    private Long tenantId;

}
