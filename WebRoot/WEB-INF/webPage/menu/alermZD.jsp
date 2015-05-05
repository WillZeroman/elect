<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>load</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
   </head>
  
  <body>
    <table width="100%" border="0" id="table8">
				<s:if test="#request.commonMsg!=null">
					<s:iterator value="#request.commonMsg" var="cm">
						<tr>
							<td style="HEIGHT:22px" align="left" width="40%">
							<%-- 	<s:property value="#cm.stationRun"/> --%>
								<!-- jstl标签 将html元素解析-->
								${cm.stationRun}
							</td>
						</tr>	
					</s:iterator>		
				</s:if>
				
	</table>
	<s:debug></s:debug>
  </body>
</html>