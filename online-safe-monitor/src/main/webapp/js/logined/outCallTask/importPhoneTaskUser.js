
//刷新页面
//var freshPage = false;
/**
 * 导入
 */
function importPhoneTaskUser() {
	var isshow=false;
//	var html = createImportUserHtml();
	art.dialog.open(basePath+"logined/outCallTask/artImport.jsp",{
		id : 'importUserArchives',
		title : '外呼对象导入',
//		content : html,
		width : 600,
		height : 300,
		opacity: 0.08,
		lock : true,
		close:function(){//关闭弹窗时执行的函数
		    	   if(isshow&&art.dialog.data("successList")!=null){
						callBack(art.dialog.data("successList"));
					}
		       },
		button : [
//		          {
//					name : '验 证',
//					callback : function() {
//						checkFileFormat();
//						return false;
//					}
//				}, 
				{
					name : '导 入',
					callback : function() {
						var iframe = this.iframe.contentWindow;
						isshow=true;
						iframe.startAjaxFileUpload();
						return false;
					},
					focus:true
				},
				{
					name : '取 消',
					callback : function() {
//						if (freshPage) {
//							location.reload(); // 重新加载
//						}
//						if(art.dialog.data("successList")!=null){
//							callBack(art.dialog.data("successList"));
//						}else{
//							
//						}
					}
				}]
	});

}

//导入成功后的返回方法
function callBack(data){
	var taskUserIds = $("#exportUserIds").val();
	var taskUserStr = "";
//	var countStr = $("#outCallPeopleCount").text();
	var count = 0;
	
	for(var i=0;i<data.length;i++){
		var temp = data[i];
		var taskUserId = temp.vid;
		var taskUserName = temp.userName;
		var taskUserPhone = temp.phone;
		//查询外呼对象中是否已经存在导入的内容
		var outCallPeopleNames = $("#outCallPeopleNames").html().toString();
		var tempStr1 = "<li>"+taskUserName+"("+taskUserPhone+")";
		var tempStr2 = "<LI>"+taskUserName+"("+taskUserPhone+")";
		if(outCallPeopleNames.indexOf(tempStr1)<0&&outCallPeopleNames.indexOf(tempStr2)<0){
			taskUserIds += taskUserId+",";
			if(taskUserPhone==null){
				taskUserPhone = "未知";
			}
			taskUserStr += "<li>"+taskUserName+"("+taskUserPhone+")<a class=\"x\" href=\"javascript:void(0);\" name=\"exportUser\" value=\""+taskUserId+"\" ><\/a><\/li>";
		}
		
//		count++;
	}
	if (taskUserIds == ",") {
		taskUserIds = "";
	}
	
	//alert(userIds);
	$("#outCallPeopleNames").append(taskUserStr);
	$("#exportUserIds").val(taskUserIds);
	
	count += $("a[name=crm]").length;
	count += $("a[name=exportUser]").length;
	count += $("a[name=taskUser]").length;
	if(count>0){
		hideError($("#crmUserIds")); 
	}
	
	$("#outCallPeopleCount").html(count);
}

/**
 * 验证格式
 */
function checkFileFormat() {
	var fileName = $("#fileToUpload").val();
	if (fileName == "") {
		$("#msg").html('请选择要验证的文件！');
		return false;
	}else{
		var rex = /.xls$/gi;
		if(!rex.test(fileName)){
			$("#msg").html('请选择电子表格！');
			return false;
		}
	}
	var radioType = 1;
	var url = basePath + 'phoneTask/checkBeforeImporting.action';
	$("#msg").html('');
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
					if (data == "") {
						$("#msg").html("验证通过,可以导入！");

					} else {
						$("#msg").html(data);
					}
				},
				error : function(data, status, e) {

					$("#msg").html("对不起！验证文件时出错！");
				}
			});
	return false;

}

/**
 * 开始上传
 * 
 * @return {Boolean}
 */
function startAjaxFileUpload() {
	var fileName = $("#fileToUpload").val();
	if (fileName == "") {
		$("#msg").html('请选择要导入的文件！');
		return false;
	}else{
		var rex = /.xls$/gi;
		if(!rex.test(fileName)){
			$("#msg").html('请选择电子表格！');
			return false;
		}
	}
	var radioType = $("input:radio[name='radioType']:checked").val();
	var url = basePath + 'phoneTask/importPhoneTaskUser.action';
	//alert(url);
	$("#msg").html('<span class="gray_9 ml20 mr10">正在导入</span><img src="'+basePath+'images/jindu.gif" />');
	$.ajaxFileUpload({
				url : url,
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'text', // 这里只能写成text，不能写成json。否则ajaxfileupload.js中103行会抛异常。不知道为什么。
				data : {
					uploadFileName : fileName
//					radioType : radioType
				},
				success : function(data, status) {
					//alert(data+"  "+status);
					var json = eval("("+data+")");
					$("#msg").html(json.result);
					art.dialog.data("successList",json.successList);
//					$("#page").attr("src",
//							basePath + 'logined/user/userList.jsp');
				},
				error : function(data, status, e) {
					//alert(data+"  "+status+"  "+e);
					$("#msg").html("对不起！导入文件时出错！");
				}
			});

	return false;

}

function createImportUserHtml() {
	var html = '<div><table width="400px" border="0" style="font-size:  12px; table-layout:auto;">';
	html += '<tr>';
	html += '  <th  style="width:100px;text-align:right;padding-right:5px"><span class="gray_9 ml20">选择导入文件</span></th>';
	html += '  <td><input type="file" name="fileToUpload" id="fileToUpload" style="width:260px; height:23px; line-height:23px;"/></td>';
	html += '</tr>';
	html += '<tr>';
	html += '	<th></th>';
	html += '	<td style="color:#999999;height:50px;">只支持<b> .xls </b>格式文件 &nbsp;&nbsp;&nbsp;';
	html += '      <font><a class="ml10" id="importModule" href="'
			+ basePath + 'down/phoneTaskUser.xls' + '">获取模板</a></font>';
	html += '   </td></tr>';
	html += '<tr><td colspan="2" align="center"><p id="msg" style="height:20px"></p></td></tr>';
	html += '</table></div>';
	return html;
}