<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<style>

header {
  width: 100%;
  height: 90px;
  margin-top: -90px;
  float: left;
  background: -webkit-linear-gradient(#0c9be2 , #4373ff); /* Safari 5.1 - 6.0 */
  background: -o-linear-gradient(#0c9be2 , #4373ff); /* Opera 11.1 - 12.0 */
  background: -moz-linear-gradient(#0c9be2 , #4373ff); /* Firefox 3.6 - 15 */
  background: linear-gradient(#0c9be2 , #4373ff); /* 标准的语法 */
  color: #ffffff;
  position: relative;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
}

header .log {
  position: relative;
  width: 6.875%;
  /*width: 132px;*/
  height: 90px;
  float: left;
  background: url('../resources/img/logo.png') no-repeat center right;
  background-origin: content-box;
  padding-right: 12px;
}

header .title {
  width: 512px;
  height: 90px;
  float: left;
}

header .title h1 {
  height: 28px;
  margin-top: 35px;
  font-size: 32px;
  white-space: nowrap;
  line-height: 28px;
  font-weight: bold;
  text-shadow: 0 3px 2px #2d65de;
}

header .title h2 {
  height: 12px;
  white-space: nowrap;
  font-size: 15px;
}

header .navbar {
  position: absolute;
  margin: auto;
  left: 6.875%;
  /*left: 644px;*/
  right: 14.73958%;
  padding-left: 512px;
  /*top: 39px;*/
  /*height: 51px;*/
  bottom: 0;
}

header .navbar ul {
  float: left;
  /*height: 51px;*/
  width: 100%;
  text-align: center;
}

header .navbar ul li {
  /*float: left;*/
  display: inline-block;
  line-height: 51px;
  height: 100%;
  margin: 0 4px;
  font-size: 24px;
  font-weight: bold;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  cursor: pointer;
  padding: 0 16px;
  color: #ffffff;
}

@media screen and (max-width: 1700px) {
  header .navbar {
    padding-left: 496px;
  }
  header .title h1 {
    font-size: 31px;
  }
  header .navbar ul li {
    line-height: 48px;
    font-size: 22px;
    padding: 0 15px;
  }
}

@media screen and (max-width: 1600px) {
  header .navbar {
    padding-left: 480px;
  }
  header .title h1 {
    font-size: 30px;
  }
  header .navbar ul li {
    line-height: 46px;
    font-size: 20px;
    padding: 0 14px;
  }
}

@media screen and (max-width: 1500px) {
  header .navbar {
    padding-left: 464px;
  }
  header .title h1 {
    font-size: 29px;
  }
  header .navbar ul li {
    line-height: 44px;
    font-size: 18px;
    padding: 0 13px;
  }
}

@media screen and (max-width: 1400px) {
  header .navbar {
    padding-left: 448px;
  }
  header .title h1 {
    font-size: 28px;
  }
  header .navbar ul li {
    line-height: 42px;
    font-size: 16px;
    padding: 0 10px;
  }
}

@media screen and (max-width: 1300px) {
  header .navbar {
    padding-left: 432px;
  }
  header .title h1 {
    font-size: 27px;
  }
  header .navbar ul li {
    line-height: 40px;
    font-size: 16px;
    padding: 0 5px;
  }
}

/*@media screen and (max-width: 1200px) {
  header .navbar ul li {
    font-size: 20px;
    padding: 0 15px;
  }
}*/

header .navbar ul li.actives {
  background-color: #ffffff;
  color: #3d78fc;
}

header .navbar ul li:hover {
  background-color: #ffffff;
  color: #3d78fc;
}

header .user {
  width: 14.73958%;
  float: right;
  height: 90px;
  position: relative;
}

header .user .usercontent{
  margin-top: 38px;
  height: 39px;
  padding: 3px;
  border-radius: 3px;
  position: relative;
  float: left;
  cursor: default;
}

header .user .usercontent:hover{
  background-color: rgba(157,190,255,0.5);
}

header .user .userhead {
  width: 33px;
  height: 33px;
  border-radius: 21px;
  background-color: #ffffff;
  float: left;
  color: #3c88ff;
  text-align: center;
  line-height: 33px;
}

header .user .userhead::after{
  content:"\e645";
  font-family: "iconfont" !important;
  font-size: 18px;
  margin-left: 1.5px;
}

header .user .username {
  margin: 0 0 0 8px;
  line-height: 33px;
  font-size: 20px;
  font-weight: bold;
  float: left;
  max-width: 93px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

@media screen and (max-width: 1620px) {
  header .user .username {
    max-width: 70px;
  }
}

header .user .userexit{
  margin-top: 38px;
  height: 39px;
  padding: 3px;
  border-radius: 3px;
  position: relative;
  float: left;
  cursor: default;
  margin-left: 16px;
  cursor: pointer;
}

header .user .userexit:hover{
  background-color: rgba(157,190,255,0.5);
}

header .user .exithead {
  width: 33px;
  height: 33px;
  border-radius: 21px;
  background-color: #ffffff;
  float: left;
  color: #3c88ff;
  text-align: center;
  line-height: 33px;
}

header .user .exithead::after{
  content:"\e6a1";
  font-family: "iconfont" !important;
  font-size: 18px;
  margin-left: 1.5px;
}

header .user .exitspan {
  margin: 0 0 0 8px;
  line-height: 33px;
  font-size: 20px;
  font-weight: bold;
  float: left;
}

@media screen and (max-width: 1500px) {
  header .user .exitspan {
    display: none;
  }
}

header .user .set {
  position: absolute;
  text-align: center;
  display: none;
  top: 44px;
  left: 0;
  width: 120px;
  font-size: 16px;
  border:1px solid #dddddd;
  color: #a5a5a5;
  background-color: #ffffff;
  border-radius: 2px;
  z-index: 100;
}
header .user .set::before{
  position: absolute;
  content: "";
  width: 100%;
  height: 7px;
  top: -7px;
  margin: auto;
  left: 0;
  right: 0;
  background: url("../resources/img/userarrow.png") no-repeat center;
}
header .usercontent:hover .set{
  display: block;
}
header .user .set li{
  height: 35px;
  line-height: 35px;
  width: 100%;
  border-bottom: 1px solid #dddddd;
  cursor: pointer;
}
header .user .set li:last-child{
  border-bottom: 0;
}
header .user .set li:hover{
  color: #242424;
  background: -webkit-linear-gradient(0deg, #f3f3f3, #ffffff); /* Safari 5.1 - 6.0 */
  background: -o-linear-gradient(0deg, #f3f3f3, #ffffff); /* Opera 11.1 - 12.0 */
  background: -moz-linear-gradient(0deg, #f3f3f3, #ffffff); /* Firefox 3.6 - 15 */
  background: linear-gradient(0deg, #f3f3f3, #ffffff); /* 标准的语法 */
}
.fixed_return_cla {
	position: absolute;
	top: 73px;
	right: -142px;
	z-index: 99999;
	width: 40px;
	height: 28px;
	line-height: 24px;
	text-align: center;
	color: #fff;
	border-radius: 3px;
	background-color: #2eaee1;
	cursor: pointer;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>

</head>
<body>
	<header>
		<div class="log">
		</div>
		<div class="title">
			<h1>在线申报后台管理系统</h1>
		</div>
		<div class="company_name" id="company" style="font-size:12px">${enterprise.name}</div>
		<div class="navbar">
			<ul>
				<!-- <li id = "homepage" class="actives">首页</li> -->
				<!-- <li data-id="35" class="" data-menu>企业填报查询</li>
				<li data-id="132" id="tongji" >填报统计</li>
				<li data-id="188" id="qiye" >企业任务</li>
				<li data-id="166" id="renwu" >PSCC任务</li>
				<li data-id="1" class="" data-menu>系统配置</li> -->
				<c:forEach items="${menus}" var="menu">
					<c:if test="${menu.pid == '0'}">
						<li data-id="${menu.id}" class="" data-menu>
							<span>${menu.name}</span>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<div class="user">
	      <div class="usercontent">
	        <div class="userhead"></div>
	        <div class="username" title="">  
	         ${principal.username}
	        </div>
	        <ul class="set">
	          <li class="change" id="updatepassword">修改密码</li>
	        </ul>
	      </div>
	      <div class="userexit" id="logout">
	        <div class="exithead"></div>
	        <div class="exitspan">退出</div>
	      </div>
    </div>
  </header>
</body>
</html>