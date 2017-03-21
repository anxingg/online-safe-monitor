package cn.com.qytx.hotline.mis.service;

import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 功能:号码段维护接口
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-17
 * 修改日期: 2014-12-17
 * 修改列表:
 */
public interface IPhoneAttribution extends BaseService<PhoneAttribution> {

	/**
	 * 功能：分页查询号码段维护表
	 * @param page
	 * @param searchKey
	 * @return
	 */
	Page<PhoneAttribution> findByPage(Pageable page,String searchKey);
	/**
	 * 功能：根据手机号码段查询
	 * @param mobileNumber
	 * @return
	 */
	PhoneAttribution findByMobileNumber( Integer id, String mobileNumber);
	
	/**
	 * 根据ID进行查询（这个方法没有用到数据权限）
	 * @param id PhoneAttribution的ID
	 * @return PhoneAttribution对象
	 */
	PhoneAttribution findById(Integer id);
}
