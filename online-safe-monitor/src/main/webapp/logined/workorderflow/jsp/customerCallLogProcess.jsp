<!-- 工单进度页面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${ctx}flat/css/works.css" rel="stylesheet" type="text/css" />
<h3 class="small_title">处理进度</h3>
<div class="steps">
								<table cellspacing="0" cellpadding="0" border="0">
                                                        <tbody>
                                                             <tr>
                                        <c:forEach items="${processStates }" var="process" varStatus="vs">
                                        	<td>
                                        		<p class="${process.classs }" >${process.name }</p>
                                        	</td>
                                        </c:forEach>                     
										
										
								 </tr>
                                                        </tbody>
                                                   </table>
								<table cellspacing="0" cellpadding="0" border="0">
                                                        <tbody>
                                                             <tr>
                                        <c:forEach items="${processStates }" var="process" varStatus="vs">
                                            <c:if test="${process.classs=='' }">                 
											<td><p><p><em class="icon_green"></em><em class="arrow_green"></em></p></p></td>
											</c:if>
											<c:if test="${process.classs=='red' }">
											<td><p><em class="icon_orange"></em></p></td>
											</c:if>
										</c:forEach>
								 </tr>
                                                        </tbody>
                                                   </table>
								<table cellspacing="0" cellpadding="0" border="0">
                                                        <tbody>
                                                             <tr>
                                                             <c:forEach items="${processStates }" var="process">
										<td>
												<p class="f12">${process.user }</p>
												<p class="f12">${process.time }</p>
										</td>
										</c:forEach>
										
								 </tr>
                                                        </tbody>
                                                   </table>
						</div>
