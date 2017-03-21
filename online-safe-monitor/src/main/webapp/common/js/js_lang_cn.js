var qy_lang = {};
qy_lang.crm = {
		name_not_null:"姓名不允许为空",
		mobile_not_null:"联系电话不允许为空",
		mobile_isMobile:"联系电话格式不正确！",
		receiveMoney_not_right:"格式不正确!",
		backPhone_not_right:"备用号码格式不正确!",
		telephone_isPhoneSimple:"固定电话号码格式不正确！",
		carId_not_right:"身份证格式不正确!"
};
qy_lang.outCallTask = {
		taskName_not_null:"任务名称不允许为空",
		taskStartTime_not_null:"开始时间不允许为空",
		taskEndTime_not_null:"结束时间不允许为空",
		seatUserIds_not_null:"坐席不允许为空",
		importDatas_not_null:"导入不允许为空",
		title_not_null : "任务名称不允许为空",
		taskType_not_null : "请选择任务类型",
		seatUserIds_not_null : "请选择执行坐席",
		taskUser_not_null : "请选择外呼对象"
};
qy_lang.area = {
		areaName_not_null:"名称不允许为空",
		orderNum_not_null:"排序号不允许为空"
};
qy_lang.partDepartment = {
		model_not_null:"型号名称不允许为空",
		systemName_not_null:"系统名称不允许为空",
		allPartName_not_null:"总成名称不允许为空",
		partName_not_null:"零部件名称不允许为空",
		partPhotoNum_not_null:"零部件图号不允许为空",
		orderCode_not_null:"排序号不允许为空"
};
qy_lang.queue = {
		serviceName_not_null:"队列说明不允许为空",
		userName_not_null:"队列成员不允许为空"
};

qy_lang.global = {
    page_up : "上一页",
    page_down : "下一页",
    songti : "宋体",
    delete_1 : "删除",
    select : "选择",
    total : "合计",
    yes : "是",
    no : "否",
    reply : "回复",
    error : "错误：",
    close : "关闭",
    regist : "注册",
    first_page : "首页",
    before_page : "上页",
    next_page : "下页",
    last_page : "尾页{0}111{1}",
    refresh_1 : "刷新",
    right : "正确",
    print : "打印",
    print_preview : "打印预览",
    clear : "清空",
    details : "详情"
};

/**
 * 人事档案模块
 * 
 * @type {{}}
 */
qy_lang.record = {

    no_select_item_to_delete : "要删除人事档案,请至少选择其中的一项。",
    confirm_to_delete_select : "确认要删除吗？删除后不可恢复。",

    login_name_not_null : "请选择用户",
    login_name_already_exist : "该用户已建档!",
    user_name_not_null : "姓名不能为空，请输入姓名。",
    mobile_no_not_null : "手机号码不能为空，请输入手机号码",
    not_allow_empty : "不允许为空",
    must_be_number : "请输入数字",
    must_over_zero : "必须大于0",
    must_be_right_phone_no : "输入的联系电话格式不正确，请重新输入！",
    must_be_right_mobile_no : "手机号码格式不正确，必须为11位数字",
    must_be_right_email : "输入的电子邮件格式不正确，请重新输入！",
    must_be_right_qq : "QQ号码只能是数字",
    must_be_chinese : "请输入中文",
    must_be_english : "请输入英文",
    must_be_english_name : "请输入正确的英文名",
    must_be_integer : "请输入数字",
    must_be_idCard : "请输入正确的身份证号"
};
/**
 * 积分管理和积分项目类型模块验证
 */
qy_lang.point = {
	point_not_null:"不允许为空",
        point_repeat:"活动类别已经存在"
}
/**
 * 任务模块的验证
 */
qy_lang.countdown = {
    countdown_content_not_null : "名称不能为空，请输入名称。",
    countdown_endTime_not_null : "截止日期不能为空，请选择截止日期！"
};

/**
 * 日志模块的验证
 */
