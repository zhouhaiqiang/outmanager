/********************************************************************************
 *第三层：业务层
 *作  者：张艳为 
 *时  间：2012年8月24日
 *说  明：该文件根据业务的需求，封装了相关函数。使用该文件前，需要先加载basic.js文件
 *注  意：该文件的内容可以根据业务的需要进行适当的修改
 ********************************************************************************/
 

 
/*<----------------------------------------分割线---------------------------------------->*/



/*
 *全局变量
 */
 
var g_xmlDoc = null;   //XML文档对象，用于给<合并文档>业务使用
var YNHongtou="0";
var YNiReadOnly="2";
var YNiShowTrace = "0";
var PreDir="filestore/";

/*<----------------------------------------分割线---------------------------------------->*/
 


/*
 *辅助函数
 */
/********************************************************************************
 *函数功能：检测WORD控件2.0是否安装
 *参数说明：
 *  无
 *返回值：安装了控件2.0则返回true, 否则返回false
 ********************************************************************************/
function IsInstallCtrl2()
{
    try
    {
        new ActiveXObject("TWOADOCCTRL.TwOADocCtrl.1");
    }
    catch(err)
    {
        return false;
    }
    return true;
}

/********************************************************************************
 *函数功能：加载XML格式的字符串
 *  sInfo:XML格式的字符串
 *返回值：加载成功则返回XML DOM对象，失败则返回null
 ********************************************************************************/
function LoadXMLString(sInfo)
{
    try
    {
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async = false;
        xmlDoc.loadXML(sInfo);
        return xmlDoc;
    }
    catch(err)
    {
    }
    return null;
}

/********************************************************************************
 *函数功能：从上传文件或下载文件的错误代码中获取字符串描述信息
 *参数说明：
 *  iErrorCode:错误代码
 *返回值：返回错误代码的相关描述信息
 ********************************************************************************/
 function GetDescriptionFromErrorCode(iErrorCode)
 {
    var res;
    try
    {
        switch(iErrorCode)
        {
        case 0://错误代码为0，表明没有错误
            res = "OK";
            break;
        case 1:
            res = "文件服务器上已存在该文件";
            break;
        case 2:
            res = "用户取消了传输过程";
            break;
        case 3:
            res = "系统内存不足";
            break;
        case 4:
            res = "与服务器建立传输线程失败";
            break;
        case 5:
            res = "连接到代理服务器后，检测到代理服务器版本错误";
            break;
        case 6:
            res = "通讯协议错误";
            break;
        case 7:
            res = "文件不存在或者没有权限读写该文件";
            break;
        case 8:
            res = "在文件下载的过程中接收到的文件实际数据和服务器指定大小不一致";
            break;
        case 9:
            res = "读取文件失败";
            break;
        case 10:
            res = "写文件失败";
            break;
        case 16:
            res = "建立socket失败";
            break;
        case 17:
            res = "设置socket选项失败";
            break;
        case 18:
            res = "获取服务器地址失败";
            break;
        case 19:
            res = "无法连接到文件服务器";
            break;
        case 20:
            res = "网络超时";
            break;
        case 21:
            res = "发送网络数据失败";
            break;
        case 22:
            res = "接收网络数据失败";
            break;
        case 23:
            res = "连接已关闭";
            break;
        case 24:
            res = "未知的网络错误";
            break;
        case 61:
            res = "Bind错误";
            break;
        case 62:
            res = "Closesocket错误";
            break;
        case 63:
            res = "Select错误";
            break;
        case 64:
            res = "Ioctlsocket错误";
            break;
        case 65:
            res = "连接准备好";
            break;
        case 66:
            res = "连接没准备好";
            break;
        case 67:
            res = "连接建立";
            break;
        case 68:
            res = "连接没建立";
            break;
        default:
            res = "服务器产生未知错误";
            break;
        }
    }
    catch(err)
    {
        res = "未知错误";
    }
    return res;
 }
 
/********************************************************************************
 *函数功能：从一个文件路径中提取出文件名
 *参数说明：
 *  sFile:文件路径+文件名
 *返回值：成功文件名，失败返回null
 ********************************************************************************/
function GetTitleFromPath(sFile)
{
    if(!sFile)
        return null;
    
    //从最后开始查找字符'\'或者'/'
    var index=sFile.lastIndexOf('\/');
    if(index==-1)
    {
        index = sFile.lastIndexOf('\\');
        if(index == -1)
            return null;
    }
    
    return sFile.substring(index+1, sFile.length);
}
 
/********************************************************************************
 *函数功能：将一个路径中的“d:/filestore/“ 替换成"filestore/"
 *参数说明：
 *  sPathName:文件路径+文件名
 *返回值：返回替换后的字符串
 ********************************************************************************/
function GetFormatPath(sPathName)
{
    var reg = new RegExp;
    reg = /\/+/g;
    //将sPathName中所有的'/'替换成'\'
    sPathName = sPathName.replace(reg, "\\");
    //将"d:/filestore/"替换成"filestore/"
    reg = /^d:[\/\\]+(filestore)[\/\\]+/i;
    sPathName = sPathName.replace(reg, "filestore\\");
    return sPathName;
}
 
/********************************************************************************
 *函数功能：禁用或者启用与修订相关的按钮
 *参数说明：
 *  bCan【可选】:为true则可用，为false则禁用。默认值为false
 *返回值：成功返回OK,失败返回相应错误代码
 ********************************************************************************/
function EnableReviseButton(bCan)
{
    var res;
    res = "OK";
    bCan = bCan ? true : false;
    try
    {
        var controls, control, count;
        
        //禁用审阅工具条上的与修订相关的按钮
        controls = g_wordApp.CommandBars("Reviewing").Controls;
        count = controls.Count;
        for(var i=1; i<=count; i++)
        {
            control = controls(i);
            
            //拒绝修订的Id号为6240， 接收修订的Id号为6243，修订的Id号为2041
            if((control.Id==6240) || (control.Id==6243) || (control.Id==2041))
                control.Enabled = bCan;
        }
        
        //禁用Track Changes Indicator弹出菜单上的与修订相关的按钮
        controls = g_wordApp.CommandBars("Track Changes Indicator").Controls;
        count = controls.Count;
        for(var i=1; i<=count; i++)
        {
            control = controls(i);
            
            //修订的Id号为2041
            if(control.Id==2041)
            {
                control.Enabled = bCan;
                break;
            }
        }
        
        //禁用Track Changes弹出菜单上的与修订相关的按钮
        controls = g_wordApp.CommandBars("Track Changes").Controls;
        count = controls.Count;
        for(var i=1; i<=count; i++)
        {
            control = controls(i);
            
            //接收当前修订的Id号为1715， 拒绝当前修订的Id号为6243，修订的Id号为2041
            if((control.Id==1715) || (control.Id==1716) || (control.Id==2041))
                control.Enabled = bCan;
        }
        
        //禁用工具菜单中的修订按钮
        controls = g_wordApp.CommandBars("Menu Bar").Controls;
        controls = controls("工具(&T)").Controls;
        count = controls.Count;
        for(var i=1; i<=count; i++)
        {
            control = controls(i);
            
            //修订的Id号为2041
            if(control.Id==2041)
            {
                control.Enabled = bCan;
                break;
            }
        }
    }
    catch(err)
    {
        res = GenerateErrorString("EnableReviseButton", err);
    }
    
    try
    {
        //所做的修改不要保存在模版里
        g_wordApp.NormalTemplate.Saved = true;
    }
    catch(err)
    {
    }
    
    return res;
}



/*<----------------------------------------分割线---------------------------------------->*/



/*
 *常用业务
 */

/********************************************************************************
 *函数功能：上传附件(包括文档)
 *参数说明：
 *  sIP:文件服务器IP地址
 *  sPort:文件传输服务端口号
 *  sRemoteFile:文件在服务器上存放的位置(包含上传后的文件名)
 *  sLocalFile:要上传的本地文件名(包含路径)
 *返回值：成功返回OK，失败返回相应错误代码
 ********************************************************************************/
 /*
  function UploadFile(sIP, sPort, sRemoteFile, sLocalFile)
 {
    try{
        var errcode;
        errcode = g_docCtrl.FileUploadInDialog(sIP, sPort, GetFormatPath(sRemoteFile), sLocalFile, "正在上传文件...");
        if(errcode)
        {
            res = "上传文件失败！\r\n";
            res += "文件名:" + sLocalFile + "\r\n";
            res += "原因:" + GetDescriptionFromErrorCode(errcode);
        }
        else
            res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("UploadFile", err);
    }
    return res;
 }*/
  
