package com.lishi.baijiaxing.search.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class HotSearch {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"gid":"6379","name":"真空炒锅不粘锅无油烟锅铁锅 电磁炉通用平底锅 厨房锅具"}]
     */

    private String status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * gid : 6379
         * name : 真空炒锅不粘锅无油烟锅铁锅 电磁炉通用平底锅 厨房锅具
         */

        private String gid;
        private String name;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.gid);
            dest.writeString(this.name);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.gid = in.readString();
            this.name = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
