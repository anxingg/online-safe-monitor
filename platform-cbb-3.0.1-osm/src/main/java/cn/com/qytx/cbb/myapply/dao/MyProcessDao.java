package cn.com.qytx.cbb.myapply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.cbb.myapply.domain.MyProcessed;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

@Repository
public class MyProcessDao extends BaseDao<MyProcessed,Integer>{
	
	public Page<MyProcessed> findListByUserId(Pageable pageable,Integer userId){
		return findAll("processerId=?1", pageable, userId);
	}

	public void del(String instanceIds, String moduleCode) {
		String[] ids = instanceIds.split(",");
		for(String id:ids){
			String hql = " delete from MyProcessed where instanceId =?1 and moduleCode=?2";
			executeQuery(hql,id,moduleCode);
		}
	}
	
	public List<MyProcessed> findByInstanceId(String instanceId){
		return findAll("instanceId = ? ",instanceId);
	}
}
