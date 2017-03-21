/**
 * 吴洲
 * 考试
 */
var practiceNum = 0;//当前题号   
var questionList = new Array();//试题list
var answerStr = "";//当前题的答案  以“-”分隔
var myAnswer = "";//我选择的答案
var currentType = 0;//当前题类型  0单选1多选
var questionNum=0;
var testTime;
var _nowDate = new Date().getTime();
var answerMap = new Map();
var startTime;
var _testTime;
var isCache;//是否保存
var abcTool = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T");
var questionIdStr = "";
var intervalId;
var examPaperId;
var testId;
var testName;
var examPaperName;
$(function(){
	//获得所有习题  
	getAllPracticeList();
	//获取考试时间
	var url = basePath+"exam/getTestTime.action";
	$.ajax({
		url:url,
		type:'POST',
		data:{"examPaperId":examPaperId},
		dataType:'text',
		async:false,
		success:function(data){
			if(data != null){
				var dataStr = data.split(":");
				_nowDate = Number(dataStr[0]);
				_testTime = parseInt(dataStr[0]);
				startTime = Number(dataStr[1]);
			}
		}
	});
	intervalId = setInterval('refreshCalendarClock()', 1000);
	$("#submitExam").click(function(){
		preSubmitExam();
	});
});
/**
 * 获得所有习题
 */
function getAllPracticeList(){
	var idcard = $("#idcard").val();
	var userName = $("#userName").val();
		var dataParam = {
				'type':1,
				'_clientType':"wap",
				'idcard':idcard,
				'testId':$("#testId").val(),
				'userName':userName
		};
		$.ajax({
			type : 'post',
			url : basePath + "exam/getExam.action?r="+Math.random(),
			data : dataParam,
			dataType : 'json',
			async:false,
			success : function(data){
				if(data != null){
					questionList = data.list;
					examTestConfig=data.examTestConfig;
					    $("#paperTime").text(data.paperTime);
						$("#paperName").text(examTestConfig.paperName);
						$("#companyName").text(data.companyName);
						$("#idcardNum").html(idcard);
						$("#userNameDT").html(userName);
						testId = data.testId;
						testName =	data.testName;
						examPaperId = examTestConfig.id;
						examPaperName = examTestConfig.examPaperName;
					if(questionList.length==0){
						//暂无数据
					}else{
						var html = "";
						questionNum = questionList.length;
						for(var i = 0;i < questionNum;i++){
							var question = questionList[i];
							questionIdStr += question.id+",";
							html += "<dl><dt>"+(i+1)+"、"+question.title+"<input type='hidden' value='"+question.id+"'/><font>[";
						
						currentType = question.questionType;
						var itemList = question.questionItems;//选项
						if(currentType==0){//单选
							html += '单选]</font></dt>';
							if(itemList !=null && itemList.length>0){
								for(var j=0;j<itemList.length;j++){
									var code = abcTool[j];
									html += '<dd style="background: transparent none repeat scroll 0% 0%;"><label  class="radio"><input type="radio" name="'+question.id+'" value="'+itemList[j].itemCode+'" >'+code+'.'+itemList[j].itemName+'</label></dd>';
								}
							}
						}else{
							html += '多选，最少2项]</font></dt>';
							if(itemList !=null && itemList.length>0){
								for(var j=0;j<itemList.length;j++){
									var code = abcTool[j];
									html += '<dd><label class="radio" ><input type="checkbox" name="'+question.id+'" value="'+itemList[j].itemCode+'"/>'+code+'.'+itemList[j].itemName+'</label></dd>';
								}
							}
						}
						html += "</dl>";
					}
						$("#questionInfo").after(html);
						$("label").delegate("input[type=checkbox]",'click',function(){
							submitAnswer(this);
						});
						$("label").delegate("input[type=radio]",'click',function(){
							submitAnswer(this);
						});
					}
				}
		
			}
		});
}


function preSubmitExam(){
	var size = answerMap.size();
	var tipInfo = "";
	if(size<questionNum){
		tipInfo = "您还有考试题未做,确定要提交试卷吗?";
		clearInterval(intervalId);
	}else{
		tipInfo = "您已做完所有试题,确定要提交试卷吗?";
		clearInterval(intervalId);
	}
	art.dialog.confirm(tipInfo,function(){
		submitExam();
	}, function () {
		intervalId = setInterval('refreshCalendarClock()', 1000);
	});
}

function submitExam(){
	var idcard = $("#idcard").val();
	var userName = $("#userName").val();
	var paperName = $("#paperName").html();
	var dataParam = {
			"examPaperId":examPaperId,
			"examUserTest.userName":userName,
			"examUserTest.idCardNum":idcard,
			"examUserTest.paperName":paperName,
			"examUserTest.testId":testId,
			"examUserTest.testName":testName,
			"examUserTest.examPaperName":examPaperName,
			"questionIdStr":questionIdStr,
			"startTime":startTime,
			"testTimeLength":(_testTime-_nowDate),
			"questionResult":JSON.stringify(answerMap),
			"testLogStatus":1
		};
	var url = basePath+"exam/submitExam.action";
	$.ajax({
		type:'POST',
		url:url,
		data:dataParam,
		dataType:'json',
		success:function(data){
			clearInterval(intervalId);
			if(data != null && data != "{}"){
				if(data==0){
					artDialog.alert("提交答卷成功!",function(){
						window.location.href = basePath + "wh/logined/exam/userExamList.jsp";
					});
				}
			}else{
				art.dialog.alert("系统繁忙,请稍候重试!");
			}
			
		}
	});
	
	
}


function CurentTime() {
	var mm = parseInt(_nowDate/(1000*60));
	var ss = _nowDate % 60000;
	ss = (ss - (ss % 1000)) / 1000;
	var clock ="";
	if (mm < 10)
		clock += '0';
	clock += mm + ':';
	if (ss < 10)
		clock += '0';
	clock += ss;
	return (clock);
}


function refreshCalendarClock() {
	_nowDate=Number(_nowDate)-Number(1000);
	if(_nowDate==60000){
		art.dialog.tips("考试倒计时还剩一分钟!");
	}
	if(_nowDate == 0){ //试卷自动提交
		art.dialog.tips("考试时间已到,自动提交试卷!");
		clearInterval(intervalId);
		submitExam();
	}
	var time = CurentTime();
	$("#time").text(time);
}


function submitAnswer(obj){
	var obj = $(obj);
	//var obj = $(obj).find("input");
	var questionType = obj.attr("type");
	var questionId = obj.parents("dl").children("dt").find("input").val();
	var myAnswer = obj.val();
	if(questionType=="radio"){//单选
		answerMap.put(questionId+"",myAnswer);
	}else{
		if(answerMap.get(questionId+"")){
			var oldAnswer = answerMap.get(questionId+"");
			var answerArr = oldAnswer.split("-");
			if(!checkIsAll(answerArr,myAnswer)){
				oldAnswer+="-"+myAnswer;
				answerMap.put(questionId+"",oldAnswer);
			}else{
				var index = getArrayIndex(answerArr,myAnswer);
				answerArr.splice(index,1);
				if(answerArr.length > 0){
					answerMap.remove(questionId+"");
					answerMap.put(questionId+"",answerArr.join("-"));
				}else{
					answerMap.remove(questionId+"");
				}
			}
		}else{
			answerMap.put(questionId+"",myAnswer);
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

function getArrayIndex(arr,val){
	for (var i = 0; i < arr.length; i++) {  
        if (arr[i] == val) return i;  
    }  
    return -1;   
}



