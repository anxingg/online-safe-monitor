package cn.com.qytx.hotline.phonetask.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.oa.util.TimeUtil;
import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 用户试卷试题
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:43:00 PM
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_question_user")
public class QuestionnaireUser extends BaseDomain implements Serializable {

	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 创建时间设置当前时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime = new Timestamp(Calendar.getInstance()
			.getTimeInMillis());
	/**
	 * 修改时间设置当前时间
	 */
	@Column(name = "update_time")
	private Timestamp updateTime = new Timestamp(Calendar.getInstance()
			.getTimeInMillis());

	/**
	 * 创建人
	 */
	@JoinColumn(name = "create_user_id", updatable = false)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private UserInfo createUser;
	/**
	 * 修改人
	 */
	@JoinColumn(name = "last_update_user_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private UserInfo updateUser;
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name = "is_delete")
	private Integer isDelete = 0;

	/**
	 * 公司ID
	 */
	@Column(name = "compy_id")
	private Integer compyId;
	/**
	 * 被调查用户
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "surveyUser")
	private PhoneTaskUser surveyUser;
	/**
	 * 结束时间
	 */
	@Column(name = "end_time")
	private Timestamp endTime;

	/**
	 * 评论
	 */
	private String comment;
	/**
	 * 执行坐席
	 */
	@JoinColumn(name = "inquirer_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private UserInfo inquirerUser;// 执行坐席
	/**
	 * 状态 1成功 2：无人接听 3 电话正忙 4 呼叫转移 5 停机 6关机 7 空号 10回访中
	 */
	private Integer statue;
	/**
	 * 被调查的试卷
	 */
	@JoinColumn(name = "info_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Questionnaire questionnaire;
	/**
	 * 被调查的任务
	 */
	@JoinColumn(name = "task_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private PhoneTask taskId;

	/**
	 * 时长（秒）
	 */
	@Column(name = "call_length")
	private int callLength;
	/**
	 * 外呼时间
	 */
	@Column(name = "call_time")
	private Timestamp callTime;

	/**
	 * 权限控制
	 */
	@Column(name = "is_fork_group")
	private Integer isForkGroup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "create_time", updatable = false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public UserInfo getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserInfo createUser) {
		this.createUser = createUser;
	}

	public UserInfo getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(UserInfo updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "compy_id")
	public Integer getCompyId() {
		return compyId;
	}

	public void setCompyId(Integer compyId) {
		this.compyId = compyId;
	}

	/**
	 * @return the surveyUser
	 */
	public PhoneTaskUser getSurveyUser() {
		return surveyUser;
	}

	/**
	 * @param surveyUser
	 *            the surveyUser to set
	 */
	public void setSurveyUser(PhoneTaskUser surveyUser) {
		this.surveyUser = surveyUser;
	}

	@Column(name = "end_time")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "comment", length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public UserInfo getInquirerUser() {
		return inquirerUser;
	}

	public void setInquirerUser(UserInfo inquirerUser) {
		this.inquirerUser = inquirerUser;
	}

	@Column(name = "statue")
	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public int getCallLength() {
		return callLength;
	}

	public void setCallLength(int callLength) {
		this.callLength = callLength;
	}

	public Timestamp getCallTime() {
		return callTime;
	}

	public void setCallTime(Timestamp callTime) {
		this.callTime = callTime;
	}

	/**
	 * @return the taskId
	 */
	public PhoneTask getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(PhoneTask taskId) {
		this.taskId = taskId;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	/**
	 * 功能：转换对象为map形式
	 * 
	 * @param i
	 * @return
	 */
	public Map<String, Object> getQuestionnaireUserMap(int i) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 标识符
		map.put("id", id);
		// 序号
		map.put("no", i);
		map.put("vid", taskId == null ? null : taskId.getVid());
		// 外呼对象id
		Integer phoneUserid = 0;
		// 外呼对象(客户姓名)
		String outCallObject = "";
		// 电话(客户号码)
		String phone = "";
		// 外呼任务用户实体类
		PhoneTaskUser surveyUser = getSurveyUser();
		if (surveyUser != null) {
			outCallObject = StringUtils.defaultString(surveyUser.getUserName(),
					"");
			phone = StringUtils.defaultString(surveyUser.getPhone(), "");
			if (surveyUser.getVid() != null) {
				phoneUserid = surveyUser.getVid();
			}
		}
		map.put("phoneUserid", phoneUserid);
		map.put("outCallObject", outCallObject);
		map.put("phone", phone);
		// 执行坐席
		String seatName = "";
		// 坐席工号
		String loginName = "";
		if (inquirerUser != null) {
			seatName = StringUtils
					.defaultString(inquirerUser.getUserName(), "");
			loginName = StringUtils.defaultString(inquirerUser.getLoginName(),
					"");
		}
		map.put("seatName", seatName);
		map.put("workNo", loginName);
		// 回访时间
		map.put("returnVisitTimeStr",
				updateTime == null ? "" : TimeUtil.timestamp2Str(updateTime,
						"yyyy-MM-dd HH:mm"));
		// 开始时间
		map.put("callTimeStr",
				callTime == null ? "" : TimeUtil.timestamp2Str(callTime,
						"yyyy-MM-dd HH:mm:ss"));
		// 结束时间
		map.put("endTimeStr",
				endTime == null ? "" : TimeUtil.timestamp2Str(endTime,
						"yyyy-MM-dd HH:mm:ss"));
		// 时长

		map.put("callLength", callLength);

		// 回访结果
		map.put("statue", statue == null ? 0 : statue);
		String callResult = getCallResult(statue);
		map.put("callResult", callResult);
		return map;
	}

	/**
	 * 
	 * 功能：将状态值转换为字符串
	 * 
	 * @param statue
	 * @return
	 */
	private String getCallResult(Integer statue) {
		String callResult = "";
		if (statue != null) {
			switch (statue) {
			case 1:
				callResult = "成功";
				break;
			case 2:
				callResult = "无人接听";
				break;
			case 3:
				callResult = "电话正忙";
				break;
			case 4:
				callResult = "呼叫转移";
				break;
			case 5:
				callResult = "电话关机";
				break;
			case 6:
				callResult = "电话停机";
				break;
			case 7:
				callResult = "空号";
				break;
			case 8:
				callResult = "用户拒访";
				break;
			default:
				callResult = "";
				break;
			}
		}

		return callResult;
	}

}
