$(document).ready(function(){
	
	/**
	 * 添加单选题
	 */
	$("#addSingle").click(function(){
		var dlString = $(".choice dl:last").find("dt").text();
		var num = 1;
		if(dlString){
			num = dlString.substring(0,dlString.indexOf("、"));
			num++;
		}
		var template = "<dl class=\"add_xz\"><input type=\"hidden\" name=\"typeId\" value=\"1\"/><dt><label>"+num+"、题干：</label><label style=\"position:relative;\"><input type=\"text\" id=\"question_"+num+"\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入题目内容！\"/></label><font>[单选]</font></dt><dd><label>选项1：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_1\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd><label>选项2：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_2\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd><label>选项3：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_3\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd><label>选项4：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_4\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd class=\"operate\"><em class=\"appendItem\">+插入选项</em><span><a class=\"moveup\"><i class=\"setTop\"></i>上移</a><a class=\"movedown\"><i class=\"cancelTop\"></i>下移</a><a class=\"no_cz\"><i class=\"batch_revise\"></i>修改</a><a class=\"del\"><i class=\"delete\"></i>删除</a></span></dd></dl>";
		$("div.choice").append(template);
		bindEventPlace("question_"+num);
		bindEventPlace("item_"+num+"_1");
		bindEventPlace("item_"+num+"_2");
		bindEventPlace("item_"+num+"_3");
		bindEventPlace("item_"+num+"_4");
	});
	
	/**
	 * 添加多选题
	 */
	$("#addMultiple").click(function(){
		var dlString = $(".choice dl:last").find("dt").text();
		var num = 1;
		if(dlString){
			num = dlString.substring(0,dlString.indexOf("、"));
			num++;
		}
		var template = "<dl class=\"add_xz\"><input type=\"hidden\" name=\"typeId\" value=\"2\"/><dt><label>"+num+"、题干：</label><label style=\"position:relative;\"><input type=\"text\" id=\"question_"+num+"\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入题目内容！\"/></label><font>[多选]</font></dt><dd><label>选项1：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_1\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd><label>选项2：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_2\" class=\"formText\" placeholder=\"请输入选项内容！\" maxlength=\"200\"/></label></dd><dd><label>选项3：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_3\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd><label>选项4：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+num+"_4\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd><dd class=\"operate\"><em class=\"appendItem\">+插入选项</em><span><a class=\"moveup\"><i class=\"setTop\"></i>上移</a><a class=\"movedown\"><i class=\"cancelTop\"></i>下移</a><a class=\"no_cz\"><i class=\"batch_revise\"></i>修改</a><a class=\"del\"><i class=\"delete\"></i>删除</a></span></dd></dl>";
		$("div.choice").append(template);
		bindEventPlace("question_"+num);
		bindEventPlace("item_"+num+"_1");
		bindEventPlace("item_"+num+"_2");
		bindEventPlace("item_"+num+"_3");
		bindEventPlace("item_"+num+"_4");
	});
	/**
	 * 添加问答题
	 */
	$("#addQuestions").click(function(){
		var dlString = $(".choice dl:last").find("dt").text();
		var num = 1;
		if(dlString){
			num = dlString.substring(0,dlString.indexOf("、"));
			num++;
		}
		var template = "<dl class=\"add_xz\"><input type=\"hidden\" name=\"typeId\" value=\"3\"/><dt><label>"+num+"、题干：</label><label style=\"position:relative;\"><input type=\"text\" id=\"question_"+num+"\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入题目内容！\"/></label><font>[问答]</font></dt><dd class=\"wd\"><textarea class=\"formTextarea\" rows=\"5\"></textarea></dd><dd class=\"operate\"><span><a class=\"moveup\"><i class=\"setTop\"></i>上移</a><a class=\"movedown\"><i class=\"cancelTop\"></i>下移</a><a class=\"no_cz\"><i class=\"batch_revise\"></i>修改</a><a class=\"del\"><i class=\"delete\"></i>删除</a></span></dd></dl>";
		$("div.choice").append(template);
		bindEventPlace("question_"+num);
	});
	
	/**
	 * 添加选项
	 */
	$(".appendItem").live("click",function(){
		var prevDD = jQuery(this).parent("dd").prev("dd");
		var text = prevDD.find("label:first").text().replace("选项","").replace("：","");
		text = ++text;
		if(text>20){
			art.dialog.alert("对不起，选项目前最多只支持20个。");
		}else{
			var num = jQuery(this).parentsUntil("div").find("dt").find("label:first").text().replace("、题干：","");
			jQuery(this).parent("dd").prev("dd").after("<dd><label>选项"+text+"：</label><label style=\"position:relative;\"><input type=\"text\" class=\"formText\" id=\"item_"+num+"_"+text+"\" maxlength=\"200\" placeholder=\"请输入选项内容！\"/></label></dd>");
			bindEventPlace("item_"+num+"_"+text);
		}
	});
	/**
	 * 删除
	 */
	$("a.del").live("click",function(){
		var $obj = jQuery(this).parents("dl");
		var questionId = $obj.find("input[name='id']").val();
		if(questionId==null||questionId==""){
			updateIndex($obj);
			$obj.remove();	
		}else{
			art.dialog.confirm("确认要删除吗？",function(){
				$.ajax({
					url:basePath+"question/question_delQuestion.action",
					type:"post",
					data:{"questionId":questionId},
					dataType:"text",
					success:function(result){
						if(result){
							updateIndex($obj);
							$obj.remove();
						}
					}
				});
			});
		}
	});
	/**
	 * 删除后更新索引
	 */
	function updateIndex($obj){
		var $allDl = $obj.nextAll("dl");  //得到索引的dl索引
		$allDl.each(function(){
			var that = jQuery(this);
			var id = that.find("input[name='id']").val();
			if(id==null||id==""){   //新增的问题
				var text = that.find("dt").find("label:first").text();
				var num = text.substring(0,text.indexOf("、"));
				num--;
				that.find("dt").find("label:first").text(num+text.substring(text.indexOf("、"),text.length));
			}else{     //已经保存的问题
				var text = that.find("dt").text();
				var num = text.substring(0,text.indexOf("、"));
				num--;
				that.find("dt").find("label:first").text(num+text.substring(text.indexOf("、"),text.length));
			}
		});
	}
	/**
	 * 修改操作
	 */
	$("a.edit").live("click",function(){
		var $currDl = $(this).parents("dl");
		$currDl.addClass("add_xz");
		var currString = $currDl.find("dt").html();
		var currNum = currString.substring(0,currString.indexOf("、")); //题干号
		var questionId = $currDl.find("input[name='id']").val(); //问题主键
		var typeId = $currDl.find("input[name='typeId']").val(); //类型
		var name = $currDl.find("input[name='name']").val();//名称
		var texts = new Array();
		if(typeId!=3){
			$currDl.find("dd:not(:last)").each(function(){
				texts.push($(this).find("label").text());
			});
		}
		var template = generTemplate(questionId,name,typeId,currNum,texts); //产生模板
		$currDl.html(template);
	});
	/**
	 * 绑定上移事件
	 */
	$("a.moveup").live("click",function(){
		var $currDl = jQuery(this).parents("dl");
		var $prevDl = jQuery(this).parents("dl").prev("dl");
		if($prevDl.html()==null||$prevDl.html()==""){
			art.dialog.alert("已经是最前一题！");
			return;
		}else{
			exchangeContent($prevDl,$currDl);
		}
	});
	/**
	 * 下移
	 */
	$("a.movedown").live("click",function(){
		var $currDl = jQuery(this).parents("dl");
		var $nextDl = jQuery(this).parents("dl").next("dl");
		if($nextDl.html()==null||$nextDl.html()==""){
			art.dialog.alert("已经是最后一题");
			return;
		}else{
			exchangeContent($currDl,$nextDl);
		}
	});
	
	/**
	 * 上移、下移交换内容
	 */
	function exchangeContent($prevDl,$nextDl){
		var prevId = $prevDl.find("input[name='id']").val();
		var nextId = $nextDl.find("input[name='id']").val();
		var prevNum;
		var prevString;
		var nextNum;
		var nextString;
		/**
		 * 得到上一题题干头部序号、头部内容
		 */
		if(prevId==null || prevId==""){  
			prevString = $prevDl.find("dt").find("label:first").text();
			prevNum = prevString.substring(0,prevString.indexOf("、"));
		}else{
			prevString = $prevDl.find("dt").html();
			prevNum = prevString.substring(0,prevString.indexOf("、"));
		}
		/**
		 * 得到下一题题干头部序号、头部内容
		 */
		if(nextId==null || nextId==""){
			nextString = $nextDl.find("dt").find("label:first").text();
			nextNum = nextString.substring(0,nextString.indexOf("、"));
		}else{
			nextString = $nextDl.find("dt").html();
			nextNum = nextString.substring(0,nextString.indexOf("、"));
		}
		/**
		 * 交换上一题、下一题序号：内容不变
		 */
		if(prevId==null || prevId==""){
			$prevDl.find("dt").find("label:first").html(nextNum+prevString.substring(prevString.indexOf("、"),prevString.length));
		}else{
			$prevDl.find("dt").html(nextNum+prevString.substring(prevString.indexOf("、"),prevString.length));
		}
		if(nextId==null || nextId==""){
			$nextDl.find("dt").find("label:first").html(prevNum+nextString.substring(nextString.indexOf("、"),nextString.length));
		}else{
			$nextDl.find("dt").html(prevNum+nextString.substring(nextString.indexOf("、"),nextString.length));
		}
		/**
		 * 判断样式，如果上一题有编辑样式，添加到下一题上，同理！
		 */
		var prevExistClass = $prevDl.hasClass("add_xz");
		var nextExistClass = $nextDl.hasClass("add_xz");
		if(prevExistClass){
			$nextDl.addClass("add_xz");
		}else{
			$nextDl.removeClass("add_xz");
		}
		if(nextExistClass){
			$prevDl.addClass("add_xz");
		}else{
			$prevDl.removeClass("add_xz");
		}
		var template = $prevDl.html();
		$prevDl.html($nextDl.html());
		$nextDl.html(template);
	}
	/**
	 * 绑定添加试题
	 */
	$("a.addQuestion").live("click",function(){
		var name = jQuery(this).parents("dl.add_xz").find("dt").find("input").val().trim(); //名称
		var $obj = jQuery(this).parents("dl.add_xz");
		if(!name){
			art.dialog.alert("请输入题目的题干内容！");
			return;
		}
		$(".choice dl").each(function(){
			var questionTitle = $(this).find("input[name='name']").val();
			if(name==questionTitle){
				art.dialog.alert("题干信息不能重复！");
				return ;
			}
		});
		var typeId= jQuery(this).parents("dl.add_xz").find("input[name='typeId']").val(); //类型
		var prevDL = jQuery(this).parents("dl.add_xz").prev("dl");
		var orderList = 1;
		while(prevDL.html()==null || prevDL.html()=="" || prevDL.find("input[name='orderList']").val()==null){
			prevDL = prevDL.prev("dl");
			orderList++;
		}
		orderList = parseInt(orderList) + parseInt(prevDL.find("input[name='orderList']").val());
		var questionnaireId = $("#questionnaireId").val();
		var param={
				"question.name":name,
				"question.typeId":typeId,
				"question.orderList":orderList,
				"question.questionnaire.id":questionnaireId
		};
		if(typeId==1 || typeId==2){
			var dds = jQuery(this).parents("dl.add_xz").find("dd:not(:last)");
			var count = 0;
			for(var i=0;i<dds.length;i++){
				var value = dds.eq(i).find("input[class='formText']").val();
				if(value!=null && value!="" && value.trim()!=""){
					param['question.items['+count+'].content']=dds.eq(i).find("input[class='formText']").val();
					param['question.items['+count+'].orderList']=dds.eq(i).find("label:first").text().replace("选项","").replace("：","");
					count++;
				}
			}
			
			if(count<1){
				art.dialog.alert("至少一个选项不为空！");
				return ;
			}
		}
		$.ajax({
			type:"post",
			url:basePath+"question/addDone.action",
			data:param,
			dataType:"json",
			success:function(data){
				$obj.html(generTemplate($obj,data));
			}
		});
	});
});

