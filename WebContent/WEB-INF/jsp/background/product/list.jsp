<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../../common/common-css.jsp" %>
<%@include file="../../common/common-js.jsp" %>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath }/css/fenyecss.css" />

</head>
<body>
<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/product/query.action" method="post">
<table width="100%">
  <tr>
    <td height="30" background="${pageContext.servletContext.contextPath }/images/tab_05.gif"><table width="100%">
      <tr>
        <td width="12" height="30"><img src="${pageContext.servletContext.contextPath }/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%">
          <tr>
            <td width="46%" valign="middle"><table width="100%">
              <tr>
                <td width="5%"><div align="center"><img src="${pageContext.servletContext.contextPath }/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：产品管理-产品查询</td>
              </tr>
            </table></td>
           
        </table></td>
        <td width="16"><img src="${pageContext.servletContext.contextPath }/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  <td align="center">
  <!-- 这里的表单 name 必须是fenye -->
  	<div class="search_k" align="left">
		<fieldset class="search">
			<legend><img src="${pageContext.servletContext.contextPath }/images/search_btn.gif" align="middle"/>&nbsp;<span class="STYLE1" style="color: blue;">高级查找</span></legend>
			<div class="search_content">
				面值：<input type="text" name="parvalue" value="${param.parvalue}" style="height: 20px"/>　
				省份：<input type="text" name="province" value="${param.province}" style="height: 20px"/>
				运营商：<select name="operator" id="newType" style="width:150px">
					   		<option value="">--请选择--</option>
					   		<option value="联通" >联通</option>
					   		<option value="移动" >移动</option>
					   		<option value="电信" >电信</option>
	   	<%-- 	<option value="1" <c:if test="${newType == 1}">selected="selected"</c:if>>最新动态</option> --%>
	  				 		</select>
	  			是否显示（到微信公众号）：<select name="showable"  style="width:150px">
					   		<option value="">--请选择--</option>
					   		<option value="1" >是</option>
					   		<option value="0" >否</option>
	  				 		</select>
				<input type="submit" value="开始查询" class="input_btn_style1"/>&nbsp;&nbsp;
			</div>
		</fieldset>
	</div>
  </td>
  </tr>
  <tr>
    <td><table class="listtable" width="100%">
      <tr>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_12.gif">&nbsp;</td>
        <td><table class="ttab" width="100%" cellspacing="1" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="3%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" >
              <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()" />
            </td>
 
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">运营商</span></td>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif" ><span class="STYLE1">省份</span></td>
            <td width="12%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">面值</td>
            <td width="10%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">是否显示</td>
            <td width="24%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">价格区间</td>
            <td width="6%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">售价</td>
            <td width="25%" height="22" background="${pageContext.servletContext.contextPath }/images/bg.gif"  class="STYLE1">基本操作</td>
          </tr>
          
          <c:forEach var="key" items="${pageView.records}">
          <tr>
            <td height="20" >
              <input type="checkbox" name="check" value="${key.product_id}" />
            </td>
          
             <td height="20" ><span class="STYLE1">${key.operator}</span></td>
             <td height="20" ><span class="STYLE1">${key.province}</span></td>
             <td height="20" ><span class="STYLE1">${key.parvalue}</span></td>
             <td height="20" ><span class="STYLE1"><c:if test="${key.showable eq '1'}">是</c:if><c:if test="${key.showable eq '0'}">否</c:if></span></td>
             <td height="20" ><span class="STYLE1">${key.price_scope}</span></td>
             <td height="20" ><span class="STYLE1">${key.price}</span></td>
            
            <td height="20" ><span class="STYLE4">
            <img src="${pageContext.servletContext.contextPath }/images/edt.gif" width="16" height="16" />
            <a href="${pageContext.servletContext.contextPath }/background/product/getById.action?productId=${key.product_id}&&type=1">
                                     编辑
            </a>
            &nbsp; &nbsp;
            <img src="${pageContext.servletContext.contextPath }/images/del.gif" width="16" height="16" />
            	<a href="javascript:void(0);" onclick="deleteId('${pageContext.servletContext.contextPath }/background/product/deleteById.action?productId=${key.product_id}');">
            	删除</a>
            	</span></td>
          </tr>
          </c:forEach>
        </table></td>
        <td width="8" background="${pageContext.servletContext.contextPath }/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.servletContext.contextPath }/images/tab_19.gif"><%@include file="../../common/webfenye.jsp" %>
    </td>
  </tr>
</table>
</form>
</body>
</html>