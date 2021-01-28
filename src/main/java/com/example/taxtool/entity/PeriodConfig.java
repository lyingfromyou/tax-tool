package com.example.taxtool.entity;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value="PeriodConfig对象", description="OKR周期配置")
public class PeriodConfig extends BaseEntity {

    @NotNull(message = "OKR周期长度不能为空")
    @ApiModelProperty(value = "OKR周期长度")
    private Integer period;

    @NotNull
    @ApiModelProperty(value = "新生效周期开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    @ApiModelProperty(value = "新生效周期结束时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    @NotNull
    @ApiModelProperty(value = "上个周期结束前多久展示下个周期入口")
    private Integer inAdvance;

    @NotNull
    @ApiModelProperty(value = "新周期出现时间的时间单位, 0:天, 1:月")
    private Integer inAdvanceType;

    @NotNull
    @ApiModelProperty(value = "开启年度okr, 0:关闭, 1:开启")
    private Integer yearOkr;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;


}
