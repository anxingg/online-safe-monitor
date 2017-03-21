/**
 * 
 */
package cn.com.wh.aqyh.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:     WuhaiSafeDanger.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-21 上午11:20:14 
 */
@Entity
@Table(name="tb_wuhai_safe_danger")
public class WuhaiSafeDanger {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vid")
	public Integer vid ;
	
	@Column(name="group_id")
	public int group_id;
	
	@Column(name="group_name")
	public String group_name;
	
	@Column(name="danger_name")
	public String danger_name;
	@Column(name="responsible_department")
	public String responsible_department;
	@Column(name="responsible")
	public String responsible;
	@Column(name="checkdate")
	public Timestamp checkdate;
	@Column(name="check_condition")
	public String check_condition;
	@Column(name="rectification_end_date")
	public Timestamp rectification_end_date;
	@Column(name="rectification")
	public String rectification;
	@Column(name="review_user")
	public String review_user;
	@Column(name="review_time")
	public Timestamp review_time;
	@Column(name="memo")
	public String memo ;
	@Column(name="is_fork_group")
	public int is_fork_group;
	@Column(name="data_source")
	public int data_source;
	@Column(name="create_time")
	public Timestamp create_time;
	@Column(name="create_user")
	public int create_user;
	@Column(name="last_update_user")
	public int last_update_user;
	@Column(name="last_update_time")
	public Timestamp last_update_time;
	@Column(name="is_delete")
	public int is_delete;
	@Column(name="can_see")
	public int can_see;
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int groupId) {
		group_id = groupId;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String groupName) {
		group_name = groupName;
	}
	public String getDanger_name() {
		return danger_name;
	}
	public void setDanger_name(String dangerName) {
		danger_name = dangerName;
	}
	public String getResponsible_department() {
		return responsible_department;
	}
	public void setResponsible_department(String responsibleDepartment) {
		responsible_department = responsibleDepartment;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public Timestamp getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(Timestamp checkdate) {
		this.checkdate = checkdate;
	}
	public String getCheck_condition() {
		return check_condition;
	}
	public void setCheck_condition(String checkCondition) {
		check_condition = checkCondition;
	}
	public Timestamp getRectification_end_date() {
		return rectification_end_date;
	}
	public void setRectification_end_date(Timestamp rectificationEndDate) {
		rectification_end_date = rectificationEndDate;
	}
	public String getRectification() {
		return rectification;
	}
	public void setRectification(String rectification) {
		this.rectification = rectification;
	}
	public String getReview_user() {
		return review_user;
	}
	public void setReview_user(String reviewUser) {
		review_user = reviewUser;
	}
	public Timestamp getReview_time() {
		return review_time;
	}
	public void setReview_time(Timestamp reviewTime) {
		review_time = reviewTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getIs_fork_group() {
		return is_fork_group;
	}
	public void setIs_fork_group(int isForkGroup) {
		is_fork_group = isForkGroup;
	}
	public int getData_source() {
		return data_source;
	}
	public void setData_source(int dataSource) {
		data_source = dataSource;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp createTime) {
		create_time = createTime;
	}
	public int getCreate_user() {
		return create_user;
	}
	public void setCreate_user(int createUser) {
		create_user = createUser;
	}
	public int getLast_update_user() {
		return last_update_user;
	}
	public void setLast_update_user(int lastUpdateUser) {
		last_update_user = lastUpdateUser;
	}
	public Timestamp getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(Timestamp lastUpdateTime) {
		last_update_time = lastUpdateTime;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int isDelete) {
		is_delete = isDelete;
	}
	public int getCan_see() {
		return can_see;
	}
	public void setCan_see(int canSee) {
		can_see = canSee;
	}

	
	
}