/*获取选择的文件路径，可多选返回包含文件路径的数组*/
function GetOpenFile(){

	var filepath = g_docCtrl.GetOpenFile();
	
	var arrPath = new Array(); 
	var xml = new ActiveXObject("Microsoft.XMLDOM");
	xml.loadXML(filepath);
	$(xml).find("item").each(function(i){
		var filepath = $(this).text();
		arrPath[i] = filepath;
	});
	return arrPath;
}
/*
获取路径中的文件名

*/
function getFileName(path){
		var pos1 = path.lastIndexOf('/');
		var pos2 = path.lastIndexOf('\\');
		var pos  = Math.max(pos1, pos2)
		if( pos<0 )
		return path;
		else
		return path.substring(pos+1);
}
  function UploadFile(sIP, sPort, sRemoteFile, sLocalFile)
 {
 	var isJMFile='NO';
    try{
    	var jmip = document.getElementById('jmip').value;
    	var jmport = document.getElementById('jmport').value;
    	isJMFile = g_docCtrl.IsEncrytedFile(sLocalFile);
    }catch(e){}
    
    try{
    	var errcode;
    	//如果是加密文件或加密流程（合同系统不存在加密流程），则走西门子文件代理上传，其他非加密文件直接走拓维文件传输服务   	
    	if(isJMFile=='OK' || sRemoteFile.indexOf("--JM") != -1){
    		//上传加密文件
    		errcode = g_docCtrl.FileUploadInDialog(jmip, jmport, GetFormatPath(sRemoteFile), sLocalFile, "正在上传文件...");
    	}else{
    		errcode = g_docCtrl.FileUploadInDialog(sIP, sPort, GetFormatPath(sRemoteFile), sLocalFile, "正在上传文件...");
    	}                
        if(errcode)
        {
            res = "上传文件失败！\r\n";
            res += "文件名:" + sLocalFile + "\r\n";
            res += "原因:" + GetDescriptionFromErrorCode(errcode);
        }
        else
            res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("UploadFile", err);
    }
    return res;
 }

 /*
 
 function UploadFile(sIP, sPort, sRemoteFile, sLocalFile)
 {
    //判断西门子客户端是否存在
    var bClientExist;
    var client
    var bClientCode;
    var bClientCodeMeaning="";
    try
    {
        //如果西门子客户端不存在，下面这句将会引发异常        
        //client.SetWSUrl("http://192.168.140.16:9080/repository/repository.ws");
        bClientCode=CheckNewSiemensClient();
        if(bClientCode==0){
        	client = new ActiveXObject("SecDocOcx.SecDoc.1");
        	bClientExist = true;
        }else{
        	bClientExist = false;
        }
        
    }
    catch(e)
    {
        bClientExist = false;
    }
    switch(bClientCode){
    	case 101:
    		bClientCodeMeaning="需安装文档内容安全客户端，请在统一信息平台下载最新的客户端进行安装";
    	break;
    	case 102:
    		bClientCodeMeaning="需注册文档内容安全加密控件";
    	break;
    	case 103:
    		bClientCodeMeaning="需安装文档内容安全客户端，请在统一信息平台下载最新的客户端进行安装";
    	break;
    }
    
    //alert("bClientExist:"+bClientExist);
    var isJMFlow=false;
    try{
    	//流程id
		var flowid=document.getElementById("flowid").value;	
		//主流程id
		var mainFlowid=document.getElementById("mainFlowid").value;	
		//alert("mainFlowid:"+mainFlowid);
		//判断是否为加密流程   流程id以--JM结尾
		isJMFlow=flowid.endWith("--JM");
    }catch(e){}
    //alert("isJMFlow:"+isJMFlow);
	
    var res="";
    var returnXml=""; 
    try
    {	while(sLocalFile.indexOf( "\\\\" ) != -1 ) {
			sLocalFile=sLocalFile.replace("\\\\","\\");			
		}
		while(sLocalFile.indexOf( "//" ) != -1 ) {
			sLocalFile=sLocalFile.replace("//","/");			
		}
		while(sLocalFile.indexOf( "/" ) != -1 ) {
			sLocalFile=sLocalFile.replace("/","\\");			
		}
    	//如果已安装文档内容安全客户端，且为加密流程，直接调用西门子加密服务，加密、赋权
        if(bClientExist && isJMFlow)
        {
            //西门子客户端存在，将文件交由西门子客户端处理
            var xml=getFileXml(isJMFlow?1:0,flowid,mainFlowid,1,sLocalFile);
            returnXml = client.DocSecService(xml);
        }else if(bClientExist && !isJMFlow){
        	//如果已安装文档内容安全客户端，且为非加密流程，调用西门子接口判断文件是否为加密文件
        	var isJMFile = client.CheckFileType(sLocalFile);
        	if(isJMFile=="1"){
        		//如果在非加密流程中上传加密附件，调用西门子加密服务赋权
        		var xml=getFileXml(isJMFlow?1:0,flowid,mainFlowid,1,sLocalFile);
        		returnXml = client.DocSecService(xml);
        	}
        }else if(!bClientExist && isJMFlow){alert("此公文为加密公文，"+bClientCodeMeaning);
        	//如果未安装文档内容安全客户端，且是加密流程，直接返回错误
        	return "此公文为加密公文，"+bClientCodeMeaning;
        }else if(!bClientExist && !isJMFlow){
        	//如果未安装文档内容安全客户端，且不是加密流程，调用拓维接口判断是否为加密附件（西门子提供dll）
        	var isJMFile = g_docCtrl.IsEncrytedFile(sLocalFile);
        	if(isJMFile=="OK"){
        		//附件为密文
        		return "在非加密流程中上传加密附件，"+bClientCodeMeaning+"，加密文件路径："+sLocalFile;
        	}
        }
        //alert(returnXml);
        if(returnXml!=""){
        	//已调用西门子加密服务，上传文件路径可能发生改变
        	var xmlDoc=LoadXMLString(returnXml);
        	docs = xmlDoc.documentElement.childNodes;
			//获取错误码
			errcode=docs[1].childNodes[0].nodeValue;
			switch(errcode){
			    case "0":
			    	res="";
			    break;
				case "101":
					res="xml格式错误";
				break;
				case "102":
					res="非加密流程";
				break;
				case "103":
					res="待加密文件不存在";
				break;
				case "104":
					res="生成密钥失败";
				break;
				case "105":
					res="加密密钥失败";
				break;
				case "106":
					res="连接was服务失败";
				break;
				case "107":
					res="was返回的文档id无效";
				break;
				case "108":
					res="加密文件失败";
				break;
				case "109":
					res="was返回的文档密钥无效";
				break;
				case "112":
					res="您无权使用该加密文件";
				break;
				default:
				    res="文档内容安全客户端接口异常，请联系管理员。";
			}
			if(res!=""){
			    //alert(res);
				//alert("调用接口失败，原因是:" + res);
				return res;
			}
			//获取fileId和filePath
			files = docs[0].getElementsByTagName("file")[0];
			fileId=files.getElementsByTagName("fileId")[0].childNodes[0].nodeValue;
			filePath=files.getElementsByTagName("filePath")[0].childNodes[0].nodeValue;
			sLocalFile=filePath;
        }
        
        //调用文档控件的上传方法开始上传文件
        var errcode;
        errcode = g_docCtrl.FileUploadInDialog(sIP, sPort, GetFormatPath(sRemoteFile), sLocalFile, "正在上传文件...");
        //alert("errcode:"+errcode);
        if(errcode)
        {
            res = "上传文件失败！\r\n";
            res += "文件名:" + sLocalFile + "\r\n";
            res += "原因:" + GetDescriptionFromErrorCode(errcode);
        }
        else
            res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("UploadFile", err);
    }
    return res;
 }
 */
/********************************************************************************
 *函数功能：下载附件(包括文档)
 *参数说明：
 *  sIP:文件服务器IP地址
 *  sPort:文件传输服务端口号
 *  sRemoteFile:文件在服务器上存放的位置(包含上传后的文件名)
 *  sLocalFile:要下载到本地的文件名(包含路径)
 *返回值：成功返回OK，失败返回相应错误代码
 ********************************************************************************/
