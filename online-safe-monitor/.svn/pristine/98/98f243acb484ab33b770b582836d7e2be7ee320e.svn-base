
/**
 * 李立泼 
 * 创建时间：2014年01月07日
 * 修改时间：
 */
$(document).ready(function(){
});

function modifyRegion(event,obj){
	$(obj).removeAttr("onclick");
	//阻止事件冒泡
	if ($.browser.msie){
		event.cancelBubble = true ;
	}else{
		event.stopPropagation();
	}
	var name=$(obj).parent().parent().attr("name");
	var arr=getAttributeValue(name);
	id=arr[0];
	code=arr[1];
	subAddressName=arr[2];
	grade=arr[3];
	orderNum=arr[4];
	isRealName=arr[5];
	var title="";
	if(grade=="province"){
		title="修改省、自治区、直辖市";
	}else if(grade=="city"){
		title="修改地市";
	}else if(grade=="county"){
		title="修改县区";
	}else if(grade=="town"){
		title="修改乡镇";
	}else if(grade=="village"){
		title="修改行政村";
	}
	art.dialog.data("obj",obj);
	art.dialog.data("code",id);
	art.dialog.data("code",code);
	art.dialog.data("subAddressName",subAddressName);
	art.dialog.data("grade",grade);
	art.dialog.data("orderNum",orderNum);
	art.dialog.data("isRealName",isRealName);
	art.dialog.data("modifyAreaFun",modArea);
	art.dialog.open(basePath+"logined/area/modifyArea.jsp"
			,{
		id:"addArea",
		width:'600px',
		height:'300px',
		title: title,
		lock:true,
		opacity: 0.0, //设为0.0时，锁屏时屏幕不变黑
		button : [{
			name : '确定',
			focus: true,
			callback : function() {
				var iframe = this.iframe.contentWindow;
				iframe.addSubmit();
				return false;
			}
		}, {
			name : '取消',
			callback : function() {
				return true;
			}
		}],
	}
	);
	$(obj).attr("onclick","modifyRegion(event,this);");
}

function modArea(i,obj,id,code,areaName,grade,orderNum,isRealName){
	var paramData = {
			'i':i,
			'id':id,
			'code':code,
			'areaName':areaName,
			'grade':grade,
			'orderNum':orderNum,
			'isRealName':isRealName
		};
		$.ajax({
			url : basePath+"fiveGradeArea/modifyArea.action",
			type : "post",
			dataType :'text',
			data:paramData,
			success : function(data) {
				if(data == "0") {		//当返回0时，表示成功。
					relocadArea($(obj).parent().parent().parent().parent().get(0));
				} else {
					if(data == "修改失败。"){
						art.dialog.alert("修改失败。");
					}else{
						art.dialog.alert(data);
					}
				}
			}
		});
}

function deleteRegion(event,obj){
	$(obj).removeAttr("onclick");
	//阻止事件冒泡
	if ($.browser.msie){
		event.cancelBubble = true ;
	}else{
		event.stopPropagation();
	}
	//删除操作，当子级不为空时，不能删除的控制放在Action中。
	art.dialog.confirm('您确定要删除吗？',function(){
		//阻止事件冒泡
		if ($.browser.msie){
			event.cancelBubble = true ;
		}else{
			event.stopPropagation();
		}
		var arr=getAttributeValue($(obj).parent().parent().attr("name"));
		id=arr[0];
		code=arr[1];
		subAddressName=arr[2];
		grade=arr[3];
		orderNum=arr[4];
		isRealName=arr[5];
		
		var paramData = {
				'code':code,
				'areaName':subAddressName,
				'grade':grade,
				'orderNum':orderNum,
				'isRealName':isRealName
			};
			$.ajax({
				url : basePath+"fiveGradeArea/deleteArea.action",
				type : "post",
				dataType :'text',
				data:paramData,
				success : function(data) {
					if(data == "0") {		//当返回0时，表示成功。
						//window.location.reload();
						relocadArea($(obj).parent().parent().parent().parent().get(0));
					} else {
						if(data == "删除失败。"){
							art.dialog.alert("删除失败。");
						}else{
							art.dialog.alert(data);
						}
					}
				}
			});
	},function(){});
	$(obj).attr("onclick","deleteRegion(event,this);");
}

/**
 * 新增按钮的操作
 * @param obj <a class="add_ans fr" href="javascript:;" onclick="addArea(this);" name="city">新增</a>
 */
