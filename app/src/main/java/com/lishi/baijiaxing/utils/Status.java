package com.lishi.baijiaxing.utils;

/**
 * Created by Administrator on 2016/11/30.
 */

public class Status {
//    '200' => '请求数据成功',
//            '201' => '登录成功',
//            '202' => '注册成功',
//            '203' => 'token验证成功',
//            '204' => '操作成功',
//            '400' => '请求数据错误',//请求格式错误
//            '401' => '注册失败',//注册失败
//            '402' => '账号不存在',//账号不存在
//            '403' => '密码错误',//账号或密码错误
//            '404' => 'token失效',//token失效
//            '405' => 'token存在',
//            '406' => '账号存在',
//            '407' => '缺少数据',//请求提交的数据缺少必要的数据
//            '408' => '无效的参数',//无效的请求参数
//            '409' => '操作失败'
    /**
     * 获取用户信息成功
     */
    public final static String STATUS_GETUSERINFO_SUCCESS = "200";
    /**
     * 登录成功
     */
    public final static String STATUS_LOGIN_SUCCESS = "201";
    /**
     * 未找到账户
     */
    public final static String STATUS_USER_NO_FOUNT = "402";
    /**
     * 密码错误
     */
    public final static String STATUS_PAW_FAILED = "403";

    /**
     * token失效
     */
    public final static String STATUS_TOKEN_LOSE_EFFICACY = "404";

    /**
     * 操作成功
     */
    public final static String STATUS_OPERATION_SUCCESS = "204";
    /**
     * 操作失败
     */
    public final static String STATUS_OPERATION_FAILED = "409";
    /**
     * 无效的请求参数
     */
    public final static String STATUS_REQUEST_INVAILD = "409";
    /**
     * 提交订单成功
     */
    public final static String STATUS_ORDER_SUCCESS = "205";
    /**
     * 提交订单失败
     */
    public final static String STATUS_ORDER_Failed = "411";
    /**
     * 库存不足
     */
    public final static String STATUS_INVENTORY_INSUFFICIENT = "410";
}
