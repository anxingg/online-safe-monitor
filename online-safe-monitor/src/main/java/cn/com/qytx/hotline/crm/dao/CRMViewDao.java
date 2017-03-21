package cn.com.qytx.hotline.crm.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.domaim.CRMView;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
@Component
public class CRMViewDao extends BaseDao<CRMView, Integer> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5582261988125612803L;
	/**
	 * 根据vid查询CRM对象
	 * @param vid
	 * @return
	 */
	public CRMView findCRMById(Integer id) {
	    CRMView crm = null;
        if (null != id) {
            String hql = "from CRMView where id = ? ";
            crm =super.findUnique(hql, id);
        }
        return crm;
    }
	

	
	/**
	 * 根据联系电话与姓名查询
	 * @param mobile
	 * @param name
	 * @return
	 */
	public CRMView findByMobileAndName(String mobile, String name){
	    CRMView crm = null;
	    String hql ="";
        if (null != mobile&&null!= name) {
             hql = " from CRMView where  mobile = ?  and name = ?  ";
            crm = super.findUnique(hql, mobile,name);
        }else if(null!=mobile){
            hql = " from CRMView where  mobile = ? ";
            crm = super.findUnique(hql, mobile);
        }
        if(crm!=null){
        	return crm;
        }else{
        	return null;
        }
	}



    /**
     * 功能：
     * @param phoneTwo
     * @param name
     * @return
     */
    public CRMView findCRMByTelephone(String phoneTwo, String name)
    {
        CRMView crm = null;
        String hql ="";
        if (null != phoneTwo&&null!= name) {
             hql = " from CRMView where  backPhone = ? and name = ?  ";
            crm = super.findUnique(hql, phoneTwo,name);
        }else if(null!=phoneTwo){
            hql = " from CRMView where   backPhone = ?  ";
            crm = super.findUnique(hql, phoneTwo);
        }
        if(crm!=null){
            return crm;
        }else{
            return null;
        }
    }
	
}
