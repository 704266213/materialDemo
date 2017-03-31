package com.app.demo.Bean;/**
 * Created by Administrator on 2016/4/20.
 */

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/20 15:20
 */
public class BannerItem extends BannerBean<BannerItem> {

    private String url;

    public BannerItem() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
