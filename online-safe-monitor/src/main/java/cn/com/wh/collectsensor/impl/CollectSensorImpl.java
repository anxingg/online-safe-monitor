package cn.com.wh.collectsensor.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.com.wh.collectsensor.dao.CollectSensorDao;
import cn.com.wh.collectsensor.domain.CollectSensor;
import cn.com.wh.collectsensor.service.ICollectSensor;
import cn.com.wh.company.dao.WHCompanyDao;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.dangersource.dao.DangerSourceDao;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.dangersource.service.IDangerSource;
import cn.com.wh.thresholdtemplate.dao.ThresholdTemplateDao;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;
import cn.com.wh.thresholdtemplate.service.IThresholdTemplate;

/**
 * 功能: 传感器
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class CollectSensorImpl extends BaseServiceImpl<CollectSensor> implements ICollectSensor,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(CollectSensorImpl.class);
	
	@Autowired
	private CollectSensorDao collectSensorDao;

	@Resource(name="dangerSourceImpl")
	public IDangerSource dangerSourceService;
	
	@Autowired
	private GroupDao groupDao;
	@Resource
    private CompanyDao<CompanyInfo> companyDao;
	
	@Resource
	private DangerSourceDao dangerSourceDao;
	@Override
	public Page<CollectSensor> findCollectSensorList(Pageable pageable,Integer dangerSourceId)
	{
		return collectSensorDao.findCollectSensorList(pageable,dangerSourceId);
	}
	@Override
	public List<CollectSensor> findCollectSensorList(Integer dangerSourceId,String notInStr) {
		// TODO Auto-generated method stub
		return collectSensorDao.findCollectSensorList(dangerSourceId,notInStr);
	}
	
	@Override
	public List<TreeNode> createSearchTree(UserInfo userInfo) {
		// TODO Auto-generated method stub
		CompanyInfo companyInfo = companyDao.findOne(userInfo.getCompanyId());
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		TreeNode treeNodeAll = new TreeNode();
    	treeNodeAll.setId("gid_0");//全部传感器
    	treeNodeAll.setName("全部传感器");
    	treeNodeAll.setTitle("全部传感器");
    	treeNodeAll.setPId("all_-1");
    	treeNodeAll.setOpen(false);
    	treeNodes.add(treeNodeAll);
    	List<GroupInfo> groupList=groupDao.getGroupList(companyInfo.getCompanyId(), "0,2", (Integer)1, null);
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
                treeNode.setOpen(true);
                treeNodes.add(treeNode);
            }
        }
    	List<DangerSource> dangerSourceList=dangerSourceDao.findAll(" isDelete = 0 ");
    	if (dangerSourceList != null)
        {
            // 遍历部门
            for (DangerSource dangerSource : dangerSourceList)
            {
                TreeNode treeNode = new TreeNode();
                treeNode.setId("did_" + dangerSource.getId());
                treeNode.setName(dangerSource.getDangerSourceName());
                treeNode.setTitle(dangerSource.getDangerSourceName());
                treeNode.setPId("gid_" + dangerSource.getCompany().getGroupId());
                treeNode.setOpen(false);
                treeNodes.add(treeNode);
            }
        }
    	return treeNodes;
	}
	/**
	 * 全部传感器统计
	 * @param groupId 区域ID
	 */
	@Override
	public Map<String,Integer> stReportByAll()
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		Integer using=collectSensorDao.countCollectSensorByIds(1,null,null,null,null,null,null);
		map.put("collectSensor_Using", using);
		Integer stoped=collectSensorDao.countCollectSensorByIds(0,null,null,null,null,null,null);
		map.put("collectSensor_Stoped", stoped);
		Integer all=collectSensorDao.countCollectSensorByIds(null,null,null,null,null,null,null);
		map.put("collectSensor_All", all);
		//获取当前月第一天：
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        Date beginDate=c.getTime();
        
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        Date endDate=c.getTime();
        
		
		Integer monthNew=collectSensorDao.countCollectSensorByIds(null,beginDate,endDate,null,null,null,null);
		map.put("collectSensor_MonthNew", monthNew);
		return map;
	}
	/**
	 * 区域传感器统计
	 * @param groupId 区域ID
	 */
	@Override
	public Map<String,Integer> stReportByArea(Integer groupId)
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		String groupIds=groupDao.getSubGroupIds(groupId,"2");
		if(!StringUtils.isEmpty(groupIds)){ //可能是企业
			groupIds=groupId.toString();
		}
		Integer using=collectSensorDao.countCollectSensorByIds(1,null,null,null,null,null,groupIds);
		map.put("collectSensor_Using", using);
		Integer stoped=collectSensorDao.countCollectSensorByIds(0,null,null,null,null,null,groupIds);
		map.put("collectSensor_Stoped", stoped);
		Integer all=collectSensorDao.countCollectSensorByIds(null,null,null,null,null,null,groupIds);
		map.put("collectSensor_All", all);
		//获取当前月第一天：
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        Date beginDate=c.getTime();
        
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        Date endDate=c.getTime();
        
		
		Integer monthNew=collectSensorDao.countCollectSensorByIds(null,beginDate,endDate,null,null,null,groupIds);
		map.put("collectSensor_MonthNew", monthNew);
		return map;
	}
	/**
	 * 企业传感器统计
	 * @param groupId 企业GroupID
	 */
	@Override
	public Map<String,Integer> stReportByCompany(Integer groupId)
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		String groupIds=groupId.toString();
		Integer using=collectSensorDao.countCollectSensorByIds(1,null,null,null,null,null,groupIds);
		map.put("collectSensor_Using", using);
		Integer stoped=collectSensorDao.countCollectSensorByIds(0,null,null,null,null,null,groupIds);
		map.put("collectSensor_Stoped", stoped);
		Integer all=collectSensorDao.countCollectSensorByIds(null,null,null,null,null,null,groupIds);
		map.put("collectSensor_All", all);
		//获取当前月第一天：
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        Date beginDate=c.getTime();
        
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        Date endDate=c.getTime();
        
		
		Integer monthNew=collectSensorDao.countCollectSensorByIds(null,beginDate,endDate,null,null,null,groupIds);
		map.put("collectSensor_MonthNew", monthNew);
		return map;
	}
}
