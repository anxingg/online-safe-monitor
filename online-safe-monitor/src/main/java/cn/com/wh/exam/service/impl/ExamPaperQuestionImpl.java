package cn.com.wh.exam.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.exam.dao.ExamPaperQuestionDao;
import cn.com.wh.exam.dao.ExamQuestionDao;
import cn.com.wh.exam.dao.ExamQuestionItemDao;
import cn.com.wh.exam.domain.ExamPaperQuestion;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.ExamQuestionItem;
import cn.com.wh.exam.service.IExamPaperQuestion;


@Service
@Transactional
public class ExamPaperQuestionImpl extends BaseServiceImpl<ExamPaperQuestion> implements IExamPaperQuestion{

	@Autowired
	private ExamPaperQuestionDao examPaperQuestionDao;
	@Autowired
	private ExamQuestionDao examQuestionDao;
	@Autowired
	private ExamQuestionItemDao examQuestionItemDao;
	@Override
	public List<ExamQuestion> findListByPaperId(Integer paperId) {
		List<ExamPaperQuestion> examTests = examPaperQuestionDao.findListByPaperId(paperId);
		List<ExamQuestion> questions = new ArrayList<ExamQuestion>();
			for (int i = 0; i < examTests.size(); i++) {
				ExamQuestion question = new ExamQuestion();
				int questionId=examTests.get(i).getQuestionId();
				question=examQuestionDao.findOne(questionId);
				List<ExamQuestionItem> questionItems=examQuestionItemDao.findByQuestionIdunDeleted(questionId);
				question.setQuestionItems(questionItems);
				question.setPaperQuestionScore(examTests.get(i).getScore());
				questions.add(question);
			}
		return questions;
	}

}
