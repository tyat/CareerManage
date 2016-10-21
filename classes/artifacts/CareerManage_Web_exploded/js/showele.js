function ShowDetailTip(){
	document.getElementById('show-detail-tip').style.display="block";
	document.getElementById("zhezhaobg").style.display="block";
}
function HideDetailTip(){
	document.getElementById('show-detail-tip').style.display="none";
	document.getElementById("zhezhaobg").style.display="none";
}
function ShowDetailInfo(){
	document.getElementById('show-detail-info').style.display="block";
	document.getElementById("zhezhaobg").style.display="block";
}
function HideDetailInfo(){
	document.getElementById('show-detail-info').style.display="none";
	document.getElementById("zhezhaobg").style.display="none";
}
 
function AreYouSour(eve){
	var result = confirm('您确定要删除该条记录吗！');  
    if(result){  
        alert('删除成功！');  
    }else{  
        alert('不删除！');  
    }  
}
function AreYouSourOut(eve){
	var result = confirm('您确定要退出系统吗！');  
    if(result){  
        alert('欢迎再来！');  
    }else{  
        return false; 
    }  
}
 
function Activeli() {
       function removeActiveClass(node) {
                node.className = '';
       }
        document.querySelector('.table-bar ul ').onclick = function (e) {
        Array.prototype.forEach.call(document.querySelectorAll('.table-bar ul  > li'), removeActiveClass);
        var target = e.target;
        target.className = 'active-li';
        }
} 
/*--------------------QiWang----------------------*/
function ShowQiwangText(){
	document.getElementById("qiwang-text").style.display="block";
	document.getElementById("mark-text").style.display="none";
	document.getElementById("mark-canvars").style.display="none";
}
function ShowMarkText(){
	document.getElementById("mark-text").style.display="block";
	document.getElementById("qiwang-text").style.display="none";
	document.getElementById("mark-canvars").style.display="none";
}
function ShowMarkCanvars(){
    document.getElementById("mark-canvars").style.display="block";
	document.getElementById("mark-text").style.display="none";
	document.getElementById("qiwang-text").style.display="none";
}
/*---------------UnEmp------------------*/
function ShowAllUnEmp(){
    document.getElementById("allUnEmp-table").style.display="block";
	document.getElementById("KaoYan-table").style.display="none";
	document.getElementById("ZhunBei-table").style.display="none";
}
function ShowKaoYan(){
    document.getElementById("allUnEmp-table").style.display="none";
	document.getElementById("KaoYan-table").style.display="block";
	document.getElementById("ZhunBei-table").style.display="none";
}  function ShowZhunBei(){
    document.getElementById("allUnEmp-table").style.display="none";
	document.getElementById("KaoYan-table").style.display="none";
	document.getElementById("ZhunBei-table").style.display="block";
}
/*-----------------------------------------------*/

function ShowTabs(){
	document.getElementById("showtabs-div").style.display="block";
	document.getElementById("zhezhaobg").style.display="block";
}
function HideTabs(){
	document.getElementById("showtabs-div").style.display="none";
	document.getElementById("zhezhaobg").style.display="none";
}
function ShowUpload(){
	document.getElementById("showupload-div").style.display="block";
	document.getElementById("zhezhaobg").style.display="block";
}
function HideUpload(){
	document.getElementById("showupload-div").style.display="none";
	document.getElementById("zhezhaobg").style.display="none";
}

/*-----------------------------------------------------------*/
function ShowAllEmpStu(){
	document.getElementById("allempstu").style.display="block";
    document.getElementById("kaifaempstu").style.display="none";
	document.getElementById("feikaifaempstu").style.display="none";
}
function ShowKaifaEmpStu(){
    document.getElementById("allempstu").style.display="none";
    document.getElementById("kaifaempstu").style.display="block";
	document.getElementById("feikaifaempstu").style.display="none";
}
function ShowFeikaifaEmpStu(){
    document.getElementById("allempstu").style.display="none";
    document.getElementById("kaifaempstu").style.display="none";
	document.getElementById("feikaifaempstu").style.display="block";
}


/*---------------------------------------------------------*/

/*----------------------添加面试学生开始----*/
function ShowAddStu(){
    document.getElementById("showAddstu-div").style.display="block";
    document.getElementById("zhezhaobg").style.display="block";
}
function  HideAddStu(){
    document.getElementById("showAddstu-div").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
    document.getElementById("search-result").style.display="none";
}
function  ShowSearchResult(){
    document.getElementById("search-result").style.display="block";
}
function  ShowMeetResult(){
    document.getElementById("showMeetResult").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideMeetResult(){
    document.getElementById("showMeetResult").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}
/*----------------------添加面试学生结束----*/

/*开启编辑开始*/
function BeginJianjie(){
	 
			document.getElementById("jianjie").removeAttribute('disabled');
		}
function beginBeizhu(){
			document.getElementById("beizhu").removeAttribute('disabled');
		}
function beginBianji(){
						/*alert('ss')*/
						document.getElementById('pingjia').removeAttribute('disabled');
					}
function beginEdit(){
						 /*alert('可以进行编辑！')*/
						document.getElementById("qw-comp").removeAttribute('disabled');
						document.getElementById("qw-gw").removeAttribute('disabled');
						document.getElementById("qw-gz").removeAttribute('disabled');
					}
/*开启编辑结束*/
/*管理员的添加和修改开始*/
function  ShowAdminAdd(){
    document.getElementById("AdminAdd").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideAdminAdd(){
    document.getElementById("AdminAdd").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}
function  ShowAdminUpdate(){
    document.getElementById("AdminUpdate").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideAdminUpdate(){
    document.getElementById("AdminUpdate").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}
/*管理员的添加和修改结束*/

function  ShowStuUpdate(){
    document.getElementById("StuUpdate").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideStuUpdate(){
    document.getElementById("StuUpdate").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}
function  ShowGwAdd(){
    document.getElementById("GangWeiAdd").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideGwAdd(){
    document.getElementById("GangWeiAdd").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}

function  ShowFXAdd(){
    document.getElementById("FangXiangAdd").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideFXAdd(){
    document.getElementById("FangXiangAdd").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}
function  ShowAdminPwd(){
    document.getElementById("AdminPwd").style.display="block";
     document.getElementById("zhezhaobg").style.display="block";
}
function  HideAdminPwd(){
    document.getElementById("AdminPwd").style.display="none";
    document.getElementById("zhezhaobg").style.display="none";
}









