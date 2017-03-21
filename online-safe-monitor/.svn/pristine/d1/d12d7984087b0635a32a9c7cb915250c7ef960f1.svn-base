package cn.com.qytx.hotline.customercall.domain;
import java.io.Serializable;
import java.util.Comparator;

import cn.com.qytx.hotline.ivr.domain.MsicallLog;
/**
 * 功能:
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2015-1-12
 * 修改日期: 2015-1-12
 * 修改列表: 
 */
@SuppressWarnings("rawtypes")
public class ComparatorLog  implements Comparator,Serializable{
	private static final long serialVersionUID = 1L;
	public int compare(Object arg0, Object arg1) {
		 MsicallLog user0=(MsicallLog)arg0;
		 MsicallLog user1=(MsicallLog)arg1;
	  int flag=user1.getVid().compareTo(user0.getVid());
	    return flag;
	  }
}
