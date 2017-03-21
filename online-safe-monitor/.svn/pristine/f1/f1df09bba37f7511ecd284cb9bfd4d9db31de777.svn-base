<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设计流程--向导</title>
<jsp:include page="../../../common/flatHead.jsp"/>
<link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/css/myworks.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/logined/workflow/js/editProcess.js"></script>
</head>
<body>
	<input type="hidden" id="processId" value="${processAttribute.id}"/>
	<input type="hidden" id="processState" value="${processAttribute.processState}"/>
	<input type="hidden" id="formId" value="${processAttribute.formId}"/>
<div class="mt10">
		<div class="pageTitle"><em class="iconList mr10 ml10" style="font-size:14px">${processAttribute.processName} <span class="ml20 nor f12">该流程下共有<i id="instanceNum" class="f16 red">${processAttribute.instanceNum}</i>个工作.</span></em></div>
		<ul class="guide-block clearfix">
				<LI id="flow" class="guide-block-item ">
						<DL>
								<DT>定义流程</DT>
								<DD class="triangle_1">
										<p>用于定义流程的基本属性</p>
										<p>可配置内容包括：<B>流程名称</B>，<B>流程类型</B>等</p>
										<p><FONT color=#91271e>	
											温馨提醒：删除操作只能删除已停用的流程或者未发布的流程，对于已停用的流程请确保当前没有在用的流程实例
										</FONT></p>
										<UL class="guide-spec-btns clearfix">
												<LI>
														<BUTTON id="flow_creat" class="guide-spec-btn " type=submit ><I class=creat></I>新建</BUTTON>
												</LI>
												<LI>
														<BUTTON id="flow_edit" class="guide-spec-btn " type=submit  ><I class=edit></I>编辑</BUTTON>
												</LI>
												<!--
												<LI>
														<BUTTON id=flow_import class="guide-spec-btn " type=submit onclick="javascript:parent.location.href='弹窗--系统设置--工作流设置--设计流程--导入流程.html'"><I class=import></I>导入</BUTTON>
												</LI>
												<LI>
														<BUTTON id=flow_export class="guide-spec-btn " type=submit ><I class=export></I>导出</BUTTON>
												</LI>
											-->
												<!-- 
												<LI>
														<BUTTON id="flow_view" class="guide-spec-btn " type=submit ><I class=formView></I>预览表单</BUTTON>
												</LI>
												 -->
												<!--
												<LI>
														<BUTTON id=flow_empty class="guide-spec-btn " type=submit ><I class=empty></I>清空</BUTTON>
												</LI>
											-->
												<LI>
														<BUTTON id="flow_delete" class="guide-spec-btn " type=submit ><I class=delete></I>删除</BUTTON>
												</LI>
										</UL>
								</DD>
						</DL>
				</LI>
				<LI id=process class="guide-block-item" >
						<DL>
								<DT>设计流程</DT>
								<DD class="triangle_2">
										<p>用于设计流程在实际应用中的模型 </p>
										<p>可配置内容包括：<B>经办权限</B>，<B>节点属性</B>等 </p>
										<p><FONT color=#91271e>
											
											温馨提醒：请先定义流程步骤，否则将影响新建工作的使用
										
										</FONT></p>
										<UL class="guide-spec-btns clearfix">
												<LI>
														<BUTTON id="process_designer" class="guide-spec-btn"  type=submit><I class="designer"></I>流程设计器</BUTTON>
												</LI>
										
												<LI>
														<BUTTON id="node_designer" class="guide-spec-btn"  type=submit><I class="designer"></I>编辑流程节点属性</BUTTON>
												</LI>
												<LI>
														<BUTTON id="process_flowCheck" class="guide-spec-btn"  type=submit ><I class="flowCheck"></I>校验</BUTTON>
												</LI>
												<LI>
														<BUTTON id="process_view" class="guide-spec-btn " type=submit ><I class=formView></I>预览流程</BUTTON>
												</LI>
									
										</UL>
										<!--          <ul _tmplitem="2"  class='guide-common-btns clearfix'>                 </ul>  --></DD>
						</DL>
				</LI>
				<LI id="process" 
				     <c:if test='${(processAttribute.procsssDefinByJSON != null)&&(processAttribute.procsssDefinByJSON != "")}'>
				        class="guide-block-item" 
				  </c:if>
				    <c:if test='${(processAttribute.procsssDefinByJSON == null)||(processAttribute.procsssDefinByJSON == "")}'>
				        class="guide-block-item disabled"
				  </c:if> >
				
						<DL>
								<DT>流程管理</DT>
								<DD class="triangle_3">
										<p>用于管理已创建好的流程 </p>
										<p>可配置内容包括：<B>发布</B>，<B>停用</B>，<B>启用</B>等 </p>
										<c:if test='${(processAttribute.procsssDefinByJSON != null)&&(processAttribute.procsssDefinByJSON != "")}'>
										<p><FONT color=#91271e>
											该流程当前状态：<B>${processAttribute.processStateCN}</B>
										</FONT></p>
											<UL class="guide-spec-btns clearfix">
												
														<LI>
																<BUTTON id="deploy" class="guide-spec-btn " type=submit ><I class=creat></I>发布最新流程</BUTTON>
														</LI>
									
													<c:if test="${processAttribute.processState == 2}">
													<LI>
															<BUTTON id="stop" class="guide-spec-btn " type=submit ><I class=delete></I>停用</BUTTON>
													</LI>
													</c:if>
													<c:if test="${processAttribute.processState == 3}">
													<LI>
															<BUTTON id="start" class="guide-spec-btn " type=submit ><I class=export></I>启用</BUTTON>
													</LI>
													</c:if>
											</UL>
										</c:if>	
										<!--          <ul _tmplitem="2"  class='guide-common-btns clearfix'>                 </ul>  --></DD>
						</DL>
				</LI>
		</ul>
</div>
</body>
</html>