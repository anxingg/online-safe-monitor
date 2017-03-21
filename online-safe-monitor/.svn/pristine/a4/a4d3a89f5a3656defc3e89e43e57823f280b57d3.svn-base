package cn.com.qytx.hotline.report.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.report.domain.WorkSummary;
import cn.com.qytx.hotline.report.domain.WorkSummarySearch;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.dao.ClearParamAfterMethod;

@Repository
public class WorkSummaryDao extends BaseDao<WorkSummary, Integer> implements
		Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 8121335810118624372L;

	/**
	 * 功能：查询工作汇总表
	 * 
	 * @return
	 */
	@ClearParamAfterMethod
	public List<WorkSummary> workSummaryNumber(WorkSummarySearch wss,Integer companyId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" WITH cte AS ( ");
		// 电话咨询
		hql.append(" select a.name as name,isnull(b.total,0 ) as allcount,1 as result from ( select cd.name as name from tb_cbb_dict cd where cd.is_delete=0 and cd.info_type='businessType' and cd.sys_tag=1 ");
		hql.append(sqlQureyByCompanyId(companyId));
		hql.append(" ) a ");
		hql.append(" left join ( select cd.name as name,COUNT(*) as total from tb_cbb_dict cd left join tb_customerCallLog c on cd.value=c.business_type where cd.is_delete=0 and cd.info_type='businessType' and cd.sys_tag=1 and c.is_delete=0 and c.access_type=1 ");
		hql.append(sqlQureyByCompanyId2(companyId));
		if (wss != null) {
			hql.append(hqlQuery(wss));
		}
		hql.append(" group by cd.name ) b on a.name=b.name ");
		hql.append(" UNION ");
		// 短信平台
		hql.append(" select a.name as name,isnull(b.total,0 ) as allcount,2 as result from ( select cd.name as name from tb_cbb_dict cd where cd.is_delete=0 and cd.info_type='businessType' and cd.sys_tag=1 ");
		hql.append(sqlQureyByCompanyId(companyId));
		hql.append(" ) a ");
		hql.append(" left join ( select cd.name as name,COUNT(*) as total from tb_cbb_dict cd left join tb_customerCallLog c on cd.value=c.business_type where cd.is_delete=0 and cd.info_type='businessType' and cd.sys_tag=1 and c.is_delete=0 and c.access_type=2 ");
		hql.append(sqlQureyByCompanyId2(companyId));
		if (wss != null) {
			hql.append(hqlQuery(wss));
		}
		hql.append(" group by cd.name ) b on a.name=b.name ");
		hql.append(" UNION ");
		// 语音留言
		hql.append(" select a.name as name,isnull(b.total,0 ) as allcount,3 as result from ( select cd.name as name from tb_cbb_dict cd where cd.is_delete=0 and cd.info_type='businessType' and cd.sys_tag=1 ");
		hql.append(sqlQureyByCompanyId(companyId));
		hql.append(" ) a ");
		hql.append(" left join ( select cd.name as name,COUNT(*) as total from tb_cbb_dict cd left join tb_customerCallLog c on cd.value=c.business_type where cd.is_delete=0 and cd.info_type='businessType' and cd.sys_tag=1 and c.is_delete=0 and c.access_type=3 ");
		hql.append(sqlQureyByCompanyId2(companyId));
		if (wss != null) {
			hql.append(hqlQuery(wss));
		}
		hql.append(" group by cd.name ) b on a.name=b.name ");
		hql.append(" ), ");
		hql.append(" cte2 AS (SELECT t0.name as businessname,SUM (t0.allcount) AS allcount, ");
		hql.append(" isnull( ( SELECT SUM (t.allcount) FROM cte t WHERE t.name = t0.name AND t.result = 1 ), 0 ) AS phonenum, ");// 电话咨询
		hql.append(" isnull( ( SELECT SUM (t.allcount) FROM cte t WHERE t.name = t0.name AND t.result = 2 ), 0 ) AS messagenum, ");// 短信平台
		hql.append(" isnull( ( SELECT SUM (t.allcount) FROM cte t WHERE t.name = t0.name AND t.result = 3 ), 0 ) AS soundnum ");// 语音留言
		hql.append(" FROM cte t0 GROUP BY t0.name ");
		hql.append(" )select * from cte2 t1 order by t1.allcount desc");

		Query query = super.entityManager.createNativeQuery(hql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		List<WorkSummary> wsList = new ArrayList<WorkSummary>();
		if (list != null && !list.isEmpty()) {
			for (Object[] objs : list) {
				WorkSummary ws = new WorkSummary((String) objs[0],
						(Integer) objs[1], (Integer) objs[2],
						(Integer) objs[3], (Integer) objs[4]);
				wsList.add(ws);
			}
		}
		return wsList;
	}

	/**
	 * 功能：查询条件拼接语句
	 * 
	 * @param wss
	 *            工作汇总查询实体类
	 * @return
	 */
	public String hqlQuery(WorkSummarySearch wss) {
		StringBuffer sbf = new StringBuffer();
		if (StringUtils.isNotBlank(wss.getStartTime())) {
			sbf.append(" AND c.recordTime >= CAST ( '" + wss.getStartTime()
					+ "' AS datetime ) ");
		}
		if (StringUtils.isNotBlank(wss.getEndTime())) {
			sbf.append(" AND c.recordTime <= CAST ( '" + wss.getEndTime()
					+ "' AS datetime ) ");
		}
		if (wss.getIsForkGroup() != null && wss.getIsForkGroup() != 1
				&& wss.getIsForkGroup() != -1) {
			sbf.append(" and c.is_fork_group=" + wss.getIsForkGroup() + " ");
		}
		return sbf.toString();
	}
	
	public String sqlQureyByCompanyId(Integer companyId){
		StringBuffer sql = new StringBuffer();
		sql.append(" and company_id =");
		sql.append(companyId);
		return sql.toString();
	}
	public String sqlQureyByCompanyId2(Integer companyId){
		StringBuffer sql = new StringBuffer();
		sql.append(" and cd.company_id =");
		sql.append(companyId);
		sql.append(" and c.company_id =");
		sql.append(companyId);
		return sql.toString();
	}
}
