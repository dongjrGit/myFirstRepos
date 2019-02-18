package com.pushwin.batchwork.quartz;

import java.util.Date;

public class Job {
	public void execute(){
		System.out.println(new Date());
	}
}
