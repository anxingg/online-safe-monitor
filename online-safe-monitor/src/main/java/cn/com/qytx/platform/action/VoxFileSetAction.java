package cn.com.qytx.platform.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.domain.VoxFileSet;

public class VoxFileSetAction extends BaseActionSupport{

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -6561540585091215900L;
	private VoxFileSet  vox;

	public VoxFileSet getVox() {
		return vox;
	}

	public void setVox(VoxFileSet vox) {
		this.vox = vox;
	}
	
	
	public String voxFileSetSave(){
		
		PrintWriter out = null;
		InputStream fis = null;
		FileOutputStream fos =null;
		try {
			out = this.getResponse().getWriter();
			//初始化输入流
//			String fileSt=DataBaseSetAction.class.getClassLoader().getResource("voxFilePath.properties").getPath();
			String fileSt=Thread.currentThread().getContextClassLoader().getResource("voxFilePath.properties").getPath();
			File fileTem= new File(fileSt);
			  fis = new FileInputStream(fileTem);   
		   Properties properties = new Properties();
		   //加载
			properties.load(fis);
		//设置properties属性
			properties.setProperty("fileIp", vox.getVoxHostIp());
			properties.setProperty("filePort", vox.getVoxPort());
			properties.setProperty("filepath", vox.getVoxPath());
			//关闭输入流 
			 fis.close();  
			// 文件输出流 
						File file=new File(fileSt);
						 fos = new FileOutputStream(file); 
						// 将Properties集合保存到流中 
						properties.store(fos, "Copyright (c) "); 
						fos.close();// 关闭流 
			
						out.print(1);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}finally{
			if(out!=null){
			    out.close();
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}	
			}
			if(fos!=null){
				
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
		
		return null;
	}
	
	
	public String voxFileSetView(){
		
		InputStream fis = null;
		try {
			//文件输入流
		String fileSt=Thread.currentThread().getContextClassLoader().getResource("voxFilePath.properties").getPath();
//			String fileSt=DataBaseSetAction.class.getClassLoader().getResource("voxFilePath.properties").getPath();
			File file= new File(fileSt);
			  fis = new FileInputStream(file);   
			Properties properties = new Properties();
			//加载文件
				properties.load(fis);
			//取出属性
			String voxHostIp=	properties.getProperty("fileIp");
			String voxPort=	properties.getProperty("filePort");
			String voxPath=	properties.getProperty("filepath");
			VoxFileSet voxTem=new VoxFileSet();
			voxTem.setVoxHostIp(voxHostIp);
			voxTem.setVoxPort(voxPort);
			voxTem.setVoxPath(voxPath);
			
			this.getRequest().setAttribute("vox", voxTem);
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}finally{
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage());
					}
				}
			}
		
		
		return SUCCESS;
	}
	
	
}
