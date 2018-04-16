package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class DataScan {
    /**
     * code : 200
     * record : {"flag":"1","qrMsg":"该二维码已被使用了！"}
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
         * flag : 1
         * qrMsg : 该二维码已被使用了！
         */

        private String flag;
        private String qrMsg;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getQrMsg() {
            return qrMsg;
        }

        public void setQrMsg(String qrMsg) {
            this.qrMsg = qrMsg;
        }
    }
}
