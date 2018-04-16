package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class DataHistoryDetails {
    /**
     * code : 200
     * msg : OK
     * record : {"orderExpress":{"datas":[{"context":"在官网运单资料&签收图,可查看签收人信息","ftime":"2018-02-27 09:43:00","location":"","time":"2018-02-27 09:43:00"},{"context":"已签收,感谢使用顺丰,期待再次为您服务","ftime":"2018-02-27 09:42:00","location":"","time":"2018-02-27 09:42:00"},{"context":"快件交给刘雄波，正在派送途中（联系电话：18950113161）","ftime":"2018-02-27 08:05:00","location":"","time":"2018-02-27 08:05:00"},{"context":"快件到达 【厦门湖里塔埔营业点】","ftime":"2018-02-27 06:50:00","location":"","time":"2018-02-27 06:50:00"},{"context":"快件在【厦门集美集散中心】装车，已发往 【厦门湖里塔埔营业点】","ftime":"2018-02-26 20:10:00","location":"","time":"2018-02-26 20:10:00"},{"context":"快件到达 【厦门集美集散中心】","ftime":"2018-02-26 18:50:00","location":"","time":"2018-02-26 18:50:00"},{"context":"快件在【泉州晋江集散中心】装车，已发往 【厦门集美集散中心】","ftime":"2018-02-26 16:19:00","location":"","time":"2018-02-26 16:19:00"},{"context":"快件到达 【泉州晋江集散中心】","ftime":"2018-02-26 15:56:00","location":"","time":"2018-02-26 15:56:00"},{"context":"快件在【金华义乌集散中心】装车，已发往下一站","ftime":"2018-02-25 23:56:00","location":"","time":"2018-02-25 23:56:00"},{"context":"快件到达 【金华义乌集散中心】","ftime":"2018-02-25 19:06:00","location":"","time":"2018-02-25 19:06:00"},{"context":"快件在【金华义乌大陈营业点】装车，已发往 【金华义乌集散中心】","ftime":"2018-02-25 18:03:00","location":"","time":"2018-02-25 18:03:00"},{"context":"顺丰速运 已收取快件","ftime":"2018-02-25 17:38:00","location":"","time":"2018-02-25 17:38:00"}],"expressId":1,"expressNum":"457453757376"},"taskData":{"buyNum":1,"details":"观音山10号楼","goodsName":"贵州茅台酒 53度","name":"戴先生","orderAddressId":32,"orderNum":"Z07612272635374","phone":"15559068280","region":"福建省-厦门市-思明区","taskId":12}}
     */

    private int code;
    private String msg;
    private RecordBean record;

//    {     "code": 200,     "msg": "OK",     "record": {         "orderExpress": {             "datas": [{"context":"在官网运单资料&签收图,可查看签收人信息","ftime":"2018-02-27 09:43:00","location":"","time":"2018-02-27 09:43:00"},{"context":"已签收,感谢使用顺丰,期待再次为您服务","ftime":"2018-02-27 09:42:00","location":"","time":"2018-02-27 09:42:00"},{"context":"快件交给刘雄波，正在派送途中（联系电话：18950113161）","ftime":"2018-02-27 08:05:00","location":"","time":"2018-02-27 08:05:00"},{"context":"快件到达 【厦门湖里塔埔营业点】","ftime":"2018-02-27 06:50:00","location":"","time":"2018-02-27 06:50:00"},{"context":"快件在【厦门集美集散中心】装车，已发往 【厦门湖里塔埔营业点】","ftime":"2018-02-26 20:10:00","location":"","time":"2018-02-26 20:10:00"},{"context":"快件到达 【厦门集美集散中心】","ftime":"2018-02-26 18:50:00","location":"","time":"2018-02-26 18:50:00"},{"context":"快件在【泉州晋江集散中心】装车，已发往 【厦门集美集散中心】","ftime":"2018-02-26 16:19:00","location":"","time":"2018-02-26 16:19:00"},{"context":"快件到达 【泉州晋江集散中心】","ftime":"2018-02-26 15:56:00","location":"","time":"2018-02-26 15:56:00"},{"context":"快件在【金华义乌集散中心】装车，已发往下一站","ftime":"2018-02-25 23:56:00","location":"","time":"2018-02-25 23:56:00"},{"context":"快件到达 【金华义乌集散中心】","ftime":"2018-02-25 19:06:00","location":"","time":"2018-02-25 19:06:00"},{"context":"快件在【金华义乌大陈营业点】装车，已发往 【金华义乌集散中心】","ftime":"2018-02-25 18:03:00","location":"","time":"2018-02-25 18:03:00"},{"context":"顺丰速运 已收取快件","ftime":"2018-02-25 17:38:00","location":"","time":"2018-02-25 17:38:00"}],             "expressId": 1,             "expressNum": "457453757376"         },         "taskData": {             "buyNum": 1,             "details": "观音山10号楼",             "goodsName": "贵州茅台酒 53度",             "name": "戴先生",             "orderAddressId": 32,             "orderNum": "Z07612272635374",             "phone": "15559068280",             "region": "福建省-厦门市-思明区",             "taskId": 12         }     } }

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
         * orderExpress : {"datas":[{"context":"在官网运单资料&签收图,可查看签收人信息","ftime":"2018-02-27 09:43:00","location":"","time":"2018-02-27 09:43:00"},{"context":"已签收,感谢使用顺丰,期待再次为您服务","ftime":"2018-02-27 09:42:00","location":"","time":"2018-02-27 09:42:00"},{"context":"快件交给刘雄波，正在派送途中（联系电话：18950113161）","ftime":"2018-02-27 08:05:00","location":"","time":"2018-02-27 08:05:00"},{"context":"快件到达 【厦门湖里塔埔营业点】","ftime":"2018-02-27 06:50:00","location":"","time":"2018-02-27 06:50:00"},{"context":"快件在【厦门集美集散中心】装车，已发往 【厦门湖里塔埔营业点】","ftime":"2018-02-26 20:10:00","location":"","time":"2018-02-26 20:10:00"},{"context":"快件到达 【厦门集美集散中心】","ftime":"2018-02-26 18:50:00","location":"","time":"2018-02-26 18:50:00"},{"context":"快件在【泉州晋江集散中心】装车，已发往 【厦门集美集散中心】","ftime":"2018-02-26 16:19:00","location":"","time":"2018-02-26 16:19:00"},{"context":"快件到达 【泉州晋江集散中心】","ftime":"2018-02-26 15:56:00","location":"","time":"2018-02-26 15:56:00"},{"context":"快件在【金华义乌集散中心】装车，已发往下一站","ftime":"2018-02-25 23:56:00","location":"","time":"2018-02-25 23:56:00"},{"context":"快件到达 【金华义乌集散中心】","ftime":"2018-02-25 19:06:00","location":"","time":"2018-02-25 19:06:00"},{"context":"快件在【金华义乌大陈营业点】装车，已发往 【金华义乌集散中心】","ftime":"2018-02-25 18:03:00","location":"","time":"2018-02-25 18:03:00"},{"context":"顺丰速运 已收取快件","ftime":"2018-02-25 17:38:00","location":"","time":"2018-02-25 17:38:00"}],"expressId":1,"expressNum":"457453757376"}
         * taskData : {"buyNum":1,"details":"观音山10号楼","goodsName":"贵州茅台酒 53度","name":"戴先生","orderAddressId":32,"orderNum":"Z07612272635374","phone":"15559068280","region":"福建省-厦门市-思明区","taskId":12}
         */

        private OrderExpressBean orderExpress;
        private TaskDataBean taskData;

        public OrderExpressBean getOrderExpress() {
            return orderExpress;
        }

        public void setOrderExpress(OrderExpressBean orderExpress) {
            this.orderExpress = orderExpress;
        }

        public TaskDataBean getTaskData() {
            return taskData;
        }

        public void setTaskData(TaskDataBean taskData) {
            this.taskData = taskData;
        }

        public static class OrderExpressBean {
            /**
             * datas : [{"context":"在官网运单资料&签收图,可查看签收人信息","ftime":"2018-02-27 09:43:00","location":"","time":"2018-02-27 09:43:00"},{"context":"已签收,感谢使用顺丰,期待再次为您服务","ftime":"2018-02-27 09:42:00","location":"","time":"2018-02-27 09:42:00"},{"context":"快件交给刘雄波，正在派送途中（联系电话：18950113161）","ftime":"2018-02-27 08:05:00","location":"","time":"2018-02-27 08:05:00"},{"context":"快件到达 【厦门湖里塔埔营业点】","ftime":"2018-02-27 06:50:00","location":"","time":"2018-02-27 06:50:00"},{"context":"快件在【厦门集美集散中心】装车，已发往 【厦门湖里塔埔营业点】","ftime":"2018-02-26 20:10:00","location":"","time":"2018-02-26 20:10:00"},{"context":"快件到达 【厦门集美集散中心】","ftime":"2018-02-26 18:50:00","location":"","time":"2018-02-26 18:50:00"},{"context":"快件在【泉州晋江集散中心】装车，已发往 【厦门集美集散中心】","ftime":"2018-02-26 16:19:00","location":"","time":"2018-02-26 16:19:00"},{"context":"快件到达 【泉州晋江集散中心】","ftime":"2018-02-26 15:56:00","location":"","time":"2018-02-26 15:56:00"},{"context":"快件在【金华义乌集散中心】装车，已发往下一站","ftime":"2018-02-25 23:56:00","location":"","time":"2018-02-25 23:56:00"},{"context":"快件到达 【金华义乌集散中心】","ftime":"2018-02-25 19:06:00","location":"","time":"2018-02-25 19:06:00"},{"context":"快件在【金华义乌大陈营业点】装车，已发往 【金华义乌集散中心】","ftime":"2018-02-25 18:03:00","location":"","time":"2018-02-25 18:03:00"},{"context":"顺丰速运 已收取快件","ftime":"2018-02-25 17:38:00","location":"","time":"2018-02-25 17:38:00"}]
             * expressId : 1
             * expressNum : 457453757376
             */

            private String expressId;
            private String expressNum;
            private List<ExpressDataBean> expressData;

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

            public List<ExpressDataBean> getExpressData() {
                return expressData;
            }

            public void setExpressData(List<ExpressDataBean> expressData) {
                this.expressData = expressData;
            }

            public static class ExpressDataBean {
                /**
                 * context : 在官网运单资料&签收图,可查看签收人信息
                 * ftime : 2018-02-27 09:43:00
                 * location :
                 * time : 2018-02-27 09:43:00
                 */

                private String context;
                private String ftime;
                private String location;
                private String time;

                public String getContext() {
                    return context;
                }

                public void setContext(String context) {
                    this.context = context;
                }

                public String getFtime() {
                    return ftime;
                }

                public void setFtime(String ftime) {
                    this.ftime = ftime;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }
        }

        public static class TaskDataBean {
            /**
             * buyNum : 1
             * details : 观音山10号楼
             * goodsName : 贵州茅台酒 53度
             * name : 戴先生
             * orderAddressId : 32
             * orderNum : Z07612272635374
             * phone : 15559068280
             * region : 福建省-厦门市-思明区
             * taskId : 12
             */

            private int buyNum;
            private String details;
            private String goodsName;
            private String name;
            private int orderAddressId;
            private String orderNum;
            private String phone;
            private String region;
            private int taskId;

            public int getBuyNum() {
                return buyNum;
            }

            public void setBuyNum(int buyNum) {
                this.buyNum = buyNum;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrderAddressId() {
                return orderAddressId;
            }

            public void setOrderAddressId(int orderAddressId) {
                this.orderAddressId = orderAddressId;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }
        }
    }
}
