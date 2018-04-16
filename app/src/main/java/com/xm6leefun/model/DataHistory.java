package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class DataHistory {
    /**
     * code : 200
     * data : {"pageNo":1,"pageSize":10,"taskList":[{"createTimeStr":"2018-02-07","finishNum":1,"sumNum":3},{"createTimeStr":"2018-02-06","finishNum":1,"sumNum":1}],"total":2,"totalPage":1}
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
         * taskList : [{"createTimeStr":"2018-02-07","finishNum":1,"sumNum":3},{"createTimeStr":"2018-02-06","finishNum":1,"sumNum":1}]
         * total : 2
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
             * createTimeStr : 2018-02-07
             * finishNum : 1
             * sumNum : 3
             */

            private String createTimeStr;
            private String finishNum;
            private String sumNum;

            public String getCreateTimeStr() {
                return createTimeStr;
            }

            public void setCreateTimeStr(String createTimeStr) {
                this.createTimeStr = createTimeStr;
            }

            public String getFinishNum() {
                return finishNum;
            }

            public void setFinishNum(String finishNum) {
                this.finishNum = finishNum;
            }

            public String getSumNum() {
                return sumNum;
            }

            public void setSumNum(String sumNum) {
                this.sumNum = sumNum;
            }
        }
    }
}
