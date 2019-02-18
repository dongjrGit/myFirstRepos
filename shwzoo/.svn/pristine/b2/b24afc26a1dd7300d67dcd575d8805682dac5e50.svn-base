package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.UserDto;
import com.yinlian.wssc.web.dto.Feature;
import com.yinlian.wssc.web.dto.MemberInfo;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.UsersExample;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Users record) throws Exception;

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example) throws Exception;

    Users selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Users record,
                                 @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record) throws Exception;

    Users selectId(String name, String pwd) throws Exception;

    List<Users> findInfo(String mobile) throws Exception;

    void updatePwd(Integer id, String pwd) throws Exception;

    List<Users> selectShow() throws Exception;

    List<Users> selectByNamaPwd(String name, String pwd) throws Exception;

    /**
     * 
     * @param feature
     * @return
     */
    List<Users> selectByFeature(Feature feature) throws Exception;

    /**
     * 返回插入时的自增主键
     * @param users
     * @return
     */
    int insertUsers(Users users) throws Exception;

    int updateByPhone(String phone, String password) throws Exception;

    Users selectByName(CriteriaAccounts cAccounts) throws Exception;

    /**
     * 分页查询会员信息 
     * 
     * @param pageBeanUtil 
     * @return
     */
    List<MemberInfo> selectMemberListByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    Users getRowLockById(int userid) throws Exception;

	int updatePayPwd(Integer id, String repwd);
	
	/**
	 * 根据用户名查询用户信息
	 * @param name
	 * @return
	 */
	List<Users> findInfoByname(String username)  throws Exception;
	
	int updateuserlevel(Users users) throws Exception;
	
	int updateConsumeAmount(Integer amount, Integer id)throws Exception;
	
	int updPayPwdByPhone(String phone, String password)throws Exception;

	/**
	 * 手机号查询用户
	 * @param phone 电话
	 * @param channelType 通道
	 * @return
	 */
	List<UserDto> selectByPhone(@Param("phone")String phone,@Param("channelType")int channelType);

	Users getByOrderCode(String number)throws Exception;

	List<Users> getUsersListByIds(@Param("ids")String ids)throws Exception;

	String getNameByID(Integer userid)throws Exception;
}