package cn.com.qytx.platform.session;

public class Constants {
	public static String DOWN_PATH="downPath";	//文件下载路径
	public static String CURRENT_LOGIN_USER="adminUser";	//当前登录用户
	public static String CURRENT_LOING_USERX="adminUserXC";	//与旧代码的兼容
	public static String CURRENT_LOGIN_COMPANY="companyInfo"; //当前登录用户的企业信息
	public static String CURRENT_LOGIN_MODULELIST="moduleList"; //当前登录用户所拥有的模块列表
	//用户所属部门
    public final static String CURRENT_USER_GROUP = "usergroup";
    //用户部门的子部门集合
    public final static String CURRENT_USER_SUBGROUPS = "usersubgroups";
    //用户的角色集合
    public final static String CURRENT_USER_ROLES = "userroles";
    
    public final static String SSO_TOKEN ="sso_token";
}
