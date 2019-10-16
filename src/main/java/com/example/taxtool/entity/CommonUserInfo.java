package com.example.taxtool.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author by Lying
 * @Date 2019/10/16
 */
@Data
@NoArgsConstructor
public class CommonUserInfo implements Serializable {

    private String xm;

    private String phone;

    private String sfz;

    private String company;

    public CommonUserInfo(UserPhone userPhone) {
        this.xm = userPhone.getXm();
        this.phone = userPhone.getPhone();
    }

    public CommonUserInfo(OutputUserInfo outputUserInfo) {
        this.xm = outputUserInfo.getXm();
        this.company = outputUserInfo.getCompany();
        this.sfz = outputUserInfo.getSfz();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUserInfo that = (CommonUserInfo) o;
        return Objects.equals(xm, that.xm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xm);
    }
}
