package cn.com.wh.professor.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.professor.domain.Professor;



/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 安全生产专家口类
 * @创建时间 2015-09-25
 * @修改时间
 * @修改列表
 */
public interface IProfessor extends BaseService<Professor> {

	/**
	 * 分页查询 
	 * @param pageable
	 * @param name 姓名
	 * @param specialties 专业特长
	 * @param professorType 专业类别
	 * @return
	 */
	public Page<Professor> findProfessorByPage(Pageable pageable,String name, String specialties, Integer professorType);

}
