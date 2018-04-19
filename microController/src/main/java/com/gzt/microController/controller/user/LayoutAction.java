package com.gzt.microController.controller.user;

import com.gzt.microController.base.WithOutModelAction;
import com.gzt.microCommon.Entity.user.AdminUser;
import com.gzt.microCommon.Entity.user.Permission;
import com.gzt.microCommon.service.user.AdminUserService;
import com.gzt.microCommon.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * 功能描述：首页布局
 *
 * @author
 */
@Controller
@RequestMapping("/layout")
public class LayoutAction extends WithOutModelAction {

    @Autowired
    AdminUserService userService;

    @Autowired
    PermissionService permissionService;

    private static Properties configs;
    private static Map<Object, Object> syscontext = new HashMap<Object, Object>();

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request, Integer storeId, Model model) throws IOException {
        UserStorePermission userStorePermission = getSessionUserStorePermission();
        if (storeId != null) userStorePermission.changeCurrentStore(request,storeId);
        model.addAttribute("user", userStorePermission.getAdminUser());
        List<Permission> permissionList = userStorePermission.getCurrentPermission();
        model.addAttribute("adminMap", AdminUser.ADMIN_MAP);
        model.addAttribute("permissions", permissionList);
        model.addAttribute("storeNum", userStorePermission.getStorePermission().size());
        model.addAttribute("storeSet", userStorePermission.getStorePermission().keySet());
        model.addAttribute("currentStoreId", userStorePermission.getCurrentStore().getId());

//        configs = configs == null ? ConfigUtil.loadProperties(COOCAA.contextPath, "sys.properties") : configs;
//        for (Map.Entry<Object, Object> config : configs.entrySet()) {
//            syscontext.put(config.getKey(), config.getValue());
//        }
//        String webhost = (String) syscontext.get("sys.webhost");
//        model.addAttribute("webhost", webhost);
        return "forward:/pages/sysviews/main.jsp";
    }

    /**
     * dashboard
     *
     * @return
     */
    @RequestMapping("dashboard")
    public String dashboard() {
        return "/sysviews/layout/dashboard";
    }

    @RequestMapping("emptyRedis")
    public void emptyRedis(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://test.tv.coocaa.com/log/emptyRdis.html");
    }
}
