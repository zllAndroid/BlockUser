package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class DataAlreadyJiHuo {
    /**
     * code : 200
     * msg : OK
     * record : {"activateList":[{"blockNum":10,"companyName":"厦门六立方","goodsImage":"321312","goodsName":"白酒","paymentTime":1520913420000,"paymentTimeStr":"11:57:00","receiverMobile":"138XXXXXX86","receiverName":"吴XX"}],"pageNo":1,"pageSize":10,"total":1,"totalPage":1}
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
         * activateList : [{"blockNum":10,"companyName":"厦门六立方","goodsImage":"321312","goodsName":"白酒","paymentTime":1520913420000,"paymentTimeStr":"11:57:00","receiverMobile":"138XXXXXX86","receiverName":"吴XX"}]
         * pageNo : 1
         * pageSize : 10
         * total : 1
         * totalPage : 1
         */

        private int pageNo;
        private int pageSize;
        private int total;
        private int totalPage;
        private List<ActivateListBean> activateList;

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

        public List<ActivateListBean> getActivateList() {
            return activateList;
        }

        public void setActivateList(List<ActivateListBean> activateList) {
            this.activateList = activateList;
        }

        public static class ActivateListBean {
            /**
             * blockNum : 10
             * companyName : 厦门六立方
             * goodsImage : 321312
             * goodsName : 白酒
             * paymentTime : 1520913420000
             * paymentTimeStr : 11:57:00
             * receiverMobile : 138XXXXXX86
             * receiverName : 吴XX
             */

            private int blockNum;
            private String companyName;
            private String goodsImage;
            private String goodsName;
            private long paymentTime;
            private String paymentTimeStr;
            private String receiverMobile;
            private String receiverName;

            public int getBlockNum() {
                return blockNum;
            }

            public void setBlockNum(int blockNum) {
                this.blockNum = blockNum;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public long getPaymentTime() {
                return paymentTime;
            }

            public void setPaymentTime(long paymentTime) {
                this.paymentTime = paymentTime;
            }

            public String getPaymentTimeStr() {
                return paymentTimeStr;
            }

            public void setPaymentTimeStr(String paymentTimeStr) {
                this.paymentTimeStr = paymentTimeStr;
            }

            public String getReceiverMobile() {
                return receiverMobile;
            }

            public void setReceiverMobile(String receiverMobile) {
                this.receiverMobile = receiverMobile;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }
        }
    }
}
