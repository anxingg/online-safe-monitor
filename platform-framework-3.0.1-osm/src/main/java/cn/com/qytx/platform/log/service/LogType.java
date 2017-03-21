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
     * 安全生产事故新增
     */
    public static final int LOG_SAFEACCIDENT_ADD = 26;
    
    /**
     * 安全生产事故修改
     */
    public static final int LOG_SAFEACCIDENT_UPDATE = 27;
    
    /**
     * 安全生产事故删除
     */
    public static final int LOG_SAFEACCIDENT_DELETE = 28;
    
    /* ==================================================================== */
    /**
     * 危险化学品新增
     */
    public static final int LOG_CDANGERH_ADD = 29;
    
    /**
     * 危险化学品修改
     */
    public static final int LOG_CDANGERH_UPDATE = 30;
    
    /**
     * 危险化学品删除
     */
    public static final int LOG_CDANGERH_DELETE = 31;
    
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
     * 安全隐患排查新增
     */
    public static final int LOG_AQYH_ADD = 35;
    
    /**
     * 安全隐患排查修改
     */
    public static final int LOG_AQYH_UPDATE = 36;
    
    /**
     * 安全隐患排查删除
     */
    public static final int LOG_AQYH_DELETE = 37;
    
    /* ==================================================================== */
    /**
     * 年度培训新增
     */
    public static final int LOG_YDPX_ADD = 38;
    
    /**
     * 年度培训修改
     */
    public static final int LOG_YDPX_UPDATE = 39;
    
    /**
     * 年度培训删除
     */
    public static final int LOG_YDPX_DELETE = 40;
    
    /* ==================================================================== */
    /**
     * 安全培训记录新增
     */
    public static final int LOG_AQPX_ADD = 41;
    
    /**
     * 安全培训记录修改
     */
    public static final int LOG_AQPX_UPDATE = 42;
    
    /**
     * 安全培训记录删除
     */
    public static final int LOG_AQPX_DELETE = 43;
    
    /* ==================================================================== */
    /**
     * 岗前三级培训新增
     */
    public static final int LOG_GQSJPX_ADD = 44;
    
    /**
     * 岗前三级培训修改
     */
    public static final int LOG_GQSJPX_UPDATE = 45;
    
    /**
     * 岗前三级培训删除
     */
    public static final int LOG_GQSJPX_DELETE = 46;
    
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
    /**
     * 应急演练新增
     */
    public static final int LOG_YJYL_ADD = 56;
    
    /**
     * 应急演练修改
     */
    public static final int LOG_YJYL_UPDATE = 57;
    
    /**
     * 应急演练删除
     */
    public static final int LOG_YJYL_DELETE = 58;
    
    /* ==================================================================== */
    /**
     * 应急预案新增
     */
    public static final int LOG_YJYA_ADD = 59;
    
    /**
     * 应急预案修改
     */
    public static final int LOG_YJYA_UPDATE = 60;
    
    /**
     * 应急预案删除
     */
    public static final int LOG_YJYA_DELETE = 61;
    
    /* ==================================================================== */
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
     * 特种作业人员新增
     */
    public static final int LOG_SWP_ADD = 68;
    
    /**
     * 特种作业人员修改
     */
    public static final int LOG_SWP_UPDATE = 69;
    
    /**
     * 特种作业人员删除
     */
    public static final int LOG_SWP_DELETE = 70;
    
    /* ==================================================================== */
    /**
     * 安全管理人员新增
     */
    public static final int LOG_SMP_ADD = 71;
    
    /**
     * 安全管理人员修改
     */
    public static final int LOG_SMP_UPDATE = 72;
    
    /**
     * 安全管理人员删除
     */
    public static final int LOG_SMP_DELETE = 73;
    
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
     * 危险化学品新增
     */
    public static final int LOG_DANGERH_ADD = 77;
    
    /**
     * 危险化学品修改
     */
    public static final int LOG_DANGERH_UPDATE = 78;
    
    /**
     * 危险化学品删除
     */
    public static final int LOG_DANGERH_DELETE = 79;
    
    /* ==================================================================== */
    /**
     * 职业卫生专家新增
     */
    public static final int LOG_ZJ1_ADD = 80;
    
    /**
     * 职业卫生专家修改
     */
    public static final int LOG_ZJ1_UPDATE = 81;
    
    /**
     * 职业卫生专家删除
     */
    public static final int LOG_ZJ1_DELETE = 82;
    
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
    /**
     * 政策法规发布
     */
    public static final int LOG_NOTIFY2_ADD1 = 94;
    /**
     * 政策法规存草稿
     */
    public static final int LOG_NOTIFY2_ADD2 = 95;
    
    /**
     * 政策法规修改
     */
    public static final int LOG_NOTIFY2_UPDATE = 96;
    
    /**
     * 政策法规删除（上面不15不用了）
     */
    public static final int LOG_NOTIFY2_DELETE = 97;
    
    /**
     * 政策法规置顶
     */
    public static final int LOG_NOTIFY2_UP = 98;
    
    /**
     * 政策法规取消置顶
     */
    public static final int LOG_NOTIFY2_UNUP = 99;
    
    /**
     * 政策法规终止
     */
    public static final int LOG_NOTIFY2_FAIL = 100;
    
    /**
     * 政策法规生效
     */
    public static final int LOG_NOTIFY2_OK = 101;
    
    /* ==================================================================== */
    /**
     * 危险化学品目录新增
     */
    public static final int LOG_CHEMICALSDIR_ADD = 102;
    
    /**
     * 危险化学品目录修改
     */
    public static final int LOG_CHEMICALSDIR_UPDATE = 103;
    
    /**
     * 危险化学品目录删除
     */
    public static final int LOG_CHEMICALSDIR_DELETE = 104;
    
    
    /**
     * 应急机构管理  新增
     */
    public static final int LOG_YJ_ADD = 105;
    
    /**
     * 应急机构管理 修改
     */
    public static final int LOG_YJ_UPDATE = 106;
    
    /**
     * 应急机构管理 删除
     */
    public static final int LOG_YJ_DELETE = 107;
    
    /**
     * 救援物资  新增
     */
    public static final int LOG_JYWZ_ADD = 108;
    
    /**
     * 救援物资 修改
     */
    public static final int LOG_JYWZ_UPDATE = 109;
    
    /**
     * 救援物资 删除
     */
    public static final int LOG_JYWZ_DELETE = 110;
    
    /**
     * 安全管理机构修改
     */
    public static final int LOG_AQGLJG_UPDATE = 111;
    
    /**
     * 工艺流程  新增
     */
    public static final int LOG_GYLC_ADD = 112;
    
    /**
     * 工艺流程 修改
     */
    public static final int LOG_GYLC_UPDATE = 113;
    
    /**
     * 工艺流程 删除
     */
    public static final int LOG_GYLC_DELETE = 114;
    
    /**
     * 重大危险源危化品目录对象  新增
     */
    public static final int LOG_DSG_ADD = 115;
    
    /**
     * 重大危险源危化品目录对象 修改
     */
    public static final int LOG_DSG_UPDATE = 116;
    
    /**
     * 重大危险源危化品目录对象 删除
     */
    public static final int LOG_DSG_DELETE = 117;
    
    /**
     * 安全生产费用提取新增
     */
    public static final int LOG_FEEEXTRACT_ADD = 118;
    
    /**
     * 安全生产费用使用新增
     */
    public static final int LOG_FEEUSED_ADD = 119;
    
    /* ==================================================================== */
    /**
     * 非煤矿山专家新增
     */
    public static final int LOG_ZJ2_ADD = 120;
    
    /**
     * 非煤矿山专家修改
     */
    public static final int LOG_ZJ2_UPDATE = 121;
    
    /**
     * 非煤矿山专家删除
     */
    public static final int LOG_ZJ2_DELETE = 122;
    
    /* ==================================================================== */
    /**
     * 危险化学品专家新增
     */
    public static final int LOG_ZJ3_ADD = 123;
    
    /**
     * 危险化学品专家修改
     */
    public static final int LOG_ZJ3_UPDATE = 124;
    
    /**
     * 危险化学品专家删除
     */
    public static final int LOG_ZJ3_DELETE = 125;
 }
