package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.util.Tool;

import com.google.gson.Gson;

/**
 * 功能：企业相关action
 * 作者：李贺
 * 时间：2015年8月17日
 * 修改人：吴胜光
 * 修改时间：2015-08-19
 */
public class CompanyAction extends BaseActionSupport{

	private static final long serialVersionUID = -3209484199082218431L;
	
	@Autowired
	public IWHCompany companyImpl;
	
	@Resource
    private IUser userService;
	
	@Resource
	private IGroup groupService;

	
	@Resource 
	private IDict dictService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	private WHCompany cpy ;
	private Integer groupId;
	//上级部门ID
	private Integer parentId;
	private String companyName;//公司名称
	private String establishmentTime;//公司，成立时间
	
	
	/**
	 * 删除企业
	 */
	public void deleteCompany()
	{
		try {
			LOGGER.info("企业删除deleteCompany   begin");	
			WHCompany whcompany = companyImpl.findByGroupId(groupId);
			companyImpl.delete(whcompany.getCompanyId(), false);	
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除企业信息成功", 
					LogType.LOG_QYXX_DELETE, 
					whcompany, 
					whcompany.getCompanyId()) );
			
			ajax("1");
			LOGGER.info("企业删除deleteCompany   end");
		} catch (Exception e) {
			LOGGER.info("企业删除deleteCompany异常，e:"+e.getMessage());
			e.printStackTrace();
			ajax("0");
		}
	}
	/**
	 * 企业保存
	 */
	public void saveOrUpdateCpy(){
		
		try {
			LOGGER.info("企业保存saveOrUpdateCpy   begin");
			WHCompany whcompany=null;
			if(groupId==null){
				UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
				//父部门path
				String path="";
				GroupInfo parentGroup=groupService.findOne(parentId);
				path=parentGroup.getPath();
				//保存部门
				GroupInfo group = new GroupInfo();
				group.setCompanyId(1);
				group.setGroupName(companyName);
				group.setGroupType(2);
				group.setIsDelete(0);
				group.setParentId(parentId);
				group.setGrade(parentGroup.getGrade()+1);
				group.setGroupState(0);
				group.setUserId(userInfo.getUserId());
				groupService.saveOrUpdate(group);
				group.setIsForkGroup(group.getGroupId());
				group.setPath(path+","+group.getGroupId());
				group.setOrderIndex(group.getGroupId());
				groupService.saveOrUpdate(group);
				
				//保存公司扩展表
				whcompany =new WHCompany();
				whcompany.setCompanyName(companyName);
				whcompany.setGroupId(group.getGroupId());
				whcompany.setIsForkGroup(group.getGroupId());
				whcompany.setIsDelete(0);
				whcompany.setCreateUserId(userInfo.getUserId());
				whcompany.setLinkId(UUID.randomUUID().toString());
				whcompany.setCreateTime(new Timestamp(System.currentTimeMillis()));
			}
			else{
				whcompany = companyImpl.findByGroupId(groupId);
			}
			//LOGGER.info("saveOrUpdateCpy 值： ");
			whcompany.setRegistrationAddress(cpy.getRegistrationAddress());
			whcompany.setCompanyCode(cpy.getCompanyCode());
			whcompany.setCityId(cpy.getCityId());	
			whcompany.setProductType(cpy.getProductType());
			whcompany.setCompanyProperty(cpy.getCompanyProperty());
			whcompany.setBusinessLicence(cpy.getBusinessLicence());
			whcompany.setProductionScope(cpy.getProductionScope());
			whcompany.setLegalRepresentative(cpy.getLegalRepresentative());
			String economicType = cpy.getEconomicType();//经济类型  需要修改为String类型 
			if(!economicType.equals("-1")){			
				whcompany.setEconomicType(economicType);
			}
			whcompany.setUnitCode(cpy.getUnitCode());
			whcompany.setProductAddress(cpy.getProductAddress());
			whcompany.setWebsite(cpy.getWebsite());
			whcompany.setPostalcode(cpy.getPostalcode());
			whcompany.setPrecision(cpy.getPrecision());
			whcompany.setDimension(cpy.getDimension());
			whcompany.setSales(cpy.getSales());
			whcompany.setEnterpriseScale(cpy.getEnterpriseScale());
			whcompany.setIsIn(cpy.getIsIn());
			whcompany.setWorkersNum(cpy.getWorkersNum());
			whcompany.setEstablishmentTime(Timestamp.valueOf(establishmentTime));			
			companyImpl.saveOrUpdate(whcompany);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"修改企业信息成功", 
					LogType.LOG_QYXX_UPDATE, 
					whcompany, 
					whcompany.getCompanyId()) );
			
			ajax("1");
			LOGGER.info("企业修改saveOrUpdateCpy   end");
		} catch (Exception e) {
			LOGGER.info("企业修改saveOrUpdateCpy异常，e:"+e.getMessage());
			e.printStackTrace();
			ajax("0");
		}
	}

	/**
	 * 获取企业名字集合
	 * @return
	 */
	public String getCompanyNameList(){
		
		try {
			List<WHCompany> list = companyImpl.findWHCompany(parentId);
			Gson json = new Gson();
			String jsons = json.toJson(list);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("getCompanyNameList发生异常： "+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 企业列表
	 * @return
	 */
	public String getWHCompanyList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.ASC, "NLSSORT(companyName, 'NLS_SORT=SCHINESE_PINYIN_M')"));
			Page<WHCompany> pageInfo = companyImpl.findWHCompanyByPage(
					this.getPageable(sort),groupId,parentId,null);
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
	 * 验证企业名称是否重复
	 * @return
	 */
	public String ajaxCheckCompanyName(){
		try {
			WHCompany com = companyImpl.findByCompanyName(companyName);
			int index = 0;
			if(com!=null){
				index=1;
			}
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(index);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取企业信息
	 * @return
	 */
	public String getWHCompanyInfo(){
		
		try {
			WHCompany wHCompany = companyImpl.findByGroupId(groupId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", wHCompany.getCompanyId());
			map.put("companyName", wHCompany.getCompanyName());
			map.put("enterpriseScale", wHCompany.getEnterpriseScale());
			map.put("cityId", wHCompany.getCityId());
			map.put("legalRepresentative", wHCompany.getLegalRepresentative());
			map.put("introduction", wHCompany.getIntroduction());
			List<UserInfo> list = userService.findUsersByGroupId(groupId.toString());
			if(list==null || list.size()==0){
				map.put("loginName", "");
			}else{				
				map.put("loginName", list.get(0).getLoginName());
			}
			
			Gson json = new Gson();
			String jsons = json.toJson(map);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 跳转到企业信息修改页面
	 * @return
	 */
	public String toUpdateCompany(){
		if(groupId!=null)
			cpy = companyImpl.findByGroupId(groupId);
		else  //新增单位
		{
			cpy = new WHCompany();
		}
		return "success";
	}
	
	/**
	 * 跳转到企业信息查看页面
	 * @return
	 */
	public String toCompanyView(){
		cpy = companyImpl.findByGroupId(groupId);
		return "success";
	}
	
	public WHCompany getCpy() {
		return cpy;
	}

	public void setCpy(WHCompany cpy) {
		this.cpy = cpy;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEstablishmentTime() {
		return establishmentTime;
	}

	public void setEstablishmentTime(String establishmentTime) {
		this.establishmentTime = establishmentTime;
	}

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
