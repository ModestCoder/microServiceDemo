package com.gzt.microController.controller.user;

import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Calendar;

public class ValidateCodeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public static Long invalidTime = 0l;

    private String validateCode;
    private Long createTime;

    public String getValidateCode() {
        return validateCode;
    }
    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    /**
     * 是否失效
     * @return
     */
    public boolean isOutOfTime() {
        long nowtime = Calendar.getInstance().getTimeInMillis();
        return (nowtime - createTime) > invalidTime;
    }

    public Long getInvalidTime() {
        return invalidTime;
    }
    public void setInvalidTime(Long invalidTime) {
        ValidateCodeBean.invalidTime = invalidTime;
    }

    public static boolean setSessionValue(HttpSession session, String key,String randomString) {
        ValidateCodeBean vcb = new ValidateCodeBean();
        vcb.setCreateTime(Calendar.getInstance().getTimeInMillis());
        vcb.setValidateCode(randomString);
        session.setAttribute(key, vcb);
        return true;
    }

    public static boolean validate(HttpSession session, String key,String validateCode){
        ValidateCodeBean vcb = (ValidateCodeBean) session.getAttribute(key);
        session.removeAttribute(key);
        return vcb != null && vcb.isOutOfTime() && StringUtils.equals(validateCode.toUpperCase(), vcb.validateCode.toUpperCase());
    }
}
