package com.yeta.sbl2.wechat_web.domain;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/05/12:53
 */
public class List {

    private Integer Key;

    private Integer Val;

    public List() {
    }

    public List(Integer key, Integer val) {
        Key = key;
        Val = val;
    }

    public Integer getKey() {
        return Key;
    }

    public void setKey(Integer key) {
        Key = key;
    }

    public Integer getVal() {
        return Val;
    }

    public void setVal(Integer val) {
        Val = val;
    }

    @Override
    public String toString() {
        return "List{" +
                "Key=" + Key +
                ", Val=" + Val +
                '}';
    }
}
