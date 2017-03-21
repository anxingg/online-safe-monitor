package cn.com.qytx.workflow.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.workflow.domain.HotProcessAttribute;


/**
 * 功能：流程定义DAO
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-21 下午5:22:08 
 * 修改日期：2013-3-21 下午5:22:08 
 * 修改列表：
 */
@Repository("hotprocessAttributeDao")
public class HotProcessAttributeDao extends BaseDao<HotProcessAttribute, Integer> implements Serializable{


	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 功能：根据类别ID获取该类别下的所有流程实例
	 * @param
	 * @return
	 * @throws   
	 */
	public List<HotProcessAttribute> findByCategoryId(final int categoryId,final int companyId){
		return super.findAll("categoryId = ? and companyId = ?", new Sort(
				new Sort.Order(Direction.ASC, "processOrder")), categoryId,
				companyId);
	}
	
	/**
	 * 功能：根据类别ID获取该类别下的所有流程实例
	 * @param
	 * @return
	 * @throws   
	 */
	public List<HotProcessAttribute> findByCategoryId(final int categoryId){
		List<HotProcessAttribute> hpaList;
		if (categoryId > 0) {
			hpaList = findAll("categoryId = ? and processState=2",new Sort(new Sort.Order(Direction.ASC,"processOrder")), categoryId);
		} else {
			hpaList = findAll("processState=2",new Sort(new Sort.Order(Direction.ASC,"processOrder")));
		}
		
		return hpaList;
	}
	
	/**
	 * 功能：根据类别ID分组查询每组的数量
	 * @param
	 * @return
	 * @throws   
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getCountByCategory(){
		//String sql = "select category_id,COUNT(process_attribute_id) as num from tb_cbb_process_attribute group by category_id";
		//return entityManager.createNativeQuery(sql).getResultList();
		return entityManager.createNativeQuery("select category_id,COUNT(process_attribute_id) as num from tb_cbb_process_attribute group by category_id").getResultList();
	}
	
	/**
	 * 功能：根据流程定义ID查询流程定义属性
	 * @param
	 * @return
	 * @throws   
	 */
	public HotProcessAttribute getProcessAttributeByDefineId(final String definId){
		return findOne("processDefineId = ?1",definId);
	}
	
	/**
	 * 功能：根据流程名称查询流程定义属性
	 * @param
	 * @return
	 * @throws   
	 */
	@SuppressWarnings("deprecation")
	public HotProcessAttribute getProcessAttributeByProcessName(final String processName){
		return findUnique("select p from HotProcessAttribute p processName = ?", processName);
	}
	
	/**
	 * 功能：获取所有的流程定义根据公司ID
	 * @param
	 * @return
	 * @throws   
	 */
	@SuppressWarnings("unchecked")
	public List<HotProcessAttribute> getAllProcessAttributesByCompanyId(final Integer companyId){
		//String hql = "select new HotProcessAttribute(processName,id,categoryId,processDefineId) from HotProcessAttribute where companyId = "+companyId;
		if(companyId!=null&&companyId>0){
		    
		    return entityManager.createQuery("select new HotProcessAttribute(processName,id,categoryId,processDefineId) from HotProcessAttribute where companyId = "+companyId).getResultList();
		}else{
		    return entityManager.createQuery("select new HotProcessAttribute(processName,id,categoryId,processDefineId) from HotProcessAttribute").getResultList();
		    
		}
	}
	
	/**
	 * 功能：查询已发布的流程
	 * @param
	 * @return
	 * @throws   
	 */
	public List<HotProcessAttribute> getAllDeployedProcessAttributes(final int companyid){
		return findAll("companyId = ? and processState = 2", companyid);
	}
	
	/**
	 * 功能：验证流程名称是否重复
	 * @param
	 * @return true:重复；false：不重复
	 * @throws   
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkProcessNameIsRepeat(final String processName,final Integer paId){
		boolean result;
		List list;
		if (paId == null) {
			//String hql = "processName = ?";
			list = this.findAll("processName = ?", processName);
		} else {
			//String hql = "processName = ? and id != ?";
			list = this.findAll("processName = ? and id != ?", processName, paId);
		}
		if (list == null || list.isEmpty()) {
			result = false;
		}else{
			result = true;
		}
		return result;
	}
	
	/**
	 * 功能：sql更新processAttribute对象
	 * @param
	 * @return
	 * @throws   
	 */
	public void  saveOrUpdateBysql(final HotProcessAttribute paab){
		saveOrUpdate(paab);
	}
	
	/**
	 * 功能：sql更新processAttribute对象
	 * @param
	 * @return
	 * @throws   
	 */
	@SuppressWarnings("deprecation")
	public void  saveOrUpdateBysql(final int paId,final String jsonData,final String xmlData){
//		String sql = "update HotProcessAttribute set procsssDefinByJSON = ?,processDefineByXML = ? "
//				+ "where process_attribute_id = ?";
		bulkUpdate("update HotProcessAttribute set procsssDefinByJSON = ?,processDefineByXML = ? "
				+ "where process_attribute_id = ?", jsonData, xmlData, paId);
	}
	
	/**
	 * 功能：更新processAttribute的起始编号
	 * @param
	 * @return
	 * @throws   
	 */
	@SuppressWarnings("deprecation")
	public void updateProcessAttributeBeginNum(final int paId){
		final String hql = "update HotProcessAttribute set processNameBeginNum=processNameBeginNum+1 where id= "
				+ paId;
		bulkUpdate(hql);
	}
	
}


	
