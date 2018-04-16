package com.xm6leefun.zll_user.main_code.retrofitrx;


/**
    * 提交接口时统一的请求参数
    * 创建作者： tushicong
    * 创建时间： 2018/2/24 13:35
   **/

public class ResponseParent<T> {

     private String count;
     private String start;
     private String total;
     private T subjects;
     private String title;


     public ResponseParent(String count, String start, String total, T subjects,String title) {
        this.count = count;
        this.start = start;
        this.total = total;
        this.subjects = subjects;
        this.title = title;
     }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
