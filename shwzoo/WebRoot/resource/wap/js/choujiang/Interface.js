var jaaulde=window.jaaulde||{};jaaulde.utils=jaaulde.utils||{};jaaulde.utils.cookies=(function(){var resolveOptions,assembleOptionsString,parseCookies,constructor,defaultOptions={expiresAt:null,path:'/',domain:null,secure:false};resolveOptions=function(options){var returnValue,expireDate;if(typeof options!=='object'||options===null){returnValue=defaultOptions;}else
{returnValue={expiresAt:defaultOptions.expiresAt,path:defaultOptions.path,domain:defaultOptions.domain,secure:defaultOptions.secure};if(typeof options.expiresAt==='object'&&options.expiresAt instanceof Date){returnValue.expiresAt=options.expiresAt;}else if(typeof options.hoursToLive==='number'&&options.hoursToLive!==0){expireDate=new Date();expireDate.setTime(expireDate.getTime()+(options.hoursToLive*60*60*1000));returnValue.expiresAt=expireDate;}if(typeof options.path==='string'&&options.path!==''){returnValue.path=options.path;}if(typeof options.domain==='string'&&options.domain!==''){returnValue.domain=options.domain;}if(options.secure===true){returnValue.secure=options.secure;}}return returnValue;};assembleOptionsString=function(options){options=resolveOptions(options);return((typeof options.expiresAt==='object'&&options.expiresAt instanceof Date?'; expires='+options.expiresAt.toGMTString():'')+'; path='+options.path+(typeof options.domain==='string'?'; domain='+options.domain:'')+(options.secure===true?'; secure':''));};parseCookies=function(){var cookies={},i,pair,name,value,separated=document.cookie.split(';'),unparsedValue;for(i=0;i<separated.length;i=i+1){pair=separated[i].split('=');name=pair[0].replace(/^\s*/,'').replace(/\s*$/,'');try
{value=decodeURIComponent(pair[1]);}catch(e1){value=pair[1];}if(typeof JSON==='object'&&JSON!==null&&typeof JSON.parse==='function'){try
{unparsedValue=value;value=JSON.parse(value);}catch(e2){value=unparsedValue;}}cookies[name]=value;}return cookies;};constructor=function(){};constructor.prototype.get=function(cookieName){var returnValue,item,cookies=parseCookies();if(typeof cookieName==='string'){returnValue=(typeof cookies[cookieName]!=='undefined')?cookies[cookieName]:null;}else if(typeof cookieName==='object'&&cookieName!==null){returnValue={};for(item in cookieName){if(typeof cookies[cookieName[item]]!=='undefined'){returnValue[cookieName[item]]=cookies[cookieName[item]];}else
{returnValue[cookieName[item]]=null;}}}else
{returnValue=cookies;}return returnValue;};constructor.prototype.filter=function(cookieNameRegExp){var cookieName,returnValue={},cookies=parseCookies();if(typeof cookieNameRegExp==='string'){cookieNameRegExp=new RegExp(cookieNameRegExp);}for(cookieName in cookies){if(cookieName.match(cookieNameRegExp)){returnValue[cookieName]=cookies[cookieName];}}return returnValue;};constructor.prototype.set=function(cookieName,value,options){if(typeof options!=='object'||options===null){options={};}if(typeof value==='undefined'||value===null){value='';options.hoursToLive=-8760;}else if(typeof value!=='string'){if(typeof JSON==='object'&&JSON!==null&&typeof JSON.stringify==='function'){value=JSON.stringify(value);}else
{throw new Error('cookies.set() received non-string value and could not serialize.');}}var optionsString=assembleOptionsString(options);document.cookie=cookieName+'='+encodeURIComponent(value)+optionsString;};constructor.prototype.del=function(cookieName,options){var allCookies={},name;if(typeof options!=='object'||options===null){options={};}if(typeof cookieName==='boolean'&&cookieName===true){allCookies=this.get();}else if(typeof cookieName==='string'){allCookies[cookieName]=true;}for(name in allCookies){if(typeof name==='string'&&name!==''){this.set(name,null,options);}}};constructor.prototype.test=function(){var returnValue=false,testName='cT',testValue='data';this.set(testName,testValue);if(this.get(testName)===testValue){this.del(testName);returnValue=true;}return returnValue;};constructor.prototype.setOptions=function(options){if(typeof options!=='object'){options=null;}defaultOptions=resolveOptions(options);};return new constructor();})();(function(){if(window.jQuery){(function($){$.cookies=jaaulde.utils.cookies;var extensions={cookify:function(options){return this.each(function(){var i,nameAttrs=['name','id'],name,$this=$(this),value;for(i in nameAttrs){if(!isNaN(i)){name=$this.attr(nameAttrs[i]);if(typeof name==='string'&&name!==''){if($this.is(':checkbox, :radio')){if($this.attr('checked')){value=$this.val();}}else if($this.is(':input')){value=$this.val();}else
{value=$this.html();}if(typeof value!=='string'||value===''){value=null;}$.cookies.set(name,value,options);break;}}}});},cookieFill:function(){return this.each(function(){var n,getN,nameAttrs=['name','id'],name,$this=$(this),value;getN=function(){n=nameAttrs.pop();return!!n;};while(getN()){name=$this.attr(n);if(typeof name==='string'&&name!==''){value=$.cookies.get(name);if(value!==null){if($this.is(':checkbox, :radio')){if($this.val()===value){$this.attr('checked','checked');}else
{$this.removeAttr('checked');}}else if($this.is(':input')){$this.val(value);}else
{$this.html(value);}}break;}}});},cookieBind:function(options){return this.each(function(){var $this=$(this);$this.cookieFill().change(function(){$this.cookify(options);});});}};$.each(extensions,function(i){$.fn[i]=this;});})(window.jQuery);}})();
///////////////////���ݲ�֧��localStorage���Ե������//////////////////////
//if(!window.localStorage){
//    var localStorage={};
//    var localStorageCook=$.cookies.get( 'localStorage');
//    if(localStorageCook){
//        if(typeof(localStorageCook)=="string"){
//            localStorage=localStorageCook;
//        }else{
//            localStorage=JSON.stringify(localStorageCook);
//        }
//    }
//    localStorage=JSON.parse(localStorage);
//    function removeItemWD(strKey){
//        delete localStorage[strKey];
//        setlocalStorage();
//    }
//    localStorage.removeItem=removeItemWD;
//    var localStorageStr=JSON.stringify(localStorage);
//
//    function setlocalStorage(){
//        localStorageStr=JSON.stringify(localStorage);
//        $.cookies.set( 'localStorage',localStorageStr);
//        localStorage.removeItem=removeItemWD;
//    }
//
//    setlocalStorage();
//    setInterval(function(){
//        if(localStorageStr!=JSON.stringify(localStorage)){
//           setlocalStorage();
//        };
//    },500);
//    $(function(){
//        $("body").click(function(e){
//            if($(e.target).is("a")){
//                setlocalStorage();
//            }
//        })
//    })
//
//}

	
	
