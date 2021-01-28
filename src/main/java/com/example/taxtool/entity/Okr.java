package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value="Okr对象", description="OKR")
public class Okr implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty("用户id")
    private Long createUser;

    @ApiModelProperty("周期id")
    private Long periodId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    public Okr(Long createUser, Long tenantId, Long periodId) {
        this.createUser = createUser;
        this.tenantId = tenantId;
        this.periodId = periodId;
    }

}
