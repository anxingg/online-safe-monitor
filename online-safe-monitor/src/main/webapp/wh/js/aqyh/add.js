var ac_flag = "add";
var _click_=0;
$(document).ready(function(){
	//加载企业名称
	getSelectCompany();
	// 单击更新
	$("#save").click(function() {
				var cr = checkpara();
				if(cr){
					var valid = validator($("#addForm")[0]);
					if (valid) {
						if(_click_==0){
							addorup();
						}else{_click_++;}
					}
					return false;
				}
			});
	
});

function getSelectCompany(){
	$.ajax({
		url : basePath + "companywh/getCompanyNameList.action",
		type : "post",
		dataType : 'json',
		data : {
		},
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				for (var i = 0; i < list.length; i++) {
						var companyName = list[i].companyName;
						var groupId = list[i].groupId;
						html += '<option value="'+groupId+'"';
						html += '>'+companyName+"</option>";
					}
				}
				$("#companName").html(html);
			}
	});
}

function checkpara(){
	var group_id = $("#companName").val();
	if(group_id==""){
		artDialog.alert("请选择企业名称！");
		return false;
	}
	var checkDate = $("#checkDate").val();
	if(checkDate==""){
		artDialog.alert("检查日期不能为空！");
		return false;
	}

	return true;
}


function  addorup(){
	_company = $("#companName").val();
	var can_see=1;
	if(iszf==1){
		if($("#can_see").attr("checked")!="checked"){
			can_see=0;
		}
	}
	$.ajax({
		url:basePath+"aqyh/saveorup.action",
		type:"post",
		data:"group_id="+_company+"&ac_flag=add&danger_name="+$("#danger_name").val()+"&vid=&responsible_department="+$("#responsible_department").val()
		+"&responsible="+$("#responsible").val()+"&checkdate="+$("#checkDate").val()+"&check_condition="+$("#check_condition").val()+"&rectification_end_date="+$("#rectification_end_date").val()
		+"&rectification="+$("#rectification").val()+"&review_user="+$("#review_user").val()+"&review_time="+$("#review_time").val()
		+"&memo="+$("#memo").val()+"&can_see="+can_see,
		dataType:"json",
		success:function(data){
			if(data=="0"){
				window.location.href=basePath+"wh/aqyh/query.jsp";
			}else{
				_click_=0;
				artDialog.alert("安全隐患排查信息新增失败！");
			}
		},error:function(){
			_click_=0;
			artDialog.alert("安全隐患排查信息新增失败！");
		}
	});
}
