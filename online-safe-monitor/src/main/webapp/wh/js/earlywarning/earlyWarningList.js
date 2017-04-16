$(function(){
	getTable();
	//城市初始化
	findSelect(1,"cityName",-1);
	//公司初始化
	findSelect(2,"companyId",-1);
	//危险源初始化
	findSelect(3,"dangerSourceId",-1);
})
/**
 * type 1 城市 2 公司 3.危险源 4 检测对象 5 传感器 6预警级别 7.状态
 */
function findSelect(type,id,parentId){
	var param={};
	var url=basePath + "earlyWarning/step_findCity0rCompany.action";
	if(type==1||type==2){
		param={"type":type,"parentId":parentId};
	}else if(type==3){
		param={"groupId":parentId};
		url=basePath + "dangersource/getSelectList.action";
	}
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : param,
		success : function(data) {
			if(data){
				var html = "<option value='-1'>全部</option>";
				for(var i=0;i<data.length;i++){
					if(parentId!=-1){
						if(i==0){
							html +="<option value='"+data[0].value+"' selected = 'selected'>"+data[0].name+"</option>";
						}else{
							html +="<option value='"+data[i].value+"'>"+data[i].name+"</option>";
						}
					}else{
						html +="<option value='"+data[i].value+"'>"+data[i].name+"</option>";
					}
				}
				
				$("#"+id).html(html);
				
			}
			
		}
	});
}
/**
 * 选择下拉框事件
 * num  级联1 公司 2.危险源 3 检测对象 4 传感器 5预警级别 6.状态
 */
function changeSelect(num){
	if(num==1){
		//选择城市 公司级联
		findSelect(2,"companyId",$("#cityName").val());
	}else if(num==2){
		findSelect(3,"dangerSourceId",$("#companyId").val());
	}
	
}



function getTable(){
	$('#List').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
			
			);
		},
		"sAjaxSource" : basePath + "earlyWarning/step_getPageList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"mDataProp" : 'cityName'
		}, {
			"mDataProp" : 'companyName'
		}, {
			"mDataProp" : 'dangerSourceName'
		}, {
			"mDataProp" : 'dangerSource'
		}, {
			"mDataProp" : 'sensorName'
		}, {
			"mDataProp" : 'waringLevelStr'
		}, {
			"mDataProp" : 'watchType'
		}, {
			"mDataProp" : 'state'
		}, {
			"mDataProp" : 'beginTime'
		},{
			"mDataProp" : null
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			 $('#myTable tbody  tr td').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
//			window.parent.frameResize();
		},
		"aoColumnDefs" : [
			{
				"aTargets" : [9], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var html = '';
					html+= '<a href="'+basePath+'wh/logined/thresholdTemplate/addTemplate.jsp?action=edit&vid='+vid+'">修改</a>';
					html+='<a href="javascript:void(0);" onclick="deleteTemplate('+vid+');">删除</a>';	
					return html;
				}
			}
		]
	});
}