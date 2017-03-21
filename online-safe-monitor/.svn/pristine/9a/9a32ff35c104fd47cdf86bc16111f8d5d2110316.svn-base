var crmMap = new HashMap();
var defaultSelectId;
var tempIds = '';
var first = true;
var iSsearch = false;

$(document).ready(function(){
	defaultSelectId = $("#defaultSelectId").val();
	searchCrm();
	
	$("#btnSearch").bind("keyup", function() {
		searchCrm();
		$("#btnSearch").focus();

		setTimeout(function() {
			$("#btnSearch").focus();
		}, 200);

	});
});

function searchCrm(){
	var searchName = $("#btnSearch").val();
	if(searchName!=null&&searchName!=''){
		isSearch = true;
	}else{
		isSearch = false;
	}
	var params = {
		"searchName":searchName
	};
	var setting = {
			//是否异步加载，默认为false
//			async: {
//				enable: true, //同步或异步加载
//				otherParam: params,//传递参数
//				url:basePath+"crm/getCRMtree.action" //异步加载链接地址，可用getUrl方法加载
//			},
			check: {
				enable : true,
	            chkboxType : {
	                "Y" : "ps",
	                "N" : "ps"
	            }
			},
			data: {
				simpleData : {
					enable : true
				},
				showTitle : true, //是否显示节点title信息提示 默认为true
				key : {
					title : "title" //设置title提示信息对应的属性名称 也就是节点相关的某个属性
				}
			},
			view: {
//				expandSpeed: "fast",
				showIcon: true
			},
			callback: {
//				 beforeClick: null,//点击前回调函数
//				beforeExpand: null, //父节点展开之前回调函数
//				onAsyncSuccess:loadSuccess,//加载成功回调函数
//				onAsyncError: null,	//加载失败回调函数
				onCheck:zTreeOnClick
			}
		};

	$.ajax({
		url : basePath+"crm/getCRMtree.action", 
		data:{"searchName":searchName},
		dataType : "text",
		type : "post",
		success : function(data){
			var json = eval("("+data+")");
			var treeObj = $.fn.zTree.init($("#crmUserTree"), setting, json);
			//展开树节点
			treeObj.expandAll(true);
			//勾选已选择的节点
			if(first){
				initDefaultSelected();
			}else{
				initSelected();
			}
		}
	});
	
	
	
	


}


function zTreeOnClick(event, treeId, treeNode){
	
	tempIds = '';
	 if(!treeNode.checked){
		 var id = treeNode.id.substr(6);
//		 userMap.remove(id);
    }
	var treeObj = $.fn.zTree.getZTreeObj("crmUserTree");
	
    var nodes = treeObj.getCheckedNodes(true); //获取选中的节点
    
    //判断是否是通过搜索框查找
    if(isSearch){
    	if(!treeNode.checked){
    		var id = treeNode.id.substr(6);
    		crmMap.remove(id);
    	}else{
    		//如果是人员，则保存到map里面
    		var node = new TreeNode();
    		var id = treeNode.id.substr(6);
    		node.setId(id);
    		node.setData(treeNode.obj);
    		node.setName(treeNode.name);
    		crmMap.set(id, node);
    	}
    	art.dialog.data("crmMap", crmMap);
    	
    	return;
    }
    //若不是清空map
    crmMap.clear();
    //map重新加载treeNode
    for(var i=0;i<nodes.length;i++){
    	var node = nodes[i];
    	if(node.id.substr(0, 6)=="crmid_"){
    		var treeNode = new TreeNode();
    		var id = node.id.substr(6);
    		treeNode.setId(id);
    		treeNode.setData(node.obj);
            treeNode.setName(node.name);
            crmMap.set(id, treeNode);
            
//            tempIds += id + ',';
    	}
    }
//    var defaultSelectId = $("#defaultSelectId").val();
//    $("#defaultSelectId").val(defaultSelectId+tempIds);
    
    art.dialog.data("crmMap", crmMap);
}

/**
 * 初始化已经选择的人员
 */
function initDefaultSelected() {
	first = false;
	var selectId = defaultSelectId;
    if ("" != selectId) {
        var ids = selectId.split(",");
        var treeObj = $.fn.zTree.getZTreeObj("crmUserTree");

        for ( var i = 0; i < ids.length; i++) {
        	if(ids[i]!=''){
        		var node = treeObj.getNodeByParam("id","crmid_"+ids[i]);
        		treeObj.checkNode(node, true, true);
        	}
        }
        var nodes = treeObj.getCheckedNodes(true); //获取选中的节点
        for(var i=0;i<nodes.length;i++){
        	var node = nodes[i];
        	if(node.id.substr(0, 6)=="crmid_"){
        		var treeNode = new TreeNode();
        		var id = node.id.substr(6);
        		treeNode.setId(id);
        		treeNode.setData(node.obj);
                treeNode.setName(node.name);
                crmMap.set(id, treeNode);
        	}
        }
        
        art.dialog.data("crmMap", crmMap);
    }
}

/**
 * 根据crmMap判断该对象是否被选择
 */
function initSelected() {
	crmMap.forEach(function(value, key) {
        var treeObj = $.fn.zTree.getZTreeObj("crmUserTree");
        var tempId = value.Id;
        var nodes = null;
        nodes = treeObj.getNodesByParam("id", "crmid_" + tempId, null);
        for ( var j in nodes) {
	        treeObj.checkNode(nodes[j], true, true);
	        var treeNode = new TreeNode();
	        var id = nodes[j].id.substr(6);
	        treeNode.setId(id);
	        treeNode.setData(nodes[j].obj);
	        treeNode.setName(nodes[j].name);
	        crmMap.set(id, treeNode);
        }
        
    });
}

