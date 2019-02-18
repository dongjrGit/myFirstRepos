package com.yinlian.wssc.platform.view.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.MessagesTypeEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.MemberVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.UserfinanceDto;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/member")
public class MemberController {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private UserslevelService userslevelService;

	@Autowired
	private UsercapitalService usercapitalService;

	@Autowired
	private UserfinanceMapper userfinanceMapper;

	/**
	 * 显示会员列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberList")
	public String showMemberList() {

		return "platform/member/membermanagement_list";
	}

	/**
	 * 显示会员编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberUpdate")
	public String showMemberUpdate(String id, Model model) {

		List<Userslevel> list = null;
		try {
			list = userslevelService.queryAllLevel();
		} catch (Exception e) {
			logger.error("", e);
		}
		model.addAttribute("list", list);

		return "platform/member/member_update";
	}

	/**
	 * 显示会员添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberAdd")
	public String showMemberAdd(String id, Model model) {
		List<Userslevel> list = null;
		try {
			list = userslevelService.queryAllLevel();
		} catch (Exception e) {
			logger.error("", e);
		}
		model.addAttribute("list", list);
		return "platform/member/member_add";
	}

	/**
	 * 显示发送邮件页面
	 * 
	 * @return
	 */
	@RequestMapping("/showSend_email")
	public String showSend_email() {

		return "platform/member/send_email";
	}

	/**
	 * 显示发送手机短信页面
	 * 
	 * @return
	 */
	@RequestMapping("/showSend_MobileMessage")
	public String showSend_MobileMessage() {

		return "platform/member/send_mobilemessage";
	}

	/**
	 * 显示发送站内信页面
	 * 
	 * @return
	 */
	@RequestMapping("/showSend_SystemMessageS")
	public String showSend_SystemMessageS(Model model) {
		List<MemberVo> list = new ArrayList<MemberVo>();
		for (int i = 0; i < MessagesTypeEnum.values().length; i++) {
			MemberVo messagesVo = new MemberVo();
			messagesVo.setCode(MessagesTypeEnum.values()[i].getValue());
			messagesVo.setName(MessagesTypeEnum.values()[i].name());
			list.add(messagesVo);
		}
		model.addAttribute("messageslist", list);
		return "platform/member/send_systemmessage";
	}
	/**
	 * 显示会员充值页面
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/showMember_Recharge") public String
	 * showMember_Recharge(String memberid, Model model){ try { Usercapital
	 * usercapital =
	 * usercapitalService.queryByUserId(StringUtilsEX.ToInt(memberid));
	 * model.addAttribute("usercapital", usercapital); } catch (Exception e) {
	 * logger.error("",e); }
	 * 
	 * return "platform/member/member_recharge"; }
	 */

