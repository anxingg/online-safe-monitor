
var hasExercise;
var hasExam;
var answerArr = [];
var orderLevel2;
$(function(){
	setQuestionType();
	$("input:radio[name='questionItem']").change(function(){
		answerArr = [];
	})
	
	$("input:radio[name='questionItem']").each(function(){
		answerArr = [];
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
	
	$("input:checkbox[name='grade']").each(function(){
		var obj = $(this);
		obj.click(function(){
			if(obj.attr("value")=="exam"){
				if(obj.prop("checked")){
					$("#inputGrade").show();
				}else{
					$("#inputGrade").hide();
				}
			}
		});
	});
	
	
	
});

//初始化试题
function initQuestion(){
	$("#courseUnit").attr("disabled",false);
	var courseUnitId = $("#courseUnit").val();
	var url = basePath+"onlineExam/findQuestion.action";
	var dataParam = {"unitId":courseUnitId};
	$.ajax({
		url:url,
		type:'POST',
		data:dataParam,
		dataType:'json',
		success:function(data){
			var choiceHtml = "";
			if(data != null && data != "{}"){
				var size = data.length;
				for(var i = 0;i < size;i++){
					var questionHtml = "";
					var question = data[i];
					var questionId = question.questionId;
					var questionTitle = question.questionTitle;
					var questionType = question.questionType;
					var itemList = question.itemList;
					var orderLevel = question.orderLevel;
					var itemList = question.itemList;
					var itemSize = itemList.length;
					var rightAnswer = question.rightAnswer;
					var isTest = question.istest;
					var isExam = question.isexam;
					var questionParse = question.questionParse;
					var questionHtml = "";
					if(questionType==0){//代表单选题
						var arrRightAnswer = rightAnswer.split("-");
						if(i%2==0){
							questionHtml += '<dl class="odd"><dt>';
						}else{
							questionHtml += '<dl><dt>';
						}
						questionHtml+='<span style="padding-left:0;">所属章节：'+$("#courseUnit").find("option:selected").text()+'</span><span>题目用途：';
						if(isTest==1){
							questionHtml+='<label class="radio"><input type="checkbox" disabled="disabled" checked="checked">练习题</label>';
						}else{
							questionHtml+='<label class="radio"><input type="checkbox" disabled="disabled">练习题</label>';
						}
						if(isExam==1){
							questionHtml+='<label class="radio" style="margin-right:0"><input type="checkbox" disabled="disabled" checked="checked">考试题</label>';
						}else{
							questionHtml+='<label class="radio" style="margin-right:0"><input type="checkbox" disabled="disabled">考试题</label>';
						}
						questionHtml +='</span>';
						if(isExam==1){
							var score = question.score;
							questionHtml += '<span style="border-right:none">题目分数：'+score+'分</span>';
						}
						questionHtml += '<span class="operate">';
						questionHtml += '<a onclick="preUpdateInit('+questionId+')" href="#" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
						questionHtml += '<a onclick="deleteQuestion('+questionId+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
						questionHtml += ' </span></dt>';
						questionHtml += '<dd class="title"><span>'+orderLevel+'</span>、<span id="tigan">'+questionTitle+'</span><font>[单选]<input type="hidden" value="'+questionId+'"/></font></dd>';
						for(var j=0;j<itemSize;j++){
							var item = itemList[j];
							var itemNo = item.itemCode;
							if(checkIsAll(arrRightAnswer,itemNo)){
								questionHtml+='<dd><label class="radio"><input type="radio" checked="checked" disabled="true">'+item.itemName+'</label></dd>';
							}else{
								questionHtml+='<dd><label class="radio"><input type="radio" disabled="true">'+item.itemName+'</label></dd>';
							}
						}
					}else if(questionType==1){
						var arrRightAnswer = rightAnswer.split("-");
						if(i%2==0){
							questionHtml += '<dl class="odd"><dt>';
						}else{
							questionHtml += '<dl><dt>';
						}
						questionHtml+='<span style="padding-left:0;">所属章节：'+$("#courseUnit").find("option:selected").text()+'</span><span>题目用途：';
						if(isTest==1){
							questionHtml+='<label class="radio"><input type="checkbox" disabled="disabled" checked="checked">练习题</label>';
						}else{
							questionHtml+='<label class="radio"><input type="checkbox" disabled="disabled">练习题</label>';
						}
						if(isExam==1){
							questionHtml+='<label class="radio" style="margin-right:0"><input type="checkbox" disabled="disabled" checked="checked">考试题</label>';
						}else{
							questionHtml+='<label class="radio" style="margin-right:0"><input type="checkbox" disabled="disabled">考试题</label>';
						}
						questionHtml +='</span>';
						if(isExam==1){
							var score = question.score;
							questionHtml += '<span style="border-right:none">题目分数：'+score+'分</span>';
						}
						questionHtml += '<span class="operate">';
						questionHtml += '<a onclick="preUpdateInit('+questionId+')" href="#" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
						questionHtml += '<a onclick="deleteQuestion('+questionId+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
						questionHtml += ' </span></dt>';
						questionHtml += '<dd class="title"><span>'+orderLevel+'</span>、<span id="tigan">'+questionTitle+'</span><font>[多选]<input type="hidden" value="'+questionId+'"/></font></dd>';
						for(var j=0;j<itemSize;j++){
							var item = itemList[j];
							var itemNo = item.itemCode;
							if(checkIsAll(arrRightAnswer,itemNo)){
								questionHtml+='<dd><label class="radio"><input type="checkbox" checked="checked" disabled="true">'+item.itemName+'</label></dd>';
							}else{
								questionHtml+='<dd><label class="radio"><input type="checkbox" disabled="true">'+item.itemName+'</label></dd>';
							}
						}
					}else{
						if(i%2==0){
							questionHtml += '<dl class="odd"><dt>';
						}else{
							questionHtml += '<dl><dt>';
						}
						questionHtml+='<span style="padding-left:0;">所属章节：'+$("#courseUnit").find("option:selected").text()+'</span><span>题目用途：';
						if(isTest==1){
							questionHtml+='<label class="radio"><input type="checkbox" disabled="disabled" checked="checked">练习题</label>';
						}else{
							questionHtml+='<label class="radio"><input type="checkbox" disabled="disabled">练习题</label>';
						}
						if(isExam==1){
							questionHtml+='<label class="radio" style="margin-right:0"><input type="checkbox" disabled="disabled" checked="checked">考试题</label>';
						}else{
							questionHtml+='<label class="radio" style="margin-right:0"><input type="checkbox" disabled="disabled">考试题</label>';
						}
						questionHtml +='</span>';
						questionHtml += '<span class="operate">';
						questionHtml += '<a onclick="preUpdateInit('+questionId+')" href="#" style="cursor:pointer"><i class="batch_revise"></i>修改</a>';
						questionHtml += '<a onclick="deleteQuestion('+questionId+')" style="cursor:pointer"><i class="delete"></i>删除</a>';
						questionHtml += ' </span></dt>';
						questionHtml += '<dd class="title"><span>'+orderLevel+'</span>、<span id="tigan">'+questionTitle+'</span><font>[问答]<input type="hidden" value="'+questionId+'"/></font></dd>';
						questionHtml += '<dd><textarea class="formTextarea" disabled="disabled"></textarea></dd>';
					}
					if(questionParse!=null && questionParse!=""){
						questionHtml += '<dd><font>解析</font>:'+questionParse+'</dd>';
					}
					questionHtml+="</dl>";
					choiceHtml+=questionHtml;
				}
			}
			$(".choice").empty().html(choiceHtml);
			
		}
	});
	
	var unitId = $("#courseUnit").val();
	//document.getElementById("form1").reset();
	$("#courseUnit option[value='"+unitId+"']").attr("selected",true);
	
	//initQuestionType();
	$("#addQuestion").show();
	$("#updateQuestion").hide();
}



function closeItem(obj){
	var dl = $(obj).parent("dd").parent("dl");
	$(obj).parent("dd").nextAll("dd").each(function(){
			var span = $(this).children("label").children("span");
			var num = span.text();
			span.text(parseInt(num)-1);
			$(this).children("span").eq(1).attr("value",parseInt(num)-1);
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
		
		var ddLength = $("#add").children("dd").length;
		if(ddLength >=11){
			art.dialog.alert("选项最多只能为10项!");
			return;
		}
		
		var dd = $(obj).parent("dd").prev();
		var newNo = parseInt(dd.children("label").children("span").text())+1;
		var newItemHtml = "";
		if(isSelectedQuestionType()){
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" maxlength="50" placeholder="请输入选项内容" id="item'+newNo+'" valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}else{
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" maxlength="50" placeholder="请输入选项内容" id="item'+newNo+'" valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" style="display: none" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}
		dd.after(newItemHtml);
	}
	if(operType=="update"){
		var ddLength = $("#update").children("dd").length;
		if(ddLength >=11){
			art.dialog.alert("选项最多只能为10项!");
			return false;
		}
		var dd = $(obj).parent(".operate").prev();
		var newNo = parseInt(dd.children("label").children("span").text())+1;
		var newItemHtml = "";
		if(isSelectedQuestionType()){
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" maxlength="50" placeholder="请输入选项内容" id="updateItem'+newNo+'" valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
		}else{
			newItemHtml = '<dd><label>选项<span>'+newNo+'</span>：</label><input type="text" class="formText" maxlength="50" placeholder="请输入选项内容" id="updateItem'+newNo+'" valid="required" errMsg="question.questionItem_not_null"/><span class="close" onclick="closeItem(this)"></span><span class="oper" style="display: none" onclick="setAnswer(this)" value="'+newNo+'">[设为正确答案]</span></dd>';
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
			answerArr.splice($.inArray(itemNo,answerArr),1);
		}
	}
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
	var radioMany = $("input:radio[value='1']").prop("checked");
	if(radioOne){
		return "one";
	}
	if(radioMany){
		return "many";
	}
	return "other"
}

function addQuestion(operType){


		if(!validator(document.getElementById("form1"))){
			return;
		}
		
		if(operType == "add"){
			var title = $.trim($("#title").val());
		

			if(!title){
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
	//让保存按钮不可用
	$(".formButton_green").attr("disabled", "disabled");
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
			if(data==1){
					//重新加载列表
					window.location.href="examQuestionList.jsp";
			}else{
				artDialog.alert("添加失败！");
				$(".formButton_green").removeAttr("disabled");
			}
			
		
			answerArr=[];
			
			//让保存按钮可用
			//$(".formButton_green").removeAttr("disabled");
		}
		
	});
	$("#courseUnit").attr("disabled",false);
}


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
					for (var i = 0; i < jsonData.length; i++) {
						$("#examType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
				}
			});
}

