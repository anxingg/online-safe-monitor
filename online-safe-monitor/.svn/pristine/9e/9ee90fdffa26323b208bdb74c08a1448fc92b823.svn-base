package cn.com.wh.professor.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.professor.domain.Professor;

/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 安全生产专家
 * @创建时间 2015-09-25
 * @修改时间
 * @修改列表
 */
@Repository
public class ProfessorDao extends BaseDao<Professor, Integer> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6082402134426279167L;

	/**
	 * 分页查询 
	 * @param pageable
	 * @param name 姓名
	 * @param specialties 专业特长
	 * @param professorType 专业类别
	 * @return
	 */
	public Page<Professor> findProfessorByPage(Pageable pageable,String name, String specialties, Integer professorType) {
		String hql = " isDelete = 0 ";
        List<Object> params = new ArrayList<Object>();
        
    	if (professorType!=null && professorType!=-1)
        {
            hql += " and professorType = ?";
            params.add(professorType);
        }
    	
		if (!StringUtils.isEmpty(name))
	    {
			hql += " and name like ?";
	     	params.add("%" + name + "%");
	    }
		
		if (!StringUtils.isEmpty(specialties))
	    {
			hql += " and specialties like ?";
	     	params.add("%" + specialties + "%");
	    }
    	
       
        return super.findAll(hql, pageable, params.toArray());
		
	}
}
