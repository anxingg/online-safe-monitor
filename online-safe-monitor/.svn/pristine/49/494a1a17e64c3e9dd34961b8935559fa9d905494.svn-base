$(document).ready(function() {

	viewList();
	
});

/**
 * 
 * 
 * @return
 */
function viewList() {
	$('#myTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		"bStateSave" : false,
		"bDestroy" : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "subject",
				"value" : ""
			},{
				"name" : "notifyType",
				"value" : 0
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
		"bPaginate" : true, 
		"bLengthChange" : false, 
		"bFilter" : false, 
		"bSort" : false, 
		"bInfo" : true,
		"bAutoWidth" : false,
		"iDisplayLength" : 10, 
		"aoColumns" : [{	
					"mDataProp" : "subject",
					"sClass" : "longTxt"
				},{
					"mDataProp" : null
				},{		
					"mDataProp" : "username"
				},{
					"mDataProp" : "approveDate"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" 
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
					return '<a title="'+subject+'" style="text-decoration:none;color:#04578D;text-align:left;'+fontWeight+'"  href="'+ basePath+ 'logined/notify/view.jsp?notifyId='+ oObj.aData.notifyId + '&columnId='+columnId+'&returnType=11"><font color="red">[置顶]&nbsp;&nbsp;</font>'+subject + '</a>';
				}else{
					return '<a title="'+subject+'" style="text-decoration:none;color:#04578D;text-align:left;'+fontWeight+'"  href="'+ basePath+ 'logined/notify/view.jsp?notifyId='+ oObj.aData.notifyId+ '&columnId='+columnId+'&returnType=11">'+ subject+ '</a>';
				}
			}            
		},{
			"aTargets" : [1],
			"fnRender" : function(oObj) {
				var counting = oObj.aData.counting;
				
				if(counting == 0){
					return '<span style="color: red;">未阅读</span>';
				}else{
					return '<span>已阅读</span>';
				}
				
			}            
		}]
	});
}

