$(document).ready(function() {
			var oTable;
			getList();
			// 保存按钮绑定事件
			$(".searchButton").bind("click", function() {
				getList();
			});
			
			$('#phone').keydown(function(e){
				if(e.keyCode==13){
					getList(); //处理事件
				}
			}); 
			
			//解决placeholder在ie中无法起作用的问题
			funPlaceholder(document.getElementById("phone"));
});

var freshPage = false;
var vid;
var aphone;//新增的号段值

//新增时只能输入数字
function checkNum(id) {
	var value=document.getElementById(id).value;
	
	document.getElementById(id).value=value.replace(/[^\d]/g,'') ;
}

/**
 * 新增号段
 */
function phoneAdd() {
	art.dialog.data("getList",getList);
	art.dialog.open(basePath+"logined/localsegment/addPhone.jsp",{
		id:'menu_phoneadd', 
		title:'新增号段', 
		width:600,
		height:300,
		lock : true,
		close : function (){
	    },
	    button : [{
					name : '确定',
					focus: true,//有了这个属性以后，这个按钮就有蓝色了。
					callback : function() {
						var iframe = this.iframe.contentWindow;
	        			iframe.addPhone();
	        			return false;
					}
	              },{
					name : '取消',
					callback : function() {
						return true;
							art.dialog().close();
						}
	              }],
	    opacity: 0.1// 透明度
   }); 
}
/**
 * 本地号码段维护(张东领添加)
 */
function impPhone(){
	art.dialog.open(basePath +"logined/localsegment/localWeihuImport.jsp",{
		id : 'importLocalPhone',
		title : '手机号码号段导入',
		width : 600,
		height : 300,
		opacity: 0.08,
		lock : true,
		button : [
//		          {
//					name : '验 证',
//					callback : function() {
//						var iframe = this.iframe.contentWindow;
//						iframe.checkFileFormat();
//						return false;
//					}
//				}, 
				{
					name : '导 入',
					callback : function() {
						var iframe = this.iframe.contentWindow;
						iframe.startAjaxFileUpload();
						return false;
					},
					focus:true
				},
				{
					name : '取 消',
					callback : function() {
						location.reload(); // 重新加载
					}
				}]
	});
}
/**
 * 导入手机号段
 */
//function impPhone() {
//	var html=createImpPhoneHtml();
//	art.dialog({
//		id : 'impPhone',
//		title : '手机号码号段导入',
//		content : html,
//		lock : true,
////		background: '#600', // 背景色
//	    opacity: 0.08,	// 透明度
//		button : [/*{
//					name : '验 证',
//					callback : function() {
//						//checkFileFormat();
//						return false;
//					}
//				}, */{
//					name : '导 入',
//					callback : function() {
//						startAjaxFileUpload();
//						return false;
//					},
//					focus:true
//				}, {
//					name : '取 消',
//					callback : function() {
//						if (freshPage) {
//							location.reload(); // 重新加载
//						}
//					}
//				}]
//	});
//}

/**
 * 手机号段导入中调用函数
 */
function createImpPhoneHtml() {
	var html = '<div><table width="400px" border="0" style="font-size:  12px; table-layout:auto;">';
	html += '<tr>';
	html += '  <th  style="width:100px;text-align:right;padding-right:5px">选择导入文件：</th>';
	html += '  <td><input type="file" name="fileToUpload" id="fileToUpload" style="width:260px; height:23px; line-height:23px;"/></td>';
	html += '</tr>';
	html += '<tr>';
	html += '	<th></th>';
	html += '	<td style="color:#999999;height:50px;">只支持<b> .xls </b>格式文件 &nbsp;&nbsp;&nbsp;';
	html += '      <font><a style="color:#f00;TEXT-DECORATION:none" id="importModule" href="'
			+ basePath + 'down/segmentList.xls' + '">获取模板？</a></font>';
	html += '   </td></tr>';
	html += '<tr><td colspan="2" align="center"><p id="msg" style="color: red;height:20px"></p></td></tr>';
	html += '</table></div>';
	return html;
}
/**
 * 开始上传手机号段文件
 * 
 * @return {Boolean}
 */
function startAjaxFileUpload() {
	var fileName = $("#fileToUpload").val();
	if (fileName == "") {
		$("#msg").html('请选择要验证的文件！');
		return false;
	}else{
		var patt = new RegExp(".xls$","gm");
		if(!patt.test(fileName)){
			$("#msg").html('请选择电子表格！');
			return false;
		}
	}
	var radioType = $("input:radio[name='radioType']:checked").val();
	var url = basePath + 'outcall/importPhone.action';
	$("#msg").html('正在导入，请稍候......');
	$.ajaxFileUpload({
				url : url,
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'text',
				data : {
					uploadFileName : fileName,
					radioType : radioType
				},
				success : function(data, status) {
					
					$("#msg").html(data);
					freshPage = true;
//					$("#page").attr("src",
//							basePath + 'logined/user/userList.jsp');
				},
				error : function(data, status, e) {
					$("#msg").html("对不起！导入文件时出错！");
				}
			});

	return false;

}

