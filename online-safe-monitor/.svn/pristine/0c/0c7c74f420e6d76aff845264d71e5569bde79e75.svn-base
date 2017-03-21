package cn.com.wh.exam.service;

import java.util.List;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamUserTest;
import cn.com.wh.exam.domain.ExamUserTestLog;
import cn.com.wh.exam.domain.SearchVo;

public interface IExamUserTest extends BaseService<ExamUserTest>{

	/**
	 * 
	 * 功能：交卷
	 * @param examUserTest
	 * @return
	 */
	public void finishPaper(ExamUserTest examUserTest,List<ExamUserTestLog> examUserTestLog);
	
	/**
	 * 
	 * 功能：通过考试id，考试人姓名，考试人身份证号查询考试记录
	 * @param testId
	 * @param userName
	 * @param idcard
	 * @return
	 */
	public List<ExamUserTest> getExamTestList(Integer testId,String userName,String idcard);
	
	/**
	 * 
	 * 功能：查询考试成绩分页
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<ExamUserTest> findByPage(Pageable page,SearchVo vo);
	
	
}