qy_lang.sysset = {
    sysset_oldPass_not_null : "请输入原密码！",
    sysset_newPass_not_null : "请输入新密码！",
    sysset_newPass1_not_null : "请输入确认新密码！",
    sysset_oldPass_is_wrong : "原密码错误，请重新输入。",
    sysset_newPass1_is_wrong : "确认新密码与新密码不一致，请重新输入。",
    sysset_birthDay_is_null : "生日不能为空，请选择生日。",
    sysset_newPass_limit : "新密码必须为6-20位的有效组合。",
    sysset_mobile_not_null : "输入的手机号码格式不正确，请重新输入！",
    sysset_be_right_email : "输入的MSN格式不正确，请重新输入！",
    sysset_userName_not_null : "请输入姓名！"
};

/**
 * 群组验证
 */
qy_lang.group = {
    group_phone_not_null : "请输入电话！",
    group_phone_format_error : "输入的电话格式不正确，请重新输入！",
    group_order_not_null : "请输入排序号！",
    group_order_format_error : "排序号必须为数字！",
    group_name_not_null : "请输入部门名称！",
    parent_group_not_self_group : "上级部门不能为本部门",
    group_name_input_error : "部门名称不能输入“\\”！",
	parent_group_not_null:"上级部门不能为空",
    	
    organizer_phone_format_error : "输入的电话格式不正确，请重新输入！",
    organizer_order_not_null : "请输入排序号！",
    organizer_order_format_error : "排序号必须为数字！",
    organizer_name_not_null : "请输入单位名称！",
    organizer_name_input_error : "单位名称不能输入“\\”！"
};


/**
 * 人员
 */
qy_lang.user = {
    user_loginName_not_null : "请输入用户名！",
    user_loginName_format_error : "用户名只能输入数字,字母或者下划线！",
    user_job_not_null : "请输入职务！",
    user_userName_not_null : "请输入姓名！",
    user_role_not_null : "请输入主角色！",
    user_group_not_null : "请输入部门！",
    user_phone_not_null : "请输入手机号码！",
    user_phone_format_error : "输入的手机号码格式不正确，请重新输入！",
    user_email_format_error : "输入的用户邮箱格式不正确，请重新输入！",
    user_phone2_format_error : "输入的工作电话格式不正确，请重新输入！",
    password_not_null : "请输入密码！",
    password_limit : "密码不能少于6位！",
    confirm_password_not_null : "请输入确认密码！",
    confirm_password_limit : "确认密码不能少于6位！",
    password_must_be_consistent : "密码和确认密码必须一致！",
    user_not_null : "请选择用户！",
    user_is_exist : "该人员已配置登录名,请重新选择！",
    loginName_is_exist : "登录名已存在!",
    seat_must_number : "坐席登录名必须为4-5为数字！",
    role_not_null : "请选择角色！"
};


/**
 * 人员
 */
qy_lang.role = {
    role_roleName_not_null : "请输入角色名称！",
    role_roleCode_not_null : "请输入角色代码！",
    role_roleCode_format_error : "角色代码必须为字母！"
};

qy_lang.seatlogin = {
    workno_not_null : "请输入工号！",
    password_not_null : "请输入密码！",
    password_limit : "密码长度不能少于6位！",
    password_wrong : "密码错误！",
    seat_state_forbit : "本用户禁止登录！",
    seat_state_abnormal : "坐席状态异常！",
    seat_logined : "坐席已登录！",
    seat_disabled : "坐席已停用！",
    compang_wrong : "获取所属公司失败！",
    work_pass_wrong : "工号或密码不正确！",
    login_forbid : "没有权限登录！"
};

