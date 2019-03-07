<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/login/login.css">
    <script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-1.9.1.min.js"></script>
    	
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/layer/layer.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/qy/ajax/ajax.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rsa.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/base64.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/prng4.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rng.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/jsbn.js"></script>
  </head>
  <script type="text/javascript">
	
	/*监听键盘 事件 */
	function KeyDown(event)
	{
	  if (event.keyCode == 13)
	  {
	    event.returnValue=false;
	    event.cancel = true;
	    Form1.btncommit.click();
	  }
	} 
</script>
<body onkeypress=KeyDown(event) >
	<div class="loginFrame">
		<div class="loginContent">
		<div class="loginbg"></div>
			<div class="logo"></div>
			<p id = "title">在线申报后台管理系统</p>
			<form  name="Form1" class = "login"   action  = "login/submit.jhtml" method = "post" autocomplete="off">
				<!-- <fieldset > -->
					<dd>
						<p style = "color:red" id = 'error'></p>
						<dl>
							<input type="text" id="username" name = "username" placeholder="您的账号" autocomplete="off">
							<label for="username" class="username"><i class="iconfont icon-denglu"></i></label>
						</dl>
						<dl>
							<input type="password"   id="password" name = "password" placeholder="密码" autocomplete="off">
							<label for="password" class="password"><i class="iconfont icon-mima"></i></label>
							<div class="eye"><i class="iconfont icon-yanjing"></i></div>
						</dl>
						<dl>        
							<button type="button" id = 'commit' class="btn btn-sm btn-primary" name="btncommit" >登录</button>
							<button type="reset" class="btn btn-sm btn-default">重置</button>
						</dl>
					</dd>
				<!-- </fieldset> -->
			</form>
		</div>
		<div class="framebg1"></div>
	</div>
</body>
<script type="text/javascript">
	function browserRedirect() {
		var sUserAgent = navigator.userAgent.toLowerCase();
		var u= navigator.userAgent;
		var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
		var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
		var bIsMidp = sUserAgent.match(/midp/i) == "midp";
		var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
		var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
		var bIsAndroid = sUserAgent.match(/android/i) == "android";
		var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
		var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
		var mobile = u.match(/AppleWebKit.*Mobile.*/);
		var android = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;
		var ios = u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
		if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM || mobile || android || ios) {
			console.log('Mobile device');
			document.body.setAttribute("class", "mobileDevice");
		} else {
			console.log('pc');
		}
	}
	browserRedirect();
	// 全屏
	function launchFullScreen(element) {  
		if(element.requestFullScreen) {  
			element.requestFullScreen();  
		} else if(element.mozRequestFullScreen) {  
			element.mozRequestFullScreen();  
		} else if(element.webkitRequestFullScreen) {  
			element.webkitRequestFullScreen();  
		} else if(element.msRequestFullscreen){
			element.msRequestFullscreen();
		}
	}
$(function(){
	var asd=$('#title').on('click',function(){
		launchFullScreen(document.documentElement);
	});
		$('#commit').click(function(){
			if(! $('#username').val()){
				layer.msg('账号不可以为空');
				return;
			}else if(! $('#password').val()){
				layer.msg('密码不可以为空');
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
				var enPassword = hex2b64(rsaKey.encrypt($('#password').val()));
				d.password = enPassword;  
				d.username = $('#username').val();
				d.captcha = $('#captcha').val();
				$.ajax({
					url :"<%=request.getContextPath()%>/admin/login.jhtml",
					type : "POST",
					dataType : 'json',
					data : d,
					success : function (data) {
						layer.close(load);
						if(data.code===20000){
							layer.msg('登陆成功！', {icon: 6});
							location.href = "<%=request.getContextPath()%>/admin/main.jhtml";
						}else{
							layer.msg(data.content);
						}
					},
					err : function (c) {
						layer.close(load);
						layer.msg('用户名或密码不正确！', {
							icon : 5
						});
					}

				})
			}

		})
	
	})
		
	});
	(function(){
			var isPassword=true;
			$('.eye').on('click',function(){
				if (isPassword) {
					$('#password').attr('type','text');
				}else{
					$('#password').attr('type','password');
				};
				isPassword=!isPassword;				
			})
		})()
</script>
</html>