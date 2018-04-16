package com.xm6leefun.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public  class RealmTaskResule extends RealmObject {

    /**
     * goodsName : 82年拉菲
     * orderNum : Z79913228931901
     * orderState : 1
     * orderStateStr : 待发货
     * region : 福建省-，厦门市-，思明区
     */
    @PrimaryKey
    private String qrcode;
    private String nfccode;
    private String nfcUid;
    private int num;
    private String expressId;
    private String expressNum;

    private String taskId;

    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    public String getNfcUid() {
        return nfcUid;
    }

    public void setNfcUid(String nfcUid) {
        this.nfcUid = nfcUid;
    }
    public String getNfccode() {
        return nfccode;
    }

    public void setNfccode(String nfccode) {
        this.nfccode = nfccode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
