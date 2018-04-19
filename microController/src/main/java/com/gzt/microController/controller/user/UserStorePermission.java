package com.gzt.microController.controller.user;

import com.gzt.microCommon.Entity.user.*;
import com.gzt.microController.base.SpringContextUtil;
import com.gzt.microCommon.service.user.AdminUserService;
import com.gzt.microCommon.service.user.PermissionService;
import com.gzt.microCommon.service.user.RoleService;
import com.gzt.microCommon.service.user.StoreService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * session中的权限信心
 * Created by chenzhenwei on 2015-12-10.
 */
public class UserStorePermission implements Serializable {
    public static final String LOGIN_SESSION_INFO = "LOGINSESSIONINFO";

    public UserStorePermission() {
    }

    public UserStorePermission(List<UserStore> userStoreList, List<UserRole> userRoleList, AdminUser adminUser) {
        PermissionService permissionService = (PermissionService) SpringContextUtil.getBean(PermissionService.class);
        StoreService storeService = (StoreService) SpringContextUtil.getBean(StoreService.class);

        this.adminUser = adminUser;
        if(adminUser.getDefaultStore() != null) {
            Store store = storeService.find(adminUser.getDefaultStore());
            List<Store> childStoreList = storeService.likeByCode(store.getCode());
            for (Store store1 : childStoreList) {
                store.getChildStoreId().add(store1.getId());
            }
            store.getChildStore().add(setList(childStoreList,store));
            this.currentStore = store;
        }

        if (adminUser.getIsAdmin() == AdminUser.SUPER_ADMIN) {
            List<Store> stores = storeService.findAll();
            List<Permission> allPermission = UserStorePermission.setPermissionLev(permissionService.findAll());
            for (Store store : stores) {
                List<Store> childStoreList = storeService.likeByCode(store.getCode());
                for (Store store1 : childStoreList) {
                    store.getChildStoreId().add(store1.getId());
                }
                store.getChildStore().add(setList(childStoreList, store));
                if (this.currentStore == null) this.currentStore = store;

                storePermission.put(store, allPermission);
            }
        } else {
            RoleService roleService = (RoleService) SpringContextUtil.getBean(RoleService.class);
            Set<Integer> storeSet = new HashSet<Integer>();
            if (userStoreList != null) {
                for (UserStore userStore : userStoreList) {
                    storeSet.add(userStore.getStoreId());
                }
            }

            for (Integer storeId : storeSet) {
                Store store = storeService.find(storeId);
                List<Store> childStoreList = storeService.likeByCode(store.getCode());
                for (Store store1 : childStoreList) {
                    store.getChildStoreId().add(store1.getId());
                }
                store.getChildStore().add(setList(childStoreList,store));
                if (this.currentStore == null) this.currentStore = store;
                if (userRoleList != null) {
                    for (UserRole userRole : userRoleList) {
                        Role role = roleService.find(userRole.getRoleId());
                        if (storeId == Integer.parseInt(role.getStoreId())) {
                            Set<Permission> permissionSet = new HashSet<Permission>();

                            List<Permission> permissionList = permissionService.findPermissionByRoleId(role.getId());
                            permissionSet.addAll(permissionList);

                            permissionList = storePermission.get(store);
                            if (permissionList != null) permissionSet.addAll(permissionList);

                            permissionList = new ArrayList<Permission>(permissionSet);
                            Collections.sort(permissionList, new Comparator<Permission>() {

                                public int compare(Permission permission1, Permission permission2) {
                                    return permission1.getCode().compareTo(permission2.getCode());
                                }
                            });
                            storePermission.put(store, permissionList);
                        }
                    }
                }
            }

            //权限分级
            for (Store store : storePermission.keySet()) {
                storePermission.put(store, UserStorePermission.setPermissionLev(storePermission.get(store)));
            }

            //过滤重复权限
            Map<Store,List<Permission>> noRepeatMap=new HashMap<Store, List<Permission>>();
            for(Map.Entry<Store,List<Permission>> entry:storePermission.entrySet()){
                noRepeatMap.put(entry.getKey(),getNoRepeatPermission(entry.getValue()));
            }
            storePermission=noRepeatMap;
        }
    }

