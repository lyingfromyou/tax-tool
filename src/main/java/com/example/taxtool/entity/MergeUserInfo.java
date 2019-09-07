package com.example.taxtool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MergeUserInfo implements Serializable {


    private String phone;

    private String name;

    private String company;

}
