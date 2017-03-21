package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.Msiservice;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 
 * 功能: 队列Dao 版本: 1.0 开发人员: 李华伟 创建日期: 2013-11-11 修改日期: 2013-11-11 修改列表:
 */
@SuppressWarnings("serial")
@Component
public class MsiserviceDao extends BaseDao<Msiservice, Integer> implements Serializable {
	public Msiservice findByPhone(String serviceLeadPhone) {
		return (Msiservice) super.findOne("serviceLeadPhone = ?", serviceLeadPhone);
	}

	/**
	 * 根据热线号码获取队列信息
	 */
	public List<Msiservice> findAllByPhone(String serviceLeadPhone) {
		String sql = "from Msiservice where serviceLeadPhone = ?";
		return super.findAll(sql, serviceLeadPhone);
	}

	/**
	 * 功能：获取所有队列信息
	 * 
	 * @return List<Msiservice>
	 */
	public List<Msiservice> findAllDao() {
		return super.dataFilter().findAll();
	}
	/**
     * 功能：查询所有队列信息（不过滤）
     * @return
     */
    public List<Msiservice> findNoFilter(){
    	return super.findAll();
    }

	public void updateMsiservice(Msiservice msiservice) {
		String sql = "update Msiservice set serviceName = ? , content = ?  where vid = "
				+ msiservice.getVid();
		executeQuery(sql,  msiservice.getServiceName(),msiservice.getContent());
	}
	/**
	 * 功能：根据坐席id字符串获得队列信息及坐席信息
	 * @param msiids 坐席ids
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllByMsiUserIds(String msiids){
		String sql = "";
		if(msiids != null && !"".equals(msiids)){
			sql = "select mu.VId, mu.Name ,mu.WorkNo , ms.Vid as sVid, ms.ServiceName from MSIUser mu left join MSIServiceUser msu on msu.MSIId = mu.VId "
					+ " left join MSIService ms on ms.Vid = msu.ServiceId where mu.VId in ("+msiids+")";
		}
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
	/**
	 * 功能：根据坐席id字符串以及登录坐席所属区域获得队列信息及坐席信息
	 * @param msiid:坐席id字符串
	 * @param isForkGroup:登录坐席所属区域
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllByIdsAndIsFork(String msiids,Integer isForkGroup){
		String sql = "";
		if(msiids != null && !"".equals(msiids)){
			sql = "select mu.VId, mu.Name ,mu.WorkNo , ms.Vid as sVid, ms.ServiceName from MSIUser mu left join MSIServiceUser msu on msu.MSIId = mu.VId "
					+ " left join MSIService ms on ms.Vid = msu.ServiceId where mu.VId in ("+msiids+")";
			if(isForkGroup!=null&&isForkGroup!=2){
				sql += " and mu.is_fork_group="+isForkGroup;
			}
		}
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findMsiServiceIdAndNameByForkGroupId( Integer isForkGroup ){
		String sql = "";
		if(isForkGroup != null){
			sql = "select Vid, ServiceName from MSIService where service_type = 1 and is_Fork_Group = " + isForkGroup;
		}
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
}