	/**
	 * 显示密码修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberPwd_update")
	public String showMemberPwd_update() {

		return "platform/member/memberpwd_update";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_Push")
	public String showMember_Push() {

		return "platform/member/member_push";
	}

	/**
	 * 显示支付密码修改页面
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/showMemberPayPwd_update") public String
	 * showMemberPayPwd_update(){
	 * 
	 * return "platform/member/memberpaypwd_update"; }
	 */
	/**
	 * 显示银行卡页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_bankcard")
	public String showMember_bankcard() {

		return "platform/member/member_bankcard";
	}

	/**
	 * 显示会员等级页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_level")
	public String showMember_level() {

		return "platform/member/member_level";
	}

	/**
	 * 获取会员等级编辑列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryMemberLevelById")
	public @ResponseBody ReusltItem queryMemberLevelById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			Userslevel userslevel = userslevelService.queryMemberLevelById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(userslevel);
		} catch (Exception e) {
			logger.error("", e);
		}
		return item;
	}

	/**
	 * 显示会员等级编辑添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/showLevel_updat")
	public String showLevel_updat() {

		return "platform/member/level_update";

	}

	/**
	 * 显示会员收藏商品页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_collect")
	public String showMember_collect() {

		return "platform/member/member_collect";

	}

	/**
	 * 显示会员收藏店铺页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_collectShop")
	public String showMember_collectShop() {

		return "platform/member/member_collect_shop";

	}

	/**
	 * 显示会员浏览记录页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_BrowserHistory")
	public String showMember_BrowserHistory() {
		return "platform/member/member_browserhistory";

	}

	/**
	 * 显示会员浏览记录查看浏览列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMember_BrowserHisDetail")
	public String showMember_BrowserHisDetail() {
		return "platform/member/member_browserhisDetail";

	}

	/**
	 * 显示咨询列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/showGoodConsult_list")
	public String showGoodConsult_list() {
		return "platform/member/goodconsult_list";
	}

	/**
	 * 显示回复咨询的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showReply_goodconsult")
	public String showReply_goodconsult() {

		return "platform/member/reply_goodconsult";

	}

	/**
	 * 显示评论列表的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberComment_list")
	public String showMemberComment_list() {

		return "platform/member/membercomment_list";

	}

	/**
	 * 显示待审核评论列表的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showComment_list")
	public String showComment() {
		return "platform/member/comment_list";
	}

	/**
	 * 显示评论明细的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberCommentDetail")
	public String showMemberCommentDetail(String type, HttpServletRequest request) {
		request.setAttribute("type", type);
		return "platform/member/memberCommentDetail";
	}

	/**
	 * 显示站内的页面
	 * 
	 * @return
	 */
	@RequestMapping("/showp_Message")
	public String showp_Message(Integer userid, HttpServletRequest request) {
		try {
			userid = SessionUtil.getSessionUserId(request);
		} catch (Exception e) {
			logger.error("", e);
		}
		request.setAttribute("userid", userid);
		return "platform/member/p_Message";
	}

	/**
	 * 显示站内消息查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/showReadMessage")
	public String showReadMessage() {

		return "platform/member/readMessage";
	}

	/**
	 * 显示会员反馈页
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberFeedBack_list")
	public String showMemberFeedBack_list() {

		return "platform/member/feedBack_list";

	}

	/**
	 * 显示反馈详情页
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberFeedBack")
	public String showMemberFeedBack() {

		return "platform/member/memberFeedBack";
	}

	/**
	 * 显示会员积分列表
	 * 
	 * @return
	 */
	@RequestMapping("/showMemberPoint_list")
	public String showMemberPoint_list() {

		return "platform/member/points_list";

	}