//alert(navigator.connection)
window.addEventListener("online", function(e) {
	window.location.href=window.location.href;
})

////////ҳ�����Ч��/////////
function strLoading(){
    $("body").append("<div class='loadMask'></div>");
    setTimeout(function(){
        endLoading();
    },10000);
}
function endLoading(){
    $(".loadMask").remove();
}


/////////ˢ��ҳ��//////////
function Refreshpage(tok){
	var thisHref=window.location.href;
	tok=tok||"";
	if(tok.length>10){
		localStorage["token"]=tok;
		if(thisHref.indexOf("html?")>-1){
			var thisHrefAry=thisHref.split("?");
			var Param=getSuotpar(thisHrefAry[1]);
			var strpam="";
			for(var o in Param){
				if(strpam==""){
					strpam+="?";	
				}else{
					strpam+="&"
				}
				if(o=="token"){
					strpam+=(o+"="+tok);
				}else{
					strpam+=(o+"="+Param[o]);
				}
				console.log(tok)
				
			}
			var shutiid="";
			if(strpam.indexOf("token")<0){
				if(strpam.length<1){
					strpam="?token="+tok;
				}else{
					strpam+=("&token="+tok);
				}
			}
			thisHref=thisHrefAry[0]+strpam;
		}else{
			thisHref=thisHref+"?token="+tok;
		}
	}
	
	if(tok=="clear"){
		localStorage["token"]="";
		var nogoto=["ShopMain","Turntable","ProductDetails","GroupDetails","Buying_activity","DiscoverMain"];
		var istiao=true;
		for(var w=0;w<nogoto.length;w++){
			if(thisHref.indexOf(nogoto[w])>-1){
				istiao=false;
			}
		}
		if(istiao){
			thisHref="MemberMain.html?token=";
		}else{
			if(thisHref.indexOf("html?")>-1){
				var Paramas=getSuotpar(thisHref.split("?")[1]);
				var strpama="";
				for(var o in Paramas){
					if(strpama==""){
						strpama+="?";	
					}else{
						strpama+="&"
					}
					if(o=="token"){
						//strpama+=(o+"="+tok);
					}else{
						strpama+=(o+"="+Paramas[o]);
					}
				}
				if(strpama.length<1){
					strpama="?token=";
				}else{
					strpama=strpama+"&token=";
				}
				thisHref=thisHref.split("?")[0]+strpama;
			}else{
				thisHref=thisHref+"?token="
			}
			
		}
		
	}
	setTimeout(function(){
		/*if(thisHref.indexOf("token=")>-1){
			var shucs=thisHref.split("?");
			for(var pk=0;pk<shucs.length;pk++){
				var slihus=shucs[pk].split("=");
				if(slihus[0]=="token"){
					alert(slihus[1]);
				}
			}
		}else{
			alert("nulltoken");
		}*/
		//alert(thisHref)
		
		/*var urelss=$("<div></div>");
		urelss.html(thisHref);
		urelss.css({position:"absolute",width:"100%",fontSize:"20px",background:"#fff",left:0, top:0,zIndex:10,wordBreak:"break-all",wordWrap:"break-word"});
		$("html").append(urelss)*/
		window.location.href=thisHref;
	},500)
    
}

