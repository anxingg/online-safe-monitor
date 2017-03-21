
$(function(){
	setQuestionType();
    $("#examType").change(function(){
   	 var examType = $("#examType").val();
   	 initTestParper(examType);
   	});
   initTestParper($("#examType").val());
   initType();
});


function initTestParper(examType){
	qytx.app.ajax({
		url : basePath + "exam/getPaper.action",
		type : "post",
		dataType : "json",
		data : {
			"search.state":0,
			"search.examType":examType
		},
		success : function(data) {
		$("#testParper").html("");
		if(data!=null&&data!=""){
			var jsonData = data;
			for (var i = 0; i < jsonData.length; i++) {
				$("#testParper").append("<option value='"
						+ jsonData[i].id + "'>" + jsonData[i].paperName
						+ "</option>");
			}
		}
		}
	});
	
}

function  initType(){
	var examTestId = $("#examTestId").val();
	$.ajax({
		url:basePath+"exam/findExamTest.action",
		type:'POST',
		data:{examTestId:examTestId},
		dataType:'json',
		success:function(data){
			$("#testName").val(data.testName);
			$("#companyName").html(data.companyName);
			$("#examType option[value='"+data.testType+"']").attr("selected","selected");
			 initTestParper(data.testType);
			if(data.parperIds!=null&&data.parperIds!=""){
				var parper = data.parperIds;
				var parperIds = parper.split(",");
				for (var i = 1; i < parperIds.length-1; i++) {
					$("#testParper option[value='"+parperIds[i]+"']").attr("selected","selected");
				}
				
			}
			
		}
	});
}



function addExamTest(operType){
		if(!validator(document.getElementById("form1"))){
			return;
		}
	var testName = $("#testName").val();
	var parperIds = $("#testParper").val();
    if(parperIds==null){
    	art.dialog.alert("请选择试卷");
    	return;
    }
	var titleType = $("#examType").val();
	var examTestId = $("#examTestId").val();
	var url = basePath+"exam/addExamTest.action";
	var dataParam = {
			"examTest.testName":testName,
			"examTest.testType":titleType,
			"examTest.PaperIds":','+parperIds+',',
			"examTestId":examTestId,
			"question.isDelete":0,
			"operType":operType
	};
	$.ajax({
		url:url,
		type:'POST',
		data:dataParam,
		dataType:'json',
		success:function(data){
	
            if(data==2){
					//重新加载列表
            		window.location.href="examTestList.jsp";
            }else{
            	artDialog.alert("修改失败！");
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

