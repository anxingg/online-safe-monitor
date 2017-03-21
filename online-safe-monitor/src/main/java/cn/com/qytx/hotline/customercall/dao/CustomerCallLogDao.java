package cn.com.qytx.hotline.customercall.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.oa.util.DataFilterUtilForSql;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.PageImpl;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.workflow.domain.HotWorkflowVar;

/**
 * 功能: 服务工单Dao 
 * 版本: 1.0 
 * 开发人员: 李华伟 
 * 创建日期: 2013-9-12 
 * 修改日期: 2013-9-12 修改列表:
 */
@Repository
public class CustomerCallLogDao extends BaseDao<CustomerCallLog, Integer> implements Serializable
{
	private static final long serialVersionUID = 1L;
    /**
     * 功能：根据来源获取最大工单号
     * @param source 工单来源
     * @return String
     */
    public String getMaxCclSnCCL(String source)
    {
        String hql = " select top 1 ccl_sn from tb_customerCallLog where ccl_sn is not null order by ccl_sn desc  ";
        String ccl=(String) entityManager.createNativeQuery(hql).getSingleResult();
        return null!=ccl?ccl:"";
    }

    /**
     * 功能：根据电话获取最后工单信息
     * @param phone
     * @return CustomerCallLog
     */
    public CustomerCallLog findByphone(String phone)
    {
        return  super.findOne("  isDelete = 0 and phone = ? ", phone);
    }
    /**
     * 功能：根据过滤条件分页获取工单信息
     * @param page 分页信息
     * @param vo 查询条件
     * @param u 当前用户
     * @return Page<CustomerCallLog>
     */
    public Page<Object[]> findCCLByPage(Pageable pageInfo, CustomerCallLog vo,
            UserInfo u,String iscomplete,String fromPage,UserInfo userInfo){
    	String countSql = "select count(*) ";
    	String listSql = "select {callLog.*},{workflow.*}  ";
    	String orderSql = " order by callLog.recordTime desc";
    	StringBuffer sql = new StringBuffer(" from tb_customerCallLog callLog,tb_workflow_var workflow where callLog.workflowId=workflow.instance_id");
    	if(vo!=null){
    		//1,与我相关；-1全部
    		Integer scope = vo.getSearchScope();
    		//-1全部,1待处理,2已办理；4已办结
			Integer state =  vo.getState();
			if(state == null){
				state = -1;
			}
			//与我相关
			if(scope!=null && scope == 1){
				if(state!=null){
					if(state == 4){
						sql.append(" and (workflow.state = 'end' ) and workflow.processers like '%,"+u.getUserId()+",%'");
					}else if(state == 1){
						sql.append(" and workflow.instance_id in (select distinct(jt.EXECUTION_ID_) from JBPM4_TASK jt,JBPM4_PARTICIPATION jp where jt.DBID_ = jp.TASK_ and jp.USERID_ = "+u.getUserId()+")");//代办理
					}else if(state ==2){
						sql.append(" and workflow.processers like '%,"+u.getUserId()+",%'");
					}else if(state == -1){
						sql.append(" and (workflow.processers like '%,"+u.getUserId()+",%' or workflow.instance_id in (select distinct(jt.EXECUTION_ID_) from JBPM4_TASK jt,JBPM4_PARTICIPATION jp where jt.DBID_ = jp.TASK_ and jp.USERID_ = "+u.getUserId()+"))");
					}
				}
			}else{//全部
				if(state == 4){
					sql.append(" and (workflow.state = 'end' )");
				}else if(state == 1){
					sql.append(" and workflow.instance_id in (select distinct(jt.EXECUTION_ID_) from JBPM4_TASK jt)");
				}else if(state ==2){
					sql.append(" and workflow.processers like '%,"+u.getUserId()+"%,'");
				}
			}
    		 // 关键字
	          String searchKey = vo.getSearchKey();
	          if (!StringUtils.isEmpty(searchKey))
	          {
	          	if(StringUtils.isNotBlank(fromPage)&&"prev".equals(fromPage)){
	          		sql.append(" and (callLog.phone like '%"+searchKey+"%' or callLog.name like '%"+searchKey+"%')");
	          	}else{
	          		sql.append(" and (callLog.phone like '%"+searchKey+"%' or callLog.ccl_sn like '%"+searchKey+"%' or callLog.name like '%"+searchKey+"%' or callLog.record_user_id in (select user_id from view_user_info where user_name like  '%"+searchKey+"%'))");
	          	}
	          }
	          
	        if(fromPage!=null && fromPage.equals("related")){
	        	String phone = vo.getPhone();
	        	sql.append(" and (callLog.phone like '%"+phone+"%')");
	        }  
	          //时间范围
	          // 受理时间 开始时间
	        Timestamp beginTime = vo.getBeginTime();
	        if (null != beginTime)
	        {
	            sql.append(" and callLog.recordTime >= :beginTime");
	        }
	        // 受理时间 结束时间
	        Timestamp endTime = vo.getEndTime();
	        if (null != endTime)
	        {
	            sql.append(" and callLog.recordTime <= :endTime");
	        }
			// 工单类型
			Integer type = vo.getType();
			if (null != type && -1 != type){
			      sql.append(" and callLog.type = "+type);
			}
			Integer accessType = vo.getAccessType();
			if (null != accessType && -1 != accessType){
				sql.append(" and callLog.access_type = "+accessType);
			}
			Integer businessType = vo.getBusinessType();
			if (null != businessType && -1 != businessType){
				sql.append(" and callLog.business_type = "+businessType);
			}
    	}
    	sql.append(" "+DataFilterUtilForSql.sqlDataFilter());
    	Query query = entityManager.createNativeQuery(countSql+sql);
    	Session session = entityManager.unwrap(org.hibernate.Session.class);
		SQLQuery sqlQuery =  session.createSQLQuery(listSql+sql+orderSql);
    	if(vo!=null){
    		if(vo.getBeginTime()!=null){
    			query.setParameter("beginTime", vo.getBeginTime());
    		}
    		if(vo.getEndTime()!=null){
    			query.setParameter("endTime", vo.getEndTime());
    		}
    		if(vo.getBeginTime()!=null){
    			sqlQuery.setParameter("beginTime", vo.getBeginTime());
    		}
    		if(vo.getEndTime()!=null){
    			sqlQuery.setParameter("endTime", vo.getEndTime());
    		}
    	}
    	int count = (Integer) query.getSingleResult();
		
		int currentPage = pageInfo.getPageNumber();
		int beginNum = currentPage*pageInfo.getPageSize();
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = sqlQuery.addEntity("callLog", CustomerCallLog.class).addEntity("workflow", HotWorkflowVar.class)
													.setFirstResult(beginNum)
													.setMaxResults(pageInfo.getPageSize())
													.list();
		return new PageImpl<Object[]>(result, pageInfo, count);
    }
    /**
     * 功能：统计前台待回访的个数
     */
    public int listCount( CustomerCallLog vo,
    		UserInfo u,String iscomplete,String fromPage)
    		{
    	String countSql = "select count(*) ";
    	StringBuffer sql = new StringBuffer(" from tb_customerCallLog callLog,tb_workflow_var workflow where callLog.workflowId=workflow.instance_id");
    	if(vo!=null){
		sql.append(" and workflow.instance_id in (select distinct(jt.EXECUTION_ID_) from JBPM4_TASK jt,JBPM4_PARTICIPATION jp where jt.DBID_ = jp.TASK_ and jp.USERID_ = "+u.getUserId()+")");//代办理
    	}
    	sql.append(" "+DataFilterUtilForSql.sqlDataFilter());
    	
    	Query query = entityManager.createNativeQuery(countSql+sql);
    	int count = (Integer) query.getSingleResult();
		return count;
		
    		}
    /**
     * 功能：获取未完成工单(不包含已经超时的工单)
     * @return
     */
    public List<CustomerCallLog> getUnfinishedCCL()
    {
        StringBuffer hql = new StringBuffer();
        hql.append("isDelete = 0 ");
        // 未完结的工单 (已受理和已派修的工单)
        hql.append(" and (state = " + CallCenterConst.STATE_ACCEPTED + " or state = "
                + CallCenterConst.STATE_TRANSFER + ")");
        // 并且未超时的工单
        hql.append(" and timeLimit >= 0");
        return super.dataFilter().findAll(hql.toString());
    }

