package com.xm6leefun.zll_user.main_code.about_kchar;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.utils.ScreenUtils;
import com.xm6leefun.zll_user.utils.ToastUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class KkkActivity extends AppCompatActivity {

    private String TAG = "qqq";
    private CombinedChart mChart;
    private Button btn;
    private int itemcount;
    private LineData lineData;
    private BarData barData;
    private CandleData candleData;
    private CombinedData combinedData;
    private ArrayList<String> xVals;
    private List<CandleEntry> candleEntries = new ArrayList<>();
    private int colorHomeBg;
    private int colorLine;
    private int colorText;
    private int colorMa5;
    private int colorMa10;
    private int colorMa20;
    TextView mTv;
    BarChart barChart;
    XAxis xAxisBar, xAxisK;
    YAxis axisLeftBar, axisLeftK;
    YAxis axisRightBar, axisRightK;
    LinearLayout mLinMainTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_kkk);
         mLinMainTop = (LinearLayout)findViewById(R.id.include_top_lin_back);

        mChart = (CombinedChart) findViewById(R.id.chart);
        barChart = (BarChart) findViewById(R.id.barchart);
        mTv = (TextView) findViewById(R.id.textView);
        initChart();
        loadChartData();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            barChart.setAutoScaleMinMaxEnabled(true);
            mChart.setAutoScaleMinMaxEnabled(true);
            mChart.notifyDataSetChanged();
            barChart.notifyDataSetChanged();

            mChart.invalidate();
            barChart.invalidate();

        }
    };

    public static int screenWidth;
    public static int screenHeight;
    private void initChart() {
        colorHomeBg = getResources().getColor(R.color.home_page_bg);
        colorLine = getResources().getColor(R.color.common_divider);
        colorText = getResources().getColor(R.color.text_grey_light);
        colorMa5 = getResources().getColor(R.color.ma5);
        colorMa10 = getResources().getColor(R.color.ma10);
        colorMa20 = getResources().getColor(R.color.ma20);

        mTv.setBackgroundColor(colorHomeBg);

//        barChart.setDrawBorders(true);//是否在折线图上添加边框
//        barChart.setBorderWidth(1);
//
//        barChart.setBorderColor(colorHomeBg);
//        barChart.setDrawGridBackground(false);// 是否显示表格颜色     barChart.setGridBackgroundColor(Color.RED); // 表格的的颜色
//
//        barChart.setDragEnabled(true);
//        barChart.setScaleYEnabled(false);

        barChart.setDrawBorders(true);
        barChart.setBorderWidth(1);
        barChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        barChart.setDragEnabled(true);
        barChart.setScaleYEnabled(false);


        barChart.setBackgroundColor(colorHomeBg);
        barChart.setGridBackgroundColor(colorHomeBg);

        barChart.setDescription("你好");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        float v = ScreenUtils.px2dp(this, screenWidth);
        barChart.setDescriptionPosition(80, 35);//数据描述的位置
        Log.e("screenWidth",(v/3)+"");
        barChart.setDescriptionColor(colorText);//数据的颜色
        barChart.setDescriptionTextSize(8);//数据字体大小
//        barChart.setDrawGridBackground(true); // 是否显示表格颜色
//        barChart.setGridBackgroundColor(Color.RED); // 表格的的颜色
        barChart.setDrawValueAboveBar(false);
//        EventLogTags.Description description=new EventLogTags.Description();
//        description.setText("description");
        Legend barChartLegend = barChart.getLegend();
        barChartLegend.setEnabled(false);

        //BarYAxisFormatter  barYAxisFormatter=new BarYAxisFormatter();
        //bar x y轴
        xAxisBar = barChart.getXAxis();
//        xAxisBar.setEnabled(true);     //是否显示X坐标轴 及 对应的刻度竖线，默认是true
        xAxisBar.setDrawLabels(false); //是否显示X坐标轴上的刻度，默认是true

        xAxisBar.setDrawGridLines(true);//是否显示竖直标尺线
        xAxisBar.setDrawAxisLine(false);
//        xAxisBar.setLabelsToSkip(3);//设置横坐标显示的间隔数
//        xAxisBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisBar.setTextColor(colorText);//X轴上的刻度的颜色
        xAxisBar.setGridColor(colorLine);
//        xAxisBar.setGridColor(getResources().getColor(R.color.minute_grayLine));

        axisLeftBar = barChart.getAxisLeft();
        axisLeftBar.setAxisMinValue(0);
        axisLeftBar.setDrawGridLines(false);
        axisLeftBar.setDrawAxisLine(false);
//        axisLeftBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftBar.setDrawLabels(true);
        axisLeftBar.setSpaceTop(0);
        axisLeftBar.setShowOnlyMinMax(true);
        axisRightBar = barChart.getAxisRight();
        axisRightBar.setDrawLabels(false);
        axisRightBar.setDrawGridLines(false);
        axisRightBar.setDrawAxisLine(false);


//        axisLeftBar.setAxisMinValue(0);
//        axisLeftBar.setDrawGridLines(true);
//        axisLeftBar.setDrawAxisLine(true);
////        axisLeftBar.setDrawLabels(true);
////        axisLeftBar.setSpaceTop(0);
//        axisLeftBar.setShowOnlyMinMax(true);


//        axisLeftBar.setLabelCount(2, false);
//        axisLeftBar.setAxisMinValue(0);
//        axisLeftBar.setDrawLabels(true);
//        axisLeftBar.setDrawAxisLine(true); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
//        axisLeftBar.setDrawGridLines(true); //是否显示X坐标轴上的刻度竖线，默认是true
//        axisLeftBar.setEnabled(true); //左侧显示Y轴 数据
        axisLeftBar.setGridColor(colorLine);
        axisLeftBar.setTextColor(colorText);

//        axisRightBar = barChart.getAxisRight();
//        axisRightBar.setEnabled(false);
////        axisRightBar.setDrawAxisLine(true);
////        axisRightBar.setDrawAxisLine(true);
//////        axisRightBar.setDrawGridLines(true);
//////        axisRightBar.setDrawAxisLine(true);
//////        axisRightBar.setDrawLabels(false);
//////        axisRightBar.setShowOnlyMinMax(true);
//////        axisRightBar.setDrawAxisLine(true); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
//////        axisRightBar.setDrawGridLines(true); //是否显示X坐标轴上的刻度竖线，默认是true
//        axisRightBar.setGridColor(colorLine);
//        axisRightBar.setTextColor(colorText);
/**
 * ==============================================================================================
 */
        mChart.setDrawBorders(true);
        mChart.setBorderWidth(1);
        mChart.setDescription("");
        mChart.setDragEnabled(true);
        mChart.setScaleYEnabled(false);
        mChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        mChart.setBackgroundColor(colorHomeBg);
        mChart.setGridBackgroundColor(colorHomeBg);

//        mChart.setDescription("");
//        mChart.setDrawGridBackground(true);
//        mChart.setBackgroundColor(colorHomeBg);
//        mChart.setGridBackgroundColor(colorHomeBg);
//        mChart.setScaleYEnabled(false);
//
//        mChart.setDrawBorders(true);
//        mChart.setDrawGridBackground(true);
//
//        mChart.setPinchZoom(true);
//        mChart.setDrawValueAboveBar(false);
//        mChart.setNoDataText("加载中...");
//        mChart.setBorderWidth(1);
//        mChart.setAutoScaleMinMaxEnabled(true);
//        mChart.setDragEnabled(true);
//        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE});

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true

        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setGridColor(colorLine);
        xAxis.setTextColor(colorText);
        xAxis.setSpaceBetweenLabels(4);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(4, false);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(false);//最外面的线
        leftAxis.setGridColor(colorLine);
        leftAxis.setTextColor(colorText);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(true);
        rightAxis.setGridColor(colorLine);
        rightAxis.setTextColor(colorText);

        int[] colors = {colorMa5, colorMa10, colorMa20};
        String[] labels = {"MA5", "MA10", "MA20"};
        Legend legend = mChart.getLegend();
        legend.setCustom(colors, labels);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
        legend.setTextColor(Color.WHITE);
        mChart.setDragDecelerationEnabled(true);
        barChart.setDragDecelerationEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.2f);
        barChart.setDragDecelerationFrictionCoef(0.2f);

        // 将K线控的滑动事件传递给交易量控件
        mChart.setOnChartGestureListener(new CoupleChartGestureListener(mChart, new Chart[]{barChart}));
        // 将交易量控件的滑动事件传递给K线控件
        barChart.setOnChartGestureListener(new CoupleChartGestureListener(barChart, new Chart[]{mChart}));
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Log.e("%%%%", h.getXIndex() + "");
            }
            @Override
            public void onNothingSelected() {
                mChart.highlightValue(null);
            }
        });
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                barChart.highlightValues(new Highlight[]{h});
                CandleEntry candleEntry = (CandleEntry) e;
                float change = (candleEntry.getClose() - candleEntry.getOpen()) / candleEntry.getOpen();
                NumberFormat nf = NumberFormat.getPercentInstance();
                nf.setMaximumFractionDigits(2);
                String changePercentage = nf.format(Double.valueOf(String.valueOf(change)));
                mTv.setText("最高" + candleEntry.getHigh() + " 最低" + candleEntry.getLow() +
                        " 开盘" + candleEntry.getOpen() + " 收盘" + candleEntry.getClose() +
                        " 涨跌幅" + changePercentage);

            }

            @Override
            public void onNothingSelected() {
                barChart.highlightValue(null);
            }
        });


    }
    BarDataSet barDataSet;
    BarDataSet barDataSet2;
    private void loadChartData() {
        mChart.resetTracking();

        candleEntries = Model.getCandleEntries();

        itemcount = candleEntries.size();
        List<StockListBean.StockBean> stockBeans = Model.getData();
        xVals = new ArrayList<>();
        for (int i = 0; i < itemcount; i++) {
            xVals.add(stockBeans.get(i).getDate());
        }

        combinedData = new CombinedData(xVals);

        /*k line*/
        candleData = generateCandleData();
        combinedData.setData(candleData);

        /*ma5*/
        ArrayList<Entry> ma5Entries = new ArrayList<Entry>();
        for (int index = 0; index < itemcount; index++) {
            ma5Entries.add(new Entry(stockBeans.get(index).getMa5(), index));
        }
        /*ma10*/
        ArrayList<Entry> ma10Entries = new ArrayList<Entry>();
        for (int index = 0; index < itemcount; index++) {
            ma10Entries.add(new Entry(stockBeans.get(index).getMa10(), index));
        }
        /*ma20*/
        ArrayList<Entry> ma20Entries = new ArrayList<Entry>();
        for (int index = 0; index < itemcount; index++) {
            ma20Entries.add(new Entry(stockBeans.get(index).getMa20(), index));
        }

        lineData = generateMultiLineData(
                generateLineDataSet(ma5Entries, colorMa5, "ma5"),
                generateLineDataSet(ma10Entries, colorMa10, "ma10"),
                generateLineDataSet(ma20Entries, colorMa20, "ma20"));


//        ArrayList<BarEntry> bar1 = new ArrayList<BarEntry>();
//        for (int index = 0; index < itemcount; index++) {
//            bar1.add(new BarEntry(stockBeans.get(index).getMa5(), index));
//        }
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < itemcount; i++) {
            barEntries.add(new BarEntry(stockBeans.get(i).getMa5(), i));
        }
        List<BarEntry> barEntries2 = new ArrayList<>();
        for (int i = 0; i < itemcount; i++) {
            barEntries2.add(new BarEntry(stockBeans.get(i).getMa20(), i));
        }
