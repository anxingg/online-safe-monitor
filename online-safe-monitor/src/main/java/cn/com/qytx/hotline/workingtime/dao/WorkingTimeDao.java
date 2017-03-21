package cn.com.qytx.hotline.workingtime.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.workingtime.domain.WorkingTime;
import cn.com.qytx.platform.base.dao.BaseDao;
/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
@Component
public class WorkingTimeDao extends BaseDao<WorkingTime, Integer> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -215679704616201483L;
	/**
	 * 初始化工作时间
	 * @return
	 */
	public List<WorkingTime> findWorkingTime(){
		return super.dataFilter().findAll(" isDelete = 0 ");
	}
	/**
	 * 根据id获得工作时间
	 * @return
	 */
	public WorkingTime findById(Integer id){
		return super.dataFilter().findOne(id);
	}
	
}
