package cn.com.wh.exam.domain;

/**
 * 功能:查询对象
 * 创建日期: 2015年3月18日
 */
public class SearchVo {
	
	private String title;//试题名称/试卷名称/考试名称
	
	private Integer examType;//类型
	
	private Integer state;//状态
	
	private Integer groupId;//单位id
	
	private String examTime;//考试日期

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
}

