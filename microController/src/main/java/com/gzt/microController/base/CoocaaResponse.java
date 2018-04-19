package com.gzt.microController.base;

import net.sf.json.JSONObject;

/**
 * 如果status为false，message会提示错误信息，如果status为true，message无信息，data有数据
 *
 * @edit 2016-10-26 新增返回code
 * @param <T>
 */
public class CoocaaResponse<T> {
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer DEFAULT_FAIL_CODE = 500;
    private Boolean result;
    private String message;
    private T data;
    private Integer code;

    /**
     *
     * @param resEnum
     *            需要返回的文字所在的emun
     * @param strings
     *            用于替换 resEnum.value() 中的 {0} 等
     * @return JSON
     */
    public static String failResponseJson(ResponseMessage resEnum,
                                          String... strings) {
        return valueOfJson(false, parseMessage(resEnum.getValue(), strings),
                null, DEFAULT_FAIL_CODE);
    }

    /**
     *
     * @param resEnum
     *            需要返回的文字所在的emun
     * @param strings
     *            用于替换 resEnum.value() 中的 {0} 等
     * @return JSON
     */
    public static String failResponseJson(Integer code, ResponseMessage resEnum,
                                          String... strings) {
        return valueOfJson(false, parseMessage(resEnum.getValue(), strings),
                null, code);
    }

    /**
     *
     * @param resEnum
     *            需要返回的文字所在的emun
     * @param strings
     *            用于替换 resEnum.value() 中的 {0} 等
     * @return CoocaaResponse<T>
     */
    public static <T> CoocaaResponse<T> failResponse(ResponseMessage resEnum,
                                                     String... strings) {
        return valueOf(false, parseMessage(resEnum.getValue(), strings), null, DEFAULT_FAIL_CODE);
    }

    /**
     *
     * @param resEnum
     *            需要返回的文字所在的emun
     * @param strings
     *            用于替换 resEnum.value() 中的 {0} 等
     * @return CoocaaResponse<T>
     */
    public static <T> CoocaaResponse<T> failResponse(Integer code, ResponseMessage resEnum,
                                                     String... strings) {
        return valueOf(false, parseMessage(resEnum.getValue(), strings), null, code);
    }

    /**
     *
     * @param message
     *            需要返回的文字
     * @param strings
     *            用于替换文字中的 {0} 等
     * @return
     */
    public static String failResponseJson(String message, String... strings) {
        return valueOfJson(false, parseMessage(message, strings), null, DEFAULT_FAIL_CODE);
    }

    /**
     *
     * @param message
     *            需要返回的文字
     * @param strings
     *            用于替换文字中的 {0} 等
     * @return
     */
    public static String failResponseJson(Integer code, String message, String... strings) {
        return valueOfJson(false, parseMessage(message, strings), null, code);
    }

    /**
     *
     * @param <T>
     * @param message
     *            需要返回的文字
     * @param strings
     *            用于替换文字中的 {0} 等
     * @return
     */
    public static <T> CoocaaResponse<T> failResponse(String message,
                                                     String... strings) {
        return valueOf(false, parseMessage(message, strings), null, DEFAULT_FAIL_CODE);
    }

    /**
     *
     * @param <T>
     * @param message
     *            需要返回的文字
     * @param strings
     *            用于替换文字中的 {0} 等
     * @return
     */
    public static <T> CoocaaResponse<T> failResponse(Integer code, String message,
                                                     String... strings) {
        return valueOf(false, parseMessage(message, strings), null, code);
    }

    /**
     * 替换字符串中的参数
     *
     * @param message
     *            需要解析的字符串
     * @param strings
     *            用于替换message中的{0},{1}等
     * @return 解析后的字符串
     */
    public static String parseMessage(String message, String... strings) {
        for (int i = 0; i < strings.length; i++) {
            message = message.replaceAll("\\{" + i + "\\}", strings[i]);
        }
        return message;
    }

    /**
     * 返回成功信息json
     *
     * @param data
     *            需要返回的信息
     * @return
     */
    public static <T> String successResponseJson(T data) {
        return valueOfJson(true, null, data, SUCCESS_CODE);
    }

    /**
     * 返回成功信息json
     *
     * @return
     */
    public static String successResponseJson() {
        return successResponseJson(null);
    }

    /**
     * 返回成功信息
     *
     * @param data
     * @return CoocaaResponse<T>
     */
    public static <T> CoocaaResponse<T> successResponse(T data) {
        return valueOf(true, null, data, SUCCESS_CODE);
    }

    /**
     * 返回成功信息
     *
     * @return CoocaaResponse<T>
     */
    public static <T> CoocaaResponse<T> successResponse() {
        return successResponse(null);
    }

    private static <T> CoocaaResponse<T> valueOf(Boolean result,
                                                 String message, T data, Integer code) {
        CoocaaResponse<T> cr = new CoocaaResponse<T>();
        cr.result = result;
        cr.message = message;
        cr.data = data;
        cr.code = code;
        return cr;
    }

