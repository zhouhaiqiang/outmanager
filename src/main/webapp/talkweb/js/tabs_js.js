// JavaScript Document
function MM_findObj(n, d) {//v4.01
				var p,i,x; 
				if(!d) d=document; 
				if((p=n.indexOf("?"))>0&&parent.frames.length) 
				{
						d=parent.frames[n.substring(p+1)].document; 
					n=n.substring(0,p);
				}
				if(!(x=d[n])&&d.all) 
					x=d.all[n]; 
			
				for (i=0;!x&&i<d.forms.length;i++) 
					x=d.forms[i][n];
			
				for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
					x=MM_findObj(n,d.layers[i].document);
			
				if(!x && d.getElementById) 
					x=d.getElementById(n);
			
				return x;
			}
			
			function MM_showHideLayers() { //v6.0
				var curdate = new Date();
				var curtime = '?curtime='+curdate.getYear()+curdate.getMonth()+curdate.getDate()+
					curdate.getHours()+curdate.getMinutes()+curdate.getSeconds()+curdate.getMilliseconds();
			  var i,p,v,obj,args=MM_showHideLayers.arguments;
			  for (i=0; i<(args.length-2); i+=3) 
				if ((obj=MM_findObj(args[i]))!=null) 
				{
					v=args[i+2];
						if (obj.style)
					{
						obj=obj.style; v=(v=='show')?'':(v=='hide')?'none':v; 
					}
						obj.display=v; 
						if(args[i+2]=='show')
					{
					switch (args[i])
					{ 
						case 'Layer1':
			try{
							frm.src = 'http://www.baidu.com'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
						case 'Layer2':
			try{
							frm.src = 'http://news.baidu.com/'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
						case 'Layer3':
			try{
							frm.src = 'http://tieba.baidu.com/'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
						case 'Layer4':
			try{
							frm.src = 'http://zhidao.baidu.com/'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
						case 'Layer5':
			try{
							frm.src = 'http://music.baidu.com/'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
						case 'Layer6':
			try{
							frm.src = 'http://image.baidu.com/'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
						case 'Layer7':
			try{
							frm.src = 'http://map.baidu.com/'+curtime;
							window.open(frm.src,'frm');
			}catch(e){alert(e);}
							break;
					}
				}
			  }
			}