qy_lang.blacklist = {
    phone_not_null : "电话号码不能为空！",
    add_error : "新增失败,请联系管理员！",
    blacklist_del_at_least_one : "要删除黑名单,请至少选择其中的一项。",
    msg_confirm_info : "确认要删除选中项吗？",
    delete_one_confirm_info : "确认要删除该黑名单吗？",
    blacklist_del_failure : "删除黑名单失败！"
};
qy_lang.member = {
    area_not_null : "请输入住址！",
    areaCity_not_null : "请输入省市区！",
    mname_not_null : "请输入姓名！",
    mobile_not_null : "请输入手机号码！",
    mobile_not_right : "手机号码格式不正确！",
    birthday_not_null : "请输入出生日期！",
    community_not_null:"请输入所属社区！",
    economics_not_null:"请输入经济情况！",
    income_not_null:"请输入月收入！"
   
};
qy_lang.commutity = {
    name_not_null : "请输入社区名称！",
    name_not_rep : "社区名称重复！",
    phone_not_null : "请输入办公电话！",
    mobile_not_right : "手机号码格式不正确！",
    person_not_null : "请输入社区负责人！",
    address_not_null : "请输入办公地点！",
    money_not_null : "请输入充值限额！"
 
};
qy_lang.customerCall = {
	company_not_null : "请输入联系方式！",
	resource_not_null : "请输入工单来源！",
	name_not_null : "请输入诉求人！",
    type_not_null : "请输入诉求类型！",
    scope_not_null : "请输入涉及范围！",
    groupId_not_null : "请输入承办单位！",
    content_not_null : "请输入工单内容！",
    backInfo_not_null : "请输入退回意见！",
    consultType_not_null:"咨询类型不能为空",
    complaintsType_not_null:"请选择投诉类别",
    complaintsLevel_not_null:"请选择投诉级别",
    complaintsContent_not_null:"投诉内容不能为空",
    consultContent_not_null:"咨询内容不能为空",
    consultAnswer_not_null:"答复内容不能为空",
    uname_not_null:"用户姓名不能为空",
    uphone_not_null:"手机号码不能为空",
    uaddress_not_null:"用户地址不能为空",
    resourceCode_not_null:"产品资源代码不能为空",
    dealers_not_null:"经销单位不能为空",
    factoryDate_not_null:"出厂日期不能为空",
    purchaseDate_not_null:"购机日期不能为空",
    productModel_not_null:"产品型号不能为空",
    productModelId_not_null:"产品型号不存在",
    hostID_not_null:"主机编号不能为空",
    engineFactory_not_null:"发动机厂家不能为空",
    enginePattern_not_null:"发动机型号不能为空",
    engineNo_not_null:"发动机号不能为空",
    fiveGradeCompleteAddressName_not_null:"故障地址不能为空",
    contact_not_null:"现场联系人不能为空",
    contactPhone_not_null:"联系人电话不能为空",
    faultInfo_not_null:"故障现象不能为空",
    breakdownAddress_not_null:"故障地址不能为空",
    repairCompany_not_null:"维修单位不能为空",
    repairUserName_not_null:"维修工姓名不能为空",
    repairUserPhone_not_null:"维修工电话不能为空",
    repairTime_not_null:"请录入维修时间",
    repairMark_not_null:"派修备注不能为空",
    answerResult_not_null:"答复结果不能为空",
    repairResultInfo_not_null:"维修结果不能为空",
    isRealy_not_null:"是否已维修不能为空",
    intoPlace_not_null:"到位时间不能为空",
    serviceDegree_not_null:"配件满意度不能为空",
    partDegree_not_null:"服务满意度不能为空",
    notTongReason_not_null:"未接通原因不能为空",
    smsCotent_not_null:"短信内容不能为空",
    dealovercontent_not_null:"办结结果不能为空",
    answercontent_not_null:"答复内容不能为空",
    callbackcontent_not_null:"回访结果内容不能为空",
   seatReplay_not_null: "请输入工单答复！",
	businessType_not_null : "请选择业务类别！",
	accessType_not_null : "请选择受理方式！",
	type_not_null : "请选择工单类别！",      
    
    
    
    
  
 
};
qy_lang.website = {
		resource_not_null : "请输入工单来源！",
		name_not_null : "请输入姓名！",
		company_not_null : "请输入联系方式！",
		address_not_null : "请输入联系地址！",
		scope_not_null : "请选择涉及范围！",
		type_not_null : "请选择类型！",
		name_not_limit : "姓名不能少于两位！",
		
		 mobile_not_right : "手机号码格式不正确！",
		content_not_null : "请输入反映内容！",
		backInfo_not_null : "请输入退回意见！",
			code_not_null : "请输入验证码！"
			
			
};
qy_lang.memberKinsfolk= {
    kName_not_null : "请输入姓名！",
    relation_not_null : "请输入关系！",
    mobile_not_null : "请输入手机号码！",
    mobile_not_rep : "手机号码重复！"
   
 
};
qy_lang.memberTerminal= {
    teType_not_null : "请输入终端类型！",
    teModel_not_null : "请输入终端型号！",
    teNumber_not_null : "请输入终端号码！",
    teCode_not_null : "请输入终端编号！",
    tePassword_not_null : "请输入消费密码！"
   
 
};
qy_lang.memberLevel = {
    levelName_not_null : "请输入等级名称！",
    levelName_not_dup : "等级名称重复！",
    zscal_fanwei_not_right : "赠送比例范围0-100",
    kscale_fanwei_not_right : "折扣比例0-1"
   
};
qy_lang.customercall = {
		industryType_not_null : "请选择行业类型！",
		price_format_error : "参考价格格式不正确！",
		baseType_not_null : "请选择业务类型！",
		serviceId_not_null : "请选择服务项目！",
		businessId_not_null : "请选择加盟商！",
		seatReplay_not_null: "请输入工单答复！",
		businessType_not_null : "请选择业务类别！",
		accessType_not_null : "请选择受理方式！",
		type_not_null : "请选择工单类别！",
	};

