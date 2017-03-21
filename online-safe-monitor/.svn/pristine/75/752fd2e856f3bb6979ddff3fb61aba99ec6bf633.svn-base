package cn.com.wh.exam.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.wh.exam.domain.ExamUserTestLog;

@Component
public class ExamUserTestLogDao extends BaseDao<ExamUserTestLog, Integer>{
	
	public List<ExamUserTestLog> getExamUserTestLogList(Integer userTestId){
		return super.dataFilter("121211").findAll("userTestId=?", userTestId);
	}
	
}
