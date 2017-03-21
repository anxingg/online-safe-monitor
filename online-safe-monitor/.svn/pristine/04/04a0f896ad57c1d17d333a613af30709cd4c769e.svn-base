var hasExercise;
var hasExam;
var answerArr = [];
//var answerArr = new Array();
var orderLevelVar;
function Question(){
	this.title = "",
	this.questionType ="",
	this.score="",
	this.rightAnswer="",
	this.istest="",
	this.isexam="",
	this.unitId="",
	this.orderLevel="",
	this.courseId="",
	this.itemList=[]
}

$(function(){
	$("input:radio[name='questionItem']").each(function(){
		answerArr = [];
//		answerArr = new Array();
		var obj = $(this);
		obj.click(function(){
			if(obj.val()!=2){
				$("#update").show();
				$("#add").show();
				$("#answerQuestion").hide();
				if(obj.prop("checked")){
					if(!$("#add").is(":hidden")){
						$("#add").find(".oper").show();
						$("#add").find(".oper").each(function(){
							$(this).removeAttr("name").removeClass("oper on").addClass("oper").text("[设为正确答案]");
						});
					}else{
						$("#update").find(".oper").show();
						$("#update").find(".oper").each(function(){
							$(this).removeAttr("name").removeClass("oper on").addClass("oper").text("[设为正确答案]");
						});
					}
				}
			}else{
				$("#answerQuestion").show();
				$("#update").hide();
				$("#add").hide();
			}
		});
	});
	
	
	initQuestion();
	setQuestionType();
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

//初始化试题
function initQuestion(){
	var questionId = $("#questionId").val();
	var url = basePath+"exam/findQuestion.action";
	var dataParam = {"questionId":questionId};
	$.ajax({
		url:url,
		type:'POST',
		data:dataParam,
		dataType:'json',
		success:function(data){
			var updateHtml = "";
			if(data != null && data != "{}"){
				var questionTitle = data.questionTitle;
				var questionType = data.questionType;
				var rightAnswer = data.rightAnswer;
				var isTest = data.isTest;
				var isExam = data.isExam;
				var score = data.score;
				var itemList = data.itemList;
				var titleType = data.titleType;
				var orderLevel = data.orderLevel;
				var itemSize = itemList.length;
				updateHtml += ' <dl class="enter" id="update">';
				
				var oldAnswerArr = rightAnswer.split("-");
				//answerArr = oldAnswerArr;
				for(var i = 0; i< oldAnswerArr.length; i++){
					answerArr.push(oldAnswerArr[i]);
				}
				for(var i = 0;i < itemSize;i++){
					var item = itemList[i];
					var itemNo = item.itemCode;
					var itemName = item.itemName;
					if(checkIsAll(oldAnswerArr,itemNo)){
						if(i > 1){
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)" ></span><span class="oper on" name="isAnswer"  onclick="setAnswer(this)" value="'+itemNo+'">[正确答案]</span></dd>';
						}else{
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="oper on" name="isAnswer"  onclick="setAnswer(this)" value="'+itemNo+'">[正确答案]</span></dd>';
						}
					}else{
						if(i > 1){
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)" ></span><span class="oper"  onclick="setAnswer(this)" value="'+itemNo+'">[设置正确答案]</span></dd>';
						}else{
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="oper"  onclick="setAnswer(this)" value="'+itemNo+'">[设置正确答案]</span></dd>';
						}
					}
				}
				updateHtml += '<dd class="operate" id="operateUpdate"><em id="addInsertItem" onclick="insertItem(\'update\',this)">+插入选项</em></dd>';
				updateHtml+="</dl>"
				$("input:radio[name='questionItem'][value='"+questionType+"']").attr("checked","checked");
				$("#grade").val(score);
				$("#examType option[value='"+titleType+"']").attr("selected","selected");
				$("#title").val(questionTitle);
				
			}
			$("#add").remove();
			$("#update").remove();
			$("#tabInfo").after(updateHtml);
			$("#addQuestion").hide();
			$("#updateQuestion").show();
		}
	});

}



