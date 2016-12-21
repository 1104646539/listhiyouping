package com.lishi.baijiaxing.classify.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ClassOne {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"categoryName":"自营推荐","categoryGoodsList":[{"name":"尚雅保温壶 爱奇屋A1-12 1L不锈钢真空咖啡壶 保温壶 户外旅游壶","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d7662193c.jpg","cid":"6359"},{"name":"开门红保温杯 爱奇屋A1-B350不锈钢真空500ML水杯旅行杯","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d79e09f5c.jpg","cid":"6358"}]},{"categoryName":"热门","categoryGoodsList":[{"name":"新品苏泊尔球釜柴火饭电饭煲4L家用智能预约电饭锅","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582e5e0bec4a4.jpg","cid":"6450"},{"name":"新品 智利进口红酒原装原瓶红葡萄酒庄园赤霞珠干红6支装整箱","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d23f4a74a6.jpg","cid":"6431"},{"name":"新品SWISSGEAR/SA-6220 万向轮商务时尚拉杆箱旅行密码行李箱","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c2de2a30a1.jpg","cid":"6424"},{"name":"新品喜芙妮爱家艾格床上用品 床上床单被套四件套","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c06e8c58fb.jpg","cid":"6409"},{"name":"新品云之怡高档护颈天鹅绒波浪枕 慢回弹记忆枕","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c03b62a075.jpg","cid":"6408"},{"name":"新品奥拉芙费丽妮法莱绒毯秋冬季加厚单双人法莱绒毯子","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582bfd117c3cc.jpg","cid":"6407"},{"name":"2017鸡年批发定制中国太平保险台历专版太平保险月历日历新年礼品","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582ab3d6d15bd.JPG","cid":"6405"},{"name":"新品爱奇屋心飞扬保温便携不锈钢壶户外旅行保温杯","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582988b772bb5.jpg","cid":"6404"},{"name":"新品舞动阳光保温壶套装 爱奇屋AI-061保冷真空便携杯壶套装","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582986494626d.jpg","cid":"6403"},{"name":"新品 高品质水分子杯壶套装 爱奇屋AI-059真空不锈钢户外运动水壶套装","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5829831758dd3.jpg","cid":"6402"},{"name":"新品尚雅保温壶 爱奇屋AI-L26不锈钢大容量家用保温壶","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58297c27e4fa5.jpg","cid":"6401"},{"name":"新品柯林随手杯玻璃 澳州SO.homeC142-40创意便携带竹木随手杯","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582977beb9e8f.jpg","cid":"6400"}]}]
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

    public static class DataBean {
        /**
         * categoryName : 自营推荐
         * categoryGoodsList : [{"name":"尚雅保温壶 爱奇屋A1-12 1L不锈钢真空咖啡壶 保温壶 户外旅游壶","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d7662193c.jpg","cid":"6359"},{"name":"开门红保温杯 爱奇屋A1-B350不锈钢真空500ML水杯旅行杯","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d79e09f5c.jpg","cid":"6358"}]
         */

        private String categoryName;
        private List<CategoryGoodsListBean> categoryGoodsList;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<CategoryGoodsListBean> getCategoryGoodsList() {
            return categoryGoodsList;
        }

        public void setCategoryGoodsList(List<CategoryGoodsListBean> categoryGoodsList) {
            this.categoryGoodsList = categoryGoodsList;
        }

        public static class CategoryGoodsListBean {
            /**
             * name : 尚雅保温壶 爱奇屋A1-12 1L不锈钢真空咖啡壶 保温壶 户外旅游壶
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d7662193c.jpg
             * cid : 6359
             */

            private String name;
            private String photoUrl;
            private String gid;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
            }

            public String getCid() {
                return gid;
            }

            public void setCid(String gid) {
                this.gid = gid;
            }
        }
    }
}
