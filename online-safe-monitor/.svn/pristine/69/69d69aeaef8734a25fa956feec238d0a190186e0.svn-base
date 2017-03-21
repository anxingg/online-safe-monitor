$(document).ready(function(){
	var whroletype = $("#whroletype").val();
	if(whroletype==1 || whroletype==3){//政府端
		//加载企业下拉列表
		getSelectCompany();
	}else if(whroletype==2){//企业端
		$("#qymc").hide();
	}
	// 保存按钮绑定事件
	$("#aqyhsearch").bind("click", function() {
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			getTableList();
			return false;
		}
	});
	//加载列表
	getTableList();
});

function addNotify(){
	window.location.href=basePath+"wh/aqyh/add.jsp";
}

function detail(vid){
	window.location.href=basePath+"aqyh/queryDetail.action?vid="+vid
}

function update(vid){
	window.location.href=basePath+"aqyh/queryDetail.action?ac_flag=upd&vid="+vid;
}

function del(vid){
	artDialog.confirm("确定要删除吗？",function(){
		$.ajax({
			url:basePath+"aqyh/del.action",
			type:"post",
			data:"vid="+vid,
			dataType:"json",
			cache:false,
			success:function(data){
				if(data=="0"){
					window.location.href=basePath+"wh/aqyh/query.jsp";
				}else{
					artDialog.alert("安全隐患排查信息删除失败，请重试！");
				}
			},error:function(){
				artDialog.alert("安全隐患排查信息删除失败，请重试！");
			}
		});
		
	});
}

function getTableList(){
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
		aoData.push(
				{"name":"group_id" ,"value":$.trim( $("#companName").val() )},
			    {"name":"responsible_department" ,"value":$.trim( $("#dept").val() )},
			    {"name":"responsible" ,"value": $.trim( $("#deptor").val() )},
			    {"name":"checkdate" ,"value": $("#happyTime").val()},
			    {"name":"review_time" ,"value": $("#reportTime").val()});
		},
		"sAjaxSource" : basePath + "aqyh/query.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '序号',
			"mDataProp" : 'id',
		}, {
			"sTitle" : '隐患内容',
			"mDataProp" : "check_condition",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '企业名称',
			"mDataProp" : "companyName",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '责任部门',
			"mDataProp" : 'responsible_department',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '责任人',
			"mDataProp" : 'responsible',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '整改期限',
			"mDataProp" : 'rectification_end_date'
		}, {
			"sTitle" : '整改情况',
			"mDataProp" : 'rectification',
				"sClass" : "longTxt"
		} ,{
			"sTitle" : '复查责任人',
			"mDataProp" : 'review_user',
			"sClass" : "longTxt"
		} ,{
			"sTitle" : '复查时间',
			"mDataProp" : 'review_time'
		} ,{
			"sTitle" : '操作',
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
				"aTargets" : [1], 
				"fnRender" : function(oObj) {
					var companyId = oObj.aData.vid;
					var check_condition= oObj.aData.check_condition;
					var html = '<a href="javascript:void(0);"  onclick="detail('+companyId+');">'+check_condition+'</a>';
				
					return html;
				}
			},
			{
				"aTargets" : [9], //操作
				"fnRender" : function(oObj) {
					var companyId = oObj.aData.vid;
					var datasource= oObj.aData.data_source;
					//var html = '<a href="javascript:void(0);"  onclick="detail('+companyId+');">查看</a>';
					var html = '';
					if(iszf == 3){
						html = '--';
					}else {
						html = html.concat('<a href="javascript:void(0);"  onclick="update('+companyId+');">修改</a>');
						if((iszf == 1 && datasource== '是')||( iszf != 1 && datasource=='否')){
							html = html.concat('<a href="javascript:void(0);"  onclick="del('+companyId+');">删除</a>');
						}
					}
					return html;
				}
			}
		]
	});
}


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
					html += '<option value="'+groupId+'">'+companyName+'</option>';
				}
				$("#companName").html($("#companName").html()+html);
			}
		}
		
	});
}