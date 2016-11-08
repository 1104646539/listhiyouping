package com.lishi.baijiaxing.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class PhotosBean {
    private List<String> photos;

    public PhotosBean(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
