
$(document).ready(function(){
	//alert("queueSeting ..");
	getDataTable();
});

function getDataTable(){	
	$('#myTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
//					{
//				"name" : "workorder.cclSn",
//				"value" :  cclSn
//			}
			);
		},
		"sAjaxSource" : basePath + "seatQueue/queue_list.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : false, // 翻页功能
		"bStateSave" : true, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : false,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
					"mDataProp" : "index" //序号
				}, {
					"mDataProp" : "serviceName", //队列说明1
					"sClass" : "data_l"
				}, {
					"mDataProp" : "userCount", //队列人数2
					"sClass" : "data_r"
					
				}, {
					"mDataProp" : "userInfoList", //队列成员3
					"sClass" : "data_l"
				}, {
					"mDataProp" : null, //操作4
					"sClass" : "right_bdr0"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#myTable tbody  tr td,#myTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				//nFoot.getElementsByTagName('th')[0].innerHTML = "Starting index is "+iStart;
				//alert("aData.length:  "+aData.length); // 打印该页显示多少行记录。
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
		"aoColumnDefs" : [{
			"aTargets" : [4], //操作4
			"fnRender" : function(oObj) {
				var vid = oObj.aData.vid;
				var html='<a href="'+basePath+'seatQueue/findById.action?vid='+vid+'">修改</a>';
				return html;
			}
		}]
	}); 
	
}

