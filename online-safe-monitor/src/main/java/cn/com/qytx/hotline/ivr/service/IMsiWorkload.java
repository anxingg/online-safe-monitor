package cn.com.qytx.hotline.ivr.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.SeatWorkload;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 功能: 坐席工作状态统计信息
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-8-20
 * 修改日期: 2013-8-20
 * 修改列表:
 */

public interface IMsiWorkload extends BaseService<MsiWorkload>
{


    /**
     * 功能：根据状态统计坐席工作量
     * @return List<MsiWorkload>
     */
    List<MsiWorkload> findMsiWorkloadGroupByState(Timestamp beginTime, Timestamp endTime, Integer companyId);
    
    /**
     * 功能：根据时间队列统计坐席工作量
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param queueId 队列Id
     * @return 结果信息
     */
    Page<SeatWorkload> findSeatWorkloadGroupByState(Pageable page, Timestamp beginTime, Timestamp endTime, Integer queueId, Integer groupId,String workIds,Integer isForkGroup,Integer companyId);

    /**
     * 功能：根据时间队列统计坐席工作量
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param queueId 队列Id
     * @param workIds  坐席id
     * @return 结果信息
     */
	Map<String,SeatWorkload>  findSeatWorkloadGroupByDayState(
			Page<SeatWorkload> pageInfo, Timestamp beginTime,
			Timestamp endTime, Integer queueId, Integer groupId, String workIds);
    
    MsiWorkload getMsiWorkload(Integer msiUserID,Integer msiState);
    
   

}
