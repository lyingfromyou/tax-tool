package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ApiModel(value = "租户")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BaseEntity {

    @ApiModelProperty("租户名称")
    private String name;

    @ApiModelProperty("全称")
    private String fullName;

    @ApiModelProperty("培训期数")
    private Integer pxqs;

    @ApiModelProperty("区域")
    private String area;

    @ApiModelProperty("行业")
    private String industry;

    @ApiModelProperty("人数")
    private Integer peopleNumber;

    @ApiModelProperty("法人姓名")
    private String frxm;

    @ApiModelProperty("法人手机")
    private String frsj;

    @ApiModelProperty("法人邮箱")
    private String fryx;

    @ApiModelProperty("对接人姓名")
    private String djrxm;

    @ApiModelProperty("对接人职务")
    private String djrzw;

    @ApiModelProperty("性别（0-男，1-女）")
    private Integer sex;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("对接人手机")
    private String djrsj;

    @ApiModelProperty("对接人邮箱")
    private String djryx;

    @ApiModelProperty("对接人qq")
    private String qq;

    @ApiModelProperty("租户代码")
    private String code;

    @ApiModelProperty("最大人数")
    private Integer maxPeopleNumber;

}
