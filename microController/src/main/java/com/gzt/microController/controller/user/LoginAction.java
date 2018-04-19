package com.gzt.microController.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gzt.microController.base.CoocaaResponse;
import com.gzt.microController.base.RandomValidateCode;
import com.gzt.microController.base.WithOutModelAction;
import com.gzt.microCommon.Entity.user.AdminUser;
import com.gzt.microCommon.Entity.user.UserRole;
import com.gzt.microCommon.Entity.user.UserStore;
import com.gzt.microCommon.service.user.AdminUserService;
import com.gzt.microCommon.service.user.UserRoleService;
import com.gzt.microCommon.service.user.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/login")
public class LoginAction extends WithOutModelAction {

    @Reference(version = "1.0.0")
    AdminUserService userService;

    @Reference(version = "1.0.0")
    UserStoreService userStoreService;

    @Reference(version = "1.0.0")
    UserRoleService userRoleService;

//    private Rule[] rules = {
//            Rule.required("name", "姓名不能为空"),
//            Rule.required("password", "密码不能为空"),
//            Rule.length("name", 1, 32, "姓名长度在1和32之间"),
//            Rule.length("password", 6, 16, "密码在6到16个字符以内")
//    };

    @ResponseBody
    @RequestMapping("login")
    public CoocaaResponse<?> login(AdminUser user, String validateCode, HttpServletRequest request) throws IOException {
        if (!ValidateCodeBean.validate(request.getSession(), RandomValidateCode.RANDOMCODEKEY, validateCode)) {
            return CoocaaResponse.failResponse("验证码错误或超时，请重新输入。");
        }

        request.getSession().invalidate();//equals session clear

        String message = null;
//        try {
//            message = Validator.validate(user, this.rules);
//        } catch (ValidateException e) {
//            this.logger.error(e);
//            return CoocaaResponse.failResponse("登陆验证失败,请重试。");
//        }
        if (message != null) {
            return CoocaaResponse.failResponse(message);
        }
        user = this.userService.find(user.getName(), user.getPassword());
        if (user == null)
            return CoocaaResponse.failResponse("用户名或密码错误,请重试。");
        if (!user.getStatus()) {
            return CoocaaResponse.failResponse("登陆失败,您可能已被禁止登陆。");
        }

        user.setLastLoginTime(new Date());
        user.setErrorTimes(0);
        this.userService.saveOrUpdate(user);

        List<UserStore> userStoreList = userStoreService.findByUserId(user.getId());
        List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());

        UserStorePermission userStorePermission = new UserStorePermission(userStoreList, userRoleList, user);

        this.getSession().setAttribute(UserStorePermission.LOGIN_SESSION_INFO, userStorePermission);

        //页面清除缓存按钮读取的配置放进session
//        configs = configs == null ? ConfigUtil.loadProperties(COOCAA.contextPath, "sys.properties") : configs;
//        for( Map.Entry<Object, Object> config : configs.entrySet()) {
//            syscontext.put( config.getKey(), config.getValue());
//        }
//        String webhost = (String) syscontext.get("sys.webhost");
//        this.getSession().setAttribute(WEBHOST, webhost);

        return CoocaaResponse.successResponse();
    }

    @RequestMapping("logout")
    public String logout() {
        this.getSession().setAttribute(UserStorePermission.LOGIN_SESSION_INFO, null);
        this.getSession().invalidate();
        return "redirect:/login/";
    }

    @RequestMapping("/")
    public String index() {
        return "forward:../login.jsp";
    }

    @RequestMapping("validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            RandomValidateCode.getRandcode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}