//        barData = generateMultiBarData(
//                generateBarDataSet(barEntries, colorMa5, "1"),
//                generateBarDataSet(barEntries2, colorMa10, "2")
//        );

//        左下角标题
//        axisLeftBar.setValueFormatter(new VolFormatter((float) barChart.getAxisLeft().getMaxWidth()));
        YAxis axisLeft = barChart.getAxisLeft();
        float maxWidth = axisLeft.getMaxWidth();

        barDataSet = new BarDataSet(barEntries, "成交量");
        barDataSet.setBarSpacePercent(50); //bar空隙
        barDataSet.setHighlightEnabled(true);
        barDataSet.setHighLightAlpha(122);
        barDataSet.setHighLightColor(Color.WHITE);
        barDataSet.setDrawValues(false);//是否显示柱子上面的数值
        barDataSet.setColor(Color.RED);
        barDataSet.setHighLightColor(Color.WHITE);

        barDataSet2 = new BarDataSet(barEntries2, "成交量2");
        barDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet2.setColor(Color.GREEN);
        barDataSet2.setBarSpacePercent(50); //bar空隙
        barDataSet2.setHighlightEnabled(true);
        barDataSet2.setHighLightAlpha(122);
        barDataSet2.setHighLightColor(Color.WHITE);
        barDataSet2.setDrawValues(false);//是否显示柱子上面的数值

        //IBarDataSet 接口很关键，是添加多组数据的关键结构，LineChart也是可以采用对应的接口类，也可以添加多组数据
        ArrayList<IBarDataSet> threebardata = new ArrayList<>();
        threebardata.add(barDataSet);
        threebardata.add(barDataSet2);
