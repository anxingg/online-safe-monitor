$(document).ready(function(){
	//
	getDangerGrade();
	
	// 单击更新
	$("#save").click(function() {
		var cr = checkpara();
		if(cr){
			var valid = validator($("#addForm")[0]);
			if (valid) {
				addorup();
			}
			return false;
		}
	});
	
	//标签页切换
	$(".tabBox li").click(function(){
		var index_tab = $(this).parent().children().index($(this));
		$(this).parent().find(".on").removeClass("on"); 
		$(this).addClass("on");
		var content_obj = $(this).parent().parent().parent().find(".tabContent");
		content_obj.hide();
	    content_obj.eq(index_tab).show(); 
	    
	});
	
	//查询按钮绑定事件
	$(".searchButton").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_queryDetail.action');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_queryDetail.action');
			getTableList();
			return false;
		}
	});
	
	getTableList();
});

function getDangerGrade(){
	$.ajax({
		url : basePath + "zhdwxy/queryGrade.action",
		type : "post",
		dataType : 'json',
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				for (var i = 0; i < list.length; i++) {
						var name = list[i].name;
						var value = list[i].value;
						html += '<option value="'+value+'"';
						if(_name == value){
							html= html.concat(" selected >").concat(name).concat("</option>");
						}else{
							html += '>'+name+"</option>";
						}
					}
				}
				$("#danger_grade").html(html);
			}
	});
}


function checkpara(){
	var danger_grade = $("#danger_grade").val();
	if(danger_grade==""){
		artDialog.alert("请选择重大危险源等级！");
		return false;
	}
	/*var review_time = $("#review_time").val();
	if(review_time==""){
		artDialog.alert("评审时间不能为空");
		return false;
	}
	var review_end_time = $("#review_end_time").val();
	if(review_end_time==""){
		artDialog.alert("评审过期时间不能为空");
		return false;
	}*/
	var distance =  $("#distance").val();
	if(distance==""||!isPosInt(distance)){
		artDialog.alert("重大危险源与周边重点保护目标最近距离情况必须输入正整数！");
		return false;
	}
	return true;
}


function  addorup(){
	$.ajax({
		url:basePath+"zhdwxy/saveorup.action",
		type:"post",
		data:"grade="+$("#grade").val()+"&ac_flag=upp&dangerSourcesName="+$("#dangerSourcesName").val()+"&vid="+_vid
		//+"&safetyMeasures="+$("#safety_measures").val()
		+"&address="+$("#address").val()+"&danger_grade="+$("#danger_grade").val()
		//+"&review_time="+$("#review_time").val()+"&review_end_time="+$("#review_end_time").val()
		+"&product_scale="+$("#product_scale").val()+"&distance="+$("#distance").val()+"&three_year_accident="+$("#three_year_accident").val()
		//+"&accident_measures="+$("#accident_measures").val(),
		+"&mechanism="+$("#mechanism").val()+"&men="+$("#men").val()+"&is_park="+$("#is_park").val()+"&use_time="+$("#use_time").val()+"&r_value="+$("#r_value").val(),
		dataType:"json",
		success:function(data){
			if(data=="0"){
				window.location.href=basePath+"wh/zhdwxy/query.jsp";
			}else{
				artDialog.alert("重大危险源信息新增失败！");
			}
		},error:function(){
			artDialog.alert("重大危险源信息新增失败！");
		}
	});
}
/**
 * 时间加3年再减一天
 */
function putTime(){
	var review_time = $("#review_time").val();
	if(review_time=="" || review_time.length!=10){
		return;
	}
	var beginDate = new Date(review_time);
	var year = beginDate.getYear()+1900;
	beginDate.setYear(year+3);
	var endDate = new Date(beginDate.getTime()-(24 * 60 * 60 * 1000));
	year = endDate.getYear()+1900;
	var month = endDate.getMonth()+1;
	if(month<10){
		month = "0"+month;
	}
	var day = endDate.getDate();
	if(day<10){
		day = "0"+day;
	}
	$("#review_end_time").val(year+"-"+month+"-"+day);
}



/**
 * 加载列表
 */