function closeItem(obj){
	var dl = $(obj).parent("dd").parent("dl");
	$(obj).parent("dd").nextAll("dd").each(function(){
			var span = $(this).children("label").children("span");
			var num = span.text();
			span.text(parseInt(num)-1);
		});
	$(obj).parent("dd").remove();
	if(answerArr){
		var tempItemNo = $(obj).siblings("span").attr("value");
		if(checkIsAll(answerArr,tempItemNo)){
			answerArr.splice($.inArray(parseInt(tempItemNo),answerArr),1);
		}
	}
	
}
//插入选项
function insertItem(operType,obj){
	if(operType=="add"){
		var dd = $(obj).parent("dd").prev();
		var newNo = parseInt(dd.children("label").children("span").text())+1;
		var newItemHtml = "";
		if(isSelectedQuestionType()){
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item"'+newNo+' valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}else{
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item"'+newNo+' valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" style="display: none" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}
		dd.after(newItemHtml);
	}
	if(operType=="update"){
		var dd = $(obj).parent(".operate").prev();
		var newNo = parseInt(dd.children("label").children("span").text())+1;
		var newItemHtml = "";
		if(isSelectedQuestionType()){
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="updateItem"'+newNo+' valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}else{
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="updateItem"'+newNo+' valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" style="display: none" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}
		dd.after(newItemHtml);
	}
}

//是否选中试题类型
function isSelectedQuestionType(){
	var flag = false;
	$("input:radio[name='questionItem']").each(function(){
		if($(this).prop("checked")){
			flag = true;
		}
	});
	return flag;
}

function setAnswer(obj){
	var text = $(obj).text();
	var rightAnswerNum = 0;
	var itemNo = parseInt($(obj).attr("value"));
	if(text.indexOf("设")>-1){
		if($("#add").html()!=undefined && !$("#add").is(":hidden")){//添加
			rightAnswerNum = $("#add").find("span[name='isAnswer']").length;
			if(judgeQuestionType()=="one"){
				if(rightAnswerNum > 0){
					$("#add").find("span[name='isAnswer']").each(function(){
						$(this).removeAttr("name").removeClass("oper on").addClass("oper").text("[设为正确答案]");
						var tempItemNo = $(this).attr("value");
						if(checkIsAll(answerArr,tempItemNo)){
							answerArr.splice($.inArray(tempItemNo,answerArr),1);
						}
					});
				}
			}
		}else{//修改
			var rightAnswerNum = 0;
				rightAnswerNum = $("#update").find("span[name='isAnswer']").length;
				if(judgeQuestionType()=="one"){
					if(rightAnswerNum > 0){
						$("#update").find("span[name='isAnswer']").each(function(){
							$(this).removeAttr("name").removeClass("oper on").addClass("oper").text("[设为正确答案]");
							var tempItemNo = $(this).attr("value");
							if(checkIsAll(answerArr,tempItemNo)){
								answerArr.splice($.inArray(tempItemNo,answerArr),1);
							}
						});
					}
				}
		}
		$(obj).removeClass("oper").addClass("oper on").attr("name","isAnswer").text("[正确答案]");
		if(!checkIsAll(answerArr,itemNo)){
			answerArr.push(itemNo);
		}
	}else{
		if($("#add").html()!=undefined && !$("#add").is(":hidden")){//添加
			rightAnswerNum = $("#add").find("span[name='isAnswer']").length;
			if(judgeQuestionType()=="many"){
				if(rightAnswerNum <= 2){
					art.dialog.alert("多选题至少要设置两个正确项!");
					return;
				}
			}
		}else{//修改
				rightAnswerNum = $("#update").find("span[name='isAnswer']").length;
				if(judgeQuestionType()=="many"){
					if(rightAnswerNum <= 2){
						art.dialog.alert("多选题至少要设置两个正确项!");
						return;
					}
				}
		}
		
		$(obj).removeClass("oper on").addClass("oper").removeAttr("name").text("[设为正确答案]");
		if(answerArr.length>0){
			answerArr.splice(inArray(itemNo,answerArr),1);
		}
	}
}

/**
 * 返回数组下标
 * @param itemNo
 * @param answerArr
 * @returns {Number} int 下标
 */
function inArray(itemNo, answerArr){
	var result = 0;
	for(var i = 0; i<answerArr.length; i++){
		if(answerArr[i] == itemNo){
			result = i;
		}
	}
	return result;
}

function checkIsAll(arr,value){
    for(var i = 0;i < arr.length;i++){
        if(value==arr[i]){
            return true;//如果有一个不存在就返回false
        }
    }
    return false;
}

//判断是否单选
function judgeQuestionType(){
	var radioOne = $("input:radio[value='0']").prop("checked");
	if(radioOne){
		return "one";
	}
	return "many";
}


function addQuestion(operType){


	if(!validator(document.getElementById("form1"))){
		return;
	}
	if(operType == "add"){
		var title = $.trim($("#title").val());
		if(title!=null){
			art.dialog.alert("请输入试题名称");
			return;
		}
		//判断提干是否相同
		$(".choice").find("dl").each(function(){
			var tempTitle = $(this).find("dt").find("#tigan").text();
			if(title==tempTitle){
				art.dialog.alert("试题名称不能相同!");
				return;
			}
		});
	}else{
		//判断提干是否相同
		$(".choice").find("dl").each(function(){
			var tempTitle = $(this).find("dt").find("#tigan").text();
			var orderLevel3 = $(this).find("dt").find("span").eq(0).text();
			if(title==tempTitle && orderLevel3 != orderLevel2){
				art.dialog.alert("试题名称不能相同!");
				return;
			}
		});
	}



if(!isSelectedQuestionType()){
	art.dialog.alert("请选择试题类型!");
	return;
}
var rightItemNum;
if(judgeQuestionType()!="other"){ //问答题特殊处理
	if(operType == "add"){
		rightItemNum = $("#add").find("span[name='isAnswer']").length;
	}else{
		rightItemNum = $("#update").find("span[name='isAnswer']").length;
	}
	//取出正确答案数量
	//判断是否设为正确答案
	if(judgeQuestionType()=="one"){
		if(rightItemNum==0){
			art.dialog.alert("请为此题设置一个正确项!");
			return;
		}
		if(rightItemNum > 1){
			art.dialog.alert("单选题只能设置一个正确项!");
			return;
		}
	}
	
	if(judgeQuestionType()=="many"){
		if(rightItemNum==0){
			art.dialog.alert("请为此题设置正确项!");
			return;
		}
		if(rightItemNum==1){
			art.dialog.alert("多选题至少要设置两个正确项!");
			return;
		}
	}
}

var grade = $("#grade").val();
	if(grade == null || grade == ""){
		art.dialog.alert("请输入考题分值!");
		return;
	}

var itemList = [];
var questionTitle = "";
if(judgeQuestionType()!="other"){
	questionTitle = $.trim($("#title").val());
}else{
	questionTitle = otherTitle;
}
var questionId = $("#questionId").val();
var itemIsSame = false;
if(judgeQuestionType()!="other"){
	if(operType=="add"){
		//获得该题的所有选项
		$("#add").find("dd input").each(function(){
			var item = new Item();
			var itemNo = $(this).prev().children("span").text();
			var itemValue = $(this).val();
			item.itemCode = itemNo;
			item.itemName = itemValue;
			item.isDelete = 0;
			if(itemList.length > 0){
				for(var i =0;i < itemList.length;i++){
					var tempItem = itemList[i];
					if(tempItem.itemName==itemValue){
						itemIsSame = true;
						break;
					}
				}
			}
			itemList.push(item);
		});
	}else{
		//获得该题的所有选项
		$("#update").find("dd input").each(function(){
			var item = new Item();
			var itemNo = $(this).prev().children("span").text();
			var itemValue = $(this).val();
			item.itemCode = itemNo;
			item.itemName = itemValue;
			item.isDelete = 0;
			if(itemList.length > 0){
				for(var i =0;i < itemList.length;i++){
					var tempItem = itemList[i];
					if(tempItem.itemName==itemValue){
						itemIsSame = true;
						break;
					}
				}
			}
			itemList.push(item);
		});
	}
	if(itemIsSame){
		art.dialog.alert("选项内容不能相同!");
		return;
	}
}

var rightItem = answerArr.join('-');
var questionType = $("input:radio[name='questionItem']:checked").val();
var courseUnitId = $("#courseUnit").val();
var isTest=0;
var isExam=0;
var score = 0;


	score = $("#grade").val();
	if(score<= 0){
		art.dialog.alert("分值必须为正整数!");
		return;

	
}
var courseId = $("#courseId").val();
//取出排序号
var titleType = $("#examType").val();
if(titleType==""){
	art.dialog.alert("请选择试题类型!");
	return;
}
var orderLevel = parseInt($(".choice").find("dl").length)+1;
var url = basePath+"exam/addQuestion.action";
var dataParam = {
		"question.title":questionTitle,
		"question.questionType":questionType,
		"question.score":score,
		"question.rightAnswer":rightItem,
		"question.istest":isTest,
		"question.isexam":isExam,
		"question.unitId":courseUnitId,
		"question.orderLevel":orderLevel,
		"question.isDelete":0,
		"question.courseId":courseId,
		"itemStr":JSON.stringify(itemList),
		"operType":operType,
		"question.titleType":titleType,
		"questionId":questionId,
};
$.ajax({
	url:url,
	type:'POST',
	data:dataParam,
	dataType:'json',
	success:function(data){
		if(data==2){
				//重新加载列表
        		window.location.href="examQuestionList.jsp";
		}else{
        	artDialog.alert("修改失败！");
		}
		answerArr=[];
	}
	
});
$("#courseUnit").attr("disabled",false);
}


function addResultInfo(data){
	if(data != null && data != ""){
		var questionTitle = data.questionTitle;
		var questionType = data.questionType;
		var itemList = data.itemList;
		var orderLevel = data.orderLevel;
		var rightAnswer = data.rightAnswer;
		var arrRightAnswer = rightAnswer.split("-");
		var itemSize = itemList.length;
		var questionHtml = "";
		if(questionType==0){//代表单选题
			questionHtml += '<dl class="odd">';
			questionHtml += '<dt><span>'+orderLevel+'</span>、'+questionTitle+'<font>[单选]<input type="hidden" value="'+orderLevel+'"/></font>';
			questionHtml += '<span class="operate">';
			questionHtml += '<a onclick="preUpdateInit('+orderLevel+')" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
			questionHtml += '<a onclick="deleteQuestion('+orderLevel+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
			questionHtml += ' </span></dt>';
			for(var i=0;i<itemSize;i++){
				var item = itemList[i];
				var itemNo = item.itemCode;
				if(checkIsAll(arrRightAnswer,itemNo)){
					questionHtml+='<dd><label class="radio"><input type="radio" checked="checked">'+item.itemName+'</label></dd>';
				}else{
					questionHtml+='<dd><label class="radio"><input type="radio">'+item.itemName+'</label></dd>';
				}
			}
		}else{
			questionHtml += '<dl class="odd">';
			questionHtml += '<dt><span>'+orderLevel+'</span>、'+questionTitle+'<font>[多选]</font>';
			questionHtml += '<span class="operate">';
			questionHtml += '<a onclick="preUpdateInit('+orderLevel+')" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
			questionHtml += '<a onclick="deleteQuestion('+orderLevel+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
			questionHtml += ' </span></dt>';
			for(var i=0;i<itemSize;i++){
				var item = itemList[i];
				var itemNo = item.itemCode;
				if(checkIsAll(arrRightAnswer,itemNo)){
					questionHtml+='<dd><label class="radio"><input type="checkbox" checked="checked">'+item.itemName+'</label></dd>';
				}else{
					questionHtml+='<dd><label class="radio"><input type="checkbox">'+item.itemName+'</label></dd>';
				}
			}
		}
		art.dialog.tips("添加试题成功!");
		document.getElementById("form1").reset();
		$("#add").find("dd").each(function(i,value){
			if(i>3){
				if($(this).find("input").attr("id")){
					$(this).remove();
				}
			}
		});
		if($.trim($(".choice").text())==""){
			$(".choice").html(questionHtml);
		}else{
			$(".choice").children("dl:last-child").after(questionHtml);
		}
		if(hasExercise==1&&hasExam==1){
			$("input:radio[value='exercise']").attr("checked","checked");
			$("#inputGrade").hide();
		}else if(hasExercise!=1&&hasExam==1){
			$("#exercise").hide();
			$("input:radio[value='exam']").attr("checked","checked").prop("disabled",true);
		    $("#inputGrade").show();
		}else if(hasExercise==1&&hasExam!=1){
			$("input:radio[value='exercise']").attr("checked","checked").prop("disabled",true);
			$("#exam").hide();
			$("#inputGrade").hide();
		}
		
		
	}
}


function updateResultInfo(data){
	if(data != null && data != ""){
		var questionTitle = data.questionTitle;
		var questionType = data.questionType;
		var itemList = data.itemList;
		var orderLevel = data.orderLevel;
		var itemSize = itemList.length;
		var rightAnswer = data.rightAnswer;
		var arrRightAnswer = rightAnswer.split("-");
		var questionHtml = "";
		if(questionType==0){//代表单选题
			questionHtml += '<dl class="odd">';
			questionHtml += '<dt><span>'+orderLevel+'</span>、'+questionTitle+'<font>[单选]<input type="hidden" value="'+orderLevel+'"/></font>';
			questionHtml += '<span class="operate">';
			questionHtml += '<a onclick="preUpdateInit('+orderLevel+')" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
			questionHtml += '<a onclick="deleteQuestion('+orderLevel+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
			questionHtml += ' </span></dt>';
			for(var i=0;i<itemSize;i++){
				var item = itemList[i];
				var itemNo = item.itemCode;
				if(checkIsAll(arrRightAnswer,itemNo)){
					questionHtml+='<dd><label class="radio"><input type="radio" checked="checked">'+item.itemName+'</label></dd>';
				}else{
					questionHtml+='<dd><label class="radio"><input type="radio">'+item.itemName+'</label></dd>';
				}
			}
		}else{
			questionHtml += '<dl class="odd">';
			questionHtml += '<dt><span>'+orderLevel+'</span>、'+questionTitle+'<font>[多选]<input type="hidden" value="'+orderLevel+'"/></font>';
			questionHtml += '<span class="operate">';
			questionHtml += '<a onclick="preUpdateInit('+orderLevel+')" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
			questionHtml += '<a onclick="deleteQuestion('+orderLevel+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
			questionHtml += ' </span></dt>';
			for(var i=0;i<itemSize;i++){
				var item = itemList[i];
				var itemNo = item.itemCode;
				if(checkIsAll(arrRightAnswer,itemNo)){
					questionHtml+='<dd><label class="radio"><input type="checkbox" checked="checked">'+item.itemName+'</label></dd>';
				}else{
					questionHtml+='<dd><label class="radio"><input type="checkbox">'+item.itemName+'</label></dd>';
				}
			}
		}
		art.dialog.tips("修改试题成功!");
		document.getElementById("form1").reset();
		//所有设定正确答案隐藏
		var dlLength = $(".choice").find("dl").length;
		if(dlLength > 1){
			var oldQuestionPosition = $(".choice").find("dl").eq(parseInt(orderLevel)-1);
			if(dlLength==orderLevel){
				$(".choice").append(questionHtml);
			}else{
				var nextQuestionPosition = $(".choice").find("dl").eq(parseInt(orderLevel));
				nextQuestionPosition.before(questionHtml);

			}
			oldQuestionPosition.remove();
		}else{
			$(".choice").find("dl").remove().html(questionHtml);
		}
		var addQuestionHtml = createAddQuestionHtml();
		$("#update").remove();
		$("#add").remove();
		initQuestionType();
		$("#tabInfo").after(addQuestionHtml);
		$("#addQuestion").show();
		$("#updateQuestion").hide();
		if(hasExercise==1&&hasExam==1){
			$("input:checkbox[value='exercise']").attr("checked","checked");
		}else if(hasExercise!=1&&hasExam==1){
			$("input:checkbox[value='exam']").attr("checked","checked").prop("disabled",true);
		    $("#inputGrade").show();
		}else if(hasExercise==1&&hasExam!=1){
			$("input:checkbox[value='exercise']").attr("checked","checked").prop("disabled",true);
		}
	}
}



//修改之前初始化试题
function preUpdateInit(orderLevel){
	orderLevelVar = orderLevel;
	//通过questionId查询试题信息
	var url = basePath+"onlineExam/findQuestionByQuestionId.action";
	var  unitName = $("#courseUnit").val();
	var dataParam = {"orderLevel":orderLevel,"unitName":unitName};
	$.ajax({
		url:url,
		type:'POST',
		data:dataParam,
		dataType:'json',
		success:function(data){
			var updateHtml = "";
			if(data != null && data != "{}"){
				var questionTitle = data.questionTitle;
				var questionType = data.questionType;
				var rightAnswer = data.rightAnswer;
				var isTest = data.isTest;
				var isExam = data.isExam;
				var score = data.score;
				var itemList = data.itemList;
				var orderLevel = data.orderLevel;
				var itemSize = itemList.length;
				updateHtml += ' <dl class="enter" id="update">';
				updateHtml += '  <dt><label>试题名称：</label><input type="text" class="formText" id="title" name="title" value="'+questionTitle+'"/></dt>';
				var oldAnswerArr = rightAnswer.split("-");
				answerArr = oldAnswerArr;
				for(var i = 0;i < itemSize;i++){
					var item = itemList[i];
					var itemNo = item.itemCode;
					var itemName = item.itemName;
					if(checkIsAll(oldAnswerArr,itemNo)){
						if(i > 1){
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)" ></span><span class="oper on" name="isAnswer"  onclick="setAnswer(this)" value="'+itemNo+'">[正确答案]</span></dd>';
						}else{
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="oper on" name="isAnswer"  onclick="setAnswer(this)" value="'+itemNo+'">[正确答案]</span></dd>';
						}
					}else{
						if(i > 1){
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)" ></span><span class="oper"  onclick="setAnswer(this)" value="'+itemNo+'">[设置正确答案]</span></dd>';
						}else{
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" value="'+itemName+'"  valid="required" errMsg="question.questionItem_not_null"/><span class="oper"  onclick="setAnswer(this)" value="'+itemNo+'">[设置正确答案]</span></dd>';
						}
					}
				}
				updateHtml += '<dd class="operate" id="operateUpdate"><em id="addInsertItem" onclick="insertItem(\'update\',this)">+插入选项</em></dd>';
				updateHtml+="</dl>"
				if(hasExercise==1&&hasExam==1){
					if(isTest==1){
						$("input:checkbox[value='exercise']").prop("checked",true);
					}
					if(isExam==1){
						$("input:checkbox[value='exam']").prop("checked",true);
						$("#inputGrade").show();
						$("#grade").val(score);
					}
				}else if(hasExercise!=1&&hasExam==1){
					$("input:checkbox[value='exam']").attr("checked","checked").prop("disabled",true);
				    $("#inputGrade").val(score).show();
				}else if(hasExercise==1&&hasExam!=1){
					$("input:checkbox[value='exercise']").attr("checked","checked").prop("disabled",true);
				}
				$("input:radio[name='questionItem'][value='"+questionType+"']").attr("checked","checked");
				
			}
			$("#add").remove();
			$("#update").remove();
			$("#tabInfo").after(updateHtml);
			$("#addQuestion").hide();
			$("#updateQuestion").show();
		}
	});
}


