package cn.com.wh.dangerchemicals.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.PageImpl;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicals;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicalsStatisticsResult;

/**
 * @author lilipo
 * 
 */
@Repository
public class CompanyDangerChemicalsDao extends
		BaseDao<CompanyDangerChemicals, Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2896500411011375477L;

	public Page<CompanyDangerChemicals> findByPage(Pageable pageable,
			String materialName, Integer dangerId, Integer groupId) {
		StringBuffer sbf = new StringBuffer(" isDelete = 0 ");

		List<Object> params = new ArrayList<Object>();

		// 企业ID
		if (groupId != null && groupId != 0) {
			sbf.append(" and groupId = ?");
			params.add(groupId);
		}

		// 危化品ID
		if (dangerId != null && dangerId != 0) {
			sbf.append(" and dangerId = ?");
			params.add(dangerId);
		}

		// 危化品名
		if (StringUtils.isNotBlank(materialName)) {
			//sbf.append(" and materialName like ?");
			sbf.append(" and dangerId in (select vid from DangerChemicalsInfo where materialName like ?)");
			params.add("%" + materialName + "%");
		}
		
		//过滤已经删除的公司
		sbf.append("and groupId not in (select groupId from WHCompany where isDelete = 1) ");
		//过滤已经删除的物质
		sbf.append("and dangerId not in (select vid from ChemicalsDirectory where isDelete = 1) ");

		return super.findAll(sbf.toString(), pageable, params.toArray());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CompanyDangerChemicalsStatisticsResult> findStatisticsResultByPage(
			Pageable pageable, String materialName, Integer statisticsType,
			Integer groupId) {

		/*
		 * select danger_id, sum(num),group_id from
		 * tb_wuhai_company_danger_chemic where is_delete = 0 and group_id = 178
		 * and danger_id = 1708 and material_name like '%氧化%' group by
		 * danger_id, group_id
		 */
		List<Object> params = new ArrayList<Object>();
		String contentStatement = "select danger_id, sum(num) as sumnum ";
		if (statisticsType != null && statisticsType == 2) {
			contentStatement += ", group_id ";
		}
		String countStatement = "SELECT count(*) from ( SELECT count(vid) as ct ";
		StringBuilder sbr = new StringBuilder();
		sbr.append("from tb_wuhai_company_danger_chemic ").append(
				"where is_delete = 0 ");
		if (groupId != null && groupId != 0) {
			sbr.append("and group_id = ? ");
			params.add(groupId);
		}

		if (StringUtils.isNotBlank(materialName)) {
			sbr.append("and material_name like ? ");
			params.add("%" + materialName + "%");
		}
		
		//过滤已经删除的公司
		sbr.append("and group_id not in (select group_id from tb_wuhai_company where is_delete = 1) ");
		//过滤已经删除的物质
		sbr.append("and danger_id not in (select vid from tb_wuhai_danger_chemicals where is_delete = 1) ");
		//分组
		sbr.append("group by danger_id ");

		// 按企业统计时，修改分组查询条件
		if (statisticsType != null && statisticsType == 2) {
			sbr.append(", group_id ");
		}

		// 查询总数
		Query countquery = super.entityManager.createNativeQuery(countStatement
				+ sbr.toString() + ")");
		if (!params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				countquery.setParameter(i + 1, params.get(i));
			}
		}
		Object total = countquery.getSingleResult();

		sbr.append("order by danger_id ");

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
		List<CompanyDangerChemicalsStatisticsResult> content = new ArrayList<CompanyDangerChemicalsStatisticsResult>();
		if (list != null && !list.isEmpty()) {
			for (Object[] objs : list) {
				if (statisticsType != null && statisticsType == 2) {
					// 按企业分组查询
					content.add(new CompanyDangerChemicalsStatisticsResult(
							Integer.parseInt(objs[2].toString()), Integer
									.parseInt(objs[0].toString()), Float
									.parseFloat(objs[1].toString())));
				} else {
					// 按园区分组查询
					content.add(new CompanyDangerChemicalsStatisticsResult(
							Integer.parseInt(objs[0].toString()), Float
									.parseFloat(objs[1].toString())));
				}
			}
		}

		return new PageImpl(content, pageable, Integer.parseInt(total
				.toString()));
	}

}