    private static <T> String valueOfJson(Boolean status, String message, T data, Integer code) {
        return JSONObject.fromObject(valueOf(status, message, data, code)).toString();
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public enum ResponseMessage {
        BONUS_NOT_EXISTS("亲，您选择的C码[{0}]并不存在!"),
        BOUNS_PARAM_IS_NULL("亲，请输入C码序列号!"),
        BOUNS_CHENK_NUM_LIMIT("亲，一个小时只能检查{0}次哦"),
        BOUNS_REPEAT("亲，你提交的C码序列号[{0}]有重复哦"),
        BOUNS_CAN_NOT_BE_USED("亲，您输入的C码[{0}]于此订单无效!"),
        BOUNS_USER_NUMBER_EMPTY("亲，您输入的C码[{0}]无使用次数!"),
        BOUNS_NOT_CONFORM_TO_C_RULES("亲，您输入的C码[{0}]与[{1}]都为C规则，不能同时使用!"),
        BOUNS_NOT_CONFORM_TO_B_RULES("亲，您输入的C码[{0}]与[{1}]都为B规则且为同一个C码类型，不能同时使用!"),
        BONUS_CAN_NOT_BIND("亲，您输入的C码[{0}]为公众C码，无法绑定！"),
        BONUS_ALREAD_BIND("亲，您输入的C码[{0}]已被绑定。"),
        BONUS_NOT_IN_USE_TIME("亲，您的C码[{0}]未在使用时间段({1}-{2})。"),
        BONUS_NOT_BELONG_TO_YOU("亲，该C码[{0}]不属于你。"),
        BONUS_GOODS_NOT_CORRECT("亲，该C码规则不对。"),
        BONUS_ATTRIBUTE_ERROR("亲，您输入的C码[{0}]有问题，请联系客服！"),
        ACTIVITY_OVER_TIME("此次活动已经结束，敬请期待下次活动开启"),
        ACTIVITY_NOT_ENOUGH_STOCK("亲，被抢光了哦。"),
        ORDER_REPEAT_COMMIT("不能重复提交"),
        ORDER_SAVE_FAILD("订单保存出错"),
        CART_IS_EMPTY("购物车无商品"),
        GOODS_NOT_ON_SALE("亲，您购买的商品[{0}]已经下架"),
        GOODS_NOT_ENOUGH_STOCK("亲，您购买的商品[{0}]库存不足"),
        GOODS_CAN_NOT_ALONE_SALE("亲，您购买的商品[{0}]无法单独销售"),
        GOODS_OVER_LIMIT("亲，您购买的商品已经超出限购数量"),
        GOODS_NOT_IN_SALE_TIME("亲，您购买的商品当前非销售时间"),
        ILLEGAL_ACCESS("非法访问"),
        ENTER_FROM_SECONDKILL_PAGE("请从秒杀页面进入"),
        ENTER_FROM_AUCTION_PAGE("请从我的竞拍页面进入"),
        ENTER_FROM_SUBSCRIBE_PAGE("请从我的竞拍页面进入"),
        NOT_LOGIN("亲，你没有登录，请登录后操作"),
        AUCTION_BID_PRICE_ERROR("竞拍价格有误，请重新输入。"),
        AUCTION_BID_PRICE_LESS_THAN_LAST("竞拍价格不能小于等于当前价格。"),
        AUCTION_BID_PRICE_MORE_THAN_MAX_ADD_PRICE("竞拍价格不能大于最高加价。"),
        AUCTION_BID_PRICE_NOT_MULTIPLE_NUM_OF_MIN_ADD_PRICE("加价不是最低加价倍数。"),
        AUCTION_TIME_IS_NOT_START("竞拍还未开始，请于{0}开始竞拍"),
        AUCTION_USER_NO_REPEAT("同一用户连续出价。"),
        AUCTION_OVER("该竞拍结束，敬请期待下次竞拍"),
        AUCTION_NOT_OVER("该竞拍还未结束，请结束后下单。"),
        ORDER_GOODS_COMMENT_ALREADY_LIKE("您已经赞过"),
        USER_ACT_NOTEMPTY("不能重复抢购"),
        ACT_WAIT("抢购活动排队等待中"),
        DEPOSIT_CAN_NOT_USE("亲，预购码貌似有点问题哦。"),
        SUBSCRIBE_OVER("该次预约已结束，敬请期待下次活动。"),
        SUBSCRIBE_NOT_START("该次预约还未开始付款。"),
        SUBSCRIBE_ALREADY_ORDERED("你已经下过单了哦。"),
        ALREADY_GRAND_C_CODE("对不起，您已经领取过了。"),
        GRAND_C_CODE_ACTION_NOT_FIND("该活动不存在或已过期。"),
        GRAND_C_CODE_ERROR("发送C码出错，请重新领取。");
        private String value;
        private ResponseMessage(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}