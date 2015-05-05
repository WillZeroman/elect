<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<title>角色权限管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript">
		  
		 function saveRole(){
		 
           document.Form2.role.value=document.Form1.role.value;
		   document.Form2.action="system/elecRoleAction_save.do";
		   document.Form2.submit();
		}
		
       
       function selectRole(){
          
          if(document.Form1.role.value=="0"){         
             document.Form1.action="system/elecRoleAction_home.do";
             document.Form1.submit();            
          }else{
            Pub.submitActionWithForm('Form2','system/elecRoleAction_edit.do','Form1');
          }
       }
		//全选  全不选
	   function checkAll(oper,name){
	   	  var select = document.getElementsByName(name);
	   	  for(var i=0;i<select.length;i++){
	   	  	 select[i].checked = oper.checked;
	   	  }
	   }
		
		</script>
	</HEAD>
		
	<body>
	 <s:form name="Form1" id="Form1"  method="post" cssStyle="margin:0px;">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
						<font face="宋体" size="2"><strong>角色管理</strong></font>
					</td>
				</tr>	
				<tr>
				   <td class="ta_01" colspan=2 align="right" width="100%"  height=10>
				   </td>
				</tr>		
				<tr>
					<td class="ta_01" align="right" width="35%" >角色类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="ta_01" align="left"  width="65%" >
					<s:select list="#request.systemList" id="role" name="role"
							  listKey="ddlCode" listValue="ddlName"
							  headerKey="0" headerValue="请选择"
							  cssClass="bg" cssStyle="width:180px"
							  onchange="selectRole()"
							  >				
					</s:select>
					
					<%--   <select name="role" class="bg" style="width:180px"  onchange="selectRole()">
						 <option value="0">请选择</option>
						 
						 <option value="1">系统管理员</option>
						 
						 <option value="2">高级管理员</option>
						 
						 <option value="3">中级管理员</option>
						 
						 <option value="4">业务用户</option>
						 
						 <option value="5">一般用户</option>
						 
						 <option value="6">普通用户</option>
						 
					  </select>   --%>
					 
					 
					   
					  
					</td>				
				</tr>
			    
			    <tr>
				   <td class="ta_01" align="right" colspan=2 align="right" width="100%"  height=10></td>
				</tr>
				
			</TBODY>
		  </table>
 </s:form>

<s:form name="Form2" id="Form2"  method="post" cssStyle="margin:0px;">
 
  <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
 <tr>
  <td>
   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left">权限分配</legend>
 	 <s:debug></s:debug>
     <table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">			 			  
				 <tr>
				 <td class="ta_01" colspan=2 align="left" width="100%" >
				 <%-- <s:property value="#request.xmlList"/>  --%>
				  <s:set value="%{''}" scope="request" var="parentCode"></s:set>
				  	<s:if test="%{#request.xmlList != null}">
				  		<s:iterator value="%{#request.xmlList}" var="xml">				  		
				  			<s:if test="%{#request.parentCode == #xml.parentCode}">
				  				
				  					<input type="checkbox" name="selectoper" value="<s:property value='%{#xml.code}'/>" >		 		
				  			 		<s:property value="%{#xml.name}"/>
				  			 	
				  			</s:if>
				  			<s:else>
				  				<br>
				  				<s:iterator begin="0" end="%{8-#xml.parentName.length()}" step="1">
						  				 	&nbsp;
						  		</s:iterator>
				  			 	<s:set value="%{#xml.parentCode}" scope="request" var="parentCode"></s:set>
				  			 		
				  			 			<s:property value="%{#xml.parentName}"/>:
				  			 		
				 			 		
					  			 		<input type="checkbox" name="selectoper" value="<s:property value='%{#xml.code}'/>" >
					  			 		<s:property value="%{#xml.name}"/>
				  			 				  			 	
				  			</s:else>
				  		
				  		</s:iterator>
				  	</s:if>				  

				</td>
				</tr>										
		 </table>	
        </fieldset>
	  </td>
	 </tr>					
  </table>
		    				    
	</s:form>
	</body>
</HTML>
