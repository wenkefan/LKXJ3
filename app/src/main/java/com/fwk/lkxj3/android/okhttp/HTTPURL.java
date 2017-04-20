package com.fwk.lkxj3.android.okhttp;

/**
 * Created by API on 2016/7/21.
 */
public class HTTPURL {

//    private static final String API = "http://192.168.1.116:8083/WebServices/KPService.ashx?";
//    private static final String API = "http://manage.youery.com/WebServices/KPService.ashx?";
    private static final String API = "http://192.168.1.66:81/WebServices/KPService.ashx?";
    private static final String API_LOGIN = "http://manage.youery.com/WebServices/MobileAttendanceService.ashx?";

//    private static final String API_LOGIN = "http://192.168.1.116:8083/WebServices/MobileAttendanceService.ashx?";

//    private static final String API = "http://manage.youery.com/WebServices/KPService.ashx?";
    /**／
     * 登录
     */
    public static final String API_LONGIN = API_LOGIN + "Option=Login&userName=%1$s&userPwd=%2$s";
//    /**
//     * 获取类型
//     */
//    public static final String API_NEW_TYPE = API + "Option=GetXjkpCheckCategory&kgId=%1$s";
    /**
     * 获取类型
     */
    public static final String API_NEW_TYPE = API + "Option=GetCheckClassificationListByUser&userId=%1$s";

    /**
     * 通过类型加载内容
     */
    public static final String API_CREAC_TYPE = API + "Option=GetXjkpCheckItem&kgId=%1$s&categoryId=%2$s";

    /**
     * 检查对象集合
     * objectTable: 区域类别   objectCategory：id
     */
    public static final String API_OBJECT_LIST = API + "Option=GetCheckObjectCategory&kgid=%1$s&checkCategoryId=%2$s";

    /**
     * 检查结果类型
     */
    public static final String API_JIEGUOOBJECT = API + "Option=GetDictionaryResult&dataDictionaryType=%1$s";

    /**
     * 获取列表
     */
//    public static final String API_LIST = API + "Option=GetXjkpCheckResultByCheckCategoryId&checkCategoryId=%1$s&kgId=%2$s";
    public static final String API_LIST = API + "Option=GetXjkpCheckResultByCheckCategoryId&checkCategoryId=%1$s&kgId=%2$s";
    /**
     * 获取详细列表
     */
    public static final String API_XIANGXILIST = API + "Option=GetXjkpCheckDetailByCheckResultsId&checkResultsId=%1$s";

    /**
     * 提交
     */
    public static final String API_TIJIAO = API + "Option=SaveXjkpCheckResults";

    /**
     * 获取职工信息
     */
    public static final String API_WORKEREXTENSION = API + "Option=GetWorkerExtension&KgId=%1$s";


    public static final String IMGURL = "http://image.youery.com";
}
