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
@ApiModel(value = "部门关系")
public class DeptRelation implements Serializable {

	@ApiModelProperty(value = "祖先节点")
	private Long ancestor;

	@ApiModelProperty(value = "后代节点")
	private Long descendant;

}
