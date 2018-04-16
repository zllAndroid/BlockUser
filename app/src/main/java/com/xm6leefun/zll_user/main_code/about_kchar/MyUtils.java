package com.xm6leefun.zll_user.main_code.about_kchar;

/**
 */
public class MyUtils {
    /**
     * Prevent class instantiation.
     */
    private MyUtils() {
    }

    public static String getVolUnit(float num) {

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return "万手";
        } else if (e >= 4) {
            return "千手";
        } else {
            return "亿手";
        }


    }
}
