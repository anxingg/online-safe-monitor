package cn.com.wh.zhdwxy.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.zhdwxy.domain.DangerSourcesGood;

/**
 * 重大危险源危化品DAO
 * @author lilipo
 * 
 */
@Repository
public class DangerSourcesGoodDao extends
		BaseDao<DangerSourcesGood, Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3196524693161386011L;

	public DangerSourcesGood saveOrUpdate(DangerSourcesGood dsg) {
		return super.saveOrUpdate(dsg);
	}

	public DangerSourcesGood findOne(Integer vid) {
		return super.findOne(vid);
	}

	public Page<DangerSourcesGood> findByPage(Pageable pageable,
			DangerSourcesGood dsg) {
		StringBuffer sbf = new StringBuffer(" isDelete = 0 ");

		List<Object> params = new ArrayList<Object>();
		
		Integer whroletype = dsg.getWhroletype();
		Integer groupId = dsg.getGroupId();
		Integer dangerId = dsg.getDangerId();
		String dangerGoodName = dsg.getDangerGoodName();
		Integer dictId = dsg.getDictId();
		
		if(whroletype !=null && whroletype.intValue() != 2){
			groupId = null;
		}
		
		//部门ID
		if (groupId != null && groupId != 0) {
			sbf.append(" and groupId = ?");
			params.add(groupId);
		}
		
		//关联的重大危险源ID
		if (dangerId != null) {
			sbf.append(" and dangerId = ?");
			params.add(dangerId);
		}
		
		// 危化品名称
		if (StringUtils.isNotBlank(dangerGoodName)) {
			sbf.append(" and dangerGoodName like ?");
			params.add("%" + dangerGoodName + "%");
		}

		// 危险性类别（数据字典）
		if (dictId != null) {
			sbf.append(" and dictId = ?");
			params.add(dictId);
		}

		return super.findAll(sbf.toString(), pageable, params.toArray());
	}
	
	public List<DangerSourcesGood> findAll() {
		return super.unDeleted().findAll();
	}
	
}
