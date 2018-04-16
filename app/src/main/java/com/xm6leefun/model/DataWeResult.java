package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class DataWeResult {
    /**
     * code : 200
     * msg : OK
     * record : {"userInfo":{"coinSum":0,"headPortrait":"","id":14,"mobile":"","nickName":"123456","token":"e11e62a7181078426f9ee69c46dcfa87","userId":14}}
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
         * userInfo : {"coinSum":0,"headPortrait":"","id":14,"mobile":"","nickName":"123456","token":"e11e62a7181078426f9ee69c46dcfa87","userId":14}
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
             * headPortrait :
             * id : 14
             * mobile :
             * nickName : 123456
             * token : e11e62a7181078426f9ee69c46dcfa87
             * userId : 14
             */

            private String coinSum;
            private String headPortrait;
            private int id;
            private String mobile;
            private String nickName;
            private String token;
            private String userId;

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

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
