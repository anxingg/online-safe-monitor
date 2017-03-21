package cn.com.qytx.oa.news.util;
/**
 * 
 * <br/>功能: 人员处理
 * <br/>版本: 1.0
 * <br/>开发人员: REN
 * <br/>创建日期: 2014-6-30
 * <br/>修改日期: 2014-6-30
 * <br/>修改列表:
 */
public class UserHandle {
	/**
	 * 功能：得到加过前缀的人员字符串
	 * @param prefix
	 * @param userIds
	 * @return
	 */
	public static String findPushUser(String prefix,String userIds){
		StringBuilder sb=new StringBuilder();
		userIds=userIds.replaceAll("^,|,$", "");
		String[] userIdArr=userIds.split(",");
		for (String userId : userIdArr) {
			sb.append(prefix+userId+",");
		}
		return sb.toString().replaceAll("^,|,$", "");
	}
}
