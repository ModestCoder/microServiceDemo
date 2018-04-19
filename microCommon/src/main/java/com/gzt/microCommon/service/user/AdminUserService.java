package com.gzt.microCommon.service.user;

import com.gzt.microCommon.Entity.user.AdminUser;
import org.springframework.stereotype.Service;

/**
 * 功能描述：用户信息
 * 
 * @author csj
 */
@Service
public interface AdminUserService {
	/**
	 * 按店铺查询
	 * @param storeId
	 * @return
	 */
//	public List<AdminUser> findByStoreId(Integer storeId);
	
	/**
	 * 按用户组查询
	 * @param roleId
	 * @return
	 */
//	public Result<AdminUser> findByRoleId(Pager pager, Integer roleId);

	/**
	 * 保存用户信息
	 * @param service
	 * @param role
	 * @return
	 */
//	public boolean saveOrUpdate(AdminUser service, Integer[] role, Integer[] selectMultiple);

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public boolean saveOrUpdate(AdminUser user);
	
	/**
	 * 改变状态
	 * @param id
	 * @return
	 */
//	public boolean changeStatus(Integer id, Integer storeId);

	/**
	 * 按用户名和密码查询
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser find(String username, String password);
	
	/**
	 * 保存用户基本信息 , 包括 邮件 ,地址, 电话 , 手机
	 * @param service
	 * @return
	 */
//	public boolean saveInfo(AdminUser service);
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return 0-旧密码不匹配 , 1-保存成功,2-保存失败
	 */
//	public int savePassword(Integer userId, String oldPassword, String newPassword);
	
	/**
	 * 用户名是否存在
	 */
//	public boolean userExists(String userName);
	
	/**
	 * 用户登陆状态是否被锁
	 */
//	public boolean userLock(String userName);
	
	/**
	 * 锁定用户登陆状态
	 */
//	public void setUserLock(String username);

	
	/**
	 * 获取用户名绑定 的手机号码
	 */
//	public String getMobile(String username);
	

	
	/**
	 * 改变用户账号锁定状态
	 */
//	public boolean changeLock(Integer id, Integer storeId);

	/**
	 * 查询
	 * @param pager
	 * @return
	 */
//	Result<AdminUser> findByPager(Pager pager);


//	CoocaaResponse<?> changePasswordByUserId(Integer userId, String password);
}
