/********************************************************************************
 *第 二 层：业务无关层                                                          *
 *作    者：张艳为                                                              *
 *创建日期：2012年08月24日                                                      *
 *修改日期：2013年02月04日                                                      *
 *支持版本：文档控件2.0(支持WPS)                                                *
 *功    能：该文件封装了常用的Word文档操作功能                                  *
 *注    意：1、请不要修改该文件中的内容；                                       *
 *          2、使用该文件前，前先加载文档控件2.0或以上版本。                    *
 ********************************************************************************/



/*<----------------------------------------分割线---------------------------------------->*/



/*
 *全局变量声明
 */

var g_docCtrl;          //文档控件
var g_wordApp;          //WORD应用程序对象
var g_curDoc;           //当前打开的文档
var g_bOpen=false;      //用于确定是否打开了一篇word文档，如果打开了则为true。(文件内部使用)
var g_iAppType = 0;     //应用程序类型，0表示还没打开文档，1代表Word，2代表WPS
var g_OrgUserName="";



/*<----------------------------------------分割线---------------------------------------->*/
/**
 * IE11下注册事件函数的兼容性方法
 * @param element
 * @param type
 * @param handler
 */
function addEvent(element, type, handler) { 
    if (element.attachEvent) { 
        element.attachEvent("on" + type, handler); 
    } else if (element.addEventListener) { 
        element.addEventListener(type, handler, false); 
    } 
} 

/**
 * IE11下删除事件函数的兼容性方法
 * @param element
 * @param type
 * @param handler
 */
function removeEvent(element, type, handler) { 
    if (element.detachEvent) { 
        element.detachEvent("on" + type, handler); 
    } else if (element.removeEventListener) { 
        element.removeEventListener(type, handler, false); 
    } 
} 


/*
 *辅助函数
 */

/********************************************************************
 *函数功能：生成格式化后的异常字符串
 *参数说明：
 *  sFunName:发生异常的函数名
 *  err:异常对象
 *返回值：异常描述字符串
 ********************************************************************/
function GenerateErrorString(sFuncName, err)
{
    //异常代码为0表示是自定义的异常
    if(err.number == 0)
        return err.message;
    
    var res;
    res = "发生异常的函数：" + sFuncName + "\r\n";
    res += "异常代码：" + Number(err.number & 0x0000FFFF) + "\r\n";
    if(!err.message)
        res += "异常描述：无相关描述";
    else
        res += "异常描述：" + err.message;
    return res;
}

/********************************************************************
 *函数功能：初始化basic.js里的全局变量
 *参数说明：
 *  sObjectId:加载WORD控件的object标签的ID属性值
 *返回值：成功返回OK, 失败返回相应错误描述
 ********************************************************************/
function Initialize(sObjectId)
{
    g_docCtrl = null;
    
    if(!sObjectId)
        return "参数错误：请传入有效的参数object id值";
    
    var tmpObject;
    tmpObject = document.getElementById(sObjectId);
    if(!tmpObject)
        return "参数错误：无效的object id值";
        
    //var clsid1="clsid:EB366EF5-158B-496c-8DE1-26B0CEEED121";
    var clsid1="clsid:022895FA-145D-4C68-A07D-9D62FF57CCD9";
    var clsid2=tmpObject.getAttribute("classid");
    if(clsid1 != clsid2)
    {
        var res;
        res = "object标签的classid值错误\r\n";
        res += "IE控件的classid值为：" + clsid1 + "\r\n";
        if(!clsid2)
            clsid2 = "未指定classid";
        res += "object标签的classid值为：" + clsid2;
        return res;
    }
    
    g_docCtrl = tmpObject;
        
    return "OK";
}



/*<----------------------------------------分割线---------------------------------------->*/



/*
 *WORD操作常用函数
 */
 
