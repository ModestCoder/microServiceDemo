package com.gzt.microCommon.Entity.user;

/**
 * Role
 *
 * @author chenzhenwei
 *         <p>
 *         Wed Dec 09 16:00:52 CST 2015
 */
public class Role {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Role() {
    }

    private Integer id;

    private String name;

    private String memo;

    private Boolean isPublic;

    private String storeId;

    Store store;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getIsPublic() {
        return this.isPublic;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return this.storeId;
    }
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


}
