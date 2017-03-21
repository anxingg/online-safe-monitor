package cn.com.wh.company.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.wh.company.dao.SafetyInstitutionsDao;
import cn.com.wh.company.domain.SafetyInstitutions;
import cn.com.wh.company.service.ISafetyInstitutions;

@Transactional
@Service("sisService")
public class SafetyInstitutionsImpl implements ISafetyInstitutions {
	
	@Autowired
	private SafetyInstitutionsDao dao;

	@Override
	public SafetyInstitutions saveOrUpdate(SafetyInstitutions sis) {
		return dao.saveOrUpdate(sis);
	}

	@Override
	public SafetyInstitutions findByGroupId(Integer groupId) {
		return dao.findByGroupId(groupId);
	}

}
