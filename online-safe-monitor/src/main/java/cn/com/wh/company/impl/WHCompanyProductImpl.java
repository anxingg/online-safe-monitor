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
import cn.com.wh.company.dao.WHCompanyProductDao;
import cn.com.wh.company.domain.WHCompanyProduct;
import cn.com.wh.company.service.IWHCompanyProduct;

/**
 * 功能: 企业产品
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class WHCompanyProductImpl extends BaseServiceImpl<WHCompanyProduct> implements IWHCompanyProduct,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(WHCompanyProductImpl.class);
	
	/**
	 *  企业产品   DAO类
	 */
	@Autowired
	private WHCompanyProductDao wHCompanyProductDao;
	
	
	public Page<WHCompanyProduct> findByPage(Pageable pageable,Integer groupId,Integer materialType,String materialName){
		return wHCompanyProductDao.findByPage(pageable, groupId, materialType,materialName);
	}
	
}
