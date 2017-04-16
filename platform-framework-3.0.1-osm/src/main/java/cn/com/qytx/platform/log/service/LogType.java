package cn.com.qytx.platform.log.service;

/**
 * 登陆日志常量类
 * @author FDX
 */
public class LogType{
    /**
     * 全部日志:0
     */
    public static final int LOG_ALL = 0;
    /**
     * 登陆成功:1
     */
    public static final int LOG_LOGIN_SUCCESS = 1;
    /**
     * 登陆日志之用密码错误:2
     */
    public static final int LOG_LOGIN_PASSWARD = 2;
    /**
     * 登陆日志之用户名错误:3
     */
    public static final int LOG_LOGIN_USERNAME = 3;
    /**
     * 登陆日志之清除密码:4
    public static final int LOG_LOGIN_PASSWARD_CLEAN = 4;
     */
    /**
     *登陆日志之非法IP登陆:
    public static final int LOG_LOGIN_ILLICIT_IP = 5;
     */
    /**
     * 登陆日志之退出系统:6
     */
    public static final int LOG_LOGIN_OUT = 6;
    
    /**
     * 用户添加：7
     */
    public static final int LOG_USER_ADD = 7;
    /**
     * 用户修改：8
     */
    public static final int LOG_USER_UPDATE = 8;
    /**
     * 用户删除：9
     */
    public static final int LOG_USER_DELETE = 9;
    /**
     * 用户离职：10
    public static final int LOG_USER_DIMISSION = 10;
     */
    /**
     * 用户修改登陆密码：11
     */
    public static final int LOG_USER_UPDATE_LOGPASS = 11;
 
    /**
     * 部门添加：12
     */
    public static final int LOG_GROUP_ADD = 12;
    /**
     * 部门修改：13
     */
    public static final int LOG_GROUP_UPDATE = 13;
    /**
     * 部门删除：14
     */
    public static final int LOG_GROUP_DELETE =14;
    
    /**
     * 删除公告：15（不用了，改用85或97）
     */
    public static final int LOG_NOTIFY_ADD =15;
    
    /**
     * 发送邮件：16
     */
    public static final int LOG_FILE_SORT =16;
    
    /**
     * 邮件删除：17
     */
    public static final int LOG_EMAIL_DELETE =17;
    
    /**
     * 按模块设置管理范围：18
     */
    public static final int LOG_RANGE_SETUP =18;
    
    /**
     * 增加单位信息
     */
    public static final int LOG_COMPANY_ADD = 19;
   
    /**
     * 管理员密码修改
     */
    public static final int LOG_ADMINPASS_UPDATE = 21;
    /**
     * 手机端登录
     */
    public static final int LOG_LOGIN_MOBILE=20;
    /**
     * 管理员添加
     */
    public static final int LOG_ADMIN_ADD = 22;
    /**
     * 管理员更新
     */
    public static final int LOG_ADMIN_UPDATE = 23;
    /**
     * 管理员删除
     */
    public static final int LOG_ADMIN_DELETE = 24;
    /**
     * 重置单位密码
     */
    public static final int LOG_COMPANYPASS_RESET = 25;
    
    
    /* ==================================================================== */
    /**
     * 重大危险源新增
     */
    public static final int LOG_ZDDANGER_ADD = 32;
    
    /**
     * 重大危险源修改
     */
    public static final int LOG_ZDDANGER_UPDATE = 33;
    
    /**
     * 重大危险源删除
     */
    public static final int LOG_ZDDANGER_DELETE = 34;
    
    /* ==================================================================== */
    /**
     * 题库管理新增
     */
    public static final int LOG_EQ_ADD = 47;
    
    /**
     * 题库管理修改
     */
    public static final int LOG_EQ_UPDATE = 48;
    
    /**
     * 题库管理删除
     */
    public static final int LOG_EQ_DELETE = 49;
    
    /**
     * 题库管理生效
     */
    public static final int LOG_EQ_OK = 50;
    
    /**
     * 题库管理失效
     */
    public static final int LOG_EQ_FAIL = 51;
    
    /* ==================================================================== */
    /**
     * 试卷管理新增
     */
    public static final int LOG_EP_ADD = 52;
    
