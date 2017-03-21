package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.SmsSumRecord;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 
 * 功能:发送短信 版本: 1.0 开发人员: 徐长江 创建日期: 2013-7-27 修改日期: 2013-7-27 修改列表:
 */
@SuppressWarnings("serial")
@Component
public class SendSumSmsDao extends BaseDao<SmsSumRecord, Integer> implements Serializable {

	/**
	 * 功能：发送短信
	 * 
	 * @param smsRecrod
	 */
	public void sendSms(SmsSumRecord smsSumRecord) {
		super.saveOrUpdate(smsSumRecord);
	}

	/**
	 * 
	 * @param page
	 * @param customerCallLogId
	 * @return
	 */
	public Page<SmsSumRecord> findCCLByPage(Pageable page, String searchkey,
			Timestamp sendStartTime, Timestamp sendEndTime,
			Integer customerCallLogId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" 1=1 ");
		List<Object> params = new ArrayList<Object>();

		/** 工单id */
		if (customerCallLogId != null) {
			hql.append(" and customerCallLog.vid = ?");
			params.add(customerCallLogId);
		}
		/** 发送开始时间 */
		if (null != sendStartTime) {
			hql.append(" and sendTime >?");
			params.add(sendStartTime);
		}

		/** 发送结束时间 */
		if (null != sendEndTime) {
			hql.append(" and sendTime >?");
			params.add(sendEndTime);
		}
		if (!StringUtils.isEmpty(searchkey)) {
			hql.append(" and ( userInfo.userName like ? or phone like ? )");
			params.add("%" + searchkey + "%");
			params.add("%" + searchkey + "%");
		}
		if (null != params && params.size() > 0) {
			return this.findAll(hql.toString(), page, params.toArray());
		} else {
			return this.findAll(hql.toString(), page);
		}
	}

}