	/**
	 * 商户消费列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showMemberfinance_list")
	public String showMemberfinance_list() {
		return "platform/member/userfinance_list";
	}

	@RequestMapping("/feedback_list")
	public String feedback_list() {

		return "platform/member/feedBack_list";

	}

	/**
	 * excel导出商户资金记录信息 @param request @param resp @throws
	 */
	@RequestMapping("/exportfinance")
	public void exportfinance(HttpServletRequest request, HttpServletResponse resp, String type, String username,
			String starttime, String endtime, String buyername, String paytype, String number, String paynum) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "会员资金记录列表.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			HSSFSheet sheet = wb.createSheet("会员资金记录列表");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 100);
			sheet.setColumnWidth(1, 50 * 160);
			sheet.setColumnWidth(2, 50 * 100);
			sheet.setColumnWidth(3, 50 * 100);
			sheet.setColumnWidth(4, 50 * 100);
			sheet.setColumnWidth(5, 50 * 100);
			sheet.setColumnWidth(6, 50 * 150);
			sheet.setColumnWidth(7, 50 * 100);
			sheet.setColumnWidth(8, 50 * 200);
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			HSSFRow row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			sheet.createRow((int) 9);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell cell = row.createCell(0);
			cell.setCellValue("用户名 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("用户昵称 ");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("金额类型");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("发生金额");
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("支付类型");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("订单编号 ");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("支付单号 ");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("资金记录日期 ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue("描述 ");
			cell = row.createCell(9);
			cell.setCellStyle(style);

			CriteriaFinance criteria = new CriteriaFinance();
			String typeslist = "";
			typeslist += CapitalChange_Type.支出.getValue() + ",";
			typeslist += CapitalChange_Type.收入.getValue() + ",";
			typeslist += CapitalChange_Type.充值.getValue() + ",";
			typeslist += CapitalChange_Type.退款返还.getValue() + ",";
			typeslist += CapitalChange_Type.后台管理添加.getValue();
			criteria.setTypes(typeslist.trim());
			if (!StringUtilsEX.IsNullOrWhiteSpace(buyername)) {
				criteria.setBuyername(buyername);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(paytype)) {
				paytype = paytype.substring(0, paytype.length() - 1);
				criteria.setPaytypes(paytype);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(number)) {
				criteria.setNumber(number);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(paynum)) {
				criteria.setPaynum(paynum);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(username)) {
				criteria.setUsername(username);
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				criteria.setType(StringUtilsEX.ToInt(type));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(starttime)) {
				criteria.setStart(StringUtilsEX.ToShortDate(starttime));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endtime)) {
				criteria.setEnd(StringUtilsEX.ToShortDate(endtime));
			}
			criteria.setSort("desc");
			criteria.setOrderByClause("createtime");

			List<UserfinanceDto> malist = userfinanceMapper.selectPlatformList(criteria);

			for (int i = 0; i < malist.size(); i++) {
				HSSFRow row1 = sheet.createRow((int) i + 1);
				UserfinanceDto ma = malist.get(i);
				row1.createCell(0).setCellValue(ma.getUsername()); //
				row1.createCell(1).setCellValue(ma.getBuyername()); //
				if (ma.getType() == CapitalChange_Type.余额转入保证金.getValue()) {
					row1.createCell(2).setCellValue("余额转入保证金"); //
				}
				if (ma.getType() == CapitalChange_Type.保证金充值.getValue()) {
					row1.createCell(2).setCellValue("保证金充值"); //
				}
				if (ma.getType() == CapitalChange_Type.充值.getValue()) {
					row1.createCell(2).setCellValue("充值"); //
				}
				if (ma.getType() == CapitalChange_Type.冻结.getValue()) {
					row1.createCell(2).setCellValue("冻结"); //
				}
				if (ma.getType() == CapitalChange_Type.冻结金额增加.getValue()) {
					row1.createCell(2).setCellValue("冻结金额增加"); //
				}
				if (ma.getType() == CapitalChange_Type.冻结金额扣除.getValue()) {
					row1.createCell(2).setCellValue("冻结金额扣除"); //
				}
				if (ma.getType() == CapitalChange_Type.后台管理添加.getValue()) {
					row1.createCell(2).setCellValue("后台管理添加"); //
				}
				// if (ma.getType() ==CapitalChange_Type.提现.getValue()) {
				// row1.createCell(2).setCellValue("提现"); //
				// }
				if (ma.getType() == CapitalChange_Type.支出.getValue()) {
					row1.createCell(2).setCellValue("支出"); //
				}
				if (ma.getType() == CapitalChange_Type.收入.getValue()) {
					row1.createCell(2).setCellValue("收入"); //
				}
				if (ma.getType() == CapitalChange_Type.解冻.getValue()) {
					row1.createCell(2).setCellValue("解冻"); //
				}
				if (ma.getType() == CapitalChange_Type.退款扣除.getValue()) {
					row1.createCell(2).setCellValue("退款扣除"); //
				}
				if (ma.getType() == CapitalChange_Type.退款返还.getValue()) {
					row1.createCell(2).setCellValue("退款返还"); //
				}
				row1.createCell(3).setCellValue(String.valueOf(ma.getMoney())); //
				if (ma.getPaytype() == null) {
					row1.createCell(4).setCellValue(""); //
				} else {
					if (ma.getPaytype() == PayTypeEnum.微信支付.getValue()) {
						row1.createCell(4).setCellValue("微信支付"); //
					}
					if (ma.getPaytype() == PayTypeEnum.支付宝支付.getValue()) {
						row1.createCell(4).setCellValue("支付宝支付"); //
					}
				}
				// row1.createCell(4).setCellValue(ma.getAreaname()); //

				row1.createCell(5).setCellValue(ma.getNumber()); //
				row1.createCell(6).setCellValue(ma.getPaynum()); //
				row1.createCell(7).setCellValue(ma.getCreattimestr()); //
				row1.createCell(8).setCellValue(ma.getDescription()); //
			}

			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.PlatformMemberManagement, "导出商户资金记录信息异常! 异常信息:{0}", e,
					"/platform/member/exportfinance");
		}
	}

}
