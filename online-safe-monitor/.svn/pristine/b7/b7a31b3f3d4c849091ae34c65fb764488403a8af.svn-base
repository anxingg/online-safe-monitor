package cn.com.wh.emergencydepartment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.emergencydepartment.dao.EmergencyDepartmentDao;
import cn.com.wh.emergencydepartment.domain.EmergencyDepartment;
import cn.com.wh.emergencydepartment.domain.SearchVo;
import cn.com.wh.emergencydepartment.service.IEmergencyDepartment;


@Service
@Transactional
public class EmergencyDepartmentImpl extends BaseServiceImpl<EmergencyDepartment> implements IEmergencyDepartment{

	//应急机构dao
	@Autowired
	private EmergencyDepartmentDao emergencyDepartmentDao;

	@Override
	public Page<EmergencyDepartment> findByPage(Pageable page,
			SearchVo search) {
		return emergencyDepartmentDao.findByPage(page, search);
	}

	@Override
	public int saveEmergency(EmergencyDepartment info) {
		int oldId = 0;
		if (info.getId()!=null) {
			oldId = info.getId();
		}
		emergencyDepartmentDao.saveED(info);
		if (info.getIsShow() == 0 && oldId == 0) {//企业新增应急机构
			dealShowOfGroup(info.getGroupId());
		}
		return 0;
	}

	@Override
	public int deleteEmergency(Integer emergencyId) {
		EmergencyDepartment info = emergencyDepartmentDao.findOne(emergencyId);
		emergencyDepartmentDao.delete(emergencyId,false);
		if (info.getDepartType()!=3&&info.getIsShow()==1) {
			dealShowOfGroup(info.getGroupId());
		}
		return 0;
	}

	public void dealShowOfGroup(Integer groupId){
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "isShow"),new Sort.Order(Direction.DESC, "departType"),new Sort.Order(Direction.ASC, "createTime"));
		SearchVo vo = new SearchVo(); 
		vo.setGroupId(groupId);
		List<EmergencyDepartment> list = emergencyDepartmentDao.findList(vo,sort);
		if (list!=null&&list.size()>0) {
			EmergencyDepartment info = list.get(0);
			if (info.getIsShow()==0) {
				emergencyDepartmentDao.updateShow(info.getId(), 1);
			}
		}
	}
}
