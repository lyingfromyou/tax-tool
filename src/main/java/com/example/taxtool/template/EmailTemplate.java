package com.example.taxtool.template;

/**
 * @author by Lying
 * @Date 2019/10/16
 */
public interface EmailTemplate {

    String CODE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"x-apple-disable-message-reformatting\" /><meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /><meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" /><meta name=\"format-detection\" content=\"telephone=no\" /><title></title><style type=\"text/css\">\n" +
            "        /* Resets */\n" +
            "        .ReadMsgBody { width: 100%; background-color: #ebebeb;}\n" +
            "        .ExternalClass {width: 100%; background-color: #ebebeb;}\n" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height:100%;}\n" +
            "        a[x-apple-data-detectors]{\n" +
            "            color:inherit !important;\n" +
            "            text-decoration:none !important;\n" +
            "            font-size:inherit !important;\n" +
            "            font-family:inherit !important;\n" +
            "            font-weight:inherit !important;\n" +
            "            line-height:inherit !important;\n" +
            "        }        \n" +
            "        body {-webkit-text-size-adjust:none; -ms-text-size-adjust:none;}\n" +
            "        body {margin:0; padding:0;}\n" +
            "        .yshortcuts a {border-bottom: none !important;}\n" +
            "        .rnb-del-min-width{ min-width: 0 !important; }\n" +
            "\n" +
            "        /* Add new outlook css start */\n" +
            "        .templateContainer{\n" +
            "            max-width:590px !important;\n" +
            "            width:auto !important;\n" +
            "        }\n" +
            "        /* Add new outlook css end */\n" +
            "\n" +
            "        /* Image width by default for 3 columns */\n" +
            "        img[class=\"rnb-col-3-img\"] {\n" +
            "        max-width:170px;\n" +
            "        }\n" +
            "\n" +
            "        /* Image width by default for 2 columns */\n" +
            "        img[class=\"rnb-col-2-img\"] {\n" +
            "        max-width:264px;\n" +
            "        }\n" +
            "\n" +
            "        /* Image width by default for 2 columns aside small size */\n" +
            "        img[class=\"rnb-col-2-img-side-xs\"] {\n" +
            "        max-width:180px;\n" +
            "        }\n" +
            "\n" +
            "        /* Image width by default for 2 columns aside big size */\n" +
            "        img[class=\"rnb-col-2-img-side-xl\"] {\n" +
            "        max-width:350px;\n" +
            "        }\n" +
            "\n" +
            "        /* Image width by default for 1 column */\n" +
            "        img[class=\"rnb-col-1-img\"] {\n" +
            "        max-width:550px;\n" +
            "        }\n" +
            "\n" +
            "        /* Image width by default for header */\n" +
            "        img[class=\"rnb-header-img\"] {\n" +
            "        max-width:590px;\n" +
            "        }\n" +
            "\n" +
            "        /* Ckeditor line-height spacing */\n" +
            "        .rnb-force-col p, ul, ol{margin:0px!important;}\n" +
            "        .rnb-del-min-width p, ul, ol{margin:0px!important;}\n" +
            "\n" +
            "        /* tmpl-2 preview */\n" +
            "        .rnb-tmpl-width{ width:100%!important;}\n" +
            "\n" +
            "        /* tmpl-11 preview */\n" +
            "        .rnb-social-width{padding-right:15px!important;}\n" +
            "\n" +
            "        /* tmpl-11 preview */\n" +
            "        .rnb-social-align{float:right!important;}\n" +
            "\n" +
            "        /* Ul Li outlook extra spacing fix */\n" +
            "        li{mso-margin-top-alt: 0; mso-margin-bottom-alt: 0;}        \n" +
            "\n" +
            "        /* Outlook fix */\n" +
            "        table {mso-table-lspace:0pt; mso-table-rspace:0pt;}\n" +
            "    \n" +
            "        /* Outlook fix */\n" +
            "        table, tr, td {border-collapse: collapse;}\n" +
            "\n" +
            "        /* Outlook fix */\n" +
            "        p,a,li,blockquote {mso-line-height-rule:exactly;} \n" +
            "\n" +
            "        /* Outlook fix */\n" +
            "        .msib-right-img { mso-padding-alt: 0 !important;}\n" +
            "\n" +
            "        /* Fix text line height on preview */ \n" +
            "        .content-spacing div {display: list-item; list-style-type: none;}\n" +
            "\n" +
            "        @media only screen and (min-width:590px){\n" +
            "        /* mac fix width */\n" +
            "        .templateContainer{width:590px !important;}\n" +
            "        }\n" +
            "\n" +
            "        @media screen and (max-width: 360px){\n" +
            "        /* yahoo app fix width \"tmpl-2 tmpl-10 tmpl-13\" in android devices */\n" +
            "        .rnb-yahoo-width{ width:360px !important;}\n" +
            "        }\n" +
            "\n" +
            "        @media screen and (max-width: 380px){\n" +
            "        /* fix width and font size \"tmpl-4 tmpl-6\" in mobile preview */\n" +
            "        .element-img-text{ font-size:24px !important;}\n" +
            "        .element-img-text2{ width:230px !important;}\n" +
            "        .content-img-text-tmpl-6{ font-size:24px !important;}\n" +
            "        .content-img-text2-tmpl-6{ width:220px !important;}\n" +
            "        }\n" +
            "\n" +
            "        @media screen and (max-width: 480px) {\n" +
            "        td[class=\"rnb-container-padding\"] {\n" +
            "        padding-left: 10px !important;\n" +
            "        padding-right: 10px !important;\n" +
            "        }\n" +
            "\n" +
            "        /* force container nav to (horizontal) blocks */\n" +
            "        td.rnb-force-nav {\n" +
            "        display: inherit;\n" +
            "        }\n" +
            "\n" +
            "        /* fix text alignment \"tmpl-11\" in mobile preview */\n" +
            "        .rnb-social-text-left {\n" +
            "        width: 100%;\n" +
            "        text-align: center;\n" +
            "        margin-bottom: 15px;\n" +
            "        }\n" +
            "        .rnb-social-text-right {\n" +
            "        width: 100%;\n" +
            "        text-align: center;\n" +
            "        }\n" +
            "        }\n" +
            "\n" +
            "        @media only screen and (max-width: 600px) {\n" +
            "\n" +
            "        /* center the address &amp; social icons */\n" +
            "        .rnb-text-center {text-align:center !important;}\n" +
            "\n" +
            "        /* force container columns to (horizontal) blocks */\n" +
            "        td.rnb-force-col {\n" +
            "        display: block;\n" +
            "        padding-right: 0 !important;\n" +
            "        padding-left: 0 !important;\n" +
            "        width:100%;\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-container {\n" +
            "         width: 100% !important;\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-btn-col-content {\n" +
            "        width: 100% !important;\n" +
            "        }\n" +
            "        table.rnb-col-3 {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "\n" +
            "        /* change left/right padding and margins to top/bottom ones */\n" +
            "        margin-bottom: 10px;\n" +
            "        padding-bottom: 10px;\n" +
            "        /*border-bottom: 1px solid #eee;*/\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-last-col-3 {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-col-2 {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "\n" +
            "        /* change left/right padding and margins to top/bottom ones */\n" +
            "        margin-bottom: 10px;\n" +
            "        padding-bottom: 10px;\n" +
            "        /*border-bottom: 1px solid #eee;*/\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-col-2-noborder-onright {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "\n" +
            "        /* change left/right padding and margins to top/bottom ones */\n" +
            "        margin-bottom: 10px;\n" +
            "        padding-bottom: 10px;\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-col-2-noborder-onleft {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "\n" +
            "        /* change left/right padding and margins to top/bottom ones */\n" +
            "        margin-top: 10px;\n" +
            "        padding-top: 10px;\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-last-col-2 {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "        }\n" +
            "\n" +
            "        table.rnb-col-1 {\n" +
            "        /* unset table align=\"left/right\" */\n" +
            "        float: none !important;\n" +
            "        width: 100% !important;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-col-3-img {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-col-2-img {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-col-2-img-side-xs {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-col-2-img-side-xl {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-col-1-img {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-header-img {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        margin:0 auto;\n" +
            "        }\n" +
            "\n" +
            "        img.rnb-logo-img {\n" +
            "        /**max-width:none !important;**/\n" +
            "        width:100% !important;\n" +
            "        }\n" +
            "\n" +
            "        td.rnb-mbl-float-none {\n" +
            "        float:inherit !important;\n" +
            "        }\n" +
            "\n" +
            "        .img-block-center{text-align:center !important;}\n" +
            "\n" +
            "        .logo-img-center\n" +
            "        {\n" +
            "            float:inherit !important;\n" +
            "        }\n" +
            "\n" +
            "        /* tmpl-11 preview */\n" +
            "        .rnb-social-align{margin:0 auto !important; float:inherit !important;}\n" +
            "\n" +
            "        /* tmpl-11 preview */\n" +
            "        .rnb-social-center{display:inline-block;}\n" +
            "\n" +
            "        /* tmpl-11 preview */\n" +
            "        .social-text-spacing{margin-bottom:0px !important; padding-bottom:0px !important;}\n" +
            "\n" +
            "        /* tmpl-11 preview */\n" +
            "        .social-text-spacing2{padding-top:15px !important;}\n" +
            "\n" +
            "    }</style><!--[if gte mso 11]><style type=\"text/css\">table{border-spacing: 0; }table td {border-collapse: separate;}</style><![endif]--><!--[if !mso]><!--><style type=\"text/css\">table{border-spacing: 0;} table td {border-collapse: collapse;}</style> <!--<![endif]--><!--[if gte mso 15]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]--><!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]--></head><body>\n" +
            "\n" +
            "<table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"main-template\" bgcolor=\"#f9fafc\" style=\"background-color: rgb(249, 250, 252);\">\n" +
            "\n" +
            "    <tbody><tr style=\"display:none !important; font-size:1px; mso-hide: all;\"><td></td><td></td></tr><tr>\n" +
            "        <td align=\"center\" valign=\"top\">\n" +
            "        <!--[if gte mso 9]>\n" +
            "                        <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"590\" style=\"width:590px;\">\n" +
            "                        <tr>\n" +
            "                        <td align=\"center\" valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
            "                        <![endif]-->\n" +
            "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\" style=\"max-width:590px!important; width: 590px;\">\n" +
            "        <tbody><tr>\n" +
            "\n" +
            "        <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "            <table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_0\" id=\"Layout_0\">\n" +
            "                <tbody><tr>\n" +
            "                    <td class=\"rnb-del-min-width\" valign=\"top\" align=\"center\" style=\"min-width:590px;\">\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" border=\"0\" height=\"38\" cellspacing=\"0\">\n" +
            "                            <tbody><tr>\n" +
            "                                <td valign=\"top\" height=\"38\">\n" +
            "                                    <img width=\"20\" height=\"38\" style=\"display:block; max-height:38px; max-width:20px;\" alt=\"\" src=\"http://img.mailinblue.com/new_images/rnb/rnb_space.gif\">\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "                        </tbody></table>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody></table>\n" +
            "            </td>\n" +
            "    </tr><tr>\n" +
            "\n" +
            "        <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "            <div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
            "                \n" +
            "                <!--[if mso]>\n" +
            "                <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
            "                <tr>\n" +
            "                <![endif]-->\n" +
            "                \n" +
            "                <!--[if mso]>\n" +
            "                <td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
            "                <![endif]-->\n" +
            "            \n" +
            "                <table width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" name=\"Layout_\" id=\"Layout_\"><tbody><tr>\n" +
            "                    <td align=\"center\" valign=\"top\"><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-container\" bgcolor=\"#ffffff\" style=\"height: 0px; background-color: rgb(255, 255, 255); border-radius: 0px; border-collapse: separate; padding-left: 20px; padding-right: 20px;\"><tbody><tr>\n" +
            "                                <td class=\"rnb-container-padding\" style=\"font-size: px;font-family: ; color: ;\">\n" +
            "\n" +
            "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-columns-container\" align=\"center\" style=\"margin:auto;\">\n" +
            "                                        <tbody><tr>\n" +
            "\n" +
            "                                            <td class=\"rnb-force-col\" align=\"center\">\n" +
            "\n" +
            "                                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"rnb-col-1\">\n" +
            "\n" +
            "                                                    <tbody><tr>\n" +
            "                                                        <td height=\"10\"></td>\n" +
            "                                                    </tr>\n" +
            "\n" +
            "                                                    <tr>\n" +
            "                                                        <td class=\"content-spacing\" style=\"font-family:Arial,Helvetica,sans-serif; color:#3c4858; text-align:center;\">\n" +
            "\n" +
            "                                                            <span style=\"color:#3c4858;\"><span style=\"font-size: 24px;\"><b>欠款逾期告知</b></span></span>\n" +
            "                                                        </td>\n" +
            "                                                    </tr>\n" +
            "                                                    <tr>\n" +
            "                                                        <td height=\"10\"></td>\n" +
            "                                                    </tr>\n" +
            "                                                    </tbody></table>\n" +
            "                                                </td></tr>\n" +
            "                                    </tbody></table></td>\n" +
            "                            </tr>\n" +
            "\n" +
            "                        </tbody></table>\n" +
            "\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "\n" +
            "            </tbody></table><!--[if mso]>\n" +
            "                </td>\n" +
            "                <![endif]-->\n" +
            "                \n" +
            "                <!--[if mso]>\n" +
            "                </tr>\n" +
            "                </table>\n" +
            "                <![endif]-->\n" +
            "            \n" +
            "        </div></td>\n" +
            "    </tr><tr>\n" +
            "\n" +
            "        <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "            <table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_\" id=\"Layout_\">\n" +
            "                <tbody><tr>\n" +
            "                    <td class=\"rnb-del-min-width\" valign=\"top\" align=\"center\" style=\"min-width:590px;\">\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" border=\"0\" height=\"30\" cellspacing=\"0\">\n" +
            "                            <tbody><tr>\n" +
            "                                <td valign=\"top\" height=\"30\">\n" +
            "                                    <img width=\"20\" height=\"30\" style=\"display:block; max-height:30px; max-width:20px;\" alt=\"\" src=\"http://img.mailinblue.com/new_images/rnb/rnb_space.gif\">\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "                        </tbody></table>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody></table>\n" +
            "            </td>\n" +
            "    </tr><tr>\n" +
            "\n" +
            "        <td align=\"center\" valign=\"top\">\n" +
            "\n" +
            "            <div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
            "            \n" +
            "                <!--[if mso]>\n" +
            "                <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
            "                <tr>\n" +
            "                <![endif]-->\n" +
            "                \n" +
            "                <!--[if mso]>\n" +
            "                <td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
            "                <![endif]-->\n" +
            "                <table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:100%;\" name=\"Layout_5\">\n" +
            "                <tbody><tr>\n" +
            "                    <td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\">\n" +
            "                        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-container\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255); padding-left: 20px; padding-right: 20px; border-collapse: separate; border-radius: 0px; border-bottom: 0px none rgb(200, 200, 200);\">\n" +
            "\n" +
            "                                        <tbody><tr>\n" +
            "                                            <td height=\"20\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td valign=\"top\" class=\"rnb-container-padding\" align=\"left\">\n" +
            "\n" +
            "                                                <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-columns-container\">\n" +
            "                                                    <tbody><tr>\n" +
            "                                                        <td class=\"rnb-force-col\" valign=\"top\" style=\"padding-right: 0px;\">\n" +
            "\n" +
            "                                                            <table border=\"0\" valign=\"top\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" align=\"left\" class=\"rnb-col-1\">\n" +
            "\n" +
            "                                                                <tbody><tr>\n" +
            "                                                                    <td class=\"content-spacing\" style=\"font-size:14px; font-family:Arial,Helvetica,sans-serif, sans-serif; color:#3c4858; line-height: 21px;\"><div><br>\n" +
            "<font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 尊敬的{{userName}}， 您好， 您在xxx的借款 xxxx 元，已逾期xx天，请您尽快还清欠款，详情咨询xxxx-xxxxxxx</font></font></div>\n" +
            "</td>\n" +
            "                                                                </tr>\n" +
            "                                                                </tbody></table>\n" +
            "\n" +
            "                                                            </td></tr>\n" +
            "                                                </tbody></table></td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td height=\"20\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
            "                                        </tr>\n" +
            "                                    </tbody></table>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody></table><!--[if mso]>\n" +
            "                </td>\n" +
            "                <![endif]-->\n" +
            "                \n" +
            "                <!--[if mso]>\n" +
            "                </tr>\n" +
            "                </table>\n" +
            "                <![endif]-->\n" +
            "\n" +
            "            </div></td>\n" +
            "    </tr></tbody></table>\n" +
            "            <!--[if gte mso 9]>\n" +
            "                        </td>\n" +
            "                        </tr>\n" +
            "                        </table>\n" +
            "                        <![endif]-->\n" +
            "                        </td>\n" +
            "        </tr>\n" +
            "        </tbody></table>\n" +
            "\n" +
            "</body></html>";

}
