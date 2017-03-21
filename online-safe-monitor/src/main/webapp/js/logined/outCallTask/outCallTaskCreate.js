$(document).ready(function(){
	dynamicBinding( $("#des").get(0) );
	//得到外呼任务类型
	getTaskType();
	
	//编辑情况
	var phoneTaskId = $("#phoneTaskId").val();
	if(phoneTaskId){
		getTaskInfo(phoneTaskId);
	}else{
		funPlaceholder(document.getElementById("title"));
		funPlaceholder(document.getElementById("des"));
	}
	
//	$(".x").live("click",function(){
//		$(this).parent().remove();
//		var seatUserIds = "";//坐席id集合
//		var seatCount = 0;//统计坐席总人数
//		var crmUserIds = "";//添加的用户档案id集合
//		var taskUserCount = 0;//统计外呼对象总人数
//		var exportUserIds = "";//导入的外呼对象id集合
//		var taskUserIds = "";//已保存的外呼对象
//		//坐席
//		$("a[name=seat]").each(function(index,element){
//			var seatId = $(this).attr("value");
//			seatUserIds += seatId + ",";
//			seatCount++;
//		});
//		$("#seatUserIdsCount").html(seatCount);
//		$("#seatUserIds").val(seatUserIds);
//		//从用户档案中添加的外呼对象
//		$("a[name=crm]").each(function(index,element){
//			var crmId = $(this).attr("value");
//			crmUserIds += crmId + ",";
//			taskUserCount++;
//		});
//		$("#crmUserIds").val(crmUserIds);
//		//导入的外呼对象
//		$("a[name=exportUser]").each(function(index,element){
//			var exportUserId = $(this).attr("value");
//			exportUserIds += exportUserId + ",";
//			taskUserCount++;
//		});
//		$("#exportUserIds").val(exportUserIds);
//		//已保存的外呼对象
//		$("a[name=taskUser]").each(function(index,element){
//			var taskUserId = $(this).attr("value");
//			taskUserIds += taskUserId + ",";
//			taskUserCount++;
//		});
//		$("#taskUserIds").val(taskUserIds);
//		$("#outCallPeopleCount").html(taskUserCount);
//	});
	//坐席删除
	$("a[name=seat]").live("click",function(){
		$(this).parent().remove();
		var seatUserIds = "";//坐席id集合
		var seatCount = 0;//统计坐席总人数
		//坐席
		$("a[name=seat]").each(function(index,element){
			var seatId = $(this).attr("value");
			seatUserIds += seatId + ",";
			seatCount++;
		});
		$("#seatUserIdsCount").html(seatCount);
		$("#seatUserIds").val(seatUserIds);
	});
	
	var taskUserCount;
	//删除CRM添加的
	$("a[name=crm]").live("click",function(){
		taskUserCount = 0;//统计外呼对象总人数
		$(this).parent().remove();
		var crmUserIds = "";//添加的用户档案id集合
		//从用户档案中添加的外呼对象
		$("a[name=crm]").each(function(index,element){
			var crmId = $(this).attr("value");
			crmUserIds += crmId + ",";
			taskUserCount++;
		});
		taskUserCount += $("a[name=exportUser]").length;
		taskUserCount += $("a[name=taskUser]").length;
		$("#crmUserIds").val(crmUserIds);
		$("#outCallPeopleCount").html(taskUserCount);
	});
	//删除导入的
	$("a[name=exportUser]").live("click",function(){
		taskUserCount = 0;//统计外呼对象总人数
		$(this).parent().remove();
		var exportUserIds = "";//导入的外呼对象id集合
		//导入的外呼对象
		$("a[name=exportUser]").each(function(index,element){
			var exportUserId = $(this).attr("value");
			exportUserIds += exportUserId + ",";
			taskUserCount++;
		});
		$("#exportUserIds").val(exportUserIds);
		taskUserCount += $("a[name=crm]").length;
		taskUserCount += $("a[name=taskUser]").length;
		$("#outCallPeopleCount").html(taskUserCount);
	});
	//删除已有的
	$("a[name=taskUser]").live("click",function(){
		taskUserCount = 0;//统计外呼对象总人数
		$(this).parent().remove();
		var taskUserIds = "";//已保存的外呼对象
		//已保存的外呼对象
		$("a[name=taskUser]").each(function(index,element){
			var taskUserId = $(this).attr("value");
			taskUserIds += taskUserId + ",";
			taskUserCount++;
		});
		$("#taskUserIds").val(taskUserIds);
		taskUserCount += $("a[name=crm]").length;
		taskUserCount += $("a[name=exportUser]").length;
		$("#outCallPeopleCount").html(taskUserCount);
	});
	
	$("#addSeat").click(function(){
		//$("#addMember").next().remove();
		var userIdList=$("#seatUserIds").val();
		showAllSeat(userIdList);
	});
	//坐席清空方法
	$("#clearSeat").click(function(){
		$("#seatUserNames").html("");
		$("#seatUserIds").val("");
		$("#seatUserIdsCount").text("0");
	});
	
	//外呼对象添加按钮单击事件
	$("#addPeople").click(function(){
		var crmIds = $("#crmUserIds").val();
		openCrmUser(crmIds);
	});
	
	//导入外呼对象
	$("#import").click(function(){
		importing();
	});
	//外呼对象清空
	$("#clearTaskUser").click(function(){
		$("#outCallPeopleNames").html("");
		$("#crmUserIds").val("");
		$("#exportUserIds").val("");
		$("#taskUserIds").val("");
		$("#outCallPeopleCount").html("0");
	});
	//下一步
	$("#nextStep").click(function(){
		nextStep(2);
	});
	//存草稿
	$("#saveTemp").click(function(){
		nextStep(1);
	});
});

