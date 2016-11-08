package com.lishi.baijiaxing.wxapi.model;

/**
 * Created by Administrator on 2016/11/8.
 */
public class WXTokenBean {

    /**
     * access_token : SrWSdpokgRnQocfMgZjKhwJBvN0kzQXIzzLaNHOKXdXcB8OPyOFEYSavnAabn6wIjTpJ9Hb6FKWuNGEdaGHdGxZFPSquB1ztOFJ7jcsxDEI
     * expires_in : 7200
     * refresh_token : ZA2W0vqDs9VFCV689git9TW8gj-KS38MNYn_z3QDtOkfK6CuTWzNAQG12yNA64X6Yk8TbW-Es3zDQxVQNi_pStze-GHvj3OpeWQLx9mvg3w
     * openid : oJxPov545ophiaP1U7H3uEDXWZpY
     * scope : snsapi_userinfo
     * unionid : ofeYCxCaqOj336CvB6cmpgVR5sAI
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return openid.toString();
    }
}
