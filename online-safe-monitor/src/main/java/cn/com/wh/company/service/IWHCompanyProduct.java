package cn.com.wh.company.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.company.domain.WHCompanyProduct;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 IWHCompanyProduct接口类
 * @创建时间 2015-08-25
 * @修改时间
 * @修改列表
 */
public interface IWHCompanyProduct extends BaseService<WHCompanyProduct> {

	/**
	 * 分页查询
	 * @param pageable
	 * @param groupId
	 * @param materialType
	 * @param materialName
	 * @return
	 */
	public Page<WHCompanyProduct> findByPage(Pageable pageable,Integer groupId,Integer materialType,String materialName);
	
}
