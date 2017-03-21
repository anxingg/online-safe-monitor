package cn.com.wh.exam.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamPaperQuestion;
import cn.com.wh.exam.domain.SearchVo;

public interface IExamPaper extends BaseService<ExamPaper>{

	/**
	 * 功能：分页查询试题信息
	 * @return
	 */
	public Page<ExamPaper> findByPage(Pageable page,SearchVo search);
	
	/**
	 * 分页查询试卷的试题列表
	 */
	public Page<ExamPaperQuestion> findPaperQuestionByPage(Pageable page, Integer paperId);
	
	/**
	 * 保存试卷
	 * @return 0失败1成功
	 */
	public int savePaper(ExamPaper paper,List<ExamPaperQuestion> list);
	
	/**
	 * 删除试卷
	 * @return 0失败1成功
	 */
	public int deletePaper(Integer paperId);
	
	/**
	 * 更新试卷状态
	 * @param paperId
	 * @param state
	 * @return 0失败1成功
	 */
	public int updateState(Integer paperId,Integer state);
	
	/**
	 * 
	 * 功能：通过试卷ids获得试卷map
	 * @param search
	 * @return
	 */
	public Map<Integer,ExamPaper> findByIds(String paperIds);
	
	/**
	 * 
	 * 功能：获得分类下的所有试卷map
	 * @param search
	 * @return
	 */
	public Map<Integer,String> findByPaperType(SearchVo search);
}
