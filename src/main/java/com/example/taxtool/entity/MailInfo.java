package com.example.taxtool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/11/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo implements Serializable {

    private String mail;

    private String isExist;

}
