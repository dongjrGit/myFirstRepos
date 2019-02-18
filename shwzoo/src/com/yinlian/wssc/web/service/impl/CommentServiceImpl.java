package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.GroupOrderEnum;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.api.app.dto.Api_CommentDto;
import com.yinlian.api.app.dto.CommentBaseDto;
import com.yinlian.api.app.dto.CommentProDetailListDto;
import com.yinlian.wssc.platform.controller.MemberCommentController;
import com.yinlian.wssc.search.Api_CommentCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.dto.MemberCommentDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CommentMapper;
import com.yinlian.wssc.web.mapper.CommentreplyMapper;
import com.yinlian.wssc.web.mapper.CommentshowimgMapper;
import com.yinlian.wssc.web.mapper.ImagesMapper;
import com.yinlian.wssc.web.mapper.OrderdetailMapper;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.mapper.SatisfactionMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.CommentAbr;
import com.yinlian.wssc.web.po.Commentreply;
import com.yinlian.wssc.web.po.Commentshowimg;
import com.yinlian.wssc.web.po.CommentshowimgExample;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 
 * @author Administrator
 *
 */
@Component("/commentService")
public class CommentServiceImpl implements CommentService {
	/**
	 * 输出日志的控制类
	 */
	private static final Logger logger = LoggerFactory.getLogger(MemberCommentController.class);
	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private SpuMapper spuMapper;
	
	@Autowired
	private  OrderdetailMapper  orderdetailMapper;
	
	@Autowired
	private  OrdersMapper   ordersMapper;
	
	@Autowired
	private  SatisfactionMapper  satisfactionMapper;
	
	@Autowired
	private CommentreplyMapper commentreplyMapper;
	@Autowired
	private CommentshowimgMapper commentshowimgMapper;
	@Autowired
	private GroupBuyOrderService     groupBuyOrderService;
	
	@Autowired
	private  ImagesMapper    imagesMapper;
	
	/**
	 * 分页查询会员评论列表
	 * 
	 * @see com.yinlian.wssc.web.service.CommentService#queryMemberCommentByCriteria(com.yinlian.wssc.web.util.CriteriaMemberComment,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryMemberCommentByCriteria(Criteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<MemberCommentDto> beanList = commentMapper.selectMemberCommentByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	 
	/**
	 * 根据id查询评论明细
	 * @see com.yinlian.wssc.web.service.CommentService#queryById(java.lang.Integer)
	 */
	@Override

	public MemberCommentDto queryById(Integer id) throws Exception {
		List<Images> images = new ArrayList<Images>();
		images = imagesMapper.getImages(id, ImageTypeEnum.买家评价图片.getValue());
		List<Commentreply> replylist = new ArrayList<Commentreply>();
		replylist = seletReply(id);
		MemberCommentDto dato = commentMapper.selectById(id);		
		dato.setImglist(images);
		dato.setReplylist(replylist);
		return dato;
	}
	/**
	 * 递归
	 * @param id
	 * @return
	 */
	public  List<Commentreply> seletReply(Integer id){
		List<Commentreply> replylist = new ArrayList<Commentreply>();
		replylist = commentreplyMapper.selectByParentId(id);
		if (replylist != null && replylist.size() > 0) {
			for (Commentreply commentreply : replylist) {
				if (replylist != null) {
					int parentid = commentreply.getId();
					List<Commentreply> list2 = seletReply(parentid);
					commentreply.setChildren(list2);
				}
				
			}
		}
	
		return replylist;
	}

	/**
	 * 根据id查询评论信息
	 * @see com.yinlian.wssc.web.service.CommentService#queryCommentById(java.lang.Integer)
	 */
	@Override
	public Comment queryCommentById(Integer id) throws Exception {
		
		return commentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更改一条评论信息
	 * @see com.yinlian.wssc.web.service.CommentService#updateCommentById(com.yinlian.wssc.web.po.Comment)
	 */
	@Override
	public int updateCommentById(Comment comment) throws Exception {
		
		return commentMapper.updateByPrimaryKey(comment);
	}
	@Override
	public int insertComm(Comment comment, Integer spuid) throws Exception {
		
		Spu spu=spuMapper.selectByPrimaryKey(spuid);
		if(spu!=null){
			Integer commentCount=spu.getCommentcount();
			if(commentCount==null){
				spu.setCommentcount(1);
			}else{
				spu.setCommentcount(commentCount+1);
			}
		}
		int j = spuMapper.updateByPrimaryKey(spu);
		int i = commentMapper.insertSelective(comment);
		return comment.getId();
	}

	@Override
	public int insertComm(Comment comment) throws Exception {
		Integer id=comment.getFkid();
		Groupbuyorder  order=groupBuyOrderService.selectByPrimaryKey(id);
		if(order!=null){
			order.setStatus(GroupOrderEnum.已评价.getValue());
			groupBuyOrderService.updateByPrimaryKeySelective(order);
		}
		int i=commentMapper.insertSelective(comment);
		return comment.getId();
	}
	
	@Override
	public int insertUserComm(Comment comment) throws Exception {
	
		return commentMapper.insertSelective(comment);
	}


	@Override
	public PageBean getProductDetailedCommentList(Api_CommentCriteria criteria, Integer pindex, Integer psize)
			throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil();
		PageBean pageBean = new PageBean();
		pageBean.setPc(pindex);
		pageBean.setPs(psize);
		pageBeanUtil.setPage(pageBean);
		pageBeanUtil.setCriteria(criteria);
		CommentProDetailListDto dto = new CommentProDetailListDto();
		if (pindex <= 1) {
			CommentAbr abr = commentMapper.getCommentAbr(criteria.getSpuid());
			dto.setBad(abr.getBad());
			dto.setGood(abr.getGood());
			dto.setMr(abr.getMr());
		}
		dto.setList(commentMapper.getApiCmtListPage(pageBeanUtil));
		pageBean.setData(dto);
		return pageBean;
	}
	/*
	 * 获取评论列表（wap）
	 * 
	 * 
	 * 
	 */
	@Override
	public PageBean getProductDetailedCommentList(Wap_CommentCriteria criteria, Integer pindex, Integer psize)
			throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil();
		PageBean pageBean = new PageBean();
		pageBean.setPc(pindex);
		pageBean.setPs(psize);
		pageBeanUtil.setPage(pageBean);
		pageBeanUtil.setCriteria(criteria);
		CommentProDetailListDto dto = new CommentProDetailListDto();
		if (pindex <= 1) {
			CommentAbr abr = commentMapper.getCommentAbr(criteria.getSpuid());
			dto.setBad(abr.getBad());
			dto.setGood(abr.getGood());
			dto.setMr(abr.getMr());
			
		}
		List<CommentBaseDto> comms=commentMapper.getApiCmtListPage(pageBeanUtil);
		List<CommentBaseDto> commsetnames=new ArrayList<CommentBaseDto>();
		for (CommentBaseDto coDto : comms) {
			String na=coDto.getUsername();
			if(na.length()>=3)
			na=na.substring(0, 1)+"**"+na.substring(na.length()-1, na.length());
			coDto.setUsername(na);
			commsetnames.add(coDto);
		}
		
