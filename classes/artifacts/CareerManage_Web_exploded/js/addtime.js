function addtime(){
	addtime1();
	addtime2();
}
function addtime1(){
	//alert('kkkk');
	var time1=document.getElementById("add_time");
	var inner1="<option value='1'>1时</option>"
	+"<option value='2'>2时</option>"
	+"<option value='3'>3时</option>"
	+"<option value='4'>4时</option>"
	+"<option value='5'>5时</option>"
	+"<option value='6'>6时</option>"
	+"<option value='7'>7时</option>"
	+"<option value='8'>8时</option>"
	+"<option value='9'>9时</option>"
	+"<option value='10'>10时</option>"
	+"<option value='11'>11时</option>"
	+"<option value='12'>12时</option>"
	+"<option value='13'>13时</option>"
	+"<option value='14'>14时</option>"
	+"<option value='15'>15时</option>"
	+"<option value='16'>16时</option>"
	+"<option value='17'>17时</option>"
	+"<option value='18'>18时</option>"
	+"<option value='19'>19时</option>"
	+"<option value='20'>20时</option>"
	+"<option value='21'>21时</option>"
	+"<option value='22'>22时</option>"
	+"<option value='23'>23时</option>"
	+"<option value='24'>24时</option>";
	time1.innerHTML=inner1;
	
}
function addtime2(){
	var time2=document.getElementById("add_time2");
	var inner2="<option value='1'>5分</option>"
	+"<option value='2'>10分</option>"
	+"<option value='3'>15分</option>"
	+"<option value='4'>20分</option>"
	+"<option value='5'>25分</option>"
	+"<option value='6'>30分</option>"
	+"<option value='7'>35分</option>"
	+"<option value='8'>40分</option>"
	+"<option value='9'>45分</option>"
	+"<option value='10'>50分</option>"
	+"<option value='11'>55分</option>";
	time2.innerHTML=inner2;
}
/*----------------------------年份月份*/
function addYearAndM(){
	addYearAndM1();
	addYearAndM2();
}
function addYearAndM1(){
	//alert('kkkk');
	var year1=document.getElementById("myyear");
	var innery1="<option value='1'>2020</option>"
				+"<option value='2'>2019</option>"
				+"<option value='3'>2018</option>"
				+"<option value='4'>2017</option>"
				+"<option value='5' >2016</option>"
				+"<option value='6'>2015</option>"
				+"<option value='7'>2014</option>";
	year1.innerHTML=innery1;
}
function addYearAndM2(){
	var month2=document.getElementById("mymonth");
	var innerm2="<option value='1'>1</option>"
	+"<option value='2'>2</option>"
	+"<option value='3'>3</option>"
	+"<option value='4'>4</option>"
	+"<option value='5'>5</option>"
	+"<option value='6'>6</option>"
	+"<option value='7'>7</option>"
	+"<option value='8'>8</option>"
	+"<option value='9'>9</option>"
	+"<option value='10'>10</option>"
	+"<option value='11'>11</option>"
	+"<option value='12'>12</option>";
	month2.innerHTML=innerm2;
}





			