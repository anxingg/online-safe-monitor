package cn.com.wh.collectsensor.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.collectsensor.domain.CollectSensor;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;




public interface ICollectSensor extends BaseService<CollectSensor> {

	/**
	 * 根据危险源ID获得传感器列表
	 * @param 危险源ID
	 * @param notInStr 不在的传感器列表
	 * @return
	 */
	public Page<CollectSensor> findCollectSensorList(Pageable pageable,Integer dangerSourceId);
	/**
	 * 根据危险源ID获得传感器列表
	 * @param 危险源ID
	 * @return
	 */
	public List<CollectSensor> findCollectSensorList(Integer dangerSourceId,String notInStr);
	
	/**
	 * 创建查询树
	 * @return
	 */
	public List<TreeNode> createSearchTree(UserInfo userInfo);
	/**
	 * 全部传感器统计
	 * @param groupId 区域ID
	 */
	public Map<String,Integer> stReportByAll();
	/**
	 * 区域传感器统计
	 * @param groupId 区域ID
	 */
	public Map<String,Integer> stReportByArea(Integer groupId);
	/**
	 * 企业传感器统计
	 * @param groupId 企业GroupID
	 */
	public Map<String,Integer> stReportByCompany(Integer groupId);
}
