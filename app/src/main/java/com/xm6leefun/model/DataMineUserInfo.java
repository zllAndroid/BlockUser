package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class DataMineUserInfo {
    /**
     * code : 200
     * msg : OK
     * record : {"userInfo":{"coinSum":0,"headPortrait":"s","id":10,"mobile":"","nickName":"123456","userId":10}}
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
         * userInfo : {"coinSum":0,"headPortrait":"s","id":10,"mobile":"","nickName":"123456","userId":10}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * coinSum : 0
             * headPortrait : s
             * id : 10
             * mobile :
             * nickName : 123456
             * userId : 10
             */

            private String coinSum;
            private String headPortrait;
            private int id;
            private String mobile;
            private String nickName;
            private int userId;

            public String getCoinSum() {
                return coinSum;
            }

            public void setCoinSum(String coinSum) {
                this.coinSum = coinSum;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
