package com.example.taxtool.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/12/17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UploadSession implements Serializable {


    private String uuid;

    private String email;

}
