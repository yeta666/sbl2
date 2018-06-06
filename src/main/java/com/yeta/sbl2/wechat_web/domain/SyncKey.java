package com.yeta.sbl2.wechat_web.domain;

import java.util.Arrays;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/05/12:52
 */
public class SyncKey {

    private Integer Count;

    private List[] List;

    public SyncKey() {
    }

    public SyncKey(Integer count, com.yeta.sbl2.wechat_web.domain.List[] list) {
        Count = count;
        List = list;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public com.yeta.sbl2.wechat_web.domain.List[] getList() {
        return List;
    }

    public void setList(com.yeta.sbl2.wechat_web.domain.List[] list) {
        List = list;
    }

    @Override
    public String toString() {
        return "SyncKey{" +
                "Count=" + Count +
                ", List=" + Arrays.toString(List) +
                '}';
    }
}
