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
import cn.com.wh.exam.dao.ExamPaperQuestionDao;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamPaperQuestion;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.IExamPaper;


@Service
@Transactional
public class ExamPaperImpl extends BaseServiceImpl<ExamPaper> implements IExamPaper{

	//试卷dao
	@Autowired
	private ExamPaperDao examPaperDao;
	//试卷试题对应关系dao
	@Autowired
	private ExamPaperQuestionDao examPaperQuestionDao;

	/**
	 * 分页查询试卷信息
	 */
	@Override
	public Page<ExamPaper> findByPage(Pageable page, SearchVo search) {
		return examPaperDao.findByPage(page, search);
	}
	/**
	 * 分页查询试卷的试题列表
	 */
	public Page<ExamPaperQuestion> findPaperQuestionByPage(Pageable page, Integer paperId) {
		return examPaperQuestionDao.findPageByPaperId(page, paperId);
	}
	/**
	 * 保存试卷
	 * @return 0失败1成功
	 */
	public int savePaper(ExamPaper paper,List<ExamPaperQuestion> list){
		try {
			examPaperDao.saveOrUpdate(paper);
			if (list!=null&&list.size()>0) {
				for (ExamPaperQuestion examPaperQuestion : list) {
					examPaperQuestion.setPaperId(paper.getId());
				}
			}
			examPaperQuestionDao.saveOrUpdate(list);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	/**
	 * 删除试卷
	 * @return 0失败1成功
	 */
	public int deletePaper(Integer paperId){
		try {
			examPaperDao.delete(paperId, false);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	/**
	 * 更新试卷状态
	 * @param paperId
	 * @param state
	 * @return 0失败1成功
	 */
	public int updateState(Integer paperId,Integer state){
		try {
			examPaperDao.updateState(paperId, state);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	/**
	 * 
	 * 功能：通过试卷ids获得试卷map
	 * @param search
	 * @return
	 */
	public Map<Integer,ExamPaper> findByIds(String paperIds){
		List<ExamPaper> list = examPaperDao.findByIds(paperIds);
		Map<Integer,ExamPaper> map = new HashMap<Integer, ExamPaper>();
		if (list!=null&&list.size()>0) {
			for (ExamPaper info : list) {
				map.put(info.getId(), info);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * 功能：获得分类下的所有试卷map
	 * @param search
	 * @return
	 */
	public Map<Integer,String> findByPaperType(SearchVo search){
		List<ExamPaper> list = examPaperDao.findByPaperType(search);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (list!=null&&list.size()>0) {
			for (ExamPaper info : list) {
				map.put(info.getId(), info.getPaperName());
			}
		}
		return map;
	}
}
