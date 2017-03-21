/**
 * 加载完成事件
 */
$(document).ready(function() {
	//	
	setNotifyType(viewList);
		
	//Enter事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			viewList();
			return false;
		}
	});
	$("#searchButton").click(function(){
		viewList();
	});
})

/**
 * 
 * 
 * @return
 */
function viewList() {
	$('#myTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		"bStateSave" : false, // 保存状态
		"bDestroy" : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "subject",
				"value" : $.trim($("#subject").val())
			},{
				"name" : "notifyType",
				"value" : $.trim($("#notifyType").val())
			},{
				"name" : "columnId",
				"value" : $.trim($("#columnId").val())
			},{
				"name" : "isShowOut",
				"value" : 1
			});
		},
		"sAjaxSource" : basePath + "notify/notify_viewList.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 
		"bLengthChange" : false, // 
		"bFilter" : false, // 是否过滤
		"bSort" : false, // 是否排序
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 
		"iDisplayLength" : 15, // 每页显示数
		"aoColumns" : [{	
					"mDataProp" : "subject",
					"sClass" : "longTxt"
				},{	
					"mDataProp" : "typename"
				},{		
					"mDataProp" : "username"
				},{
	
					"mDataProp" : "approveDate"
				},{
					"mDataProp" : "totalCount",
					"sClass" : 	"right_bdr0 data_r"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"aoColumnDefs" :[{
			"aTargets" : [0],
			"fnRender" : function(oObj) {
				var isTop = oObj.aData.isTop;
				var subject = oObj.aData.subject;
				var columnId =$("#columnId").val();
				var counting = oObj.aData.counting;
				var fontWeight = "";
				if(counting == 0){
					fontWeight = "font-weight:bold;";
				}
				if(isTop==1){
					return '<a title="'+subject+'" style="text-decoration:none;color:#04578D;text-align:left;'+fontWeight+'"  href="'+ basePath+ 'logined/notify/view.jsp?notifyId='+ oObj.aData.notifyId + '&columnId='+columnId+'&returnType=1"><font color="red">[置顶]&nbsp;&nbsp;</font>'+subject + '</a>';
				}else{
					return '<a title="'+subject+'" style="text-decoration:none;color:#04578D;text-align:left;'+fontWeight+'"  href="'+ basePath+ 'logined/notify/view.jsp?notifyId='+ oObj.aData.notifyId+ '&columnId='+columnId+'&returnType=1">'+ subject+ '</a>';
				}
			}            
		},{
			"aTargets" : [1],
			"fnRender" : function(oObj) {
				var typename = oObj.aData.typename;
				if(typename==null){
					return "--";
				}else{
					return typename;
				}
			}
		}]
	});
}
/**
 * 加载下拉
 * 
 * @return
 */
function setNotifyType( callback ) {
	var paramData = {
		'infoType' : "notifyType"+$("#columnId").val(),
		'sysTag' : 1
	};
	$.ajax({
				url : basePath + "dict/getDicts.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
					jsonData = eval("(" + data + ")");
					for (var i = 0; i < jsonData.length; i++) {
						$("#notifyType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
					
					if(callback != null && callback!= undefined){
						callback();
					}
				}
			});
}
