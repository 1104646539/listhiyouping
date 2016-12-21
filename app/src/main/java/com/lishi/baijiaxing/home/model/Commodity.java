package com.lishi.baijiaxing.home.model;

import java.util.List;

/**
 * 首页商品列表
 * Created by Administrator on 2016/11/23.
 */

public class Commodity {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"cid":"150","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_582ec80aa25be.jpg","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"154","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_582ec7cb4be66.jpg","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"155","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_582ec8ab491c3.jpg","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"6363","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_583546de68bcc.jpg","name":"   悦读时光 每日7:00-8:00，限时1元抢购，每日200个名额。其它时段购买，价格199元！","oldPrice":"168.00","nowPrice":"98.00"},{"cid":"6298","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58354a0e06539.JPG","name":"虎符龙节套装  极速上传下载 高大上龙节笔 公虎符龙节套装 包邮","oldPrice":"208.00","nowPrice":"128.00"},{"cid":"6354","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c1eede472a.jpg","name":"福至心灵茶具套装 高档礼盒装茶杯 时尚漂亮礼盒礼品茶杯套装带茶叶罐","oldPrice":"208.00","nowPrice":"158.00"},{"cid":"141","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/%E5%B0%8F%E6%B5%B7%E6%8A%A5.png","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"6395","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","nowPrice":"59.90","oldPrice":"159.90"},{"cid":"6373","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823e40a70029.jpg","name":"太平吉象抱枕被 保险公司的礼品抱枕被鸡年靠枕福字抱枕被 LOGO定制","nowPrice":"35.00","oldPrice":"78.00"},{"cid":"6369","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5822ce189d665.jpg","name":"爱华仕360°全方位电脑保护双肩包 时尚撞色大容量韩版旅行休闲双肩背包","nowPrice":"79.00","oldPrice":"159.00"},{"cid":"6314","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809df18a250a.jpg","name":"开心一家饭勺套装|不锈钢家庭饭勺套装","nowPrice":"23.50","oldPrice":"28.20"},{"cid":"6303","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e13462b5f.jpg","name":"花艺暖茶 玫瑰组合花茶红巧梅金盏菊茉莉花贡菊花女性美容养颜养生花草茶","nowPrice":"98.00","oldPrice":"118.00"},{"cid":"6298","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58354a0e06539.JPG","name":"虎符龙节套装  极速上传下载 高大上龙节笔 公虎符龙节套装 包邮","nowPrice":"168.00","oldPrice":"208.00"},{"cid":"145","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_580d7221dadf4.png","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"6278","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809db60bfe01.jpg","name":"招财进宝杯定制 刻字全手工 正品办公杯茶具 定制个人杯茶杯500ml","nowPrice":"286.00","oldPrice":"348.00"},{"cid":"6279","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dc48ab669.jpg","name":"生日纪念日同号钞珍藏纪念册 送亲朋好友礼品 包邮","nowPrice":"490.00","oldPrice":"766.00"},{"cid":"6298","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58354a0e06539.JPG","name":"虎符龙节套装  极速上传下载 高大上龙节笔 公虎符龙节套装 包邮","nowPrice":"168.00","oldPrice":"208.00"},{"cid":"6333","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823d72902939.jpg","name":"56°恒温紫砂杯CEO杯 养生杯 办公室专用茶杯 全手工制作高端定制礼品","nowPrice":"398.00","oldPrice":"478.00"},{"cid":"144","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_580d720de521b.png","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"6299","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823d7fc98b3d.jpg","name":"创意书灯折叠书灯 阅读灯 定制送情侣礼物 毕业礼物 生日礼物 包邮","nowPrice":"188.00","oldPrice":"256.00"},{"cid":"6311","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d4eb817de4.jpg","name":"  LED月光灯简约现代客厅灯卧室灯艾普达装饰灯 手机阅读护眼灯","nowPrice":"280.00","oldPrice":"336.00"},{"cid":"6331","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582a85a9d85e4.jpg","name":"无线手机U盘  iphone急速上传下载 高速WIFI传输U盘32G","nowPrice":"299.00","oldPrice":"358.80"},{"cid":"6351","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582aa1493be4c.jpg","name":"办公用品虎符U盘 极速上传下载创意U盘 车载U盘包邮","nowPrice":"68.00","oldPrice":"128.00"},{"cid":"146","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_580d71bf47693.png","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"6337","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809ddb357b7b.png","name":"高山壶组复古粗陶 粗陶整套茶具 养生壶墨绿 高档礼盒 包邮","nowPrice":"360.00","oldPrice":"432.00"},{"cid":"6319","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809daee7fab6.jpg","name":"福气多多三件套 茶杯 茶叶罐 醒茶罐 普洱茶叶罐 茶匙三合一套装 包邮","nowPrice":"1213.00","oldPrice":"1455.00"},{"cid":"6304","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809db940f4ae.JPG","name":"乐道纯锡茶叶罐 纯锡坊 纯锡密封保鲜罐 纯锡手工茶叶罐 定制茶叶罐","nowPrice":"328.00","oldPrice":"526.80"},{"cid":"6292","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dbf8d7a21.JPG","name":"禅定純锡茶叶罐 纯锡坊 醒茶罐 禅文化高档茶具礼品 送领导长辈 包邮","nowPrice":"378.00","oldPrice":"570.00"},{"cid":"6289","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dbb22eeae.JPG","name":"太极功夫茶具套装高档潮流原初格物 陶瓷茶具 商务礼品 包邮","nowPrice":"588.00","oldPrice":"700.00"},{"cid":"6277","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dc5c63567.jpg","name":"澳洲白泥套组  白品茶忆友陶壶 烧水茶壶泡茶壶茶道专用壶小茶壶套装","nowPrice":"3318.00","oldPrice":"3981.00"},{"cid":"146","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_580d71bf47693.png","name":"测试","oldPrice":"测试","nowPrice":"测试"},{"cid":"6420","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c21a6e79a8.jpg","name":"新品CHOOCI轻薄旅行收纳七件套 旅行出差必备收纳包","nowPrice":"145.00","oldPrice":"199.00"},{"cid":"6421","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c2299261ef.jpg","name":"新品雅哲英伦洗漱包 旅游商务差旅加厚防水洗漱收纳包","nowPrice":"51.00","oldPrice":"128.00"},{"cid":"6422","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c2385617b0.jpg","name":"新品 CHOOCI轻薄旅行包收纳四件套 衣物收纳包鞋便携洗漱包旅行必备","nowPrice":"105.00","oldPrice":"129.00"},{"cid":"6423","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c28e398091.jpg","name":"新品瑞士军刀SA-5008时尚商务男士单肩斜跨帆布包","nowPrice":"84.00","oldPrice":"128.00"},{"cid":"6424","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582c2de2a30a1.jpg","name":"新品SWISSGEAR/SA-6220 万向轮商务时尚拉杆箱旅行密码行李箱","nowPrice":"263.00","oldPrice":"328.00"},{"cid":"6425","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d0cac67285.jpg","name":"新品瑞士军刀SA-7318男女户外休闲旅行背包  抗震导汗、减压舒适防水双肩包","nowPrice":"72.00","oldPrice":"119.00"}]
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
         * cid : 150
         * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_582ec80aa25be.jpg
         * name : 测试
         * oldPrice : 测试
         * nowPrice : 测试
         */

        private String gid;
        private String photoUrl;
        private String name;
        private String oldPrice;
        private String nowPrice;

        public String getCid() {
            return gid;
        }

        public void setCid(String gid) {
            this.gid = gid;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getNowPrice() {
            return nowPrice;
        }

        public void setNowPrice(String nowPrice) {
            this.nowPrice = nowPrice;
        }
    }
}
