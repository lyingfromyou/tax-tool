package com.example.taxtool.entity;


import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInDataExport implements Serializable {

    /**
     * "序列"
     */
    @Alias("序列")
    private Integer num;

    /**
     * 市/区县
     */
//    @Alias("市/区县")
    private String districtName;

    /**
     * 教育局/学校
     */
//    @Alias("教育局/学校")
    private String orgName;


}
