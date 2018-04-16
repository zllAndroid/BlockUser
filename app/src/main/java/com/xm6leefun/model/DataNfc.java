package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class DataNfc {
    /**
     * code : 200
     * msg : OK
     * record : {"flag":"0","nfcUid":"2343243242343","nfcUrl":"23423423432423","nfcCode":"73683b82984744c18321596f382eda28"}
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
         * flag : 0
         * nfcUid : 2343243242343
         * nfcUrl : 23423423432423
         * nfcCode : 73683b82984744c18321596f382eda28
         */

        private String flag;
        private String nfcUid;
        private String nfcUrl;
        private String nfcCode;
        private String nfcMsg;

        public String getNfcMsg() {
            return nfcMsg;
        }

        public void setNfcMsg(String nfcMsg) {
            this.nfcMsg = nfcMsg;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getNfcUid() {
            return nfcUid;
        }

        public void setNfcUid(String nfcUid) {
            this.nfcUid = nfcUid;
        }

        public String getNfcUrl() {
            return nfcUrl;
        }

        public void setNfcUrl(String nfcUrl) {
            this.nfcUrl = nfcUrl;
        }

        public String getNfcCode() {
            return nfcCode;
        }

        public void setNfcCode(String nfcCode) {
            this.nfcCode = nfcCode;
        }
    }
}
