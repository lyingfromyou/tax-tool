package com.example.taxtool.utils;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
public interface CommonConstants {

    /**
     * 基本路径
     */
    String BASE_PATH = "/opt/docker/nginx/html/files/";


    /**
     * 获取公司信息的上传文件路径
     */
    String COMPANY_INFO_FILE_PATH = BASE_PATH + "company_info/";

    /**
     * 文件上传下载的上传路径
     */
    String FILE_UPLOAD_PATH = BASE_PATH + "upload_file/";

    /**
     * 上传文件的id标识
     */
    String UPLOAD_FILE_ID_KEY = "UPLOAD_FILE_ID_KEY";

    /**
     * 发送邮件的上传文件路径
     */
    String SEND_MAIL_FILE_PATH = BASE_PATH + "send_mail_file_path/";

    /**
     * 执行日志
     */
    String TAX_HANDLE_LOG_PATH = BASE_PATH + "tax_handle_log/";

    /**
     * 合并文件上传路径
     */
    String MERGE_FILE_UPLOAD_PATH = BASE_PATH + "merge_file_upload_path/";

    /**
     * 合并文件下载路径
     */
    String MERGE_FILE_PATH = BASE_PATH + "merge_file_path/";

    /**
     * 限制ip
     */
    String LIMIT_IP = "LIMIT_IP";

    /**
     * 上传空号检测的url
     */
    String CHECK_PHONE_UPLOAD_URL = "https://xcjk.mobwin.me/api/Upload.ashx";

    /**
     * 获取空号检测结果的url
     */
    String CHECK_PHONE_GET_RESULT_URL = "https://xcjk.mobwin.me/api/Query.ashx";

    /**
     * 下载url
     */
    String CHECK_PHONE_DOWNLOAD_URL = "https://xcjk.mobwin.me/api/Download.ashx";

    /**
     * 空号检测上传文件路径
     */
    String CHECK_PHONE_UPLOAD_FILE_PATH = BASE_PATH + "check_phone_upload_file_path/";

}
