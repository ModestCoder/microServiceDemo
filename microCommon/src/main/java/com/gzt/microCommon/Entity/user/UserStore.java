package com.gzt.microCommon.Entity.user;

/**
 * User-Store
 *
 * @author chenzhenwei
 *         <p>
 *         Wed Dec 09 16:06:23 CST 2015
 */
public class UserStore {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserStore() {
    }

    private Integer id;

    private Integer adminUserId;

    private Integer storeId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreId() {
        return this.storeId;
    }


}
