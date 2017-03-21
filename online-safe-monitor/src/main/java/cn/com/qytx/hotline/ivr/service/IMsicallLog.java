package cn.com.qytx.hotline.ivr.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import cn.com.qytx.hotline.ivr.domain.CallQuantity;
import cn.com.qytx.hotline.ivr.domain.InQueueInfo;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.MsicallLogArea;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;


/**
*项目名称：wzerp
*类名称：通话记录服务层
*类描述：
*创建人：WangBin
*创建时间：2012-11-22
*@version
 */
public interface IMsicallLog extends BaseService<MsicallLog> {

	/**
	 *@Title:查询通话记录分页记录
	 *@Description:
	 *@param @param pageInfo
	 *@param @param call
	 *@param @param callType
	 *@param @param begDate
	 *@param @param endDate
	 *@param @return
	 *@return  返回类型
	 *@throws
	 */
	Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String call,
			int callType, String begDate, String endDate);
	
	Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String searchkey,
			int callType, String begDate, String endDate, String calledNum,UserInfo userInfo);

	/**
	 *@Title:获取通话记录分页总记录数
	 *@Description:
	 *@param @param call
	 *@param @param parseInt
	 *@param @param begDate
	 *@param @param endDate
	 *@param @return
	 *@return  返回类型
	 *@throws
	 */
	int getTotalCount(String call, int callType, String begDate, String endDate);

	MsicallLog findById(Class<MsicallLog> arg0, Integer vid);
	
	/**
	 *@Title:查询通话记录分页记录
	 *@Description:
	 *@param @param pageInfo
	 *@param @param call
	 *@param @param workNo
	 *@param @param begDate
	 *@param @param endDate
	 *@param @return
	 *@return  返回类型
	 *@throws
	 */
	Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String call,
			 String workNo, String begDate, String endDate, UserInfo userInfo, String cclSn);
	
	/**
	 * 功能：分天统计话务量综合统计表 
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param queueId 队列Id
	 * @return List<CallQuantity>
	 */
	List<CallQuantity> findCallQuantity(Timestamp beginTime, Timestamp endTime, Integer queueId,Integer isForkGroup);
	/**
	 * 功能：分天统计话务量综合统计表 
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param queueId 队列Id
	 * @return List<CallQuantity>
	 */
	List<CallQuantity> findCallQuantityNew(Timestamp beginTime, Timestamp endTime, Integer queueId,Integer isForkGroup,Integer companyId);
	
	   
    /**
     * 功能：根据时间段分队列统计呼损原因
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param queueId 队列Id
     * @param callphone 主叫电话
     * @param queueId 被叫电话
     * @return Page<CallQuantity> 结果信息
     */
    Page<MsicallLog> findCallBlockList(Pageable pageInfo, Timestamp beginTime, Timestamp endTime, Integer queueId, String searchkey);

    /**
     * 功能：分时段统计排队信息
     * @param beginTime统计开始时间
     * @param endTime统计结束时间
     * @param queueId队列Id
     * @return List<InQueueInfo>
     */
    List<InQueueInfo> findInQueueInfo(Timestamp beginTime, Timestamp endTime, Integer queueId,Integer groupId,Integer isForkGroup,Integer companyId);

    List<String> listFileUrls(String msiIds,String basePath);

    /**
     * 
     * 功能：获得来电时段分布表
     * @param beginTime
     * @param endTime
     * @param isFormGroup 权限
     * @return
     */
	List<Map<String, Object>> findTimeFrame(Timestamp beginTime,Timestamp endTime,Integer isFormGroup ,Integer companyId);
	
	/**
	 * 功能：来电区域统计表
	 * @param beginTime：开始时间
	 * @param endTime：结束时间
	 * @param isForkGroup：区域
	 * @return
	 */
	List<MsicallLogArea> findCallInByArea(String beginTime,String endTime,Integer isForkGroup ,Integer companyId);

}
