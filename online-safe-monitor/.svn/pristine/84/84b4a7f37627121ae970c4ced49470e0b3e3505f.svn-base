package cn.com.qytx.hotline.org.dao;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.org.domain.GroupInfo;

/**
 * 功能:部门dao
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015年4月9日
 * 修改人员：
 * 修改日期: 
 * 修改列表:
 */
@Component
public class HotlineGroupDao extends BaseDao<GroupInfo, Integer> implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1346586092739546750L;
	
	/**
	 * 功能：更具部门名称查询部门信息
	 * 开发人员:张东领
	 * 创建日期:2015年4月9日
	 * 修改人员:
	 * 修改日期:
	 * @param groupName
	 * @return
	 */
	public GroupInfo findByName(String groupName){
		String hql = " isDelete = 0 ";
		if(StringUtils.isNotBlank(groupName)){
			hql += " and groupName = '"+groupName+"' ";
		}
		return super.findOne(hql);
	}

}