function getSuotpar(str){
	var parm={};
	var strAr=[];
		strAr=str.split("&");
	for(var i=0;i<strAr.length;i++){
		var psrx=strAr[i].split("=");
		parm[psrx[0]]=psrx[1];
	}
	return parm;
}

var $upary=[];
///////����ˢ��////////
function dragdownEvent(){
	Refreshpage();
}
///////�������ظ���////////
function dragupEvent(){
	for(var i=0;i<$upary.length;i++){
			$upary[i]();
		}
}




/////////url��ʽ��//////////
function urlparse(str){
        var strary=str.split("=");
        var retrt="";
        for(var i=0;i<strary.length;i++){
            if(retrt!=""){
                retrt+=":";
            }
            retrt+=strary[i];

        }

        return retrt;
}

function NosetlocalStorage(){
    if(window.setlocalStorage){
        setlocalStorage();
    }
}



function joinSrt(str){
    var dataAyr=str.split("|||");
    if(dataAyr.length>1){
        var blNamr=dataAyr[3]+"AA";
        var zonleng=dataAyr[2];
        if(!window[blNamr]){
            window[blNamr]=[];
        }
        window[blNamr].push({i:dataAyr[1],dt:dataAyr[0]});
        if(window[blNamr].length>=parseInt(zonleng)){
            window[blNamr].sort(function(a,b){
                return a.i- b.i;
            });
            var datastre="";
            for(var s=0;s<window[blNamr].length;s++){
                datastre+=window[blNamr][s].dt;
            }
            window[blNamr]=null;
            alert(datastre)
            return datastre;
        }
        return false;
    }else{
        return str;
    }
}


//console.log(joinSrt("asdasdasdsadAAAAa|||0|||3|||abhshd"));
//console.log(joinSrt("asdasdasdsadBBBBB|||2|||3|||abhshd"));
//console.log(joinSrt("asdasdasdsadCCCC|||1|||3|||abhshd"));


///////////////////���ݲ�֧��localStorage���Ե������****************//////////////////////

function gotourl(urlstr){
    window.location.href=urlstr;
};

//var hrefurl="http://yinlink.f3322.net:9001";
//var hrefurl="http://172.19.1.20:8080/jkmanage";
var hrefurl="http://estore.jingcaiwang.cn:9001";
var PCajaxurl="http://httptest.com01.org/httpTest.aspx";
function html_decode(str)
{
    var strA=str.split("&lt;");
    str=strA.join("<");
    strA=str.split("&gt;");
    str=strA.join(">");

    return str;
}
var de=decodeURIComponent;//����////
var en=encodeURIComponent; ///����///

var deA=function(str){
    var stra=str.split("\+");
    //alert(JSON.stringify(stra))
    var sstr="";
    for(var i=0;i<stra.length;i++){
        if(sstr!=""){
            sstr+="%20";
        }
        sstr+=stra[i];
    }
	
   // alert(de(sstr))
    //console.log(str)
    return de(sstr);
	
}; ///����///

var enjm=function(str){
    var tostr=str;
    var statJont=tostr.split("[");
    tostr=statJont.join("|a|a|");
    statJont=tostr.split("]");
    tostr=statJont.join("|b|b|");
    statJont=tostr.split("{");
    tostr=statJont.join("|1|1|");
    statJont=tostr.split("}");
    tostr=statJont.join("|2|2|");
    statJont=tostr.split(",");
    tostr=statJont.join("|d|d|");
    statJont=tostr.split('"');
    tostr=statJont.join("|y|y|");
    statJont=tostr.split(':');
    tostr=statJont.join("|m|m|");
    return statJont;
};
/////��������////////
var call_net; ////����ӿ�//////
var call_tell;////�绰//////
var call_sms;////����//////
var call_camera;////����ͷ//////
var call_album;////���//////
var call_netYs;/////����ԭ�����󷽷�//////


