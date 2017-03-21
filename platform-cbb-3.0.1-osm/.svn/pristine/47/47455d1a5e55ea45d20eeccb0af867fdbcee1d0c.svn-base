package cn.com.qytx.cbb.org.action.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.qytx.cbb.org.service.MobileGroupService;

/**
 * 部门管理手机接口
 * @author jiayongqiang
 *
 */
@Controller
@RequestMapping("/org/mobile/group")
public class GroupManagerAction extends BaseOrgController{
	protected final static Logger logger = LoggerFactory
			.getLogger(UserManagerAction.class);
	
	@Autowired
	private MobileGroupService mobileGroupService;
	
	/**
	 * 添加部门
	 *  org/mobile/group/add.action
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/add.c")
	public String add(@RequestParam(value="companyId",required=true)Integer companyId,
					@RequestParam(value="userId",required=true)Integer userId,
					@RequestParam(value="userName",required=true)String userName,
					@RequestParam(value="groupName",required=true)String groupName,
					@RequestParam(value="parentId",required=false)Integer parentId,
					@RequestParam(value="groupType",required=true)Integer groupType) throws Exception{
		logger.info("接收到请求,method=addGroup,companyId="+companyId+",userId="+userId+",userName="+userName+",parentId="+parentId+
                ",parentId="+parentId+",groupName="+groupName+",groupType="+groupType);
		if(parentId==null){
			parentId = 0;
		}
		int newId = mobileGroupService.addGroup(companyId,userId,userName,parentId,groupName,groupType);
		sendPush(companyId);
		return SUCCESS(newId);
	}
	
	/**
	 * 修改部门
	 * /org/mobile/group/update.action
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update.c")
	public String update(@RequestParam(value="companyId",required=true)Integer companyId,
						@RequestParam(value="userId",required=true)Integer userId,
						@RequestParam(value="userName",required=true)String userName,
						@RequestParam(value="groupName",required=true)String groupName,
						@RequestParam(value="groupId",required=true)Integer groupId,
						@RequestParam(value="parentId",required=true)Integer parentId
						) throws Exception{
		
            logger.info("接收到请求,method=updateGroup,companyId="+companyId+",userId="+userId+",userName="+userName+",groupId="+groupId+
                        ",parentId="+parentId+",groupName="+groupName);

            mobileGroupService.updateGroup(companyId,userId,userName,groupId,parentId,groupName);
            sendPush(companyId);
            return SUCCESS("修改成功");
	}
	
	/**
	 * 删除部门
	 * /org/mobile/group/delete.action
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete.c")
	public String delete(@RequestParam(value="companyId",required=true)Integer companyId,
			@RequestParam(value="userId",required=true)Integer userId,
			@RequestParam(value="userName",required=true)String userName,
			@RequestParam(value="groupId",required=true)Integer groupId) throws Exception{
		
            logger.info("接收到请求,method=deleteGroup,companyId="+companyId+",userId="+userId+",userName="+userName+",groupId="+groupId);

            int pid = mobileGroupService.deleteGroup(companyId,userId,userName,groupId);
            sendPush(companyId);
            return SUCCESS(pid);
	}
	
	/**
	 * 部门排序
	 * /org/mobile/group/order.action
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/order.c")
	public String order(@RequestParam(value="companyId",required=true)Integer companyId,
			@RequestParam(value="userId",required=true)Integer userId,
			@RequestParam(value="userName",required=true)String userName,
			@RequestParam(value="sortList",required=true)String sortList) throws Exception{
		
            logger.info("接收到请求,method=groupOrder,companyId="+companyId+",userId="+userId+",userName="+userName+",sortList="+sortList);
            mobileGroupService.groupOrder(companyId,userId,userName,sortList);
            return SUCCESS("部门排序成功");
	}
}
