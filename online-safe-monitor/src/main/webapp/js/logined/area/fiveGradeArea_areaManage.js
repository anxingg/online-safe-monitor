
/**
 * 李立泼 
 * 创建时间：2014年01月06日
 * 修改时间：
 */
var completeAddressName=[
{"id":0,"code":"","province":"请选择","grade":"province","orderNum":"","isRealName":""},
{"id":0,"code":"","city":"","grade":"city","orderNum":"","isRealName":""},
{"id":0,"code":"","county":"","grade":"county","orderNum":"","isRealName":""},
{"id":0,"code":"","town":"","grade":"town","orderNum":"","isRealName":""},
{"id":0,"code":"","village":"","grade":"village","orderNum":"","isRealName":""}
];
var fullOfAddressName=false;
$(document).ready(function(){
	
	loadProvince();

});

function loadProvince(){
	var code=completeAddressName[0].code;
	var grade=completeAddressName[0].grade;
	
	var dataParam = {
			'code' : code,
			'grade' : grade
		};
		$.ajax({
		    type : 'post',
		    url : basePath + "fiveGradeArea/getAddress.action",
		    data : dataParam,
		    dataType : 'text',
		    success : function(data) {
		    	jsonData = eval('('+data+')');
		    	for(var i=0;i<jsonData.length;i++){
		    		var id=jsonData[i].id;
		    		var code=jsonData[i].code;
		    		var grade=jsonData[i].grade;
		    		var orderNum=jsonData[i].orderNum;
		    		var isRealName=jsonData[i].isRealName;
		    		var regionName=null;
			    	if(grade=="province"){
			    		regionName=jsonData[i].province;
			    	}else if(grade=="city"){
			    		regionName=jsonData[i].city;
			    	}else if(grade=="county"){
			    		regionName=jsonData[i].county;
			    	}else if(grade=="town"){
			    		regionName=jsonData[i].town;
			    	}else if(grade=="village"){
			    		regionName=jsonData[i].village;
			    	}
			    	var subRegionName = "";
			    	if(regionName.length>7){
			    		subRegionName = regionName.substring(0,7)+"...";
			    	}else{
			    		subRegionName = regionName;
			    	}
			    	
		    		var htmlContent ='<li title="'+regionName+'" name="'+id+','+code+','+regionName+','+grade+','+orderNum+','+isRealName+'" onclick="selectAddress(this);"><span><a href="javascript:;" class="shezhiicon" onclick="modifyRegion(event,this);"></a><a href="javascript:;" class="delecticon" onclick="deleteRegion(event,this);"></a></span>'+subRegionName+'</li>';
		    		$("#fiveGradeAreaContentProvince").append(htmlContent);
		    	}
		    }
		});
}

/**
 * 保存地址对象的各个值
 * @param id
 * @param code
 * @param subAddressName
 * @param grade
 * @param orderNum
 * @param isRealName
 */
function saveCompleteAddressName(id,code,subAddressName,grade,orderNum,isRealName){
	if(grade=="province"){
		completeAddressName[0].id=id;
		completeAddressName[0].code=code;
		completeAddressName[0].province=subAddressName;
		completeAddressName[0].orderNum=orderNum;
		completeAddressName[0].isRealName=isRealName;
	}else if(grade=="city"){
		completeAddressName[1].id=id;
		completeAddressName[1].code=code;
		completeAddressName[1].city=subAddressName;
		completeAddressName[1].orderNum=orderNum;
		completeAddressName[1].isRealName=isRealName;
	}else if(grade=="county"){
		completeAddressName[2].id=id;
		completeAddressName[2].code=code;
		completeAddressName[2].county=subAddressName;
		completeAddressName[2].orderNum=orderNum;
		completeAddressName[2].isRealName=isRealName;
	}else if(grade=="town"){
		completeAddressName[3].id=id;
		completeAddressName[3].code=code;
		completeAddressName[3].town=subAddressName;
		completeAddressName[3].orderNum=orderNum;
		completeAddressName[3].isRealName=isRealName;
	}else{
		completeAddressName[4].id=id;
		completeAddressName[4].code=code;
		completeAddressName[4].village=subAddressName;
		completeAddressName[4].orderNum=orderNum;
		completeAddressName[4].isRealName=isRealName;
	}
}

/**
 * 点击label标签的动作
 * @param thisObj
 */
