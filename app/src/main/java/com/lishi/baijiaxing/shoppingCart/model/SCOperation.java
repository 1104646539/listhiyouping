package com.lishi.baijiaxing.shoppingCart.model;

/**购物车操作返回类
 * Created by Administrator on 2016/12/5.
 */

public class SCOperation {

    /**
     * status : 204   409   
     * msg : 操作成功  失败
     * {
     "status": "413",
     "msg": "订单不存在"
     }
     {
     "status": "414",
     "msg": "重复下单"
     }
     {
     "status": "415",
     "msg": "用户不存在"
     }
     {
     "status": "416",
     "msg": "openid不存在"
     }
     */

    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
