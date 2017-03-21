package cn.com.qytx.hotline.customercall.action;

import java.util.List;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.domaim.CRMView;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.crm.service.ICRMView;

import com.opensymphony.xwork2.ActionContext;

public class WorkorderActionExpand {
	private static final String _PHONE="phone";
	private static final String _PHONEONE="phoneOne";
	private static final String _PHONETWO="phoneTwo";
	private static final String _ISCRMS="isCRMs";
	private static final String _RECEIVEMONEY="receiveMoney";
	public static void ctxPutCrm(ActionContext ctx,String phone,ICRM crmimpl,ICRMView crmViewimpl){
		CRM crm = null;
		CRMView  crmV=null;
		if (phone.substring(0, 3).equals("371")) {
			String phoneTwo = "0" + phone;
			ctx.put(_PHONE, phoneTwo);// 来电号码为座机号码
			crm = crmimpl.findCRMByMobile(phoneTwo);
			
			if (crm != null) {
				ctx.put(_PHONEONE, phoneTwo);// 来电号码 为移动手机号码
				ctx.put(_PHONETWO, crm.getBackPhone());
			} else {
				crm = crmimpl.findCRMByTelephone(phoneTwo);
				if (crm != null) {
					ctx.put(_PHONEONE, crm.getMobile());
					ctx.put(_PHONETWO, phoneTwo);// 来电号码为备用号码
				} else {
					ctx.put(_PHONEONE, phoneTwo);// 来电号码 为移动手机号码
				}
			}
			if(crm==null){
			    crmV=crmViewimpl.findByMobileAndName(phone, null);
			    if (crmV != null) {
	                ctx.put(_PHONEONE, phoneTwo);// 来电号码 为移动手机号码
	                ctx.put(_PHONETWO, crmV.getBackPhone());
	            } else {
	                crmV = crmViewimpl.findCRMByTelephone(phoneTwo,null);
	                if (crmV != null) {
	                    ctx.put(_PHONEONE, crmV.getMobile());
	                    ctx.put(_PHONETWO, phoneTwo);// 来电号码为备用号码
	                } else {
	                    ctx.put(_PHONEONE, phoneTwo);// 来电号码 为移动手机号码
	                }
	            }
			}
			List<CRM> crmList = crmimpl.findByPhone(phoneTwo);
			ctx.put(_ISCRMS, crmList.size()>1?1:0);

		} else {
			ctx.put(_PHONE, phone);// 来电号码 为移动手机号码
			crm = crmimpl.findCRMByMobile(phone);
			if (crm != null) {
				ctx.put(_PHONEONE, phone);// 来电号码 为移动手机号码
				ctx.put(_PHONETWO, crm.getBackPhone());
			} else {
				crm = crmimpl.findCRMByTelephone(phone);
				if (crm != null) {
					ctx.put(_PHONEONE, crm.getMobile());
					ctx.put(_PHONETWO, phone);// 来电号码为备用号码
				} else {
					ctx.put(_PHONEONE, phone);// 来电号码 为移动手机号码
				}
			}
			if(crm==null){
                crmV=crmViewimpl.findByMobileAndName(phone, null);
                if (crmV != null) {
                    ctx.put(_PHONEONE, phone);// 来电号码 为移动手机号码
                    ctx.put(_PHONETWO, crmV.getBackPhone());
                } else {
                    crmV = crmViewimpl.findCRMByTelephone(phone,null);
                    if (crmV != null) {
                        ctx.put(_PHONEONE, crmV.getMobile());
                        ctx.put(_PHONETWO, phone);// 来电号码为备用号码
                    } else {
                        ctx.put(_PHONEONE, phone);// 来电号码 为移动手机号码
                    }
                }
            }
			
			List<CRM> crmList = crmimpl.findByPhone(phone);
			if(crmList!=null){
				ctx.put(_ISCRMS, crmList.size()>1?1:0);
			}
		}
		if(crm!=null){
		    ctxPutCrmSon(ctx, crm);
		}else{
		    ctxPutCrmSon(ctx, crmV);
		}
		
	}
	/**
     * 功能：
     * @param ctx
     * @param crmV
     */
    private static void ctxPutCrmSon(ActionContext ctx, CRMView crm)
    {
        if (crm != null) {
            //获取用户的crm id
            ctx.put("linkedId", crm.getId());
            // 获取用户的姓名
            ctx.put("uname", crm.getName());
            // 获取用户的地址
            ctx.put("uaddress", crm.getAddress());
            // 获取用户的性别
            ctx.put("usex", crm.getGender());
            ctx.put("age", crm.getAge());
            // 获取用户类别
            ctx.put("personType", crm.getPersonType());
            // 获取用户身份证号
            ctx.put("cardId", crm.getCardId());
            // 获取用户户口所在地
            ctx.put("hkAddress", crm.getHkAddress());
            // 获取用户工作单位
            ctx.put("company", crm.getCompany());
            // 获取用户职务
            ctx.put("job", crm.getJob());
            // 获取用户月收入
            if (crm.getReceiveMoney() != null) {
                String receiveMoney = crm.getReceiveMoney().toString();
                ctx.put(_RECEIVEMONEY, receiveMoney);
            } else {
                ctx.put(_RECEIVEMONEY, crm.getReceiveMoney());
            }
            // 获取备注
            ctx.put("note", crm.getNote());
        }
    }
    /**
	 * 功能：
	 * @param ctx
	 * @param crm
	 */
	private static void ctxPutCrmSon(ActionContext ctx, CRM crm) {
		if (crm != null) {
			//获取用户的crm id
			ctx.put("crmId", crm.getVid());
			// 获取用户的姓名
			ctx.put("uname", crm.getName());
			// 获取用户的地址
			ctx.put("uaddress", crm.getAddress());
			// 获取用户的性别
			ctx.put("usex", crm.getGender());
			ctx.put("age", crm.getAge());
			// 获取用户类别
			ctx.put("personType", crm.getPersonType());
			// 获取用户身份证号
			ctx.put("cardId", crm.getCardId());
			// 获取用户户口所在地
			ctx.put("hkAddress", crm.getHkAddress());
			ctx.put("linkedId", crm.getLinkedId());
			// 获取用户工作单位
			ctx.put("company", crm.getCompany());
			// 获取用户职务
			ctx.put("job", crm.getJob());
			// 获取用户月收入
			if (crm.getReceiveMoney() != null) {
				String receiveMoney = crm.getReceiveMoney().toString();
				ctx.put(_RECEIVEMONEY, receiveMoney);
			} else {
				ctx.put(_RECEIVEMONEY, crm.getReceiveMoney());
			}
			// 获取备注
			ctx.put("note", crm.getNote());
		}
	}
}
