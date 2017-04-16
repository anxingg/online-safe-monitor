package cn.com.wh.company.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.CompanyDao;
import cn.com.qytx.platform.org.dao.GroupDao;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.company.dao.GroupCompanyDao;
import cn.com.wh.company.domain.GroupCompany;
import cn.com.wh.company.service.IGroupCompany;
import cn.com.wh.thresholdtemplate.dao.ThresholdTemplateDao;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;
import cn.com.wh.thresholdtemplate.service.IThresholdTemplate;

/**
 * 功能: 企业产品
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class GroupCompanyImpl extends BaseServiceImpl<GroupCompany> implements IGroupCompany,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(GroupCompanyImpl.class);
	
	@Autowired
	private GroupCompanyDao groupCompanyDao;
	@Autowired
	private GroupDao groupDao;
	@Resource
    private CompanyDao<CompanyInfo> companyDao;
	@Override
	public String getGroupIds(Integer groupId)
	{
		List<GroupCompany> groupCompanyList=groupCompanyDao.findGroupCompany(groupId);
		StringBuilder inStr=new StringBuilder();
		for(GroupCompany groupCompany  : groupCompanyList){
			inStr.append(",");
			inStr.append(groupCompany.getCompanyGroupId());
		}
		if(inStr.length()>0)
			inStr.delete(0, 1);
		return inStr.toString();
	}
	public void bulckInsert(Integer groupId,String groupIds)
	{
		if(!StringUtils.isEmpty(groupIds)){
			String [] groupIdArray=groupIds.split(",");
			for(String companyGroupId:groupIdArray){
				if(!StringUtils.isEmpty(groupIds)){
					GroupCompany groupCompany = new GroupCompany();
					groupCompany.setCompanyGroupId(Integer.parseInt(companyGroupId));
					groupCompany.setGroupId(groupId);
					groupCompany.setCompanyId(1);
					groupCompany.setIsDelete(0);
					this.saveOrUpdate(groupCompany);
				}
			}
		}
	}
	public List<TreeNode> getBindSelectNode(UserInfo userInfo,
			String path,Integer groupId,String groupTypeIn)
	{
		CompanyInfo companyInfo = companyDao.findOne(userInfo.getCompanyId());
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		String groupIds=getGroupIds(groupId);
		List<GroupInfo> groupList=groupDao.
				getGroupListNotIn(userInfo.getCompanyId(),groupTypeIn,groupIds);
		TreeNode treeHead = new TreeNode();
    	treeHead.setId("gid_0");//部门ID前加gid表示类型为部门
    	treeHead.setName(StringUtils.isBlank(companyInfo.getShortName())?companyInfo.getCompanyName():companyInfo.getShortName());
      	treeHead.setTitle(StringUtils.isBlank(companyInfo.getShortName())?companyInfo.getCompanyName():companyInfo.getShortName());
    	treeHead.setPId("gid_-1");
    	treeHead.setIcon(path + "/images/company.png");
    	treeHead.setOpen(true);
    	treeNodes.add(treeHead);
    	if (groupList != null)
        {
            // 遍历部门
            for (GroupInfo group : groupList)
            {
                TreeNode treeNode = new TreeNode();
                treeNode.setId("gid_" + group.getGroupId().toString());// 部门ID前加gid表示类型为部门
                treeNode.setName(group.getGroupName());
                treeNode.setTitle(group.getGroupName());
                treeNode.setObj(group.getOrderIndex());
                treeNode.setPId("gid_" + group.getParentId().toString());
                treeNode.setIcon(path + "/images/group.png");
                treeNodes.add(treeNode);
            }
        }
    	return treeNodes;
	}
}
