package cn.com.wh.zhdwxy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.zhdwxy.domain.DangerSourcesGood;
import cn.com.wh.zhdwxy.service.IDangerSourcesGood;

/**
 * 重大危险源危化品目录
 * @author lilipo
 *
 */
public class DangerSourcesGoodAddAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190163841482954491L;
	
	/**
	 * 重大危险源危化品目录接口
	 */
	@Autowired
	private IDangerSourcesGood dsgService;
	
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
    
    /**
     * 重大危险源危化品目录对象
     */
    private DangerSourcesGood dangerSourcesGood;
    

	/**
	 * 新增或修改
	 * @return
	 */
	public String saveOrUpdate() {
		int result = 0;//
		if(dangerSourcesGood != null){
			if(dangerSourcesGood.getVid() == -1){
				dangerSourcesGood.setVid(null);
				//新增
				dangerSourcesGood.setIsDelete(0);
				dangerSourcesGood.setCreateTime(new Date());
				//dangerSourcesGood.setCreateUser(getLoginUser().getUserId());
				//dangerChemicals.setIsForkGroup(getLoginUser().getIsForkGroup());
				try{
					dangerSourcesGood = dsgService.saveOrUpdate(dangerSourcesGood);
					if(dangerSourcesGood.getVid() != null){
						result = 1;
					}
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"新增重大危险源危化品目录对象成功", 
							LogType.LOG_DSG_ADD, 
							dangerSourcesGood, 
							dangerSourcesGood.getVid()) );
				}catch(Exception e){
					if("materialName used".equals(e.getMessage())){
						result = -1;
					}else if("molecularFormula used".equals(e.getMessage())){
						result = -2;
					}else{
						result = 0;
						LOGGER.error("新增重大危险源危化品目录对象时出错", e);
					}
				}
			}else {
				//修改
				DangerSourcesGood newdangerChemicals = dsgService.findOne(dangerSourcesGood.getVid());
				dangerSourcesGood.setIsDelete(newdangerChemicals.getIsDelete());
				dangerSourcesGood.setCreateTime(newdangerChemicals.getCreateTime());
				//dangerSourcesGood.setCreateUser(newdangerChemicals.getCreateUser());
				
				try{
					dsgService.saveOrUpdate(dangerSourcesGood);
					result = 1;
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"修改重大危险源危化品目录对象成功", 
							LogType.LOG_DSG_UPDATE, 
							dangerSourcesGood, 
							dangerSourcesGood.getVid()) );
				}catch(Exception e){
					if("materialName used".equals(e.getMessage())){
						result = -1;
					}else if("molecularFormula used".equals(e.getMessage())){
						result = -2;
					}else{
						result = 0;
						LOGGER.error("修改重大危险源危化品目录对象时出错", e);
					}
				}
			}
		}
		
		try {
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("新增或修改重大危险源危化品目录对象后，返回数据时出错", e);
		}
		return null;
	}


	public DangerSourcesGood getDangerSourcesGood() {
		return dangerSourcesGood;
	}

	public void setDangerSourcesGood(DangerSourcesGood dangerSourcesGood) {
		this.dangerSourcesGood = dangerSourcesGood;
	}

}
