package cn.com.qytx.platform.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.org.domain.ModuleInfo;
@Repository
public class ModuleForHotlineDao extends BaseDao<ModuleInfo,Integer> implements Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -4912830618543395485L;

	/**
	 * 逻辑删除模块功能，满足定制需求
	 * 蔡明雪 2014-04-08
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void updateModuleByURL(String url,Integer flag){
		String sql="";
		if(flag==0){
			sql = "update ModuleInfo set isDelete = 1 where url = '" + url + "'";
		}else{
		sql = "update ModuleInfo set isDelete = 0 where url = '" + url + "'";
		}
        super.bulkUpdate(sql);
        
	}
}