/**
 * 得到任务类型
 */
function getTaskType(){
	$.ajax({
		url : basePath + "phoneTask/phonetask_getTaskType.action",
		type : "post",
		success : function(data){
			var json = eval("("+data+")");
			var html = '';
			for(var i=0;i<json.length;i++){
				var taskName = json[i].name;
				var value = json[i].value;
				html += '<option value=\"'+value+'\">'+taskName+'</option>';
			}
			$("#taskType").append(html);
		}
	});
}

/**
 * 编辑时得到任务信息
 * @param phoneTaskId
 */
function getTaskInfo(phoneTaskId){
	$.ajax({
		url : basePath + "phoneTask/phonetask_loadPhoneTask.action",
		data : {"phoneTaskId":phoneTaskId},
		dataType : "text",
		type : "post",
		success : function(data){
			var json = eval("("+data+")");
			var taskName = json.taskName;
			var taskType = json.taskType;
			var taskExplain = json.taskExplain;
			var seatUserIds = json.seatUserIds;
			var seatStr = json.seatStr;
			var seatCount = json.seatCount;
			var crmIds = json.crmIds;
			var taskUserIds = json.taskUserIds;
			var taskUserStr = json.taskUserStr;
			var outCallPeopleCount = json.taskUserCount;
			$("#title").val(taskName);
			$("#taskType option[value="+taskType+"]").first().attr("selected","selected");
			$("#des").val(taskExplain);
			$("#seatUserIds").val(seatUserIds);
			$("#seatUserNames").html(seatStr);
			$("#seatUserIdsCount").html(seatCount);
			$("#outCallPeopleNames").html(taskUserStr);
			$("#crmUserIds").val(crmIds);
			$("#taskUserIds").val(taskUserIds);
			$("#outCallPeopleCount").html(outCallPeopleCount);
			
			//编辑时，数据加载完后执行placeholder插件
			funPlaceholder(document.getElementById("title"));
			funPlaceholder(document.getElementById("des"));
		}
	});
}

/**
 * 进入下一步
 */