function selectGradeLabel(thisObj){
	var grade=$(thisObj).attr("name");//参数据，当前操作的label标签的级别
	
	$("#fiveGradeAreaUl li").removeClass("curr"); //0被点击的label标签的其他同辈的label标签去掉选中属性
	$("#fiveGradeAreaUl li a").removeClass("hover"); //0被点击的label标签的其他同辈的label标签去掉选中属性
	$(thisObj).parent("li").addClass("curr"); //1被点击的label标签添加选中属性
	$(thisObj).parent("a").addClass("hover"); //1被点击的label标签添加选中属性
	$("div[class='mc']").hide();//2所有的存储区显示不显示
	if(grade=="province"){ //3被点击的label标签对应的存储区显示
		$("#fiveGradeAreaContentProvince").parent().show();
	}else if(grade=="city"){
		$("#fiveGradeAreaContentCity").parent().show();
	}else if(grade=="county"){
		$("#fiveGradeAreaContentCounty").parent().show();
	}else if(grade=="town"){
		$("#fiveGradeAreaContentTown").parent().show();
	}else{
		$("#fiveGradeAreaContentVillage").parent().show();
	}
}

function getAttributeValue(attributeValue){
	var arr=new Array();
	arr=attributeValue.split(",");
	return arr;
}

/**
 * 点击地址名的操作
 * @param obj <li name="'+id+','+code+','+regionName+','+grade+','+orderNum+','+isRealName+'" onclick="selectAddress(this);"><span><a href="javascript:;" class="shezhiicon" onclick="modifyRegion(this);"></a><a href="javascript:;" class="delecticon" onclick="deleteRegion(this);"></a></span>'+regionName+'</li>
 */
