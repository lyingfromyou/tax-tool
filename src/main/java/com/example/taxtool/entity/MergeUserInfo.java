package com.example.taxtool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MergeUserInfo implements Serializable {


    private String phone;

    private String name;

    private String company;

}
