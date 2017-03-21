package cn.com.qytx.oa.util;

import java.util.List;

import cn.com.qytx.platform.security.SystemContextHolder;

public class DataFilterUtilForSql {

	   /**
     * 如果用户拥有ID为26的角色，则可以查看全部，否则只能查看本部门的信息
     * ID为26的角色目前只有超级管理员拥有，其他人没有
     * 生成SQL格式的数据权限过滤
     * @return
     */
    public static String sqlDataFilter(){
    	String sql = "and ( is_fork_group = "+SystemContextHolder.getSessionContext().getUser().getIsForkGroup();
    	List<String> relations = SystemContextHolder.getSessionContext().getRelationIds();
    	if(relations.contains("role_26")){
    		sql+=" or is_fork_group is not null ";
    	}
    	sql+=")";
    	return sql;
    }
}
