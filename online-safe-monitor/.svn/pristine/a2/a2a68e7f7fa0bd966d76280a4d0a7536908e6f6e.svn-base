package cn.com.qytx.hotline.user.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IUser;

import com.google.gson.Gson;

public class GetGroupAndUserTreeAction extends BaseActionSupport {

	private static final long serialVersionUID = 7417381634485871557L;
	
	private static final String SEX="sex";
	// 输入
	@Autowired
	private IGroup groupService;
	@Autowired
	private transient IUser userService;
	private int iDisplayStart;
	private int iDisplayLength;

	private Integer groupId;
	private String searchkey;

	// 输出

	/**
	 * 通讯录左侧的表
	 * 
	 * @return
	 */
	public String getAllUserInfoByPage() {          
		try {
			UserInfo userInfo = (UserInfo) this.getSession().getAttribute(
					"adminUser");
			List<UserInfo> list = userService.findAll();
			if (userInfo != null&&list!=null) {
				int iTotalRecords = list.size();
				int iTotalDisplayRecords = list.size() < 0 ? 0 : list.size();
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

				if (list != null&&list.size()>0) {
					for(UserInfo uInfo :list){
						if(uInfo.getIsDelete()!=null&&uInfo.getIsDelete().intValue()==1){
							list.remove(uInfo); //删除已经删除的用户
						}
					}
					// int i = (pageNum - 1) * iDisplayLength + 1;
					getAllUserInfoByPageOne( list,mapList);
				}
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("iTotalRecords", iTotalRecords);
				jsonMap.put("iTotalDisplayRecords", iTotalDisplayRecords);
				jsonMap.put("aaData", mapList);
				Gson json = new Gson();
				String jsons = json.toJson(jsonMap);
				PrintWriter writer = new PrintWriter(this.getResponse()
						.getWriter());
				writer.print(jsons);
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * getAllUserInfoByPage方法扩展
	 */
	private List<Map<String, Object>> getAllUserInfoByPageOne(List<UserInfo> list,List<Map<String, Object>> mapList){
		int pageNum = iDisplayStart / iDisplayLength + 1;
		int orderNumber = 0; // 序号
		for (int i = 0; i < list.size(); i++) {
			UserInfo user = list.get(i);
			// 0 userId, 1 userName, 2 sex, 3 phone,4 job,5
			// groupName,6 phone,7 companyId,8 loginName
			orderNumber++;
			Map<String, Object> map = new HashMap<String, Object>();
			// userId
			map.put("userId", (String.valueOf(user.getUserId())));
			// 序号
			map.put("orderNumber",
					((pageNum - 1) * iDisplayLength + orderNumber));
			// 姓名
			if (user.getUserName() != null) {
				map.put("userName", user.getUserName());
			} else {
				map.put("userName", "");
			}
			// 性别 0女 1男
			if (user.getSex() != null) {
				if (user.getSex() == 0) {
					map.put(SEX, "女");
				} else if (user.getSex() == 1) {
					map.put(SEX, "男");
				} else {
					map.put(SEX, "");
				}
			} else {
				map.put(SEX, "");
			}
			// 手机号码
			if (user.getPhone() != null) {
				map.put("phone", user.getPhone());
			} else {
				map.put("phone", "");
			}
			// 部门、单位
			GroupInfo gi = groupService.findOne(user.getGroupId());
			String groupName = "";
			if (gi != null) {
				groupName = gi.getGroupName();
			}
			if (StringUtils.isNotEmpty(groupName)) {
				map.put("GroupName", groupName);
			} else {
				map.put("GroupName", "");
			}
			// 部门/单位电话
			if (gi != null) {
				map.put("GroupPhone", gi.getPhone());
			} else {
				map.put("GroupPhone", "");
			}
			// 职务
			if (user.getJob() != null) {
				map.put("job", user.getJob());
			} else {
				map.put("job", "");
			}
			// 工号
			if (user.getLoginName() != null) {
				map.put("loginName", user.getLoginName());
			} else {
				map.put("loginName", "");
			}

			mapList.add(map);
		}
		return mapList;
	}

	public Integer getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getSearchkey() {
		return searchkey;
	}

	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}

}