//        BarData barData = new BarData();
        BarData barData = new BarData(xVals, threebardata);
//        BarData barData2 = new BarData(xVals, barDataSet2);
//        barData.addDataSet(barDataSet);
//        barData.addDataSet(barDataSet2);
        barChart.setData(barData);


//        barChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);//设置注解的位置在左上方
//        barChart.getLegend().setForm(Legend.LegendForm.CIRCLE);//这是左边显示小图标的形状
//
//        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置

//        barChart.setData(barData2);
        final ViewPortHandler viewPortHandlerBar = barChart.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(xVals.size()));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        final float xscale = 3;
        touchmatrix.postScale(xscale, 1f);
        combinedData.setData(lineData);
        mChart.setData(combinedData);//当前屏幕会显示所有的数据
        mChart.invalidate();
        mChart.moveViewToX(itemcount - 1);
        barChart.moveViewToX(itemcount - 1);
        setOffset();
        handler.sendEmptyMessageDelayed(0, 300);
//        mChart.animateX(1500);
    }
    private float culcMaxscale(float count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

    private LineDataSet generateLineDataSet(List<Entry> entries, int color, String label) {

        LineDataSet set = new LineDataSet(entries, label);
        set.setColor(color);

        set.setLineWidth(1f);

        set.setDrawCubic(true);//圆滑曲线
        set.setDrawCircles(false);

        set.setDrawCircleHole(false);
        set.setDrawValues(false);

        set.setHighlightEnabled(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }
    private BarDataSet generateBarDataSet(List<BarEntry> entrie, int color, String label) {
        BarDataSet set = new BarDataSet(entrie, label);
        set.setColor(color);
        set.setBarBorderWidth(1f);
        set.setDrawValues(false);
        set.setHighlightEnabled(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return set;
    }

    private BarData generateMultiBarData(BarDataSet... BarDataSets) {
        List<IBarDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < BarDataSets.length; i++) {
            dataSets.add(BarDataSets[i]);
        }
        List<String> xVals = new ArrayList<String>();
        for (int i = 0; i < itemcount; i++) {
            xVals.add("" + (1990 + i));
        }
        BarData data = new BarData(xVals, dataSets);
        return data;
    }
    private LineData generateMultiLineData(LineDataSet... lineDataSets) {
        List<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < lineDataSets.length; i++) {
            dataSets.add(lineDataSets[i]);
        }
        List<String> xVals = new ArrayList<String>();
        for (int i = 0; i < itemcount; i++) {
            xVals.add("" + (1990 + i));
        }

        LineData data = new LineData(xVals, dataSets);

        return data;
    }

    private CandleData generateCandleData() {

        CandleDataSet set = new CandleDataSet(candleEntries, "");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowWidth(0.7f);//设置阴影

        set.setDecreasingColor(Color.RED);
        set.setDecreasingPaintStyle(Paint.Style.FILL);

        set.setIncreasingColor(Color.GREEN);
        set.setIncreasingPaintStyle(Paint.Style.FILL);

        set.setNeutralColor(Color.RED);
        set.setShadowColorSameAsCandle(true);

        set.setHighlightLineWidth(0.5f);
        set.setHighLightColor(Color.WHITE);
        set.setDrawValues(false);

        CandleData candleData = new CandleData(xVals);
        candleData.addDataSet(set);

        return candleData;
    }

    /*设置量表对齐*/
    private void setOffset() {
        float lineLeft = mChart.getViewPortHandler().offsetLeft();
        float barLeft = barChart.getViewPortHandler().offsetLeft();
        float lineRight = mChart.getViewPortHandler().offsetRight();
        float barRight = barChart.getViewPortHandler().offsetRight();
        float barBottom = barChart.getViewPortHandler().offsetBottom();
        float offsetLeft, offsetRight;
        float transLeft = 0, transRight = 0;
 /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
        if (barLeft < lineLeft) {
           /* offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            barChart.setExtraLeftOffset(offsetLeft);*/
            transLeft = lineLeft;
        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            mChart.setExtraLeftOffset(offsetLeft);
            transLeft = barLeft;
        }
  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
        if (barRight < lineRight) {
          /*  offsetRight = Utils.convertPixelsToDp(lineRight);
            barChart.setExtraRightOffset(offsetRight);*/
            transRight = lineRight;
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            mChart.setExtraRightOffset(offsetRight);
            transRight = barRight;
        }
        barChart.setViewPortOffsets(transLeft, 15, transRight, barBottom);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean screenOriatationPortrait = ScreenUtils.isScreenOriatationPortrait(this);
        Log.e("is",screenOriatationPortrait+"");
        if (screenOriatationPortrait) {
            ToastUtil.show("竖屏");
            mLinMainTop.setVisibility(View.VISIBLE);
        }
        else {
            ToastUtil.show("横屏");
            mLinMainTop.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

//        boolean screenOriatationPortrait = ScreenUtils.isScreenOriatationPortrait(this);
//        Log.e("is",screenOriatationPortrait+"");
//        if (screenOriatationPortrait)
//        ToastUtil.show("竖屏");
//        else
//            ToastUtil.show("横屏");

    }
//拦截头部返回，因为没有头部
//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }
//拦截右滑退出
//    @Override
//    protected boolean isSupportSwipeBack() {
//        return false;
//    }
}