qy_lang.serviceItem = {
		serviceItem_not_null : "请输入服务项目！",
		serviceItem_is_exist : "服务项目不能重复！",
		itmeType_not_null : "请选择业务类型！"
	};
qy_lang.notice = {
	    title_not_null : "请输入公告标题！",
	    effectiveBeginTime_not_null : "请输入生效时间！",
		content_not_null : "请输入公告内容！",
		content_length_limit : "内容长度不能超过2000个字！",
		content_submit_error :"公告内容提交失败！",
		content_already_right :"公告内容已提交！"
	};

qy_lang.business = {
		bname_not_null : "请输入单位名称！",
		bname_is_exist : "单位名称不能重复！",
		bnature_not_null : "请选择企业性质！",
		profession_not_null : "请选择行业类型！",
		burl_not_null : "请输入网址不能！",
		address_not_null : "请输入单位地址！",
		phone_not_null : "请输入办公电话！",
		contacts_not_null : "请输入联系人！",
		mobile_not_null : "请输入联系人电话！"
	};

qy_lang.knowledge = {
		subject_not_null : "请输入标题！",
		keyword_not_null : "请输入关键字！",
		content_length_limit : "内容长度不能超过2000个字！",
		content_not_null : "请输入内容！",
		knowledgeType_not_null : "请选择分类名称！",
		TypeName_not_null:"请输入分类名称！"
};


qy_lang.holiday = {
		content_not_null:"请输入内容！",
		beginTime_not_null:"请选择开始时间",
		endTime_not_null:"请选择结束时间"
	   
};

qy_lang.workorder = {
		resultInfo_not_null : "请输入办结结果！",
		visitInfo_not_null : "请输入回访结果！",
		type_not_null : "请选择诉求类型！",
		scope_not_null : "请选择涉及范围！",
		proposedInfo_not_null: "请输入拟办意见！",
		receiveGroupName_not_null: "请输入承办单位！",
		timeLimit_not_null: "请选择办理期限！",
		logInfo_not_null: "请输入反映内容！",
		proposedInfo_not_null: "请输入拟办意见！",
		auditMessage_not_null: "请选择审批意见！",
		result_not_null: "请选择批示意见！"
};
/**
 * 自定义流程的验证
 */