function getTableList(){
	//部门ID
	var groupId = $("#group_id").val();
	
	//危化品名称
	var dangerGoodName = $.trim( $('#dangerGoodName').val() );
	
	//危险性类别
	var dictId = $('#dictId').val();
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "dangerSourcesGood.groupId",//部门ID
				"value" : groupId
			}, {
				"name" : "dangerSourcesGood.dangerId",//关联的重大危险源ID
				"value" : _vid
			}, {
				"name" : "dangerSourcesGood.dangerGoodName",//危化品名称
				"value" : dangerGoodName
			}, {
				"name" : "dangerSourcesGood.dictId",//危险性类别
				"value" : dictId
			});
		},
		"sAjaxSource" : basePath + "dangersourcesgood/dangerSourcesGood_List_list.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"mDataProp" : 'no'
		}, {
			"mDataProp" : "dangerGoodName",
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'dangerObjType'
		}, {
			"mDataProp" : 'unCode',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'purpose',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'process',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'physicalState',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : null
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {

			 $('#myTable tbody tr td').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
			
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
//			window.parent.frameResize();
		},
		"aoColumnDefs" : [
			{
				"aTargets" : [7], //操作
				"fnRender" : function(oObj) {
					var whroletype = $("#whroletype").val();
					var vid = oObj.aData.vid;
					var html = '<a href="javascript:addOne('+vid+', 4);">查看</a>';
					//企业端可见
					if(whroletype == 2){
						html += '<a href="javascript:addOne('+vid+', 2);">修改</a>';
						html += '<a href="javascript:deleteOne('+vid+');">删除</a>';
					}
					return html;
				}
			}
		]
	});
}

var closenow = false;

/**
 * 新增或修改一个重大危险源危化品目录对象
 * @param vid
 */
function addOne(vid, operation){
	if(!vid){
		vid = '';
	}
	if(!operation){
		operation = 1;
	}
	var title = '新增危化品信息';
	var okButton = openWinOk;
	if(operation == 2){
		title = '修改危化品信息';
	}
	if(operation == 4){
		title = '查看危化品信息';
		okButton = null;
	}
	
	var url = basePathFull+'dangersourcesgood/dangerSourcesGoodToPage_findOne_addDSG.action?infoType=dangerObjType&dangerSourcesGood.vid='+vid+'&operation='+operation;
	
	art.dialog.data("addOneSubmit", addOneSubmit);
	art.dialog.open(url, {
	    id : "addDSG",
	    title : title,
	    width : 890,
	    height : 455,
	    lock : true,
	    //drag:false,
	    opacity: 0.08,// 透明度
//	    close : function(){
//	    	set(_infoType,_sysTypeId,_value) ;
//	    	return true;
//	    },
	    ok : okButton,
	    cancel : function(){
	    	return true;
	    }
	});
}

function openWinOk(){
	var iframe = this.iframe.contentWindow;
	iframe.save();
	return closenow;
}

function closeAddDSGPage(){
	closenow = true;
	art.dialog.list['addDSG'].close();
	//重置
	closenow = false;
}

/**
 * addOne方法的子方法
 * 
 * @param vid
 * @param dangerGoodName
 * @param dictId
 * @param unCode
 * @param purpose
 * @param process
 * @param physicalState
 * @param operationTemp
 * @param operationPressure
 * @param simpleStock
 * @param unitStock
 * @param criticalMass
 */
function addOneSubmit(vid, dangerGoodName, dictId, unCode, purpose, 
		process, physicalState, operationTemp, operationPressure, 
		simpleStock, unitStock, criticalMass) {
	
	//alert(vid+', '+dangerGoodName+', '+dictId+', '+unCode+', '+purpose+', '+process+', '+physicalState+', '+operationTemp+', '+operationPressure+', '+simpleStock+', '+unitStock+', '+criticalMass);
	
	//部门ID
	var groupId = $("#group_id").val();
	
	$.ajax({
		url : basePathFull + "dangersourcesgood/dangerSourcesGood_Add_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'dangerSourcesGood.vid' : vid,
			'dangerSourcesGood.groupId' : groupId,
			'dangerSourcesGood.dangerId' : _vid,
			'dangerSourcesGood.dangerGoodName' : dangerGoodName,
			'dangerSourcesGood.dictId' : dictId,
			'dangerSourcesGood.unCode' : unCode,
			'dangerSourcesGood.purpose' : purpose,
			'dangerSourcesGood.process' : process,
			'dangerSourcesGood.physicalState' : physicalState,
			'dangerSourcesGood.operationTemp' : operationTemp,
			'dangerSourcesGood.operationPressure' : operationPressure,
			'dangerSourcesGood.simpleStock' : simpleStock,
			'dangerSourcesGood.unitStock' : unitStock,
			'dangerSourcesGood.criticalMass' : criticalMass
		},
		success : function(data) {
			if (data == 1) {
				closeAddDSGPage();
				getTableList();
			}
		}
		
	});
}

/**
 * 删除操作
 */
function deleteOne(id) {
	artDialog.confirm("你确定要删除吗？", function(){
		$.ajax({
			url : basePathFull + "dangersourcesgood/dangerSourcesGood_Load_deleteOne.action",
			type : "post",
			dataType : 'text',
			data : {
				'dangerSourcesGood.vid' : id
			},
			success : function(data) {
				if(data == 1){
					getTableList();
				}
			}
			
		});
	});
}