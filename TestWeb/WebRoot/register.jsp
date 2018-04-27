<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta name="renderer" content="webkit">
	<title>登录.云购物商城</title>
	<link rel="shortcut icon" type="image/x-icon" href="img/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
</head>
<body>

<header id="pc-header">
	<div class="center">
		<div class="pc-fl-logo">
			<h1>
				<a href="index.jsp"></a>
			</h1>
		</div>
	</div>
</header>
<section>
	<div class="pc-login-bj">
		<div class="center clearfix">
			<div class="fl"></div>
			<div class="fr pc-login-box">
				<div class="pc-login-title"><h2>用户注册</h2></div>
				<form action="UserSerlet?method=add" method="post" id="myForm">
				<div class="pc-sign">
						<input type="text" name="name" placeholder="用户名">
					</div>
					<div class="pc-sign">
						<input type="text" name="email_phone" placeholder="邮箱/手机号">
					</div>
					<div class="pc-sign">
						<input type="password" name="pwd" id="pwd" placeholder="请输入您的密码">
					</div>
					<div class="pc-sign">
						<input type="password" name="repwd" placeholder="请确认您的密码">
					</div>
					<!-- <div class="pc-sign">
						<input type="password" name="" placeholder="请输入您的验证码">
					</div> -->
					<div class="pc-submit-ss">
						<input type="submit" value="立即注册" placeholder="">
					</div>
					<div class="pc-item-san clearfix">
						<a href="#"><img src="img/icon/weixin.png" alt="">微信登录</a>
						<a href="#"><img src="img/icon/weibo.png" alt="">微博登录</a>
						<a href="#" style="margin-right:0"><img src="img/icon/tengxun.png" alt="">QQ登录</a>
					</div>
					<div class="pc-reg">
						<a href="login.jsp" class="red">已有账号 请登录</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<footer>
	<div class="center">
		<div class="pc-footer-login">
			<p>关于我们 招聘信息 联系我们 商家入驻 商家后台 商家社区 ©2017 Yungouwu.com 北京云购物网络有限公司</p>
			<p style="color:#999">营业执照注册号：990106000129004 | 网络文化经营许可证：北网文（2016）0349-219号 | 增值电信业务经营许可证：京2-20110349 | 安全责任书 | 京公网安备 99010602002329号 </p>
		</div>
	</div>
</footer>
<script type="text/javascript">
 $(function () {
       $("#myForm").validate({
           rules:{//验证规则
              name:{
                required:true
              },
              pwd:{
                required:true,
                minlength:6,
                maxlength:10
              },
               repwd:{
                   required:true,
                   minlength:6,
                   maxlength:10,
                   equalTo:"#pwd"//必须和某值相同
               },
               email_phone:{
                   required:true,
                   /* email:true */
               }
               /* email_phone:{
                required:true,
                  /*  email:true */
                   /*checkPhone:true//自定义方法
               } */
               /* ,
               phone:{
                   required:true,
                   checkPhone:true//自定义方法
               },
               context:{
                   required:true,
               } */
           },
           messages:{//提示信息
               name:{
                   required:"请输入用户名！"
               },
               pwd:{
                   required:"密码不能为空",
                   minlength:"密码长度不能小于6",
                   maxlength:"密码长度不能大于10"
               },
               repwd:{
                   required:"请重复输入密码",
                   minlength:"密码长度不能小于6",
                   maxlength:"密码长度不能大于10",
                   equalTo:"两次密码不一致"
               },
               email_phone:{
                   required:"请输入邮箱或手机",
                  /*  email:"邮箱格式不正确" */
               }/* ,
                email_phone:{
                   required:"请输入邮箱或手机", */
                  /*  email:"邮箱格式不正确" */
              /* }/* ,
               phone:{
                   required:"请输入手机号 "
               },
               context:{
                   required:"必须同意协议",
               } */
           },
           onfocusout:function (e) {//文本框失去焦点就验证
               // e:相当于下标，即this.index
               $(e).valid();
           }
       });
       /* jQuery.validator.addMethod("checkPhone",function (value,element) {
            var phone2=/^1[3|4|5|8][0-9]\d{4,8}$/;
            return this.optional(element)||phone2.test(value);//this.optional(element)用于表单输入值不为空时验证，当field为空时，即element的值为空
       },"手机号码不正确") */
    });
</script>
</body>
</html>