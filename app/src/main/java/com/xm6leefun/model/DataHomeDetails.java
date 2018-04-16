package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class DataHomeDetails {
    /**
     * code : 200
     * data : {"taskData":{"buyNum":2,"details":"观音山10号楼","goodsName":"82年拉菲","name":"李先生","orderNum":"Z79913228931901","phone":"13358585858","region":"福建省-厦门市-思明区","taskId":6}}
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
         * taskData : {"buyNum":2,"details":"观音山10号楼","goodsName":"82年拉菲","name":"李先生","orderNum":"Z79913228931901","phone":"13358585858","region":"福建省-厦门市-思明区","taskId":6}
         */

        private TaskRecordBean taskData;

        public TaskRecordBean getTaskData() {
            return taskData;
        }

        public void setTaskData(TaskRecordBean taskData) {
            this.taskData = taskData;
        }

        public static class TaskRecordBean {
            /**
             * buyNum : 2
             * details : 观音山10号楼
             * goodsName : 82年拉菲
             * name : 李先生
             * orderNum : Z79913228931901
             * phone : 13358585858
             * region : 福建省-厦门市-思明区
             * taskId : 6
             */

            private String buyNum;
            private String details;
            private String goodsName;
            private String name;
            private String orderNum;
            private String phone;
            private String region;
            private String taskId;

            public String getBuyNum() {
                return buyNum;
            }

            public void setBuyNum(String buyNum) {
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

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }
        }
    }
}
