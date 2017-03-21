package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.dangerchemicals.domain.ChemicalsDirectory;
import cn.com.wh.dangerchemicals.service.ChemicalsDirectoryService;
import cn.com.wh.safeaccident.util.Tool;

/**
 * 危险化学品目录
 * @author lilipo
 *
 */
public class ChemicalsDirectoryAddAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5747846493717968965L;

	/**
	 * 危化品对象
	 */
	private ChemicalsDirectory chemicalsDirectory;
	
	/**
	 * 危化品目录接品
	 */
	@Autowired
	private ChemicalsDirectoryService cdService;
	
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
		if(chemicalsDirectory != null){
			if(chemicalsDirectory.getVid() == -1){
				chemicalsDirectory.setVid(null);
				//新增
				chemicalsDirectory.setIsDelete(0);
				chemicalsDirectory.setCreateDate(new Date());
				chemicalsDirectory.setCreateUser(getLoginUser().getUserId());
				//dangerChemicals.setIsForkGroup(getLoginUser().getIsForkGroup());
				try{
					chemicalsDirectory = cdService.saveOrUpdate(chemicalsDirectory);
					if(chemicalsDirectory.getVid() != null){
						result = 1;
					}
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"新增危险化学品目录成功", 
							LogType.LOG_CHEMICALSDIR_ADD, 
							chemicalsDirectory, 
							chemicalsDirectory.getVid()) );
				}catch(Exception e){
					if("materialName used".equals(e.getMessage())){
						result = -1;
					}else if("molecularFormula used".equals(e.getMessage())){
						result = -2;
					}else{
						result = 0;
						LOGGER.error("新增危险化学品目录时出错", e);
					}
				}
			}else {
				//修改
				ChemicalsDirectory newdangerChemicals = cdService.findOne(chemicalsDirectory.getVid());
				chemicalsDirectory.setIsDelete(newdangerChemicals.getIsDelete());
				chemicalsDirectory.setCreateDate(newdangerChemicals.getCreateDate());
				chemicalsDirectory.setCreateUser(newdangerChemicals.getCreateUser());
				
				try{
					cdService.saveOrUpdate(chemicalsDirectory);
					result = 1;
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"修改危险化学品目录成功", 
							LogType.LOG_CHEMICALSDIR_UPDATE, 
							chemicalsDirectory, 
							chemicalsDirectory.getVid()) );
				}catch(Exception e){
					if("materialName used".equals(e.getMessage())){
						result = -1;
					}else if("molecularFormula used".equals(e.getMessage())){
						result = -2;
					}else{
						result = 0;
						LOGGER.error("修改危险化学品目录时出错", e);
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
			LOGGER.error("新增或修改危险化学品目录后，返回数据时出错", e);
		}
		return null;
	}

	public ChemicalsDirectory getChemicalsDirectory() {
		return chemicalsDirectory;
	}

	public void setChemicalsDirectory(ChemicalsDirectory chemicalsDirectory) {
		this.chemicalsDirectory = chemicalsDirectory;
	}

}
