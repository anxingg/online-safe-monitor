package cn.com.qytx.hotline.crm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.crm.dao.CRMDao;
import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.utils.tree.TreeNode;


@Service
@Transactional
public class CRMImpl extends BaseServiceImpl<CRM> implements ICRM,Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	private CRMDao crmdao;

	public void delete(int vid) {
		crmdao.delete(vid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page findPageByVo(Pageable page, CRM vo) {
		return crmdao.findPageByVo(page, vo);
	}

	@Override
	public CRM findCRMById(Integer vid) {
		return crmdao.findCRMById(vid);
	}

	@Override
	public CRM findCRMByMobile(String mobile) {
		return crmdao.findCRMByMobile(mobile);
	}

	@Override
	public CRM findCRMByTelephone(String telephone) {
		return crmdao.findCRMByTelephone(telephone);
	}

	@Override
	public List<TreeNode> getCRMtree(String path,String searchName) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
    	List<CRM> crmList;
    	if("".equals(searchName)||searchName==null){
    		 crmList = crmdao.findAll();
    		 TreeNode treeHead = new TreeNode();
    	    	treeHead.setId("0");
    	    	treeHead.setName("用户列表");
    	    	treeHead.setTitle("用户列表");
    	    	treeHead.setPId("-1");
    	    	treeHead.setIcon(path + "/images/company.png");
    	    	treeHead.setOpen(true);
    	    	treeNodes.add(treeHead);
    	}else{
    		crmList = crmdao.findListByName(searchName);
    	}
		if(crmList!=null){
			for(CRM temp:crmList){
				 TreeNode treeNode = new TreeNode();
				 treeNode.setId("crmid_"+temp.getVid());
				 if(temp.getName()==null||"".equals(temp.getName())){
					 continue;
				 }
				 treeNode.setName(temp.getName());
				 treeNode.setObj(temp.getMobile());
				 treeNode.setPId("0");
				 if (null != temp.getGender() && 0 == temp.getGender()){
                     treeNode.setIcon(path + "/images/woman.png");
                 }
                 else{
                     treeNode.setIcon(path + "/images/man.png");
                 }
				 treeNodes.add(treeNode);
			}
		}
		
		return treeNodes;
	}




	@Override
	public List<CRM> findByPhone(String phone) {
		
		return crmdao.findByPhone(phone);
	}




	@Override
	public CRM findByMobileAndName(String mobile, String name) {
		return crmdao.findByMobileAndName(mobile, name);
	}




	@Override
	public CRM findByBackPhoneAndName(String backPhone, String name) {
		return crmdao.findByBackPhoneAndName(backPhone, name);
	}

}
