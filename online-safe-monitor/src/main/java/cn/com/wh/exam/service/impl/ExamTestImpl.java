package cn.com.wh.exam.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.exam.dao.ExamPaperDao;
import cn.com.wh.exam.dao.ExamTestDao;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamTest;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.ExamTestService;

@Service
@Transactional
public class ExamTestImpl extends BaseServiceImpl<ExamTest> implements ExamTestService{

	@Autowired
	private ExamTestDao examTestDao;
	@Autowired
	private ExamPaperDao examPaperDao;
	
	@Override
	public Page<ExamTest> findByPage(Pageable page, SearchVo search) {
		// TODO Auto-generated method stub
		return examTestDao.getExamTestList(page, search);
	}

	@Override
	public int updateTestState(Integer examTestId, Integer state) {
		// TODO Auto-generated method stub
		return examTestDao.updateTestState(examTestId, state);
	}
	/**
	 * 查询所有试卷类型
	 * @return Map<testId,Type>
	 */
	public Map<Integer, Integer> getAllTestType(){
		List<ExamPaper> list = examPaperDao.findAll();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (list!=null&&list.size()>0) {
			for (ExamPaper examPaper : list) {
				map.put(examPaper.getId(),examPaper.getPaperType());
			}
		}
		return map;
	}

}