function nextStep(state){
//	$("#saveTemp").attr("disabled",true);
	$("#nextStep").attr("disabled",true);
	// 框架校验
	if (!validator(document.getElementById("addroomtable"))) {
//		$("#saveTemp").attr("disabled",false);
		$("#nextStep").attr("disabled",false);
		return;
	}
	if($("#des").html().length > parseInt( $("#des").attr("fmaxlength"), 10) ){
		showObjError($("#des"), 'maxLength.answercontent_max_length');
		$("#nextStep").attr("disabled",false);
		return ;
	}else{
		hideError($("#des"));
	}
	if($("#seatUserIds").val()==""||$("#seatUserIds").val()==null){
		showObjError($("#seatUserIds"), 'outCallTask.seatUserIds_not_null');
//		$("#saveTemp").attr("disabled",false);
		$("#nextStep").attr("disabled",false);
		return;
	}else{
		hideError($("#seatUserIds"));
	}
	if(($("#crmUserIds").val()==""||$("#crmUserIds").val()==null)&&($("#exportUserIds").val()==""||$("#exportUserIds").val()==null)&&($("#taskUserIds").val()==""||$("#taskUserIds").val()==null)){
		showObjError($("#crmUserIds"), 'outCallTask.taskUser_not_null');
//		$("#saveTemp").attr("disabled",false);
		$("#nextStep").attr("disabled",false);
		return;
	}else{
		hideError($("#crmUserIds"));
	}
	
	var phoneTaskId = $.trim($("#phoneTaskId").val());
	var title = $.trim($("#title").val());
	var des = $.trim($("#des").val());
	var taskType = $("#taskType option:selected").val();
	var seatUserIds = $("#seatUserIds").val();
//	seatUserIds = seatUserIds.substring(0,seatUserIds.length-1);
	var crmUserIds = $("#crmUserIds").val();
	var exportUserIds = $("#exportUserIds").val();
	var taskUserIds = $("#taskUserIds").val();
	var taskTotal = parseInt($("#outCallPeopleCount").html().toString());
	var param = {
			"phoneTask.taskState" : state,//任务状态 如果点击下一步也存草稿
			"phoneTask.taskName" : title,
			"phoneTask.taskExplain" : des,
			"phoneTask.taskType" : taskType,
			"phoneTask.vid":phoneTaskId,
			"phoneTask.seatUserIds":seatUserIds,
			"phoneTask.crmUserIds":crmUserIds,//从用户档案添加
			"phoneTask.callUserIds":exportUserIds,//导入的
			"phoneTask.phoneUserIds":taskUserIds,//已有的
			"phoneTask.taskTotal":taskTotal//需要外呼的数量
		};
	$.ajax({
		type : "post",
		url : basePath + "phoneTask/phonetask_addPhoneTask.action",
		data : param,
		dataType : "text",
//		beforeSend : function() {
//			$("body").lock();
//		},
//		complete : function() {
//			$("body").unlock();
//		},
		success : function(data) {
			var json = eval("("+data+")");
			var type = $("#type").val();
			if(state==1){
//				art.dialog.alert("保存成功！",function(){
					window.location.href = basePath + "logined/outCallTask/outCallTaskList.jsp";
//				});
			}else if(state==2){
				window.location.href = basePath + "phoneTask/phonetask_editNaire.action?type="+type+"&paperId=" + json.questionnaireId+"&phoneTaskId="+json.phoneTaskId ;
			}
		}
	});
}
/**
 * 坐席树
 * @param userIds
 */
function showAllSeat(userIds){
	openSelectUser(3,selectCallBack,"user",userIds, "roleManager");//选择人员
}
/**
 * 坐席弹窗的回调函数
 * @param data
 * @param param
 */
function selectCallBack(data, param) {
	var userIds = "";
	var userNames = "";
	var userStr = "";
	var count=0;
	data.forEach(function(value, key) {
				// alert("key="+key+",name="+value.Name+",id="+value.Id+",data="+value.Data+",type="+value.Type);
				userIds += value.Id + ",";
				userId = value.Id;
//				userNames += value.Name + ",";
				userName = value.Name;
				userPhone = value.data;
				if(userPhone==null){
					userPhone = "无";
				}
				userStr += "<li>"+userName+"("+userPhone+")<a class=\"x\" href=\"javascript:void(0);\" name=\"seat\" value=\""+userId+"\" ><\/a><\/li>";
				
				count++;
			});

	if (userIds == ",") {
		userIds = "";
	}
	//alert(userIds);
	$("#seatUserNames").html(userStr);
	$("#seatUserIds").val(userIds);
	if($.trim(userIds)!=''){
		hideError($("#seatUserIds"));
	}
	$("#seatUserIdsCount").html(count);
	$("#addMember").siblings("p[class='requireField']").remove();
}

