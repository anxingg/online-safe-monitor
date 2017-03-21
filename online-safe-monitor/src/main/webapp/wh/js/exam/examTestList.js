$(document).ready(function(){
	
		getExamQuestList();
		setQuestionType();
		// 保存按钮绑定事件
		$("#search").bind("click", function() {
			getExamQuestList();
			return false;
		});
		//回车事件
		$(document).keydown(function(event){
			var code=event.which;
			if (code == 13) {
				getExamQuestList();
				return false;
			}
		});
});

/**
 * 初始化考试类型数据
 * 
 * @return
 */
function setQuestionType() {
	var paramData = {
		'infoType' : "examType",
		'sysTag' : 1
	};
	qytx.app.ajax({
				url : basePath + "dict/getDicts.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
				var	jsonData = eval("(" + data + ")");
					$("#examType").empty();
					$("#examType").append("<option value='' seleted>请选择</option>");
					for (var i = 0; i < jsonData.length; i++) {
						$("#examType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
				}
			});
}


function getExamQuestList() {
	
	var title=$.trim( $("#title").val() );
	var titleType = $("#examType").val();
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
					"name" : "search.title",
					"value" : title
				}, {
					"name" : "search.examType",
					"value" : titleType
				}, {
					"name" : "search.state",
					"value" : 0
				});
		},
		"sAjaxSource" : basePath + "exam/listExamTest.action",
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
			"mDataProp" : 'no'
		}, {
			"sTitle" : '考试名称',
			"mDataProp" : "testName",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '考试类型 ',
			"mDataProp" : 'testType'
		}, {
			"sTitle" : '试卷名称',
			"mDataProp" : 'paperSrt',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '公司名称',
			"mDataProp" : 'companyName'
		}, {
			"sTitle" : '是否生效',
			"mDataProp" : 'state'
		},{
			"sTitle" : '操作',
			"mDataProp" : null
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {

			 $('#Table tbody  tr td').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
			
			
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
//			window.parent.frameResize();
		},
		"aoColumnDefs" : [
			{
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var examTestId = oObj.aData.id;
					var html = '';
					 html += '<a href="updateExamTest.jsp?examTestId='+examTestId+'">修改</a>';
					if(oObj.aData.state=="生效"){
						html += ' <a href="javascript:void(0);" onclick="updateState('+examTestId+',1);">失效</a>';
					}
					else{
						html += ' <a href="javascript:void(0);" onclick="updateState('+examTestId+',0);">生效</a> ';
					}
					 html += '<a href="javascript:void(0);" onclick="deletequestion('+examTestId+');">删除</a>';
					return html;
				}
			}
		]
	});
}

function updateState(examTestId,state){
	//确认对话框
	var content = "";
	if(state==0){
		content = "确定使该考试生效吗？";
	}else{
		content = "确定使该考试失效吗？";
	}
	art.dialog.confirm(content, function() {
		$.ajax({
			url : basePath + "/exam/updateTestState.action",
			type : "post",
			dataType : 'json',
			data : {
				examTestId : examTestId,
				state : state
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					window.location.href="examTestList.jsp";
				} else if (data == 0){
					artDialog.alert("操作失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
}


/**
 * 删除
 * */
function deletequestion(examTestId){
	//确认对话框
	art.dialog.confirm('确定删除该考试吗？', function() {
		$.ajax({
			url : basePath + "/exam/deleteExamTest.action",
			type : "post",
			dataType : 'json',
			data : {
				examTestId : examTestId
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					window.location.href="examTestList.jsp";
				} else if (data == 0){
					artDialog.alert("删除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}




/**
 * 新增
 */
function addExamTest(){
	
	window.location.href="addExamTest.jsp"; 
}