var call_roupedProduct;//////�����Ʒ����////////
var call_preferences;//////����ѡ��////////
var call_logout; ///////�˳���¼///////////
var call_pushlogin; ///////��ת����½ҳ///////////

var call_historydel;/////����ָ��ҳ��/////
var call_clearCookie;//////�������/////////

var showButton;/////��ʾ��ť////////
var gotoProduct;////��ת����Ʒ����ҳ////
var uploadimg;//////�ϴ�ͼƬ/////
var selectAddre;//////ѡ���ַ////
var selectCoupon;////ѡ���Ż�ȯ/////
var PushSet; /////��������//////
var openURL;///��url///
var merchantJSshow;////////���̵�����ά��////////
var Buyagain;///////�ٴι���////////////
var gotoUrlall;////��ת���̶�ҳ��1����2��Ʒ3�Ź� ��id/////
var adddragupEvent;////////////
var zhaohui;///////�һ�֧������/////



var Geturlstring=getUrlParam("ch");
if(Geturlstring!=0 || Geturlstring!=1 || Geturlstring!=2){
    localStorage.ch="2";
}



////////////�豸�ж�//////////
var userAge=navigator.userAgent;
var windwHref=window.location.href;
var ISPC=IsPC();
///////////�������//////////////
function addvar(){
    var vatageObj=$("var");
    if(vatageObj.length<0) return;
    vatageObj.each(function(){
        var thistext=$(this).text();
        if(eval(thistext)){
            $(this).after(eval(thistext).toString());
        }
        $(this).remove();
    });
    vatageObj=null;
}


function Urlpushlogin(str){
    if((str==undefined || str=="") && window.location.href.indexOf("MemberMain.html")<0){
        call_pushlogin();
    }else{
       // call_net(hrefurl+"/app/api/userinfo/selectInfo",'{"token":"'+str+'","ch":"2"}','callback_logyz');
    }
}

function callback_logyz(data){
    var data_dianpu=data;
    var datajon=JSON.parse(data_dianpu);
    if(datajon.code!=0){
        alert("�����µ�¼��");
        call_pushlogin();
    }
}



//////�ӳ�token////
function tokenyanshi(){

}



function getUrlParamURLys(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //����һ������Ŀ�������������ʽ����
    var r = window.location.search.substr(1).match(reg);  //ƥ��Ŀ�����
    if (r!=null){
        return unescape(r[2]);
    }
    return false; //���ز���ֵ
}


function getUrlParamURL(name)
{
    var pahegts="";
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //����һ������Ŀ�������������ʽ����
    var r = window.location.search.substr(1).match(reg);  //ƥ��Ŀ�����
    if (r!=null){
        localStorage[name]=unescape(r[2]);
        return unescape(r[2]);
    }
    pahegts=localStorage[name];
    if(pahegts!=undefined && pahegts!=""){
        return pahegts;
    }
    return false; //���ز���ֵ
}

function getToken(){
	var token=GetCookie("_u_token");
	if(token!=null){
		return token;
	}else{
		return false;
	}
}


function getUrlParam(name)
{

    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //����һ������Ŀ�������������ʽ����
    var r = window.location.search.substr(1).match(reg);  //ƥ��Ŀ�����
    var pahegts;
    if (r!=null){
        var parule=unescape(r[2]);
        localStorage[name]=parule;
        NosetlocalStorage();

        if(!window.isMaintoken && name=="token"){
                Urlpushlogin(parule);
        }
        if(name=="token"){
            //call_net(hrefurl+"/app/api/userinfo/delayheartbeat",'{"token":"'+unescape(r[2])+'"}','tokenyanshi');
        }
        return unescape(r[2]);
    }
    pahegts=localStorage[name];

    if(!window.isMaintoken && name=="token"){
        Urlpushlogin(pahegts);
    }
    if(pahegts!=undefined && pahegts!=""){
       // if(name=="token"){
            //call_net(hrefurl+"/app/api/userinfo/delayheartbeat",'{"token":"'+pahegts+'"}','tokenyanshi');
       // }
        return pahegts;
    }
    if(name=="ch"){
        return "2";
    }
    return false; //���ز���ֵ
}
if(windwHref.indexOf("http:")>-1 || userAge.indexOf("indows")>-1 || userAge.indexOf("Macintosh")>-1){
    var PCbendi=windwHref.indexOf(":/")>-1;
    webjs(PCbendi);

}else{
    if(userAge.indexOf("Android")>-1 || userAge.indexOf("android")>-1){
        appAndroid();
    }else if(userAge.indexOf("iPhone")>-1 || userAge.indexOf("iPad")>-1 || userAge.indexOf("iPod")>-1 || userAge.indexOf("IOS")>-1 || userAge.indexOf("ios")>-1 || userAge.indexOf("Ios")>-1){
        appIos();
    }
}
function IsPC()
{
    var userAgentInfo = navigator.userAgent;
    var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }
    }
    return flag;
}