    public boolean changeCurrentStore(HttpServletRequest request,Integer storeId) {
        UserStorePermission userStorePermission = (UserStorePermission) request.getSession().getAttribute(LOGIN_SESSION_INFO);
        StoreService storeService = (StoreService) SpringContextUtil.getBean(StoreService.class);
        this.currentStore = storeService.find(storeId);
        List<Store> childStoreList = storeService.likeByCode(currentStore.getCode());
        for (Store store1 : childStoreList) {
            currentStore.getChildStoreId().add(store1.getId());
        }
        currentStore.getChildStore().add(setList(childStoreList, this.currentStore));
        request.getSession().removeAttribute(LOGIN_SESSION_INFO);
        userStorePermission.setCurrentStore(currentStore);
        request.getSession().setAttribute(LOGIN_SESSION_INFO, userStorePermission);
        adminUser.setDefaultStore(storeId);
        AdminUserService adminUserService = (AdminUserService) SpringContextUtil.getBean(AdminUserService.class);
        adminUserService.saveOrUpdate(adminUser);

        return true;
    }

    public  Store setList(List<Store> childStoreList, Store currentStore){
        List<Store> list = new ArrayList<Store>();
        for (Store store : childStoreList) {
            if(store.getCode().substring(0,store.getCode().length()-3).equals(currentStore.getCode())){
                setList(childStoreList,store);
                list.add(store);
            }
        }
        currentStore.setChildStore(list);
        return currentStore;
    }

    public static List<Permission> setPermissionLev(List<Permission> permissionList) {
        if (permissionList == null || permissionList.isEmpty()) return permissionList;

        List<Permission> levelPermission = new ArrayList<Permission>();
        List<Permission> removeList = new ArrayList<Permission>();
        for (Permission permission : permissionList) {
            if (permission.getLev() == 1) {
                levelPermission.add(permission);
                removeList.add(permission);
            }
        }
        permissionList.removeAll(removeList);
        removeList = new ArrayList<Permission>();
        for (Permission p : levelPermission) {
            for (Permission permission : permissionList) {
                if (permission.getLev() == 2 && p.getId() == permission.getParentId().intValue()&& permission.getStatus()) {
                    p.getChildPermission().add(permission);
                    removeList.add(permission);
                }
            }
        }

        permissionList.removeAll(removeList);
        if (!permissionList.isEmpty()) {
            for (Permission p : levelPermission) {
                for (Permission p2 : p.getChildPermission()) {
                    for (Permission permission : permissionList) {
                        if (permission.getLev() == 3 && p2.getId() == permission.getParentId().intValue()&& permission.getStatus()) {
                            p2.getChildPermission().add(permission);
                        }
                    }
                }
            }
        }

        return levelPermission;
    }

    private Store currentStore;//当前店铺id
    private Map<Store, List<Permission>> storePermission = new HashMap<Store, List<Permission>>();//店铺所对应的权限
    private AdminUser adminUser;//当前用户信息

    /**
     * 返回一个权限的克隆体
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Permission> getCurrentPermission() {
        List<Permission> list = null;
        //            list = Object.deepClone(storePermission.get(currentStore));
        list = storePermission.get(currentStore);
        return list;
    }

    public Store getCurrentStore() {
        return currentStore;
    }

    public void setCurrentStore(Store currentStore) {
        this.currentStore = currentStore;
    }

    public Map<Store, List<Permission>> getStorePermission() {
        return storePermission;
    }

    public void setStorePermission(Map<Store, List<Permission>> storePermission) {
        this.storePermission = storePermission;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public List<Permission> getNoRepeatPermission(List<Permission> permissionList){
        List<Permission> result=new ArrayList<Permission>();
        for(Permission permission:permissionList){
            boolean noRepeat=true;
            int i=0;
            for(Permission per:result){
                if(permission.getId().equals(per.getId())){
                    noRepeat=false;
                    break;
                }
                i++;
            }
            if(noRepeat==true){
                result.add(permission);
            }else if(permission.getChildPermission()!=null){
                //找到相同名称的permission的子权限的并集
                List<Permission> child1=permission.getChildPermission();
                List<Permission> child2=result.get(i).getChildPermission();
                child1.addAll(child2);
                result.get(i).setChildPermission(getNoRepeatPermission(child1));
            }
        }
        return result;
    }
}
