package com.example.taxtool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/10/11
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SendMailInfo implements Serializable {

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮箱地址
     */
    private String mail;
}