function DownloadFile(sIP, sPort, sRemoteFile, sLocalFile)
{
	//对TWTemp文件夹的处理，删除前三天文件夹，下载西门子的临时文件
	try{
		var toDay = new Date();
		var newDay = new Date();
		for(var i=1;i<=3;i++){
			var newDay_ms = toDay.getTime()-1000*60*60*24*i;
			newDay.setTime(newDay_ms); 
			
			day = newDay.getDate();
			month = newDay.getMonth()+1;
			year = newDay.getFullYear();
			
			var reMoveStr = "C:\\TWTemp\\" + year + month + day;
			g_docCtrl.RemoveDirectory(reMoveStr)
		}
		
		if(g_docCtrl.IsFileExist("C:\\TWTemp\\temp.doc")=="NO"){
	        var formateRemoteFileTemp;
	        var sRemoteFileTemp = "d:/filestore/temp.doc";
	        formateRemoteFileTemp = GetFormatPath(sRemoteFileTemp);
	        g_docCtrl.FileDownload(sIP, sPort, formateRemoteFileTemp, "C:\\TWTemp\\temp.doc");
		}
	}catch(err){
    //	alert("临时文件夹处理出错，请安装最新的附件控件");
    }
	
    var res;
    try
    {
        var errcode;
        var formateRemoteFile;
       
        formateRemoteFile = GetFormatPath(sRemoteFile);
        //console.log("g_docCtrl: " + g_docCtrl + ", sIP: " + sIP + ", sPort: " + sPort + ", sRemoteFile: " + sRemoteFile + ", formateRemoteFile: " + formateRemoteFile + ", sLocalFile: " + sLocalFile);
        errcode = g_docCtrl.FileDownloadInDialog(sIP, sPort, formateRemoteFile, GetFormatPath(sLocalFile), "正在下载文件...");
        if(errcode)
        {
            res = "下载文件失败！\r\n";
            res += "文件名:" + formateRemoteFile + "\r\n";
            res += "原因:" + GetDescriptionFromErrorCode(errcode);
        }
        else
            res = "OK";
            
    }
    catch(err)
    {
        res = GenerateErrorString("DownloadFile", err);
    }
    return res;
}
 
function OpenLocalWord(sFile, iVisible, iReadOnly, sUsername)
{
	OpenWord(sFile, iVisible, iReadOnly, sUsername);
	if (!g_docCtrl.attachEvent || g_docCtrl.GetIEVersion() == '11') {
		g_docCtrl.BlockBrowser();
		g_bOpen = false;
	}
}
/********************************************************************************
 *函数功能：全文批注
 *参数说明：
 *  sIP:文件服务器IP地址
 *  sPort:文件传输服务端口号
 *  sUsername:当前用户名(中文名)
 *  sRemoteFile:在服务器上的正文原稿(路径+正文原稿名)
 *  sUpRemoteFile:编辑后的文档要上传到服务器上的路径(路径+正文原稿名)
 *  sLocalPath:正文原稿在客户端的存储路径(不包含文档名)
 *  sRemoteHongtou:在服务器上的红头文件(路径+红头文件名)。如果为该参数传递null或者空字符串，则表明不需要红头文件
 *  sLocalHongtouPath:下载到本地后红头文件的存储路径(不包含红头文件名)。只有当sRemoteHongtou不是空时，该参数才有效
 *  sFlowID:流程ID
 *  iRevise【可选】:0表示不打开，非0表示打开。默认不打开修订功能
 *  iShowTrace【可选】:非0表示显示痕迹，0表示不显示痕迹。默认不显示痕迹
 *  iReadOnly【可选】:0表示以非只读方式打开，非0以只读方式打开。默认以非只读方式打开
 *返回值：成功返回OK，失败返回相应错误代码
 ********************************************************************************/