/********************************************************************
 *函数功能：打开一篇WORD文档
 *参数说明：
 *  sFile:文档路径(包括文档名)
 *  iVisible【可选】:0表示WORD不可见，非0表示可见。默认不可见
 *  iReadOnly【可选】:0表示以非只读方式打开，非0以只读方式打开。默认以非只读方式打开
 *  sUsername【可选】:用户名。默认或者为空字符串将不主动设置用户名，用户名将会是系统原来设置的
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function OpenWord(sFile, iVisible, iReadOnly, sUsername)
{    
    //用户保存WORD原始用户名
    var sOrgUsername="";
    //检测参数
    if(!g_docCtrl)
        return "操作错误：文档控件未加载，请先加载文档控件！";
    if(g_bOpen)
        return "操作错误：当前已打开文档，请先关闭当前文档！";
    if(!sFile)
        return "参数错误：请传入要打开的文档名";
    //调用内部方法打开WORD文档
    return _Open();
    
    
    //打开文档
    function _Open()
    {
        var res;
        try
        {
            //默认(即不传递该参数)以非只读方式打开
            if(!iReadOnly)
                iReadOnly = 0;
            //默认不可见
            iVisible = (iVisible ? 1 : 0);
            //打开WORD   
            res = g_docCtrl.EditWord(sFile, 2, "", iReadOnly, "", 0, iVisible, "");
            if(res != "OK")
                return "WORD操作失败：" + res;
            
            //保存WORD应用程序对象
            g_wordApp = g_docCtrl.wordAppcation;
            //保存通过控件打开的文档对象
            g_curDoc = g_docCtrl.wordDocument;
            //设置用户信息
            if(sUsername)
            {
                //保存原始用户名
                sOrgUsername = g_wordApp.Username;
                g_OrgUserName = sOrgUsername;
                //设置新的用户名
                g_wordApp.Username = sUsername;
            }
            
            //获取打开文档的应用程序类型
            if (typeof g_docCtrl.GetAppType == "undefined") {
                g_iAppType = 1;     //如果控件内部没有定义GetAppType，肯定是用Word打开该文档(以前版本不支持WPS)
            } else {
                g_iAppType = g_docCtrl.GetAppType();
            }
               
            if (g_docCtrl.attachEvent || g_docCtrl.GetIEVersion() != '11') {
            		//注册文档关闭事件
            		//alert('g_docCtrl.attachEvent');
                g_docCtrl.attachEvent("OnDocmentClose", _OnDocumentClose);
            }
            
            g_bOpen = true;
            res = "OK";
        }
        catch(err)
        {
            res = GenerateErrorString("OpenWord", err);
        }
        return res;
    }    
    
    //处理文档关闭事件
    function _OnDocumentClose(info)
    {
    	//alert('_OnDocumentClose');
        //注销文档关闭事件
        if (g_docCtrl.attachEvent || g_docCtrl.GetIEVersion() != '11') {
            g_docCtrl.detachEvent("OnDocmentClose", _OnDocumentClose);
        }
    	    	
        //重置打开标识
        g_bOpen = false;
        //恢复原始用户名
        if(sOrgUsername!="")
            g_wordApp.Username = sOrgUsername;
        //不保存模板
        g_wordApp.NormalTemplate.Saved = true;
        //将g_wordApp和g_curDoc置为null（2012年11月28日增加）
        g_wordApp = null;
        g_curDoc = null;
    }
}
function CleanWord()
{
    //重置打开标识
    g_bOpen = false;
    //恢复原始用户名
    if(g_OrgUserName!="")
        g_wordApp.Username = g_OrgUserName;
    //不保存模板
    g_wordApp.NormalTemplate.Saved = true;
}

//关闭word
function CloseWord()
{
    if(!g_bOpen)
        return "操作错误：未打开任何文档";
    var res;
    try
    {

        //保存文档(2012年11月28日增加)
        if(g_curDoc.ReadOnly)
            g_curDoc.Saved = true;
        else
            g_curDoc.Save();

        //关闭文档
        g_curDoc.Close();
        g_bOpen = false;
        res = "OK";
	    g_wordApp = null;
	    g_curDoc = null;	        
    }
    catch(err)
    {
        g_bOpen = false;
        res = GenerateErrorString("CloseWord", err);
    }
    return res;
}

//设置视图模式为页面视图
function SetActivePages()
{
    try
    {
        var mViewActive =  g_wordApp.ActiveWindow.ActivePane.View;
        var mViewActives  = g_wordApp.ActiveWindow.View;
        var wdPecialPan = mViewActive.SplitSpecial;
        if(wdPecialPan==0) {
            mViewActive.Type=3; //wdPrintView=3
        }
        else {
            mViewActives.Type=3;
        }

        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("SetActivePages", err);
    }
    return res;
}

/********************************************************************
 *函数功能：选中指定书签
 *  sLabelName:书签名
 *返回值：成功返回true，失败返回false
 ********************************************************************/
