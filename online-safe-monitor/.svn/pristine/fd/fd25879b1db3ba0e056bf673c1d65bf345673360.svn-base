package cn.com.qytx.hotline.ivr.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.SendSumSmsDao;
import cn.com.qytx.hotline.ivr.domain.SmsSumRecord;
import cn.com.qytx.hotline.ivr.service.ISendSumSms;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 
 * 功能: 版本: 1.0 开发人员: 徐长江 创建日期: 2013-7-17 修改日期: 2013-7-17 修改列表:
 */
@SuppressWarnings("serial")
@Service
@Transactional
public class SendSumSmsImpl extends BaseServiceImpl<SmsSumRecord> implements
		ISendSumSms, Serializable {

	/** DAO */
	@Autowired
	SendSumSmsDao sendSumSmsDao;

	/**
	 * 
	 * 功能：发送短信
	 * 
	 * @param smsRecrod
	 */
	@Override
	public void sendSms(SmsSumRecord smsSumRecrod) {
		sendSumSmsDao.sendSms(smsSumRecrod);
	}

	@Override
	public Page<SmsSumRecord> findCCLByPage(Pageable page, String searchkey,
			Timestamp sendStartTime, Timestamp sendEndTime,
			Integer customerCallLogId) {
		return sendSumSmsDao.findCCLByPage(page, searchkey, sendStartTime,
				sendEndTime, customerCallLogId);
	}

}
