$(document).ready(function(){
	var whroletype = $("#whroletype").val();
	if(whroletype==1 ){//政府端
		$("#xz").show();//新增按钮
	}else if(whroletype==2 || whroletype==3){//企业端或政府普通用户
		$("#xz").hide();//新增按钮
	}
	// 保存按钮绑定事件
	$("#search").bind("click", function() {
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


function getTableList(){
	var name = $.trim($('#name').val());
	var specialties = $.trim($("#specialties").val());
	var type = $.trim($("#type").val());
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push(
				{
					"name" : "name",
					"value" : name
				},{
					"name" : "specialties",
					"value" : specialties
				},{
					"name" : "professorType",
					"value" : type
				});
		},
		"sAjaxSource" : basePath + "professor/getList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"mDataProp" : 'no'
		}, {
			"mDataProp" : "name"
		}, {
			"mDataProp" : 'specialties'
		}, {
			"mDataProp" : 'title'
		}, {
			"mDataProp" : 'workCompany'
		}, {
			"mDataProp" : 'phone'
		}, {
			"mDataProp" : 'memo',
			"sClass" : "longTxt"
		} ,{
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
				"aTargets" : [7], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var whroletype = $("#whroletype").val();
					var html = '<a href="'+basePath+'professor/getProfessorInfoView.action?vid='+vid+'">查看</a>';
					if(whroletype==1){//政府端
						html+= '<a href="'+basePath+'professor/getProfessorInfoAdd.action?vid='+vid+'">修改</a>';
						html+='<a href="javascript:void(0);" onclick="deleteProfessor('+vid+');">删除</a>';						
					}
					return html;
				}
			}
		]
	});
}


/**
 * 新增操作
 */
function addProfessor() {
	window.location.href = basePath + "wh/logined/professor/addProfessor.jsp?type="+$.trim($("#type").val());
}

function deleteProfessor(vid){
	//确认对话框
	art.dialog.confirm('确定删除该专家吗？', function() {
		$.ajax({
			url : basePath + "professor/delete.action",
			type : "post",
			dataType : 'json',
			data : {
				"professor.vid" : vid,
				"professor.professorType" : 3
			},
			success : function(data) {
				if (data == 1) {
					getTableList();
				} else if (data == 0){
					artDialog.alert("删除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}

