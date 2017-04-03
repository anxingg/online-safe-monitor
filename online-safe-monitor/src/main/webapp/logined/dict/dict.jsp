<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据字典设置</title>
<jsp:include page="../../common/flatHead.jsp"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css?version=${version}"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css?version=${version}"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css?version=${version}"/>
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/flat/plugins/tree/skins/tree_default.css" type="text/css" />
<link href="${ctx}/flat/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/logined/dict/dict.js"></script>
</head>

<body>
<input type="hidden" id="sysTag" value='2'/>
<input type="hidden" id="sysTagItem" value='${paramValues.sysTag[0]}'/>
<input type="hidden" id="infoType" />
<input type="hidden" id="SysTypeId" />
<input type="hidden" id="SystypeValue" />
<input type="hidden" id="sid" />
<div class="bread-line">
	<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">系统管理</a>&gt;&nbsp;数据字典
</div>
<div class="mainpage">
  <div class="leftMenu">
    <div class="service-menu">
      <h1>设置列表</h1>
        <div class="menu-cont"  id="sysTypeList" style="height: 0px !important;display:none;">
        
       </div>
       <div class="zTreeDemoBackground">
			<ul id="groupUserTree" class="ztree">
			</ul>
		</div>
    </div>
  </div>
  <div class="list">
  <div class="searchArea">
      <ul>
          <li>
           <div class="fButton greenBtn" id="addType">
                   <span class="add">新增</span>
                  </div>
                  <div class="fButton orangeBtn" id="deleteType">
                    <span class="delete" >删除</span>
                  </div>
              </li>
          </ul>
    </div>
    <table cellpadding="0" cellspacing="0"  class="pretty dataTable">
      <thead>
        <tr>
          <th class="chk"><input id="total"  type="checkbox" /></th>
          <th class="num" style="width:60px;">序号</th>
          <th style="width:100%">名称</th>
          <th class="right_bdr0" style="width:80px;">操作</th>
        </tr>
      </thead>
      <tbody  id="types">
      </tbody>
    </table>
  </div>
</div>
</body>
</html>