/**
 * CRM树
 */
function openCrmUser(crmIds){
	var url = basePath + "/logined/outCallTask/selectCrmUser.jsp?defaultSelectId="+crmIds;
	var title = "选择人员";
	art.dialog.open(url, {
		title : title,
		width : 360,
		height : 407,
		lock : true,
	    opacity:0.08,
//	    resize:false,
//	    drag:false,
		button : [{
					name : '确定',
					focus:true,//按钮颜色加深
					callback : function() {
						crmCallBack(art.dialog.data("crmMap"));
						return true;
					}
				}, {
					name : '取消',
					callback : function() {
						return true;
					}
				}]
	}, false);
}
function crmCallBack(data) {
	var crmIds = ",";
	var crmNames = "";
	var crmStr = "";
//	var countStr = $("#outCallPeopleCount").text();
	var count = 0;
	data.forEach(function(value, key) {
				// alert("key="+key+",name="+value.Name+",id="+value.Id+",data="+value.Data+",type="+value.Type);
				var crmId = value.Id;
//				userNames += value.Name + ",";
				var crmName = value.Name;
				var crmPhone = value.data;
				
				$("#outCallPeopleNames li").each(function(index,element){
					var liStr = $(this).html().toString();
					var temp = crmName+"("+crmPhone+")";
					//存在姓名与电话相同的li时对其进行点击事件，删除此li，将值放入name="crm"的li中
					if(liStr.indexOf(temp)>=0){
						$(this).children().click();
					}
				});
				crmIds += value.Id + ",";
				if(crmPhone==null){
					crmPhone = "未知";
				}
				crmStr += "<li>"+crmName+"("+crmPhone+")<a class=\"x\" href=\"javascript:void(0);\" name=\"crm\" value=\""+crmId+"\" ><\/a><\/li>";
				
				count++;
			});

	if (crmIds == ",") {
		crmIds = "";
	}
	//alert(userIds);
	
	$("a[name=crm]").each(function(index,element){
		$(this).parent().remove();
	});
	$("#outCallPeopleNames").append(crmStr);
	$("#crmUserIds").val(crmIds);
	
	count += $("a[name=exportUser]").length;
	count += $("a[name=taskUser]").length;
	
	if(count>0){
		hideError($("#crmUserIds"));
	}
	
	$("#outCallPeopleCount").html(count);
	$("#addPeople").siblings("p[class='requireField']").remove();
}

/**
 * 导入外呼对象
**/
function importing(){
	importPhoneTaskUser();
	return false;
}
/**
 * 取消保存
 */
function goback(){
	var type = $("#type").val();
	if(type=="save"){  //删除任务
		var ids = $("#phoneTaskId").val();
		if(ids){
			art.dialog.confirm('确认要取消吗？<br/>已编辑的内容将不会保存。', function() {
//				$.ajax({
//					url : basePath + "question/deleteQuestion.action?tjId="+ ids,
//					type : "post",
//					dataType : "html",
//					success : function(data) {
//						if (data == 1) {
//							window.location.href = basePath + "logined/question/questionnaire.jsp";
//						} else {
//							art.dialog.alert("删除失败！");
//						}
//					}
//				});
				window.location.href = basePath +"logined/outCallTask/outCallTaskList.jsp";
			});
		}else{
			window.location.href = basePath +"logined/outCallTask/outCallTaskList.jsp";
		}
		
	}else{
		window.location.href = basePath +"logined/outCallTask/outCallTaskList.jsp";
	}
}