		dto.setList(commsetnames);
		pageBean.setData(dto);
		return pageBean;
	}
	
	@Override
	public Comment selectByOrderId(Integer orderid) throws Exception {
		
		return commentMapper.selectByOrderId(orderid);
	}


	@Override
	public int insertOrderComm(List<Api_CommentDto> commentlist,
			Integer orderid, Integer gooddescription, Integer sellerattitude,
			Integer logisticsspeed) throws Exception {
		int userid=0,shopid=0;
		for (Api_CommentDto commentDto : commentlist) {
			
			Comment comment=new Comment();
			comment.setTitle(commentDto.getTitle());
			comment.setContent(commentDto.getContent());
			comment.setOrderdetailid(commentDto.getOrderdetailid());
			comment.setBuyerid(commentDto.getBuyerid());
			comment.setShopid(commentDto.getShopid());
			comment.setType(commentDto.getType());
			comment.setShowname(commentDto.getShowname());
			comment.setSpuid(commentDto.getSpuid());
			comment.setStar(commentDto.getStar());
			comment.setDate(new Date());
			comment.setReplycount(0);
			comment.setVaildflag(0);
			comment.setStarDepict(0);
			comment.setStarService(0);
			comment.setStarSpeed(0);
			
			commentMapper.insertSelective(comment);
			String imgs=commentDto.getImgs();
			if(!StringUtilsEX.IsNullOrWhiteSpace(imgs)){
				List<Images> imglist = new ArrayList<Images>();
					Images images = null;
					for (String img : imgs.split(",")) {
						images = new Images();
						images.setCreatetime(new Date());
						images.setFkid(comment.getId());
						images.setStatus(0);
						images.setSort(0);
						images.setImgurl(img);
						images.setType(ImageTypeEnum.买家评价图片.getValue());
						imglist.add(images);
					}
					imagesMapper.insertList(imglist);
			}
			
			
			Satisfaction satisfaction = new Satisfaction();
			satisfaction.setBuyerid(userid);
			satisfaction.setShopid(shopid);
			satisfaction.setOrderdetailid(commentDto.getOrderdetailid());
			satisfaction.setLogisticsspeed(logisticsspeed);
			satisfaction.setGooddescription(gooddescription);
			satisfaction.setSellerattitude(sellerattitude);
			satisfaction.setStaffattitude(0);
			satisfaction.setVaildflag(0);// 默认0 不删除 1 删除
			satisfactionMapper.insert(satisfaction);
			
			userid=commentDto.getBuyerid();
			shopid=commentDto.getShopid();
			Spu spu = spuMapper.selectByPrimaryKey(commentDto.getSpuid());
			if (spu != null) {
				Integer commentCount = spu.getCommentcount();
				if (commentCount == null) {
					spu.setCommentcount(1);
				} else {
					spu.setCommentcount(commentCount + 1);
				}
			}
			spuMapper.updateByPrimaryKey(spu);
			Orderdetail orderdetail = orderdetailMapper
					.selectByPrimaryKey(commentDto.getOrderdetailid());
			if (orderdetail != null) {
				orderdetail.setIscomment(1);
				orderdetailMapper.updateByPrimaryKey(orderdetail);
				
			}
			else{
				return 0;
			}
		}

		Orders order=ordersMapper.selectByPrimaryKey(orderid);
		if(order!=null){
			order.setIscomment(1);
			ordersMapper.updateByPrimaryKey(order);
		}
		else{
			return 0;
		}
		return 1;
	}


	@Override
	public PageBean getCommentBySpuId(Api_CommentCriteria criteria, Integer pageindex, Integer pagesize)
			throws Exception {
		PageBeanUtil beanUtil = new PageBeanUtil(criteria, pageindex, pagesize);
		PageBean pageBean = beanUtil.getPage();
		List<CommentBaseDto> comments = commentMapper.getComBySpuIdByPage(beanUtil);
		List<Images> images = new ArrayList<Images>();
		for(CommentBaseDto dto : comments){
			images = imagesMapper.getImages(dto.getId(), ImageTypeEnum.买家评价图片.getValue());
			dto.setShowImgList(images);
		}
		pageBean.setBeanList(comments);
		return pageBean;
	}
}
