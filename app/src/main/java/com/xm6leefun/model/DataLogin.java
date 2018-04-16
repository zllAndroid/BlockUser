package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class DataLogin {
    /**
     * code : 200
     * data : {"shippingClerk":{"companyId":0,"createTime":1517822826000,"id":1,"isDel":0,"jobNum":"00000001","mobile":"15559068580","pwd":"","realName":"afsd","token":"edc9de998119f21e3be157a9abd506b3","tokenId":1,"updateTime":1518058567000}}
     * msg : OK
     */
    private int code;
    private RecordBean record;
    private String msg;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RecordBean {
        /**
         * shippingClerk : {"companyId":0,"createTime":1517822826000,"id":1,"isDel":0,"jobNum":"00000001","mobile":"15559068580","pwd":"","realName":"afsd","token":"edc9de998119f21e3be157a9abd506b3","tokenId":1,"updateTime":1518058567000}
         */

        private ShippingClerkBean shippingClerk;

        public ShippingClerkBean getShippingClerk() {
            return shippingClerk;
        }

        public void setShippingClerk(ShippingClerkBean shippingClerk) {
            this.shippingClerk = shippingClerk;
        }

        public static class ShippingClerkBean {
            /**
             * companyId : 0
             * createTime : 1517822826000
             * id : 1
             * isDel : 0
             * jobNum : 00000001
             * mobile : 15559068580
             * pwd :
             * realName : afsd
             * token : edc9de998119f21e3be157a9abd506b3
             * tokenId : 1
             * updateTime : 1518058567000
             */

            private int companyId;
            private long createTime;
            private String id;
            private int isDel;
            private String jobNum;
            private String mobile;
            private String pwd;
            private String realName;
            private String token;
            private int tokenId;
            private long updateTime;

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
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

            public String getJobNum() {
                return jobNum;
            }

            public void setJobNum(String jobNum) {
                this.jobNum = jobNum;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getTokenId() {
                return tokenId;
            }

            public void setTokenId(int tokenId) {
                this.tokenId = tokenId;
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
