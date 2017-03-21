package cn.com.qytx.hotline.phonetask.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.oa.util.TimeUtil;
import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 外呼任务实体类
 * 
 * @author lihuawei
 * 
 */
@Entity
@Table(name = "tb_outcall_task")
public class PhoneTask extends BaseDomain implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;

	/**
	 * 任务名称
	 */
	@Column
	private String taskName;

	/**
	 * 任务说明
	 */
	@Column
	private String taskExplain;

	/**
	 * 任务类型: 1,三包单回访; 2,其他回访
	 */
	@Column
	private Integer taskType;

	/**
	 * 需要外呼的数量
	 */
	@Column
	private Integer taskTotal;

	/**
	 * 已外呼的数量
	 */
	@Column
	private Integer taskCallCount;

	/**
	 * 外呼的成功数
	 */
	@Column
	private Integer taskSuccessCount;
	/**
	 * 外呼的失败数
	 */
	@Column
	private Integer taskFailureCount;

	/**
	 * 任务状态:1草稿 2 未开始 3 正在进行 4 暂停 5 已结束
	 */
	@Column
	private Integer taskState;
	@Transient
	private String taskStateVo;

	/**
	 * 任务开始时间
	 */
	@Column
	private Timestamp taskStartTime;
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name = "is_delete")
	private Integer isDelete = 0;
	/**
	 * 任务结束时间
	 */
	@Column
	private Timestamp taskEndTime;

	/**
	 * 外呼任务对象
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "taskId")
	private List<PhoneTaskUser> task_user = new ArrayList<PhoneTaskUser>();
	/**
	 * 坐席Id （以,号分割）
	 */
	@Column(length = 100)
	private String seatUserIds;

	@JoinColumn(name = "info_id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Questionnaire questionnaire;

	/**
	 * 创建时间设置当前时间
	 */
	@Column(name = "create_time", updatable = false)
	private Timestamp createTime;
	/**
	 * 修改时间设置当前时间
	 */
	@Column(name = "last_update_time")
	private Timestamp updateTime;
	/**
	 * 访问用户id（以,号分割）
	 */
	@Transient
	/**
	 * 新导入的外呼对象id
	 */
	private String callUserIds;
	@Transient
	/**
	 * 用户档案的外呼对象id
	 */
	private String crmUserIds;
	@Transient
	/**
	 * 已经保存过的外呼对象ids 
	 */
	private String phoneUserIds;
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
	 * 权限控制
	 */
	@Column(name = "is_fork_group")
	private Integer isForkGroup;

	// Constructors

	/* get set */
	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskExplain() {
		return taskExplain;
	}

	public void setTaskExplain(String taskExplain) {
		this.taskExplain = taskExplain;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getTaskTotal() {
		return taskTotal;
	}

	public void setTaskTotal(Integer taskTotal) {
		this.taskTotal = taskTotal;
	}

	public Integer getTaskCallCount() {
		if (taskCallCount == null) {
			taskCallCount = 0;
		}
		return taskCallCount;
	}

	public void setTaskCallCount(Integer taskCallCount) {
		this.taskCallCount = taskCallCount;
	}

	public Integer getTaskSuccessCount() {
		return taskSuccessCount;
	}

	public void setTaskSuccessCount(Integer taskSuccessCount) {
		this.taskSuccessCount = taskSuccessCount;
	}

	public Integer getTaskState() {
		return taskState;
	}



	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}

	public Timestamp getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Timestamp taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Timestamp getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Timestamp taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete
	 *            the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the task_user
	 */
	public List<PhoneTaskUser> getTask_user() {
		return task_user;
	}

	/**
	 * @param task_user
	 *            the task_user to set
	 */
	public void setTask_user(List<PhoneTaskUser> task_user) {
		this.task_user = task_user;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the createUser
	 */
	public UserInfo getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(UserInfo createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateUser
	 */
	public UserInfo getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 *            the updateUser to set
	 */
	public void setUpdateUser(UserInfo updateUser) {
		this.updateUser = updateUser;
	}

	public String getSeatUserIds() {
		return seatUserIds;
	}

	public void setSeatUserIds(String seatUserIds) {
		if (seatUserIds != null && !seatUserIds.startsWith(",")) {
			this.seatUserIds = "," + seatUserIds;
		} else {
			this.seatUserIds = seatUserIds;
		}
		if (seatUserIds != null && !seatUserIds.endsWith(",")) {
			this.seatUserIds = this.seatUserIds + ",";
		}

	}

	/**
	 * @return the questionnaire
	 */
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	/**
	 * @param questionnaire
	 *            the questionnaire to set
	 */
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Integer getTaskFailureCount() {
		if (taskFailureCount == null) {
			taskFailureCount = 0;
		}
		return taskFailureCount;
	}

	public void setTaskFailureCount(Integer taskFailureCount) {
		this.taskFailureCount = taskFailureCount;
	}

	/**
	 * @return the callUserIds
	 */
	public String getCallUserIds() {
		return callUserIds;
	}

	/**
	 * @param callUserIds
	 *            the callUserIds to set
	 */
	public void setCallUserIds(String callUserIds) {
		this.callUserIds = callUserIds;
	}

	/**
	 * @return the crmUserIds
	 */
	public String getCrmUserIds() {
		return crmUserIds;
	}

	/**
	 * @param crmUserIds
	 *            the crmUserIds to set
	 */
	public void setCrmUserIds(String crmUserIds) {
		this.crmUserIds = crmUserIds;
	}

	/**
	 * @return the phoneUserIds
	 */
	public String getPhoneUserIds() {
		return phoneUserIds;
	}

	/**
	 * @param phoneUserIds
	 *            the phoneUserIds to set
	 */
	public void setPhoneUserIds(String phoneUserIds) {
		this.phoneUserIds = phoneUserIds;
	}

	/**
	 * @return the taskStateVo
	 */
	public String getTaskStateVo() {
		return taskStateVo;
	}

	/**
	 * @param taskStateVo
	 *            the taskStateVo to set
	 */
	public void setTaskStateVo(String taskStateVo) {
		this.taskStateVo = taskStateVo;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	/**
	 * 功能：将对象转换为map格式
	 * 
	 * @param i 序号
	 * @return
	 */
	public Map<String, Object> PhoneTaskToMap(int i) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 标识符
		map.put("vid", vid);
		// 序号
		map.put("orderNumber", i);
		//总数量
		map.put("taskTotal",  taskTotal==null?0: taskTotal);
		//已回访
		map.put("taskCallCount",  taskCallCount==null?0: taskCallCount);
		//成功数
		map.put("taskSuccessCount", taskSuccessCount==null?0:taskSuccessCount);
		//未成功数
		map.put("taskFailureCount", taskFailureCount==null?0:taskFailureCount);
		
		map.put("taskStateStr", getTaskStateStr());
		//问卷id
		map.put("questionnaireId", questionnaire==null?"":questionnaire.getId());
		// 任务名称
		map.put("taskName", StringUtils.defaultString(taskName, ""));
		// 任务说明
		map.put("taskExplain", StringUtils.defaultString(taskExplain, ""));
		// 任务开始时间
		map.put("taskStartStr",
				taskStartTime == null ? "" : TimeUtil.timestamp2Str(
						taskStartTime, "yyyy-MM-dd"));
		// 任务结束时间
		map.put("taskEndStr",
				taskEndTime == null ? "" : TimeUtil.timestamp2Str(taskEndTime,
						"yyyy-MM-dd"));
		// 已回访
		// 总数量
		map.put("countTotal", ( taskCallCount == null ? 0 : taskCallCount ) + "/"
				+  (taskTotal == null ? 0 : taskTotal ) );
		// 任务状态
		map.put("taskState", taskState == null ? 0 : taskState);
		map.put("taskStateStr", getTaskStateStr());
		return map;
	}

	/**
	 * 
	 * 功能：将状态值转换为字符串
	 * 
	 * @param taskState
	 * @return
	 */
	public String getTaskStateStr() {
		String taskStateStr = "";
		if (taskState != null) {
			switch (taskState) {
			case 1:
				taskStateStr = "草稿";
				break;
			case 2:
				taskStateStr = "未开始";
				break;
			case 3:
				taskStateStr = "正在进行";
				break;
			case 4:
				taskStateStr = "暂停";
				break;
			case 5:
				taskStateStr = "已结束";
				break;
			default:
				taskStateStr = "";
				break;
			}
		}
		return taskStateStr;
	}
}
