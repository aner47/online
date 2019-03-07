<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
<script
	src="<%=request.getContextPath()%>/resources/js/jquery/jquery-1.9.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/reg.css">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZwvKDkc8AosMeMixrVVDfEqK"></script>
	<style>
		#map{
			width:100%;
			height:270px;
			border:1px solid #ccc;
		}
		.reg_secondpage .reg_linediv{
			margin-bottom: 27px;
		}
	</style>
</head>
<script type="text/javascript">
	require(
			[ "validate", "ajax", "select", "panel" ],
			function() {

	

				//验证表单
				$("#formcheckone")
						.validate(
								{
									rules : {
										username : {
											rangelength : [ 3, 64 ],
											remote : {
												url : base
														+ "/common/checkUserNameIsRepeat.jhtml", //后台处理程序
												type : "post", //数据发送方式
												dataType : "json", //接受数据格式   
												data : { //要传递的数据
													userName : function() {
														return $(
																"input[name=username]")
																.val();
													}
												}
											}
										},
										password : {
											rangelength : [ 6, 14 ],
										},
										passwordtwo : {
											equalTo : "[name='password']"
										}
									},
									messages : {
										username : {
											remote : '用户名已存在！',
											rangelength : '长度大于3小于64个字符！',
										},
										password : {
											rangelength : '6-14位字符 中英文均可！',
										},
									},
									submitHandler : function(form) {
										$(".reg_firstpage").hide();
										$(".reg_secondpage").show();
										$(".reg_first").css("border-bottom",
												"none");
										$(".reg_second").addClass("active");
										return false;
									}
								});
				$("#formchecktwo")
						.validate(
								{
									rules : {
										name : {
											remote : {
												url : base
														+ "/common/checkEnterpriseNameIsRepeat.jhtml", //后台处理程序
												type : "post", //数据发送方式
												dataType : "json", //接受数据格式   
												data : { //要传递的数据
													enterpriseName : function() {
														return $(
																"input[name=name]")
																.val();
													}
												}
											}
										},
										longitude : {
											number : true
										},
										latitude : {
											number : true
										},
										contact_number : {
											isTelephone : true
										}
									},
									messages : {
										name : {
											remote : '企业名已存在'
										},
										longitude : "",
										latitude : "请输入正确的经纬度",
										contact_number : "请输入正确的电话号码"
									},
									submitHandler : function(form) {

										qy
												.ajax({
													url : base
															+ '/web/registerenterprisedictionary/registered.jhtml',
													data : {
														"username" : $(
																'input[name=username]')
																.val(),
														"password" : $(
																'input[name=password]')
																.val(),
														"captcha" : $(
																'input[name=captcha]')
																.val(),
														"name" : $(
																'input[name=name]')
																.val(),
														"inputIndustry" : $(
																'input[name=inputIndustry]')
																.val(),
														"code" : $(
																'input[name=code]')
																.val(),
														"countyName" : $(
																'input[name=countyName]')
																.val(),
														"detailAddress" : $(
																'input[name=detailAddress]')
																.val(),
														
														"contacts" : $(
																'input[name=contacts]')
																.val(),
														"contactsPhone" : $(
																'input[name=contactsPhone]')
																.val(),
														"corporation" : $(
																'input[name=corporation]')
																.val(),
														"captchaCode" : $(
																'input[name=captchaCode]')
																.val(),
														"captchaKey" : $("input[name=captchaKey]").val()
													},
													callBack : function(data) {
														if (data.code == 10000) {
															layer
																	.msg(data.content);
														} else {
															$(".reg_firstpage")
																	.css(
																			"display",
																			"none");
															$(".reg_secondpage")
																	.css(
																			"display",
																			"none");
															$(".reg_thirdpage")
																	.css(
																			"display",
																			"block");
															$(".reg_nextStep")
																	.css(
																			"display",
																			"none");
															$(".reg_nextLogin")
																	.css(
																			"display",
																			"block");
															$(".reg_first")
																	.css(
																			"border-bottom",
																			"none");
															$(".reg_second")
																	.css(
																			"border-bottom",
																			"none");
															$(".reg_third")
																	.addClass(
																		"active");
														}
													}
												})
										return false;
									}
								});

				$(".reg_nextStep").click(function() {
					if ($(".reg_secondpage").is(":hidden")) {
						$("#formcheckone").submit();
					} else {
						$("#formchecktwo").submit();
					}
				});
				$(".reg_nextLogin").click(function() {
					location.href = "../web/login.jhtml";
				});
				$(".reg_userlogin").click(function() {
					location.href = "../web/login.jhtml";
				});
				$(".reg_exit").click(function() {
					location.href = "../web/login.jhtml";
				});
				
				//刷新验证码
				$("#changeImg").click(function(){
					qy.ajax({
						url:base+"/common/changeCaptcha.jhtml",
						type : "post",
						dataType:"text",
						callBack:function(d){
							$("input[name=captchaKey]").val(d);
							$("#captchaImage").attr("src",base+"/common/captcha.jhtml?captchaId="+d);
						}
					});
				});

				var category = $("#category").select({
					name : "industry_category_code",
					zIndex : 8,
					param : "categoryLevel:MAIN",
					change : function(event, value) {
						var text = category.getSelectCaption();
						$("input[name=industry_category_name]").val(text);
						categoryMiddle.clear();
						categoryMiddle.loadData("parent:" + value);
					}
				});

				var categoryMiddle = $("#categoryMiddle").select(
						{
							name : "industry_category_code_middle",
							"initLoad" : false,
							zIndex : 8,
							change : function(event, value) {
								var text = categoryMiddle.getSelectCaption();
								$("input[name=industry_category_name_middle]")
										.val(text);
							}
						});

				var provinces = $("#provinces").select({
					name : "provinces",
					change : function(event, value) {
						city.clear();
						city.loadData("parent:" + value);
					}
				});

				var city = $("#city").select({
					name : "city",
					"initLoad" : false,
					clearEvent : function() {
						county.clear();
					},
					change : function(event, value) {
						county.clear()
						county.loadData("parent:" + value);
					}
				});
				var county = $("#county").select({
					name : "county",
					clearEvent : function() {
						street.clear();
					},
					"initLoad" : false,
					change : function(event, value) {
						street.clear()
						street.loadData("parent:" + value);
					}
				});
				var street = $("#street").select({
					name : "street",
					"initLoad" : false
				});

				var map = new BMap.Map("map"); // 创建Map实例
				map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
				var local = new BMap.LocalSearch(map, {
					renderOptions:{map: map},
					onSearchComplete: function(results){
						// 判断状态是否正确
						if (local.getStatus() == BMAP_STATUS_SUCCESS){
							if(results.getCurrentNumPois() > 0 ){
								map.centerAndZoom(results.getPoi(0).point);
								$("#latitude").val(results.getPoi(0).point.lat)
								$("#longitude").val(results.getPoi(0).point.lng)
							}else{
								qy.fail2({title:"地址不存在！"});
							}
						}else{
							qy.fail2({title:"地址不存在！"});
						}
					}
				});			
				$("#latitude,#longitude").blur(function() {
					if($("#latitude").val() != "" && $("#longitude").val() != ""){
						var point = new BMap.Point($("#longitude").val(),$("#latitude").val() );
						var marker = new BMap.Marker(point);
						map.addOverlay(marker);
						map.panTo(point);
					}
							/* var address = provinces.getSelectCaption()
									+ city.getSelectCaption()
									+ county.getSelectCaption()
									+ street.getSelectCaption()
									+ $("#houseNumber").val();
							local.search(address); */
							
				})

/* 				function showInfo(e) {
					var marker = new BMap.Marker(e.point); // 创建标注
					map.addOverlay(marker); // 将标注添加到地图中
					map.panTo(e.point);
					qy.sure({
								title : "是否定位当前点！",
								yes : function(index) {
									$("#latitude").val(e.point.lat)
									$("#longitude").val( e.point.lng)
									layer.close(index);
								}
							})
				}
				map.addEventListener("click", showInfo); */
			})