function EditWord(sIP, sPort, sUsername, sRemoteFile, sUpRemoteFile, sLocalPath, sRemoteHongtou,whether,json, sLocalHongtouPath, 
            sFlowID, iRevise, iShowTrace, iReadOnly) {
    var m_hasDocOpen    = false;    //是否有文档处于打开状态
    var m_hasHongtou    = false;    //在正文原稿中是否存在红头
    var m_sLocalFile    = null;     //下载到本地的正文原稿路径
    var m_sLocalHongtou = null;     //下载到本地的红头文件路径
    var m_sRes          = "OK";     //返回结果
    var m_sUserName     = sUsername;//保存用户名称
    
    try {
        //设置本地正文原稿路径名(正文原稿本地路径 + 正文原稿名)
        m_sLocalFile = sLocalPath + "\\" + GetTitleFromPath(sRemoteFile);  
        //比较sRemoteFile和sUpRemoteFile，并获取正文原稿的最近被编辑路径(包含文件名)
        var sNewestFile = g_docCtrl.GetNewestDocPath(sIP, sPort, sRemoteFile, sUpRemoteFile);
        if (sNewestFile == "") {
        	
            throw new Error(0, "无法获取正文原稿在服务器上的路径");
        }
          
        //备份m_sLocalFile指向的文档
        PrBackupDoc(m_sLocalFile);
        //下载正文原稿
        m_sRes = DownloadFile(sIP, sPort, sNewestFile, m_sLocalFile);
        if (m_sRes != "OK") throw new Error(0, m_sRes);
        //打开正文原稿
        //alert(m_sLocalFile+"+++"+sUsername);
        m_sRes =OpenWord(m_sLocalFile, 0, 0, sUsername); 
        if (m_sRes!="OK") throw new Error(0, m_sRes);
        m_hasDocOpen = true;
        //如果sRemoteHongtou不为空，则正文原稿需要红头
        if (sRemoteHongtou) {
            m_hasHongtou = true;
            //设置红头文件下载到本地的路径（红头文件本地路径 + 红头文件名）
            m_sLocalHongtou = sLocalHongtouPath + "\\" + GetTitleFromPath(sRemoteHongtou);
            //下载红头文件
            m_sRes = DownloadFile(sIP, sPort, sRemoteHongtou, m_sLocalHongtou);
            
            if (m_sRes != "OK") throw new Error(0, m_sRes);
            //插入红头(如果插入红头失败，则直接跳过该错误，不报告给用户)
            try {
                var shape;
                //先关闭修订功能
                g_curDoc.TrackRevisions = false;
                //将红头文件插入到正文原稿最开头
                var range = g_curDoc.Range(0, 0);
                shape = range.inlineShapes.AddOLEObject("", m_sLocalHongtou, false, false);
                //AlternativeText的值作为删除红头时的标志（2012年11月29日增加）
                shape.AlternativeText = "TwHongtou";
                //清除撤销列表，防止添加的红头被用CTRL+Z给撤销掉
                g_curDoc.UndoClear();
            } catch (e) {
                //由于插入红头失败，则正文原稿的开头不会有红头文件存在
                m_hasHongtou = false;
            }
        }
      
        //设置打开文档的方式
		if(YNiReadOnly=="1")
		{
			iReadOnly = YNiReadOnly;
		}
		//else if(YNiReadOnly=="2")
		//{
		//	iRevise = YNiReadOnly;
		//}
		if(YNiShowTrace=="1")
		{
			iShowTrace = YNiShowTrace;
		}
		
		//iShowTrace = "1";
        if (iReadOnly) {
            //以只读方式打开文档
            PrEditByReadOnly();
        } else if(iRevise) {
            //以修订方式打开文档
            PrEditByRevision();
        } else {
            //以正常方式(可读写且非修订)打开文档 
            g_curDoc.TrackRevisions = false;
        }  
        //是否显示痕迹
        iShowTrace = (iShowTrace ? 1 : 0);
        g_curDoc.ShowRevisions = iShowTrace;
        g_curDoc.PrintRevisions = iShowTrace;
        g_wordApp.ActiveWindow.ActivePane.View.RevisionsView = 0;
        
        if (g_docCtrl.attachEvent || g_docCtrl.GetIEVersion() != '11') {
        	//alert("非IE11,使用attachEvent注册事件");
	        try{
	        	g_docCtrl.attachEvent("OnDocmentClose", PrOnDocmentClose);   	
	        }catch(err){
	        	alert(err.message);
	        }      
        }  
        else {
        	//alert("IE11, 不支持attachEvent方法");
        }
        
        //目前ie11下暂不能注册word关闭事件（addEventListener也注册不上），因此只能允许只读打开文档，后续跟踪处理
//    	if(!g_docCtrl.attachEvent){
//    		alert("对不起，正文的在线编辑功能暂不支持您电脑的浏览器(IE)版本，但您可正常阅读正文！");
//    		PrEditByReadOnly();    		
//    	}
        
        //最大化Word应用程序窗口
        g_wordApp.WindowState = 1;
        //获取当前文档对应的窗口
        var window = g_curDoc.Windows(1);
        //显示Word文档窗口
        window.Visible = true;
        //激活Word应用程序
        g_wordApp.Activate();
        //锁定正文原稿页面（无需锁定,以弹出页面）
        //g_docCtrl.BlockBrowser();
        
        //解决ie11下面不能成功注册回调函数问题,采用人工锁定页面回调方式
        try{
	        if (!g_docCtrl.attachEvent || g_docCtrl.GetIEVersion() == '11') {
	        	//alert("IE11, 主动阻塞浏览器并调用PreOnDocmentClose()");
	            g_docCtrl.BlockBrowser();
	            PrOnDocmentClose();
	            g_docCtrl.ReleaseControl();
	        }
        }catch(err){
        	//非ie11情况下此代码会产生错误信息,这里捕获后只保存信息不做其它处理
        	//GenerateErrorString("EditWord", err);
        }
        
        return m_sRes;
    } catch(err) {
        //如果文档已经打开，则关闭文档
        if (m_hasDocOpen) {
            CloseWord();
            m_hasDocOpen = false;
        }
        //保存错误描述
        m_sRes = GenerateErrorString("EditWord", err);
    }
    //对网页做处理
    /*try {
        window.opener.document.all.LabFileName.disabled=false;
        window.opener.document.all.Submit6.disabled=false;
    } catch(err) {
    }*/
    return m_sRes;
    //处理WORD文档关闭事件，需要判断是否有文件锁
    function PrOnDocmentClose(info) {
        var isDeblockBrowser="NO";
        try {
            //将打开标识置为false,表明正文原稿已经关闭
            m_hasDocOpen = false;
            //解除正文原稿页面锁定
    	    //isDeblockBrowser=g_docCtrl.DeblockBrowser();
            //删除动态添加的WORD关闭事件
            //g_docCtrl.detachEvent("OnDocmentClose", PrOnDocmentClose);
            if (g_docCtrl.attachEvent || g_docCtrl.GetIEVersion() != '11') {
            	  //alert("非IE11, 注销已经注册的事件");
            	  g_docCtrl.detachEvent("OnDocmentClose", PrOnDocmentClose)
                //removeEvent(g_docCtrl,"DocmentClose", PrOnDocmentClose);
            }
            else {  // IE11这时候原WORD文档已经关闭,为了进行下面的操作必须再次并原文档打开
            	  g_bOpen = false;
            	  //alert("IE11, 重新打开WORD");
            	  var ret_val = OpenWord(m_sLocalFile, 0, 0, m_sUserName);
            	  //alert("测试-ie11重新打开状态="+ret_val);
            }
            
            //隐藏WORD
            g_wordApp.Visible = false;
            //如果文档是以只读或者修订方式打开的，则需解锁文档
            if (iReadOnly || iRevise) {
                g_docCtrl.UnLoclFile("123");
            }
            //如果红头文件存在，则删除正文原稿开头的红头
            if (m_hasHongtou) {
                PrDeleteHongtou();
            }
          
            //保存文档
            g_curDoc.Save();               
            //将文档另存为，这样为了保证当前打开的文档是完全关闭的
            g_docCtrl.EnableSaveAs(1);
            
            g_curDoc.SaveAs(m_sLocalFile + "_SaveAs");
             //业务上查询文件锁状态 bysf
            var ret="0";
            if (iReadOnly==0){
		//		ret=closeQueryFileLock(sFlowID,sUsername);
				//alert("yewu:"+ret);
				if(ret!=0){
					//调用另存接口 
					SaveFileToOtherPath(m_sLocalFile);
					iReadOnly=1;
				}
			  } 
			  
			// 在IE11下再次进行了打开操作,所以在这要再次主动关闭WORD
			var IEVersion = "";
			try{
				if(g_docCtrl.GetIEVersion()){
					IEVersion = g_docCtrl.GetIEVersion();
				}
			}catch(err){
				//非IE11环境下无GetIEVersion()方法
			}
			
			//alert("IE Version=" + IEVersion);
			if (!g_docCtrl.attachEvent || IEVersion == '11') {
				  CleanWord();
				  //alert("before close word 关闭前状态："+g_bOpen);
				  
				  /**----------------------------------------------------
				   * by zengzb 在word控件关闭方法只有当g_bOpen为true时才执行，
				   * 而目前这里会调用CleanWord方法会将g_bOpen初始化为false
				   *----------------------------------------------------*/
				  if(!g_bOpen){
					  g_bOpen = true;
				  }
				  CloseWord();
				  //alert("g_bOpen="+g_bOpen);
			}			  

            //如果文档是以非只读方式打开的，则需上传文档
            if (!iReadOnly) {
            	//alert("非只读需要上传文件")
                m_sRes = UploadFile(sIP, sPort, sUpRemoteFile, m_sLocalFile);
				if(whether!="0")
				{
					whether = "1";
				}
				var upattpath=sUpRemoteFile.replace(PreDir,"")
				upattpath = upattpath.substring(0,upattpath.lastIndexOf("/")+1);
				$.ajax({
				url: "r?wf_num=R_S024_B004&Processid="+$("#WF_Processid").val()+"&DocUnid="+ $("#WF_DocUnid").val()+"&whether="+whether+"&filepath="+ upattpath +"&sId="+sId +"&nodename="+$("#WF_CurrentNodeid").val(),
				type: 'POST',
				async:false
				})
				$("#sRemoteHongtou").val("0");
            }else{
            	//文件另存到本地接口
            }
        } catch(e) {
            m_sRes = e.message;
        }
        //更新数据库路径
        //addAtt(m_sRes);
        
       // if (isDeblockBrowser!="OK") {
       //     g_docCtrl.DeblockBrowser();  //解锁失败，再次解锁一次
       // }
        //对网页做某些处理
        /*try { 	
        	if (res=="OK" && !iReadOnly) {
    	        var strFoldUrl = document.getElementById("strFoldUrl").value;
    	        var strfilename = document.getElementById("strfilename").value;
    	        //location.href='addAttach.jsp?flag=3&url='+strFoldUrl+'&filename='+strfilename;
            }
       	} catch(e) {
       	}*/
    }
    
    //备份文档
    function PrBackupDoc(sDocPath) {
        if (!sDocPath) return;
        try {
            var res = g_docCtrl.IsFileExist(sDocPath);
            if (res=="OK") {
                //获取sDocPath中扩展名之前的字符，并保存在sNewPath中
                var sNewPath;
                var hasDot = false;
                var dotPos = sDocPath.lastIndexOf(".");
                if (dotPos!=-1) {
                    hasDot = true;
                    sNewPath = sDocPath.substring(0, dotPos) + "_";
                } else {
                    sNewPath = sDocPath + "_"
                }
                //将系统当前日期时间添加到sNewPath后面
                var date = new Date();
                sNewPath = sNewPath + new String(date.getFullYear()).substr(2) + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分" + date.getSeconds() + "秒";
                //将原始文件扩展名添加到sNewPath后面
                if (hasDot) {
                    sNewPath = sNewPath + sDocPath.substr(dotPos);
                }
                //将sDocPath指向的文档改变为sNewPath
                g_docCtrl.Rename(sDocPath, sNewPath);
            }
        } catch (err) {
        }
    }
    
    //以只读方式编辑(打开)文档（2013年1月17日增加）
    function PrEditByReadOnly() {
        var wdProType = g_curDoc.ProtectionType;
        if (wdProType==3) {             //文档已经受到只读保护，直接返回
            return;
        } else if (wdProType!=-1) {     //文档受到其他方式的保护，需要先解除保护
            m_sRes = UnLoclFile("123");
            if (m_sRes!="OK") throw new Error(0, "无法以只读方式打开文档");
        }
        //以只读的方式保护文档
        m_sRes = g_docCtrl.LockFile("123", 0);
        if (m_sRes!="OK") throw new Error(0, m_sRes);
    }
    
    //以修订方式编辑文档（2013年1月17日增加）
    function PrEditByRevision() {
        var wdProType = g_curDoc.ProtectionType;
        if (wdProType==0) {             //文档已经受到修订保护，直接返回
            return;
        } else if (wdProType!=-1) {     //文档受到其他方式的保护，需要先解除保护
            m_sRes = UnLoclFile("123");
            if (m_sRes!="OK") throw new Error(0, "无法以修订方式打开文档");
        }
        //以只能修订的方式保护文档
        m_sRes = g_docCtrl.LockFile("123", 2);
        if (m_sRes!="OK") throw new Error(0, m_sRes);
    }
    
    //删除红头（2013年05月02日修改）
    function PrDeleteHongtou() {
        try {           
            //先关闭修订功能
            g_curDoc.TrackRevisions = false;
            //删除红头 - 红头是以类型为wdInlineShapeEmbeddedOLEObject(值为1)的InlineShape方式插入的
            var inlineShapes=g_curDoc.InlineShapes;
            var shape;
            for (var i=inlineShapes.Count; i>0; i--) {
                shape = inlineShapes(i);
                if ((shape.Type == 1) && (shape.AlternativeText == "TwHongtou")) {
                    shape.Delete();
                }
            }
        } catch(err) {    
        }
    }
}

