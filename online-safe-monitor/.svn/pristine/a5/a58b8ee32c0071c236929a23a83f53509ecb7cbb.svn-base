package cn.com.qytx.hotline.crm.service;

import java.util.List;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.domaim.CRMView;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.utils.tree.TreeNode;



public interface ICRMView extends BaseService<CRMView> {

	
	
	/**
	 * 根据vid查询CRM对象
	 * @param vid
	 * @return
	 */
    CRMView findCRMById(Integer vid);
	
	
	
	/**
	 * 根据联系电话与姓名查询
	 * @param mobile
	 * @param name
	 * @return
	 */
    CRMView findByMobileAndName(String mobile, String name);



    /**
     * 功能：根据备用号码查询
     * @param phoneTwo
     * @param object
     * @return
     */
    CRMView findCRMByTelephone(String phoneTwo, String name);
	
	
}