function SetLabel(sLabelName)
{
	if(!sLabelName)
	    return false;
	    
	try
	{
	    //获取书签集合对象   
	    var bookmarks = g_curDoc.Bookmarks;
	    //判断指定书签是否存在
        if(!bookmarks.Exists(sLabelName))
	        return false;

	    //获取指定书签对象
        var bookmark = bookmarks.Item(sLabelName);
        //选中指定书签
        bookmark.Select();
	}
	catch(err)
	{
	   return false;
	}
	return true;
}

/********************************************************************
 *函数功能：选中指定域名
 *  sFieldName:域名
 *返回值：成功返回true，失败返回false
 ********************************************************************/
function SetField(sFieldName)
{
	if(!sFieldName)
	    return false;
	
	try
	{
	    var fields = g_curDoc.Fields;
	    var counts = fields.Count;
	    if(counts<=0)
	        return false;
	    for(i=1;i<=counts;i++)
	    {
		    var field = fields.Item(i);
		    var ranges = field.Code;
		    var bstrText = ranges.Text;
		    var reg = eval("/\\s+" + sFieldName + "\\s+/g");
		    if(bstrText.search(reg) > -1)
		    {
			    //选中指定域
			    field.Select();
			    return true;
		    }
	    }
	}
	catch(err)
	{
	}
	return false;
}

