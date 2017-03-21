package cn.com.wh.exam.dao;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.SearchVo;

@Component
public class ExamPaperDao extends BaseDao<ExamPaper, Integer>{
	
	/**
	 * 
	 * 功能：试题列表
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<ExamPaper> findByPage(Pageable page,SearchVo search) {
		StringBuffer condition=new StringBuffer(" 1=1");
	    if(search!=null){
	    	if(StringUtils.isNotBlank(search.getTitle())){
				condition.append(" and paperName like '%"+search.getTitle()+"%'");
			}
		
			if (null!=search.getExamType()) {
				condition.append(" and paperType="+search.getExamType());
			}
			
			if (search.getState() != null) {
				condition.append(" and state="+search.getState());
			}
	    }
		return unDeleted().findAll(condition.toString(), page);
	}
	
	/**
	 * 
	 * 功能：试题列表（包括删除的）
	 * @param page
	 * @param search
	 * @return
	 */
	public List<ExamPaper> findByPaperType(SearchVo search) {
		StringBuffer condition=new StringBuffer(" 1=1");
	
		if(StringUtils.isNotBlank(search.getTitle())){
			condition.append(" and paperName like '%"+search.getTitle()+"%'");
		}
	
		if (null!=search.getExamType()) {
			condition.append(" and paperType="+search.getExamType());
		}
		
		if (search.getState() != null) {
			condition.append(" and state="+search.getState());
		}
		return super.findAll(condition.toString());
	}
	
	/**
	 * 保存实体类
	 * @param paper
	 * @return
	 */
	public void savePaper(ExamPaper paper){
		super.saveOrUpdate(paper);
	}
	
	/**
	 * 更新试卷状态
	 * @param paperId
	 * @param state
	 */
	public void updateState(Integer paperId,Integer state){
		String hql = "update ExamPaper set state=?1 where id=?2";
		executeQuery(hql,state,paperId);
	}
	
	/**
	 * 
	 * 功能：通过试卷ids获得试卷列表
	 * @param paperIds
	 * @return
	 */
	public List<ExamPaper> findByIds(String paperIds) {
		if (StringUtils.isNotBlank(paperIds)) {
			if (paperIds.startsWith(",")) {
				paperIds = paperIds.substring(1);
			}
			if (paperIds.endsWith(",")) {
				paperIds = paperIds.substring(0, paperIds.length()-1);
			}
			return super.find("select * from ExamPaper where id in ("+paperIds+")");
		}else {
			return null;
		}
		
	}
	
}
