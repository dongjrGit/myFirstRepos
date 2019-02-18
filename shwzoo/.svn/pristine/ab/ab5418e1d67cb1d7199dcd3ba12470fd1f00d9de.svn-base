//添加银行卡
var ch=3;
var AddBankcard= {
    add: function (openBank,subBank,name,number,phone)
    {
        $.ajax(({
            type:"post",
            url:"/api/wap/userinfo/addBankcard",
            datatype: "json",
            data: {ch:ch,
            	openBank:openBank,
            	subBank:subBank,
            	name:name,
            	number:number,
            	phone:phone},
            success: function (fh)
            {
            	 var card=JSON.parse(fh);
                if (card.code==0) {
                	alert("添加银行卡成功");
                	location.href = "/wap/userinfo/queryBankcard?ch=3";
                }else{
                	alert(card.desc);
                }
               
            },
            error: function (e) {
            	alert(card.desc);
            }
            
        }));
    },
     deletecard:function(id){
    	 $.ajax(({
             type:"post",
             url:"/api/wap/userinfo/deletecard",
             datatype: "json",
             data: {ch:ch,id:id},
             success: function (fh)
             {
             	 var card=JSON.parse(fh);
                 if (card.code==0) {
                 	alert("解绑银行卡成功");
                 	location.href = "/wap/userinfo/queryBankcard?ch=3";
                 }else{
                	 alert(card.desc);
                 }
                
             },
             error: function (e) {
             	alert(card.desc);
             }
             
         }));
   }
}