/**
 * 上一步
 */
function lastStep(){
	var questionnaireId = $("#questionnaireId").val();
	var type = $("#type").val();
	if(questionnaireId){
		window.location.href = basePath + "logined/question/createNaire.jsp?type="+type+"&paperId="+questionnaireId;
	}else{
		window.location.href = basePath + "logined/question/createNaire.jsp?type="+type;
	}
	
}
/**
 * 取消
 */
function goback(){
	var type = $("#type").val();
	if(type=="save"){  //删除试卷
		art.dialog.confirm('确认要取消吗？<br/>已编辑的内容将不会保存。', function() {
			var ids = $("#questionnaireId").val();
			$.ajax({
				url : basePath + "question/question_deleteQuestion.action?tjId="+ ids,
				type : "post",
				dataType : "html",
				success : function(data) {
					if (data == 1) {
						window.location.href = basePath + "logined/question/questionnaire.jsp";
					} else {
						art.dialog.alert("删除失败！");
					}
				}
			});
		});
	}else{
		window.location.href = basePath + "logined/question/questionnaire.jsp";
	}
}

/**
 * 预览
 */
function view(){
	var url= basePath + "question/viewNaire.action?type=win&paperId="+$("#questionnaireId").val();
	art.dialog.open(url, {
	    id : "viewNaire",
	    title : "预览问卷",
	    lock : true,
	    width:800,
	    height:450,
	    drag:false,
	    opacity: 0.3,
	    close : function(){
	    	return true;
	    },
	    cancel : function(){
	    	return true;
	    }
	});
	
}
/**
 * 保存
 */
