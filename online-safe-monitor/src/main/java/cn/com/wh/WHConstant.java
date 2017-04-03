package cn.com.wh;

/**
 * 功能: 常量说明
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2015年8月11日
 * 修改日期: 2015年8月11日
 * 修改列表:
 */
public interface WHConstant
{ 
	//系统名称，例如菜单等支持多系统的需要传递系统名称
	public static final Integer DICT_SYS_TAG =2;  //本系统的数据字典标识的系统标记 
	public static final String SYS_NAME="osm";    //主菜单的系统标记
    public static final String MODULE_SYSTEMMANAGE="osm_systemmanage";
	
	/**
     * 超级管理员角色代码
     */
    public static final String ADMIN = "admin";
    
    /**
     * 角色类型，可能会根据不通的角色类型做不同的处理
     * @author WYG
     *
     */
    public class WHROLETYPE{
    	public static String WHROLETYPE="whroletype";//Session中表示角色类型的常量
    	public static int ADMIN=0;   //这个根据角色是否包含ADMIN来获得
    	public static int MANAGE=1;  //这个可以根据用户的groupInfo,Type=1来获得
    	public static int ENTERPRIS=2; //这个可以根据用户的groupInfo,Type=2来获得
    }
    
    public class SESSIONCONSTANTS{
    	public static String COMPANY_NAME="companyName";
    }
}
