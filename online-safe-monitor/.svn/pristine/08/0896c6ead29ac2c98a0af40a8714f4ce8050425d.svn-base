package cn.com.qytx.hotline.phonetask.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 试题
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:27:55 PM
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_question")
public class Question extends BaseDomain implements Serializable {
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
	 * 试题名称
	 */
	private String name;
	/**
	 * 试题类型 1单选题 2多选题 3问答题
	 */
	@Column(name = "type_id")
	private Integer typeId;
	/**
	 * 排序
	 */
	@Column(name = "order_list")
	private Integer orderList;
	/**
	 * 所属试卷
	 */
	@JoinColumn(name = "info_id")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Questionnaire questionnaire;
	/**
	 * 试题项
	 */
	@OrderBy("orderList asc")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
	private List<QuestionItem> items = new ArrayList<QuestionItem>();
	/**
	 * 答案
	 */
	private String answer;

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

	@Column(name = "name", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type_id")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "order_list")
	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<QuestionItem> getItems() {
		return items;
	}

	public void setItems(List<QuestionItem> items) {
		this.items = items;
	}

	@Transient
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * 功能：
	 * @return
	 */
	public String getQuestionStr(int i) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<li>");
		sbf.append("<p class='blue' ><span><font class='mr5'>"+i+".</font>问题：</span>"+this.name+"</p>");
		String questionTypeStr="";
		switch(this.typeId){
			case 1:
				questionTypeStr="单选";
				break;
			case 2:
				questionTypeStr="多选";
				break;
			case 3:
				questionTypeStr="问答";
				break;
			default:
				break;
		}
		sbf.append("<p><span>类型：</span>");
		sbf.append(questionTypeStr);
		sbf.append("</p>");
		if(this.typeId==1 || this.typeId==2){
			int j=1;
			sbf.append("<p><span>选项：</span>");
			if(items!=null){
				for(QuestionItem qi : items){
					if(j==1){
						sbf.append("（"+ j++ +"）");
						sbf.append(qi.getContent());
						sbf.append("</p>");
					}else{
						sbf.append("</p>");
						sbf.append("<p style='padding-left:42px;'>");
						sbf.append("（"+ j++ +"）");
						sbf.append(qi.getContent());
						sbf.append("</p>");
					}
				}
			}
		}
		sbf.append("</li>");
		return sbf.toString();
	}

	
	
}
