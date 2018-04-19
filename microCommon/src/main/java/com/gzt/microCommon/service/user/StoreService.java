package com.gzt.microCommon.service.user;

import com.gzt.microCommon.Entity.user.Store;

import javax.jws.WebService;
import java.util.List;


/**
 * Store
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:04:08 CST 2015
 */
@WebService
public interface StoreService {
    /**
     * 查询总部
     * @return
     */
//    Store rootStore();
    /**
     * 根据当前用户的店铺id 获取店铺 2015.11.26
     * @return
     */
//    List<Store> getUserStore(Integer storeId);

    /**
     * 分页查询
     * @param pager
     * @return
     */
//    Result<Store> findByPager(Pager pager);

    List<Store> likeByCode(String code);

//    List<Store> getChildStore(Integer storeId);

    /**
     * 根据平台code获取店铺列表
     * @param platformCode
     * @return
     */
//    List<Store> getListByPlatformCode(String platformCode);
//
//    List<Store> findChildStoreByCode(String code);

    Store find(Integer id);

    List<Store> findAll();

}
