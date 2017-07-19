

//结构化form to json
$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  



//格式化显示时间
function jsonDateFormat(jsonDateStr) {
	
	//空转换
	if(jsonDateStr==''||jsonDateStr=='null'||jsonDateStr==null){		
		return "";
	}
		
	try {
        var date = new Date(parseInt(jsonDateStr, 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
//         var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
//         var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
//         var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        var milliseconds = date.getMilliseconds();
//         return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        return date.getFullYear() + "-" + month + "-" + day;
     }
     catch (ex) {
         return "时间格式转换错误";
     }
}


//sleep
function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
		return;
	}
}




/**
 * 把复杂的列表转换成最简单的列表，中间只有ID字段，用户删除操作
 * @param arr
 * @returns
 */
function todelarr(arr) {
	var objadd; 
	var newarr = [];
	
	//简化传输对象
	arr.forEach(function(item){     	
		 objadd = {"id": item.id};
		 newarr.push(objadd);
	})

	return newarr;
}



/**
 * from reset
 * @returns
 */
function formReset()
{	
	document.getElementById("formSearch").reset()
}


/**
 * 检测一年的最后一天 yyyy-mm-dd 格式
 * 输入是字符串
 * @returns
 */
function checkYlastday(strdate)
{	
	//空转换
	if(strdate==''||strdate=='null'||strdate==null){		
		return false;
	}
		
	try {
        var date = string2date(strdate); 
        var year = date.getFullYear();
        var month = date.getMonth()+1;  //月份要加1
        var day = date.getDate();
        
        //判断逻辑
        if(month==12&&day==31){        	
        	return true;
        	
        }
     }
     catch (ex) {
         return false;
     }
     
     return false;
}

/**
 * 检测一个季度的最后一天 yyyy-mm-dd 格式
 * 输入是字符串
 * @returns
 */
function checkSlastday(strdate)
{	
	//空转换
	if(strdate==''||strdate=='null'||strdate==null){		
		return false;
	}
		
	try {
        var date = string2date(strdate); 
        var year = date.getFullYear();
        var month = date.getMonth()+1;  //月份要加1
        var day = date.getDate();

        //判断逻辑
        if(month%3==0){       	
        	if((month==3||month==12)&&day==31){
        		return true;
        	} else if((month==6||month==9)&&day==30){
        		return true;
        	}
        	
        	
        }
     }
     catch (ex) {
         return false;
     }
     
     return false;
}

/**
 * 检测月份的最后一天 yyyy-mm-dd 格式
 * 输入是字符串
 * @returns
 */
function checkMlastday(strdate)
{	
	
	
	//空转换
	if(strdate==''||strdate=='null'||strdate==null){		
		return false;
	}
		
	try {

        var date = string2date(strdate); 
        var year = date.getFullYear();
        var month = date.getMonth()+1;  //月份要加1
        var day = date.getDate();
    
        //给定月份的最后一天
        var  tmpdate = new Date(year,month,0);  
        
        //月份最后一天比较
        if(date.getDate()==tmpdate.getDate()){        	
        	return true;
        }
       
        
     }
     catch (ex) {
         return false;
     }
     
     return false;
}


/**
 * 字符串转日期 支持格式["2008-8-1","2009/9/2","10/3/2010"]
 * @param str
 * @returns
 */
function string2date(str){
	return new Date(Date.parse(str.replace(/-/g,  "/")));
}