</Script>
<style>
body .layui-layer-page .layui-layer-content {
  overflow: visible;
}

body .layui-layer-title {
  height: 38px;
  font-size: 16px;
  line-height: 38px;
  text-align: left;
  margin: 0;
  padding-left: 20px;
  color: #ffffff;
  background-color: #303D50;
}

body .layui-layer-setwin .layui-layer-close1 {
  background-image: url(../img/close2.png);
}

body .layui-layer-page .layui-layer-btn {
  padding-right: 6px;
  text-align: right;
}

body .layui-layer-btn a {
  background-color: #ffffff;
  box-shadow: none;
  border: 1px solid #4fc1ee;
  color: #4fc1ee;
  width: auto;
  padding: 0 22px;
  height: 28px;
  line-height: 26px;
  border-radius: 15px;
  font-weight: normal;
  font-size: 14px;
  margin: 0 29px 0 0;
}

body .layui-layer-btn a:hover {
  border: 0;
  padding: 0 23px;
  line-height: 28px;
  color: #ffffff;
  background-color: #2eaee1;
}

body .layui-layer-btn a:active {
  border: 0;
  padding: 0 23px;
  line-height: 28px;
  color: #ffffff;
  background-color: #0699d3;
}

body .layui-layer-btn .layui-layer-btn1 {
  background: #ffffff;
}

