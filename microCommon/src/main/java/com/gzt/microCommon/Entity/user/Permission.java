package com.gzt.microCommon.Entity.user;


import java.util.ArrayList;
import java.util.List;

public class Permission {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String code;

    private String path;

    private Integer parentId;

    private Integer lev;

    private String memo;

    private Boolean status;

    private Boolean menu;

    private Boolean isButton;

    private String iconClass;

    private String model;

    private List<Permission> childPermission = new ArrayList<Permission>();

    private Integer select = 0;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
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

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public Boolean getMenu() {
        return this.menu;
    }

    public void setMenu(Boolean menu) {
        this.menu = menu;
    }

    public Boolean getIsButton() {
        return isButton;
    }

    public void setIsButton(Boolean isButton) {
        this.isButton = isButton;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Permission> getChildPermission() {
        return childPermission;
    }

    public void setChildPermission(List<Permission> childPermission) {
        this.childPermission = childPermission;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }
}