package cn.com.wh.exam.service;

import java.util.Map;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.ExamTest;
import cn.com.wh.exam.domain.SearchVo;

public interface ExamTestService extends BaseService<ExamTest>{

	/**
	 * 
	 * 功能：分页查询考试页面
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<ExamTest> findByPage(Pageable page,SearchVo search);

	/**
	 * 
	 * 功能：是否生效
	 * @param examTestId
	 * @param state
	 * @return
	 */
    public int updateTestState(Integer examTestId,Integer state);
    
    /**
	 * 查询所有考试的考试类型
	 * @return Map<testId,Type>
	 */
	public Map<Integer, Integer> getAllTestType();
}