function selectAddress(obj){
	$(obj).removeAttr("onclick");
	$(obj).parent().find("li").removeClass("online");
	$(obj).addClass("online");
	var arr=getAttributeValue($(obj).attr("name"));
	id=arr[0];
	code=arr[1];
	subAddressName=arr[2];
	grade=arr[3];
	orderNum=arr[4];
	isRealName=arr[5];
	saveCompleteAddressName(id,code,subAddressName,grade,orderNum,isRealName);//0把点击的地址存入地址对象
	if(grade=="province"){ //5被点击的地址所在的存储区的同辈后面的存储区清空
		$("#fiveGradeAreaContentCity").children("li").remove();
		$("#fiveGradeAreaContentCity").prev().find("a").attr("style","display:none;");
		$("#fiveGradeAreaContentCounty").children("li").remove();
		$("#fiveGradeAreaContentCounty").prev().find("a").attr("style","display:none;");
		$("#fiveGradeAreaContentTown").children("li").remove();
		$("#fiveGradeAreaContentTown").prev().find("a").attr("style","display:none;");
		$("#fiveGradeAreaContentVillage").children("li").remove();
		$("#fiveGradeAreaContentVillage").prev().find("a").attr("style","display:none;");
	}else if(grade=="city"){
		$("#fiveGradeAreaContentCounty").children("li").remove();
		$("#fiveGradeAreaContentCounty").prev().find("a").attr("style","display:none;");
		$("#fiveGradeAreaContentTown").children("li").remove();
		$("#fiveGradeAreaContentTown").prev().find("a").attr("style","display:none;");
		$("#fiveGradeAreaContentVillage").children("li").remove();
		$("#fiveGradeAreaContentVillage").prev().find("a").attr("style","display:none;");
	}else if(grade=="county"){
		$("#fiveGradeAreaContentTown").children("li").remove();
		$("#fiveGradeAreaContentTown").prev().find("a").attr("style","display:none;");
		$("#fiveGradeAreaContentVillage").children("li").remove();
		$("#fiveGradeAreaContentVillage").prev().find("a").attr("style","display:none;");
	}else if(grade=="town"){
		$("#fiveGradeAreaContentVillage").children("li").remove();
		$("#fiveGradeAreaContentVillage").prev().find("a").attr("style","display:none;");
	}else{
		
	}
	//***********************
	if(grade=="province"){ //9
		if(code=="11" || code=="12" || code=="31" || code=="50"){
			
		}else{
			$("#fiveGradeAreaContentCity").prev().find("a").attr("style","display:block;");
		}
	}else if(grade=="city"){
		$("#fiveGradeAreaContentCounty").prev().find("a").attr("style","display:block;");
	}else if(grade=="county"){
		$("#fiveGradeAreaContentTown").prev().find("a").attr("style","display:block;");
	}else if(grade=="town"){
		$("#fiveGradeAreaContentVillage").prev().find("a").attr("style","display:block;");
	}else{
		
	}
	
	//***********************
	
	/*
	 * （下面三条在ajax中写了）
	 * 6、在被点击的地址所在的label标签后添加新的同辈label标签
	 * 7、新label标签对应的存储区加载数据
	 * 8、新label标签对应的存储区显示
	 */
	
	
	var dataParam = {
			'code' : code,
			'grade' : grade
		};
		$.ajax({
		    type : 'post',
		    url : basePath + "fiveGradeArea/getAddress.action",
		    data : dataParam,
		    dataType : 'text',
		    success : function(data) {
		    	jsonData = eval('('+data+')');
		    	for(var i=0;i<jsonData.length;i++){
		    		var id=jsonData[i].id;
		    		var code=jsonData[i].code;
		    		var grade=jsonData[i].grade;
		    		var orderNum=jsonData[i].orderNum;
		    		var isRealName=jsonData[i].isRealName;
		    		var regionName;
		    		var subRegionName = "";
			    	if(grade=="province"){
			    		regionName=jsonData[i].province;
			    	}else if(grade=="city"){
			    		regionName=jsonData[i].city;
				    	if(regionName.length>7){
				    		subRegionName = regionName.substring(0,7)+"...";
				    	}else{
				    		subRegionName = regionName;
				    	}
			    		var htmlContent ='<li title="'+regionName+'" name="'+id+','+code+','+regionName+','+grade+','+orderNum+','+isRealName+'" onclick="selectAddress(this);"><span><a href="javascript:;" class="shezhiicon" onclick="modifyRegion(event,this);"></a><a href="javascript:;" class="delecticon" onclick="deleteRegion(event,this);"></a></span>'+subRegionName+'</li>';
			    		$("#fiveGradeAreaContentCity").append(htmlContent);
			    		$("#fiveGradeAreaContentCity").prev().find("a").attr("style","display:block;");
			    	}else if(grade=="county"){
			    		regionName=jsonData[i].county;
			    		if(regionName.length>7){
				    		subRegionName = regionName.substring(0,7)+"...";
				    	}else{
				    		subRegionName = regionName;
				    	}
			    		var htmlContent ='<li title="'+regionName+'" name="'+id+','+code+','+regionName+','+grade+','+orderNum+','+isRealName+'" onclick="selectAddress(this);"><span><a href="javascript:;" class="shezhiicon" onclick="modifyRegion(event,this);"></a><a href="javascript:;" class="delecticon" onclick="deleteRegion(event,this);"></a></span>'+subRegionName+'</li>';
			    		$("#fiveGradeAreaContentCounty").append(htmlContent);
			    		$("#fiveGradeAreaContentCounty").prev().find("a").attr("style","display:block;");
			    	}else if(grade=="town"){
			    		regionName=jsonData[i].town;
			    		if(regionName.length>7){
				    		subRegionName = regionName.substring(0,7)+"...";
				    	}else{
				    		subRegionName = regionName;
				    	}
			    		var htmlContent ='<li title="'+regionName+'" name="'+id+','+code+','+regionName+','+grade+','+orderNum+','+isRealName+'" onclick="selectAddress(this);"><span><a href="javascript:;" class="shezhiicon" onclick="modifyRegion(event,this);"></a><a href="javascript:;" class="delecticon" onclick="deleteRegion(event,this);"></a></span>'+subRegionName+'</li>';
			    		$("#fiveGradeAreaContentTown").append(htmlContent);
			    		$("#fiveGradeAreaContentTown").prev().find("a").attr("style","display:block;");
			    	}else if(grade=="village"){
			    		regionName=jsonData[i].village;
			    		if(regionName.length>7){
				    		subRegionName = regionName.substring(0,7)+"...";
				    	}else{
				    		subRegionName = regionName;
				    	}
			    		var htmlContent ='<li title="'+regionName+'" name="'+id+','+code+','+regionName+','+grade+','+orderNum+','+isRealName+'" onclick="selectAddress(this);"><span><a href="javascript:;" class="shezhiicon" onclick="modifyRegion(event,this);"></a><a href="javascript:;" class="delecticon" onclick="deleteRegion(event,this);"></a></span>'+subRegionName+'</li>';
			    		$("#fiveGradeAreaContentVillage").append(htmlContent);
			    		$("#fiveGradeAreaContentVillage").prev().find("a").attr("style","display:block;");
			    	}
		    	}
		    }
		});

		/* 添加-结束 */
		$(obj).attr("onclick","selectAddress(this);");
}