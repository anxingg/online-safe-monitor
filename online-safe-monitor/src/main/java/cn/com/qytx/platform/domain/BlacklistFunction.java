package cn.com.qytx.platform.domain;

import java.io.Serializable;
/**
 * 功能:黑名单设置实体
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2015-2-12
 * 修改日期: 2015-2-12
 * 修改列表:
 */
public class BlacklistFunction implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4995834732524508840L;
	/**
	 * 是否启用黑名单 1：不启用 2：启用
	 */
	private Integer isEnableBlacklist;
	/**
	 * 参数描述
	 */
	private String parDescribe;
	/**
	 * 参数项
	 */
	private String parItems;
	
	public Integer getIsEnableBlacklist() {
		return isEnableBlacklist;
	}
	public void setIsEnableBlacklist(Integer isEnableBlacklist) {
		this.isEnableBlacklist = isEnableBlacklist;
	}
	public String getParDescribe() {
		return parDescribe;
	}
	public void setParDescribe(String parDescribe) {
		this.parDescribe = parDescribe;
	}
	public String getParItems() {
		return parItems;
	}
	public void setParItems(String parItems) {
		this.parItems = parItems;
	}
	
}
