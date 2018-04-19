package com.gzt.microCommon.service.user;

import com.gzt.microCommon.Entity.user.UserStore;

import javax.jws.WebService;
import java.util.List;


/**
 * User-Store
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:06:23 CST 2015
 */
@WebService
public interface UserStoreService {
    List<UserStore> findByUserId(Integer userId);
}
