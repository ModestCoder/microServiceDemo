package com.gzt.microController.base;

import com.gzt.microController.controller.user.UserStorePermission;
import com.gzt.microCommon.Entity.user.AdminUser;
import com.gzt.microCommon.Entity.user.Permission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：所有无model的controller的父类
 *
 * @author 陈振威
 * @date 2015-07-09 10:01:16
 */
public abstract class WithOutModelAction extends AbstractController {
    protected final Log logger = LogFactory.getLog(getClass());

    private static HttpHeaders responseHeaders = new HttpHeaders();

    static {
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    }

    /**
     * 取得一个session
     */
    public HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession contextSess = attr.getRequest().getSession(true);
        return contextSess;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        throw new NotImplementedException();
    }

    /**
     * 重定向
     *
     * @param path
     * @return
     */
//    public String redirect(String path) {
//        return StringUtils.join(new String[]{"redirect:", path});
//    }

    /**
     * 统一的错误转向
     *
     * @param model
     * @param errorMsg
     * @return
     */
    public String error(Model model, String errorMsg) {
        return error(errorMsg);
    }

    /**
     * 统一的错误转向
     *
     * @param errorMsg
     * @return
     */
    public String error(String errorMsg) {
        throw new RuntimeException(errorMsg);
    }

    /**
     * 无model的action取RequestMapping中的value为路径
     *
     * @return
     */
//    public String dir() {
//        return StringUtil.lowerCase(getClass().getAnnotation(RequestMapping.class).value()[0].replaceAll("/", ""));
//    }

    /**
     * 获取用户
     */
    public AdminUser getSessionAdminUser() {
        UserStorePermission userStorePermission = getSessionUserStorePermission();
        return userStorePermission != null ? userStorePermission.getAdminUser() : null;
    }

    public UserStorePermission getSessionUserStorePermission() {
        return (UserStorePermission) this.getSession().getAttribute(UserStorePermission.LOGIN_SESSION_INFO);
    }

    /**
     * 跳转
     *
     * @param path
     * @return
     */
//    public String forward(String path) {
//        return StringUtils.join("/", new String[]{dir(), path});
//    }

    /**
     * 跳转
     *
     * @param path
     * @return
     */
//    public String forward(String path,String viewsName) {
//        return StringUtils.join("/", new String[] { viewsName, dir(), path });
//    }

    /**
     * 返回页面字符串,编码UTF-8
     *
     * @param msg
     * @return
     */
    public ResponseEntity<String> responseString(String msg) {
        return new ResponseEntity<String>(msg, responseHeaders, HttpStatus.CREATED);
    }

    /**
     * 获取用户
     */
//    public Users getSessionUser() {
//        Users user = (Users) this.getSession().getAttribute(Users.USER_SESSION_ID);
//        return ObjectUtil.isNotEmpty(user) ? user : null;
//    }

    /**
     * 重定向
     *
     * @param path
     * @return
     */

//    public String redirect(String path, Map<String, String> params) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(path);
//        if (!path.contains("?")) {
//            sb.append("?");
//        }
//        try {
//            sb.append(HttpUtils.buildQuery(params, null));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return this.redirect(sb.toString());
//    }


    public List<Permission> getChildPermission(String uri) throws Exception {
        Assert.notNull(uri, "uri is null, can not get child permission");
        String path = uri.substring(1, uri.lastIndexOf("."));

        List<Permission> permissionList = new ArrayList<Permission>(this.getSessionUserStorePermission().getCurrentPermission());

        //非递归实现
        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            if (path.equals(permission.getPath())) {
                return permission.getChildPermission();
            } else if (!permission.getChildPermission().isEmpty()) {
                permissionList.addAll(permission.getChildPermission());
            }
        }
        return null;
    }

//    public Pager getDatatablesPager(HttpServletRequest request, Pager pager, String... sortName) {
//        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
//        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
//        if (iDisplayLength == -1){
//            iDisplayLength = Integer.MAX_VALUE;
//        }
//        String sSortDir = request.getParameter("sSortDir_0");
//        Integer iSortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
//        if (sortName.length > iSortCol && !StringUtils.isEmpty(sortName[iSortCol]))
//            pager.putSort(sortName[iSortCol], "asc".equals(sSortDir));
//        pager.setPageSize(iDisplayLength);
//        pager.setPage(iDisplayStart / pager.getPageSize() + 1);
//        return pager;
//    }
//
//    public String parseResultToDatatables(HttpServletRequest request, Result<?> result) {
//        String sEcho = request.getParameter("sEcho");
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("aaData", result.getList());
//        map.put("iTotalRecords", result.getPager().getTotalNum());
//        map.put("iTotalDisplayRecords", result.getPager().getTotalNum());
//        map.put("sEcho", sEcho);
//        return JSONObject.fromObject(map).toString();
//    }

    private static final String COOCAAIMG_PATH = "http://img.coocaa.com";

//    public CoocaaResponse<String> uploadImageToFTP(MultipartFile multipartFile) throws IOException {
//        FTPUtil ftpUtil = COOCAA.context.getBean(FTPUtil.class);
//
//        if (multipartFile.isEmpty()) return CoocaaResponse.failResponse("请上传文件");
//
//        String type = multipartFile.getOriginalFilename().toLowerCase();
//        if (!type.endsWith(".jpg") && !type.endsWith(".png")) {
//            return CoocaaResponse.failResponse("只能上传jpg/png格式的图片哦");
//        }
//        String realPath = this.getSession().getServletContext().getRealPath("/");
//        String path = "/upload/" + UUID.randomUUID() + type.substring(type.indexOf("."),type.length());
//        File file = new File(realPath + path);
//        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
//
//        //上传到ftp
//        SimpleDateFormat sdfImg = new SimpleDateFormat("yyyyMMdd");
//        String ftpPath = "java-manage/images/" + sdfImg.format(new Date()) + "/";
//
//        try {
//            ftpUtil.upload(file, ftpPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return CoocaaResponse.failResponse("上传ftp出错，请重试");
//        }
//
//        return CoocaaResponse.successResponse(COOCAAIMG_PATH + "/" + ftpPath + file.getName());
//    }

}