/********************************************************************
 *函数功能：往打开文档的指定的书签添加图片
 *  sPictureFile:指定图片路径（包括图片文件名）
 *  sLabelName:书签名
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddPicture(sPictureFile, sLabelName) 
{
	if(!sPictureFile || !sLabelName)
	    return "参数错误：请传入有效的参数";
	
	var res;    
	try
	{
	    if(g_docCtrl.IsFileExist(sPictureFile)=="NO")
	        return "参数错误：要添加的图片文件不存在";
	    if(!SetLabel(sLabelName))
	        return "参数错误：指定书签不存在";
	    
	    var sel, shapes, shape;
        sel = g_wordApp.Selection;
        sel.Collapse(0);
        shapes = sel.InlineShapes;
        shape = shapes.AddPicture(sPictureFile);
        
        res = "OK";
	}
	catch(err)
	{
	    res = GenerateErrorString("AddPicture", err);
	}
	return res;
}

/********************************************************************
 *函数功能：往指定书签处写文字
 *  sTextInfo:要写入的文本
 *  sLabelName:书签名
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function WriteTextOnLabel(sTextInfo,sLabelName)
{
	if(!sTextInfo || !sLabelName)
	    return "参数错误：请传入有效的参数";
	    
	var res;    
	try
	{
	    if(!SetLabel(sLabelName))
	        return "参数错误：指定书签不存在";
	    
	    var sel, shape;
        sel = g_wordApp.Selection;
        //sel.TypeText(sTextInfo);
        //在选择区后插入文字
        sel.InsertAfter(sTextInfo);
        
        res = "OK";
	}
	catch(err)
	{
	    res = GenerateErrorString("WriteTextOnLabel", err);
	}
	return res;
}

/********************************************************************
 *函数功能：往指定域名处写文字
 *  sTextInfo:要写入的文本
 *  sFieldName:域名
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function WriteTextOnField(sTextInfo, sFieldName)
{
	if(!sFieldName)
	    return "参数错误：请传入有效的域名";
	if(!sTextInfo)
	    sTextInfo = "";
	    
	var res;    
	try
	{
	    if(!SetField(sFieldName))
	        return "参数错误：指定域名不存在";
	    
	    var sel, shape;
        sel = g_wordApp.Selection;
        //sel.TypeText(sTextInfo);
        //在选择区后插入文字
        sel.InsertAfter(sTextInfo);
        
        res = "OK";
	}
	catch(err)
	{
	    res = GenerateErrorString("WriteTextOnField", err);
	}
	return res;
}

/********************************************************************
 *函数功能：添加文字页眉
 *  sHeaderInfo:要写入的文本
 *  iAlignment【可选】:对齐方式， 0居左 1居中 2局右，默认左对齐
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddTextHeader(sHeaderInfo, iAlignment)
{
    if(!sHeaderInfo)
        return "参数错误：请传入要在页眉插入的文字";
     //默认左对齐
    if(!iAlignment)
        iAlignment = 0;
    if(iAlignment<0 || iAlignment>2)
        return "参数错误：请输入有效的对齐方式，对齐参数取值只能为0，1或2"
    var res;
    try
    {
        var window, view, pane, sel, para;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;

        view.SeekView = 9;          //页眉视图，wdSeekCurrentPageHeader=9
        sel.EndKey(6);              //将光标移到页眉最后
        para = sel.Paragraphs;
        para.Alignment = 0;         //先设置页眉左对齐
        if(iAlignment == 1)         //居中
            sel.TypeText("\t");
        else if(iAlignment == 2)    //居右
            sel.TypeText("\t\t");
        sel.TypeText(sHeaderInfo);  //在页眉添加文本
        view.SeekView = 0;          //回到文档主视图，wdSeekMainDocument=0
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("SetTextHeader", err);
    }
    return res;
}

/********************************************************************
 *函数功能：添加文字页脚
 *  sFooterInfo:要写入的文本
 *  iAlignment【可选】:对齐方式， 0居左 1居中 2居右，默认左对齐
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddTextFooter(sFooterInfo, iAlignment)
{
    if(!sFooterInfo)
        return "参数错误：请传入要在页脚插入的文字";
    //默认左对齐
    if(!iAlignment)
        iAlignment = 0;
    if(iAlignment<0 || iAlignment>2)
        return "参数错误：请输入有效的对齐方式，对齐参数取值只能为0，1或2"
    var res;
    try
    {
        var window, view, pane, sel, para;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;

        view.SeekView = 10;          //页脚视图，wdSeekCurrentPageFooter=10
        para = sel.Paragraphs;
        para.Alignment = iAlignment;//对齐方式
        sel.TypeText(sFooterInfo);  //在页脚添加文本
        view.SeekView = 0;          //回到文档主视图，wdSeekMainDocument=0
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("SetTextFooter", err);
    }
    return res;
}

/********************************************************************
 *函数功能：添加图片页眉
 *  sPictureFile:图片路径(包括图片文件名)
 *  iAlignment【可选】:对齐方式， 0居左 1居中 2局右
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddPictureHeader(sPictureFile, iAlignment)
{
     //默认左对齐
    if(!iAlignment)
        iAlignment = 0;
    if(iAlignment<0 || iAlignment>2)
        return "参数错误：请输入有效的对齐方式，对齐参数取值只能为0，1或2";
        
    var res;
    try
    {   
        if(g_docCtrl.IsFileExist(sPictureFile)=="NO")
            return "参数错误：指定图片不存在";   
        
        var window, view, pane, sel, para;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;

        view.SeekView = 9;                          //页眉视图，wdSeekCurrentPageHeader=9
        sel.EndKey(6);                              //将光标移到页眉最后
        para = sel.Paragraphs;
        para.Alignment = 0;                         //先设置页眉左对齐
        if(iAlignment == 1)                         //居中
            sel.TypeText("\t");
        else if(iAlignment == 2)                    //居右
            sel.TypeText("\t\t");
        sel.InlineShapes.AddPicture(sPictureFile);  //在页眉添加图片
        view.SeekView = 0;                          //回到文档主视图，wdSeekMainDocument=0
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AddPictureHeader", err);
    }
    return res;
}

/********************************************************************
 *函数功能：添加图片页脚
 *  sPicturePath:图片路径
 *  iAlignment【可选】:对齐方式， 0居左 1居中 2局右
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddPictureFooter(sPicturePath, iAlignment)
{
     //默认左对齐
    if(!iAlignment)
        iAlignment = 0;
    if(iAlignment<0 || iAlignment>2)
        return "参数错误：请输入有效的对齐方式，对齐参数取值只能为0，1或2";
        
    var res;
    try
    {   
        if(g_docCtrl.IsFileExist(sPicturePath)=="NO")
            return "参数错误：指定图片不存在";   
        
        var window, view, pane, sel, para;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;

        view.SeekView = 10;                         //页脚视图，wdSeekCurrentPageFooter=10
        para = sel.Paragraphs;
        para.Alignment = iAlignment;                //对齐方式
        sel.InlineShapes.AddPicture(sPicturePath);  //在页脚添加图片
        view.SeekView = 0;                          //回到文档主视图，wdSeekMainDocument=0
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AddPictureFooter", err);
    }
    return res;
}

/********************************************************************
 *函数功能：在页脚添加页码
 *  iAlignment【可选】:对齐方式， 0居左 1居中 2局右
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddPageNumbers(iAlignment) 
{
	//默认左对齐
    if(!iAlignment)
        iAlignment = 0;
    if(iAlignment<0 || iAlignment>2)
        return "参数错误：请输入有效的对齐方式，对齐参数取值只能为0，1或2";
        
	var res;
    try
    {
        var window, view, pane, sel;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;

        view.SeekView = 10; //页脚视图，wdSeekCurrentPageFooter=10

        //先清除页脚原先的内容
        sel.WholeStory();
        sel.TypeBackspace();

        //设置对齐方式
        var para;;
        para = sel.Paragraphs;
        para.Alignment = iAlignment;

        //添加页码
        var range;
        sel.TypeText("第");
        range = sel.Range;
        sel.Fields.Add(range, 33);//当前第几页，wdFieldPage=33
        sel.TypeText("页 共");
        range = sel.Range
        sel.Fields.Add(range, 26);//共多少页，wdFieldNumPages=26
        sel.TypeText("页");

        view.SeekView = 0; //回到主文档视图，wdSeekMainDocument=0
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AddPageNumbers", err);
    }
    return res;
}

/**********************************************************
 *功能描述：为当前通过IE控件打开的WORD文档添加文字水印
 *参数说明：sEffectInfo            文字信息
 *          sFontName【可选】      字体名称，如：宋体、隶书等。默认宋体
 *		    iFontSize【可选】      字体大小。默认30像素
 *		    rTransparency【可选】  透明度，如果是50%则填0.5。默认值0.5
 *		    iRotation【可选】      旋转角度，如：45。默认值0
 *返回值：成功返回"OK"，失败返回相应错误代码
 ***********************************************************/
