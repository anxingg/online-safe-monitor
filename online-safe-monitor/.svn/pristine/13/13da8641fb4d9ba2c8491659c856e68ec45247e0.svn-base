package cn.com.wh.safeaccident.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.PageImpl;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.safeaccident.domain.SafeAccident;
import cn.com.wh.safeaccident.domain.SafeAccidentStatisticsResult;

/**
 * 安全生产事故的DAO
 * 
 * @author lilipo
 * 
 */
@Repository
public class SafeAccidentDao extends BaseDao<SafeAccident, Serializable>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2822261753478755012L;

	public Page<SafeAccident> findByPage(Pageable pageable,
			String keyword, Integer accidentCharacter,
			String accidentName, Integer whroletype, Integer groupId,
			Date beginDate, Date endDate) {

		String hql = " isDelete = 0 ";
		List<Object> params = new ArrayList<Object>();

		if (whroletype == 1 || whroletype == 3) {
			// 政府端

			// 事故名称
			if (StringUtils.isNotBlank(accidentName)) {
				hql += " and accidentName like ?";
				params.add("%" + accidentName + "%");
			}

		} else {
			// 企业端

			// 关键字：事故名称、事故简介、事故后果、事故处理情况
			if (StringUtils.isNotBlank(keyword)) {
				hql += " and (accidentName like ? or occurredDescription like ? or occurredConsequence like ? or processCondition like ?)";
				params.add("%" + keyword + "%");
				params.add("%" + keyword + "%");
				params.add("%" + keyword + "%");
				params.add("%" + keyword + "%");
			}

			// 企业是否可见
			hql += " and canSee = 1";
		}

		// 事故性质
		if (accidentCharacter != null && accidentCharacter != 0) {
			hql += " and accidentCharacter = ?";
			params.add(accidentCharacter);
		}

		// 企业ID
		if (groupId != null && groupId != 0) {
			hql += " and groupId = ?";
			params.add(groupId);
		}
		
		//事故查询开始时间
		if(beginDate != null){
			hql += " and occurredTime >= ?";
			params.add(beginDate);
		}
		
		//事故查询结束时间
		if(endDate != null){
			hql += " and occurredTime <= ?";
			params.add(endDate);
		}
		
		//过滤已经删除的公司
		hql +="and groupId not in (select groupId from WHCompany where isDelete = 1) ";

		return super.findAll(hql, pageable, params.toArray());
	}

	/**
	 * @param pageable
	 * @param occurredTimeBegin
	 * @param occurredTimeEnd
	 * @param accidentCharacter
	 * @param whroletype
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<SafeAccidentStatisticsResult> findStatisticsResultByPage(
			Pageable pageable, Timestamp occurredTimeBegin,
			Timestamp occurredTimeEnd, Integer accidentCharacter,
			Integer whroletype, Integer groupId) {

		/*
		 * SELECT group_id, accident_character, count(vid) as ct from
		 * tb_wuhai_safe_accident where is_delete = 0 and occurred_time >=
		 * to_date('2015-08-20 00:00:00', 'yyyy-MM-dd HH24:mi:ss') and
		 * occurred_time <= to_date('2015-08-22 00:00:00', 'yyyy-MM-dd
		 * HH24:mi:ss') --and accident_character = 3 --and group_id = 178 group
		 * BY group_id, accident_character order by group_id ;
		 */
		List<Object> params = new ArrayList<Object>();
		String contentStatement = "SELECT group_id, accident_character, count(vid) as ct ";
		String countStatement = "SELECT count(*) from ( SELECT count(vid) as ct ";
		StringBuilder sbr = new StringBuilder();
		sbr.append("from tb_wuhai_safe_accident ").append(
				"where is_delete = 0 ");
		if (occurredTimeBegin != null) {
			sbr.append("and occurred_time >= ? ");
			params.add(occurredTimeBegin);
		}

		if (occurredTimeEnd != null) {
			sbr.append("and occurred_time <= ? ");
			params.add(occurredTimeEnd);
		}

		if (accidentCharacter != null && accidentCharacter != 0) {
			sbr.append("and accident_character = ? ");
			params.add(accidentCharacter);
		}

		if (groupId != null && groupId != 0) {
			sbr.append("and group_id = ? ");
			params.add(groupId);
		}
		//过滤已经删除的公司
		sbr.append("and group_id not in (select group_id from tb_wuhai_company where is_delete = 1) ");
		
		sbr.append("group BY group_id, accident_character ");

		// 查询总数
		Query countquery = super.entityManager.createNativeQuery(countStatement
				+ sbr.toString() + ")");
		if (!params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				countquery.setParameter(i + 1, params.get(i));
			}
		}
		Object total = countquery.getSingleResult();

		sbr.append("order by group_id ");

		// 查询内容
		Query query = super.entityManager.createNativeQuery(contentStatement
				+ sbr.toString());
		if (!params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
		}
		// System.out.printf("pageable.getOffset()=%d\n",pageable.getOffset());
		// System.out.printf("pageable.getPageSize()=%d\n",pageable.getPageSize());
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		List<Object[]> list = query.getResultList();
		List<SafeAccidentStatisticsResult> content = new ArrayList<SafeAccidentStatisticsResult>();
		if (list != null && !list.isEmpty()) {
			for (Object[] objs : list) {
				content.add(new SafeAccidentStatisticsResult(Integer
						.parseInt(objs[0].toString()), Integer.parseInt(objs[1]
						.toString()), Integer.parseInt(objs[2].toString())));
			}
		}

		return new PageImpl(content, pageable, Integer.parseInt(total
				.toString()));
	}
}
