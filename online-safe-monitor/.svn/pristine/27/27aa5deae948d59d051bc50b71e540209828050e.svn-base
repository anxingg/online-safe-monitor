package cn.com.wh.exam.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.wh.exam.domain.ExamQuestionItem;
import cn.com.wh.exam.domain.ExamUserTest;
import cn.com.wh.exam.domain.ExamUserTestLog;
import cn.com.wh.exam.service.impl.ExamUserTestLogService;
import cn.com.wh.exam.service.impl.ExamUserTestService;
import cn.com.wh.util.DataInitUtil;

/**
 * 
 * 功能:查看考卷
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年9月6日
 * 修改日期: 2015年9月6日
 * 修改列表:
 */
public class ExamTestDetailViewAction extends BaseActionSupport{

	private Integer userTestId;
	

	@Autowired
	private IGroup groupService;
	
	@Autowired
	private ExamUserTestLogService examUserTestLogService;
	
	@Autowired
	private ExamUserTestService examUserTestService;
	
	/**
	 * 
	 * 功能：查看试卷详情
	 * @return
	 */
	public String examTestView(){
		ExamUserTest examUserTest = examUserTestService.findOne(userTestId);
		String companyName = "";
		if(examUserTest!=null){
			companyName = DataInitUtil.companyMap.get(examUserTest.getGroupId());
		}
		List<ExamUserTestLog> examUserTestLogList = examUserTestLogService.getExamUserTestLogList(userTestId);
		List<ExamUserTestLog> examUserTestList = new  ArrayList<ExamUserTestLog>();
		if(examUserTestLogList!=null&&examUserTestLogList.size()>0){
			for (ExamUserTestLog examUserTestLog : examUserTestLogList) {
				String itemStr = examUserTestLog.getAllItem();
				if(StringUtils.isNotBlank(itemStr)){
					String itemArr[] = itemStr.split("&");
					List<ExamQuestionItem> questionItemList = new ArrayList<ExamQuestionItem>();
					for (int i = 0; i < itemArr.length; i++) {
						ExamQuestionItem examQuestionItem = new ExamQuestionItem();
						String itemNoArr[] = itemArr[i].split(":");
						examQuestionItem.setItemCode(itemNoArr[0]);
						examQuestionItem.setItemName(itemNoArr[1]);
						questionItemList.add(examQuestionItem);
					}
					examUserTestLog.setExamQuestionItemList(questionItemList);
				}
				examUserTestList.add(examUserTestLog);
			}
		}
		this.getRequest().setAttribute("questionList", examUserTestLogList);
		this.getRequest().setAttribute("examUserTest", examUserTest);
		this.getRequest().setAttribute("companyName", companyName);
		return "success";
	}

	public Integer getUserTestId() {
		return userTestId;
	}

	public void setUserTestId(Integer userTestId) {
		this.userTestId = userTestId;
	}
	
	
}
