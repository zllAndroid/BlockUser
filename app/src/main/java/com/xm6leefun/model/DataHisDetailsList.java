package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class DataHisDetailsList {

    /**
     * code : 200
     * record : {"pageNo":1,"pageSize":10,"taskList":[{"goodsName":"82年拉菲","taskId":4,"orderNum":"Z79015555445339","orderState":2,"orderStateStr":"已发货","region":"福建省-厦门市-思明区"}],"total":1,"totalPage":1}
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
         * pageNo : 1
         * pageSize : 10
         * taskList : [{"goodsName":"82年拉菲","taskId":4,"orderNum":"Z79015555445339","orderState":2,"orderStateStr":"已发货","region":"福建省-厦门市-思明区"}]
         * total : 1
         * totalPage : 1
         */

        private int pageNo;
        private int pageSize;
        private int total;
        private int totalPage;
        private List<TaskListBean> taskList;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<TaskListBean> getTaskList() {
            return taskList;
        }

        public void setTaskList(List<TaskListBean> taskList) {
            this.taskList = taskList;
        }

        public static class TaskListBean {
            /**
             * goodsName : 82年拉菲
             * taskId : 4
             * orderNum : Z79015555445339
             * orderState : 2
             * orderStateStr : 已发货
             * region : 福建省-厦门市-思明区
             */

            private String goodsName;
            private String taskId;
            private String orderNum;
            private int orderState;
            private String orderStateStr;
            private String region;

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public int getOrderState() {
                return orderState;
            }

            public void setOrderState(int orderState) {
                this.orderState = orderState;
            }

            public String getOrderStateStr() {
                return orderStateStr;
            }

            public void setOrderStateStr(String orderStateStr) {
                this.orderStateStr = orderStateStr;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }
    }
}
