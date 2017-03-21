package cn.com.wh.exam.service;

import java.util.List;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamPaperQuestion;
import cn.com.wh.exam.domain.ExamQuestion;

public interface IExamPaperQuestion extends BaseService<ExamPaperQuestion>{
	
	/**
	 * 
	 * 功能：获取题的列表
	 * @param paperId
	 * @return
	 */
	public List<ExamQuestion> findListByPaperId(Integer paperId);
}
