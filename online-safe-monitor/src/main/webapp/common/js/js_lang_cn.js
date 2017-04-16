var qy_lang = {};

qy_lang.area = {
		areaName_not_null:"名称不允许为空",
		orderNum_not_null:"排序号不允许为空"
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


qy_lang.notice = {
	    title_not_null : "请输入公告标题！",
	    effectiveBeginTime_not_null : "请输入生效时间！",
		content_not_null : "请输入公告内容！",
		content_length_limit : "内容长度不能超过2000个字！",
		content_submit_error :"公告内容提交失败！",
		content_already_right :"公告内容已提交！"
	};

qy_lang.knowledge = {
		subject_not_null : "请输入标题！",
		keyword_not_null : "请输入关键字！",
		content_length_limit : "内容长度不能超过2000个字！",
		content_not_null : "请输入内容！",
		knowledgeType_not_null : "请选择分类名称！",
		TypeName_not_null:"请输入分类名称！"
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
 * 乌海 阈值模板
 */
qy_lang.wuhaiTemplate = {
		templateName_not_null : "模板名称不能为空！",
		range_not_valid : "适用量程格式不正确！",
};

/**
 * 乌海 危险源
 */
qy_lang.wuhaiDangerSource = {
		dangerSourceName_not_null : "重大危险源名称不能为空！",
		rvalue_not_null : "危险源级别不能为空！",
		rvalue_not_number :"危险源级别必须是数字！",
		address_not_null : "重大危险源所在地址不能为空！",
		productScale_not_null : "单元内主要装置、设施及生产（存储）规模不能为空！",
		minDistance_not_null : "重大危险源与周边重点防护目标最近距离情况不能为空！",
		minDistance_not_number : "重大危险源与周边重点防护目标最近距离情况必须是数字！",
		estimatePeopleCount_not_number : "厂区边界外500米范围内人数估算值必须是数字！",
		accidentDesc_not_null : "近三年内危险化学品事故情况不能为空！",
};

/**
 * 乌海 设备
 */
qy_lang.wuhaiCollectDevice = {
		installPosition_not_null : "安装位置不能为空！",
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