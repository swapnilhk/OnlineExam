function setCookie(c_name,value,exdays)
{
	var exdate=new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
	document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
	var c_value = document.cookie;
	var c_start = c_value.indexOf(" " + c_name + "=");
	if (c_start == -1)
	{
		c_start = c_value.indexOf(c_name + "=");
	}
	if (c_start == -1)
	{
		c_value = null;
	}
	else
	{
		c_start = c_value.indexOf("=", c_start) + 1;
		var c_end = c_value.indexOf(";", c_start);
		if (c_end == -1)
		{
			c_end = c_value.length;
		}
		c_value = unescape(c_value.substring(c_start,c_end));
	}
	return c_value;
}



function deleteCookie( name, path, domain ) {
	if ( getCookie( name ) ) document.cookie = name + '=' +
			( ( path ) ? ';path=' + path : '') +
			( ( domain ) ? ';domain=' + domain : '' ) +
			';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}
var hour="";
var min="";
var sec="";
function initTime()
{
	hour=getCookie("hour");
	if (hour==null || hour=="")
	{
		hour=0;
	}
	min=getCookie("min");
	if (min==null || min=="")
	{	
		min=10;
	}
	sec=getCookie("sec");
	if (sec==null || sec=="")
	{
		sec=0;
	}	
	runClock(hour);
	setInterval(function(){runClock()},1000);	
	setInterval(function(){saveTime()},1000);	
}

function runClock()
{			
	document.getElementById('clock').innerHTML=hour+":"+min+":"+sec;
	if(sec == 0){
		if(min == 0){
			if(hour == 0){
				deleteCookie("hour");
				deleteCookie("min");
				deleteCookie("sec");
				window.location.href="/OnlineExam/ProcessResult";
			}
			else{
				hour = hour - 1;
				min = 59;
				sec = 59;
			}
		}
		else{
			min = min - 1;
			sec = 59;			
		}		
	}
	else{
		sec = sec - 1;
	}	
}

function saveTime(){
	setCookie("hour", hour, 10);
	setCookie("min", min, 10);
	setCookie("sec", sec, 10);
}