function save(a){
	var $dls = $("div.choice").find("dl");  
	var param ={};
	var questionnaireId = $("#questionnaireId").val();
	for(var i = 0 ; i < $dls.length ; i++){
		var $dl = jQuery($dls[i]);
		if($dl.hasClass("add_xz")){ //新建状态或者编辑状态问题
			param['list['+i+'].name'] = $dl.find("dt").find("input").val();
			param['list['+i+'].typeId'] = $dl.find("input[name='typeId']").val();
			var orderList = $dl.find("dt").find("label:first").text().substring(0,$dl.find("dt").find("label:first").text().indexOf("、"));
			param['list['+i+'].orderList'] = orderList;
			param['list['+i+'].questionnaire.id'] = questionnaireId;
			if($dl.find("input[name='typeId']").val()!=3){
				var dds = $dl.find("dd:not(:last)");
				var count = 0;
				for(var j=0;j<dds.length;j++){
					var value = dds.eq(j).find("input").val();
					if(value!=null && value!="" && value.trim()!=""){
						param['list['+i+'].items['+count+'].content']=value;
						param['list['+i+'].items['+count+'].orderList']=dds.eq(j).find("label:first").text().replace("选项","").replace("：","");
						count++;
					}
				}
				if(count==0){
					art.dialog.alert("选择题至少要有一个选项!");
					return ;
				}
			}
		}else{  //需要更新的问题
			param['list['+i+'].name'] = $dl.find("input[name='name']").val();
			param['list['+i+'].typeId'] = $dl.find("input[name='typeId']").val();
			param['list['+i+'].orderList'] = $dl.find("dt").text().substring(0,$dl.find("dt").text().indexOf("、"));
			param['list['+i+'].questionnaire.id'] = questionnaireId;
			if($dl.find("input[name='typeId']").val()!=3){
				var dds = $dl.find("dd:not(:last)");
				for(var j=0;j<dds.length;j++){
					var value = dds.eq(j).find("input").val();
					if(value!=null && value!="" && value.trim()!=""){
						param['list['+i+'].items['+j+'].content']=dds.eq(j).text();
						param['list['+i+'].items['+j+'].orderList']=j;
					}
				}
			}
		}
	}
	if(validate(param)){
		$.ajax({
			url:basePath+"question/question_save.action?questionnareId="+questionnaireId,
			type:"post",
			data:param,
			dataType:"text",
			success:function(result){
				if(result){
					if(a==1){
						var type = $("#type").val();
						window.location.href= basePath + "logined/question/releaseQuestion.jsp?type="+type+"&id="+$("#questionnaireId").val();;
					}
					else{
						art.dialog.alert("保存成功！",function(){
						    window.location.reload();
						});
					}
					
				}
			}
		});
	}
}
/**
 * 验证
 */