function AddTextEffect(sEffectInfo,sFontName,iFontSize,rTransparency,iRotation)
{
    if(!sEffectInfo)
        return "参数错误：请传入水印文字";
        
    if(!sFontName)
        sFontName = "宋体";
    if(!iFontSize)
        iFontSize = 30;
    if(typeof rTransparency == "undefined")
        rTransparency = 0.5;
    if(!iRotation)
        iRotation = 0;
        
    var res;
    try
    {
        var window, pane, view, sel, shapes;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;
        
        view.SeekView = 9;      //页眉视图，wdSeekCurrentPageHeader=9
        shapes = sel.HeaderFooter.Shapes;
        //添加文字水印（msoTextEffect18=17），并选中添加的水印文字
        shapes.AddTextEffect(17, sEffectInfo, sFontName, iFontSize, false, false, 0, 0).Select();
        var shapeRange, fill;
        shapeRange = sel.ShapeRange;
        fill = shapeRange.Fill;
        fill.Visible = true;                    //填充色可见
        fill.Solid();                           //填充类型为纯色
        fill.Transparency = rTransparency;      //设置透明度;
        shapeRange.Rotation = iRotation;        //设置文字的角度
        //设置水平位置与纵向页边距关联，wdRelativeHorizontalPositionMargin=0
        shapeRange.RelativeHorizontalPosition = 0;
        //设置垂直位置与纵向页边距关联，wdRelativeVerticalPositionMargin = 0
        shapeRange.RelativeVerticalPosition = 0;
        shapeRange.Left = -999995;      //水平居中，wdShapeCenter = -999995
        shapeRange.Top = -999995;       //垂直居中，wdShapeCenter = -999995
        
        view.SeekView = 0;              //回到主视图
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AddTextEffect", err);
    }
    return res;
}

