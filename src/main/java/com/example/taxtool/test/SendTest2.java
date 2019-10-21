package com.example.taxtool.test;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by Lying
 * @Date 2019/10/16
 */
public class SendTest2 {

    private static final String KEY = "421f456e32ea5567fb56be9fb5847d1a";
    private static final String SECRET = "dde6f6ded459f2ce4f1469e2a428acb5";

    public static void main(String[] args) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(KEY, SECRET, new ClientOptions("v3.1"));


        List<Map<String, String>> userInfos = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>();
        map1.put("userName", "李乾");
        map1.put("email", "183023840@qq.com");

        Map<String, String> map2 = new HashMap<>();
        map2.put("userName", "傻儿");
        map2.put("email", "121453698@qq.com");

        Map<String, String> map3 = new HashMap<>();
        map3.put("userName", "傻儿");
        map3.put("email", "1959596147@qq.com");


        userInfos.add(map1);
        userInfos.add(map3);
        userInfos.add(map2);


        request = buildRequest(userInfos);
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());

    }


    public static MailjetRequest buildRequest(List<Map<String, String>> userInfos) {
        JSONArray messages = new JSONArray();
        for (Map<String, String> map : userInfos) {
            JSONObject message = new JSONObject();

            message.put(Emailv31.Message.FROM, new JSONObject()
                    .put("Email", "liqian0213@gmail.com")
                    .put("Name", "逾期告知"))
                    .put(Emailv31.Message.TO, new JSONArray()
                            .put(new JSONObject().put("Email", map.get("email"))))
                    .put(Emailv31.Message.TEMPLATEID, 1044372)
                    .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                    .put(Emailv31.Message.SUBJECT, " 欠款逾期告知")
                    .put(Emailv31.Message.VARIABLES, new JSONObject()
                            .put("userName", map.get("userName")));

            messages.put(message);
        }
        return new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, messages);
    }


    @Test
    public void sendBaseMail() throws Exception{
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(KEY, SECRET, new ClientOptions("v3.1"));

        String text = "赵家柱，我方已核实你单位：重庆市万盛区白塔建筑劳务有限公司，我方会安排工作人员前往，同时会请求你单位同事领导等协助处理，目前已联系单位进行转告，限2小时内来电17521779411处理";


        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "liqian0213@gmail.com")
                                        .put("Name", "耶稣"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "183023840@qq.com")))
                                .put(Emailv31.Message.SUBJECT, "来自上帝的警告")
                                .put(Emailv31.Message.TEXTPART, text)));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());



    }

    @Test
    public void test3() throws UnknownHostException {
//        String s = "asfasdfasdfasd-1111.xlsx";
//        String fileName = StrUtil.subAfter(s, StrUtil.DASHED,true);
//        System.err.println(fileName);
        System.err.println( InetAddress.getLocalHost().getHostAddress());
    }
}
