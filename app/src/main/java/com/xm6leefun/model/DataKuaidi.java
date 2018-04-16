package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class DataKuaidi {
    /**
     * code : 200
     * msg : OK
     * record : {"express":[{"code":"23","createTime":1520409394000,"id":1,"isDel":0,"name":"中通","updateTime":1520409394000}]}
     */

    private int code;
    private String msg;
    private RecordBean record;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public static class RecordBean {
        private List<ExpressBean> express;

        public List<ExpressBean> getExpress() {
            return express;
        }

        public void setExpress(List<ExpressBean> express) {
            this.express = express;
        }

        public static class ExpressBean {
            /**
             * code : 23
             * createTime : 1520409394000
             * id : 1
             * isDel : 0
             * name : 中通
             * updateTime : 1520409394000
             */

            private String code;
            private long createTime;
            private String id;
            private int isDel;
            private String name;
            private long updateTime;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
