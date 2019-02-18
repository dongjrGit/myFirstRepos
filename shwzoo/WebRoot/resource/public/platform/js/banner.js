// JavaScript Document


function Switchmenu(obj){
    if(document.getElementById){
        var el = document.getElementById("menu_"+obj);
        var ar = document.getElementById("menu").getElementsByTagName("ul"); 
        if(el.style.display != "block"){ 
            for (var i=0; i<ar.length; i++){
                if (ar[i].className=="submenu")
                    ar[i].style.display = "none";
                document.getElementById("menu"+(i+1)).className="menutbg_1 cursor"
            }
            el.style.display = "block";
            document.getElementById("menu"+obj).className="menutbg_2 cursor"
        }else{
            el.style.display = "none";
            document.getElementById("menu"+obj).className="menutbg_1 cursor"
        }
    }
}


//<!--
var number=6;
function LMYC() {
    var lbmc;
    for (i=1;i<=number;i++) {
        lbmc = eval('LM' + i);
        lbmc.style.display = 'none';
    }
}
 
function ShowFLT(i) {
    lbmc = eval('LM' + i);
    //treePic = eval('treePic' + i)
    if (lbmc.style.display == 'none') {
        LMYC();
        //treePic.src = 'images/nofile.gif';
        lbmc.style.display = '';
    }
    else {
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}
//-->
