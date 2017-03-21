/**
 * 新增试卷
 * @author wuzhou
 */
var questionList = [];
var _checkedIds ;
var map = new Map();
$(document).ready(function(){
	setExamType();
	//试题列表
	$("#examType").change(function(){
		map = new Map();
		getDataTable();
	});
	//头部全选复选框
	$("#myTable").delegate("#total", "click", function(event){
	   	checkTotal();
		event.stopPropagation();
	});
	//第一列复选按钮
    $("#myTable").delegate("input:checkbox[name='chk']", "click", function(event){
        check();
        event.stopPropagation();
    });
    //输入框的值改变
    $("#myTable").delegate("input[type='text']", "blur", function(event){
    	addCheckedToList();
        event.stopPropagation();
    });
});
/**
 * 分页查询
 */
function getDataTable(){
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
					"name" : "search.examType",
					"value" : $("#examType").val()
				}, {
					"name" : "search.state",
					"value" : 0
				});
		},
		"sAjaxSource" : basePath + "exam/listExamQuestion.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"mDataProp" : null
		}, {
			"sTitle" : '试题名称',
			"mDataProp" : "title",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '试题分值',
			"mDataProp" : 'score'
		}, {
			"sTitle" : '题目类型',
			"mDataProp" : 'questionType',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '试题类型 ',
			"mDataProp" : 'titleType',
			"sClass" : "longTxt"
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			 $('#myTable tbody  tr td').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
			 mapToTable();
		},
		"fnInitComplete" : function() {
			
		},
		"fnPreDrawCallback": function(){
			//保存当前页面中已经选中的题和分值
			addCheckedToList();
		},
		"aoColumnDefs" : [
		       {
					"aTargets" : [0],
					"fnRender" : function(oObj) {
						var id = oObj.aData.id;
						return "<input type='checkbox' name='chk' value='"+id+"'/>";
					}          
				},{
					"aTargets" : [2],
					"fnRender" : function(oObj) {
						var id = oObj.aData.id;
						var score = oObj.aData.score;
						return '<input onkeyup="testNum(this)" class="formText" style="width:90px;text-align:center;" id="question'+id+'" value="'+score+'" type="text" maxlength="2"/>';}
				}
		]
	});
}

/**
 * 头部全选记录
 */
function checkTotal() {
    var isTotalChecked=$("input:checkbox[id='total']").prop("checked");
    var listCheckbox=$("input:checkbox[name='chk']");
    if(isTotalChecked){
        listCheckbox.prop("checked", function( i, val ) {
            if (!$(this).prop("disabled")) {
                return true;
            }
        });
    }else{
        listCheckbox.prop("checked", false);
    }
    addCheckedToList();
}
/**
 * 选择记录
 */
function check() {
    var checkTotalNum=$("input:checkbox[name='chk']");
    var checkNum=0;
    var checkNumAll=true;
    checkTotalNum.each(function(i,val){
        if($(checkTotalNum[i]).prop("checked")){
            checkNum++;
        }else{
            checkNumAll=false;
        }
    });
    
    if(!checkNumAll){
        $("input:checkbox[id='total']").prop("checked",false);
    }else{
        $("input:checkbox[id='total']").prop("checked",true);
    }
    addCheckedToList();
}

/**
 * 把当前页面选中项的id和值放到list中
 */
function addCheckedToList() {
	var oneches = $("tbody input[type=checkbox]");
	for ( var i = 0; i < oneches.length; i++) {
		var score = $("#question"+oneches[i].value).val();
		if (oneches[i].checked == true) {
			if(map.get(oneches[i].value)==null){
				var item = new Item();
				item.score=score;
				item.questionId=oneches[i].value;
				map.put(oneches[i].value,item);
			}else{
				var olditem = map.get(oneches[i].value);
				olditem.score=score;
				map.put(olditem.questionId,olditem);
			}
		}
		if (oneches[i].checked == false) {
			if(map.get(oneches[i].value)!=null){
				map.remove(oneches[i].value);
			}
		}
	}
	//计算分值
	if(map!=null&&!map.isEmpty()){
		var qList = map.values();
		if(qList!=null&&qList.length>0){
			$("#questionTotal").html(qList.length);
			var totalScore = 0;
			for (var i = 0; i < qList.length; i++) {
				if(qList[i]!=null&&qList[i].score!=null){
					totalScore += parseInt(qList[i].score);
				}
			}
			$("#scoreTotal").html(totalScore);
		}else{
			$("#questionTotal").html(0);
			$("#scoreTotal").html(0);
		}
	}else{
		$("#questionTotal").html(0);
		$("#scoreTotal").html(0);
	}
}
/**
 * 把map中的值附加到列表中
 */
function mapToTable() {
	var oneches = $("tbody input[type=checkbox]");
	for ( var i = 0; i < oneches.length; i++) {
		if (map.get(oneches[i].value)!=null) {
			$(oneches[i]).attr("checked", "checked");
			$("#question"+oneches[i].value).val(map.get(oneches[i].value).score);
		} else {
			$(oneches[i]).removeAttr("checked");
		}
	}
	//判断全选是否被选中
	if($("tbody input[type=checkbox]:checked").length==$("tbody input[type=checkbox]").length && $("tbody input[type=checkbox]").length > 0){
		$("thead input[type=checkbox]").prop("checked",true);
	}else{
		$("thead input[type=checkbox]").prop("checked",false);
	}
}


function addQuestion(operType){
	var paperName = $("#paperName").val();
	if(paperName==""){
		art.dialog.alert("试卷名称不能为空!");
		return;
	}
	var paperTime = $("#paperTime").val();
	if(paperTime==""){
		art.dialog.alert("考试时长不能为空!");
		return;
	}
	if(paperTime==0){
		art.dialog.alert("考试时长必须大于零!");
		return;
	}
	var examType = $("#examType").val();
	if(examType==""){
		art.dialog.alert("请选择试卷类型!");
		return;
	}
	var questionToTal = $("#questionTotal").html();
	if(parseInt(questionToTal)==0){
		art.dialog.alert("请选择试题!");
		return;
	}
	var scoreTotal = $("#scoreTotal").html();
	if(parseInt(scoreTotal)==0){
		art.dialog.alert("试卷总分不能为零!");
		return;
	}
	var url = basePath+"exam/addPaper.action";
	var dataParam = {
			"paper.paperName":paperName,
			"paper.paperType":examType,
			"paper.paperTime":paperTime,
			"paper.questionNum":questionToTal,
			"paper.score":scoreTotal,
			"paperQuestion":JSON.stringify(map.values())
	};
	$.ajax({
		url:url,
		type:'POST',
		data:dataParam,
		dataType:'json',
		success:function(data){
			if(data==1){
					window.location.href=basePath+"wh/logined/exam/examPaperList.jsp";
			}else{
				art.dialog.alert("添加失败！");
			}
		}
		
	});
}


function setExamType() {
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
					getDataTable();
				}
			});
}


//选项类
function Item(){
	this.score="",
	this.questionId=""
}

function Question(){
	this.orderLevel="",
	this.id=""
}
/**
 * 输入框只能输入数字
 * @param obj
 */
function testNum(obj){
	if(!/^(\d)*$/.test(obj.value)){//验证需要增加别的字符的时候/^(\d|;|,)*$/
		obj.value = obj.value.replace(/[^\d]/g,'');
	}
}
