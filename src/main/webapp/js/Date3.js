function choose_date_czw(date_id,objtd){
if(date_id=="choose_date_czw_close"){
    document.getElementById("choose_date_czw_id").style.display="none";
    return;
}

if(objtd!=undefined){
    if(objtd=="choose_date_czw_empty"){
        document.getElementById(date_id).value="";
    }else{
        var year1 = document.getElementById("choose_date_czw_year").value;
        var month1 = document.getElementById("choose_date_czw_month").value;
        document.getElementById(date_id).value=year1+"-"+month1+"-"+objtd.innerHTML;
    }
    document.getElementById("choose_date_czw_id").style.display="none";
    return;
}
var nstr=new Date(); //当前
if(document.getElementById("choose_date_czw_year")!=null){
    var year = document.getElementById("choose_date_czw_year").value;
    var month = document.getElementById("choose_date_czw_month").value;
    var str=year+"/"+month+"/1";
    nstr=new Date(str); //当前
}
var ynow=nstr.getFullYear(); //年份
var mnow=nstr.getMonth(); //月份
var dnow=nstr.getDate(); //今日日期
var n1str=new Date(ynow,mnow,1); //当月第一天
var firstday=n1str.getDay(); //当月第一天星期几
function is_leap(year) {
   return (year%100==0 ? res=(year%400==0 ? 1 : 0) : res=(year%4==0 ? 1: 0));
}
var dstr="<select id=\"choose_date_czw_year\" onchange=\"choose_date_czw('"+date_id+"')\">";
for(var y=1901;y<2050;y++){
    if(y==ynow){
        dstr+="<option value='"+y+"' selected>"+y+"</option>"
    }else{
        dstr+="<option value='"+y+"'>"+y+"</option>"
    }
}
dstr+="</select>&nbsp;<select id=\"choose_date_czw_month\" onchange=\"choose_date_czw('"+date_id+"')\">";
for(var m=1;m<13;m++){
    if(parseInt(mnow+1)==m){
        dstr+="<option value='"+m+"' selected>"+m+"</option>"
    }else{
        dstr+="<option value='"+m+"'>"+m+"</option>"
    }
}
dstr+="</select>&nbsp;<span style='cursor:pointer;' onclick=\"choose_date_czw('choose_date_czw_close')\">关闭</span>|<span style='cursor:pointer;' onclick=\"choose_date_czw('"+date_id+"','choose_date_czw_empty')\">清空</span>";
//一三五七八十腊(十二月),三十一日永不差;四六九冬(十一月)三十日,唯有二月二十八(闰年二十九).
var m_days = new Array(31,28+is_leap(ynow),31,30,31,30,31,31,30,31,30,31);
var tr_str=Math.ceil((m_days[mnow] + firstday)/7);
dstr+="<table border='0' cellpadding='5' cellspacing='0' width='100%'><tr><td>日</td><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td>六</td></tr>";
var dqdate=new Date(); //当前
for(i=0;i<tr_str;i++) { //外层for语句 - tr标签
   dstr+="<tr>";
   for(k=0;k<7;k++) { //内层for语句 - td标签
      idx=i*7+k; //表格单元的自然序号
      date_str=idx-firstday+1; //计算日期
     if(date_str<=0 || date_str>m_days[mnow]){
          dstr+="<td>&nbsp;</td>";
     }else{
        if(ynow==dqdate.getFullYear() && mnow==dqdate.getMonth() && dqdate.getDate()==date_str){
            dstr+="<td onmouseover=\"this.style.backgroundColor='#6FF'\" onmouseout=\"this.style.backgroundColor='#fff'\" onclick=\"choose_date_czw('"+date_id+"',this)\" style='cursor:pointer; background-color:#6FF;'>"+date_str+"</td>";
        }else{
            dstr+="<td onmouseover=\"this.style.backgroundColor='#6FF'\" onmouseout=\"this.style.backgroundColor='#fff'\" onclick=\"choose_date_czw('"+date_id+"',this)\" style='cursor:pointer;'>"+date_str+"</td>";
        }
     }
   }
   dstr+="</tr>";
}
dstr+="</table>";
if(document.getElementById("choose_date_czw_id")==null){
var obj = document.getElementById(date_id);
var odiv = document.createElement("div");
odiv.id="choose_date_czw_id";
odiv.innerHTML=dstr;
 odiv.style.position="fixed";
odiv.style.border="1px #0CF solid";
odiv.style.fontSize="12px";
odiv.style.zIndex=99999;
odiv.style.top=obj.offsetTop+obj.offsetHeight+"px";
//odiv.style.top="0px";
odiv.style.left="370px";
odiv.style.margin="10%";

document.body.appendChild(odiv);
}else{
    document.getElementById("choose_date_czw_id").style.display="block";
    document.getElementById("choose_date_czw_id").innerHTML=dstr;
}
 
}


