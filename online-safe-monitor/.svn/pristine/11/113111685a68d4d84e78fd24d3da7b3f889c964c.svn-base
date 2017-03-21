package cn.com.wh.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.exam.dao.ExamPaperDao;
import cn.com.wh.exam.dao.ExamUserTestDao;
import cn.com.wh.exam.dao.ExamUserTestLogDao;
import cn.com.wh.exam.domain.ExamUserTest;
import cn.com.wh.exam.domain.ExamUserTestLog;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.IExamUserTest;


@Service
@Transactional
public class ExamUserTestService extends BaseServiceImpl<ExamUserTest> implements IExamUserTest{
	
	//考试记录dao
	@Autowired
	private ExamUserTestDao examUserTestDao;
	@Autowired
	private ExamUserTestLogDao examUserTestLogDao;
	
	@Override
	public void finishPaper(ExamUserTest examUserTest,List<ExamUserTestLog> examUserTestLog) {
		// TODO Auto-generated method stub
		 examUserTestDao.saveOrUpdate(examUserTest);
		 if(examUserTestLog!=null&&examUserTestLog.size()>0){
			 for (ExamUserTestLog examUserTestLog2 : examUserTestLog) {
				 examUserTestLog2.setUserTestId(examUserTest.getId());
			}
		 }
		 examUserTestLogDao.saveOrUpdate(examUserTestLog);
	}
	
	/**
	 * 
	 * 功能：通过考试id，考试人姓名，考试人身份证号查询考试记录
	 * @param testId
	 * @param userName
	 * @param idcard
	 * @return
	 */
	public List<ExamUserTest> getExamTestList(Integer testId,String userName,String idcard){
		return examUserTestDao.getExamTestList(testId, userName, idcard);
	}
	
	/**
	 * 
	 * 功能：查询考试成绩分页
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<ExamUserTest> findByPage(Pageable page,SearchVo vo){
		return examUserTestDao.findByPage(page, vo);
	}

}