function deleteQuestion(orderLevel){
	var url = basePath+"onlineExam/deleteUpdateQuestion.action";
	var unitName = $("#courseUnit").val();
	var dataParam = {"unitName":unitName,"orderLevel":orderLevel};
	art.dialog.confirm("您确定要删除吗?",function(){
		$.ajax({
			url:url,
			type:'POST',
			data:dataParam,
			dataType:'json',
			success:function(data){
				if(data != null && data != "{}"){
					//update
					var questionPosition = $(".choice").find("dl").eq(parseInt(orderLevel)-1);
					var questionArr = [];
					if($(".choice").find("dl").length!=1){
						questionPosition.nextAll("dl").each(function(){
							var newOrderLevel = parseInt($(this).children("dt").children("span").eq(0).text())-1;
							//递增排序号
							$(this).children("dt").children("span").eq(0).text(newOrderLevel)
							$(this).children("dt").children("span").eq(1).find("a").eq(0).removeAttr("onclick").attr("onclick","preUpdateInit("+newOrderLevel+")");;
							$(this).children("dt").children("span").eq(1).find("a").eq(1).removeAttr("onclick").attr("onclick","deleteQuestion("+newOrderLevel+")");;
						});	
						art.dialog.tips("删除成功!");
						questionPosition.remove();
					}else{
						questionPosition.remove();
						art.dialog.tips("删除成功!");
					}
				}else{
					art.dialog.alert("删除失败,请稍候重试!");
				}
				deleteAfterInit();
			}
		
		});
	});
	
}

