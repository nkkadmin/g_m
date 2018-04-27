<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>测试表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="testDepController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${testDepPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门名称:
							</label>
						</td>
						<td class="value">
						    <input id="depName" name="depName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${testDepPage.depName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门编号:
							</label>
						</td>
						<td class="value">
						    <input id="depCode" name="depCode" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${testDepPage.depCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门编号</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/demo/testDep.js"></script>		