function validate(param){	
	var $dls = $("div.choice").find("dl");  
	var names = new Array();
	var a;
	var index;
	for(var i = 0 ; i < $dls.length ; i++){
		var $dl = jQuery($dls[i]);
		if($dl.hasClass("add_xz")){ //新建状态或者编辑状态问题
		    a =$dl.find("input[name='name']").val()||$dl.find("dt").find("input").val();
		    if(!$.trim(a)){
		    	art.dialog.alert("第"+(i+1)+"题题干不能为空！");
		    	return false;
		    }
		    index=$.inArray(a, names);
		    if(index>-1){
				art.dialog.alert("第"+(index+1)+"题和第"+(i+1)+"题题干重复！");
				return false;
			}
			names.push(a);
		}else{  //需要更新的问题
			a=$dl.find("input[name='name']").val();
			 if(!$.trim(a)){
		    	art.dialog.alert("第"+(i+1)+"题题干不能为空！");
		    	return false;
		    }
			index=$.inArray(a, names);
		    if(index>-1){
		    	art.dialog.alert("第"+(index+1)+"题和第"+(i+1)+"题题干重复！");
				return false;
			}
			names.push(a);
		}
	}
	if(names.length==0){
		art.dialog.alert("至少需要有一个题目！");
	}
	return true;

}
/**
 * 绑定提示事件
 */
