<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
    <script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rsa.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/base64.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/prng4.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rng.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/jsbn.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/mainframe.css" >
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/iconfont/iconfont.css" >
</head>
<style>
body {
	position: relative;
}
.noticeWrapActive {
	position: absolute;
	bottom: 20px;
	right: 20px;
	width: 380px;
	/* height: 284px; */
	height: 140px;
	overflow: hidden;
	border-radius: 5px;
	background: rgba(255, 255, 255, .4);
}
.noticeActive {
	position: absolute;
	top: 0px;
	left: 0px;
	padding-left: 10px;
}
.noticeActive span {
	display: block;
	margin-top: 8px;
	font-weight: 800;
}
.noticeActive li {
	font-size: 13px;
}
.noticeActive li span {
	display: inline-block;
	margin-top: 2px;
	margin-right: 4px;
	font-weight: 100;
}
.editionsBig .ediTitle {
	margin-bottom: 20px;
	font-size: 16px;
	color: #fff;
}
.editionsBig .ediContent {
	margin-bottom: 20px;
	font-size: 14px;
	color: #fff;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/editions.js"></script>
<script type="text/javascript">
/*监听键盘 事件 */
function KeyDown(event){
  	if (event.keyCode == 13) $(".login_login").click();
} 

 require(["validate","ajax","panel"],function(){
	 $(function(){
			$('.login_login').click(function(){
				if(! $('input[name=username]').val()){
					qy.suc3({title:'账号不可以为空'});
					return;
				}else if(! $('input[name=password]').val()){
					qy.suc3({title:'密码不可以为空'});
					return;
				}
				$.ajax({
				url : "<%=request.getContextPath()%>/common/public_key.jhtml",
				type : "GET",
				dataType : "json",
				cache : false,
				beforeSend : function () {
					load = layer.load(2)
				},
				success : function (data) {
					var d  = {captchaId:'${captchaId}'};
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($('input[name=password]').val()));
					d.password = enPassword;  
					d.username = $('input[name=username]').val();
					d.invitationCode = $('input[name=invitationCode]').val();
					$.ajax({
						url :"<%=request.getContextPath()%>/web/login.jhtml",
						type : "POST",
						dataType : 'json',
						data : d,
						success : function (data) {
							layer.close(load);
							if(data.code===20000){
								qy.suc2({title:'登陆成功！'});
								location.href = "<%=request.getContextPath()%>/web/main.jhtml";
							}else{
								qy.suc3({title:data.content});
							}
						},
						err : function (c) {
							layer.close(load);
							qy.fail2({title:'登陆失败！'});
						}

					})
				}

			})
		
		})
		
		$(".login_top").click(function(){
			location.href = "<%=request.getContextPath()%>/web/registerenterprisedictionary/registerPage.jhtml";
		})
		$(".login_user").click(function(){
			location.href = "<%=request.getContextPath()%>/web/registerenterprisedictionary/registerPage.jhtml";
		})	
		});
	 	
	 var result = edition;	

		for (var i = 0; i < result.length; i++) {

			var editionTemplate = '<span class="ediTitle">'+ result[i].edition +'</span>';
			$(".js-editions").append(editionTemplate);

			for (var j = 0; j < result[i].data.length; j++) {

				var editionsListTemplate = 
					'<li class="ediContent">' +
						(j+1) + '、'+ result[i].data[j];
					'</li>';
				/* var editionsListTemplate = 
					'<li class="ediContent">' +
						'<span>'+ (j+1) +'、</span>'+ result[i].data[j];
					'</li>'; */
				$(".js-editions").append(editionsListTemplate);
			}
			
		}
 		
 		var NoticeTop = null;
 		var _height = $("#Notice").height();
 		var timer = window.setInterval(move, 100);// 150
 		function move() {
		 	if (NoticeTop === _height - 20) {
		 		NoticeTop = -10
		 	} else {
		 		NoticeTop++;
		 		$("#Notice").css({
			 		"top": -NoticeTop + "px"
			 	})
		 	}
 		}
 		
	 	/* 移入与移出 */
	 	$("#NoticeWrap").on("mouseenter",function () {
	 		window.clearInterval(timer);
	 		$(this).on("mousemove", function() {
	 			window.clearInterval(timer);
	 		})
	 	}).on("mouseout", function() {
	 		timer = window.setInterval(move, 150);
	 	})
    }) 
</script>
<body onkeypress=KeyDown(event) >
        <div class="login_head">
             <%-- <div class="login_head_main">
                <div class="login_logo"><img src="<%=request.getContextPath()%>/resources/img/titleLogo.png"></div>
                <div class="login_user">
                                注册
                </div>
            </div>  --%>
        </div>
        <div class="login_main">
            <div class="login_frame">
                <!-- <div class="login_top">没有账号去注册<span class="reg_arrow"><i class="iconfont">&#xe6c3;</i></span></div> -->
                <div class="login_center">
                    <div class="login_title">排放清单污染源调查在线填报系统</div>
                    <div class="login_info">
                        <form id="login_form">
                        <div class="login_input"><input type="text" name="username"  placeholder="用户名"></div>
                        <div class="login_input"><input type="password" name="password" placeholder="密码"></div>
                        <div class="login_input"><input type="text" name="invitationCode" placeholder="认证码"></div>
                        </form>
                    </div>
                    <div class="login_forgetpsd">忘记密码？<span>请联系管理员</span></div>
                    <div class="login_login">登陆</div>
                </div>
                <div class="login_textone">HONGQINGENVIRON</div>
                <div class="login_texttwo">版权所有 中科弘清（北京）科技有限公司</div>
            </div>
        </div>
        <div id="NoticeWrap" class="noticeWrapActive">
        	<div id="Notice" class="noticeActive">
        		<ul class="js-editions editionsBig">
        			
					
			   </ul>
        </div>
	</div>
</body>
</html>