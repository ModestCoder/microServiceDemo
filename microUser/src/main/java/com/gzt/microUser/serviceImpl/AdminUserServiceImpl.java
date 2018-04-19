package com.gzt.microUser.serviceImpl;

import com.gzt.microUser.mapper.AdminUserMapper;
import com.gzt.microUser.mapper.UserRoleMapper;
import com.gzt.microUser.mapper.UserStoreMapper;
import com.microCommon.Entity.user.AdminUser;
import com.microCommon.service.user.AdminUserService;
import com.microCommon.service.user.UserRoleService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    private static final long serialVersionUID = -964331016996876701L;

    @Autowired
    AdminUserMapper adminUserMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserStoreMapper userStoreMapper;

    @Transactional
    public boolean delete(Integer[] ids) {
//        for (Integer id : ids) {
//            if (userStoreMapper.deleteByUserId(id))
//                getDao().delete(id);
//            else
//                return false;
//        }
        return true;
    }

    @Override
    public boolean saveOrUpdate(AdminUser user) {
        return this.adminUserMapper.saveOrUpdate(user);
    }

    private String findPasswordByUserId(Integer userId) {
        return adminUserMapper.findPasswordByUserId(userId);
    }

    public AdminUser find(String userName, String password) {
        return this.adminUserMapper.find(userName, DigestUtils.md5Hex(password));
    }

//    @Transactional
//    public boolean saveInfo(AdminUser user) {
//        AdminUser info = find(user.getId());
//        info.setAddress(user.getAddress());
//        info.setEmail(user.getEmail());
//        info.setMobile(user.getMobile());
//        info.setTel(user.getTel());
//        return this.getDao().saveOrUpdate(info);
//    }

//    @Transactional
//    public int savePassword(Integer userId, String oldPassword, String newPassword) {
//        if (StringUtils.areNotEmpty(oldPassword, newPassword)) {
//            AdminUser info = find(userId);
//            if (StringUtils.equals(DigestUtils.md5Hex(oldPassword),
//                    info.getPassword())) {
//                info.setPassword(DigestUtils.md5Hex(newPassword));
//                boolean flag = this.getDao().saveOrUpdate(info);
//                if (flag) {
//                    return 1;
//                }
//                return 2;
//            }
//            return 0;
//        }
//
//        return 1;
//    }

//    public boolean userExists(String userName) {
//        AdminUser user = super.hasSame(new Pair<String, String>("name", userName));
//        boolean flag = false;
//        if (ObjectUtil.isEmpty(user))
//            flag = false;
//        else {
//            flag = true;
//        }
//        return flag;
//    }

    //TODO locked 为什么没有为true的时候
//    public boolean userLock(String userName) {
//        boolean locked = false;
//        AdminUser userlogin = super.hasSame(new Pair<String, String>("name", userName));
//        if (ObjectUtil.isEmpty(userlogin.getLocked())) {
//            locked = false;
//        } else if (userlogin.getLocked()) {
//            if (userlogin.getLockLoginTime() != null) {
//
//            } else {
//                locked = false;
//            }
//        } else locked = false;
//
//        return locked;
//    }

//    public void setUserLock(String username) {
//        AdminUser userlogin = super.hasSame(new Pair<String, String>("name", username));
//        userlogin.setLocked(true);
//        this.getDao().saveOrUpdate(userlogin);
//    }


//    public String getMobile(String username) {
//        AdminUser user = super.hasSame(new Pair<String, String>("name", username));
//        if (StringUtils.areNotEmpty(user.getMobile())) {
//            return user.getMobile();
//        }
//        return null;
//    }
//
//
//    public boolean changeLock(Integer id, Integer storeId) {
//        AdminUser user = super.find(id);
//        if (!storeId.equals(user.getDefaultStore()))
//            return false;
//        if (ObjectUtil.isEmpty(user.getLocked())) {
//            user.setLocked(true);
//            user.setLastLoginTime(new Date());
//        } else {
//            user.setLocked(!user.getLocked());
//        }
//        return this.getDao().saveOrUpdate(user);
//    }

}