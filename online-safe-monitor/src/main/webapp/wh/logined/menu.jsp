<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@include file="/ninclude/import.jsp"%>
			<div class="l_menu">
        	<ul id="showmenu">
				<c:forEach var="moduleInfo" items="${showModuleList}" varStatus="menu">
				<c:choose>
					<c:when test="${moduleInfo.moduleLevel==2}">
						<c:if test="${menu.index!=1}">
							</dl></li>
						</c:if>
						<li><h2>${moduleInfo.moduleName}</h2><dl>
					</c:when>
					<c:when test="${moduleInfo.moduleLevel==3}">	
							<dd>
								<c:if test="${fn:startsWith(moduleInfo.url, 'http://')}">
									<a target="_blank" href="${moduleInfo.url}" style="width:240px">${moduleInfo.moduleName}</a>
								</c:if>
								<c:if test="${!fn:startsWith(moduleInfo.url, 'http://')}">
									<a target="mainIframe" href="${ctx}${moduleInfo.url}" style="width:240px">${moduleInfo.moduleName}</a>
								</c:if>
							</dd>
							<c:if test="${menu.last==true}"></dl></li></c:if>
					</c:when>
					</c:choose>	
						
				</c:forEach>
				            </ul>
        </div>
				    <script type="text/javascript">
  		
  	 $(document).ready(function() {
		 $(".l_menu li h2").click(function(){
				if($(this).next().is(":visible"))
						$(this).parent().removeClass("current");
					else
						$(this).parent().addClass("current"); 
		});

         $("#showmenu a").click(function(){
        	 $("#showmenu a").parents("dd").removeClass("current");
        	 $("#showmenu a").parents("li").removeClass("current");
			 $(this).parents("dd").addClass("current");
			 $(this).parents("li").addClass("current");
         });
			
			
	});
	
	/**
	 * 选中指定的菜单项目（添加选中样式）
	 * @param liname li标签级别对应的名称（实际上是li标签下的h2标签中的名称）
	 * @param ddname dd标签级别对应的名称（实际上是dd标签下的a标签中的名称）
	 */
	function selectdd(liname, ddname){
		var liindex = -1;
		var ddindex = -1;
		var h2s = $(".l_menu ul li h2");
		for(var i=0; i<h2s.length; i++){
			if($.trim(h2s.eq(i).html()) == liname){
				liindex = i;
				break;
			}
		}
		if(liindex != -1){
			var as = $(".l_menu ul li").eq(liindex).find("dl dd a");
			for(var i=0; i<as.length; i++){
				if($.trim(as.eq(i).html()) == ddname){
					ddindex = i;
					break;
				}
			}
			if(ddindex != -1){
				var $currentdd = $(".l_menu ul li[class='current'] dl dd[class='current']").eq(0);
				if($currentdd != null && $currentdd != undefined){
					$currentdd.removeClass("current");
				}
				$(".l_menu ul li").eq(liindex).addClass("current");
				$(".l_menu ul li").eq(liindex).find("dl dd").eq(ddindex).addClass("current");
			}
		}
	}

    </script>