//////������js/////////////////////////////////////////////////////////////////////////////////
function webjs(isBd){
    ////����//////
    call_net=function(url,argument,callbackA){
        if(isBd){ /////�����pc�˱���//////
            $.ajax({
                type: "get",
                url: PCajaxurl, // ������ǲ�ͬ�ڵ�ǰ���һ��URL��ַ�����ﵥ����ʾ������ͬ��
                dataType: "jsonp",
                jsonp: "jsonpcallback",  // ָ���ص��������������ֿ���Ϊ����������ϲ���ģ�����callback��������������һ�е�GET����һ��
                data: "url="+url+"&datas="+argument, // jsonpcallback�������jsonpֵһ��
                success: function (json) {
                    eval(callbackA)(JSON.stringify(json));
                }
            });
        }else{ ///////����Ƿ�������////////
            $.post(url,eval(JSON.parse(argument)),eval(callback));
        }
    };
    /////�绰////////
    call_tell=function(phone){
        if(ISPC){
            alert("�����ֻ��豸����绰��");
        }else{
            window.open("tel:"+phone,"_blank");
        }
    }
    /////����////////
    call_sms=function(){
        alert("�������֧�ִ˹��ܣ�");
    }
    /////����ͷ////////
    call_camera=function(){
        alert("�������֧�ִ˹��ܣ�");
    }
    /////���////////
    call_album=function(){
        alert("�������֧�ִ˹��ܣ�");
    }

    //////��ϲ�Ʒ/////////
    call_roupedProduct=function(){}
    //////����ѡ��////////
    call_preferences=function(){};
    /////�˳���¼///////
    call_logout=function(){
        setTimeout(function(){
            window.location="MemberMain.html?token=";
        },200);
    };
    /////��ת����¼ҳ///////
    call_pushlogin=function(){
		//if(window.location.href.indexOf("MemberMain.html")<0){
			window.location="MemberMain.html?token=";
		//               }
        
    };
    /////����ָ��ҳ��///////
    call_historydel=function(){};
    /////�������///////
    call_clearCookie=function(){};
    ////////���ذ�ť/////////
    showButton=function(){};
    ////////��ת����Ʒ����ҳ//////////
    gotoProduct=function(id){
        window.location="ProductDetails.html?sid="+id;
    };
	
	/////////�ϴ�ͼƬ/////////
	uploadimg=function(){};
    //////����ʱѡ���ַ////////
    selectAddre=function(){};
    /////////ѡ���Ż�ȯ/////////////
    selectCoupon=function(){};
    /////////ԭ����������////////////
    call_netYs=function(){};
    confirm=function(){};
    PushSet=function(){}; /////��������//////
    openURL=function(){};///��url///
    merchantJSshow=function(){};////������ά��/////

    Buyagain=function(jso){};/////�ٴι���////////
    gotoUrlall=function(tp,id){
        //alert(tp+":"+id)
    };///��ת���ƶ�webviwҳ��////
	adddragupEvent=function(fn){
		$upary.push(fn);
		if(window["ISadddragupEvent"]) return;
		ISadddragupEvent=true;
		var doucmnd=$(document);
		var windA=$(window);
		var srintime;
		function srintime_hs(){
			if(windA.scrollTop()>doucmnd.height()-windA.height()-10){
				dragupEvent();
			}
		}
		windA.scroll(function(){
			clearTimeout(srintime);
			if(windA.scrollTop()>doucmnd.height()-windA.height()-10){
				srintime=setTimeout(srintime_hs,1000);
			}
		})
		
	};///ע���������¼�/////
	zhaohui=function(){};//////�һ�֧������///////

}





