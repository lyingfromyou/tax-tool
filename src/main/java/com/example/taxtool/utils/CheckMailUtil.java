package com.example.taxtool.utils;

import cn.hutool.json.JSONUtil;
import com.example.taxtool.entity.MailInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * @author by Lying
 * @Date 2019/11/5
 */
@Slf4j
public class CheckMailUtil {

    private static final String CHECK_URL = "https://shorttimemail.com/mail-validator/check?email=";

    public static final MailInfo check(String mail) {
        return batchCheck(Arrays.asList(mail)).get(0);
    }

    public static final List<MailInfo> batchCheck(Collection<String> mails) {
        return doCheck(mails);
    }

    private static final List<MailInfo> doCheck(Collection<String> mails) {
        List<MailInfo> mailInfos = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        for (String mail : mails) {
            params.put("email", mail);
            try {
                String resultStr = HttpUtil.get("https://shorttimemail.com/mail-validator/check", params);
                CheckResult result = JSONUtil.toBean(resultStr, CheckResult.class);
                if (null != result && result.getCode() == 0) {
                    mailInfos.add(new MailInfo(mail, "存在"));
                } else {
                    mailInfos.add(new MailInfo(mail, "不存在"));

                }
            } catch (IOException e) {
                log.error("mail: " + mail + ", check error, msg: " + e.getMessage());
            }
        }
        return mailInfos;
    }

}

@Data
class CheckResult {

    /**
     * 0 == ok
     * -1 == no ok
     */
    private int code;
}