package com.gzt.microCommon.Entity.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息
 *
 * @author csj
 */
public class AdminUser {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String ADMINUSER_SESSION_ID = "ADMINUSER_SESSION_ID";

    public static final int SUPER_ADMIN = 2;
    public static final int SHOP_ADMIN = 1;
    public static final int NORMAL = 0;

    public static Map<Integer, String> ADMIN_MAP = new HashMap<Integer, String>();
    static {
        ADMIN_MAP.put(SUPER_ADMIN, "超级管理员");
        ADMIN_MAP.put(SHOP_ADMIN, "店铺管理员");
        ADMIN_MAP.put(NORMAL, "普通用户");
    }
    private Integer id;

    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 备注
     */
    private String memo;
    /**
     * email
     */
    private String email;
    /**
     * 电话
     */
    private String tel;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 地址
     */
    private String address;
    /**
     * 0-停用 1-启用
     */
    private Boolean status;

    /**
     * 登陆验证问题
     */
    private String loginQuestion;

    /**
     * 登陆验证问题答案
     */
    private String loginAnswer;

    /**
     * 上次登陆时间
     */
    private Date lastLoginTime;

    /**
     * 上次密码错误时间
     */
    private Date lastErrorTime;


    /**
     * 密码连续错误次数
     */
    private Integer errorTimes;

    /**
     * 账号锁定时间
     */
    private Date lockLoginTime;


    /**
     * 账号状态  是否锁定
     */
    private Boolean locked;

    /**
     * 2-超级管理员，1-店铺管理员，0-普通用户
     */
    private Integer isAdmin = 0;

    /**
     * 默认商店
     */
    private Integer defaultStore;

    /**
     * 默认操作店铺
     */
    private Integer optionStore;

//    List<UserRole> userRoleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setMemo(String memo) {
        this.memo = memo;

    }

    public String getMemo() {
        return memo;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setTel(String tel) {
        this.tel = tel;

    }

    public String getTel() {
        return tel;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;

    }

    public String getMobile() {
        return mobile;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public void setStatus(Boolean status) {
        this.status = status;

    }

    public Boolean getStatus() {
        return status;
    }

    public String getLoginQuestion() {
        return loginQuestion;
    }

    public void setLoginQuestion(String loginQuestion) {
        this.loginQuestion = loginQuestion;
    }

    public String getLoginAnswer() {
        return loginAnswer;
    }

    public void setLoginAnswer(String loginAnswer) {
        this.loginAnswer = loginAnswer;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastErrorTime() {
        return lastErrorTime;
    }

    public void setLastErrorTime(Date lastErrorTime) {
        this.lastErrorTime = lastErrorTime;
    }

    public Integer getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }

    public Date getLockLoginTime() {
        return lockLoginTime;
    }

    public void setLockLoginTime(Date lockLoginTime) {
        this.lockLoginTime = lockLoginTime;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getDefaultStore() {
        return defaultStore;
    }

    public void setDefaultStore(Integer defaultStore) {
        this.defaultStore = defaultStore;
    }

//    public List<UserRole> getUserRoleList() {
//        return userRoleList;
//    }
//
//    public void setUserRoleList(List<UserRole> userRoleList) {
//        this.userRoleList = userRoleList;
//    }

    public Integer getOptionStore() {
        return optionStore;
    }

    public void setOptionStore(Integer optionStore) {
        this.optionStore = optionStore.intValue();
    }
}