//////ios_js///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function appIos(){
    
    
    //////��������ӿ�////
    call_net=function(url,argument,callbackA){
        setTimeout(function(){
            window.location.href="function|||getcllist|||"+url+"|||"+argument+"|||"+callbackA;
        },50)
    }

    /////////ԭ����������////////////
    call_netYs=function(url,argument,callbackA){
        setTimeout(function(){
            window.location.href="function|||getcllist|||"+url+"|||"+argument+"|||"+callbackA;
        },50)
    }



    ////�绰//////
    call_tell=function(phone){
        setTimeout(function(){
            window.location.href="function|||tell|||"+phone;
        },50)
    }
////����//////
    call_sms=function(phone,text){
        setTimeout(function(){
            window.location.href="function|||sms|||"+phone+"|||"+text;
        },50)
    }
////����ͷ//////
    call_camera=function(){
        setTimeout(function(){
            window.location.href="function|||camera";
        },50)
    }
////���//////
    call_album=function(){
        setTimeout(function(){
            window.location.href="function|||album";
        },50)
    }
////��ϲ�Ʒ//////
    call_roupedProduct=function(){
        setTimeout(function(){
            window.location.href="function|||roupedProduct";
        },50)
    }
    ////��������//////
    call_preferences=function(){
        setTimeout(function(){
            window.location.href="function|||preferences";
        },50)
    }
    ////�˳���¼//////
    call_logout=function(){
        setTimeout(function(){
            window.location.href="function|||logout";
        },50)
    }
    ////��ת����¼ҳ//////
    call_pushlogin=function(){
        setTimeout(function(){
            window.location.href="function|||pushlogin";
        },50)
    }
    /////����ָ��ҳ��///////
    call_historydel=function(urlstr){
       
            setTimeout(function(){
                window.location.href="function|||historydel|||"+urlstr;
            },100)
    
    };

    /////�������///////
    call_clearCookie=function(callback){
        setTimeout(function(){
            window.location.href="function|||clearCookie|||"+callback;
        },100)

    };
    
    
    alert=function(textstr){
        setTimeout(function(){
                   window.location.href="function|||alertShow|||"+textstr;
                   },50)
    }



    ////////���ذ�ť/////////
    showButton=function(str){
        setTimeout(function(){
            window.location.href="function|||showButton|||"+str;
        },50)
    };

    ////////��ת����Ʒ����ҳ//////////
    gotoProduct=function(id){
        setTimeout(function(){
            window.location.href="function|||gotoProduct|||"+id;
        },50)
    };
	
	///////�ϴ�ͼƬ///////
	uploadimg=function(callback){
		setTimeout(function(){
            window.location.href="function|||uploadimg|||"+callback;
        },50)
	}

    //////����ʱѡ���ַ////////
    selectAddre=function(str){
        setTimeout(function(){
            window.location.href="function|||selectAddre|||"+str;
        },50)
    }
    /////////ѡ���Ż�ȯ/////////////
    selectCoupon=function(str,stra){
        setTimeout(function(){
            window.location.href="function|||selectCoupon|||"+stra;
        },50)
    };
    ////////��ȡ������//////////
    confirm=function(str,callback){
        setTimeout(function(){
            window.location.href="function|||confirm|||"+str+"|||"+callback;
        },50)
    }
    /////��������//////
    PushSet=function(){
        setTimeout(function(){
            window.location.href="function|||PushSet";
        },50)
    };
    ///��url///
    openURL=function(str){
        setTimeout(function(){
            window.location.href="function|||openURL|||"+en(str);
        },50)
    };
    /////////�����������̶�ά��///////
    merchantJSshow=function(spid,str){
        setTimeout(function(){
            window.location.href="function|||merchantJSshow|||"+spid+"|||"+str;
        },50)
    };

    Buyagain=function(json){
        setTimeout(function(){
            window.location.href="function|||Buyagain|||"+json;/////�ٴι���////////
        },50)
    };
    gotoUrlall=function(tp,id){
        setTimeout(function(){
            window.location.href="function|||gotoUrlall|||"+tp+"|||"+id;///��ת���ƶ�webviwҳ��////
        },50)
    };
	adddragupEvent=function(fn){
        setTimeout(function(){
            window.location.href="function|||adddragupEvent";///ע���������¼�////
        },50)
		$upary.push(fn);
    };
	zhaohui=function(){
		setTimeout(function(){
            window.location.href="function|||zhaohui";///�һ�֧������////
        },50)
	};


}
////////��׿js////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function appAndroid(){
    //call_net=window.Android.getcllist;////����//////
    //call_tell=window.Android.tell;////�绰//////
    //call_sms=window.Android.sms;////����//////
    //call_camera=window.Android.camera;////����ͷ//////
    //call_album=window.Android.album;////���//////

    call_net=function(){
        window.location.href="javascript:window.Android.getcllist("+windowHref(arguments)+")";////����//////
    };
    call_tell=function(){
        window.location.href="javascript:window.Android.tell("+windowHref(arguments)+")";////�绰//////
    };
    call_sms=function(){
        window.location.href="javascript:window.Android.sms("+windowHref(arguments)+")";////����//////
    };
    call_camera=function(){
        window.location.href="javascript:window.Android.camera("+windowHref(arguments)+")";////����ͷ//////
    };
    call_album=function(){
        window.location.href="javascript:window.Android.album("+windowHref(arguments)+")";////���//////
    };


    call_roupedProduct=function(){
        window.location.href="javascript:window.Android.roupedProduct()";////��ϲ�Ʒ//////
    };
    call_preferences=function(){
        window.location.href="javascript:window.Android.preferences()";////����ѡ��//////
    };
    call_logout=function(){
        window.location.href="javascript:window.Android.logout()";////�˳���¼//////
    };
    call_pushlogin=function(){
        window.location.href="javascript:window.Android.pushlogin()";////��ת����¼ҳ//////
    };
    /////����ָ��ҳ��///////
    call_historydel=function(urlstr){
        $(function(){
            window.location.href="javascript:window.Android.historydel('"+urlstr+"')";;////����ָ��ҳ��//////
        })
    };


    /////�������///////
    call_clearCookie=function(callback){
        window.location.href="javascript:window.Android.clearCookie('"+callback+"')";;////�������//////
    };

    ////////���ذ�ť/////////
    showButton=function(str){
        setTimeout(function(){
            window.location.href="javascript:window.Android.showButtonA("+JSON.stringify(str)+")";;////�������//////
        },50)
    };

    ////////��ת����Ʒ����ҳ//////////
    gotoProduct=function(id){
        setTimeout(function(){
            window.location.href="javascript:window.Android.gotoProduct('"+id+"')";;////�������//////
        },50)
    };
	
	///////�ϴ�ͼƬ///////
	uploadimg=function(callback){
		setTimeout(function(){
            window.location.href="javascript:window.Android.uploadimg('"+callback+"')";;////�ϴ�ͼƬ//////
        },50)
	}

    //////����ʱѡ���ַ////////
    selectAddre=function(str){
        setTimeout(function(){
            window.location.href="javascript:window.Android.selectAddre('"+str+"')";;////����ʱѡ���ַ//////
        },50)
    }

    /////////ѡ���Ż�ȯ/////////////
    selectCoupon=function(str,stra){
        setTimeout(function(){
            window.location.href="javascript:window.Android.selectCoupon('"+stra+"')";;////ѡ���Ż�ȯ//////
        },50)
    };

    /////////ԭ����������////////////
    call_netYs=function(url,argument,callbackA){
        setTimeout(function(){
            window.location.href="javascript:window.Android.getcllist("+windowHref([url,argument,callbackA])+")";////����//////
        },50)
    }
		
	

    ////////��ȡ������//////////
    confirm=function(str,callback){
        setTimeout(function(){
            window.location.href="javascript:window.Android.confirm("+windowHref([str,callback])+")";
        },50)
    }

    /////��������//////
    PushSet=function(){
        setTimeout(function(){
            window.location.href="javascript:window.Android.PushSet()";
        },50)
    };
    ///��url///
    openURL=function(str){
        setTimeout(function(){
            window.location.href="javascript:window.Android.openURL('"+en(str)+"')";
        },50)
    };

    /////////�����������̶�ά��///////
    merchantJSshow=function(spid,str){
        setTimeout(function(){
            window.location.href="javascript:window.Android.merchantJSshow("+windowHref([spid,str])+")";
        },50)
    };

    Buyagain=function(json){
        setTimeout(function(){
            window.location.href="javascript:window.Android.Buyagain('"+json+"')";
        },50)
    };

    gotoUrlall=function(tp,id){
        setTimeout(function(){
            window.location.href="javascript:window.Android.gotoUrlall("+windowHref([tp,id])+")";///��ת���ƶ�webviwҳ��////
        },50)
    };
	
	adddragupEvent=function(fn){
        setTimeout(function(){
            window.location.href="javascript:window.Android.adddragupEvent()";///��ת���ƶ�webviwҳ��////
        },50)
		$upary.push(fn);
    };
	
	zhaohui=function(){
		setTimeout(function(){
            window.location.href="javascript:window.Android.zhaohui()";///�һ�֧������////
        },50)
	};
	
	 alert=function(textstr){
        setTimeout(function(){
               window.location.href="javascript:window.Android.alert('"+textstr+"')";///�һ�֧������////
          },50)
    }



}







