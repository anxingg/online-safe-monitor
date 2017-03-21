package cn.com.qytx.hotline.mis.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.mis.dao.PhoneAttributionDao;
import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.hotline.mis.service.IPhoneAttribution;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:号码段维护实现类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-17
 * 修改日期: 2014-12-17
 * 修改列表:
 */
@Service
@Transactional
public class PhoneAttributionImpl extends BaseServiceImpl<PhoneAttribution> implements IPhoneAttribution,Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -3173317831620558248L;
	@Autowired
	PhoneAttributionDao phoneAttributionDao;
	
	
	/**
	 * 功能：分页查询号码段维护表
	 * @param page
	 * @param searchKey
	 * @return
	 */
	@Override
	public Page<PhoneAttribution> findByPage(Pageable page, String searchKey) {
		return phoneAttributionDao.findByPage(page, searchKey);
	}


	/**
	 * 功能：根据手机号码段查询
	 * @param mobileNumber
	 * @return
	 */
	@Override
	public PhoneAttribution findByMobileNumber(Integer id, String mobileNumber) {
		return phoneAttributionDao.findByMobileNumber(id, mobileNumber);
	}
	

	@Override
	public PhoneAttribution findById(Integer id){
		return phoneAttributionDao.findById(id);
	}
}
