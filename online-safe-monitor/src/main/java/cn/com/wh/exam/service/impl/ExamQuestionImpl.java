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
import cn.com.wh.exam.dao.ExamQuestionDao;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.ExamQuestionService;


@Service
@Transactional
public class ExamQuestionImpl extends BaseServiceImpl<ExamQuestion> implements ExamQuestionService{

	@Autowired
	private ExamQuestionDao examQuestionDao;

	@Override
	public Page<ExamQuestion> findByPage(Pageable page, SearchVo search) {
		// TODO Auto-generated method stub
		return examQuestionDao.findByPage(page, search);
	}
	
	/**
	 * 
	 * 功能：通过试题id获得试题列表（不加数据权限）
	 * @param questionIds
	 * @param search
	 * @return
	 */
	public Map<Integer,ExamQuestion> findByQuestionIds(String questionIds){
		Map<Integer, ExamQuestion> map = new HashMap<Integer, ExamQuestion>();
		List<ExamQuestion> list = examQuestionDao.findByQuestionIds(questionIds);
		if (list!=null&&list.size()>0) {
			for (ExamQuestion examQuestion : list) {
				map.put(examQuestion.getId(), examQuestion);
			}
		}
		return map;
	}

	@Override
	public int updateQuestionState(Integer questionId, Integer state) {
		// TODO Auto-generated method stub
		return examQuestionDao.updateQuestionState(questionId, state);
	}
	
	
}