/********************************************************************************
 *函数功能：从XML提取所有附件
 *参数说明：
 *  sInfo:要合并文档的有关信息，XML格式
 *返回值：返回一个数组，数组第一个元素表示错误代码；
 *        如果数组第一个元素为OK,表示函数调用成功，数组其余元素表示附件名称
 *        如果数组第一个元素不为OK,表示函数调用失败，此时第一个元素表示相关错误代码，数组的其余元素无效
 ********************************************************************************/
function ExtractAllAttachments(sInfo)
{
    var arrRes = new Array();
    var count=0;
    arrRes[0] = "OK";
    
    //检查函数参数，参数不能为空
    if(!sInfo)
    {
        arrRes[0] = "参数错误：请传递有效的参数";
        return arrRes;
    }
    
    //加载XML字符串sInfo
    g_xmlDoc = LoadXMLString(sInfo);
    if(!g_xmlDoc)
    {
        arrRes[0] = "未知错误：无法加载XML字符串";
        return arrRes;
    }
    if(!g_xmlDoc.documentElement)
    {
        g_xmlDoc = null;    //将g_xmlDoc置为空
        arrRes[0] = "XML格式错误：提供的XML的格式有错误";
        return arrRes;
    }
    
    //获取所有attchment节点
    var list, node, docs, index;
    index = -1;
    docs = g_xmlDoc.documentElement.childNodes;
    for(var i=0; i<docs.length; i++)
    {
        //如果节点类型不是元素节点，则跳过该节点
        if(docs[i].nodeType != 1)
            continue;
            
        //
        index++;
            
        //提取attchment节点
        node = docs[i].getElementsByTagName("attchment")[0];
        if(!node)
            continue;
            
        //提取attchment节点下的name节点
        list = node.getElementsByTagName("name");
        
        //保存所有name节点中的文本
        for(var j=0; j<list.length; j++)
        {
            //如果name节点下无文本，则跳过该name节点
            if(!list[j].childNodes[0])
                continue;
            count++;
            //保存name节点下的文本和该name节点所在的docinfo,它们之间用'|'
            arrRes[count] = list[j].childNodes[0].nodeValue + "|" + index;
        }
    }
    
    return arrRes;
}

/********************************************************************************
 *函数功能：合并文档
 *参数说明：
 *  sIP:文件服务器IP地址
 *  sPort:文件传输服务端口号
 *  sLocalPath:从服务器上下载的文件存放到本地的路径
 *  arrAttachs:一维数组，每个元素中的格式为"path|index",其中path为附件在服务器上的路径，index为附件所属的docinfo节点的索引号
 *返回值：返回一个数组，数组第一个元素表示错误代码；
 *        如果数组第一个元素为OK,表示函数调用成功，数组其余元素表示生成的文件名
 *        如果数组第一个元素不为OK,表示函数调用失败，此时第一个元素表示相关错误代码，数组的其余元素无效
 *说明：调用该函数前，需要先调用ExtractAllAttachments
 ********************************************************************************/
