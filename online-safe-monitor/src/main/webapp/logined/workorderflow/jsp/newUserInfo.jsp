<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="../../../common/taglibs.jsp"%>
 <table border="0" class="inputTable1 mt10" style="table-layout:fixed">
    <tr>
      <th><span class="requireField">*</span><label>用户姓名</label></th>
      <td><input name="" value="" class="formText" type="text"  id="uname"  maxlength="6"  valid="required" errmsg="customerCall.uname_not_null"/></td>
      <th><label>手机号码</label></th>
      <td><input style="width:80%" name=""  value="<s:property value="#request.phoneOne" />" class="formText" type="text" id="uphone" maxlength="12" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" readonly /></td>
      <th><label>座机号码</label></th>
      <td><input style="width:80%" name="" value="<s:property value="#request.phoneTwo" />" class="formText" type="text" id="umobile" maxlength="13" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" readonly /></td>
    </tr>
    <tr>
	  <th><label>用户性别</label></th>
      <td><select id="gender" style="width:54%">
      <option id="male" value="1">男</option>
      <option id="female" value="0">女</option>
      </select></td>
      <th><span class="requireField">*</span><label>用户地址</label></th>
      <td colspan="3" ><input name="" id="uaddress" value="" class="formText" maxlength="200" type="text"  style="width:94%"  valid="required" errmsg="customerCall.uaddress_not_null"/></td>  
    </tr>
  </table>
  <%--
  <div class="buttonArea">
  <input name="" class="formButton_green" value="保 存" type="button"  id="addProduct"/>
  </div>--%>