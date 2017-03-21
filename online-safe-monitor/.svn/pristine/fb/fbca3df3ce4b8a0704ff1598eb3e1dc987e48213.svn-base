$(function(){
	setQuestionType();
	initQuestion();
});



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
				
				$("#title").html(questionTitle);
				if(questionType==1){
					$("#questionItem").html("多选题");
				}else{
					$("#questionItem").html("单选题");
				}
				$("#grade").html(score);
				$("#examType1").html($("#examType option[value='"+titleType+"']").text());
				var oldAnswerArr = rightAnswer.split("-");
				answerArr = oldAnswerArr;
//				$("#examType option[value='"+titleType+"']").attr("selected","selected");
//				$("#examType").attr("disabled","disabled")
				var updateHtml = "";
				for(var i = 0;i < itemSize;i++){
					var item = itemList[i];
					var itemNo = item.itemCode;
					var itemName = item.itemName;
					if(checkIsAll(oldAnswerArr,itemNo)){
						if(i > 1){
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：'+itemName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp[正确答案]</label></dd>';
						}else{
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：'+itemName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp[正确答案]</label></dd>';
						}
					}else{
						if(i > 1){
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：'+itemName+'</label></dd>';
						}else{
							updateHtml += '<dd><label>选项<span>'+itemNo+'</span>：'+itemName+'</label></dd>';
						}
					}
					
				}
			$("#add").empty().html(updateHtml);
			}
		}
	});

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




function checkIsAll(arr,value){
    for(var i = 0;i < arr.length;i++){
        if(value==arr[i]){
            return true;//如果有一个不存在就返回false
        }
    }
    return false;
}







