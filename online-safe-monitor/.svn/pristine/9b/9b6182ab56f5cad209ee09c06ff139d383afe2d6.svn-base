package cn.com.wh.exam.service;

import java.util.Map;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.SearchVo;

public interface ExamQuestionService extends BaseService<ExamQuestion>{

	/**
	 * 功能：分页查询试题信息
	 * @return
	 */
	public Page<ExamQuestion> findByPage(Pageable page,SearchVo search);
	
	/**
	 * 
	 * 功能：通过试题id获得试题列表（不加数据权限）
	 * @param questionIds
	 * @param search
	 * @return
	 */
	public Map<Integer,ExamQuestion> findByQuestionIds(String questionIds);

	/**
	 * 
	 * 功能：是否生效 0生效 1失效
	 * @param questionId
	 * @param state
	 * @return
	 */
    public int updateQuestionState(Integer questionId,Integer state);
}
