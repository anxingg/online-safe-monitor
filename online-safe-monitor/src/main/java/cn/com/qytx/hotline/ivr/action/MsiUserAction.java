package cn.com.qytx.hotline.ivr.action;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.platform.base.action.BaseActionSupport;

/**
 * 
 * 功能: 重置坐席状态action
 * 开发人员: 李立泼
 * 创建日期: 2014-09-15
 * 修改日期: 
 * 修改列表:
 */
public class MsiUserAction extends BaseActionSupport {

	private static final long serialVersionUID = 5233355362100297513L;

	@Autowired
    private transient IMsiUser msiuserService; //话务员登记

    /**
     * 坐席ID
     */
    private int vid;
    
    /**
     * 重置坐席状态
     */
    public String resetMsiUser(){
    	PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			Msiuser mUser=	msiuserService.findById(vid);
	    	mUser.setState(0);
	    	msiuserService.saveOrUpdate(mUser);
	    	out.print(1);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
    
    }
    

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}
    
}
