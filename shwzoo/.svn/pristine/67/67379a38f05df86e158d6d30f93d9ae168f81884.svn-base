var accessid = '';
var accesskey = '';
var host = '';
var policyBase64 = '';
var signature = '';
var key = '';
var expire = 0;
var fileName = '';
var now = timestamp = Date.parse(new Date()) / 1000;
var relationtype = '';
var uploadUrl = '';
var params;
var uploadZip = {
    upload: function(options) {
    	params = options;
    	relationtype = options.relationtype;
        get_signature();
        var file = document.getElementById(options.fileElementId).files[0];
        suffix = get_suffix(file.name);
        var lowerSuffix = suffix.toLowerCase();
        if (".zip" != lowerSuffix) {
            var object = {
                "code": 0,
                "data": [],
                "desc": "",
                "startTime": "",
                "token": ""
            };
            object.code = -101;
            object.desc = '文件格式不正确！';
            params.success(object);
            if (params.complete) {
                params.complete();
            }
            return;
        }
        var fd = new FormData();
        fd.append('key', key + fileName + suffix);
        // fd.append('Content-Type', file.type);
        fd.append('OSSAccessKeyId', accessid);
        fd.append('policy', policyBase64);
        fd.append('signature', signature);
        fd.append("file", file);
        fd.append("success_action_status", '200');
        var xhr = createXmlHttpRequest();
        // xhr.upload.addEventListener("progress", uploadProgress,
        // false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open('POST', uploadUrl, true); // MUST BE LAST LINE BEFORE YOU SEND
        xhr.send(fd);

    }
}

function get_signature() {
    // 可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.30s 做为缓冲
    now = timestamp = Date.parse(new Date()) / 1000;
    fileName = new Date().getTime() + random_string(4);
    if (expire < now + 30) {
        body = send_request();
        var obj = eval("(" + body + ")");
        host = obj['host'];
        policyBase64 = obj['policy'];
        accessid = obj['accessid'];
        signature = obj['signature'];
        expire = parseInt(obj['expire']);
        key = obj['dir'];
        uploadUrl = obj['uploadUrl'];
        return true;
    }
    return false;
}

function send_request() {
    var xmlhttp = null;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlhttp != null) {
        serverUrl = '/zoo/image/getSignature';
        xmlhttp.open("GET", serverUrl + "?type=" + relationtype, false);
        xmlhttp.send(null);
        return xmlhttp.responseText
    } else {
        alert("Your browser does not support XMLHTTP.");
    }
}

function random_string(len) {
    len = len || 4;
    var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function get_suffix(name) {
    pos = name.lastIndexOf('.');
    suffix = '';
    if (pos != -1) {
        suffix = name.substring(pos);
    }
    return suffix;
}
function createXmlHttpRequest() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function uploadComplete(evt) {
    if (evt.target.status == 200) {
        var object = {
            "code": 0,
            "data": [],
            "desc": "",
            "startTime": "",
            "token": "",
            "ossObject": ""
        };
        object.ossObject = key + fileName + suffix;
        object.data.push(host + "/" + key + fileName + suffix);
        params.success(object);
        if (params.complete) {
            params.complete();
        }
    }
}
function uploadFailed(evt) {
    alert("There was an error attempting to upload the file." + evt);
    params.complete();
}
function uploadCanceled(evt) {
    alert("The upload has been canceled by the user or the browser dropped the connection.");
}