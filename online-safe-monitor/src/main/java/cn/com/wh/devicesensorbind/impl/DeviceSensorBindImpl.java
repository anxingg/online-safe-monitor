package cn.com.wh.devicesensorbind.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.CompanyDao;
import cn.com.qytx.platform.org.dao.GroupDao;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.collectdevice.dao.CollectDeviceDao;
import cn.com.wh.collectdevice.domain.CollectDevice;
import cn.com.wh.devicesensorbind.dao.DeviceSensorBindDao;
import cn.com.wh.devicesensorbind.domain.DeviceSensorBind;
import cn.com.wh.devicesensorbind.service.IDeviceSensorBind;

/**
 * 功能: 传感器
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class DeviceSensorBindImpl extends BaseServiceImpl<DeviceSensorBind> implements IDeviceSensorBind,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(DeviceSensorBindImpl.class);
	
	@Autowired
	private DeviceSensorBindDao deviceSensorBindDao;


	@Autowired
	private GroupDao groupDao;
	@Resource
    private CompanyDao<CompanyInfo> companyDao;
	
	@Resource
	private CollectDeviceDao collectDeviceDao;
	@Override
	public Page<DeviceSensorBind> findDeviceSensorBindList(Pageable pageable,Integer collectDeviceId)
	{
		return deviceSensorBindDao.findDeviceSensorBindList(pageable, collectDeviceId);
	}
	
	@Override
	public List<DeviceSensorBind> findDeviceSensorBindList(Integer collectDeviceId)
	{
		return deviceSensorBindDao.findDeviceSensorBindList(collectDeviceId);
	}
	public String findBindedCollectSensorIds(Integer collectDeviceId)
	{
		List<DeviceSensorBind> list=deviceSensorBindDao.findDeviceSensorBindList(collectDeviceId);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			if(i!=0){
				sb.append(",");
			}
			sb.append(list.get(i).getCollectSensor().getVid());
		}
		return sb.toString();
	}
	@Override
	public void bind(Integer id,Integer DEVICEID,Integer SENSORID)
	{
		deviceSensorBindDao.update(id, DEVICEID, SENSORID, 2);
	}
	@Override
	public List<TreeNode> createSearchTree(UserInfo userInfo) {
		// TODO Auto-generated method stub
		CompanyInfo companyInfo = companyDao.findOne(userInfo.getCompanyId());
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
    	List<GroupInfo> groupList=groupDao.getGroupList(companyInfo.getCompanyId(), "0,2", (Integer)1, null);
    	if (groupList != null)
        {
            // 遍历部门
            for (GroupInfo group : groupList)
            {
                TreeNode treeNode = new TreeNode();
                treeNode.setId("gid_" + group.getGroupId().toString());// 部门ID前加gid表示类型为部门
                treeNode.setName(group.getGroupName());
                treeNode.setTitle(group.getGroupName());
                treeNode.setObj(group.getOrderIndex());
                treeNode.setPId("gid_" + group.getParentId().toString());
                treeNode.setOpen(true);
                treeNodes.add(treeNode);
            }
        }
    	List<CollectDevice> collectDeviceList=collectDeviceDao.findAll();
    	if (collectDeviceList != null)
        {
            // 遍历部门
            for (CollectDevice collectDevice : collectDeviceList)
            {
                TreeNode treeNode = new TreeNode();
                treeNode.setId("cid_" + collectDevice.getVid());
                treeNode.setName(collectDevice.getInstallPosition());
                treeNode.setTitle(collectDevice.getInstallPosition());
                treeNode.setPId("gid_" + collectDevice.getCompany().getGroupId());
                treeNode.setOpen(false);
                treeNodes.add(treeNode);
            }
        }
    	return treeNodes;
	}
}
