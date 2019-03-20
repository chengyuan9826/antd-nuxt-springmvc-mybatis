<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	application.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微商城知识库</title>
<link rel="stylesheet" type="text/css" href="${basePath }/css/css.css" />
<script type="text/javascript"
	src="${basePath }/js/common/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	var basePath;
	function sub() {
		$.post(basePath + "/user/login.do", {
			"username" : $("input:eq(0)").val(),
			"password" : $("input:eq(1)").val()
		}, function(data) {
			$("#pwd-ts").text(data.msg);
			if (data.state == 0) {
				window.location = basePath + "/user/home.do";
			}
		});
	}
	$(document).ready(function() {
		basePath = $("#basePath").val();
		//使用户名输入框获得焦点
		$("input:eq(0)").focus();
		//绑定回车键提交
		window.document.onkeydown = function(e) {
			var evt = window.event || e;
			if (evt.keyCode == 13) {//如果按键为回车
				sub();
			}
		};
		$('.login_btn').click(function() {
			sub();
		});
		$('.password_text').click(function() {
			$(this).hide();
			$(this).parent().find('.login_text').addClass('focus');
		});
		$(".login_text").focus(function() {
			$(this).addClass("focus");
			if ($(this).val() == this.defaultValue) {
				$(this).val("");
			}
		}).blur(function() {
			$(this).removeClass("focus");
			if ($(this).val() == '') {
				$(this).val(this.defaultValue);
			}
		});
	});
</script>
</head>
<body>
	<div>
		<div>
				<table class="login_table" width="284" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td valign="top"><input class="login_text" type="text"
							placeholder="工号">
							<div class="ts" id="username-ts"></div></td>
					</tr>
					<tr>
						<td valign="top">
							<div class="login_text2">
								<input class="login_text " type="password" value=""
									placeholder="密码">
							</div>
							<div class="ts" id="pwd-ts"></div>
						</td>
					</tr>
					<tr>
						<td valign="top"><input class="login_btn" type="button"
							value="登陆"></td>
					</tr>
				</table>
			</div>
	</div>
	<input type="hidden" value="${basePath }" id="basePath" />
</body>
</html>
