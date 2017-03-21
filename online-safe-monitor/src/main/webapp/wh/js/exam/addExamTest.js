$(function(){
	setQuestionType();
    setcompanyName();
    $("#examType").change(function(){
    	 var examType = $("#examType").val();
    	 initTestParper(examType);
    	});
    
    initTestParper($("#examType").val());
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
	$(".formButton_green").attr("disabled", "disabled");
	var url = basePath+"exam/addExamTest.action";
	var dataParam = {
			"examTest.testName":testName,
			"examTest.testType":titleType,
			"examTest.PaperIds":','+parperIds+',',
			"question.isDelete":0,
			"operType":operType
	};
	$.ajax({
		url:url,
		type:'POST',
		data:dataParam,
		dataType:'json',
		success:function(data){
	            if(data==1){
						//重新加载列表
	            		window.location.href="examTestList.jsp";
	            }else{
	            	artDialog.alert("修改失败！");
	    			$(".formButton_green").removeAttr("disabled");
	            }
	            
		
		}
		
	});
}

function setcompanyName(){
	var paramData = {groupId:$("#group_id").val()};
	qytx.app.ajax({
		url : basePath + "companywh/getWHCompanyInfo.action",
		type : "post",
		dataType : "html",
		data : paramData,
		success : function(data) {
			var company = eval("(" + data + ")");
			$("#companyName").html(company.companyName);
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