function UniteWordEsp(sIP, sPort, sLocalPath, arrAttachs) {
    var m_sLocalPath = sLocalPath;  //保存本地文件路径
    var m_sIP = sIP;                //保存IP地址
    var m_sPort = sPort;            //保存端口号
    var m_arrAttachs = arrAttachs;  //保存所有需要插入的附件
    var m_curNode = null;           //当前正在分析的docinfo节点
    var m_curNodeIndex = -1;        //当前docinfo节点的索引号（第一个docinfo节点的索引号为0，依次类推）
    var m_bOpen = false;            //用于确定是否有文件打开
    var m_arrFiles = new Array();   //该数组用于存储所有合并后的文件名(包含路径)
    var m_cFiles = 0;               //表示已经生成的文件数量
    var m_arrRes = new Array();     //返回结果
    var m_htRange = null;           //红头在文档中的区域
    
    //生成文档
    try {
        //检测传入的参数是否合法
        PrCheckParameters();
        //遍历XML的docinfo[n]节点
        var docs = g_xmlDoc.documentElement.childNodes;
        for (var i=0; i<docs.length; i++) {   
            //如果所枚举的节点不是元素节点，则跳过该节点
            if(docs[i].nodeType != 1) continue;
            m_curNode = docs[i];
            m_curNodeIndex++;
            //生成文档
            PrGenerateDoc();
        }
        m_arrRes[0] = "OK";
    } catch(err) {
        m_arrRes[0] = GenerateErrorString("UniteWordEsp", err);
    }
    //结束处理
    return PrLastHandle();
    
    //检测参数是否合法
    function PrCheckParameters() {
        if (!m_sIP || !m_sPort || !m_sLocalPath) {
            throw new Error(0, "请传递有效的参数");
        }
        if (!g_xmlDoc) {
            throw new Error(0, "g_xmlDoc不能为空");
        }
    }
    
    //根据当前docinfo节点(即m_curNode)下的信息来生成文档
    function PrGenerateDoc() {
        var sDocName        = null; //合并后的文档名（可能带有红头）
        var sModeTitle      = null; //模板文件名
        var bHongtou        = false;//确定红头文件是否存在。为true表明存在
        var bLock           = false;//合并后的文档是否锁住了。为true表明锁住了
        //分析docname节点，获取合并后的文档名
        sDocName = PrParseDocnameNode();
        //分析mode节点，获取模板文件名,并下载模板文件到本地
        sModeTitle = PrParseModeNode();
        // 先下载正文原稿并对它进行接受修订处理
        PreProcessTextNode();
        //打开模板文件
        PrOpenDoc(m_sLocalPath+"\/"+sModeTitle);
        //分析Text节点,下载正文原稿,并插入到模板文档中（如果Text节点不存在，则不做任何事情）
        //PrParseTextNode();
        //分析insertmode节点，下载红头文件，并插入到模板文件中
        bHongtou = PrParseHongTouNode();
        //将附件插入到模板文件的"Attach"书签处（如果没有Attach书签，则将不会做任何事）
        PrInsertAttachments("Attach");
        //分析元素节点info,并将相关信息插入到文档的指定域名处
        PrParseInfoNode();
        //分析"HeaderPic"节点
        PrParseHeaderPicNode();
        //分析"pagenumbertype"节点
        PrParsePagenumbertypeNode();
        //分析"PicSeal"节点
        PrParsePicSealNode();
        //分析"PicUnderWrite"节点
        PrParsePicUnderWriteNode();
        //分析Text节点,下载正文原稿,并插入到模板文档中（如果Text节点不存在，则不做任何事情）
        PrParseTextNode();
        //分析insertmode节点，下载红头文件，并插入到模板文件中
        //bHongtou = PrParseHongTouNode();
        //分析"ifcanupdate"节点，确定是否需要锁住文档
        bLock = PrParseIfcanupdateNode();
        //将模板文件另存到sDocName
        g_curDoc.SaveAs(m_sLocalPath + "\/" + sDocName);
        m_arrFiles[m_cFiles] = m_sLocalPath + "\/" + sDocName;
        m_cFiles++;
        //分析title节点,在sDocName的基础上生成未带红头的文件
        PrParseTitleNode(sDocName, bHongtou, bLock);   
        //删除所有域代码
        //DeleteAllFields();    
        //关闭当前打开的文档
        CloseWord();
        m_bOpen = false;
        return "OK";
    }   //PrGenerateDoc函数结束
    
    //作最后的处理
    function PrLastHandle() {
        if (m_arrRes[0] != "OK") {
            //如果当前有文档打开，则关闭文档
            if(m_bOpen) CloseWord();
            //删除已经生成的文档
            for (var i=0; i<m_cFiles; i++) {
                g_docCtrl.DeleteFile(m_arrFiles[i]);
            }   
        } else {
            //将cFiles的文件名都移到arrRes中
            for (var i=0; i<m_cFiles; i++) {
                m_arrRes[1+i] = m_arrFiles[i];
            }
        }
        return m_arrRes;
    }   //PrLastHandle函数结束
    
    function DeleteAllFields() {
        try {
            var fields = g_curDoc.Fields;
            var count = fields.Count;
            if(count<=0) return;
            for (var i=1; i<=count; i++) {
            	//alert(i);
                var field = fields.Item(i);
                var ranges = field.Code;
                field.Select();
                g_wordApp.Selection.Delete(1, 1);
            }
        } catch (err) {
        }
    }
    
    
    function PrOpenDoc(sDocPath) {
        var errCode = OpenWord(sDocPath);
        if (errCode != "OK") throw new Error(0, errCode);
        //将bOpen置为true,表明有文档打开
        m_bOpen = true;
        //关闭修订功能
        g_curDoc.TrackRevisions = false;
         //让另存为功能可用,控件默认屏蔽了另存为功能
        g_docCtrl.EnableSaveAs(1);
    }
    
    //下载文件,返回下载的文件名
    function PrDownloadFile(sRemoateFile)
    {
        //从sRemoateFile中提取文件名
        var file = GetTitleFromPath(sRemoateFile);
        if (!file) {
            throw new Error(0, "远程文件路径无效");
        }
        //下载文件
        var errorCode = DownloadFile(m_sIP, m_sPort, sRemoateFile, m_sLocalPath+"\/"+file);
        //var errorCode = g_docCtrl.CopyFile(sRemoateFile, m_sLocalPath+"\/"+file);
        if (errorCode != "OK") {
            throw new Error(0, errorCode);
        }
        return file;
    }
    
    //分析Mode节点 - 下载模板文件，并返回模板文件名
    function PrParseModeNode() {
        var node = m_curNode.getElementsByTagName("mode")[0];
        if (!node || !node.childNodes[0]) {
            throw new Error(0, "请在XML的mode节点中提供模板文件的路径");
        }
        return PrDownloadFile(node.childNodes[0].nodeValue);
    }
    
    //分析docname - 获取主模板保存名称
    function PrParseDocnameNode() {
        var node = m_curNode.getElementsByTagName("docname")[0];
        if (!node || !node.childNodes[0]) {
            throw new Error(0, "请在XML的docname节点中提供文档合并后的名称");
        }
        return node.childNodes[0].nodeValue;
    }
    
    //分析"FormFormat"节点 - 合并文档时是保留正文原稿原格式(true)还是采用模板格式(false)，默认保留正文原稿原格式
    function PrParseFormFormatNode() {
        var node = m_curNode.getElementsByTagName("FormFormat")[0];
        if (node) {
            node = node.getElementsByTagName("ReserveFormatNode")[0];
            if (node && node.childNodes[0]) {
                if (node.childNodes[0].nodeValue == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
    
     function PreProcessTextNode() {
        var node = m_curNode.getElementsByTagName("Text")[0];
        if (!node || !node.childNodes[0]) {
            return null;
        }
        //下载正文原稿,并将光标移到TxtText书签处
        var txtFile = PrDownloadFile(node.childNodes[0].nodeValue);
        
        var errCode = OpenWord(m_sLocalPath+"\/"+txtFile);
        if (errCode != "OK") throw new Error(0, errCode);
        //将bOpen置为true,表明有文档打开
        m_bOpen = true;
        //关闭修订功能
        g_curDoc.TrackRevisions = false;
         //让另存为功能可用,控件默认屏蔽了另存为功能
        g_docCtrl.EnableSaveAs(1);
        AcceptAllRevisions();
        g_curDoc.Save(); 
        CloseWord();  
        m_bOpen = false;
    }   //PreProcessTextNode函数结束
    
    //分析Text节点 - 插入正文原稿，并返回正文原稿名
    function PrParseTextNode() {
        var node = m_curNode.getElementsByTagName("Text")[0];
        if (!node || !node.childNodes[0]) {
            return null;
        }
        //获取正文原稿文件名称
        var txtFile = GetTitleFromPath(node.childNodes[0].nodeValue);
       
        
       //下载正文原稿
        //var txtFile = PrDownloadFile(node.childNodes[0].nodeValue);
     
        //将光标移到书签TxtText处
        if (!SetLabel("TxtText")) {
            throw new Error(0, "请先在模板文件中添加TxtText书签");
        }
        
        //将正文原稿插入到当前光标处
        if (PrParseFormFormatNode()) {  //保留正文原稿的格式
            if (InsertFile(m_sLocalPath+"\/"+txtFile)!="OK") {
                throw new Error(0, "无法插入正文原稿<"+txtFile+">");
            }
        } else {                        //使用模板文件的格式
            //不要调用OpenWord方法打开该文档(2012年11月28日修改为以只读方式打开)
            var doc = g_wordApp.Documents.Open(m_sLocalPath+"\/"+txtFile, false, true);
            //复制正文原稿
            doc.Content.Copy();
            //激活模板文档
            g_curDoc.Activate();
            //粘贴到模板文件的"TxtText"书签处,20表示匹配目标格式
            g_wordApp.Selection.PasteAndFormat(20);
            doc.Close();
        }
        //接受所有修订
        AcceptAllRevisions();
        return txtFile;
    }   //PrParseTextNode函数结束
    
    //分析"insertmode"节点 - 下载并插入红头文件，HongTou节点存在则返回true
    function PrParseHongTouNode() {
        var node = m_curNode.getElementsByTagName("insertmode")[0];
        if (!node)  return false;
        node = node.getElementsByTagName("HongTou")[0];
        if (!node || !node.childNodes[0]) return false;
        //下载红头文件
        var hongtouFile = PrDownloadFile(node.childNodes[0].nodeValue);
        //判断"HongTou"书签是否存在
        if (!g_curDoc.Bookmarks.Exists("HongTou")) {
            throw new Error(0, "请先在模板文件中添加HongTou书签");
        }
        //获取"HongTou"书签,并设置m_htRange
        var bkHt = g_curDoc.Bookmarks("HongTou");
        m_htRange = g_curDoc.Range(bkHt.Start, bkHt.Start+1);
        //插入红头文件
        bkHt.Range.InsertFile(m_sLocalPath+"\/"+hongtouFile);
        m_htRange.End = m_htRange.End - 1;
        return true;
    }
    
    //在指定书签处插入一组附件,sBookmark为书签名
    function PrInsertAttachments(sBookmark) {
        //如果未提供有效的附件数组，直接返回;
        if (!m_arrAttachs || !m_arrAttachs[0]) return;
        //如果未找到指定书签，直接返回
        if (!SetLabel(sBookmark)) return;
        //开始插入附件
        var arrTmp, file;
        for (var j=0; j<m_arrAttachs.length; j++) {
            //从m_arrAttachs数组提取附件在服务器上的路径，以及附件所属的docinfo节点的索引
            arrTmp = m_arrAttachs[j].split("|");
            if (arrTmp[1] != m_curNodeIndex) continue;
            //下载附件
            file = PrDownloadFile(arrTmp[0]);
            //在文档中插入分页符
            g_wordApp.Selection.Collapse(0);
            g_wordApp.Selection.InsertBreak(7);
            //插入附件
            if (InsertFile(m_sLocalPath+"\/"+file)!="OK") {
                throw new Error(0, "插入附件<" + file + ">失败");
            }
        }
    }   //PrInsertAttachments函数结束
    
    //分析"HeaderPic"节点 - 用于插入页眉页脚
    function PrParseHeaderPicNode() {
        var node = m_curNode.getElementsByTagName("HeaderPic")[0];
        if (node) {
            node = node.getElementsByTagName("HeaderPicNode")[0];
            if (node) {
                //先清除页眉和页脚
                ClearHeaderOrFooter(false);
                ClearHeaderOrFooter(true);
                //插入文字页眉，左对齐
                var sText = node.getAttribute("headertext");
                if (sText) {
                    AddTextHeader(sText, 0);
                }
                //插入图片页眉(二维码)，右对齐
                var textNode = node.childNodes[0];
                if (textNode) {
                    try {
                        var file = PrDownloadFile(textNode.nodeValue);
                        AddPictureHeader(m_sLocalPath+"\/"+file, 2);
                    } catch (e) {
                    }
                }
            }
        }
    }   //PrParseHeaderPicNode函数结束
    
    //分析"pagenumbertype"节点 - 用于在页脚插入页码
    function PrParsePagenumbertypeNode() {
        var node = m_curNode.getElementsByTagName("pagenumbertype")[0];
        if (node && node.childNodes[0]) {
            var type = node.childNodes[0].nodeValue;
            var pagenumbers = g_curDoc.Sections(1).Footers(1).PageNumbers;
            if(type == 1)
                pagenumbers.Add(3, true);   //页码左右分开显示，显示首页
            else if(type == 2)
                pagenumbers.Add(3, false);  //页码左右分开显示，不显示首页
            else if(type == 3)
                pagenumbers.Add(1, false);  //页码居中显示，不显示首页
            else if(type == 4)
                pagenumbers.Add(1, true);   //页码居中显示，显示首页
        }
    }
    
    //分析"PicSeal"节点 - 插入公章图片(该节点不存在则不作任何事)
    function PrParsePicSealNode() {
        var node = m_curNode.getElementsByTagName("PicSeal")[0];
        if (node) {
            node = node.getElementsByTagName("PicSealNode")[0];
            if (node && node.childNodes[0]) {
                //获取书签名
                var bookmark = node.getAttribute("bookmarkname");
                if (!bookmark) {
                    throw new Error(0, "XML的PicSealNode节点缺少bookmarkname属性");
                }
                //下载公章图片
                var pic = PrDownloadFile(node.childNodes[0].nodeValue);
                //插入图片
                var errCode = AddPicture(m_sLocalPath+"\/"+pic, bookmark);
                if (errCode != "OK") {
                    throw new Error(0, errCode);
                }
            }
        }
    }
    
    //分析"PicUnderWrite"节点 - 插入领导签字图片(该节点不存在则不作任何事)
    function PrParsePicUnderWriteNode() {
        var node = m_curNode.getElementsByTagName("PicUnderWrite")[0];
        if (node) {
            node = node.getElementsByTagName("picWrite")[0];
            if (node && node.childNodes[0]) {
                //获取书签名
                var bookmark = node.getAttribute("bookmarkname");
                if (!bookmark) {
                    throw new Error(0, "XML的PicUnderWriteNode节点缺少bookmarkname属性");
                }
                //插入领导签字图片或者文字
                var errCode;
                try {
                    var pic = PrDownloadFile(node.childNodes[0].nodeValue);
                    errCode = AddPicture(m_sLocalPath+"\/"+pic, bookmark);
                } catch (e) {
                    var text = node.getAttribute("UserName");
                    errCode = WriteTextOnLabel(text, bookmark);
                }
                if (errCode!="OK") {
                    throw new Error(0, errCode);
                }
            }
        }
    }
    
    //分析"info"节点 - 该节点下的子节点名全代表文档中的域名
    function PrParseInfoNode() {
        var arrWriteFailureFields = new Array();
        var count = 0;
        var node = m_curNode.getElementsByTagName("info")[0];
        if (!node) return;
        var nodeList = node.childNodes;
        for (var j=0; j<nodeList.length; j++) {
            //只分析节点info下的元素节点
            if (nodeList[j].nodeType==1 && nodeList[j].childNodes[0]) {
                var value;
                value = nodeList[j].childNodes[0].nodeValue;
                //将"@#@$*$"替换成"\r\n"
                value = value.replace(/@#@\$\*\$/g, "\r\n");
                //将"$*$"替换成"\r\n"
                value = value.replace(/\$\*\$/g, "\r\n");
                //将"@#@"替换成"\r\n"(2012年11月28日添加)
                value = value.replace(/@#@/g, "\r\n");
                
                //将相关信息插到指定域处,如果写入失败，将写入失败的域名与值存入arrField中
                PrWriteTextOnField(value, nodeList[j].nodeName);
            }
        }
    }   //PrParseInfoNode函数结束
    
    //将文本写入指定域
    function PrWriteTextOnField(sValue, sFieldName) {
        try {
            var fields = g_curDoc.Fields;
	        var count = fields.Count;
	        if(count<=0) return;
	        for (var i=1; i<=count; i++) {
		        var field = fields.Item(i);
		        var ranges = field.Code;
		        var bstrText = ranges.Text;
		        var reg = eval("/\\s+" + sFieldName + "\\s+/g");
		        if (bstrText.search(reg) > -1) {
			        field.Select();
			        g_wordApp.Selection.InsertAfter(sValue);
		        }
	        }
        } catch (err) {
        }
    }
    
    //分析ifcanupdate节点 - 是否需要锁住文档
    function PrParseIfcanupdateNode() {
        var node = m_curNode.getElementsByTagName("ifcanupdate")[0];
        if (node && node.childNodes[0]) {
            //如果元素节点ifcanupdate的值为1，则需要锁住文档，为其他值则不需要锁住文档
            if (node.childNodes[0].nodeValue == 1) {
                //保护文档，保护模式为只读
                var errCode = g_docCtrl.LockFile("123", 0);
                if (errCode != "OK") {
                    throw new Error(0, errCode);
                }
                return true;
            }
        }
        return false;
    }   //PrParseIfcanupdateNode函数结束
    
    //分析Title节点 - 以sDocName为基础生成未带红头的文件
    function PrParseTitleNode(sDocName, bHongtou, bLock) {
        //如果title节点不存在，直接返回
        var node = m_curNode.getElementsByTagName("title")[0];
        if (!node || !node.childNodes[0]) return;
        //将文件另存到sTitleName中
        var sTitleName = node.childNodes[0].nodeValue;
        g_curDoc.SaveAs(m_sLocalPath + "\/" + sTitleName);
        m_arrFiles[m_cFiles] = m_sLocalPath + "\/" + sTitleName;
        m_cFiles++;
        //如果没有红头，则直接返回
        if (!bHongtou) return;
        //如果文档已经锁住，则先解锁
        if (bLock) g_docCtrl.UnLoclFile("123");
        //删除红头
        m_htRange.Delete();
        //如果文档需要锁住，则锁住文档
        if (bLock) g_docCtrl.LockFile("123", 0);
    }
    
}   //UniteWordEsp函数结束

/********************************************************************************
 *函数功能：上传一组文件到服务器
 *参数说明：
 *  sIP:文件服务器IP地址
 *  sPort:文件传输服务端口号
 *  sUpRemotePath:要上传的文件在服务器上的路径(文件名将与arrFiles中包含的文件名相同)
 *  arrFiles:包含所有要上传文件的数组(每个元素包含路径+文件名)
 *返回值：所有文件都上传成功返"OK"，失败将返回相应错误代码
 ********************************************************************************/
 function UploadFilesArray(sIP, sPort, sUpRemotePath, arrFiles)
 {
    var res="OK";
    if(!sIP || !sPort || !sUpRemotePath || !arrFiles)
        return "参数错误：请传入有效的参数";
        
    var title;
    for(var i=0; i<arrFiles.length; i++)
    {
        //从数组元素中提取出文件名
        title = GetTitleFromPath(arrFiles[i]);
        if(!title)
        {
            res = "参数错误：提供要上传的文件<" + arrFiles[i] + ">是无效的！";
            break;
        }
        
        //上传文件
        res = UploadFile(sIP, sPort, sUpRemotePath + "\/" + title, arrFiles[i]);
        if(res!="OK")
            break;
    }
    
    return res;
 }
 
 /********************************************************************************
 *函数功能：另存文件sSrcFile到其他路径
 *参数说明：
 *  sSrcFile:源文件名(包含路径)
 *  sDefaultFileName【可选】:显示在另存为对话框上的默认文件名
 *返回值：成功返"OK"，失败将返回相应错误代码
 ********************************************************************************/
function SaveFileToOtherPath(sSrcFile, sDefaultFileName)
{
    var res="OK";
    var sDesFile;
    try
    {  
        //检测传入的参数
        if(!sSrcFile){ 
          return "参数错误：请传递有效的参数！";	
        }  
        if(g_docCtrl.IsFileExist(sSrcFile)!="OK")
        {  
            res = "参数错误：需要另存为的文件<" + sSrcFile + ">不存在！";
            return res;
        } 
        if(!sDefaultFileName)
            sDefaultFileName = GetTitleFromPath(sSrcFile);
       
        //打开另存为对话框
        sDesFile = g_docCtrl.GetSavePath(sDefaultFileName);
        if(sDesFile=="")
        {
            res = "另存为操作失败：用户取消了<另存为>操作！";
            return res;
        } 
        //复制文件
        res = g_docCtrl.CopyFile(sSrcFile, sDesFile);
        if(res != "OK")
            res = "另存为操作失败：" + res;
    }
    catch(err)
    {
        res = GenerateErrorString("SaveFileToOtherPath", err);
    }
    return res;
}

/*****************************************************************************************
 *函数功能：编辑本地Word文档。关闭文档后，不会自动上传该文件，文件关闭后是否只读保持原样
 *参数说明：
 *  sLocalFile:本地Word文档路径名
 *返回值：成功返"OK"，失败将返回相应错误代码
 *****************************************************************************************/
function EditLocalWord(sLocalFile)
{
    var bOpen = false;          //文档打开标识
    var iLockType = -1;          //锁住文档的类型，为-1表示不需要锁住文档
    var res = "OK";
    try
    {
        //打开正文原稿
        res = OpenWord(sLocalFile, 0, 0, "");
        if(res!="OK")
            throw new Error(0, res);
        bOpen = true;
        
        //如果文档被锁住，则解锁文档
        iLockType = PrCvtWdProtectionType(g_curDoc.ProtectionType);
        if(iLockType != -1)
        {
            res = g_docCtrl.UnLoclFile("123");
            if(res != "OK")
                throw new Error(0, res);
        }
        
        //关闭修订
        g_curDoc.TrackRevisions = false;
        
        //注册WORD文档关闭事件
        //g_docCtrl.attachEvent("OnDocmentClose", OnDocmentClose);
        addEvent(g_docCtrl,"DocmentClose", OnDocmentClose);
        
        //最大化Word应用程序窗口
        g_wordApp.WindowState = 1;
        //获取当前文档对应的窗口
        var window = g_curDoc.Windows(1);
        //显示Word文档窗口
        window.Visible = true;
        //激活Word应用程序
        g_wordApp.Activate();
        
        //锁定正文原稿页面
        g_docCtrl.BlockBrowser();
        
        return res;
    }
    catch(err)
    {
        //如果已经打开文档，则关闭它
        if(bOpen)
        {
            CloseWord();
            bOpen = false;
        }
        //获取错误描述
        res = GenerateErrorString("EditLocalWord", err);
    }
    return res;
    
    //文档关闭事件
    function OnDocmentClose(info)
    {
        try
        {
            //解除正文原稿页面锁定
    	    //g_docCtrl.DeblockBrowser();
        	
            //删除动态添加的WORD关闭事件
            //g_docCtrl.detachEvent("OnDocmentClose", OnDocmentClose);
    	    removeEvent(g_docCtrl,"DocmentClose", OnDocmentClose);
    	    
            //隐藏WORD
            g_wordApp.Visible = false;
            //如果先前解锁过文档，则这里需要锁住文档
            if(iLockType != -1)
                res = g_docCtrl.LockFile("123", iLockType);
        }
        catch(err)
        {
            res = err.message;
        }
        bOpen = false;
    }
    
    //转换保护类型
    function PrCvtWdProtectionType(iProtectionType)
    {
        var iLockType = -1;
        switch(iProtectionType)
        {
        case 0:
            iLockType = 2;
            break;
        case 1:
            iLockType = 3;
            break;
        case 2:
            iLockType = 1;
            break;
        case 3:
            iLockType = 0;
            break;
        default:
            iLockType = -1;
            break;
        }
        return iLockType;
    }
}

//自定义endwith函数
String.prototype.endWith=function(str){
	 if(str==null||str==""||this.length==0||str.length>this.length){
	 	return false; 
	 }	
	 if(this.substring(this.length-str.length)==str){
	 	return true;
	 }else{
	 	return false;
	 }    
} 


//iRevise【可选】:0表示不打开，非0表示打开。默认不打开修订功能
//iShowTrace【可选】:非0表示显示痕迹，0表示不显示痕迹。默认不显示痕迹
//iReadOnly【可选】:0表示以非只读方式打
//Word正文
function OpenWordDoc1(fileName)
{
	//if(!fileName){fileName="正文.doc";}
	//OpenUrl(url);
	var ifexit = IsInstallCtrl2();				
	if(!ifexit)					
	{
		alert('请安装客户端控件');
		$("#exitscontrol").show();	
		$("#WordZengWen").hide();
	}
	else
	{
		var sIP;
		sId = fileName.id;
		var sPort;
		var sUsername=$("#WF_UserName").val();
		var json;
		var sRemoteFile;
		var whether = "0";	
		var sRemoteHongtouUrl = $("#sRemoteHongtouUrl").val();
		var sUpRemoteFile;
		var sLocalHongtouPath;
		$.ajax({
			url: "r?wf_num=R_S024_B005&DocUnid="+$("#WF_DocUnid").val()+"&sId="+sId,
			type: 'POST',
			async:false,
			success:function(data)
			{
				json = eval('(' + data.replace(/[\r\n]/g,"") + ')');
				sIP=json.sip;
				sPort=json.sport;
				month=json.month;
				userComp=json.userComp;
				if(json.url=="0")
				{
					sRemoteFile = "/正文原稿.doc";
					//2015/bpm/09/2812000/2812000/13afb7d500e0104a2908002059e0ee0f53f1/
					///2015/002002001001/2812000/9/90bbf2b60b6e704f450aae50e6aa9f137c05/13afb7d500e0104a2908002059e0ee0f53f1WordZengWen.doc
					if(month<10){
						month="0"+month;
					}
					sUpRemoteFile = json.year+"/bpm"+"/"+ month+"/"+userComp +"/"+ json.userdept +"/"+$("#WF_DocUnid").val()+"/"+$("#WF_DocUnid").val()+sId+".doc";
					//alert(sUpRemoteFile)
				}
				else
				{
					whether = "1";
					sRemoteFile = json.url;
					sUpRemoteFile = sRemoteFile;
				}
				
			}
		})
		if (!sRemoteHongtouUrl && typeof(sRemoteHongtouUrl)!="undefined" && sRemoteHongtouUrl!=0)
		{}
		else
		{
			//sRemoteHongtouUrl="/红头模版.doc";
			sLocalHongtouPath= "C:\\talkweb";
		}
		var sLocalPath = "C:\\talkweb";	
		Initialize('netWord1');   
		//sRemoteHongtouUrl="/filestore/mode/红头.doc";
		var readOnly=iReadOnly();
		EditWord(sIP,sPort,sUsername,PreDir+sRemoteFile,PreDir+sUpRemoteFile,sLocalPath,sRemoteHongtouUrl,whether,json,sLocalPath,sId,0,0,readOnly);
		try{
			var editwordstatu=EditWord(sIP,sPort,sUsername,PreDir+sRemoteFile,PreDir+sUpRemoteFile,sLocalPath,sRemoteHongtouUrl,whether,json,sLocalPath,sId,0,0,readOnly);
			if (editwordstatu != "OK"){
				alert(editwordstatu);
			}
		}catch(err){
			 alert(err);
		}
	}
}

/**
 * 获取是否以只读方式打开正文
 * 20151211谌文琦新增
 */
function iReadOnly(){
	var isReadOnly=0;
	//流程ID
	var processid=$("#WF_Processid").val();
	//环节ID
	var currentNoid=$("#WF_CurrentNodeid").val();
	$.ajax({
		url: "r?wf_num=R_S024_B006&processid="+processid+"&currentNoid="+currentNoid,
		type: 'POST',
		async:false,
		data:"Text",
		success:function(data){
			if(data.indexOf("1")>-1){
				isReadOnly=1;
			}
		}
	});
	return isReadOnly;
}