qy_lang.process = {
		process_name_not_null : "流程名称不能为空！",
		process_type_not_null : "请选择流程分类！",
		process_name_not_repate : "流程名称已存在！",
		process_OperateName_not_null : "操作名称不能为空！",
		process_OperateUrl_not_null : "操作URL不能为空！",
		process_OperateName_limit : "操作名称不能超过50个字符！",
		process_OperateUrl_limit : "操作名称不能超过400个字符！"
};
/**
 * 新建工单验证
 */
qy_lang.customerCall = {
		uname_not_null : "请输入姓名！",
		uaddress_not_null : "请输入用户地址！",
		content_not_null : "请输入工单内容！",
		uphone_not_null : "请输入联系电话！",
		uphone_not_right : "请输入正确的电话格式！",
		receiveMoney_not_right : "请输入正确的月收入金额！",
			seatReplay_not_null: "请输入工单答复！",
			businessType_not_null : "请选择业务类别！",
			accessType_not_null : "请选择受理方式！",
			type_not_null : "请选择工单类别！",
};
/**
 * 字数超长限制
 */
qy_lang.maxLength = {
		answercontent_max_length :"超出字数限制，请按字数限制填写"
};


/**
 * 行政区新增，修改验证
 */
qy_lang.area = {
		areaName_not_null : "请输入名称！",
		orderNum_not_null : "请输入排序号！"
};

/**
 * 号码段维护
 */
qy_lang.phoneAttribution = {
		mobileNumber_not_null : "请输入手机号码前七位数字!",
		mobileArea_not_null : "请输入归属区域！",
		areaCode_not_null : "请输入区号！"
};

/**
 * 问题上报
 */
qy_lang.projectq = {
		content_not_null : "请输入问题描述!",
		content_max_length : "超出字数限制，请按字数限制填写"
};
/**
 * 来电弹屏/工单回访发送短信
 */
qy_lang.sendmessage = {
		content_not_null : "请输入短信内容!",
		content_max_length : "超出字数限制，请按字数限制填写！"
};

qy_lang.localAddPhone = {
		phone_not_null:"号段不能为空！",
		phone_not_right:"号段只能输入数字！"
};

/**
* 乌海 安全管理人员
*/
qy_lang.safety = {
		name_not_null:"姓名不能为空!",
		idNumber_not_null:"身份证号不能为空!",
		must_be_idCard : "请输入正确的身份证号",
		certificateNum_not_null:"证书号码不能为空!",
		requireField_not_null:"换证日期不能为空!",
		phone_not_null:"联系电话不能为空！",
		phone_not_mobile:"请输入正确格式的联系电话！",
		text_max_length:"备注最大字数为256，请修改!"
};
/**
* 乌海 特种作业人员
*/
qy_lang.special = {
		name_not_null:"姓名不能为空!",
		idNumber_not_null:"身份证号不能为空!",
		must_be_idCard : "请输入正确的身份证号",
		certificateNum_not_null:"证书编号不能为空!",
		startTime_not_null:"上岗时间不能为空!",
		workType_not_null:"工种不能为空！",
		issuingAuthority_not_null:"发证机关不能为空！",
		text_max_length:"备注最大字数为256，请修改!"
};
/**
* 乌海 企业法人
*/
qy_lang.legal = {
		name_not_null:"姓名不能为空!",
		certificateNum_not_null:"证件号码不能为空!",
		phone_not_null:"联系电话不能为空！",
		cardType_not_null:"请选择证件类型！",
		phone_not_mobile:"请输入正确格式的联系电话！"
};
/**
* 乌海 企业注册
*/
qy_lang.comreg = {
		name_not_null:"企业名称不能为空!",
		loginName_not_null:"企业账号不能为空!",
		loginPass_not_null:"登录密码不能为空!",
		userName_not_null:"联系人不能为空!",
		phone_not_null:"联系电话不能为空！",
		phone_not_mobile:"请输入正确格式的联系电话！",
		text_max_length:"备注最大字数为256，请修改!",
		newLoginPass_not_null:"新密码不能为空!",
		newAgainLoginPass_not_null:"确认新密码不能为空!"
};
/**
* 乌海 企业信息
*/
qy_lang.wuhaicom = {
		dwdm_not_null:"单位代码不能为空!",
		ssx_not_null:"省市县不能为空!",
		clsj_not_null:"成立时间不能为空!",
		fddbr_not_null:"法定代表人不能为空！",
		qygm_not_null:"企业规模不能为空！",
		extractDescription_not_null:"生产投入提取标准不能为空！",
		fzr_not_null:"安全生产负责人不能为空！",
		this_not_email:"请输入正确格式的电子邮箱！",
		phone_not_mobile:"请输入正确格式的联系电话！",
		text_max_length:"超过字数最大限制，请修改!"
};


