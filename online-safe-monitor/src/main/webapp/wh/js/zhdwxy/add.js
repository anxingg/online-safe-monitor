$(document).ready(function(){
	//加载企业名称
	getDangerGrade();
	
	// 单击更新
	$("#save").click(function() {
				var cr = checkpara();
				if(cr){
					var valid = validator($("#addForm")[0]);
					if (valid) {
						addorup();
					}
					return false;
				}
			});
});

function getDangerGrade(){
	$.ajax({
		url : basePath + "zhdwxy/queryGrade.action",
		type : "post",
		dataType : 'json',
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				for (var i = 0; i < list.length; i++) {
						var name = list[i].name;
						var value = list[i].value;
						if(list[i].sysTag==1){
							html += '<option value="'+value+'"';
							html += '>'+name+"</option>";
						}
					}
				}
				$("#danger_grade").html(html);
			}
	});
}

function checkpara(){
	var danger_grade = $("#danger_grade").val();
	if(danger_grade==""){
		artDialog.alert("请选择重大危险源等级！");
		return false;
	}
	/*var review_time = $("#review_time").val();
	if(review_time==""){
		artDialog.alert("评审时间不能为空");
		return false;
	}
	var review_end_time = $("#review_end_time").val();
	if(review_end_time==""){
		artDialog.alert("评审过期时间不能为空");
		return false;
	}*/
	var distance =  $("#distance").val();
	if(distance==""||distance=="0"||!isPosInt(distance)){
		artDialog.alert("重大危险源与周边重点保护目标最近距离情况必须输入正整数！");
		return false;
	}
	return true;
}


function  addorup(){
	$.ajax({
		url:basePath+"zhdwxy/saveorup.action",
		type:"post",
		data:"grade="+$("#grade").val()+"&ac_flag=add&dangerSourcesName="+$("#dangerSourcesName").val()+"&vid="
		//+"&safetyMeasures="+$("#safety_measures").val()
		+"&address="+$("#address").val()+"&danger_grade="+$("#danger_grade").val()
		//+"&review_time="+$("#review_time").val()+"&review_end_time="+$("#review_end_time").val()
		+"&product_scale="+$("#product_scale").val()+"&distance="+$("#distance").val()+"&three_year_accident="+$("#three_year_accident").val()
		//+"&accident_measures="+$("#accident_measures").val(),
		+"&mechanism="+$("#mechanism").val()+"&men="+$("#men").val()+"&is_park="+$("#is_park").val()+"&use_time="+$("#use_time").val()+"&r_value="+$("#r_value").val(),
		dataType:"json",
		success:function(data){
			if(data=="0"){
				window.location.href=basePath+"wh/zhdwxy/query.jsp";
			}else{
				artDialog.alert("重大危险源信息新增失败！");
			}
		},error:function(){
			artDialog.alert("重大危险源信息新增失败！");
		}
	});
}


/**
 * 时间加3年再减一天
 */
function putTime(){
	var review_time = $("#review_time").val();
	if(review_time=="" || review_time.length!=10){
		return;
	}
	var beginDate = new Date();
	beginDate.setFullYear(review_time.substring(0,4));
	beginDate.setMonth(parseInt(review_time.substring(5,7), 10) - 1);
	beginDate.setDate(review_time.substring(8,10));
	beginDate.setFullYear(beginDate.getFullYear()+3);
	var endDate = new Date(beginDate.getTime()-(24 * 60 * 60 * 1000));
	var year = endDate.getFullYear();
	var month = endDate.getMonth()+1;
	if(month<10){
		month = "0"+month;
	}
	var day = endDate.getDate();
	if(day<10){
		day = "0"+day;
	}
	$("#review_end_time").val(year+"-"+month+"-"+day);
}
