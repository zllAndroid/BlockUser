package com.xm6leefun.zll_user.utils.about_volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequest {
    public static StringRequest stringRequest;
    public static void RequestGet(final Context context, String url, VolleyInterface volleyInterface) {
        String tag = url;
        VolleyHelper.getInstance(context).cancelPendingRequests(tag);
        stringRequest = new StringRequest(Request.Method.GET, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyHelper.getInstance(context).addToRequestQueue(stringRequest, tag);
    }
    public static void MyRequest(final Context context, String url, VolleyResponse response) {
        String tag = url;
        stringRequest = new StringRequest(Request.Method.GET, url,
                response.resListener(), response.reserrorListener()){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Charset", "utf-8");
                params.put("Content-Type", "application/x-javascript");
                params.put("Accept-Encoding", "gzip,deflate");
                return params;
            }};
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyHelper.getInstance(context).addToRequestQueue(stringRequest, tag);
    }
    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params,
                                   VolleyInterface volleyInterface) {
        VolleyHelper.getInstance(context).cancelPendingRequests(tag);
        stringRequest = new StringRequest(Request.Method.POST, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyHelper.getInstance(context).addToRequestQueue(stringRequest, tag);
    }

//        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//            }
//        },new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Charset", "utf-8");
//                headers.put("Content-Type", "application/x-javascript");
//                headers.put("Accept-Encoding", "gzip,deflate");
//                return headers;
//            }
//        };
//        VolleyHelper.getInstance(context).addToRequestQueue(stringRequest, tag);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        VolleyHelper.getInstance(context).addToRequestQueue(stringRequest, tag);
//    }
//
//
//
//    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params,
//                                   VolleyInterface volleyInterface) {
//        VolleyHelper.getInstance(context).cancelPendingRequests(tag);
//        stringRequest = new StringRequest(Request.Method.POST, url, volleyInterface.loadingListener(),
//                volleyInterface.errorListener()) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // TODO Auto-generated method stub
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleyHelper.getInstance(context).addToRequestQueue(stringRequest, tag);
//    }
}
