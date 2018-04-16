package com.xm6leefun.zll_user.base;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class URL_GET {
    public static String USER_TOKEN= "123";
    public static String USER_HEAD_URL= "";
    public static String USER_NI_NAME= "";
    public static String USER_ID= "";
    public static ArrayList<String> mList=new ArrayList<String>();
//    public static String URL = "http://192.168.1.70:8080";
    public static String URL = "http://192.168.1.21:8080";

    public static String ttt(){
        mList.clear();
//        mList.add("user_name="+"123");
//        mList.add("password="+"456");
        mList.add("name="+"abc");
        mList.add("num="+"123");
        return "http://192.168.1.20:8080/api/block/bindBlock?"+SignForXm6leefunJava.postRequests(mList);
    }
//    public static String login(String mobile,String pwd){
//        mList.clear();
//        mList.add("user_name="+mobile);
//        mList.add("password="+pwd);
//        return "http://www.xm6leefun.cn:2000/wwwroot/app/index.php?api_act=apiTest&"+SignForXm6leefunJava.getSing(mList);
//    }
    public static String getIndex(){
        mList.clear();
        return URL+"/api/userInfo/getIndex?"+ SignForXm6leefunJava.getSing(mList);
    }

    public static String login(String mobile,String pwd){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("pwd="+pwd);
        return URL+"/api/shippingClerk/login?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String nfcActivate(String userId,String num,String goodsId){
        mList.clear();
        mList.add("userId="+userId);
        mList.add("num="+num);
        mList.add("goodsId="+goodsId);
        return URL+"/api/userInfo/nfcActivate?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String wechatLogin(String openId,String nickName,String headPortrait){
        mList.clear();
        mList.add("openId="+openId);
        mList.add("nickName="+nickName);
        mList.add("headPortrait="+headPortrait);
        return URL+"/api/userInfo/wechatLogin?"+ SignForXm6leefunJava.getSing(mList);
    }
//    用户信息接口
    public static String getUserData(String userId){
        mList.clear();
        mList.add("userId="+userId);
        return URL+"/api/userInfo/getUserData?"+ SignForXm6leefunJava.getSing(mList);
    }
//    退出
    public static String loguot(String userId){
        mList.clear();
        mList.add("userId="+userId);
        return URL+"/api/userInfo/loguot?"+ SignForXm6leefunJava.getSing(mList);
    }
    //    获取版本号
    public static String getEdition(){
        mList.clear();
        mList.add("type="+"android");
        return URL+"/api/shippingClerk/getEdition?"+ SignForXm6leefunJava.getSing(mList);
    }
//    用户激活列表
    public static String getActivateList(String userId,String pageNo){
        mList.clear();
        mList.add("userId="+userId);
        mList.add("pageNo="+pageNo);
        return URL+"/api/userInfo/getActivateList?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String checkMoblie(String userId){
        mList.clear();
        mList.add("userId="+userId);
        return URL+"/api/userInfo/checkMoblie?"+ SignForXm6leefunJava.getSing(mList);
    }
//待发货任务列表
//public static String login(String scId,String pageNo){
//    mList.clear();
//    mList.add("scId="+"1");
//    mList.add("pageNo="+"1");
//    return URL+"/api/task/getWaitShipTask?"+SignForXm6leefunJava.getSing(mList);
//}
//    public static String getWaitShipTask(String scId,String pageNo){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("pageNo="+pageNo);
//        return URL+"/api/task/getWaitShipTask?"+ SignForXm6leefunJava.getSing(mList);
//    }
//    public static String getExpressList(String scId){
//        mList.clear();
//        mList.add("scId="+scId);
//        return URL+"/api/task/getExpressList?"+ SignForXm6leefunJava.getSing(mList);
//    }

//    public static String getWaitShipTaskData(String scId,String taskId){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("taskId="+taskId);
//        return URL+"/api/task/getWaitShipTaskData?"+ SignForXm6leefunJava.getSing(mList);
//    }
////    校验qrCode接口
//    public static String checkQrCode(String scId,String qrCode){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("qrCode="+qrCode);
//        return URL+"/api/task/checkQrCode?"+ SignForXm6leefunJava.getSing(mList);
//    }
////    校验nfc接口
//    public static String checkNfcUid(String scId,String nfcUid){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("nfcUid="+nfcUid);
//        return URL+"/api/task/checkNfcUid?"+ SignForXm6leefunJava.getSing(mList);
//    }
//    历史记录接口
//    public static String getHistoryTask(String scId,String pageNo){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("pageNo="+pageNo);
//        return URL+"/api/task/getHistoryTask?"+ SignForXm6leefunJava.getSing(mList);
//    }
//    public static String getHistoryTaskDetails(String scId,String historyDate,String pageNo){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("historyDate="+historyDate);
//        mList.add("pageNo="+pageNo);
//        return URL+"/api/task/getHistoryTaskDetails?"+ SignForXm6leefunJava.getSing(mList);
//    }
////    历史任务详情
//    public static String getHistoryTaskData(String scId,String taskId){
//        mList.clear();
//        mList.add("scId="+scId);
//        mList.add("taskId="+taskId);
//        return URL+"/api/task/getHistoryTaskData?"+ SignForXm6leefunJava.getSing(mList);
//    }
////    短信接口
//    public static String getNoteCode(String mobile,String type){
//        mList.clear();
//        mList.add("mobile="+mobile);
//        mList.add("type="+type);
//        return URL+"/api/noteCode/getNoteCode?"+ SignForXm6leefunJava.getSing(mList);
//    }
////    修改密码接口
//    public static String resetPwd(String mobile,String pwd,String code){
//        mList.clear();
//        mList.add("mobile="+mobile);
//        mList.add("pwd="+pwd);
//        mList.add("code="+code);
//        return URL+"/api/shippingClerk/resetPwd?"+ SignForXm6leefunJava.getSing(mList);
//    }

}
