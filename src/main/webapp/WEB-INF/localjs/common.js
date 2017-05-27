

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






