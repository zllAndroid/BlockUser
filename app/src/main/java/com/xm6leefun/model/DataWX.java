package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

class DataWX {
    /**
     * openid : obidI1Ako6JgAtogJi2zaN7W0BHo
     * nickname : 孩子气
     * sex : 1
     * language : zh_CN
     * city : Quanzhou
     * province : Fujian
     * country : CN
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKTaK9MlElLuReAMrxPD9yuk7E5aydc3rLMJ27iao8RmR3rISuQ1aZXwBSEn6MBFyAibIH25RvBKocQ/132
     * privilege : []
     * unionid : oZDGz07qqAw_xdAVZYzqOBS8TTUs
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
