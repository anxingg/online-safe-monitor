package cn.com.qytx.hotline.ivr.service;

import java.util.List;

import cn.com.qytx.hotline.ivr.domain.Msiservice;
import cn.com.qytx.platform.base.service.BaseService;


/**
 * 功能: 服务队列维护
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2014-1-9
 * 修改日期: 2014-1-9
 * 修改列表:
 */
public interface IMsiservice extends BaseService<Msiservice>
{
    /**
     * 功能：查询所有队列信息
     * isFilter == true 过滤
     * isFilter == false 不过滤
     * @return
     */
    List<Msiservice> findAllDao(boolean isFilter);
    
    /**
     * 功能：根据队列Id获取队列信息
     * @param vid
     * @return
     */
    Msiservice findById(Integer vid);
    
    /**
	 * 功能：根据短信id获得队列信息
	 * @param msiid 坐席id
	 * @return List<Msiservice>
	 */
	List<Object[]> findAllByMsiUserIds(String msiid);
	
	/**
	 * 功能：根据坐席id字符串以及登录坐席所属区域获得队列信息及坐席信息
	 * @param msiid:坐席id字符串
	 * @param isForkGroup:登录坐席所属区域
	 * @return
	 */
	List<Object[]> findAllByIdsAndIsFork(String msiids,Integer isForkGroup);
	
	
	/**
	 * 根据isForkGroup查询有哪些专家队列
	 * @param isForkGroup
	 * @return
	 */
	List<Object[]> findMsiServiceIdAndNameByForkGroupId( Integer isForkGroup );

}
