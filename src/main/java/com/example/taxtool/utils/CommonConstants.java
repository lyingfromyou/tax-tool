package com.example.taxtool.utils;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
public interface CommonConstants {

    /**
     * 获取公司信息的上传文件路径
     */
    String COMPANY_INFO_FILE_PATH = "/opt/files/company_info/";

    /**
     *  文件上传下载的上传路径
     */
    String FILE_UPLOAD_PATH = "/opt/files/upload_file/";

    /**
     * 上传文件的id标识
     */
    String UPLOAD_FILE_ID_KEY = "UPLOAD_FILE_ID_KEY";

    /**
     * 发送邮件的上传文件路径
     */
    String SEND_MAIL_FILE_PATH = "/opt/files/send_mail_file_path/";

    /**
     * 验证邮箱的文件保存路径
     */
    String VERIFICATION_MAIL_FILE_PATH = "/opt/files/verification_mail/";

    /**
     * 验证结果
     */
    String VERIFICATION_MAIL_RESULT_FILE_PATH = "/opt/files/verification_result_mail/";

    /**
     * 执行日志
     */
    String TAX_HANDLE_LOG_PATH = "/opt/files/tax_handle_log/";

    /**
     * 合并文件上传路径
     */
    String MERGE_FILE_UPLOAD_PATH = "/opt/files/merge_file_upload_path/";

    /**
     * 合并文件下载路径
     */
    String MERGE_FILE_PATH = "/opt/files/merge_file_path/";

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
    String CHECK_PHONE_UPLOAD_FILE_PATH = "/opt/files/check_phone_upload_file_path/";

}