////����//////
call_net=function(url,argument,callbackA){
    function getNet(){
        $.ajax({
            type: "get",
            url: PCajaxurl, // ������ǲ�ͬ�ڵ�ǰ���һ��URL��ַ�����ﵥ����ʾ������ͬ��
            dataType: "jsonp",
            jsonp: "jsonpcallback",  // ָ���ص��������������ֿ���Ϊ����������ϲ���ģ�����callback��������������һ�е�GET����һ��
            data: "url="+url+"&datas="+argument, // jsonpcallback�������jsonpֵһ��
            success: function (json) {
                //clearInterval(setInns);
               // clearTimeout(settimees);
                eval(callbackA)(JSON.stringify(json));
            }
        });
    }
    var settimees=setTimeout(getNet,500);

   // var setInns=setInterval(getNet,50);
};


call_historydel("");////////��շ���url///////////

/////////������ʽ��////////
function windowHref(parm){
    var canshu="";
    for(var i=0;i<parm.length;i++){
        if(canshu!=""){
            canshu+=",";
        }
        canshu+=("\""+parm[i]+"\"");
    }
    return canshu;
}




//////////�������//////////
var adddelayheartbeatTime=setInterval(adddelayheartbeat,120000);
adddelayheartbeat();
function adddelayheartbeat(){
    var tokenhzis=getUrlParamURL("token");
    if(tokenhzis && tokenhzis!=""){
        call_net(hrefurl+"/app/api/userinfo/delayheartbeat",'{"token":"'+tokenhzis+'","ch":"2"}','adddelayheartbeat_hs')
    }else{
        clearInterval(adddelayheartbeatTime);
    }
}
function adddelayheartbeat_hs(data){
    console.log(data);
};


    /*
     HTML5 Shiv v3.7.0 | @afarkas @jdalton @jon_neal @rem | MIT/GPL2 Licensed
     */
