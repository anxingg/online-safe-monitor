package cn.com.qytx.hotline.ivr.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 项目名称：wzerp 类名称：话务员登记实体 类描述： 创建人：WangBin 创建时间：2012-11-21
 * 
 * @version
 */
@Entity
@Table(name = "MSIUser")
public class Msiuser extends BaseDomain implements Serializable{

	/**
     * 描述含义
     */
    private static final long serialVersionUID = 2165871130092391164L;

    /**
	 * 坐席序号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;

	/**
	 * 分机号码
	 */
	@Column(name = "UserNum")
	private String userNum;

	/**
	 * 工号
	 */
	@Column(name = "WorkNo")
	private String workNo;

	/**
	 * 登陆密码
	 */
	@Column(name = "Pass")
	private String pass;

	/**
	 * 姓名
	 */
	@Column(name = "Name")
	private String name;

	/**
	 * 坐席类型
	 */
	@Column(name = "MSIType")
	private Integer msitype;

	/**
	 * 坐席转接使用的电话既绑定号码
	 */
	@Column(name = "MSIPhone")
	private String msiphone;

	/**
	 * 坐席类型(按位操作)
	 */
	@Column(name = "MSIWorkType")
	private Integer msiWorkType;

	/**
	 * 坐席状态 0：离线 1：在线 2：停用 3：呼入通话 4：置闲 5：置忙 13：外呼通话
	 */
	@Column(name = "msiWorkState")
	private Integer state;

	/**
	 * 是否录音
	 */
	@Column(name = "BRecord")
	private Integer brecord;

	/**
	 * 最后登陆时间
	 */
	@Column(name = "LoginTime")
	private Timestamp loginTime;

	/**
	 * 最后登陆时间
	 */
	@Transient
	private String loginTimeStr;

	/**
	 * 创建时间
	 */
	@Column(name = "CreateTime")
	private Timestamp createTime;

	/**
	 * 坐席角色0：普通作息1：班长坐席
	 */
	@Column(name = "role")
	private Integer role;

	/**
	 * 单位序号
	 */
	@Column(name = "companyid")
	private Integer companyid;

	/**
	 * 班组序号
	 */
	@Column(name = "Teamid")
	private Integer teamid;
	/**
	 * 在某一个状态下的时长
	 */
	@Transient
	private Integer stateTime;
	
	@Column(name="is_fork_group")
	private Integer isForkGroup;

	/**
	 * @return the vid
	 */
	public Integer getVid() {
		return vid;
	}

	/**
	 * @param vid
	 *            the vid to set
	 */
	public void setVid(Integer vid) {
		this.vid = vid;
	}

	/**
	 * @return the userNum
	 */
	public String getUserNum() {
		return userNum;
	}

	/**
	 * @param userNum
	 *            the userNum to set
	 */
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	/**
	 * @return the workNo
	 */
	public String getWorkNo() {
		return workNo;
	}

	/**
	 * @param workNo
	 *            the workNo to set
	 */
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the msitype
	 */
	public Integer getMsitype() {
		return msitype;
	}

	/**
	 * @param msitype
	 *            the msitype to set
	 */
	public void setMsitype(Integer msitype) {
		this.msitype = msitype;
	}

	/**
	 * @return the msiphone
	 */
	public String getMsiphone() {
		return msiphone;
	}

	/**
	 * @param msiphone
	 *            the msiphone to set
	 */
	public void setMsiphone(String msiphone) {
		this.msiphone = msiphone;
	}

	/**
	 * @return the msiWorkType
	 */
	public Integer getMsiWorkType() {
		return msiWorkType;
	}

	/**
	 * @param msiWorkType
	 *            the msiWorkType to set
	 */
	public void setMsiWorkType(Integer msiWorkType) {
		this.msiWorkType = msiWorkType;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the brecord
	 */
	public Integer getBrecord() {
		return brecord;
	}

	/**
	 * @param brecord
	 *            the brecord to set
	 */
	public void setBrecord(Integer brecord) {
		this.brecord = brecord;
	}

	/**
	 * @return the loginTime
	 */
	public Timestamp getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(Timestamp loginTime) {
		if (null != loginTime) {
			this.setLoginTimeStr(DateTimeUtil.timestampToString(loginTime,
					CallCenterConst.HOUR_MIN));
		}
		this.loginTime = loginTime;
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

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getTeamid() {
		return teamid;
	}

	public void setTeamid(Integer teamid) {
		this.teamid = teamid;
	}

	public String getLoginTimeStr() {
		return loginTimeStr;
	}

	public void setLoginTimeStr(String loginTimeStr) {
		this.loginTimeStr = loginTimeStr;
	}

	public Integer getStateTime() {
		return stateTime;
	}

	public void setStateTime(Integer stateTime) {
		this.stateTime = stateTime;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	
	/**
	 * 功能：将对象转为map(特殊的不写，例：Timestamp的时间,数据字典查询的类型)
	 * @return
	 */
	public Map<String, Object> msiuserToMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		//标识符
		map.put("vid", vid);
		//工号
		map.put("workNo", StringUtils.defaultString(workNo, ""));
		//姓名
		map.put("name", StringUtils.defaultString(name, ""));
		//坐席状态 0：离线 1：在线 2：停用 3：呼入通话 4：置闲 5：置忙 13：外呼通话
		map.put("state", state==null?0:state);
		//坐席转接使用的电话既绑定号码
		map.put("msiphone", StringUtils.defaultString(msiphone, ""));
		//在某一个状态下的时长
		map.put("stateTime", stateTime==null?0:stateTime);
		//坐席类型（0内线，1外线）Integer
		map.put("msiType", msitype==null?0:msitype);
		//坐席类型（0内线，1外线）String
		map.put("msiTypeStr", msitype == 0 ? "内线" : "外线");
		return map;
	}
	
}