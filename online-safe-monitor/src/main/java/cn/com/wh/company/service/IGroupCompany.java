package cn.com.wh.company.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.company.domain.GroupCompany;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 阈值模板接口类
 * @创建时间 2017-04-03
 * @修改时间
 * @修改列表
 */
public interface IGroupCompany extends BaseService<GroupCompany> {
	/**
	 * 获得某单位绑定的单位的群组列表
	 * @param groupId
	 * @return
	 */
	public String getGroupIds(Integer groupId);
	/**
	 * 批量插入
	 * @param groupId
	 * @param groupIds
	 */
	public void bulckInsert(Integer groupId,String groupIds);
	/**
	 * 获得可绑定的树
	 * @param groupId
	 * @return
	 */
	public List<TreeNode> getBindSelectNode(UserInfo userInfo,
			String path,Integer groupId,String groupTypeIn);
}
