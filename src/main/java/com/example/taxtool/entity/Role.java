package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "角色")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Role extends BaseEntity {

	@NotBlank(message = "角色名称不能为空")
	@ApiModelProperty( "角色名称")
	private String roleName;

	@NotBlank(message = "角色标识不能为空")
	@ApiModelProperty( "角色标识")
	private String roleCode;

	@ApiModelProperty( "角色描述")
	private String roleDesc;

	@ApiModelProperty("租户id")
	private Long tenantId;

	@ApiModelProperty("数据权限类型, 0:全部, 1:自定义, 2:本级及子级, 3:本级")
	private Integer dsType;

	@ApiModelProperty(value = "数据权限作用范围")
	private String dsScope;

	@ApiModelProperty("0:新建角色, 1:默认角色, 默认角色不允许修改")
	private Integer isDefault;

	@ApiModelProperty("0:显示, 1:隐藏")
	private Integer isHide;

}
