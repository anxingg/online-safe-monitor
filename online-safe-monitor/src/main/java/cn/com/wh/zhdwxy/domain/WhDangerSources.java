/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-25 上午10:20:54 
 * 类说明 
 */
package cn.com.wh.zhdwxy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:     WhDangerSources.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-25 上午10:20:54 
 */
@Entity
@Table(name="tb_wh_danger_sources")
public class WhDangerSources {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vid")
	public Integer vid ;
	@Column(name="group_id")
	public Integer group_id ;
	@Column(name="group_name")
	public String group_name;
	@Column(name="danger_sources_name")
	public String danger_sources_name;
	@Column(name="address")
	public String address;
	@Column(name="danger_grade")
	public Integer danger_grade;
	@Column(name="danger_grade_name")
	public String danger_grade_name;
	@Column(name="grade")
	public Integer grade = 1;
	@Column(name="review_time")
	public Date review_time;
	@Column(name="review_end_time")
	public Date review_end_time;
	@Column(name="product_scale")
	public String product_scale;
	@Column(name="safety_measures")
	public String safety_measures;
	@Column(name="distance")
	public Integer distance;
	@Column(name="three_year_accident")
	public String three_year_accident;
	@Column(name="accident_measures")
	public String accident_measures;
	@Column(name="create_time")
	public Date create_time;
	@Column(name="last_update_time")
	public Date last_update_time;
	@Column(name="is_fork_group")
	public Integer is_fork_group ;
	@Column(name="is_delete")
	public Integer is_delete;
	@Column(name="mechanism")
	public String mechanism;//评价机构
	@Column(name="is_park")
	public String is_park;//是否位于化工（工业）园区
	@Column(name="men")
	public String men;//厂区边界外500米范围内人数估算值
	@Column(name="use_time")
	public Date use_time;//重大危险源投用时间
	@Column(name="r_value")
	public String r_value;//R值
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer groupId) {
		group_id = groupId;
	}
	public String getDanger_sources_name() {
		return danger_sources_name;
	}
	public void setDanger_sources_name(String dangerSourcesName) {
		danger_sources_name = dangerSourcesName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDanger_grade() {
		return danger_grade;
	}
	public void setDanger_grade(Integer dangerGrade) {
		danger_grade = dangerGrade;
	}
	public String getDanger_grade_name() {
		return danger_grade_name;
	}
	public void setDanger_grade_name(String dangerGradeName) {
		danger_grade_name = dangerGradeName;
	}
	public Date getReview_time() {
		return review_time;
	}
	public void setReview_time(Date reviewTime) {
		review_time = reviewTime;
	}
	public Date getReview_end_time() {
		return review_end_time;
	}
	public void setReview_end_time(Date reviewEndTime) {
		review_end_time = reviewEndTime;
	}
	public String getProduct_scale() {
		return product_scale;
	}
	public void setProduct_scale(String productScale) {
		product_scale = productScale;
	}
	public String getSafety_measures() {
		return safety_measures;
	}
	public void setSafety_measures(String safetyMeasures) {
		safety_measures = safetyMeasures;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getThree_year_accident() {
		return three_year_accident;
	}
	public void setThree_year_accident(String threeYearAccident) {
		three_year_accident = threeYearAccident;
	}
	public String getAccident_measures() {
		return accident_measures;
	}
	public void setAccident_measures(String accidentMeasures) {
		accident_measures = accidentMeasures;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Date getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(Date lastUpdateTime) {
		last_update_time = lastUpdateTime;
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
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String groupName) {
		group_name = groupName;
	}
	public String getIs_park() {
		return is_park;
	}
	public void setIs_park(String is_park) {
		this.is_park = is_park;
	}
	public String getMen() {
		return men;
	}
	public void setMen(String men) {
		this.men = men;
	}
	public String getMechanism() {
		return mechanism;
	}
	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	public Date getUse_time() {
		return use_time;
	}
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	public String getR_value() {
		return r_value;
	}
	public void setR_value(String r_value) {
		this.r_value = r_value;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
}
