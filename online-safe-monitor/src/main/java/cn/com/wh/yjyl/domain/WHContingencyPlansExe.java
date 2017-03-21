package cn.com.wh.yjyl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:     WHContingencyPlans.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-27 下午02:11:34 
 */
@Entity
@Table(name="tb_wuhai_contingency_plans_exe")
public class WHContingencyPlansExe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vid")
	public Integer vid ;
	@Column(name="plan_id")
	public Integer plan_id;
	@Column(name="plan_no")
	public String plan_no ;
	@Column(name="group_id")
	public Integer group_id ;
	@Column(name="company_name")
	public String company_name;
	@Column(name="organize_group")
	public String organize_group;
	@Column(name="exercise_name")
	public String exercise_name;
	@Column(name="exercise_program")
	public String exercise_program;
	@Column(name="exercise_purpose")
	public String exercise_purpose;
	@Column(name="exercise_time")
	public Date exercise_time;
	@Column(name="exercise_address")
	public String exercise_address;
	@Column(name="exercise_people")
	public String exercise_people;
	@Column(name="exercise_records")
	public String exercise_records;
	@Column(name="rescue_reviews")
	public String rescue_reviews;
	@Column(name="reviews")
	public String reviews;
	@Column(name="photo_path")
	public String photo_path;
	@Column(name="attachment_ids")
	public String attachment_ids;
	@Column(name="create_time")
	public Date create_time;
	@Column(name="is_fork_group")
	public Integer is_fork_group;
	@Column(name="is_delete")
	public Integer is_delete;
	@Column(name="plan_type")
	public Integer plan_type;
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Integer getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Integer planId) {
		plan_id = planId;
	}
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String planNo) {
		plan_no = planNo;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer groupId) {
		group_id = groupId;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String companyName) {
		company_name = companyName;
	}
	public String getOrganize_group() {
		return organize_group;
	}
	public void setOrganize_group(String organizeGroup) {
		organize_group = organizeGroup;
	}
	public String getExercise_name() {
		return exercise_name;
	}
	public void setExercise_name(String exerciseName) {
		exercise_name = exerciseName;
	}
	public String getExercise_program() {
		return exercise_program;
	}
	public void setExercise_program(String exerciseProgram) {
		exercise_program = exerciseProgram;
	}
	public String getExercise_purpose() {
		return exercise_purpose;
	}
	public void setExercise_purpose(String exercisePurpose) {
		exercise_purpose = exercisePurpose;
	}
	public Date getExercise_time() {
		return exercise_time;
	}
	public void setExercise_time(Date exerciseTime) {
		exercise_time = exerciseTime;
	}
	public String getExercise_address() {
		return exercise_address;
	}
	public void setExercise_address(String exerciseAddress) {
		exercise_address = exerciseAddress;
	}
	public String getExercise_people() {
		return exercise_people;
	}
	public void setExercise_people(String exercisePeople) {
		exercise_people = exercisePeople;
	}
	public String getExercise_records() {
		return exercise_records;
	}
	public void setExercise_records(String exerciseRecords) {
		exercise_records = exerciseRecords;
	}
	public String getRescue_reviews() {
		return rescue_reviews;
	}
	public void setRescue_reviews(String rescueReviews) {
		rescue_reviews = rescueReviews;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photoPath) {
		photo_path = photoPath;
	}
	public String getAttachment_ids() {
		return attachment_ids;
	}
	public void setAttachment_ids(String attachmentIds) {
		attachment_ids = attachmentIds;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Integer getIs_fork_group() {
		return is_fork_group;
	}
	public void setIs_fork_group(Integer isForkGroup) {
		is_fork_group = isForkGroup;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer isDelete) {
		is_delete = isDelete;
	}
	public Integer getPlan_type() {
		return plan_type;
	}
	public void setPlan_type(Integer planType) {
		plan_type = planType;
	}
}
