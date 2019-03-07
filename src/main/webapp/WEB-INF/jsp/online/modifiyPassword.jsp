<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改用户信息</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css" >
</head>

<script type="text/javascript">
require(["validate","ajaxform","less"],function(a){
		$('#commit').click(function(){
			$("#modifyinput").submit();
		})
		//提交表单
		$("#modifyinput").validate({
			rules: {
				newPassword2:{
					equalTo:"#newPassword1"
				},
				newPassword: {
					rangelength:[6,16],
				}
			},
			messages:{
				newPassword: {
					rangelength:"6~16字符，数字英文均可，不能有空格！"
				}
			},
			submitHandler:function(form){
				var url =base+"/web/updatePassword.jhtml";
				var options  = {
			        url:url,
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.content!="请求成功"){
			        		qy.fail2({title:'原密码错误！'});
			        		return;
			        	}
						layer.closeAll('page');
			        	qy.suc2({title:'修改成功！'});
			        },
			        error:function(){
			        	qy.fail2({title:'修改失败！'});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});	
	});
</script>
<style>
	form#modifyinput *{
		box-sizing: content-box;
	}
	label.error {
    position: absolute;
    right: 10px;
    top:0px;
}
.dialog_div {
    width: 527px;
    height: auto;
    }
    .content {
    width: auto;
    height: 100%;
    border: 0;
}
    /*弹窗*/
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
  background-image: url(<%=request.getContextPath()%>/resources/img/close2.png);
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

	<form id="modifyinput">
	<div class="dialog_div">
                    <div class="dialog_title_left"><span>*</span>原密码</div>
                    <div class="dialog_title_right">
                        <input class="dialog_pswinput " type="password" id="Password"  autocomplete="off" name="password" placeholder="" required>
                    </div>
                    <div class="dialog_title_left"><span>*</span>新密码</div>
                    <div class="dialog_title_right">
                        <input class="dialog_pswinput " type="password" id="newPassword1"  autocomplete="off" name="newPassword" placeholder="" required>
                    </div>
                    <div class="dialog_title_left"><span>*</span>重复密码</div>
                    <div class="dialog_title_right">
                        <input class="dialog_pswinput " type="password" id="newPassword2"  autocomplete="off" name="newPassword2" placeholder="" required>
                    </div>

                </div>
	<%-- <input type="hidden" id="id" name="id" value="${User.id}"/> --%>
	</form>
</body>
</html>