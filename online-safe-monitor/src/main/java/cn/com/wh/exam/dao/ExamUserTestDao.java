package cn.com.wh.exam.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.exam.domain.ExamUserTest;
import cn.com.wh.exam.domain.SearchVo;

@Component
public class ExamUserTestDao extends BaseDao<ExamUserTest, Integer>{

	/**
	 * 
	 * 功能：通过考试id，考试人姓名，考试人身份证号查询考试记录
	 * @param testId
	 * @param userName
	 * @param idcard
	 * @return
	 */
	public List<ExamUserTest> getExamTestList(Integer testId,String userName,String idcard){
		StringBuffer condition=new StringBuffer(" 1=1");
		if (null!=testId) {
			condition.append(" and paperId="+testId);
		}
		if(StringUtils.isNotEmpty(userName)){
			condition.append(" and userName = '"+userName+"'");
		}
		if(StringUtils.isNotEmpty(idcard)){
			condition.append(" and idCardNum = '"+idcard+"'");
		}
		return super.findAll(condition.toString());
	}
	
	/**
	 * 
	 * 功能：查询考试成绩分页
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<ExamUserTest> findByPage(Pageable page,SearchVo vo){
		StringBuffer condition=new StringBuffer(" 1=1");
		List<Object> param = new ArrayList<Object>();
		if (vo!=null&&(StringUtils.isNotBlank(vo.getTitle())||vo.getExamType()!=null)) {
			//condition.append(" and testId in (select id from ExamTest where 1=1 ");
			condition.append(" and testId in (select id from ExamPaper where 1=1 ");
			if (StringUtils.isNotBlank(vo.getTitle())) {
				condition.append(" and paperName like ?");
				param.add("%"+vo.getTitle()+"%");
			}
			if (vo.getExamType()!=null) {
				condition.append(" and paperType=?");
				param.add(vo.getExamType());
			}
			condition.append(" )");
		}
		if(vo.getGroupId()!=null){
			condition.append(" and groupId = ?");
			param.add(vo.getGroupId());
		}
		if(StringUtils.isNotEmpty(vo.getExamTime())){
			String startTimeStr = vo.getExamTime()+"-01 00:00:00";
			Timestamp startTime = null;
			try {
				startTime = Timestamp.valueOf(startTimeStr);
			} catch (Exception e) {
				startTime = null;
			}
			if (startTime!=null) {
				Timestamp endTime = getMouthAgoTime(startTimeStr, 1);
				if (endTime!=null) {
					condition.append(" and startTime >? and endTime<?");
					param.add(startTime);
					param.add(endTime);
				}
			}
		}
		return super.findAll(condition.toString(), page,param.toArray());
	}
	
	/**
     * 得到一个时间n分钟之后/之前的时间
     */
    private static Timestamp getMouthAgoTime(String oldTime,int mouth){
    	String oneHoursAgoTime = "";
    	try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date newdate = df.parse(oldTime);
            Calendar c = Calendar.getInstance();
            c.setTime(newdate);
            c.add(Calendar.MONTH, mouth);
            oneHoursAgoTime = df.format(c.getTime());
            return Timestamp.valueOf(oneHoursAgoTime);
		} catch (Exception e) {
			return null;
		}
    }
	
	
}
