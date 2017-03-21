package cn.com.wh.reliefgoods.domain;

/**
 * 功能:查询对象
 * 创建日期: 2015年3月18日
 */
public class SearchVo {
	
	private String title;//装备名称
	
	private Integer isShow;//是否显示
	
	private Integer groupId;//单位id
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	
}