/********************************************************************
 *函数功能：为当前通过IE控件打开的WORD文档添加图片水印
 *  sPictureFile:图片路径(包括图片文件名)
 *  bStretch【可选】:为true则拉伸图片，否则居中。默认居中
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddPictureEffect(sPictureFile, bStretch)
{
    
    var res;
    try
    {
        if(g_docCtrl.IsFileExist(sPictureFile)=="NO")
            return "参数错误：指定图片不存在";
        if(!bStretch)
            bStretch = false;
        
        var window, pane, view, sel, shapes;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;
        
        view.SeekView = 9;      //页眉视图，wdSeekCurrentPageHeader=9
        shapes = sel.HeaderFooter.Shapes;
        //添加水印图片，并选中添加的图片(一定要选中)
        shapes.AddPicture(sPictureFile, false, true, 0, 0).Select();
        
        var shapeRange, fill;
        shapeRange = sel.ShapeRange;
        //拉伸图片
        if(bStretch==true)
        {
            shapeRange.Width = g_wordApp.CentimetersToPoints(20.9);
            shapeRange.Height = g_wordApp.CentimetersToPoints(23.04);
        }
        
        //设置水平位置与纵向页边距关联，wdRelativeHorizontalPositionMargin=0
        shapeRange.RelativeHorizontalPosition = 0;
        //设置垂直位置与纵向页边距关联，wdRelativeVerticalPositionMargin = 0
        shapeRange.RelativeVerticalPosition = 0;
        shapeRange.Left = -999995;      //水平居中，wdShapeCenter = -999995
        shapeRange.Top = -999995;       //垂直居中，wdShapeCenter = -999995
        
        view.SeekView = 0;              //回到主视图
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AddPictureEffect", err);
    }
    return res;
}

/********************************************************************
 *函数功能：在当前光标处插入附件
 *  sFile:附件路径(包括附件名)
 *  bDisplayAsIcon【可选】:取值为true，插入的附件将作为图标显示；取值为false,插入的附件将作为图片显示。默认取值为false
 *  sIconFile【可选】:bDisplayAsIcon取值为true，该参数则为图标文件路径(包括图标文件名)。否则，不需要该参数
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function AddAttachment(sFile, bDisplayAsIcon, sIconFile)
{
    var res;
    try
    {
        if(g_docCtrl.IsFileExist(sFile)=="NO")
            return "参数错误：指定附件不存在";
             
        var sel, inlineShapes, inlineShape;
        
        sel=g_wordApp.Selection;
        //折叠选区到最后，即不要选中任何内容
	    sel.Collapse(0);
        inlineShapes=sel.InlineShapes;
        
        //添加附件
        bDisplayAsIcon = (bDisplayAsIcon ? true : false);
        inlineShape=inlineShapes.AddOLEObject("", sFile, false, bDisplayAsIcon, sIconFile);
        
        //设置图标的宽度和高度均为50像素
        if(bDisplayAsIcon)
        {
            inlineShape.Width = 50;
            inlineShape.Height = 50;
        }
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AddAttachment", err);
    }
    return res;
}

/********************************************************************
 *函数功能：将一个文件的内容插入到当前光标处
 *  sFile:文件路径(包含文件名)
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function InsertFile(sFile)
{
    if(!sFile)
        return "参数错误：请传递有效参数！";
        
    var res;
    try
    {
        if(g_docCtrl.IsFileExist(sFile)=="NO")
        {
            res = "参数错误：文件<" + sFile + ">不存在！";
            return res;
        }
        
        var sel;
        sel = g_wordApp.Selection;
        //折叠选区到最后，即不要选中任何内容
		sel.Collapse(0);
        //第3个参数为false表明不要弹出"转换文件"对话框
        sel.InsertFile(sFile, "", false, false, false);
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("InsertFile", err);
    }
    return res;
}

/********************************************************************
 *函数功能：在指定书签处插入时间日期
 *  sFormat:日期时间格式，如:YYYY-MM-dd hh:mm:ss
 *  sLabelName:书签名
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function InsertDateTime(sFormat, sLabelName)
{
    if(!sFormat || !sLabelName)
        return "参数错误：请传入有效的参数";
    
    var res;
    try
    {
        if(!SetLabel(sLabelName))
            return "参数错误：指定书签不存在";
            
        var sel=g_wordApp.Selection;
        //折叠选区到最后，即不要选中任何内容
		sel.Collapse(0);
        sel.InsertDateTime(sFormat, 0);
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("InsertDateTime", err);
    }
    return res;
}

/********************************************************************
 *函数功能：清除页眉或者页脚
 *  bFooter【可选】:为ture，则清除页脚，否则清除页眉。默认清除页眉
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function ClearHeaderOrFooter(bFooter) {
    var res;
    if (bFooter) {
        res = ClearPageFooter();
    } else {
        res = ClearPageHeader();
    }
    return res;
}

//删除页眉
function ClearPageHeader() {
    var res;
    try {
        var window, view, pane, sel;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;
        //切换到页眉视图，wdSeekCurrentPageHeader=9
        view.SeekView = 9;
        //选中页眉所有内容
        sel.WholeStory();
        //删除格式
        sel.ClearFormatting();
        //按下Backspace键，用于删除选中的内容（注：如果用sel.Delete(1, 1)会连水印一起删除）
        sel.TypeBackspace();
        //删除页眉段落的所有制表符
        var paraformat = sel.ParagraphFormat;
        var tabs = paraformat.TabStops;
        tabs.ClearAll();
        //在页眉段落中添加2个制表符
        tabs.Add(g_wordApp.CentimetersToPoints(7.33), 1, 0);
        tabs.Add(g_wordApp.CentimetersToPoints(14.65), 2, 0);
        //回到主文档视图，wdSeekMainDocument=0
        view.SeekView = 0;
        res = "OK";
    } catch(err) {
        res = GenerateErrorString("ClearPageHeader", err);
    }
    return res;
}

//删除页脚
function ClearPageFooter() {
    var res;
    try {
        var window, view, pane, sel;
        window = g_wordApp.ActiveWindow;
        pane = window.ActivePane;
        view = pane.View;
        sel = g_wordApp.Selection;
        //切换到页眉视图，wdSeekCurrentPageFooter=10
        view.SeekView = 10;
        //选中页脚所有内容
        sel.WholeStory();
        //删除格式
        sel.ClearFormatting();
        //按下Backspace键，用于删除选中的内容
        sel.TypeBackspace();
        //按下Delete键，用于删除页脚最后的段落标记
        sel.Delete(1, 1);
        //回到主文档视图，wdSeekMainDocument=0
        view.SeekView = 0;
        res = "OK";
    } catch(err) {
        res = GenerateErrorString("ClearPageFooter", err);
    }
    return res;
}

/**********************************************************
 *功能描述：设置字体和颜色
 *参数说明：sFontName      字体名称，如：宋体、隶书等
 *          iFontSize      字体大小
 *		    R              颜色RGB值的R值
 *		    G              颜色RGB值的G值
 *		    B              颜色RGB值的B值
 *返回值：成功返回"OK"，失败返回相应错误代码
 ***********************************************************/
