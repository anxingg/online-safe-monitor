package cn.com.qytx.hotline.outcall.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.mis.dao.MyPageImpl;
import cn.com.qytx.hotline.outcall.domain.OutCallSegment;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 
 * 功能:外呼号码段dao 版本: 1.0 开发人员: 徐长江 创建日期: 2014-3-6 修改日期: 2014-3-6 修改列表:
 */
@Repository
public class OutCallSegmentDao extends BaseDao<OutCallSegment, Integer>
		implements Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 功能：根据号码段得id得到号码端实体类
	 * 
	 * @param vid
	 * @return
	 */
	public OutCallSegment findOutCallSegmentById(Integer vid) {

		// return (OutCallSegment)
		// super.findUnique("from OutCallSegment outCallSegment where isDelete = 0 and OutCallSegment.vid=?",
		// vid);
		return (OutCallSegment) super.findOne(vid);
	}

	/**
	 * 
	 * 功能：根据号码段得电话得到号码端实体类
	 * 
	 * @param vid
	 * @return
	 */
	public OutCallSegment findOutCallSegmentByPhone(String phone) {
		javax.persistence.Query query = super.entityManager.createQuery("from OutCallSegment where isDelete = 0 and phone=?1");
		query.setParameter(1, phone);
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			
		} catch (javax.persistence.NonUniqueResultException ex) {
			obj = query.getResultList();
			obj = ((List<?>) obj).get(0);
		}
		
		return (OutCallSegment) obj;
		//return (OutCallSegment) super.findUnique("from OutCallSegment where isDelete = 0 and phone=?", phone);
		//String hql = "isDelete = 0 and phone=?";
//		return (OutCallSegment) super.findOne(hql, phone);
	}
	
	/**
	 * 
	 * 功能：保存 号码段记录
	 * 
	 * @param outCallSegment
	 */
	public void saveOutCallSegment(OutCallSegment outCallSegment) {
		super.saveOrUpdate(outCallSegment);
	}

	/**
	 * 
	 * 功能：删除号码段记录
	 * 
	 * @param outCallSegment
	 */
	public void deleteOutCallSegmentById(Integer vid) {
		// super.bulkUpdate("update OutCallSegment outCallSegment set outCallSegment.isDelete=1 , outCallSegment.checkState=0 where outCallSegment.vid=?",
		// vid);
		super.delete(vid, false);
	}

	/**
	 * 
	 * 功能：分页查询
	 * 
	 * @param page
	 * @param phone
	 * @return
	 */
	public Page<OutCallSegment> findOutCallSegmentPageList(Pageable pageable, String phone) {

		//一、获取总条数
		StringBuilder hql0 = new StringBuilder();
		hql0.append("select count(*) from OutCallSegment where isDelete=0 ");
		if (StringUtils.isNotBlank(phone)) {
			hql0.append(" and phone like :phone ");
		}
		javax.persistence.Query query0 = super.entityManager.createQuery(hql0.toString());
		if (StringUtils.isNotBlank(phone)) {
			query0.setParameter("phone", "%"+phone+"%");
		}
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
		hql.append("from OutCallSegment where isDelete=0 ");
		
		if (StringUtils.isNotBlank(phone)) {
			hql.append(" and phone like :phone ");
		}
		hql.append("order by createTime desc");

		javax.persistence.Query query = super.entityManager.createQuery(hql.toString());
		int pageSize = pageable.getPageSize();
		int pageNum = pageable.getPageNumber();
		query.setFirstResult(pageNum*pageSize);
		query.setMaxResults(pageSize);
		if (StringUtils.isNotBlank(phone)) {
			query.setParameter("phone", "%"+phone+"%");
		}
		@SuppressWarnings("unchecked")
		List<OutCallSegment> objlist = query.getResultList();
		
		//三、把list转成Page，并返回
		return listToPage(objlist, total, pageSize);
	}
	
	/**
	 * 把List转成Page
	 * @param list 查询出来的List对象
	 * @param total 该查询条件下的总条数
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	private Page<OutCallSegment> listToPage(List<OutCallSegment> list, long total, int pageSize){
		MyPageImpl<OutCallSegment> page = new MyPageImpl<OutCallSegment>();
		page.setList(list);
		page.setTotal(total);
		page.setTotalPage(pageSize == 0 ? 1 : (int)Math.ceil(total / pageSize));
		return page;
	}

}

