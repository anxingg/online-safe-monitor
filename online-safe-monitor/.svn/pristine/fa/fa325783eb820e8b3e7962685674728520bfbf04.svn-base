$(document).ready(function(){
	getSelectCompany();
	getSelectPlans();
	
	// 保存按钮绑定事件
	$("#yjylsearch").bind("click", function() {
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
	//初始化预案类型下拉选
	initSelect("planType","plan_type");
	//加载列表
	getTableList();
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
				$("#companName").html($("#companName").html()+html);
			}
	});
}
function getSelectPlans(){
	$.ajax({
		url : basePath + "plans/plans_getPlansList.action",
		type : "post",
		dataType : 'json',
		data : {
		},
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				html += '<option value="">请选择</option>';
				for (var i = 0; i < list.length; i++) {
						var vid = list[i].vid;//id
						var planType = list[i].planType;//预案类型
						var planNo = list[i].planNo;//预案编号
						html += '<option value="'+vid+","+planType+'"';
						html += '>'+planNo+"</option>";
					}
				}
				$("#plan_id").html(html);
			}
	});
}

function addNotify(){
	window.location.href=basePath+"wh/yjyl/add.jsp";
}

function detail(vid){
	window.location.href=basePath+"yjyl/queryDetail.action?vid="+vid
}

function update(vid){
	window.location.href=basePath+"yjyl/queryDetail.action?ac_flag=upd&vid="+vid;
}

function del(vid){
	art.dialog.confirm("确定要删除吗？",function(){
		
		$.ajax({
			url:basePath+"yjyl/del.action",
			type:"post",
			data:"vid="+vid,
			dataType:"json",
			cache:false,
			success:function(data){
				if(data=="0"){
					window.location.href=basePath+"wh/yjyl/query.jsp";
				}else{
					artDialog.alert("安全隐患排查信息删除失败，请重试！");
				}
			},error:function(){
				artDialog.alert("安全隐患排查信息删除失败，请重试！");
			}
		});
		
	},function(){return;});
}
function getTableList(){
	var exercise_name = $.trim( $("#exercise_name").val() );
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
		aoData.push(
			    {"name":"exercise_name" ,"value": exercise_name},
			    {"name":"plan_id" ,"value":$("#plan_id").val()},
			    {"name":"group_id" ,"value":$("#companName").val()},
			    {"name":"plan_type" ,"value": $("#plan_type").val()});
		},
		"sAjaxSource" : basePath + "yjyl/query.action",
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
			"mDataProp" : 'id'
		}, {
			"sTitle" : '演练项目',
			"mDataProp" : "exercise_program",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '演练时间',
			"mDataProp" : 'exercise_time',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '演练地点',
			"mDataProp" : 'exercise_address',
			"sClass" : "longTxt"
		},{
			"sTitle" : '组织部门',
			"mDataProp" : 'organize_group',
			"sClass" : "longTxt"
		},{
			"sTitle" : '负责人',
			"mDataProp" : 'reviews',
			"sClass" : "longTxt"
		} ,
		{
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
		},
		"aoColumnDefs" : [
			{
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var html = '<a href="javascript:void(0);" onclick="detail('+vid+');">查看</a> &nbsp;&nbsp;';
					if(iszf != 1 && iszf != 3){
						html = html.concat('<a href="javascript:void(0);" onclick="update('+vid+');">修改</a> &nbsp;&nbsp;');
						html = html.concat('<a href="javascript:void(0);" onclick="del('+vid+');">删除</a>');
					}
					return html;
				}
			}
		]
	});
}

/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			$("#"+id).append("<option value='-1'>全部</option>");
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}
