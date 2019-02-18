/*!art-template - Template Engine | http://aui.github.com/artTemplate/*/
!function () { function a(a) { return a.replace(t, "").replace(u, ",").replace(v, "").replace(w, "").replace(x, "").split(y) } function b(a) { return "'" + a.replace(/('|\\)/g, "\\$1").replace(/\r/g, "\\r").replace(/\n/g, "\\n") + "'" } function c(c, d) { function e(a) { return m += a.split(/\n/).length - 1, k && (a = a.replace(/\s+/g, " ").replace(/<!--[\w\W]*?-->/g, "")), a && (a = s[1] + b(a) + s[2] + "\n"), a } function f(b) { var c = m; if (j ? b = j(b, d) : g && (b = b.replace(/\n/g, function () { return m++, "$line=" + m + ";" })), 0 === b.indexOf("=")) { var e = l && !/^=[=#]/.test(b); if (b = b.replace(/^=[=#]?|[\s;]*$/g, ""), e) { var f = b.replace(/\s*\([^\)]+\)/, ""); n[f] || /^(include|print)$/.test(f) || (b = "$escape(" + b + ")") } else b = "$string(" + b + ")"; b = s[1] + b + s[2] } return g && (b = "$line=" + c + ";" + b), r(a(b), function (a) { if (a && !p[a]) { var b; b = "print" === a ? u : "include" === a ? v : n[a] ? "$utils." + a : o[a] ? "$helpers." + a : "$data." + a, w += a + "=" + b + ",", p[a] = !0 } }), b + "\n" } var g = d.debug, h = d.openTag, i = d.closeTag, j = d.parser, k = d.compress, l = d.escape, m = 1, p = { $data: 1, $filename: 1, $utils: 1, $helpers: 1, $out: 1, $line: 1 }, q = "".trim, s = q ? ["$out='';", "$out+=", ";", "$out"] : ["$out=[];", "$out.push(", ");", "$out.join('')"], t = q ? "$out+=text;return $out;" : "$out.push(text);", u = "function(){var text=''.concat.apply('',arguments);" + t + "}", v = "function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);" + t + "}", w = "'use strict';var $utils=this,$helpers=$utils.$helpers," + (g ? "$line=0," : ""), x = s[0], y = "return new String(" + s[3] + ");"; r(c.split(h), function (a) { a = a.split(i); var b = a[0], c = a[1]; 1 === a.length ? x += e(b) : (x += f(b), c && (x += e(c))) }); var z = w + x + y; g && (z = "try{" + z + "}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:" + b(c) + ".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}"); try { var A = new Function("$data", "$filename", z); return A.prototype = n, A } catch (B) { throw B.temp = "function anonymous($data,$filename) {" + z + "}", B } } var d = function (a, b) { return "string" == typeof b ? q(b, { filename: a }) : g(a, b) }; d.version = "3.0.0", d.config = function (a, b) { e[a] = b }; var e = d.defaults = { openTag: "<%", closeTag: "%>", escape: !0, cache: !0, compress: !1, parser: null }, f = d.cache = {}; d.render = function (a, b) { return q(a, b) }; var g = d.renderFile = function (a, b) { var c = d.get(a) || p({ filename: a, name: "Render Error", message: "Template not found" }); return b ? c(b) : c }; d.get = function (a) { var b; if (f[a]) b = f[a]; else if ("object" == typeof document) { var c = document.getElementById(a); if (c) { var d = (c.value || c.innerHTML).replace(/^\s*|\s*$/g, ""); b = q(d, { filename: a }) } } return b }; var h = function (a, b) { return "string" != typeof a && (b = typeof a, "number" === b ? a += "" : a = "function" === b ? h(a.call(a)) : ""), a }, i = { "<": "&#60;", ">": "&#62;", '"': "&#34;", "'": "&#39;", "&": "&#38;" }, j = function (a) { return i[a] }, k = function (a) { return h(a).replace(/&(?![\w#]+;)|[<>"']/g, j) }, l = Array.isArray || function (a) { return "[object Array]" === {}.toString.call(a) }, m = function (a, b) { var c, d; if (l(a)) for (c = 0, d = a.length; d > c; c++) b.call(a, a[c], c, a); else for (c in a) b.call(a, a[c], c) }, n = d.utils = { $helpers: {}, $include: g, $string: h, $escape: k, $each: m }; d.helper = function (a, b) { o[a] = b }; var o = d.helpers = n.$helpers; d.onerror = function (a) { var b = "Template Error\n\n"; for (var c in a) b += "<" + c + ">\n" + a[c] + "\n\n"; "object" == typeof console && console.error(b) }; var p = function (a) { return d.onerror(a), function () { return "" } }, q = d.compile = function (a, b) { function d(c) { try { return new i(c, h) + "" } catch (d) { return b.debug ? p(d)() : (b.debug = !0, q(a, b)(c)) } } b = b || {}; for (var g in e) void 0 === b[g] && (b[g] = e[g]); var h = b.filename; try { var i = c(a, b) } catch (j) { return j.filename = h || "anonymous", j.name = "Syntax Error", p(j) } return d.prototype = i.prototype, d.toString = function () { return i.toString() }, h && b.cache && (f[h] = d), d }, r = n.$each, s = "break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined", t = /\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g, u = /[^\w$]+/g, v = new RegExp(["\\b" + s.replace(/,/g, "\\b|\\b") + "\\b"].join("|"), "g"), w = /^\d[^,]*|,\d[^,]*/g, x = /^,+|,+$/g, y = /^$|,+/; e.openTag = "{{", e.closeTag = "}}"; var z = function (a, b) { var c = b.split(":"), d = c.shift(), e = c.join(":") || ""; return e && (e = ", " + e), "$helpers." + d + "(" + a + e + ")" }; e.parser = function (a) { a = a.replace(/^\s/, ""); var b = a.split(" "), c = b.shift(), e = b.join(" "); switch (c) { case "if": a = "if(" + e + "){"; break; case "else": b = "if" === b.shift() ? " if(" + b.join(" ") + ")" : "", a = "}else" + b + "{"; break; case "/if": a = "}"; break; case "each": var f = b[0] || "$data", g = b[1] || "as", h = b[2] || "$value", i = b[3] || "$index", j = h + "," + i; "as" !== g && (f = "[]"), a = "$each(" + f + ",function(" + j + "){"; break; case "/each": a = "});"; break; case "echo": a = "print(" + e + ");"; break; case "print": case "include": a = c + "(" + b.join(",") + ");"; break; default: if (/^\s*\|\s*[\w\$]/.test(e)) { var k = !0; 0 === a.indexOf("#") && (a = a.substr(1), k = !1); for (var l = 0, m = a.split("|"), n = m.length, o = m[l++]; n > l; l++) o = z(o, m[l]); a = (k ? "=" : "=#") + o } else a = d.helpers[c] ? "=#" + c + "(" + b.join(",") + ");" : "=" + a } return a }, "function" == typeof define ? define(function () { return d }) : "undefined" != typeof exports ? module.exports = d : this.template = d }();

template.helper('toFixed', function (num, digits) {
    if (num == "" || num == null || num == undefined) {
        return 0.00
    }
    digits = digits || 2;
    if (Number.prototype.toFixed) {
        return parseFloat(num).toFixed(digits);
    }
});

template.helper('toDateDay', function (date, digits) {
    digits = digits || 10;
    if (date.length > digits) {
        date = date.substr(0, digits).toString();
    }
    return date;
});

template.helper('toSplitStr', function (str) {
    if (str == "" || str == null || str == undefined) {
        return "";
    } else {
        return str.split(',')[0].toString();
    }
});

template.helper('toString', function (bool) {
    if (bool) {
        return "True";
    } else {
        return "False";
    }
});
template.helper('toHideStr', function (str) {
    if (str == "" || str == null || str == undefined) {
        return "";
    } else {
        if (str.length > 2) {
            str = str.substr(0, 1) + "**" + str.substr(str.length - 1, 1);
        } else {
            if (str.length == 2) {
                str = str.substr(0, 1) + "**";
            }
        }
        return str;
    }
});

/**
 * 时间转换 wkh
 * 
 * @author wkh
 */
template.helper("toDate",function(date,digits){
	if (date == "null" || date == "undefined" || date == "") {
		return "";
	}
	return new Date(date).pattern("yyyy-MM-dd");  
});

/**
 * 时间转换 
 * @author wkh
 */
template.helper("toDateTime",function(date,digits){
	return new Date(date).pattern("yyyy-MM-dd HH:mm:ss");  
});

template.helper("toDateTimes",function(date,digits){
	return new Date(date).pattern("yyyy-MM-dd");  
});

template.helper("toHideLoongString",function(str){
	 if (str.length > 15) {
         str = str.substr(0,15)+"...";
     } 
     return str;   
});

template.helper("toHideWapLoongString",function(str){
	 if (str.length > 25) {
        str = str.substr(0,25)+"...";
    } 
    return str;   
});
/**
 * 日期转换
 * @author wkh
 * @param fmt
 * @returns
 */
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}


