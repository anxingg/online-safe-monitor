package cn.com.qytx.hotline.mis.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 功能: 号码归属地 版本: 1.0 开发人员: 李华伟 创建日期: 2014-1-20 修改日期: 2014-1-20 修改列表:
 */
@Repository
public class PhoneAttributionDao extends BaseDao<PhoneAttribution, Integer> implements Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 2278957199267789854L;

	/**
	 * 功能：根据电话查询归属地
	 * @param phone:手机号码
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public PhoneAttribution findByPhone(String phone) {
		PhoneAttribution result = null;
		String hql = "";
		if (!StringUtils.isEmpty(phone)) {
			// 13 15 18 开头都为手机号码
			if ((phone.indexOf("13") == 0 || phone.indexOf("15") == 0 || phone
					.indexOf("18") == 0) && phone.length() > 8) {
				hql = "from PhoneAttribution where mobileNumber = ?";
				result = (PhoneAttribution) super.findUnique(hql,
						phone.substring(0, 7));
			} else if (phone.length() > 6) {
				hql = "from PhoneAttribution where (areaCode = ? or areaCode = ?)";
				result = (PhoneAttribution) super.findUnique(hql,
						phone.substring(0, 3), phone.substring(0, 4));
			}
		}
		return result;
	}
	
	/**
	 * 功能：分页查询号码段维护表
	 * @param page
	 * @param searchKey 号码段/归属区域/区号
	 * @return
	 */
	public Page<PhoneAttribution> findByPage(Pageable page, String searchKey) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(" 1=1 ");
//		if(StringUtils.isNotBlank(searchKey)){
//			sb.append(" and (mobileNumber like '%"+searchKey+"%' or mobileArea like '%"+searchKey+"%' or areaCode like '%"+searchKey+"%')");
//		}
//		return super.findAll(sb.toString(), page);
		
		//一、获取总条数
		StringBuilder hql0 = new StringBuilder();
		hql0.append("select count(*) from PhoneAttribution where 1=1 ");
		if(StringUtils.isNotBlank(searchKey)){
			hql0.append(" and (mobileNumber like '%"+searchKey+"%' or mobileArea like '%"+searchKey+"%' or areaCode like '%"+searchKey+"%') ");
		}
		javax.persistence.Query query0 = super.entityManager.createQuery(hql0.toString());
		Object count = null;
		/*
		 * JPA中的Query对象的getSingleResult()方法，
		 * 当查询不到结果时，抛出NoResultException、
		 * 当查询到多个结果时，抛出NonUniqueResultException；
		 * 并且NoResultException和NonUniqueResultException都是RuntimeException。
		 */
		try {
			count = query0.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			count = null;
		} catch (javax.persistence.NonUniqueResultException ex) {
			count = query0.getResultList();
			count = ((List<?>) count).get(0);
		}
		long total = 0l;
		if(count != null){
			total = Long.parseLong(count.toString());
		}
		
		//二、分页查询数据
		StringBuilder hql = new StringBuilder();
		hql.append("from PhoneAttribution where 1=1 ");
		if(StringUtils.isNotBlank(searchKey)){
			hql.append(" and (mobileNumber like '%"+searchKey+"%' or mobileArea like '%"+searchKey+"%' or areaCode like '%"+searchKey+"%') ");
		}
		hql.append(" order by id desc ");
		//System.out.println(sql.toString());
		
		javax.persistence.Query query = super.entityManager.createQuery(hql.toString());
		int pageSize = page.getPageSize();
		int pageNum = page.getPageNumber();
		query.setFirstResult(pageNum*pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<PhoneAttribution> result =  query.getResultList();
		//System.out.println(result);
		
		//三、把list转成Page，并返回
		return listToPage(result, total, pageSize);
	}
	
	/**
	 * 把List转成Page
	 * @param list 查询出来的List对象
	 * @param total 该查询条件下的总条数
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	private Page<PhoneAttribution> listToPage(List<PhoneAttribution> list, long total, int pageSize){
		MyPageImpl<PhoneAttribution> page = new MyPageImpl<PhoneAttribution>();
		page.setList(list);
		page.setTotal(total);
		page.setTotalPage(pageSize == 0 ? 1 : (int)Math.ceil(total / pageSize));
		return page;
	}
	
	/**
	 * 根据ID进行查询（这个方法没有用到数据权限）
	 * @param id PhoneAttribution的ID
	 * @return PhoneAttribution对象
	 */
	public PhoneAttribution findById(Integer id){
		String hql = "from PhoneAttribution where id = :id ";
		javax.persistence.Query query = super.entityManager.createQuery(hql);
		query.setParameter("id", id);
		Object obj =  query.getSingleResult();
		return (PhoneAttribution) obj;
	} 
	
	
	/**
	 * 功能：根据手机号码段查询
	 * @param mobileNumber
	 * @return
	 */
	public PhoneAttribution findByMobileNumber(Integer id, String mobileNumber){
//		PhoneAttribution pa = new PhoneAttribution();
//		String hql = "";
//		if(StringUtils.isNotBlank(mobileNumber)){
//			hql += " mobileNumber = "+mobileNumber;
//			if( id != null ){
//				hql += " and id != " + id;
//			}
//			pa = super.findOne(hql);
//		}
//		return pa;
		
		if(StringUtils.isBlank(mobileNumber)){
			return null;
		}else{
			StringBuilder hql = new StringBuilder();
			hql.append("from PhoneAttribution where mobileNumber = ?1 ");
			if(id != null){
				hql.append(" and id <> ?2 ");
			}
			javax.persistence.Query query = super.entityManager.createQuery(hql.toString());
			query.setParameter(1, mobileNumber);
			if(id != null){
				query.setParameter(2, id);
			}
			
			Object obj = null;
			try{
				obj = query.getSingleResult();
			}catch(org.springframework.dao.EmptyResultDataAccessException e){
				//e.printStackTrace();
			}catch(javax.persistence.NoResultException nre){
				//nre.printStackTrace();
			}
			return (PhoneAttribution) obj;
		}
	}
}
