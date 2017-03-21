package cn.com.qytx.hotline.crm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.crm.dao.CRMDao;
import cn.com.qytx.hotline.crm.dao.CRMViewDao;
import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.domaim.CRMView;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.crm.service.ICRMView;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.utils.tree.TreeNode;


@Service
@Transactional
public class CRMViewImpl extends BaseServiceImpl<CRMView> implements ICRMView,Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	private CRMViewDao crmdao;


	@Override
	public CRMView findCRMById(Integer vid) {
		return crmdao.findCRMById(vid);
	}




	@Override
	public CRMView findByMobileAndName(String mobile, String name) {
		return crmdao.findByMobileAndName(mobile, name);
	}




    /**
     * 功能：
     * @param phoneTwo
     * @param object
     * @return
     */
    @Override
    public CRMView findCRMByTelephone(String phoneTwo, String name)
    {
        // TODO Auto-generated method stub
        return  crmdao.findCRMByTelephone(phoneTwo, name);
    }



}