//选项类
function Item(){
	this.itemCode="",
	this.itemName="",
	this.isDelete=""
}


function createAddQuestionHtml(){
    var addQuestionHtml = "";
    addQuestionHtml+='<dl class="enter" id="add" >';
    addQuestionHtml+='<dt><label>题干：</label><input type="text" class="formText" id="title" name="title" valid="required" errMsg="question.questionTitle_not_null"/></dt>';
    addQuestionHtml+='<dd><label>选项<span>1</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item1" valid="required" errMsg="question.questionItem_not_null"/><span class="oper" onclick="setAnswer(this)"  value="1">[设为正确答案]</span></dd>';
    addQuestionHtml+='<dd><label>选项<span>2</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item2" valid="required" errMsg="question.questionItem_not_null"/><span class="oper"  onclick="setAnswer(this)" value="2">[设为正确答案]</span></dd>';
    addQuestionHtml+='<dd><label>选项<span>3</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item3" valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)" ></span><span class="oper"  onclick="setAnswer(this)" value="3">[设为正确答案]</span></dd>';
    addQuestionHtml+='<dd><label>选项<span>4</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item4" valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)" ></span><span class="oper"  onclick="setAnswer(this)" value="4">[设为正确答案]</span></dd>';
    addQuestionHtml+='<dd class="operate" id="operateAdd"><em id="addInsertItem" onclick="insertItem(\'add\',this)">+插入选项</em></dd>';
    addQuestionHtml+='</dl>';
    return addQuestionHtml;
}


function deleteAfterInit(){
	document.getElementById("form1").reset();
	var addQuestionHtml = createAddQuestionHtml();
	$("#update").remove();
	$("#add").remove();
	initQuestionType();
	$("#tabInfo").after(addQuestionHtml);
	$("#addQuestion").show();
	$("#updateQuestion").hide();
	$("input:radio[name='questionItem']").attr("checked",false);
	if(hasExercise==1&&hasExam==1){
		$("input:checkbox[value='exercise']").attr("checked","checked");
	}else if(hasExercise!=1&&hasExam==1){
		$("input:checkbox[value='exam']").attr("checked","checked").prop("disabled",true);
	    $("#inputGrade").show();
	}else if(hasExercise==1&&hasExam!=1){
		$("input:checkbox[value='exercise']").attr("checked","checked").prop("disabled",true);
	}
}