function deleteQuestion(questionId){
	var url = basePath+"onlineExam/deleteQuestion.action";
	var dataParam = {"questionId":questionId};
	art.dialog.confirm("您确定要删除吗?",function(){
		$.ajax({
			url:url,
			type:'POST',
			data:dataParam,
			dataType:'json',
			success:function(data){
				if(data != null && data != "{}"){
					var orderLevel = data.orderLevel;
					var questionPosition = $(".choice").find("dl").eq(parseInt(orderLevel)-1);
					var questionArr = [];
					if($(".choice").find("dl").length!=1){
						questionPosition.nextAll("dl").each(function(){
							var questionId = $(this).children("dd").eq(0).children("font").children("input").val();
							var newOrderLevel = parseInt($(this).children("dd").eq(0).children("span").eq(0).text())-1;
							var question = new Question();
							question.id= questionId;
							question.orderLevel= newOrderLevel;
							questionArr.push(question);
							//递增排序号
							$(this).children("dd").eq(0).children("span").eq(0).text(newOrderLevel)
						});	
						var unitId = $("#courseUnit").val();
						var url2 = basePath+"onlineExam/updateQuestionOrderLevel.action";
						$.ajax({
							url:url2,
							type:'POST',
							data:{"questionStr":JSON.stringify(questionArr),"unitId":unitId,"orderLevel":orderLevel},
							dataType:'text',
							success:function(data){
								if(data != null && data !=""){
									art.dialog.tips("删除成功!");
									questionPosition.remove();
								}
							}
						});
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

function Question(){
	this.orderLevel="",
	this.id=""
}



function deleteAfterInit(){
	//document.getElementById("form1").reset();
	var addQuestionHtml = createAddQuestionHtml();
	$("#update").remove();
	$("#add").remove();
	//initQuestionType();
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
	$("input:radio[name='questionItem']").parent("label").show();
	$("#answerQuestion").hide();
}

