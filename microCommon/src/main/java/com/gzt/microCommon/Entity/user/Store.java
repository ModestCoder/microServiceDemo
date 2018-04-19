package com.gzt.microCommon.Entity.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Store
 *
 * @author chenzhenwei
 *         <p>
 *         Wed Dec 09 16:04:08 CST 2015
 */
public class Store {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Store() {
    }

    private Integer id;

    private String name;

    private String logo;

    private String memo;

    private Integer parentId;

    private Integer lev;

    private String address;

    private Boolean isShow;

    private Integer sortOrder;

    private String code;

    private String customerServiceEmails;


    private String platformCode;

    private Date updateTime;   //更新时间

    List<Integer> childStoreId = new ArrayList<Integer>();

    List<Store> childStore = new ArrayList<Store>();

    private String dataselection = "";

    private Integer select = 0;

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

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public Integer getLev() {
        return this.lev;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getChildStoreId() {
        return childStoreId;
    }

    public void setChildStoreId(List<Integer> childStoreId) {
        this.childStoreId = childStoreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        return !(id != null ? !id.equals(store.id) : store.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public List<Store> getChildStore() {
        return childStore;
    }

    public void setChildStore(List<Store> childStore) {
        this.childStore = childStore;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    public String getDataselection() {
        return dataselection;
    }

    public void setDataselection(String dataselection) {
        this.dataselection = dataselection;
    }

    public String getCustomerServiceEmails() {
        return customerServiceEmails;
    }

    public void setCustomerServiceEmails(String customerServiceEmails) {
        this.customerServiceEmails = customerServiceEmails;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
