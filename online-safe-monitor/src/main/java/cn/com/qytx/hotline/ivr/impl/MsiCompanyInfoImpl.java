package cn.com.qytx.hotline.ivr.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.MsiCompanyInfoDao;
import cn.com.qytx.hotline.ivr.domain.MsiCompanyInfo;
import cn.com.qytx.hotline.ivr.service.IMsiCompanyInfo;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * User: 黄普友 Date: 12-11-1 Time: 上午12:09
 */
@Service
@Transactional
public class MsiCompanyInfoImpl extends BaseServiceImpl<MsiCompanyInfo>
		implements IMsiCompanyInfo {

	@Autowired
	private MsiCompanyInfoDao msiCompanyInfoDao;

	/**
	 * 根据id获取公司信息
	 * 
	 * @param vid
	 * @return CompanyInfo
	 */
	public MsiCompanyInfo findByVid(Integer vid) {
		return msiCompanyInfoDao.findByVid(vid);
	}

	public MsiCompanyInfoDao getMsiCompanyInfoDao() {
		return msiCompanyInfoDao;
	}

	public void setMsiCompanyInfoDao(MsiCompanyInfoDao msiCompanyInfoDao) {
		this.msiCompanyInfoDao = msiCompanyInfoDao;
	}

}
