package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class DataUpVersion {

    /**
     * code : 200
     * msg : OK
     * record : {"edition":{"appType":2,"createTime":1521184458000,"createTimeStr":"2018-03-16 15:14:18","editionId":2,"id":2,"isDel":0,"isForce":1,"name":"安卓","num":"2","remark":"安卓上线了","type":"android","updateTime":1521184458000,"updateTimeStr":"2018-03-16 15:14:18","url":"http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/file/app-debug.apk"}}
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
        /**
         * edition : {"appType":2,"createTime":1521184458000,"createTimeStr":"2018-03-16 15:14:18","editionId":2,"id":2,"isDel":0,"isForce":1,"name":"安卓","num":"2","remark":"安卓上线了","type":"android","updateTime":1521184458000,"updateTimeStr":"2018-03-16 15:14:18","url":"http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/file/app-debug.apk"}
         */

        private EditionBean edition;

        public EditionBean getEdition() {
            return edition;
        }

        public void setEdition(EditionBean edition) {
            this.edition = edition;
        }

        public static class EditionBean {
            /**
             * appType : 2
             * createTime : 1521184458000
             * createTimeStr : 2018-03-16 15:14:18
             * editionId : 2
             * id : 2
             * isDel : 0
             * isForce : 1
             * name : 安卓
             * num : 2
             * remark : 安卓上线了
             * type : android
             * updateTime : 1521184458000
             * updateTimeStr : 2018-03-16 15:14:18
             * url : http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/file/app-debug.apk
             */

            private int appType;
            private long createTime;
            private String createTimeStr;
            private int editionId;
            private int id;
            private int isDel;
            private String isForce;
            private String name;
            private int num;
            private String remark;
            private String type;
            private long updateTime;
            private String updateTimeStr;
            private String url;

            public int getAppType() {
                return appType;
            }

            public void setAppType(int appType) {
                this.appType = appType;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCreateTimeStr() {
                return createTimeStr;
            }

            public void setCreateTimeStr(String createTimeStr) {
                this.createTimeStr = createTimeStr;
            }

            public int getEditionId() {
                return editionId;
            }

            public void setEditionId(int editionId) {
                this.editionId = editionId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getIsForce() {
                return isForce;
            }

            public void setIsForce(String isForce) {
                this.isForce = isForce;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateTimeStr() {
                return updateTimeStr;
            }

            public void setUpdateTimeStr(String updateTimeStr) {
                this.updateTimeStr = updateTimeStr;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