    /**
     * 试卷管理生效
     */
    public static final int LOG_EP_OK = 53;
    
    /**
     * 试卷管理失效
     */
    public static final int LOG_EP_FAIL = 54;
    
    /**
     * 试卷管理删除
     */
    public static final int LOG_EP_DELETE = 55;
    
    
    
    /* ==================================================================== */
    public static final int LOG_QYXX_DELETE = 61;
   /**
     * 企业注册
     */
    public static final int LOG_QYXX_ADD = 62;
    
    /**
     * 企业信息修改
     */
    public static final int LOG_QYXX_UPDATE = 63;
    /**
     * 企业法人新增
     */
    public static final int LOG_QYLP_ADD = 64;
    
    /**
     * 企业法人修改
     */
    public static final int LOG_QYLP_UPDATE = 65;
    
    /**
     * 企业证照修改
     */
    public static final int LOG_QYZZ_UPDATE = 66;
    
    /**
     * 企业证照删除
     */
    public static final int LOG_QYZZ_DELETE = 67;
    
    
    
    /* ==================================================================== */
    /**
     * 企业产品新增
     */
    public static final int LOG_CP_ADD = 74;
    
    /**
     * 企业产品修改
     */
    public static final int LOG_CP_UPDATE = 75;
    
    /**
     * 企业产品删除
     */
    public static final int LOG_CP_DELETE = 76;
    
    /* ==================================================================== */
    /**
     * 数据字典新增
     */
    public static final int LOG_DICT_ADD = 83;
    
    /**
     * 数据字典修改
     */
    public static final int LOG_DICT_UPDATE = 84;
    
    /**
     * 数据字典删除
     */
    public static final int LOG_DICT_DELETE = 85;
    
    /* ==================================================================== */
    /**
     * 公告发布
     */
    public static final int LOG_NOTIFY1_ADD1 = 86;
    /**
     * 公告存草稿
     */
    public static final int LOG_NOTIFY1_ADD2 = 87;
    
    /**
     * 公告修改
     */
    public static final int LOG_NOTIFY1_UPDATE = 88;
    
    /**
     * 公告删除（上面不15不用了）
     */
    public static final int LOG_NOTIFY1_DELETE = 89;
    
    /**
     * 公告置顶
     */
    public static final int LOG_NOTIFY1_UP = 90;
    
    /**
     * 公告取消置顶
     */
    public static final int LOG_NOTIFY1_UNUP = 91;
    
    /**
     * 公告终止
     */
    public static final int LOG_NOTIFY1_FAIL = 92;
    
    /**
     * 公告生效
     */
    public static final int LOG_NOTIFY1_OK = 93;
    /* ==================================================================== */
    //阈值模板10XX
    public static final int LOG_YZMB_ADD = 1001;
    
    public static final int LOG_YZMB_UPDATE = 1002;
    
    public static final int LOG_YZMB_DELETE = 1003;
    /* ==================================================================== */
    /* ==================================================================== */
    //传感器12XX
    public static final int LOG_CGQ_ADD = 1201;
    
    public static final int LOG_CGQ_UPDATE = 1202;
    
    public static final int LOG_CGQ_DELETE = 1203;
    public static final int LOG_CGQ_ENABLEWARNING = 1203;
    /* ==================================================================== */
    //设备13XX
    public static final int LOG_CJSB_ADD = 1301;
    
    public static final int LOG_CJSB_UPDATE = 1302;
    
    public static final int LOG_CJSB_DELETE = 1303;
    
    public static final int LOG_CJSB_STATUS = 1303;
    /* ==================================================================== */
    //通道14XX
    public static final int LOG_TD_ADD = 1401;
    
    public static final int LOG_TD_UPDATE = 1402;
    
    public static final int LOG_TD_DELETE = 1403;
    
    public static final int LOG_TD_STATUS = 1403;
    public static final int LOG_TD_BIND = 1404;
    /* ==================================================================== */
    /* ==================================================================== */
    //组织绑定
    public static final int LOG_ZZBD_BIND =1101;
    public static final int LOG_ZZBD_UNBIND =1102;
 }
