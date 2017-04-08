package cn.com.wh.company.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.GroupInfo;
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
	@Override
	public String getCompanyIds(Integer groupId)
	{
		List<GroupCompany> groupCompanyList=groupCompanyDao.findGroupCompany(groupId);
		StringBuilder inStr=new StringBuilder();
		for(GroupCompany groupCompany  : groupCompanyList){
			inStr.append(",");
			inStr.append(groupCompany.getCompanyId());
		}
		if(inStr.length()>0)
			inStr.delete(0, 1);
		return inStr.toString();
	}

}
