package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class CustomizeBean extends BaseBean {
    private String advertisementUrl1;
    private String advertisementUrl2;
    private List<Integer> advertisementUrls;

    public CustomizeBean() {
    }

    public CustomizeBean(String advertisementUrl1, String advertisementUrl2, List<Integer> advertisementUrls) {
        this.advertisementUrl1 = advertisementUrl1;
        this.advertisementUrl2 = advertisementUrl2;
        this.advertisementUrls = advertisementUrls;
    }

    public String getAdvertisementUrl1() {
        return advertisementUrl1;
    }

    public void setAdvertisementUrl1(String advertisementUrl1) {
        this.advertisementUrl1 = advertisementUrl1;
    }

    public String getAdvertisementUrl2() {
        return advertisementUrl2;
    }

    public void setAdvertisementUrl2(String advertisementUrl2) {
        this.advertisementUrl2 = advertisementUrl2;
    }

    public List<Integer> getAdvertisementUrls() {
        return advertisementUrls;
    }

    public void setAdvertisementUrls(List<Integer> advertisementUrls) {
        this.advertisementUrls = advertisementUrls;
    }
}
