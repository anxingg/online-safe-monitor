package cn.com.qytx.hotline.user.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.tree.TreeNode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HotlineSelectUserAction extends BaseActionSupport {

	private static final long serialVersionUID = -1748390019571078817L;

	private int type;// 选择类型1 部门 2 角色 3 分组 4 在线
	/** 用户信息 */
	@Resource(name = "userService")
	private transient IUser userService;
	/**
	 * 部门/群组管理接口
	 */
	@Resource(name = "groupService")
	private IGroup groupService;


	private String searchName;
	private int showType;// 选择类型 1只显示部门 2 显示角色 3 显示人员

	private Integer userId;

	/**
	 * 模块名称
	 */
	private String moduleName;

	/**
	 * 根据类型选择人员
	 * 
	 * @return
	 */

	public String getUserInfo() {
		try {
			String userIdStr = getRequest().getParameter("userId");
			if (userIdStr != null && !"".equals(userIdStr)) {
				Integer userId = Integer.parseInt(userIdStr);
				UserInfo userInfo = userService.findOne(userId);
				if (userInfo != null) {
					Integer groupId = userInfo.getGroupId();
					GroupInfo g = groupService.findOne(groupId);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", userInfo.getUserId());
					map.put("userName", userInfo.getUserName());
					if (g != null) {
						map.put("groupName", g.getGroupName());
					}
					Gson json = new Gson();
					String jsons = json.toJson(map);
					ajax(jsons);
				}
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public String selectUserByType() {
		String contextPath = getRequest().getContextPath() + "/";
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		moduleName = (moduleName == null ? "" : moduleName);
		if (type == 1) {
			// 根据部门选择
			GroupInfo forkGroup = groupService.getForkGroup(getLoginUser()
					.getCompanyId(), getLoginUser().getUserId());
			// 如果是发文分发，则不区分二级局
			if (moduleName != null && moduleName.equals("dispatch")) {
				forkGroup = null;
			}
			int key = 0;
			if (forkGroup != null) {
				key = forkGroup.getGroupId();
			}
			UserInfo loginUser = getLoginUser();
			treeNodes = userService.selectUserByGroup(loginUser, forkGroup,
					moduleName, showType, key, contextPath, type);
		} else if (type == 2) {
			// 根据角色选择
			treeNodes = userService.selectUserByRole(getLoginUser(),
					moduleName, showType, contextPath);
		} else if (type == 5) {
			// 查找人员
			searchUser(treeNodes);
		}
		Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String jsons = json.toJson(treeNodes);
		ajax(jsons);
		return null;
	}

	/**
	 * 查找人员
	 * 
	 * @param treeNodes
	 *            SimpleTreeNode列表
	 */
	private void searchUser(Collection<TreeNode> treeNodes) {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName()
//				+ ":" + request.getServerPort() + path + "/";

		try {
			UserInfo adminUser = (UserInfo) this.getSession().getAttribute(
					"adminUser");
			if (adminUser != null) {
				if (searchName == null || searchName.equals("")) {
					return;
				}
				// 遍历人员
				List<UserInfo> userList = userService.findAllUsers(
						adminUser.getCompanyId(), searchName);
				GroupInfo forkGroup = groupService.getForkGroup(
						adminUser.getCompanyId(), adminUser.getUserId());
				CompanyInfo companyInfo = (CompanyInfo) this.getSession()
						.getAttribute("companyInfo");
				List<UserInfo> forkUsers = getUsersByForkGroup(forkGroup,
						companyInfo);
				if (userList != null) {
					for (UserInfo user : userList) {
						if ("roleManager".equals(this.moduleName)
								&& user.getUserState().intValue() == UserInfo.USERSTATE_UNLOGIN) {
							continue;
						}

						boolean flag = isExist(user, forkUsers);
						if (!flag) {
							continue;
						}

						TreeNode treeNode = new TreeNode();
						treeNode.setId("uid_" + user.getUserId());// 部门ID前加gid表示类型为部门
						treeNode.setName(user.getUserName());
						treeNode.setObj(user.getPhone()); // 把号码存放到node里面，js里面调用
						// 职务
						treeNode.setTarget(user.getJob());
						treeNodes.add(treeNode);
					}
				}
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private boolean isExist(UserInfo ui, List<UserInfo> userlist) {
		for (int i = 0; i < userlist.size(); i++) {
			if (userlist.get(i).getUserId().intValue() == ui.getUserId()
					.intValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * add by jiayq 功能：获取指定二级局下的所有人员
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	private List<UserInfo> getUsersByForkGroup(GroupInfo forkGroup,
			CompanyInfo companyInfo) {
		List<GroupInfo> grouplist = null;
		if (forkGroup == null) {
			grouplist = groupService
					.getGroupList(companyInfo.getCompanyId(), 1);
		} else {
			grouplist = groupService.getSubGroupList(forkGroup.getGroupId());
			grouplist.add(forkGroup);
		}
		String ids = "";
		StringBuffer idsb = new StringBuffer();
		for (Iterator<GroupInfo> it = grouplist.iterator(); it.hasNext();) {
			GroupInfo temp = it.next();
			idsb.append(temp.getGroupId());
			if (it.hasNext()) {
				idsb.append(",");
			}
		}
		ids = idsb.toString();
		List<UserInfo> userList = userService.findUsersByGroupId(ids);
		return userList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	protected UserInfo getLoginUser() {
		UserInfo ui = super.getLoginUser();
		if (ui == null) {
			ui = userService.findOne(userId);
		}
		return ui;
	}
}
