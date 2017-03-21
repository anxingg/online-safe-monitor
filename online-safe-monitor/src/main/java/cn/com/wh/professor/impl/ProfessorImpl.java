package cn.com.wh.professor.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.professor.dao.ProfessorDao;
import cn.com.wh.professor.domain.Professor;
import cn.com.wh.professor.service.IProfessor;

/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 安全生产专家口类
 * @创建时间 2015-09-25
 * @修改时间
 * @修改列表
 */
@Service
public class ProfessorImpl extends BaseServiceImpl<Professor> implements IProfessor,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6501801646255981452L;
	
	@Autowired
	private ProfessorDao professorDao;

	@Override
	public Page<Professor> findProfessorByPage(Pageable pageable,String name, String specialties, Integer professorType) {
		
		return professorDao.findProfessorByPage(pageable,name,specialties,professorType);
	}
	
	

}