function SetFont(sFontName, iFontSize, R, G, B)
{
    var res;
    try
    {
        var font = g_wordApp.Selection.Font;
        font.Name = sFontName;
        font.Size = iFontSize;
        font.Color = 0x1*R + 0x100*G +0x10000*B;
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("SetFont", err);
    }
    return res;
}

/********************************************************************
 *函数功能：显示或者不显示痕迹
 *  iShow【可选】:0不显示痕迹，非0显示痕迹。默认不显示痕迹
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function ShowTrace(iShow)
{
    var res;
    try
    {
        if(g_curDoc.ProtectionType != -1)
            return "操作错误：文档受到保护，不能操作";
            
        iShow = (iShow ? 1 : 0);
                  
        //为1是显示标记，0是不显示标记
        g_curDoc.ShowRevisions = iShow;
        g_curDoc.PrintRevisions = iShow;
        //1是原始状态，wdRevisionsViewOriginal=1
        //0是最终状态，wdRevisionsViewFinal = 0
        g_wordApp.ActiveWindow.ActivePane.View.RevisionsView = iShow;
        
        
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("ShowTrace", err);
    }
    return res;
}

/********************************************************************
 *函数功能：设置窗体域的可操作属性(此功能在保护模式为只允许填写窗体下才能看到效果)
 *  iFlag【可选】:0表示不可以操作，非0表示可以操作。默认不可以操作
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
 function EnableFormFields(iFlag)
 {
    var res;
    try
    {
        var count, formFields, formField;
        formFields = g_curDoc.FormFields;
        count = formFields.Count;
        for(var i=1; i<=count; i++)
        {
            formField = formFields.Item(i);
            if(formField)
            {
                if(!iFlag)
                    formField.Enabled = false;
                else
                    formField.Enabled = true;
            }
        }
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("EnableFormFields", err);
    }
    return res;
 }
 
 //接受所有修订
function AcceptAllRevisions()
{
    var res;
    try
    {
        g_curDoc.AcceptAllRevisions();
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("AcceptAllRevisions", err);
    }
    return res;
}

//删除所有批注
function DeleteAllComments()
{
    var res;
    try
    {
        g_curDoc.DeleteAllComments();
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("DeleteAllComments", err);
    }
    return res;
}

//弹出打印对话框
function ShowPrintDialog()
{
	var res;
    try
    {
        var dlg;
        g_wordApp.ActiveWindow.Activate();
        if (g_iAppType==2) {    //WPS显示打印对话框
            dlg = g_wordApp.Dialogs.Item(16387);
        } else {                //Word显示打印对话框
            dlg = g_wordApp.Dialogs.Item(88);
        }
        dlg.Show();
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("ShowPrintDialog", err);
    }
    return res;
}

/********************************************************************
 *函数功能：设置指定窗体域的值
 *  sDomainName:域名
 *  sValue：域值
 *返回值：成功返回"OK"，失败返回相应错误代码
 ********************************************************************/
