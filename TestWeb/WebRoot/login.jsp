<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
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
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<link rel="shortcut icon" type="image/x-icon" href="img/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<link rel="stylesheet" type="text/css" href="css/verify.css">
	<script type="text/javascript" src="js/verify.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
	.modal-dialog{
	position:absolute;
  	left:-50px;
 	 top:150px;
	}
	</style>
</head>
<body>
<%
		String hh="";
		String pwd="";
		Cookie[] cook=request.getCookies();
		System.out.println(cook);
		if(cook!=null){
			for(Cookie cookie:cook){
				if("name".equals(cookie.getName())){
					hh=URLDecoder.decode(cookie.getValue(),"utf-8");
					/* System.out.println("这是namekey:"+hh); */
				}
				 if("pwd".equals(cookie.getName())){
					pwd=URLDecoder.decode(cookie.getValue(),"utf-8");
					/* System.out.println("这是pwdkey:"+pwd); */
				}
			}
			
			%>
				<script type="text/javascript">
				 $(function () {
				 
					$("[id='nep']").attr("value","<%=hh%>");
					$("[id='pwd']").attr("value","<%=pwd%>");
					
					});
				</script>
			<%
		}
 %>

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
				<div class="pc-login-title"><h2>用户登录</h2></div>
			
				<form id="form" action="UserSerlet?method=login" method="post">
					<div class="pc-sign">
						<input type="text" name="nep" id="nep" placeholder="请输入用户名或邮箱" >
					</div>
					<div class="pc-sign">
						<input type="password" name="pwd" id="pwd" placeholder="请输入密码" >
					</div>
					<div class="pc-submit-ss">
						<input type="button" id="button" value="登录" placeholder=""  data-toggle="modal"  onclick="clicks()"><!-- onclick="clicks()" -->
					</div>
					<div class="pc-checkbox">
						<input type="checkbox" name="rspwd">记住密码
					</div>
					<div class="pc-item-san clearfix">
						<a href="#"><img src="img/icon/weixin.png" alt="">微信登录</a>
						<a href="#"><img src="img/icon/weibo.png" alt="">微博登录</a>
						<a href="#" style="margin-right:0"><img src="img/icon/tengxun.png" alt="">QQ登录</a>
					</div>
					<div class="pc-reg">
						<a href="#">忘记密码</a>
						<a href="register.jsp" class="red">免费注册</a>
					</div>
				</form>
			</div>
				<div id="pc-hua">
				
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div id="mpanel4" style="margin-top:50px;">
						</div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
					
						</div>
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
    
       $("#form").validate({
           rules:{//验证规则
              nep:{
                required:true
             
              },
              pwd:{
                required:true,
                minlength:6,
                maxlength:60
              }
           },
           messages:{//提示信息
               nep:{
                   required:"请输入用户名或邮箱或手机！",
                   remote:"用户已被注册"
               },
               pwd:{
                   required:"密码不能为空",
                   minlength:"密码长度不能小于6",
                   maxlength:"密码长度不能大于60"
               }
           },
           onfocusout:function (e) {//文本框失去焦点就验证
               // e:相当于下标，即this.index
               $(e).valid();
           }
       });
      /*  jQuery.validator.addMethod("checkPhone",function (value,element) {
            var phone2=/^1[3|4|5|8][0-9]\d{4,8}$/;
            return this.optional(element)||phone2.test(value);//this.optional(element)用于表单输入值不为空时验证，当field为空时，即element的值为空
       },"手机号码不正确") */
    });
function clicks(){
if($("#form").valid()){ 
 $("#myModal").modal("show")  ;//干嘛的，这个是模态窗口的
$("#mpanel4").css({"display":"block"});
}	
}


$("input").keydown(function(event){
	var x = event.which || event.keyCode;
	if(x==13){
	if($("#form").valid()){
	 $("#myModal").modal("show")  ;
	$("#mpanel4").css({"display":"block"})
	}
	}
	}
);


$("#mpanel4").slideVerify({
		type : 2,		//类型
		vOffset : 5,	//误差量，根据需求自行调整
		vSpace : 5,	//间隔
		imgName : ['1.jpg', '2.jpg'],
		imgSize : {
			width: '400px',
			height: '200px',
		},
		blockSize : {
			width: '40px',
			height: '40px',
		},
		barSize : {
			width : '400px',
			height : '40px',
		},
		ready : function() {
		},
		success : function() {
			alert('验证成功');
			$("#form").submit();
		},
		error : function() {
		      alert('验证失败！');
		      return false; 
		}
		
	});
	
</script>
</body>
</html>