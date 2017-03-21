package cn.com.qytx.hotline.balck.service;

import java.io.Serializable;

import cn.com.qytx.hotline.balck.domain.Blacklist;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 功能: 黑名单service
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-8-19
 * 修改日期: 2013-8-19
 * 修改列表:
 */
public interface IBlacklist extends BaseService<Blacklist>,Serializable 
{
    /**
     * 功能：分页查询
     * @param Pageable 分页信息
     * @return Page 分页结果数据
     */
    Page<Blacklist> findAll(Pageable pageable, String phone);

    /**
     * 批量删除黑名单
     * @param blacklistId blacklistId
     */
    void deleteBatch(Integer[] blacklistId);

    /**
     * 功能：根据电话查询黑名单信息
     * @param phone 电话
     * @return Blacklist
     */
    Blacklist findByPhone(String phone);
}