(function(l,f){function m(){var a=e.elements;return"string"==typeof a?a.split(" "):a}function i(a){var b=n[a[o]];b||(b={},h++,a[o]=h,n[h]=b);return b}function p(a,b,c){b||(b=f);if(g)return b.createElement(a);c||(c=i(b));b=c.cache[a]?c.cache[a].cloneNode():r.test(a)?(c.cache[a]=c.createElem(a)).cloneNode():c.createElem(a);return b.canHaveChildren&&!s.test(a)?c.frag.appendChild(b):b}function t(a,b){if(!b.cache)b.cache={},b.createElem=a.createElement,b.createFrag=a.createDocumentFragment,b.frag=b.createFrag();
    a.createElement=function(c){return!e.shivMethods?b.createElem(c):p(c,a,b)};a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+m().join().replace(/[\w\-]+/g,function(a){b.createElem(a);b.frag.createElement(a);return'c("'+a+'")'})+");return n}")(e,b.frag)}function q(a){a||(a=f);var b=i(a);if(e.shivCSS&&!j&&!b.hasCSS){var c,d=a;c=d.createElement("p");d=d.getElementsByTagName("head")[0]||d.documentElement;c.innerHTML="x<style>article,aside,dialog,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}template{display:none}</style>";
    c=d.insertBefore(c.lastChild,d.firstChild);b.hasCSS=!!c}g||t(a,b);return a}var k=l.html5||{},s=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,r=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,j,o="_html5shiv",h=0,n={},g;(function(){try{var a=f.createElement("a");a.innerHTML="<xyz></xyz>";j="hidden"in a;var b;if(!(b=1==a.childNodes.length)){f.createElement("a");var c=f.createDocumentFragment();b="undefined"==typeof c.cloneNode||
    "undefined"==typeof c.createDocumentFragment||"undefined"==typeof c.createElement}g=b}catch(d){g=j=!0}})();var e={elements:k.elements||"abbr article aside audio bdi canvas data datalist details dialog figcaption figure footer header hgroup main mark meter nav output progress section summary template time video",version:"3.7.0",shivCSS:!1!==k.shivCSS,supportsUnknownElements:g,shivMethods:!1!==k.shivMethods,type:"default",shivDocument:q,createElement:p,createDocumentFragment:function(a,b){a||(a=f);
    if(g)return a.createDocumentFragment();for(var b=b||i(a),c=b.frag.cloneNode(),d=0,e=m(),h=e.length;d<h;d++)c.createElement(e[d]);return c}};l.html5=e;q(f)})(this,document);


