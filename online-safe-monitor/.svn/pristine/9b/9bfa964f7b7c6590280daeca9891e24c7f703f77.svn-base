package cn.com.qytx.hotline.ivr.service;

import java.util.List;

import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.domain.SeatMonitor;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;



/**
 * User: 黄普友
 * Date: 12-11-1
 * Time: 上午12:04
 */
public interface IMsiUser extends BaseService<Msiuser>{
    /**
     * 登录
     * @param workNo
     * @param pass
     * @return
     */
      Msiuser login(String workNo,String pass);
      /**
       * 根据登陆人信息获取坐席号码
       * 功能：
       * @param workNo
       * @return
       */
      Msiuser getMsiuerByWorkNo(String workNo);
      /**
       * @Title:得到列表
       * @Description:
       * @param @param workNo
       * @param @return
       * @return 返回类型
       * @throws
       */
      List<Object> misUserList();
     
      
      /**
       * @Title:查询话务员分页列表
       * @Description:
       * @param @param pageInfo
       * @param @param params
       * @param @return
       * @return 返回类型
       * @throws
       */
   // public Page<Msiuser> findMsiuserByPage(Page<Msiuser> pageInfo, String params, UserInfo userInfo, Integer queueId);
      
      /**
       * 
       * 功能：获取坐席列表
       * @return List<Msiuser> 
       */
      List<Msiuser> findAllmisUserList();
      
      /**
       * 
       * 功能：根据Id获取坐席信息
       * @param vid ID
       * @return Msiuser
       */
      Msiuser findById(Integer vid);
      
      /**
       * 
       * 功能：获取坐席列表
       * @return List<Msiuser> 
       */
      List<Msiuser> findMymisUserList(UserInfo userInfo);
      
      /**
       * 功能：获取坐席监控列表
       * @param u 当前用户
       * @param scope 范围0表示全部 1表示自己所在班
       * @return SeatMonitor
       */
      SeatMonitor getSeatMonitor(UserInfo userInfo, Integer scope);
      /**
       * @Title:查询话务员分页列表
       * @Description:
       * @param @param pageInfo
       * @param @param params
       * @param @return
       * @return 返回类型
       * @throws
       */
      Page<Msiuser> findMsiuserByPage(Pageable pageInfo, String params, UserInfo userInfo, Integer queueId, Integer groupId,String workIds,Integer isForkGroup);
      
      /**
       * 三方通话、咨询转接页面的空闲坐席显示查询坐席是内线坐席还是外线坐席时用。
       * @param ids 用逗号分割开的坐席id
       * @return
       */
      List<Msiuser> findMsiUserListByIds(String ids);
      
      /**
       * 功能：查询当前在线坐席的分组
       * @param msiType 内线/外线
       * @param isForkGroup 
       * @return
       */
      List<Object> findMsiUserGruops(Integer msiType,Integer isForkGroup);
      
      /**
  	 * 获取坐席分页
  	 * @param pageInfo
  	 * @param isForkGroup
  	 * @param searchKey 
  	 * @return
  	 */
  	Page<Msiuser> monitorMisuserByPage(Pageable pageInfo, Integer isForkGroup, String searchKey);
}