    /**
     * 功能：更新工单剩余时长
     * @param ccl
     * CustomerCallLog
     */
	public void updateCCLTimeLimit(CustomerCallLog ccl)
    {
        String sql = "update CustomerCallLog set timeLimit = " + ccl.getTimeLimit()+ " where vid = " + ccl.getVid();
        entityManager.createQuery(sql);
    }
    /**
     * 功能：根据电话号码查询工单
     * @param vid
     */
	public CustomerCallLog getCustomerCallLogByPhone(String phone)
    {
        return (CustomerCallLog) super.dataFilter().findOne("isDelete = 0 and phone=? order by vid desc ", phone);
    }
    
    /**
	 * 功能：根据受理来源ID查询工单对象
	 * @param accessSourceId：受理来源ID 
	 * @param accessType：受理类型
	 * @return
	 */
	public CustomerCallLog findByAccessSourceid(Integer accessSourceId,Integer accessType){
		if(accessSourceId!=null&&accessType!=null){
			String sql = " isDelete = 0 and accessSourceId="+accessSourceId+" and accessType="+accessType;
			return (CustomerCallLog) super.findOne(sql.toString());
		}
		return null;
	}
	
	public CustomerCallLog findByInstanceId(String instanceId){
		return super.findOne("workflowId = ?", instanceId);
	}
	/**
	 * 获取各地市工单编号的最大值
	 * @return
	 */
	public String getMaxCclSnForArea(String groupArea){
		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX([ccl_sn]) as maxCclSn  ");
		sql.append(" from tb_customerCallLog");
		sql.append(" where SUBSTRING(ccl_sn,9,2) ="+groupArea+" ");//is_fork_group="+isForkGroup+" and 
		sql.append(" group by SUBSTRING(ccl_sn,9,2) ");
		String result = (String) entityManager.createNativeQuery(sql.toString()).getSingleResult();
		return null!=result?result:"";
	}
	/**
	 * 调用有返回参数的存储过程
	 * @param procedureName 存储过程的名称
	 * @param params 有效参数(最后一个返回值参数不需要传)的List集合
	 * @return
	 */
	public String myExecuteProcedure( String procedureName, List<Object> params ) {
		if( StringUtils.isBlank( procedureName )||params == null || params.isEmpty()){
			return null;
		}
		String result = null;
		int paramslength = params.size();
		try {
			Session session = entityManager.unwrap(org.hibernate.Session.class);
			@SuppressWarnings("deprecation")
			Connection conn = session.connection();
			StringBuffer sql = new StringBuffer();
			sql.append( "{call " );
			sql.append( procedureName );
			sql.append( "( " );
			for( int i = 0; i < paramslength; i++ ){
				sql.append( "?" );
				sql.append( "," );
			}
			sql.append( "?" );
			sql.append( " )}" );
			CallableStatement cstmt = conn.prepareCall(sql.toString());
			//这里需要再传一个参数类型的数组，然后赋值。
			for( int i = 0; i < params.size(); i++ ){
				cstmt.setObject( i+1, params.get(i));
			}
			cstmt.registerOutParameter(paramslength+1, java.sql.Types.VARCHAR);
			cstmt.execute();
			/* 
			*记录集获取到后,把rs记录集循环取出后或者调用cstmt.getMoreResults()方法后,sqlserver才会处理output返回值 
			*/ 
			if (!cstmt.getMoreResults()) {//此行判断是否还有更多的结果集,如果没有,接下来会处理output返回参数了  
				result = cstmt.getString(paramslength+1);//此行不再报错  
			}
			conn.close();
			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
