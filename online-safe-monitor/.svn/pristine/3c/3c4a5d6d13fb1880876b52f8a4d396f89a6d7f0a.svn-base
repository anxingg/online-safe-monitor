package cn.com.qytx.hotline.ivr.service;

import java.sql.Timestamp;

import cn.com.qytx.hotline.ivr.domain.SmsSumRecord;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
/**
 * 
 * 功能:发送短信的接口
 * 版本: 1.0
 * 开发人员: 徐长江
 * 创建日期: 2013-7-27
 * 修改日期: 2013-7-27
 * 修改列表:
 */

public interface ISendSumSms extends BaseService<SmsSumRecord> {
   /**
    * 
    * 功能：发送短信
    * @param smsRecrod
    */
	void sendSms(SmsSumRecord smsSumRecrod);
	
	
	/**
     * 
     * @param page
     * @param customerCallLogId
     * @return
     */
    Page<SmsSumRecord> findCCLByPage(Pageable page,String searchkey,Timestamp sendStartTime,Timestamp sendEndTime,Integer customerCallLogId);
	
}
