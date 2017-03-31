package com.app.demo.response;/**
 * Created by Administrator on 2016/4/20.
 */

import com.alibaba.fastjson.JSON;
import com.app.demo.Bean.BaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/20 15:15
 */
public abstract class BaseParse<T extends BaseBean> implements Runnable{

    private Type type;

    public BaseParse() {
        type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println("=======type==============" + type);
    }

    public T parseData(String result) {
        T t = (T) JSON.parseObject(result, type);
        System.out.println("=successHandler==retcode===============" + t.retcode);
        System.out.println("==successHandler=retmsg===============" + t.retmsg);
        return t;
    }

}
