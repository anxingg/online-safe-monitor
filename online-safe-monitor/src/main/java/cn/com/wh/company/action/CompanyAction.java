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

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.RoleUser;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IRoleUser;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.encrypt.MD5;
import cn.com.wh.company.domain.SafetyInstitutions;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.ISafetyInstitutions;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.support.SessionSupport;
import cn.com.wh.util.DataInitUtil;
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
	private IRoleUser roleUserService;
	
	@Resource 
	private IDict dictService;
	
	/**
	 * 安全管理机构接口
	 */
	@Autowired
	private ISafetyInstitutions sisService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private WHCompany cpy ;
	
	private Integer groupId;
	
	private String loginName;//登录用户名
	
	private String companyName;//公司名称
	
	private String hylx;//企业类型
	
	private String loginPass;//登录密码
	
	private String userName;//联系人
	
	private String phone;//联系电话
	
	private String memo;//备注
	
	private String safeProductGrade;
	
	private String productTypeName;
	
	private String importCompanyQualification;
	
	private String establishmentTime;//公司，成立时间
	
	private String userIds;//删除的人员id
	
	
	/**
	 * 企业保存
	 */
	public void saveOrUpdateCpy(){
		
		try {
			LOGGER.info("企业保存saveOrUpdateCpy   begin");
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			groupId = userInfo.getGroupId();
			WHCompany whcompany = companyImpl.findByGroupId(groupId);
			//LOGGER.info("saveOrUpdateCpy 值： ");
			String companyCode = cpy.getCompanyCode();
			whcompany.setCompanyCode(companyCode);
			
			String product = cpy.getProduct();
			whcompany.setProduct(product);
			
			
			Integer productType =  cpy.getProductType();
			whcompany.setProductType(productType);
			
			
			Integer safeManageUserNum = cpy.getSafeManageUserNum();
			whcompany.setSafeManageUserNum(safeManageUserNum);
			String safeManageUserName = cpy.getSafeManageUserName();
			whcompany.setSafeManageUserName(safeManageUserName);
			
			String safeManageUserPhone = cpy.getSafeManageUserPhone();
			whcompany.setSafeManageUserPhone(safeManageUserPhone);
			String safeManageUserTel = cpy.getSafeManageUserTel();
			whcompany.setSafeManageUserTel(safeManageUserTel);
			
			String safeManageUserEmail = cpy.getSafeManageUserEmail();
			whcompany.setSafeManageUserEmail(safeManageUserEmail);
			
			
			
			Integer specialUserNum = cpy.getSpecialUserNum();
			whcompany.setSpecialUserNum(specialUserNum);
			Integer safeProductGrade = cpy.getSafeProductGrade();//安全生产标准等级 数据字段维护
			whcompany.setSafeProductGrade(safeProductGrade);
			String emergencyPhone = cpy.getEmergencyPhone();
			whcompany.setEmergencyPhone(emergencyPhone);
			String safeDutyPhone = cpy.getSafeDutyPhone();
			whcompany.setSafeDutyPhone(safeDutyPhone);
			
			
			Integer importCompanyQualification = cpy.getImportCompanyQualification();//进口企业资质证明名称 数据字典维护
			whcompany.setImportCompanyQualification(importCompanyQualification);
			String importCompanyQualificationNum = cpy.getImportCompanyQualificationNum();
			whcompany.setImportCompanyQualificationNum(importCompanyQualificationNum);
			
			String introduction = cpy.getIntroduction();
			whcompany.setIntroduction(introduction);
			
			
			Integer workersNum = cpy.getWorkersNum();
			whcompany.setWorkersNum(workersNum);
			String outsideSituation = cpy.getOutsideSituation();
			if(outsideSituation!=null && !outsideSituation.equals("")){
				outsideSituation = outsideSituation.substring(0,outsideSituation.length()-1);
			}
			whcompany.setOutsideSituation(outsideSituation);
			
			Integer enterpriseScale = cpy.getEnterpriseScale();
			whcompany.setEnterpriseScale(enterpriseScale);
			
			String industryClassification = cpy.getIndustryClassification();
			if(industryClassification!=null && !industryClassification.equals("")){
				industryClassification = industryClassification.substring(0,industryClassification.length()-1);
			}
			whcompany.setIndustryClassification(industryClassification);
			
			whcompany.setExtractDescription(cpy.getExtractDescription());
			
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
			List<WHCompany> list = companyImpl.unDeleted().findAll();
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
			Page<WHCompany> pageInfo = companyImpl.findWHCompanyByPage(this.getPageable(sort),groupId);
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
					
					//企业简介
					String introduction = wHCompany.getIntroduction();
					map.put("introduction", introduction == null ? "-" : introduction);
					
					//生产状况
					
					Integer productType = wHCompany.getProductType();
					Map<String, String> dictmapProductType = dictService.findMap("productType", 1);
					if(productType!=null){
						productTypeName = dictmapProductType.get(productType.toString());
						map.put("productTypeName", productTypeName == null ? "-" : productTypeName);
					}else{
						map.put("productTypeName", "-" );
					}
					
					//已提取金额
					map.put("allMoney", wHCompany.getSafetyAllMoney()==null?0:wHCompany.getSafetyAllMoney());
					//已使用金额
					map.put("usedMoney", wHCompany.getSafetyConsumerMoney()==null?0:wHCompany.getSafetyConsumerMoney());
					//余额
					map.put("remainMoney", wHCompany.getSafetySurplusMoney()==null?0:wHCompany.getSafetySurplusMoney());
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
	 * 验证登录用户名是否重复
	 * @return
	 */
	public String ajaxCheckLoginName(){
		try {
			UserInfo user = userService.findByLoginName(loginName);
			int index = 0;
			if(user!=null){
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
	 * 注册企业
	 * @return
	 */
	public String addCompany(){
		try {
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			//保存部门
			GroupInfo group = new GroupInfo();
			group.setCompanyId(1);
			group.setGroupName(companyName);
			group.setGroupType(1);
			group.setIsDelete(0);
			groupService.saveOrUpdate(group);
			group.setIsForkGroup(group.getGroupId());
			groupService.saveOrUpdate(group);
			
			//保存登录用户
			UserInfo user = new UserInfo();
			user.setCompanyId(1);
			user.setGroupId(group.getGroupId());
			user.setGroupName(companyName);
			user.setLoginName(loginName);
			MD5 md5 = new MD5();
			user.setLoginPass(md5.encrypt(loginPass));
			user.setPhone(phone);
			user.setUserName(userName);
			user.setIsDelete(0);
			user.setIsDefault(1);
			user.setOrderIndex(1);
			user.setUserState(0);
			user.setOfficeWidget(0);
			user.setPartitionCompanyId(1);
			user.setIsForkGroup(group.getGroupId());
			userService.saveOrUpdate(user);
			
			//保存用户权限表
			RoleUser roleUser = new RoleUser();
			roleUser.setCompanyId(1);
			roleUser.setType(1);
			roleUser.setRoleId(2);
			roleUser.setUserId(user.getUserId());
			roleUserService.saveOrUpdate(roleUser);
			
			//保存公司扩展表
			WHCompany wHCompany = new WHCompany();
			wHCompany.setGroupId(group.getGroupId());
			wHCompany.setIsForkGroup(group.getGroupId());
			wHCompany.setCompanyName(companyName);
			if(hylx!=null && !hylx.equals("")){				
				wHCompany.setIndustryClassification(hylx.substring(0, hylx.length()-1));
			}
			wHCompany.setIsDelete(0);
			wHCompany.setCreateUserId(userInfo.getUserId());
			wHCompany.setCreateTime(new Timestamp(System.currentTimeMillis()));
			wHCompany.setMemo(memo);
			companyImpl.saveOrUpdate(wHCompany);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"注册企业成功", 
					LogType.LOG_QYXX_ADD, 
					wHCompany, 
					wHCompany.getCompanyId()) );
			
			//刷新全局公司名称MAP
			DataInitUtil.flush();
			
			SafetyInstitutions sis = new SafetyInstitutions();
			sis.setGroupId(group.getGroupId());
			sis.setGroupName(group.getGroupName());
			sis.setCreateUserId(userInfo.getUserId());
			sis.setCreateTime(new Timestamp(System.currentTimeMillis()));
			sis.setIsDelete(0);
			sis.setIsForkGroup(group.getGroupId());
			sisService.saveOrUpdate(sis);
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(1);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String resetPass(){
		try {
			Integer whroletype = ((SessionSupport)this.getSessionSupport()).getCurrentWHRoleType();
			if(whroletype!=null && whroletype==1){//政府用户
				//保存登录用户
				List<UserInfo> list = userService.findUsersByGroupId(groupId.toString());
				if(list!=null && list.size()>0){
					UserInfo user = list.get(0);
					MD5 md5 = new MD5();
					user.setLoginPass(md5.encrypt(loginPass));
					userService.saveOrUpdate(user);
					LOGGER.info("重置密码成功，"+user.getLoginName()+",groupId="+groupId);
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"修改密码成功", 
							LogType.LOG_COMPANYPASS_RESET, 
							user, 
							user.getUserId()) );
					ajax(1);
				}else{
					ajax(0);
					LOGGER.info("重置密码失败，没有查到用户,groupId="+groupId);
				}
			}else{
				ajax(0);
				LOGGER.info("重置密码失败，企业端非法登录,userid="+getLoginUser().getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajax(0);
			LOGGER.info("重置密码失败，异常。e="+e.getMessage());
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
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		cpy = companyImpl.findByGroupId(userInfo.getGroupId());
		return "success";
	}
	
	/**
	 * 跳转到企业信息查看页面
	 * @return
	 */
	public String toCompanyView(){
		if(groupId==null){
			groupId = getLoginUser().getGroupId();
		}
		cpy = companyImpl.findByGroupId(groupId);
		
		Integer spg =cpy.getSafeProductGrade();//安全生产标准等级
		//map<value,name>
		Map<String, String> dictmap = dictService.findMap("safeProductType", 1);
		if(spg!=null){
			safeProductGrade = dictmap.get(spg.toString());
		}else{
			safeProductGrade = "";
		}
		
		Integer productType =cpy.getProductType();//生产状况
		//map<value,name>
		Map<String, String> dictmapProductType = dictService.findMap("productType", 1);
		if(productType!=null){
			productTypeName = dictmapProductType.get(productType.toString());
		}else{
			productTypeName = "";
		}
		
		Integer icq =cpy.getImportCompanyQualification();//进口企业资质证明名称
		//map<value,name>
		Map<String, String> icqmap = dictService.findMap("importCertificate", 1);
		if(icq!=null){
			importCompanyQualification = icqmap.get(icq.toString());
		}else{
			importCompanyQualification = "";
		}
		
		return "success";
	}
	/**
	 * 删除用户
	 * @author wuzhou 
	 * @return
	 */
	public String deleteLoginUser(){
		if (StringUtils.isNotBlank(userIds)) {
			if (userIds.startsWith(",")) {
				userIds = userIds.substring(1);
			}
			if (userIds.endsWith(",")) {
				userIds = userIds.substring(0, userIds.length()-1);
			}
			companyImpl.deleteCompanyByUserIds(userIds, getLoginUser().getCompanyId());
			
			for(String str: userIds.split(",")){
				try{
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"人员删除成功", 
							LogType.LOG_USER_DELETE, 
							null, 
							Integer.parseInt(str)) );
				}catch(NumberFormatException e){
					LOGGER.error("人员删除操作，传过来的人员ID不是数字哦！", e);
				}
			}
			ajax("1");
		}
		return null;
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getHylx() {
		return hylx;
	}

	public void setHylx(String hylx) {
		this.hylx = hylx;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSafeProductGrade() {
		return safeProductGrade;
	}

	public void setSafeProductGrade(String safeProductGrade) {
		this.safeProductGrade = safeProductGrade;
	}

	public String getImportCompanyQualification() {
		return importCompanyQualification;
	}

	public void setImportCompanyQualification(String importCompanyQualification) {
		this.importCompanyQualification = importCompanyQualification;
	}

	public String getEstablishmentTime() {
		return establishmentTime;
	}

	public void setEstablishmentTime(String establishmentTime) {
		this.establishmentTime = establishmentTime;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	
}
