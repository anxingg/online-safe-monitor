package cn.com.wh.collectsensor.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.collectsensor.domain.CollectSensor;

import java.util.Date; 

import cn.com.qytx.cbb.util.DateUtils;
/**
 * 功能: 应急预案
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-27
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class CollectSensorDao extends BaseDao<CollectSensor, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	public Page<CollectSensor> findCollectSensorList(Pageable pageable,Integer dangerSourceId) {
		String hql=" 1=1 ";
		if((dangerSourceId!=null)&&(dangerSourceId!=-1)){
			hql+=" and DangerSource.Id="+dangerSourceId;
		}
		return this.findAll(hql,pageable);
	}
	/**
	 * 根据危险源获得传感器列表
	 * @param dangerSourceId
	 * @param notInStr 不在的传感器列表
	 * @return
	 */
	public List<CollectSensor> findCollectSensorList(Integer dangerSourceId,String notInStr) {
		// TODO Auto-generated method stub
		String hql=" 1=1 ";
		if((dangerSourceId!=null)&&(dangerSourceId!=-1)){
			hql+=" and DangerSource.Id="+dangerSourceId;
		}
		if(!StringUtils.isEmpty(notInStr)){
			hql+= "and vid not in ("+notInStr+")";
		}
		return this.findAll(hql);
	}
	/**
	 * 统计传感器列表的数据
	 * @param status
	 * @param beginDate
	 * @param endDate
	 * @param dangerSourceIds 危险源ID列表
	 * @param ids 数据ID
	 * @param companyIds 单位ID
	 * @param groupIds 单位表的groupId
	 * @return
	 */
	public Integer countCollectSensorByIds(Integer status,
			Date beginDate,Date endDate,String dangerSourceIds,
			String ids,String companyIds,String groupIds)
	{
		String sql="select count(*) from v_collectsensor where is_Delete=0";
		if((status!=null)&&(status!=-1)){
			sql+=" and enableWarning="+status;
		}
		if((beginDate!=null)&&(endDate!=null)){
			sql+=" and CREATETIME >=to_timestamp('"+
					DateUtils.date2ShortStr(beginDate)+
					"','yyyy-mm-dd')";
			sql+=" and CREATETIME <to_timestamp('"+
					DateUtils.date2ShortStr(endDate)+
					"','yyyy-mm-dd')";
		}
		if(!StringUtils.isEmpty(dangerSourceIds)){
			sql+=" and dangerSourceId in ("+dangerSourceIds+")";
		}
		if(!StringUtils.isEmpty(ids)){
			sql+=" and id in ("+ids+")";
		}
		if(!StringUtils.isEmpty(companyIds)){
			sql+=" and company_id in ("+companyIds+")";
		}
		if(!StringUtils.isEmpty(groupIds)){
			sql+=" and group_id in ("+groupIds+")";
		}
		return this.totalCountBySql(sql);
	}
}
