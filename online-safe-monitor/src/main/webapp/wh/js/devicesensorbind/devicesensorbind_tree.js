var callbackFunc;//回调函数
function zTreeOnCheck(event, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("groupUserTree");
    var node = treeObj.getNodeByTId(treeNode.tId);
    var treeNode=new TreeNode();
    var id=node.id.substr(4);
    var pid=node.pId.substr(4);
    treeNode.setId(id);
    treeNode.setData(pid);
    treeNode.setName(node.name);
    treeNode.setType(node.id.substr(0,3));
    callbackFunc(treeNode);
};
/**
 * 根据类型列表选择群组
 * @param t
 */
function openCollectDeviceTree(callback) {
	//ztree设置
	callbackFunc=callback;
    var setting = 
    {
        check: {enable: false,chkboxType: {"Y":"ps", "N":"ps"}},
        view: {dblClickExpand: false},
        data: { simpleData: { enable: true},
	        	showTitle:true, //是否显示节点title信息提示 默认为true
	            key: {
	            		title:"title" //设置title提示信息对应的属性名称 也就是节点相关的某个属性
	            	 }
        	  },
        callback: {onClick: zTreeOnCheck}
    };
    var url=basePath + "devicesensorbind/devicesensorbind_createSearchTree.action";
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        success: function(data) {
            var dataTemp = new Array();
            for (var i = 0; i < data.length; i++) {
                if (data[i].pId != "gid_-1") {
                    dataTemp.push(data[i]);
                }
            }
            var treeObj = $.fn.zTree.init($("#groupUserTree"), setting, dataTemp);
        }
    });
}
