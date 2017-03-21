package cn.com.qytx.hotline.crm.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
@Component
public class CRMDao extends BaseDao<CRM, Integer> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5582261988125612803L;

	/**
	 * 根据mobile查询CRM对象
	 * @param mobile
	 * @return
	 */
	public CRM findCRMByMobile(String mobile) {
		List<CRM> crm = null;
        if (null != mobile) {
            String hql = " mobile = ?  and isDelete=0 ";
            crm = super.dataFilter().findAll(hql, mobile);
        }
        if(crm!=null && crm.size()>0){
        	return crm.get(0);
        }else{
        	return null;
        }
	}
	
	
	/**
	 * @param telePhone  座机号码
	 * @return  根据座机号码来查询CRM客户
	 */
	public CRM findCRMByTelephone(String backPhone){
		List<CRM> crm = null;
        if (null != backPhone&&!"".equals(backPhone)) {
            String hql = "backPhone=? and isDelete=0 ";
            crm = super.dataFilter().findAll(hql, backPhone);
        }
        if(crm!=null && crm.size()>0){
        	return crm.get(0);
        }else{
        	return null;
        }
	}
	
	
	
	/**
	 * 更新CRM对象
	 * @param crm
	 */
	public void saveOrUpdateCRM(CRM crm) {
        super.saveOrUpdate(crm);
    }
	
	/**
	 * 根据vid查询CRM对象
	 * @param vid
	 * @return
	 */
	public CRM findCRMById(Integer vid) {
		CRM crm = null;
        if (null != vid) {
            String hql = "vid = ? and isDelete=0 ";
            crm =super.findOne(hql, vid);
        }
        return crm;
    }
	
	/**
	 * 删除CRM
	 * @param vid
	 */
	@SuppressWarnings("deprecation")
	public void delete(int vid) {
        String hql="update CRM set isDelete=1 where vid="+vid;
        this.createQuery(hql);
    }

	/**
	 * 分页查询CRM
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<CRM> findPageByVo(Pageable page, CRM vo) {
       StringBuilder hqlSb = new StringBuilder();
       hqlSb.append("isDelete=0 ");
       // 条件过滤
       if (null != vo) {
            List<Object> params = new ArrayList<Object>();
            Integer gender = vo.getGender();
            if(gender!=null){
            	hqlSb.append(" and gender="+gender+" ");
            }
            String name = vo.getName();
            String mobile = vo.getMobile();
            if(!StringUtils.isEmpty(name)&&!StringUtils.isEmpty(mobile)){
            	hqlSb.append(" and (name like ? or mobile like ?)");
            	params.add("%"+name+"%");
            	params.add("%"+mobile+"%");
            }
      
            return super.dataFilter().findAll(hqlSb.toString(), page, params.size()>0?params.toArray():null);
       }
       return super.dataFilter().findAll(hqlSb.toString(), page);
    }
	/**
	 * 根据名称模糊查询crm
	 * @param name
	 * @return
	 */
	public List<CRM> findListByName(String name){
		List<CRM> crm = null;
        if (null != name&&!"".equals(name)) {
            String hql = " isDelete=0 and name like ?   ";
            crm = super.dataFilter().findAll(hql, "%"+name+"%");
        }
		return crm;
	}
	
	/**
	 * 查询所有的用户
	 * @param name
	 * @return
	 */
	public List<CRM> findAll(){
		List<CRM> crm = null;
        String hql = " isDelete=0 ";
        crm = super.dataFilter().findAll(hql);
		return crm;
	}
	
	/**
	 * 根据电话查询出所有关联的用户信息
	 * @param phone
	 * @return
	 */
	public List<CRM> findByPhone(String phone){
		List<CRM> crm = null;
        if (null != phone) {
        	//只用联系电话查询，多个客户
            String hql = " (mobile = ? ) and isDelete=0 ";
            crm = super.dataFilter().findAll(hql, phone);
        }
        if(crm!=null && crm.size()>0){
        	return crm;
        }else{
        	return null;
        }
	}
	
	/**
	 * 根据联系电话与姓名查询
	 * @param mobile
	 * @param name
	 * @return
	 */
	public CRM findByMobileAndName(String mobile, String name){
		CRM crm = null;
        if (null != mobile&&null!= name) {
            String hql = " mobile = ? and name = ? and isDelete=0 ";
            crm = super.findOne(hql, mobile, name);
        }
        if(crm!=null){
        	return crm;
        }else{
        	return null;
        }
	}
	
	/**
	 * 根据备用电话与姓名查询
	 * @param mobile
	 * @param name
	 * @return
	 */
	public CRM findByBackPhoneAndName(String backPhone, String name){
		List<CRM> crm = null;
        if (null != backPhone&&null!= name) {
            String hql = " backPhone = ? and name = ? and isDelete=0 ";
            crm =super.findAll(hql, backPhone, name);
        }
        if(crm!=null && crm.size()>0){
        	return crm.get(0);
        }else{
        	return null;
        }
	}
}
