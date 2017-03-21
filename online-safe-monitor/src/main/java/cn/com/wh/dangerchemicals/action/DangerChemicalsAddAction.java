package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;
import cn.com.wh.safeaccident.util.Tool;

/**
 * 危险化学品
 * @author lilipo
 *
 */
public class DangerChemicalsAddAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3794767593668152543L;

	/**
	 * 危化品对象
	 */
	private DangerChemicalsInfo dangerChemicals;
	
	/**
	 * 危化品接品
	 */
	@Autowired
	private DangerChemicalsService dcService;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;

	/**
	 * 新增或修改
	 * @return
	 */
	public String saveOrUpdate() {
		int result = 0;//
		if(dangerChemicals != null){
			if(dangerChemicals.getVid() == -1){
				dangerChemicals.setVid(null);
				//新增
				dangerChemicals.setIsDelete(0);
				dangerChemicals.setCreateDate(new Date());
				dangerChemicals.setCreateUser(getLoginUser().getUserId());
				//dangerChemicals.setIsForkGroup(getLoginUser().getIsForkGroup());
				try{
					dangerChemicals = dcService.saveOrUpdate(dangerChemicals);
					if(dangerChemicals.getVid() != null){
						result = 1;
					}
					try{
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"新增危险化学品成功", 
							LogType.LOG_DANGERH_ADD, 
							dangerChemicals, 
							dangerChemicals.getVid()) );
					}catch(Exception e){
						LOGGER.error("新增危险化学品后，记录日志时出错。", e);
					}
				}catch(Exception e){
					if("materialName used".equals(e.getMessage())){
						result = -1;
					}else if("molecularFormula used".equals(e.getMessage())){
						result = -2;
					}else{
						result = 0;
						LOGGER.error("新增危险化学品时出错", e);
					}
				}
			}else {
				//修改
				DangerChemicalsInfo newdangerChemicals = dcService.findOne(dangerChemicals.getVid());
				dangerChemicals.setIsDelete(newdangerChemicals.getIsDelete());
				dangerChemicals.setCreateDate(newdangerChemicals.getCreateDate());
				dangerChemicals.setCreateUser(newdangerChemicals.getCreateUser());
				
				try{
					dcService.saveOrUpdate(dangerChemicals);
					result = 1;
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"修改危险化学品成功", 
							LogType.LOG_DANGERH_UPDATE, 
							dangerChemicals, 
							dangerChemicals.getVid()) );
				}catch(Exception e){
					if("materialName used".equals(e.getMessage())){
						result = -1;
					}else if("molecularFormula used".equals(e.getMessage())){
						result = -2;
					}else{
						result = 0;
						LOGGER.error("修改危险化学品时出错", e);
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
			LOGGER.error("新增或修改危险化学品后，返回数据时出错", e);
		}
		return null;
	}

	
	public DangerChemicalsInfo getDangerChemicals() {
		return dangerChemicals;
	}

	public void setDangerChemicals(DangerChemicalsInfo dangerChemicals) {
		this.dangerChemicals = dangerChemicals;
	}
	
}
