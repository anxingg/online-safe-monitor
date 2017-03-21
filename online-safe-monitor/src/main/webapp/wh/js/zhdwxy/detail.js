$(document).ready(function(){
	
	//标签页切换
	$(".tabBox li").click(function(){
		var index_tab = $(this).parent().children().index($(this));
		$(this).parent().find(".on").removeClass("on"); 
		$(this).addClass("on");
		var content_obj = $(this).parent().parent().parent().find(".tabContent");
		content_obj.hide();
	    content_obj.eq(index_tab).show(); 
	    
	});
	
	//查询按钮绑定事件
	$(".searchButton").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_queryDetail.action');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_queryDetail.action');
			getTableList();
			return false;
		}
	});
	
	getTableList();
});


/**
 * 加载列表
 */
function getTableList(){
	//
	var whroletype = $("#whroletype").val();
	
	//部门ID
	var groupId = $("#group_id").val();
	
	//危化品名称
	var dangerGoodName = $.trim( $('#dangerGoodName').val() );
	
	//危险性类别
	var dictId = $('#dictId').val();
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "dangerSourcesGood.groupId",//部门ID
				"value" : groupId
			}, {
				"name" : "dangerSourcesGood.dangerId",//关联的重大危险源ID
				"value" : _vid
			}, {
				"name" : "dangerSourcesGood.dangerGoodName",
				"value" : dangerGoodName
			}, {
				"name" : "dangerSourcesGood.dictId",
				"value" : dictId
			}, {
				"name" : "dangerSourcesGood.whroletype",
				"value" : whroletype
			});
		},
		"sAjaxSource" : basePath + "dangersourcesgood/dangerSourcesGood_List_list.action",
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
			"mDataProp" : "dangerGoodName",
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'dangerObjType'
		}, {
			"mDataProp" : 'unCode',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'purpose',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'process',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'physicalState',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : null
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {

			 $('#myTable tbody tr td').each(function() {
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
					//var whroletype = $("#whroletype").val();
					var vid = oObj.aData.vid;
					var html = '<a href="javascript:addOne('+vid+', 4);">查看</a>';
					/*
					//企业端可见
					if(whroletype == 2){
						html += '<a href="javascript:addOne('+vid+', 2);">修改</a>';
						html += '<a href="javascript:deleteOne('+vid+');">删除</a>';
					}
					*/
					return html;
				}
			}
		]
	});
}

/**
 * 新增或修改一个重大危险源危化品目录对象
 * @param vid
 */
function addOne(vid, operation){
	if(!vid){
		vid = '';
	}
	if(!operation){
		operation = 1;
	}
	var title = '新增危化品信息';
	if(operation == 2){
		title = '修改危化品信息';
	}
	if(operation == 4){
		title = '查看危化品信息';
	}
	
	var url = basePathFull+'dangersourcesgood/dangerSourcesGoodToPage_findOne_addDSG.action?infoType=dangerObjType&dangerSourcesGood.vid='+vid+'&operation='+operation;
	
	art.dialog.data("addOneSubmit", addOneSubmit);
	art.dialog.open(url, {
	    id : "addDSG",
	    title : title,
	    width : 890,
	    height : 455,
	    lock : true,
	    //drag:false,
	    opacity: 0.08,// 透明度
//	    close : function(){
//	    	set(_infoType,_sysTypeId,_value) ;
//	    	return true;
//	    },
//	    ok : function(){
//	    	var iframe = this.iframe.contentWindow;
//	    	iframe.save();
//	    	return closenow;
//	    },
	    cancel : function(){
	    	return true;
	    }
	});
}

/**
 * addOne方法的子方法
 * 
 * @param vid
 * @param dangerGoodName
 * @param dictId
 * @param unCode
 * @param purpose
 * @param process
 * @param physicalState
 * @param operationTemp
 * @param operationPressure
 * @param simpleStock
 * @param unitStock
 * @param criticalMass
 */
function addOneSubmit(vid, dangerGoodName, dictId, unCode, purpose, 
		process, physicalState, operationTemp, operationPressure, 
		simpleStock, unitStock, criticalMass) {
	return;
}

///**
// * 删除操作
// */
//function deleteOne(id) {
//	artDialog.confirm("你确定要删除吗？", function(){
//		$.ajax({
//			url : basePathFull + "dangersourcesgood/dangerSourcesGood_Load_deleteOne.action",
//			type : "post",
//			dataType : 'text',
//			data : {
//				'dangerSourcesGood.vid' : id
//			},
//			success : function(data) {
//				if(data == 1){
//					getTableList();
//				}
//			}
//			
//		});
//	});
//}