function SetDomainValue(sDomainName, sValue)
{
    if(!sDomainName || !sValue)
        return "参数错误：请传入有效的参数";
	var res;
    try
    {
        var formFields, formField;
        formFields =  g_curDoc.FormFields;
        try
        {
            formField = formFields.Item(sDomainName);
        }
        catch(e)
        {
            return "参数错误：指定窗体域不存在";
        }
        formField.Result = sValue;
        res = "OK";
    }
    catch(err)
    {
        res = GenerateErrorString("SetDomainValue", err);
    }
    return res;
}

/********************************************************************
 *函数功能：获取指定窗体域的值
 *  sDomainName:域名
 *返回值：返回一个数组，第一个元素为错误代码；
 *        如果第一个元素为OK,第二个元素则为指定域的值；
 *        如果第一个元素不为OK,则第二个元素无效。
 ********************************************************************/
function GetDomainValue(sDomainName)
{
	var arrRes = new Array();
	if(!sDomainName)
	{
        arrRes[0] = "参数错误：请传入有效的窗体域的域名";
        return arrRes;
    }
    try
    {
        var formFields, formField;
        formFields =  g_curDoc.FormFields;
        try
        {
            formField = formFields.Item(sDomainName);
        }
        catch(e)
        {
            arrRes[0] = "参数错误：窗体域<" + sDomainName + ">不存在！";
            return arrRes;
        }
        arrRes[1] = formField.Result;
    }
    catch(err)
    {
        arrRes[0] = GenerateErrorString("GetDomainValue", err);
    }
    return arrRes;
}

/********************************************************************
 *函数功能：获取所有窗体域的域名和域值
 *返回值：成功返回所有域的值，失败返回相应错误代码
 ********************************************************************/
function GetDomainValues()
{
	var arrRes = new Array("OK");
	
    try
    {
        var formFields, formField, count;
        formFields =  g_curDoc.FormFields;
        count = formFields.Count;
        arrRes[1] = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        arrRes[1] += "\r\n<info>";
        for(var i=1; i<=count; i++)
        {
            formField = formFields(i);
            arrRes[1] += "\r\n<item id=\"" + formField.Name + "\">";
            try
            {
                arrRes[1] += formField.Result;
            }
            catch(e)
            {
            }
            arrRes[1] += "</item>";
        }
        arrRes[1] += "\r\n</info>";
    }
    catch(err)
    {
        arrRes[0] = GenerateErrorString("GetDomainValues", err);
    }
    return arrRes;
}