/**
 * 导出本地维护的号码段
 */
function expPhone() {
	var phone=$.trim($("#phone").val());
	//alert(phone);
	window.open(basePath + "outcall/exportPhone.action?phone="+phone);

}
/**
 * 删除号段号码
 */
function delPhone(vid){
	art.dialog.confirm('确定要删除该号码吗？这将不可恢复！', function() {
		$.ajax({
					url : basePath + "outcall/deletePhone.action?vId="+vid,
					type : "post",
					dataType : 'text',
					//data : vid,
					success : function(data) {
					//alert(data);
						if (data==1) {
//							art.dialog.alert('删除成功！');
							getList();
						} else {
							art.dialog.alert('删除失败！');
						}
					}
				});
	}, function() {
		return;
	});
	
}

/**
 * 查询日志获取列表
 */
function getList() {
	//alert(basePath);
	if (typeof oTable == 'undefined') {
		oTable = $('#Table').dataTable({
			"bProcessing" : true,//当正在处理数据的时候，是否显示“正在处理”这个提示信息
			'bServerSide' : true,//在服务器端整理数据
			"bStateSave" : false, // 状态保存
			"bDestroy" : true,
			'fnServerParams' : function(aoData) {
				aoData.push({
							"name" : "phone",
							"value" : $.trim($("#phone").val())
						});
			},
			"sAjaxSource" : basePath + "outcall/getHaoduanList.action",//指定要从哪个URL获取数据
			"sServerMethod" : "POST",
			"sPaginationType" : "full_numbers",//用于指定分页器风格:'full_numbers' or 'two_button'
			"bPaginate" : true, // 开关，是否显示（使用）分页器
			"bLengthChange" : false, // 改变每页显示数据数量.开关，是否显示一个每页长度的选择条（需要分页器支持）,在上方
			"bFilter" : false, // 过滤功能，开关，是否启用客户端过滤功能===========================
			"bSort" : false, // 排序功能，开关，是否让各列具有按列排序功能
			"bInfo" : true,// 页脚信息，开关，是否显示表格的一些信息
			"bAutoWidth" : false,// 自动宽度，是否自动计算表格各列宽度
			"iDisplayLength" : 15, // 每页显示多少行，用于指定一屏显示的条数，需开启分页器，默认为10
			"aoColumns" : [{  //给所有列设置属性,逐列设置
				"mDataProp" : "orderNum"
			}, {
				"mDataProp" : "phone",
				"sClass" : "data_l"
			}, {
				"mDataProp" : "addTime"
			}, {
				"mDataProp" : "action",
				"sClass":"right_bdr0"
			}],
			"oLanguage" : {
				"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
			},
			"fnDrawCallback" : function(oSettings) { //在每次table被draw完后调用，至于做什么就看着办吧
				$('#Table tbody  tr td').each(function() {
//							if($(this).text().length>7){
//								if($(this).text().indexOf("阅读人员 编辑") <= 0){
//									this.setAttribute('title', $(this).text()); 
//								}
//							}
						});
			},
			"fnInitComplete" : function() { //表格初始化完成后调用
				// 重置iframe高度
			},
			"aoColumnDefs" : [
			                  //{ //aoColumnDefs设置列的属性时，可以任意指定列，并且不需要给所有列都设置。
				/*"aTargets" : [1],
				"fnRender" : function(oObj) {
					var type = oObj.aData.type;//类型
					if (type == 1) {
						return '短信营销任务';
					} else if (type == 2) {
						return '电话营销任务';
					} else if (type == 3) {
						return '客户走访任务';
					} 
				}
			},*/
			{
				"aTargets" : [3],
				"fnRender" : function(oObj) {
					vid = oObj.aData.vid;
					//alert(vid);
					return '<a  href="javascript:void(0);" onclick="delPhone('+ vid + ');">删除</a>';
				}
			} ]
		});
	} else {
		var oSettings = oTable.fnSettings();
		oSettings._iDisplayStart = 0;
		oTable.fnClearTable();
	}
}

/**
 * 详情
 */
/*function orderView(vid){
	window.location.href = basePath + "order/findOrderView.action?vid="+vid;
}*/
/**
 * 删除
 * 
 * @return
 */
/*function orderCancel(vid) {
	art.dialog.confirm('确认要取消该条任务吗?', function() {
		$.ajax({
			url : basePath + "order/order_orderCancel.action?vid=" + vid,
			type : "post",
			dataType : "html",
			success : function(data){
				window.location.href = basePath + "logined/hbyx/order/monitorList.jsp";
			}
		});
	}, function() {

	});
}*/