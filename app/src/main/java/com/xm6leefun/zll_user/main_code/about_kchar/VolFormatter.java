package com.xm6leefun.zll_user.main_code.about_kchar;


import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

public class VolFormatter implements YAxisValueFormatter {

    private final float unit;
    private DecimalFormat mFormat;
    private String u;
    public VolFormatter(float unit) {
        if (unit <= 1) {
            mFormat = new DecimalFormat("#0");
        } else {
            mFormat = new DecimalFormat("#0.00");
        }
        this.unit = unit;
        this.u=MyUtils.getVolUnit(unit);
    }


    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        value = value / unit;
        if(value==0){
            return u;
        }
        return mFormat.format(value);
    }
}
