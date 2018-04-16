package com.xm6leefun.zll_user.base;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class CommonParameter {

    public static final String LodingNormal= "0";
    public static final String LodingFlower= "1";
    public static final String LodingFlowerHaveResult= "3";
    public static final String LodingGIF= "2";
    public static final String NORMAL= "4";
//错误代码
    public static final String worry = "未找到指定服务器";
    public static final String ERROR = "网络请求失败，请检测";
    public static final String OutputInfoNull = "请完善信息";
    public static final String TAG = "xm6leefun";
    public static final String TOKEN_KEY= "token_key";
    public static final String VIDEO_KEY= "video_key";

    public static final String GOH5_TITAL_KEY= "tital_html";
    public static final String GOH5_ARTICAL_KEY= "artical_html";
    public static final String GOVIDEO_TD_KEY= "video_tongdao";
    public static final String GOVIDEO_ID_KEY= "video_id";
    public static final String GOVIDEO_NO_CONTROL_KEY= "video_nohave_id";
    public static final String Manor_KEY= "intervalRequest";

    /**
     * handle 返回参数
     */

    public static final int HANDLE_MSG_SUCCESS= 0x01;//成功
    public static final int HANDLE_TOWN_SUCCESS= 0x06;//成功
    public static final int HANDLE_TOWN_SUCCESS_ONE= 0x07;//成功
    public static final int HANDLE_TOWN_SUCCESS_FIVE= 0x08;//成功

    public static final int HANDLE_MSG_SUCCESS_FIRST= 0x02;//成功  第一次

    public static final int HANDLE_MSG_SUCCESS_TWCE= 0x03;//成功   第二次以后

    public static final int HANDLE_MSG_FAILED= 0x04;//失败
    public static final int HANDLE_MSG_ERROR= 0x05;//错误

/**
 * 服务器返回码
 * String 类型
 */
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_EPC = "9999";
    public static final String CODE_KEY_INVALID = "1001";
    public static final String CODE_SIGN_EMPTY = "1002";
    public static final String CODE_SIGN_FAIL = "1003";
    public static final String CODE_LACK = "1004";
    public static final String CODE_TIMEOUT= "1007";
    public static final String CODE_NO_METHOD= "1006";

    //    200  正常；请求已完成。
//9999 参数异常
//1000 APP接口关闭，无法使用
//1001 参数appkey无效
//1002 签名不能为空
//1003 签名验证失败
//1004 缺少api_secret参数
//1005 code代码未定义
//1006 方法不存在
//1007 登录超时,请重新登录
}