body .layui-layer-btn a:last-child {
  box-shadow: none;
  border: 1px solid #4fc1ee;
  color: #4fc1ee;
}

body .layui-layer-btn a:last-child:hover {
  border: 0;
  padding: 0 23px;
  line-height: 28px;
  color: #ffffff;
  background-color: #2eaee1;
}

body .layui-layer-btn a:last-child:active {
  border: 0;
  padding: 0 23px;
  line-height: 28px;
  color: #ffffff;
  background-color: #0699d3;
}
</style>
<body>
	<div class="reg_head">
		<div class="reg_head_main">
			<div class="reg_logo">
				<img src="<%=request.getContextPath()%>/resources/img/titleLogo.png">
			</div>
			<div class="reg_user">
				<div class="reg_login">
					<div class="reg_userimg">
						<span class="loginicon"><span class="iconfont">&#xe6b5;</span></span>
					</div>
					<div class="reg_userlogin">登陆</div>
				</div>
				<div class="reg_exit">返回</div>
			</div>
		</div>
		<div class="reg_body">
			<div class="reg_body_main">
				<div class="reg_tab">
					<div class="reg_first active">
						<div class="reg_stepone">
							<span class="stepicon">1</span>
						</div>
						<div class="reg_steponeuser">设置用户名</div>
						<!--  -->
					</div>
					<div class="reg_second">
						<div class="reg_stepone">
							<span class="stepicon">2</span>
						</div>
						<div class="reg_steponeuser">填写企业信息</div>
					</div>
					<div class="reg_third">
						<div class="reg_stepone">
							<span class="stepicon">3</span>
						</div>
						<div class="reg_steponeuser">注册成功</div>
					</div>
				</div>
				<div class="reg_content">
					<div class="reg_firstpage">
						<form id="formcheckone" method="get" action="">
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>用户名
								</div>
								<div class="reg_inputdiv">
									<input type="text" name="username" class="reg_input"
										placeholder="请输入用户名" required />
									<!-- <div class="reg_tips">可设置3-8位字符 中英文均可，不予许有空格</div> -->
								</div>
							</div>
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>密码
								</div>
								<div class="reg_inputdiv">
									<input type="password" name="password" class="reg_input"
										placeholder="请输入密码" required />
									<!-- <div class="reg_tips">可设置6-14位字符 中英文均可，不予许有空格</div> -->	
								</div>								
							</div>
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>重复密码
								</div>
								<div class="reg_inputdiv">
									<input type="password" name="passwordtwo" class="reg_input"
										placeholder="请确认密码" required />
									<!-- <div class="reg_tips">请再次输入密码</div> -->	
								</div>
							</div>
							<!-- <div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>电子邮箱
								</div>
								<div class="reg_inputdiv">
									<input type="email" name="email" class="reg_input"
										placeholder="请输入正确的邮箱格式" required />
								</div>
							</div> -->
						</form>
					</div>
					<div class="reg_secondpage" style="display: none;">
						<form id="formchecktwo" method="get" action="">
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>企业名称
								</div>
								<div class="reg_inputdiv">
									<input type="text" name="name" class="reg_inputlong"
										placeholder="请填写企业全名，与单位公章一致" required />
								</div>
								<div class="reg_tips"></div>
							</div>
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>行业
								</div>
								<div class="reg_inputdiv">
									<input type="text" name="inputIndustry" class="reg_inputlong"
										placeholder="行业" required />
								</div>
								<div class="reg_tips"></div>
								<!-- <div class="reg_inputdiv">
									<div data-code="行业" id="category" class="reg_areaSelectlong"
										></div>
									<div data-code="行业" id="categoryMiddle" class="reg_areaSelectlong"
										></div>
									<select id="category" name="industry_category_code" class="reg_input" placeholder=""><option value=''>请选择行业类别</option></select>
								</div>
								<input type="hidden" name="industry_category_name" /> <input
									type="hidden" name="industry_category_name_middle" />
								<div class="reg_tips"></div> -->
							</div>
							<div class="reg_linediv">
								<div class="reg_name" style="line-height:20px;">
									<span>*</span>组织机构代码或社会统一信息代码
								</div>
								<div class="reg_inputdiv">
									<input type="text" name="code" class="reg_inputlong"
										placeholder="有营业执照的均需填写。" required/>
								</div>
								<div class="reg_tips"></div>
							</div>
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>所在区县
								</div>
								<div class="reg_inputdiv">
									<input type="text" name="countyName" class="reg_inputlong"
										placeholder="区县" required/>
										<!-- <div data-code="地域" id="provinces" class=" reg_areaSelect"></div>
										<div id="city" data-code="地域" class="reg_areaSelect"></div>
										<div id="county" data-code="地域" class="reg_areaSelect"></div>
										<div id="street" data-code="地域" class="reg_areaSelect"
											style="margin-right: 0;"></div> -->

									<div class="reg_tips"></div>
								</div>
							</div>
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>详细地址
								</div>
								<div class="reg_inputdiv">
									<input type="text" id="detailAddress" name="detailAddress"
										class="reg_inputlong" placeholder="请您如实填写企业详细地址" required />
								</div>
								<div class="reg_tips"></div>
							</div>
							 <div class="reg_linediv">
								<div class="reg_lineleft">
									<div class="reg_linelat">
									<!--<div class="reg_name">
										<span>*</span>经度
									</div>
									<div class="reg_inputdiv">
										<input type="text" id="longitude" name="longitude"
											class="reg_inputjd" placeholder="请输入经度信息" />
									</div>
									</div>
	
									<div class="reg_linelon">
									<div class="reg_name">
										<span>*</span>纬度
									</div>
									<div class="reg_inputdiv">
										<input type="text" id="latitude" name="latitude"
											class="reg_inputwd" placeholder="请输入纬度信息" />
											</div>
									</div>-->
	
									<div class="reg_linelat"> 
									<div class="reg_name">
										<span>*</span>联系人
									</div>
									<div class="reg_inputdiv">
										<input type="text" name="contacts" class="reg_inputjd"
											placeholder="" required/>
									</div>
									</div>
									<div class="reg_linelat"> 
									<div class="reg_name">
										<span>*</span>联系电话
									</div>
									<div class="reg_inputdiv">
										<input type="text" name="contactsPhone" class="reg_inputjd"
											placeholder="" required/>
									</div>
									</div>
									<div class="reg_linelon">
									<div class="reg_name">
										<span>*</span>法人
									</div>
									<div class="reg_inputdiv">
										<input type="text" name="corporation" class="reg_inputwd"
											placeholder="" required/>
											</div>
									</div>
								</div>
								<!-- <div class="reg_lineright">
									<div id="map"></div>
								</div> -->

							</div>							
							<div class="reg_linediv">
								<div class="reg_name">
									<span>*</span>验证码
								</div>
								<div class="reg_inputdiv_yzm">
									<input type="text" name="captchaCode" class="reg_input" placeholder="请输入验证码"  required />
								</div>
								<input type="hidden" name="captchaKey" value="${captchaId }">
								<img id="captchaImage" class="captchaImage" style="padding-left:10px;float:left;" src="<%=request.getContextPath()%>/common/captcha.jhtml?captchaId=${captchaId}" />								
								<a id="changeImg" href="javascript:void(0);" style="padding-left:10px;float:left;" >看不清?换一张</a>
							</div>
						</form>
					</div>
					<div class="reg_thirdpage">
						<div class="reg_success">恭喜你，注册成功啦！</div>
					</div>
				</div>
				<div class="reg_next">
					<div class="reg_nextStep">
						<span class="iconfont">&#xe6be;</span>下一步
					</div>
					<div class="reg_nextLogin">
						<span class="iconfont">&#xe6be;</span>立即登陆
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>