function addArea(obj){
	//防止二次点击
	$(obj).removeAttr("onclick");//  ?$("a").removeAttr("onclick");
	var grade = $(obj).attr("name");//obj.name;
	var code ="";//这里要传父级的选中的<li></li>的完整code
	if(grade=="city"){
		var liObj=$("#fiveGradeAreaContentProvince li[class='online']");//省级选中的<li></li>
		var arr=getAttributeValue($(liObj.get(0)).attr("name"));
		code=arr[1];
		title="新增地市";
	}else if(grade=="county"){
		var ts=false;
		var liObj;
		if($("#fiveGradeAreaContentCity").has("li[class='online']").length>0){//地市级有选中的，是添加一般的县
			//alert("添加一般的县");
			liObj=$("#fiveGradeAreaContentCity li[class='online']");//地市级选中的<li></li>
		}else{//地市级没有选中的，添加一般的是省直辖县
			//alert("添加直辖市所辖的区县、省直辖县");
			ts=true;
			liObj=$("#fiveGradeAreaContentProvince li[class='online']");//省级选中的<li></li>
		}
		var arr=getAttributeValue($(liObj.get(0)).attr("name"));
		code=arr[1];
		if(ts){
			if(code=="11" || code=="12" || code=="31" || code=="50"){
				code +="01";
			}else{
				code +="90";
			}
		}
		title="新增县区";
	}else if(grade=="town"){
		var liObj=$("#fiveGradeAreaContentCounty li[class='online']");//选中的<li></li>
		var arr=getAttributeValue($(liObj.get(0)).attr("name"));
		code=arr[1];
		title="新增乡镇";
	}else if(grade=="village"){
		var liObj=$("#fiveGradeAreaContentTown li[class='online']");//选中的<li></li>
		var arr=getAttributeValue($(liObj.get(0)).attr("name"));
		code=arr[1];
		title="新增行政村";
	}else{
		
	}
	/* 添加-开始（参数：code：父级code； grade：将要添加到的级别） */
	art.dialog.data("code",code);
	art.dialog.data("obj",obj);
	art.dialog.data("grade",grade);
	art.dialog.data("newAreaFun",newArea);
	art.dialog.open(basePath+"logined/area/addArea.jsp"
			,{
		id:"addArea",
		width:'600px',
		height:'300px',
		title: title,
		lock:true,
		opacity: 0.0, //设为0.0时，锁屏时屏幕不变黑
		button : [{
			name : '确定',
			focus: true,
			callback : function() {
				var iframe = this.iframe.contentWindow;//得到初始页面
				iframe.addSubmit();
				return false;
			}
		}, {
			name : '取消',
			callback : function() {
				return true;
			}
		}],
	}
	);
	/* 添加-结束 */
	$(obj).attr("onclick","addArea(this);");
}

function newArea(obj,code,areaName,grade,orderNum,isRealName){
	var paramData = {
		'code':code,
		'areaName':areaName,
		'grade':grade,
		'orderNum':orderNum,
		'isRealName':isRealName
	};
	$.ajax({
		url : basePath+"fiveGradeArea/addNewArea.action",
		type : "post",
		dataType :'text',
		data:paramData,
		success : function(data) {
			if(data == "0") {		//当返回0时，表示成功。
				relocadArea($(obj).parent().parent().get(0));
			} else {
				if(data == "添加失败。"){
					art.dialog.alert("添加失败。");
				}else{
					art.dialog.alert(data);
				}
			}
		}
	});
}

/**
 * 局部刷新
 * @param obj DOM对象
 */
function relocadArea(obj){
	var grade=$(obj).find("ul").attr("id");
	if(grade=='fiveGradeAreaContentProvince'){
		window.location.reload();
	}else if(grade=='fiveGradeAreaContentCity'){
		var liObj=$("#fiveGradeAreaContentProvince li[class='online']");//省级选中的<li></li>
		liObj.click();
	}else if(grade=='fiveGradeAreaContentCounty'){
		var liObj;
		if($("#fiveGradeAreaContentCity").has("li[class='online']").length>0){//地市级有选中的，是添加一般的县
			//alert("添加一般的县");
			liObj=$("#fiveGradeAreaContentCity li[class='online']");//地市级选中的<li></li>
		}else{//地市级没有选中的，添加一般的是省直辖县
			//alert("添加直辖市所辖的区县、省直辖县");
			liObj=$("#fiveGradeAreaContentProvince li[class='online']");//省级选中的<li></li>
		}
		liObj.click();
	}else if(grade=='fiveGradeAreaContentTown'){
		var liObj=$("#fiveGradeAreaContentCounty li[class='online']");//选中的<li></li>
		liObj.click();
	}else if(grade=='fiveGradeAreaContentVillage'){
		var liObj=$("#fiveGradeAreaContentTown li[class='online']");//选中的<li></li>
		liObj.click();
	}
	
}