/**
 * 乌海 安全生产事故
 */
qy_lang.wuhaiSafeAccident = {
	accidentName_not_null:"事故名称不能为空!",
	responsible_not_null:"事故调查人/部门不能为空!",
	occurredTime_not_null:"事故发生时间不能为空!",
	occurredAddress_not_null:"事故地点不能为空！"
};

/**
 * 乌海 企业产品
 */
qy_lang.wuhaiProduct = {
	outputYear_not_null:"年设计产量不能为空!",
	outputMouth_not_null:"月设计产量不能为空!",
	useYear_not_null:"年消耗量不能为空!",
	useMouth_not_null:"月消耗量不能为空！",
	text_max_length:"备注最大字数为256，请修改!"
};


/**
 * 乌海 公司危险化学品
 */
qy_lang.wuhaiCompanyDangerChemicals = {
		storagePlace_not_null:"存放地点不能为空!",
		num_not_null:"数量不能为空!",
		num_not_PositiveIntegerOrFloat:"数量必须是正整数或正浮点数(8位整数，2位小数)!",
		userPlace_not_null:"使用地点不能为空!",
		riskType_not_null:"危险性分类不能为空！",
		riskNum_not_null:"危规号不能为空！",
		packagingCategory_not_null:"包装类别不能为空！",
		registrationNO_not_null:"登记号不能为空！"
};

/**
 * 乌海 危险化学品目录
 */
qy_lang.wuhaiChemicalsDirectory = {
		code_not_null:"编号不能为空!",
		materialName_not_null:"品名不能为空!",
		molecularFormula_not_null:"别名不能为空!",
		cas_not_null:"CAS号不能为空!",
		other_not_null:"备注不能为空!"
};

/**
 * 乌海 危险化学品
 */
qy_lang.wuhaiDangerChemicals = {
		materialName_not_null:"物质名称不能为空!",
		molecularFormula_not_null:"分子式不能为空!",
		meltingPoint_not_null:"熔点（℃）不能为空!",
		meltingPoint_not_number:"熔点（℃）为整数或浮点数(8位整数，2位小数)!",
		boilingPoint_not_null:"沸点（℃）不能为空!",
		boilingPoint_not_number:"沸点为整数或浮点数(8位整数，2位小数)!",
		gravity_not_null:"比重（水=1）不能为空!",
		gravity_not_number:"比重（水=1）为整数或浮点数(8位整数，2位小数)!",
		saturatedVaporPressure_not_null:"饱和蒸气压（kPa）不能为空!",
		saturatedVaporPressure_not_number:"饱和蒸气压（kPa）为整数或浮点数(8位整数，2位小数)!",
		vaporDensity_not_null:"蒸气密度（空气=1）不能为空!",
		vaporDensity_not_number:"蒸气密度（空气=1）为整数或浮点数(8位整数，2位小数)!",
		solubility_not_null:"溶解性不能为空!",
		appearance_not_null:"外观与性状不能为空!",
		dangerousCharacteristic_not_null:"危险特性不能为空!",
		fireFightingMethods_not_null:"处置方法不能为空!",
		taboo_not_null:"禁忌物不能为空!",
		breakdownProducts_not_null:"燃烧（分解）产物不能为空!",
		healthRisk_not_null:"健康危害（急性和慢性）不能为空!",
		leakageHandling_not_null:"泄漏紧急处理不能为空!",
		storageTransportationAttenti_not_null:"储运注意事项不能为空!",
		mac_not_null:"MAC不能为空!",
		engineeringControl_not_null:"工程控制不能为空!",
		respiratoryProtection_not_null:"呼吸系统防护不能为空!",
		bodyProtection_not_null:"身体防护不能为空!",
		handProtection_not_null:"手防护不能为空!",
		eyeProtection_not_null:"眼防护不能为空!"
};

