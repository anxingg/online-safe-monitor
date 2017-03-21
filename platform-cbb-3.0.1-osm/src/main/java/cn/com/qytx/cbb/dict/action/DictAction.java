package cn.com.qytx.cbb.dict.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.domain.Log;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;

import com.google.gson.Gson;

/**
 * 功能:数据维护
 * 版本: 1.0
 * 开发人员: zhangjingjing
 * 创建日期: 2014-6-26
 * 修改日期: 2014-6-26
 * 修改列表: 
 */
public class DictAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource(name = "dictService")
	private  IDict dictService;
	
    /** 系统日志接口 */
    @Resource
    private ILog logService;

	private Integer sysTag; // 类型类别
	private String infoType;//类型
	private String name;//名字
	private Integer id;//对象ID
	private Integer sysTagItem;//项目类别
	private String ids;
	private int typeValue;
    private int infoOrder; //排序
    private int grade;//层级
    private int parentId;//父节点id
	/**
	 * @Title: 根据不同项目和类型获取通用信息
	 * @param
	 * @return
	 * @return 返回类型
	 */
	public String getDicts() {
		List<Dict> list = dictService.findList(infoType, Math.abs(sysTag));
		ajax(list,true);
		return null;
	}
	
	/**
	 * @Title: 根据数据权限获得分类省级和市级分类
	 * @param
	 * @return
	 * @return 返回类型
	 */
	public String getDictsByIsForkGroup() {
		List<Dict> list = dictService.findList(infoType, Math.abs(sysTag));
		ajax(list,true);
		return null;
	}
	
	/**
	 * @Title: 根据父节点的id和层级获取数据字典信息
	 * @param
	 * @return
	 * @return 返回类型
	 */
	public String getDictsByParentId() {
		List<Dict> list = dictService.findListByParentId(infoType, grade, id);
		ajax(list,true);
		return null;
	}
	/**
	 * 
	 * 功能：手机端接口
	 * @return
	 */
	public String getMoblieDicts() {
		final List<Dict> list = dictService.findList(infoType, Math.abs(sysTag));
		try {
			Gson gson = new Gson();
			String data = gson.toJson(list);
			ajax("100||" + data);
		} catch (Exception e) {
			ajax("102||操作异常");
			LOGGER.error(e.getMessage());
		} 
		return null;
	}
	/**
	 * @Title: 根据不同项目和类型获取通用信息
	 * @Description:
	 * @param
	 * @return
	 * @return 返回类型
	 * @throws
	 */
	public String getSysDicts() {
		List<Dict> list = dictService.findSysList(sysTagItem==null?0:sysTagItem);
		ajax(list,true);
		return null;
	}
	/**
	 * 获得类别属性结构(数据字典页面的右侧树形结构使用)
	 */
	public String getAllDictsTree() {
        List<Dict> list = dictService.findSysListByCompanyId();  //获取数据字典列表
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        
        //创建部门树
        buildTreeNodeList(list,treeNodes);
        Gson json = new Gson();
        String jsons = json.toJson(treeNodes);
        ajax(jsons);
        return null;
    }
	/**
     * 创建部门树
     * 在zTree树中使用时，只需要给TreeNode节点中pid赋值就可以了，不需要设置Children
     */
    private void buildTreeNodeList(final  List<Dict> list,Collection<TreeNode> data)
    {
    	Collection<TreeNode> treeNodes=data;
        if(treeNodes==null)
        {
            treeNodes=new ArrayList<TreeNode>();
        }
        for (Dict dict : list) {
        	String name = "";
        	if (StringUtils.isNotBlank(dict.getName())&&dict.getName().length()>8) {
        		name = StringUtils.substring(dict.getName(), 0, 7) + "...";
			}else {
				name = StringUtils.substring(dict.getName(), 0, 8);
			}
            TreeNode treeNode = new TreeNode();
            treeNode.setId("gid_"+dict.getId().toString());//部门ID前加gid表示类型为部门
            treeNode.setPId("gid_"+(dict.getParentId()==null?"0":dict.getParentId().toString()));
            treeNode.setObj(dict);
            treeNode.setName(name);
            treeNode.setTitle(dict.getName());
            treeNodes.add(treeNode);
         }
    }
    
    /**
	 * 通过数据字典infotype类型获得下面的所有数据字典封装树（公告模块的分类使用）
	 */
	public String getDictsTreeByInfoType() {
        List<Dict> list = dictService.findSysListByInfoType(infoType);  //通过数据字典infotype类型获得下面的所有数据字典
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        
        //创建部门树
        buildTreeNodeListOn(list,treeNodes);
        Gson json = new Gson();
        String jsons = json.toJson(treeNodes);
        ajax(jsons);
        return null;
    }
	/**
     * 创建部门树
     * 在zTree树中使用时，只需要给TreeNode节点中pid赋值就可以了，不需要设置Children
     */
    private void buildTreeNodeListOn(final  List<Dict> list,Collection<TreeNode> data)
    {
    	Collection<TreeNode> treeNodes=data;
        if(treeNodes==null)
        {
            treeNodes=new ArrayList<TreeNode>();
        }
        for (Dict dict : list) {
            String name = StringUtils.substring(dict.getName(), 0, 8);
            TreeNode treeNode = new TreeNode();
            treeNode.setId("gid_"+dict.getId().toString());//部门ID前加gid表示类型为部门
            treeNode.setPId("gid_"+(dict.getParentId()==null?"0":dict.getParentId().toString()));
            treeNode.setObj(dict);
            treeNode.setName(name);
            if (dict.getSysTag()==-1) {
            	treeNode.setOpen(true);
			}
            treeNodes.add(treeNode);
         }
    }
	/**
	 * 通过数据字典infotype类型获得下面的所有数据字典（手机端接口）
	 */
	public String getDictsByInfoType() {
        List<Dict> list = dictService.findSysListByInfoType(infoType);  //通过数据字典infotype类型获得下面的所有数据字典
        Gson json = new Gson();
        List<Map<String,Object>> mapList = null;
        if(list != null && list.size() > 0){
        	mapList = new ArrayList<Map<String,Object>>();
        	for(Dict dict:list){
        		Map<String,Object> map = new HashMap<String,Object>();
        		map.put("dictId", dict.getId());
        		map.put("name", dict.getName());
        		map.put("value", dict.getValue());
        		map.put("infoType", dict.getInfoType());
        		map.put("createDate", dict.getCreateDate());
        		map.put("sysTag", dict.getSysTag());
        		map.put("parentId", dict.getParentId());
        		map.put("grade", dict.getGrade());
        		map.put("isForkGroup", dict.getIsForkGroup());
        		mapList.add(map);
        	}
        }
        
        String jsons = json.toJson(mapList);
        ajax("100||"+jsons);
        return null;
    }

	/**
	 * add类别
	 * 
	 * @return String
	 */
	public String addType() {
		UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
		if (dictService.getInfoTypeByNameAndParentId(infoType, sysTag, name,parentId) == null) {
			List<Dict> list = dictService.findAllListByParentId(infoType, sysTag,parentId);
			if (list != null && !list.isEmpty())
            {
				typeValue = list.get(list.size()-1).getValue() + 1;
            }else{
            	typeValue = 1;
            }
			Dict parentDict = dictService.findOne(parentId);
			Dict d = new Dict();
			d.setInfoType(infoType);
			d.setName(name);
			d.setCreateDate(new Timestamp(System.currentTimeMillis()));
			d.setModifyDate(new Timestamp(new Date().getTime()));
			d.setSysTag(sysTag);
			d.setValue(typeValue);
			d.setRecordUserId(getLoginUser().getUserId());
			d.setIsDelete(0);
			d.setCompanyId(getLoginUser().getCompanyId());
			d.setInfoOrder(infoOrder);
			d.setParentId(parentId);
			d.setIsForkGroup(parentDict.getIsForkGroup());
			d.setGrade(grade+1);
			dictService.saveOrUpdate(d);
			//处理路径
//				String path = "";
//				if (parentDict.getPath().endsWith(",")) {
//					path = parentDict.getPath()+d.getId();
//				}else {
//					path = parentDict.getPath()+","+d.getId();
//				}
//				d.setPath(path);
//				d.setValue(d.getId());
//				dictService.saveOrUpdate(d);
			Gson gson = new Gson();
	        Log log = new Log();
	        log.setCompanyId(userInfo.getCompanyId());
	        log.setInsertTime(new Timestamp(new Date().getTime()));
	        log.setModuleName("user");
	        log.setSysName("wuhai");
	        log.setIp(this.getRequest().getRemoteAddr());
	        log.setIsDelete(0);
	        log.setLogType(LogType.LOG_DICT_ADD);
	        log.setRefId(d.getId());
	        log.setRemark("数据字典新增");
	        log.setUserId(userInfo.getUserId());
	        log.setUserName(userInfo.getUserName());
	        log.setLogContent(gson.toJson(d));
	        log.setType(0);// 手动添加系统日志
	        logService.saveOrUpdate(log);
	        
			ajax("0");
		} else {
			ajax("1");
		}
		return null;
	}

	/**
	 * update类别
	 * 
	 * @return
	 */
	public String updateType() {
		UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
		Dict type = dictService.findOne(id);
		// 判断名称是否已经存在
		Dict t = dictService.getInfoTypeByNameAndNotIdParentId(type.getInfoType(), type.getSysTag(), name, id,parentId);
		if (t == null) {
			type.setName(name);
			//type.setValue(typeValue);
			type.setModifyDate(new Timestamp(new Date().getTime()));
			type.setRecordUserId(getLoginUser().getUserId());
			type.setInfoOrder(infoOrder);
			dictService.saveOrUpdate(type);
			if (sysTag==-1) {
				dictService.upateDictByInfoType(type.getInfoType(), infoType);
			}
			
			Gson gson = new Gson();
	        Log log = new Log();
	        log.setCompanyId(userInfo.getCompanyId());
	        log.setInsertTime(new Timestamp(new Date().getTime()));
	        log.setModuleName("user");
	        log.setSysName("wuhai");
	        log.setIp(this.getRequest().getRemoteAddr());
	        log.setIsDelete(0);
	        log.setLogType(LogType.LOG_DICT_UPDATE);
	        log.setRefId(type.getId());
	        log.setRemark("数据字典修改");
	        log.setUserId(userInfo.getUserId());
	        log.setUserName(userInfo.getUserName());
	        log.setLogContent(gson.toJson(type));
	        log.setType(0);// 手动添加系统日志
	        logService.saveOrUpdate(log);
			ajax("0");
		} else {
			ajax("1");
		}
		return null;
	}

	/**
	 * 删除类别
	 * 
	 * @return
	 */
	public String deleteType() {
		UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
		if (sysTag==-1) {
			dictService.deleteByInfoType(infoType);
		}
		else {
			List<Dict> list = dictService.findListByParentId(ids);
			if (list!=null&&list.size()>0) {
				ajax("1");
				return null;
			}else {
				String[] typeIds = ids.split(",");
				for (int i = 0; i < typeIds.length; i++) {
					dictService.delete(Integer.parseInt(typeIds[i]),false);
					
			        Log log = new Log();
			        log.setCompanyId(userInfo.getCompanyId());
			        log.setInsertTime(new Timestamp(new Date().getTime()));
			        log.setModuleName("user");
			        log.setSysName("wuhai");
			        log.setIp(this.getRequest().getRemoteAddr());
			        log.setIsDelete(0);
			        log.setLogType(LogType.LOG_DICT_DELETE);
			        log.setRefId(Integer.parseInt(typeIds[i]));
			        log.setRemark("数据字典删除");
			        log.setUserId(userInfo.getUserId());
			        log.setUserName(userInfo.getUserName());
			        //log.setLogContent(gson.toJson(d));
			        log.setType(0);// 手动添加系统日志
			        logService.saveOrUpdate(log);
				}
			}
		}
		  ajax("0");
		return null;
	}

	public Integer getSysTag() {
		return sysTag;
	}

	public void setSysTag(Integer sysTag) {
		this.sysTag = sysTag;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysTagItem() {
		return sysTagItem;
	}

	public void setSysTagItem(Integer sysTagItem) {
		this.sysTagItem = sysTagItem;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}
	/**
	 * @return the infoOrder
	 */
	public int getInfoOrder() {
		return infoOrder;
	}
	/**
	 * @param infoOrder the infoOrder to set
	 */
	public void setInfoOrder(int infoOrder) {
		this.infoOrder = infoOrder;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