function bindEventPlace(id){
	funPlaceholder(document.getElementById(id));
}
/**
 * 生成编辑模板
 * @param questionId
 * @param name
 * @param typeId
 * @param currNum
 * @param texts
 * @returns {String}
 */
function generTemplate(questionId,name,typeId,currNum,texts){
	if(typeId==3){
		var template = "<input type=\"hidden\" value=\""+questionId+"\" name=\"id\"><input type=\"hidden\" name=\"typeId\" value=\""+typeId+"\"/><dt><label>"+currNum+"、题干：</label><label style=\"position:relative;\"><input type=\"text\" id=\"question_"+currNum+"\" value=\""+name+"\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入题目内容！\"/></label><font>[问答]</font></dt><dd class=\"wd\"><textarea class=\"formTextarea\" rows=\"5\"></textarea></dd><dd class=\"operate\"><span><a class=\"moveup\"><i class=\"setTop\"></i>上移</a><a class=\"movedown\"><i class=\"cancelTop\"></i>下移</a><a class=\"no_cz\"><i class=\"batch_revise\"></i>修改</a><a class=\"del\"><i class=\"delete\"></i>删除</a></span></dd>";
		return template;
	}else if(typeId==2){
		var template = "<input type=\"hidden\" value=\""+questionId+"\" name=\"id\"><input type=\"hidden\" name=\"typeId\" value=\""+typeId+"\"/><dt><label>"+currNum+"、题干：</label><label style=\"position:relative;\"><input type=\"text\" id=\"question_"+currNum+"\" value=\""+name+"\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入题目内容！\"/></label><font>[多选]</font></dt>";
		for(var i = 0 ;i < texts.length;i++){
			template = template + "<dd><label>选项"+(i+1)+"：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+currNum+"_"+(i+1)+"\" class=\"formText\" maxlength=\"200\" value=\""+texts[i]+"\" placeholder=\"请输入选项内容！\"/></label></dd>";
		}
		template = template + "<dd class=\"operate\"><em class=\"appendItem\">+插入选项</em><span><a class=\"moveup\"><i class=\"setTop\"></i>上移</a><a class=\"movedown\"><i class=\"cancelTop\"></i>下移</a><a class=\"no_cz\"><i class=\"batch_revise\"></i>修改</a><a class=\"del\"><i class=\"delete\"></i>删除</a></span></dd>";
		return template;
	}else if(typeId==1){
		var template = "<input type=\"hidden\" value=\""+questionId+"\" name=\"id\"><input type=\"hidden\" name=\"typeId\" value=\""+typeId+"\"/><dt><label>"+currNum+"、题干：</label><label style=\"position:relative;\"><input type=\"text\" id=\"question_"+currNum+"\" value=\""+name+"\" class=\"formText\" maxlength=\"200\" placeholder=\"请输入题目内容！\"/></label><font>[单选]</font></dt>";
		for(var i = 0 ;i < texts.length;i++){
			template = template + "<dd><label>选项"+(i+1)+"：</label><label style=\"position:relative;\"><input type=\"text\" id=\"item_"+currNum+"_"+(i+1)+"\" class=\"formText\" maxlength=\"200\" value=\""+texts[i]+"\" placeholder=\"请输入选项内容！\"/></label></dd>";
		}
		template = template + "<dd class=\"operate\"><em class=\"appendItem\">+插入选项</em><span><a class=\"moveup\"><i class=\"setTop\"></i>上移</a><a class=\"movedown\"><i class=\"cancelTop\"></i>下移</a><a class=\"no_cz\"><i class=\"batch_revise\"></i>修改</a><a class=\"del\"><i class=\"delete\"></i>删除</a></span></dd>";
		return template;
	}
}