/**
 * 乌海 培训
 */
qy_lang.train = {
		userName_not_null:"姓名不能为空!",
		age_not_null:"年龄不能为空!",
		receiveType_not_null:"录用形式不能为空!"
};

/**
 * 乌海 公司危险化学品
 */
qy_lang.wuhaiPlans = {
		planNo_not_null:"预案编号不能为空!",
		agent_not_null:"经办人不能为空!",
		phone_not_null:"企业电话不能为空!",
		phone_not_mobile:"请输入正确格式的电话!",
		prepareTime_not_null:"备案时间不能为空！",
		prepareEndTime_not_null:"备案到期时间不能为空！"
};

/**
 * 乌海 安全生产专家管理
 */
qy_lang.professor = {
		name_not_null:"姓名不能为空!",
		specialties_not_null:"专业特长不能为空!",
		title_not_null:"技术职称不能为空!",
		workCompany_not_null:"工作单位不能为空！",
		phone_not_null:"联系电话不能为空！",
		phone_not_mobile:"请输入正确格式的电话!"
};

/**
 * 乌海 重大危险源危化品
 */
qy_lang.dangersourcesgood = {
		dangerGoodName_not_null:"危化品名称不能为空!",
		unCode_not_null:"UN编号不能为空!",
		purpose_not_null:"生产用途不能为空!",
		process_not_null:"生产工艺不能为空！",
		physicalState_not_null:"物理状态不能为空！",
		
		operationTemp_not_null:"操作温度不能为空！",
		operationTemp_not_number:"操作温度必须是数字！",
		
		operationPressure_not_null:"操作压力不能为空！",
		operationPressure_not_number:"操作压力必须是数字！",
		
		simpleStock_not_null:"存量能为空！",
		simpleStock_not_number:"存量必须是数字！",
		
		unitStock_not_null:"单元内危化品存量不能为空！",
		unitStock_not_number:"单元内危化品存量必须是数字！",
		
		criticalMass_not_null:"临界量不能为空！",
		criticalMass_not_number:"临界量必须是数字！"
		
};

/**
 * 乌海 提取
 */
qy_lang.wuhaifee = {
		turnover_not_null:"上年度营业额不能为空!",
		turnover_not_positiveIntegerOrFloat:"上年度营业额必须是数字！",
		
		extractTime_not_null:"提取时间不能为空！",
		
		extractMoney_not_null:"本次提取不能为空！",
		extractMoney_not_isNumber:"本次提取必须是数字！",
		
		useTime_not_null:"使用日期不能为空！",
		useDirection_not_null:"资金用向不能为空！",
		useMoney_not_null:"费用提取金额不能为空！",
		useMoney_not_isNumber:"费用提取金额必须是数字！"
			
};

/**
 * 获取格式化后的提示信息
 * 
 * @param str
 *            调用的信息
 * @returns 格式化后信息
 */
function sprintf(str) {
	var array = str.split("&&");
	var msgArray;
	var paramArray;
	var result = qy_lang;
	if (array.length == 1) {
		// 没有需要替换的参数
		msgArray = array[0].split(".");

		for ( var i = 0; i < msgArray.length; i++) {
			result = result[msgArray[i]];
		}
	} else {
		msgArray = array[0].split(".");
		paramArray = array[1].split("##");

		for ( var i = 0; i < msgArray.length; i++) {
			result = result[msgArray[i]];
		}
		result = result.format(paramArray);
	}
	return result;
}

String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	var paramArray = arguments[0];
	for ( var s = this, i = 0; i < paramArray.length; i++) {
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), paramArray[i]);
	}
	return s;
};