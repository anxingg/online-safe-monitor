package cn.com.wh.company.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompanyPhoto;
import cn.com.wh.company.service.IWHCompanyPhoto;
import cn.com.wh.safeaccident.util.Tool;

/**
 * 功能：企业证照
 * 作者：吴胜光
 * 时间：2015年8月24日
 * 修改人：
 * 修改时间：
 */
public class CompanyPhotoAction extends BaseActionSupport{

	private static final long serialVersionUID = -3209484199082218431L;
	
	@Autowired
	public IWHCompanyPhoto companyPhotoImpl; 
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private Integer attachmentId;//照片ID
	
	private String attachmentPath;//相对路径
	
	private String photoName;//照片名称
	
	private Integer photoId;//原照片ID
	
	private Integer groupId;//部门ID
	
	private List<WHCompanyPhoto> list;//
	
	private Integer photoType;
	
	private String downPath;
	
	/**
	 * 企业证照保存上传
	 */
	public void saveOrUpdatePhoto(){
		
		try {
			LOGGER.info("企业证照保存上传begin...");
			
			UserInfo userInfo = getLoginUser();//获取登录用户
			WHCompanyPhoto photo = new WHCompanyPhoto();
			photo.setGroupId(userInfo.getGroupId());
			photo.setIsDelete(0);
			photo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			photo.setCreateUserId(userInfo.getUserId());
			photo.setIsForkGroup(userInfo.getGroupId());
			photo.setPhotoType(photoType);
			photo.setAttachmentId(attachmentId);
			photo.setAttachmentPath(attachmentPath);
			photo.setPhotoName(photoName);
			
			//先删除   后保存
			companyPhotoImpl.updatePhone(photoType,photo,userInfo.getGroupId());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"修改企业证照成功", 
					LogType.LOG_QYZZ_UPDATE, 
					photo, 
					photo.getVid()) );
			ajax("1");
			LOGGER.info("企业证照保存上传end...");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
			LOGGER.error("企业证照保存上传异常，"+e.getMessage());
		}
	}
	
	public String toPhotoView(){
		
		list = companyPhotoImpl.findByGroupId(groupId);
		
		downPath = (String) this.getSession().getAttribute("downPath");
		downPath = downPath.replace("\\:", ":");
				
		return "success";
	}

	
	/**
	 * 获取企业证照路径
	 * @return
	 */
	public String getPhonePath(){
		
		try {
			if(groupId==null){
				UserInfo userInfo = getLoginUser();//获取登录用户
				groupId = userInfo.getGroupId();
			}
			WHCompanyPhoto photo  = companyPhotoImpl.findByType(photoType, groupId);
	
			Map<String, Object> map = new HashMap<String, Object>();
			if(photo!=null){
				map.put("isNull", 0);//是否为空 1：是   0：否
				map.put("attachmentPath", photo.getAttachmentPath());
				map.put("photoName", photo.getPhotoName());
			}else{
				map.put("isNull", 1);//是否为空 1：是   0：否
			}
			
			Gson json = new Gson();
			String jsons = json.toJson(map);
			
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
	 * 清楚企业证照
	 * @return
	 */
	public String deleteImg(){
		
		
		try {
			UserInfo userInfo = getLoginUser();//获取登录用户
			companyPhotoImpl.deleteImg(photoType, userInfo.getGroupId());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除企业证照成功", 
					LogType.LOG_QYZZ_DELETE, 
					null, 
					null) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			ajax("0");
		}
			
			
		return null;
		
	}
	

	public Integer getAttachmentId() {
		return attachmentId;
	}


	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}


	public String getAttachmentPath() {
		return attachmentPath;
	}


	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	public Integer getPhotoId() {
		return photoId;
	}


	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public List<WHCompanyPhoto> getList() {
		return list;
	}

	public void setList(List<WHCompanyPhoto> list) {
		this.list = list;
	}

	public Integer getPhotoType() {
		return photoType;
	}

	public void setPhotoType(Integer photoType) {
		this.photoType = photoType;
	}

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}

	
	
}
