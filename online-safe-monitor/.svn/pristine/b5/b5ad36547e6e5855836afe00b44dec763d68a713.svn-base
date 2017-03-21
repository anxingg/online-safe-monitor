var qy_wh_lang = {};
qy_wh_lang.global = {
		 page_up : "<<",
		    page_down : ">>",
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

//即时消息
qy_wh_lang.aqyh = {
		aqyh_name_not_null : "安全隐患名称不能为空，请输入！",
		aqyh_name_valid : "安全隐患名称非法，请重新输入！",
		aqyh_dept_not_null : "责任部门不能为空，请检查！",
		aqyh_responsible_not_null:"责任人不能为空，请检查！",
		aqyh_checkdate_not_null:"检查日期不能为空，请检查！",
		aqyh_check_condition_not_null:"隐患内容不能为空，请输入！"
		
};

//重大危险源
qy_wh_lang.zhdwxy = {
		zhdwxy_name_not_null : "重大危险源名称不能为空，请重新输入！",
		zhdwxy_name_valid : "重大危险源名称非法，请重新输入！",
		zhdwxy_danger_grade_valid:"请选择重大危险源等级！",
		zhdwxy_address_not_null:"危险源企业内部详细地址不能为空，请输入！",
		zhdwxy_review_time_not_null:"评审时间不能为空，请输入！",
		zhdwxy_review_end_time_not_null:"评审过期时间（3年）不能为空，请输入！",
		zhdwxy_product_scale_not_null:"单元内主要装置、设施及生产（储存）规模不能为空，请输入！",
		zhdwxy_safety_measures_not_null:"安全管理措施不能为空，请输入！",
		zhdwxy_distance_not_null:"重大危险源与周边重点保护目标最近距离情况（米）不能为空，请输入！",
		zhdwxy_three_year_accident_not_null:"近三年内危险化学品事故情况不能为空，请输入！",
		zhdwxy_accident_measures_not_null:"发生事故时紧急措施不能为空，请输入！"
};

//应急演练
qy_wh_lang.yjyl = {
		yjyl_plan_id_valid : "应急预案不能为空，请选择！",
		yjyl_organize_group_not_null : "演练组织部门不能为空，请重新输入！",
		yjyl_exercise_name_not_null:"应急演练名称不能为空！",
		yjyl_exercise_program_not_null:"应急演练项目不能为空，请输入！",
		yjyl_exercise_purpose_not_null:"演练目的不能为空，请输入！",
		yjyl_exercise_address_not_null:"演练地点不能为空，请输入！",
		yjyl_exercise_people_not_null:"参演人员不能为空，请输入！",
		yjyl_exercise_records_not_null:"演练记录不能为空，请输入！",
		yjyl_rescue_reviews_not_null:"现场救援讲评不能为空，请输入！",
		yjyl_reviews_not_null:"现场救援讲评负责人不能为空，请输入！"
};

/**
 * 字数超长限制
 */
qy_wh_lang.maxLength = {
		answercontent_max_length :"超出字数限制，请按字数限制填写"
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
	var result = qy_wh_lang;
	if (array.length == 1) {
		// 没有需要替换的参数
		msgArray = array[0].split(".");

		for (var i = 0; i < msgArray.length; i++) {
			result = result[msgArray[i]];
		}
	} else {
		msgArray = array[0].split(".");
		paramArray = array[1].split("##");

		for (var i = 0; i < msgArray.length; i++) {
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
	for (var s = this, i = 0; i < paramArray.length; i++) {
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), paramArray[i]);
	}
	return s;
};
