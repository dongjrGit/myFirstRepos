<%@page contentType="text/html; charset=utf-8" language="java"%>

<%	
	//设置请求字符集 避免产生乱码	
	request.setCharacterEncoding("utf-8");
%>


		<%
			//测试商户号			
			String pMerCode=request.getParameter("pMerCode");
			String pBillNo=request.getParameter("pBillNo");
			//int pAmount= (Integer.valueOf(request.getParameter("pAmount"))).intValue();
			String pAmount= request.getParameter("pAmount");

			Double pAmount1= Double.valueOf(pAmount);
			String pDate=request.getParameter("pDate");
			String pSucc=request.getParameter("pSucc");
			String pMsg=request.getParameter("pMsg");
			String pRetEncodeType=request.getParameter("pRetEncodeType");
			String pAttach=request.getParameter("pAttach");
			String pCurrency=request.getParameter("pCurrency");
			String pSignature=request.getParameter("pSignature");
			String pIpsBankTime=request.getParameter("pIpsBankTime");
			String pIpsBillNo=request.getParameter("pIpsBillNo");
			
			//下面是分帐才有的参数
			String pRuleType=request.getParameter("pRuleType");
			String pAmtType=request.getParameter("pAmtType");
			String pFeeType=request.getParameter("pFeeType");
			String pFeeBase=request.getParameter("pFeeBase");
			String pInstruction=request.getParameter("pInstruction");
			String pWhoCall=request.getParameter("pWhoCall");
		    String pRuleDetails=request.getParameter("pRuleDetails");
			String pSignMD5=request.getParameter("pSignMD5");
			String pSys=request.getParameter("pSys");
			
			//测试商户MD5证书
   		String md5Cert="loykhyDlfi8rlWB5CT5UUuwsi8puD9Aykru5MCok438CHS4DjiioZDnQSgb9jt858jhTCgJaEuz1LJBqeCk40vz6d3CFAFbkUcd7KAadyiAFnMei4L56idemKBQj3dzZ";
   		   		
				
			//验证结果
			String result="null";			
						
			 //**************************************************
			//为了保证交易数据的正确，建议采用md5withrsa的验证方式								
			//**************************************************			
	 if (pSucc.equalsIgnoreCase("Y"))
	 {			
		//金额验证成功	
			//mdtwithRsa
			if (pSys==null)
			{
			  if(pRetEncodeType==null)
			  {
			  }
			  else
			  {
				if (pRetEncodeType.equalsIgnoreCase("11"))
				{
				
					//MD5WithRSA验证 明文=订单编号+订单金额+订单日期+成功标志+IPS订单编号+币种
					String md5withrsaSource=pBillNo + pAmount + pDate + pSucc + pIpsBillNo + pCurrency;								
					
					cryptix.jce.provider.MD5WithRSA md5rsa=new cryptix.jce.provider.MD5WithRSA();
					
					//使用RSA密钥绝对路径
					md5rsa.verifysignature(md5withrsaSource,pSignature,request.getRealPath("/") + "md5withrsa_public_key.txt");					
					
					//错误代码定义
					//-99 未处理
					//-1 公钥路径错
					//-2 公钥路径为空
					//-3 读取公钥失败
					//-4 验证失败，格式错误
					//1： 验证失败
					//0: 成功
					if (md5rsa.getresult()==0)
					{
						result="验证签名成功";

					}
					else
					{
						result="验证签名失败";						
					
					}
				}
				else if (pRetEncodeType.equalsIgnoreCase("12"))
				{
				
					//MD5验证 明文=订单编号+订单金额+订单日期+成功标志+IPS订单编号+币种+MD5证书
					String md5withrsaSource=pBillNo + pAmount + pDate + pSucc + pIpsBillNo + pCurrency + md5Cert;
			
				/* 	if (pSignature.equalsIgnoreCase(IPSCrypto.Security.MD5(md5withrsaSource)))
					{
						result="验证签名成功";
					
					
					}
					else
					{
						result="验证签名失败";
						
					} */
				}
			}
			}
		else if (pSys.equalsIgnoreCase("DistributionSystem"))
		{  
				//out.write("pRetEncodeType的值"+pRetEncodeType);
			    //分帐返回
					 
			if (pSignMD5!=null)
			     {
			
		      //验证明文
			String MD5Source="";	
		
					//MD5验证 明文=订单编号+订单金额+订单日期+成功标志+IPS订单编号+币种+MD5证书
	           MD5Source=pBillNo+pAmount+pDate+pMerCode+pRuleType+pAmtType+pFeeType+pFeeBase+pInstruction+pWhoCall+ java.net.URLEncoder.encode(pRuleDetails).toLowerCase()+pSucc+md5Cert;
			
			
					/* if (pSignMD5.equalsIgnoreCase(IPSCrypto.Security.MD5(MD5Source)))
					
					{
				
						result="验证签名成功";
					   
					}
					else
					{
					
						result="验证签名失败";
						 
					} */
			}
		
		
		}
		}
		else
		{
		   out.write("交易证失败");
		   return;
		}

			if 	(result!=null)
			{  
			    if (result=="验证签名成功")
				{
				    int money=3000;   //此处为测试的提交金额,请用户替换成真实交易时订单提交金额
					 //建议以分为最小单位
				    double	pAmount2=pAmount1*100;
					int pAmount3 =(int)pAmount2;
					
					if (money==pAmount3)
					{
					  out.write("成功");
					} 
					else
					{
					   out.write("金额验证失败");
					}
				    
				}
				else
				{
				    //签名验证失败
				     
					  out.write("签名验证失败");
				}
			}	
			
%>
	