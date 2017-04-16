package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.company.domain.GroupCompany;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.impl.GroupCompanyImpl;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;
import cn.com.wh.thresholdtemplate.service.IThresholdTemplate;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.Tool;

public class GroupCompanyAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public GroupCompanyImpl groupCompanyImpl; 
		
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;

	@Autowired
	public IWHCompany companyImpl;
	
	@Resource 
	private IDict dictService;
	
	//需要绑定的企业的groupID列表
	private String companyGroupIds;
	private Integer vid;
	
	/**
	 * 绑定的群组ID
	 */
	private Integer groupId;
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	
	
	
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public String getCompanyGroupIds() {
		return companyGroupIds;
	}
	public void setCompanyGroupIds(String companyGroupIds) {
		this.companyGroupIds = companyGroupIds;
	}
	/**
	 * 获得绑定选择的树
	 */
	public String getBindSelectTree()
	{
		String contextPath = getRequest().getContextPath();
        List<TreeNode> treeNodes=groupCompanyImpl.getBindSelectNode(getLoginUser()
        		, contextPath, groupId, "0,2");
 
        Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsons = json.toJson(treeNodes);
        ajax(jsons);
        return null;
		
	}
	/**
	 * 企业列表
	 * @return
	 */
	public String getGroupCompanyList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.ASC, "NLSSORT(companyName, 'NLS_SORT=SCHINESE_PINYIN_M')"));
			String groupIds=groupCompanyImpl.getGroupIds(groupId);
			if(StringUtils.isEmpty(groupIds)){  //如果没有绑定单位，设置一个固定值，以不获得任何单位
				groupIds="0";
			}
			Page<WHCompany> pageInfo = companyImpl.findWHCompanyByPage(
					this.getPageable(sort),null,null,groupIds);
			List<WHCompany> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (WHCompany wHCompany : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					//企业编号
					map.put("companyId", wHCompany.getCompanyId());
					
					//groupId
					map.put("groupId", wHCompany.getGroupId());
					
					//企业名称
					String companyName = wHCompany.getCompanyName();
					map.put("companyName", companyName == null ? "-" : companyName);
					
					//企业法人
					String legalRepresentative = wHCompany.getLegalRepresentative();
					map.put("legalRepresentative", legalRepresentative == null ? "-" : legalRepresentative);
					
					//经济类型
					String economicType = wHCompany.getEconomicType();
					map.put("economicType", economicType == null ? "-" : economicType);
					
					String establishmentTime ="-";
					if(wHCompany.getEstablishmentTime()!=null)
					{
						//成立时间
						establishmentTime = DateTimeUtil.timestampToString(wHCompany.getEstablishmentTime(), "yyyy-mm-dd HH:MM:dd");
					}
					map.put("establishmentTime", establishmentTime == null ? "-" : establishmentTime);
					
					//企业简介
					String introduction = wHCompany.getIntroduction();
					map.put("introduction", introduction == null ? "-" : introduction);
					
					//生成产场所地址
					String productAddress = wHCompany.getProductAddress();
					map.put("productAddress", productAddress == null ? "-" : productAddress);
					
					//生产状况
					Integer productType = wHCompany.getProductType();
					Map<String, String> dictmapProductType = dictService.findMap("productType", 1);
					String productTypeName="-";
					if(productType!=null){
						productTypeName = dictmapProductType.get(productType.toString());
						map.put("productTypeName", productTypeName == null ? "-" : productTypeName);
					}else{
						map.put("productTypeName", "-" );
					}
					mapList.add(map);
					i++;
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String bind(){
		String log="新增组织绑定";
		try{
			
			LOGGER.info(log+"开始。。。");
			groupCompanyImpl.bulckInsert(groupId, companyGroupIds);
			LOGGER.info(log+"结束");
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					log+"成功", 
					LogType.LOG_ZZBD_BIND, 
					null, 
					0) );
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(log+"异常，e:"+e.getMessage());
			ajax(0);
		}
		return null;
	}
	
	/**
	 * 接触绑定
	 * @return
	 */
	public String unBind(){
		try {
			groupCompanyImpl.delete(vid, true);
			LOGGER.info("解除组织绑定，vid："+vid);
			
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"解除组织绑定成功", 
					LogType.LOG_ZZBD_UNBIND, 
					null, 
					vid) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("解除组织绑定异常，vid="+vid+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	
}
