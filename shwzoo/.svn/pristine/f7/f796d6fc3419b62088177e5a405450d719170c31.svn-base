package com.yinlian.wssc.web.task;

import java.util.TimerTask;

import org.springframework.web.context.WebApplicationContext;

import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.service.AccountsService;
import com.yl.soft.log.LogHandle;

public class AddMemberTask extends TimerTask {
	
	private AccountsService accountsService;
	
	private WebApplicationContext     context = null;
	  
	public AddMemberTask(WebApplicationContext context) {
        this.context = context;
	}
	@Override
	public void run() {
		accountsService=(AccountsService)context.getBean("accountsService");
		try{
		for (Integer i=1;i<21000;i++) {
			int no=200+i;
			String username=String.format("%07d", no);
			String mobile="158552"+String.format("%05d", i);
			accountsService.addMenber(UserTypeEnum.Buyer.getValue(), "", "1" + username, "", "",
			        "123456", mobile,"", 0,"","", "", "", "", "", "", "", "", "", "", "");
		}
		}catch(Exception e){
			e.printStackTrace();
			LogHandle.error(LogType.Businessbills, "生成用户信息错误：",e,"/task/addMemberTask");
		}
	}
}