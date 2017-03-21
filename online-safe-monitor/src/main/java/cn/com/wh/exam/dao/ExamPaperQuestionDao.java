package cn.com.wh.exam.dao;


import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamPaperQuestion;
import cn.com.wh.exam.domain.SearchVo;

@Component
public class ExamPaperQuestionDao extends BaseDao<ExamPaperQuestion, Integer>{
	
	/**
	 * 通过试卷id获得试题列表
	 * @param paperId
	 * @return list
	 */
	public List<ExamPaperQuestion> findListByPaperId(Integer paperId) {
		StringBuffer condition=new StringBuffer(" 1=1");
	
		if (null!=paperId) {
			condition.append(" and paperId="+paperId);
		}
		return super.dataFilter("121211").findAll(condition.toString());
	}
	/**
	 * 通过试卷id获得试题列表
	 * @param paperId
	 * @return page
	 */
	public Page<ExamPaperQuestion> findPageByPaperId(Pageable page,Integer paperId) {
		StringBuffer condition=new StringBuffer(" 1=1");
	
		if (null!=paperId) {
			condition.append(" and paperId="+paperId);
		}
		return super.dataFilter("121211").findAll(condition.toString(), page);
	}
	/**
	 * 保存实体类
	 * @param paper
	 * @return
	 */
	public void saveAllPaper(List<ExamPaperQuestion> list){
		super.saveOrUpdate(list);
	}
	
}
