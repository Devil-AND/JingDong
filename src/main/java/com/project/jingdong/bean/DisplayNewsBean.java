package com.project.jingdong.bean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/12.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class DisplayNewsBean {

    /**
     * error : false
     * results : [{"_id":"5a685120421aa911548992ab","createdAt":"2018-01-24T17:25:52.341Z","desc":"Android 下的音乐可视化","images":["http://img.gank.io/e0d29181-282e-4465-9965-1da81e0557d9"],"publishedAt":"2018-01-29T07:53:57.676Z","source":"web","type":"Android","url":"https://github.com/nekocode/MusicVisualization","used":true,"who":"nekocode"},{"_id":"5a69608a421aa9115b9306a2","createdAt":"2018-01-25T12:43:54.642Z","desc":"插件化理解与实现 \u2014\u2014 加载 Activity「类加载篇」","images":["http://img.gank.io/a861c69f-02d2-46e8-b120-58ba4b3d97bf"],"publishedAt":"2018-01-29T07:53:57.676Z","source":"web","type":"Android","url":"https://fashare2015.github.io/2018/01/24/dynamic-load-learning-load-activity/","used":true,"who":"梁山boy"},{"_id":"5a6c2351421aa9115696004b","createdAt":"2018-01-27T14:59:29.299Z","desc":"This library generate an Mp4 movie using Android MediaCodec API and apply filter, scale, and rotate Mp4.","images":["http://img.gank.io/6fe115da-20d7-4774-8f87-0b776ec7885c"],"publishedAt":"2018-01-29T07:53:57.676Z","source":"web","type":"Android","url":"https://github.com/MasayukiSuda/Mp4Composer-android","used":true,"who":null},{"_id":"5a6da1e0421aa911548992b5","createdAt":"2018-01-28T18:11:44.726Z","desc":"使用MVP模式，基于高德地图开发，实现毛玻璃特效","publishedAt":"2018-01-29T07:53:57.676Z","source":"web","type":"Android","url":"https://github.com/JoshuaRogue/BetterWay","used":true,"who":null},{"_id":"5a6dc688421aa9115b9306ae","createdAt":"2018-01-28T20:48:08.141Z","desc":"AccessibilityService经常被黑产用来制作外挂影响正常的竞争环境，本文通过对AccessibilityService源码分析介绍其运行原理并给出相应的防御措施","publishedAt":"2018-01-29T07:53:57.676Z","source":"web","type":"Android","url":"https://lizhaoxuan.github.io/2018/01/27/AccessibilityService分析与防御/","used":true,"who":"lizhaoxuan"},{"_id":"5a5e0fbc421aa91154899281","createdAt":"2018-01-16T22:44:12.875Z","desc":"全面总结WebView遇到的坑及优化","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://www.jianshu.com/p/2b2e5d417e10","used":true,"who":"阿韦"},{"_id":"5a600bf9421aa9115b930674","createdAt":"2018-01-18T10:52:41.730Z","desc":"Android 股票图表库","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://github.com/donglua/JZAndroidChart","used":true,"who":"东东东鲁"},{"_id":"5a634c83421aa9115b93067c","createdAt":"2018-01-20T22:04:51.258Z","desc":"AndroidGodEye是一个可以在PC浏览器中实时监控Android数据指标（比如性能指标，但是不局限于性能）的工具，你可以通过wifi连接手机和pc，通过pc浏览器实时监控手机性能。","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://github.com/Kyson/AndroidGodEye","used":true,"who":"AndroidKy"},{"_id":"5a65a83f421aa91156960028","createdAt":"2018-01-22T17:00:47.798Z","desc":"WhatsNew - 自动展示更新日志的提示库","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://github.com/TonnyL/WhatsNew","used":true,"who":"黎赵太郎"},{"_id":"5a65a8f8421aa911577aa7e6","createdAt":"2018-01-22T17:03:52.479Z","desc":"用Lottie把启动界面动起来","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489401&idx=1&sn=9eecc9faa9d2dc0ce8bae6d7c45885a9","used":true,"who":"陈宇明"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5a685120421aa911548992ab
         * createdAt : 2018-01-24T17:25:52.341Z
         * desc : Android 下的音乐可视化
         * images : ["http://img.gank.io/e0d29181-282e-4465-9965-1da81e0557d9"]
         * publishedAt : 2018-01-29T07:53:57.676Z
         * source : web
         * type : Android
         * url : https://github.com/nekocode/MusicVisualization
         * used : true
         * who : nekocode
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
