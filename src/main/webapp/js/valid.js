/*--------------------------------------------登录的验证*/
function v_login(){
	var login_name=document.getElementById("uname").value;
	if (login_name.length==0) {
		alert('用户名不可以为空');
		return false;
	} else{
		var login_pwd=document.getElementById("upwd").value;
		if (login_pwd.length==0) {
			alert('密码不可以为空');
			return false;
		} else{
			return true;		
		}	
	}
	
}
/*--------------------------------------------修改密码的验证*/
function v_updatePwd(){
	var oldpwd=document.getElementById("oldpwd").value;
	var newpwd=document.getElementById("newpwd").value;
	var repwd=document.getElementById("repwd").value;
	if (oldpwd.length==0) {
		alert('旧密码不能为空');
		return false;
	} else{
		if (newpwd.length==0) {
			alert('新密码不能为空');
			return false;
		} else{
			if (repwd.length==0) {
				alert('重复密码不能为空')
				return false;
			} else{
				if (repwd!=newpwd) {
					alert('新密码与重复密码不一致，请修改')
					return false;
				} else{
					return true;
				}
			}
		}
	}
}

/*--------------------------------------------添加管理员的验证*/
function v_add_admin(){
	var add_admin_uname=document.getElementById("add_admin_uname").value;
	var add_admin_pwd=document.getElementById("add_admin_pwd").value;
	var add_admin_nickname=document.getElementById("add_admin_nickname").value;
	if (add_admin_uname.length==0) {
		alert('用户名不能为空');
		return false;
	} else{
		if (add_admin_nickname.length==0) {
			alert('昵称不能为空');
			return false;
		} else{
			if (add_admin_pwd.length==0) {
				alert('密码不能为空');
				return false;
			} else{
				if (add_admin_pwd.length<6 ) {
					alert('密码长度在6~12个字符内')
					return false;
				} else{
					return true;
				}
				
			}	
		}
	}
}
 
/*--------------------------------------------不为空的验证
* 使用方法：
* 	<input type="password" value="" name="login_pwd" id="login_pwd" onblur="v_notNull(this.id,'密码不能为空')" /> 
*/
function v_notNull(thisId,thisMsg){
	var myele=document.getElementById(thisId).value;
	if (myele.length==0) {
		alert(thisMsg);
		return false;
	} else{
		return true;
	}
}
/*--------------------------------------------密码输入字符的验证
 *密码规定只能输入大小写英文、数字，字符长度在6~20
 * onblur="v_pwd(this.id,'密码不能为空')"
 * */
 function v_pwd(thisId,thisMsg){
	var RegPwd =/^(\w){6,12}$/;
	var myele=document.getElementById(thisId).value;
	if (myele.length==0) {
		alert(thisMsg);
		return false;
	} else{
		if (!RegPwd.exec(myele)) {
			 alert('密码只能输入6~12个数字和英文')
			 return false;
		} else{
			return true;
		}
	} 
		 
}
/*--------------------------------------------邮箱的验证v_email(thisId)
   onblur="v_email(this.id)" 
 * 
 * */

function v_email(thisId){
	var RegEmail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var myele=document.getElementById(thisId).value;
	if (myele.length==0) {
		alert('邮箱不能为空');
		return false;
	} else{
		if (!RegEmail.test(myele)) {
			 alert('邮箱正确格式：123456@qq.com')
			 return false;
		} else{
			return true;
		}
	}
}

/*--------------------------------------------手机号的验证v_phone(thisId)
   onblur="v_phone(this.id)" 
 * 
 * */

function v_phone(thisId){
	var RegPhone=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var myele=document.getElementById(thisId).value;
	if (myele.length==0) {
		alert('电话号码不能为空');
		return false;
	} else{
		if (!RegPhone.test(myele)) {
			 alert('手机号格式不正确')
			 return false;
		} else{
			return true;
		}
	}
}


function v_xuehao(thisId){
	var myele=document.getElementById(thisId).value;
	if (myele.length<12){
		alert('请填写12位学号');
		document.empadd.myele.focus();
		return false;
	} 
}