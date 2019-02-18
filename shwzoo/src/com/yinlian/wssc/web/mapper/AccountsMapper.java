package com.yinlian.wssc.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface AccountsMapper {
    int countByExample(AccountsExample example);

    int deleteByExample(AccountsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Accounts record);

    int insertSelective(Accounts record);

    List<Accounts> selectByExample(AccountsExample example);

    List<Accounts> queryLogin(Map<String, Object> map) throws Exception;

    Accounts selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Accounts record,
                                 @Param("example") AccountsExample example);

    int updateByExample(@Param("record") Accounts record, @Param("example") AccountsExample example);

    int updateByPrimaryKeySelective(Accounts record);

    int updateByPrimaryKey(Accounts record);

    Accounts selectByUser(CriteriaAccounts cAccounts) throws Exception;

    Accounts getByRoleID(Integer roleid) throws Exception;

    int updatePassword(Accounts record) throws Exception;

    Accounts Login(String name, String pwd) throws Exception;

    void updPwdByPhone(String name, String pwd) throws Exception;

    void updPwd(Integer userid, String pwd) throws Exception;

    int isExistAcc(CriteriaAccounts cAccounts) throws Exception;
    int isExistAccByPhone(CriteriaAccounts cAccounts) throws Exception;

    /**
     * 分页查询会员信息
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
    List<UsersInfoDto> selectMemberByPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 会员信息绑定
     * @param memberid
     * @return
     */
    UsersInfoDto selectMemberById(Integer memberid);

    /**
     * 根据登录名个密码查询accounts
     * @param map
     */
    Accounts queryByUserNameAndPassword(Map<String, String> map) throws Exception;

    /**
     * 批量添加站内信
     * 
     * @param list
     * @return
     */
    int insertBacth(List<Messages> list)throws Exception;
    
    Accounts getSuperadmin()throws Exception;

	Accounts toLogin(String name, String passwordEncrypt, Integer usertype);
	
	/**
	 * 单点登录
	 * @param username
	 * @return
	 */
	List<Accounts> selectByUsername( Map<String, Object> map);

	Accounts findAccount(String mobile, int value) throws Exception;

	List<Accounts> selectByLikeName(Map<String, Object> map) throws Exception;
	
	List<Accounts> selectname(String name) throws Exception;

	List<Accounts> selectAccounts(Platfrom_SYCriteria criteria) throws Exception;

	Accounts queryAccounts(String accName,String userType) throws Exception;

	Accounts querySeller(String accName) throws Exception;
	
	/**
	 * 根据userid查询登录密码
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	String getPwdByUserid(Integer userid,Integer usertype)throws Exception;

	Integer queryAccountsCount(Platfrom_SYCriteria criteria) throws Exception;

	int deleteByUserid(Integer userid) throws Exception;

}