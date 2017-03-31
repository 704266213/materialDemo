package com.app.demo.Bean;

import java.util.List;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/20 15:18
 */
public class BannerBean<T extends BaseBean> extends BaseBean {

    private List<T> list;

    public BannerBean() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
