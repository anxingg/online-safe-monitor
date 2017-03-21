package cn.com.qytx.hotline.crm.service;

import java.util.List;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.utils.tree.TreeNode;



public interface ICRM extends BaseService<CRM> {

	/**
	 * 分页查询用户信息
	 * @param page
	 * @param vo
	 * @return
	 */
	Page<CRM> findPageByVo(Pageable page, CRM vo);
	
	/**
	 * 根据vid查询CRM对象
	 * @param vid
	 * @return
	 */
	CRM findCRMById(Integer vid);
	
	/**
	 * 根据phone来电号码查询CRM对象
	 * @param phone
	 * @return
	 */
	CRM findCRMByMobile(String phone);
	
	/**
	 * 根据座机号查询CRM对象
	 * @param mobile  座机号
	 * @return
	 */
	
	CRM findCRMByTelephone(String telephone);
	
	/**
	 * 获得CRM的树结构
	 * @return
	 */
	List<TreeNode> getCRMtree(String path,String searchName);
	
	/**
	 * 根据电话查询出所有关联的用户信息
	 * @param phone
	 * @return
	 */
	List<CRM> findByPhone(String phone);
	
	/**
	 * 根据联系电话与姓名查询
	 * @param mobile
	 * @param name
	 * @return
	 */
	CRM findByMobileAndName(String mobile, String name);
	
	/**
	 * 根据备用电话与姓名查询
	 * @param mobile
	 * @param name
	 * @return
	 */
	CRM findByBackPhoneAndName(String backPhone, String name);
}
