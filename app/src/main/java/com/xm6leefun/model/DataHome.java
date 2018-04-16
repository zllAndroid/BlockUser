package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class DataHome {

    /**
     * code : 200
     * msg : OK
     * record : {"indexBrandList":[{"image":"http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/images/c48732d5-1b49-4d95-8cea-648f81a894b2.jpg","url":"2"},{"image":"http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/images/581d5a98-293d-400a-acad-c1b5aeb634f8.jpg","url":"22"}]}
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
        private List<IndexBrandListBean> indexBrandList;

        public List<IndexBrandListBean> getIndexBrandList() {
            return indexBrandList;
        }

        public void setIndexBrandList(List<IndexBrandListBean> indexBrandList) {
            this.indexBrandList = indexBrandList;
        }

        public static class IndexBrandListBean {
            /**
             * image : http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/images/c48732d5-1b49-4d95-8cea-648f81a894b2.jpg
             * url : 2
             */

            private String image;
            private String url;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
