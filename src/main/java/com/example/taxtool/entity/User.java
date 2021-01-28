package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
@ApiModel("用户表")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @ApiModelProperty("用户姓名")
    private String name;

    @NotBlank(message = "手机号不能空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("性别（0-男，1-女）")
    private Integer sex;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("状态0冻结1正常")
    private Integer status;

    @ApiModelProperty("加入时间")
    private Date joinTime;

    @ApiModelProperty("编号")
    private String no;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("用户头像地址")
    private String avatar;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐值")
    private String salt;

    @ApiModelProperty("是否激活, 0:未激活, 1:激活")
    private Integer isActive;

    @ApiModelProperty("登录时间")
    private Date loginTime;

    @ApiModelProperty("用户角色")
    private Long roleId;

    @ApiModelProperty("手机号隐藏, 0:显示, 1:隐藏")
    private Integer isHidePhone;

    @ApiModelProperty("管理层, 0:无, 1:基层, 2:中层, 3:高层")
    private Integer managementLevel;

}

