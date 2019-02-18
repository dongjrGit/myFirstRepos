/*! infoc.js v 0.0.1 */
(function () {
    var browser = 'other',
        system = 'other',
        device = 'PC';

    var pf = navigator.platform,
        ua = navigator.userAgent,
        href = window.location.href;

    // 埋点地址
    var root = '/ards/add?';

    // 开关
    var ON = false;

    // window系统匹配表
    var WinMap = {
        'windows nt 5.0': 'Win2000',
        'windows 2000': 'Win2000',
        'windows nt 5.1': 'WinXP',
        'windows xp': 'WinXP',
        'windows nt 5.2': 'Win2003',
        'windows 2003': 'Win2003',
        'windows nt 6.0': 'WinVista',
        'windows vista': 'WinVista',
        'windows nt 6.1': 'Win7',
        'windows 7': 'Win7',
        'windows nt 6.2': 'Win8',
        'windows 8': 'Win8',
        'windows nt 6.3': 'Win8.1',
        'windows 8.1': 'Win8.1',
        'windows 10': 'Win10',
        'windows nt 10.0': 'Win10'
    };

    var LinuxMap = {
        'android': 'Android',
        'linux': 'Linux'
    };

    // 各系统匹配表
    var sysMap = {
        'win32': WinMap,
        'windows': WinMap,
        'ipad': 'iOS',
        'iphone': 'iOS',
        'macintosh': 'Mac',
        'macIntel': 'Mac',
        'mac': 'Mac',
        'x11': 'Unix',
        'linux': LinuxMap
    };

    // 合并参数
    function buildQuery(obj) {
        var i,
            arr = [];

        if (typeof obj === "object") {
            for (i in obj) {
                if (obj.hasOwnProperty(i)) {
                    arr.push(i + "=" + encodeURIComponent(obj[i] == null ? "" : obj[i] + ""));
                }
            }
        }
        return arr.join("&");
    }
    // 发起请求
    function create(url) {
        var img = new Image(0, 0);

        img.onload = function () {
            img = null;
        };
        img.src = url;
        if (img.complete) {
            img = null;
        }
        //alert(url);
    }
    // 初始化操作
    function initialize() {
        var lua = ua.toLowerCase(),
            lpf = pf.toLowerCase(),
            match, version;

        // 判断操作系统
        for (i in sysMap) {
            if (sysMap.hasOwnProperty(i) && lpf.indexOf(i) > -1) {
                if (typeof sysMap[i] === 'object') {
                    for (j in sysMap[i]) {
                        if (sysMap[i].hasOwnProperty(j) && lua.indexOf(j) > -1) {
                            system = sysMap[i][j];
                            break;
                        }
                    }
                } else {
                    system = sysMap[i];
                }
                break;
            }
        }

        // 判断设备
        if (system === 'Mac') {
            device = 'Mac';
        }
        else if (system === 'iOS') {
            match = /iPad|iPhone/.exec(ua);
            device = match && match[0] || device;
        }
        else if (system === 'Android') {
            device = 'Mobile';
        }

        // 判断浏览器
        match = /(chrome)[ \/]([\w.]+)/.exec(lua) ||
        /(webkit)[ \/]([\w.]+)/.exec(lua) ||
        /ms(ie)\s([\w.]+)/.exec(lua) ||
        /(firefox)[ \/]([\w.]+)/.exec(lua) ||
        [];

        if (match && match[1]) {
            if (match[1] === 'ie') {
                // ie判断版本号
                version = /msie\s([\d\.]+)/.exec(lua);
                browser = match[1] + (version && version[1] ? parseInt(version[1]) : '');
            }
            else if (match[1] === 'webkit') {
                browser = 'Webkit';
                // webkit内核类 主要是移动端
                if (lua.indexOf('safari') > -1 && (system === 'iOS' || system === 'MAC')) {
                    browser = 'Safari';
                }
            }
            else {
                // 默认 首字母大写
                browser = match[1].substr(0, 1).toUpperCase() + match[1].substr(1);
            }
        }
    }
    initialize();

    // 埋点
    function infoc(obj) {
        if (ON === false) {
            return;
        }
        // 公共字段设置
        var isn = false;
        var isnew = infoc.getCookie("IsNewTag");
        if (isnew == undefined || isnew == null || isnew == "") {
            isn = true;
            infoc.setCookie("IsNewTag", 1, 5 * 365 * 24);
        }
        var once = infoc.getCookie("OnceTag");
        if (once == undefined || once == null || once == "") {
            once = Guid.NewGuid().ToString();
            infoc.setCookie("OnceTag", once, 0);
        }
        infoc.addParams({
            once: once,
            isn: isn
        });
        var i;
        if (typeof obj === "object") {
            for (i in infoc.params) {
                if (infoc.params.hasOwnProperty(i)) {
                    if (typeof obj[i] === "undefined") {
                        obj[i] = infoc.params[i];
                    }
                }
            }
        }

        setTimeout(function () {
            create(root + buildQuery(obj));
        }, 1);
    }
    // 开关
    infoc.on = function () {
        ON = true;
        return this;
    };
    infoc.off = function () {
        ON = false;
        return this;
    };
    
    infoc.getDevice = function () {
        return {
            browser: browser,
            system: system,
            device: device
        };
    };

    infoc.addParams = function (obj) {
        if (typeof obj === "object") {
            for (i in obj) {
                if (obj.hasOwnProperty(i)) {
                    if (typeof i === "string" || typeof i === "number") {
                        this.params[i] = obj[i];
                    }
                }
            }
        }
        return this;
    };

    infoc.getURL = function () {
        return href;
    };

    //获得cookie值
    infoc.getCookie = function (objName) {
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == objName) return unescape(temp[1]);
        }
        return "";
    }

    //添加cookie 
    infoc.setCookie = function (objName, objValue, objHours) {
        var str = objName + "=" + escape(objValue);
        if (objHours > 0) {//为0时不设定过期时间，浏览器关闭时cookie自动消失 
            var date = new Date();
            var ms = objHours * 3600 * 1000;
            date.setTime(date.getTime() + ms);
            str += "; expires=" + date.toGMTString();
            str += "; path=/";
        }
        document.cookie = str;
        return this;
    }

    //删除cookie
    infoc.delCookie = function (keyName) {
        var date = new Date();
        date.setTime(date.getTime() - 10000);
        document.cookie = name + "=a; expires=" + date.toGMTString();
        return this;
    }

    //添加埋点
    infoc.add = function(kn,ot,step,type) {
        infoc.on();
        infoc({
            kn: kn,
            ot: ot,
            step: step,
            type: type
        });
        infoc.off();
    }

    //获得参数
    infoc.getQueryStringValue = function (keyName) {
        var searchStr = location.search.substr(1);
        if (searchStr.length == 0)
            return null;
        var collection = searchStr.split('&');
        for (var i = 0; i < collection.length; i++) {
            var tmp = collection[i].split('=');
            if (tmp.length < 2)
                continue;
            if (tmp[0].toUpperCase() == keyName.toUpperCase())
                return tmp[1];
        }
        return null;
    }

    //设置frm
    infoc.setFrm = function (obj) {
        infoc.setCookie("Infoc.Frm", obj, 2);
        return this;
    }

    //得到frm
    infoc.getFrm = function () {
        return infoc.getCookie("Infoc.Frm");
    }

    // 公共参数
    infoc.params = {
        browser: infoc.getDevice().browser,
        sys: infoc.getDevice().system,
        dev: infoc.getDevice().device,
        ws: "PC",
        uid: infoc.getCookie("_uid"),
        un: infoc.getCookie("_un"),
        frm: document.referrer.split('?')[0],
        fa: document.referrer
    };

    window.infoc = infoc;
})();

/*
    kn: "",  //关键字 1
    tn: "",  //表名
    uid: 0,  //用户id 1
    un: "",  //用户名 1
    ip: "",  //访问ip
    frm: "", //来源 1
    ot: "",  //页面或按钮标识 1
    step: 0, //步骤 1
    type: 0, //类型，0页面访问  1按钮点击 1
    browser: infoc.getDevice().browser,   //浏览器及版本信息
    sys: infoc.getDevice().system,        //系统信息
    ws: "",  //站点
    dev: infoc.getDevice().device         //设备信息
*/

/*
infoc({
    click: 11,
    user_id: 0
});
*/
