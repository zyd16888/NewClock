package com.example.dong.newclock;

/**
 * Created by dong on 2017/10/26.
 */

public class MainActivity_bat {
//    package com.example.dong.newclock;
//
//import android.os.Bundle;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//    public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//
//        private ViewPager viewPager;
//        private ArrayList<View> pageview;
//        private TextView Alarm_clock;
//        private TextView Timer;
//        private TextView stopwatch;
//        private TextView World_time;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            viewPager = (ViewPager) findViewById(R.id.viewPager);
////        查找布局文件用LayoutInflater.inflate
//            LayoutInflater inflater = getLayoutInflater();
//            View view1 = inflater.inflate(R.layout.clock, null);
//            View view2 = inflater.inflate(R.layout.stopwatch, null);
//            View view3 = inflater.inflate(R.layout.count_down, null);
//            View view4 = inflater.inflate(R.layout.world_time, null);
//
//
//            Alarm_clock = (TextView) findViewById(R.id.Alarm_clock);
//            stopwatch = (TextView) findViewById(R.id.Stopwatch);
//            Timer = (TextView) findViewById(R.id.Timer);
//            World_time = (TextView) findViewById(R.id.world_time);
//
//            Alarm_clock.setOnClickListener(this);
//            Timer.setOnClickListener(this);
//            stopwatch.setOnClickListener(this);
//            World_time.setOnClickListener(this);
////        添加切换的界面
//            pageview = new ArrayList<View>();
//            pageview.add(view1);
//            pageview.add(view2);
//            pageview.add(view3);
//            pageview.add(view4);
//
////        数据适配器
//            PagerAdapter mPagerAdapter = new PagerAdapter(){
//
//                @Override
//                //获取当前窗体界面数
//                public int getCount() {
//                    // TODO Auto-generated method stub
//                    return pageview.size();
//                }
//
//                @Override
//                //判断是否由对象生成界面
//                public boolean isViewFromObject(View arg0, Object arg1) {
//                    // TODO Auto-generated method stub
//                    return arg0==arg1;
//                }
//                //使从ViewGroup中移出当前View
//                public void destroyItem(View arg0, int arg1, Object arg2) {
//                    ((ViewPager) arg0).removeView(pageview.get(arg1));
//                }
//
//                //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
//                public Object instantiateItem(View arg0, int arg1){
//                    ((ViewPager)arg0).addView(pageview.get(arg1));
//                    return pageview.get(arg1);
//                }
//            };
////      绑定适配器
//            viewPager.setAdapter(mPagerAdapter);
//
//            //设置viewPager的初始界面为第一个界面
//            viewPager.setCurrentItem(0);
//
//
//            //        切换界面监听器
//            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    //当界面滑动时
//                    int currentPage = viewPager.getCurrentItem();//获得当前页面
//                    switch (currentPage) {
//                        case 0:
//                            Alarm_clock.setSelected(true);
//                            break;
//                        case 1:
//                            stopwatch.setSelected(true);
//                            break;
//                        case 2:
//                            Timer.setSelected(true);
//                            break;
//                        case 3:
//                            World_time.setSelected(true);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
//
//
//
//
////        //为了获取屏幕宽度，新建一个DisplayMetrics对象
////        DisplayMetrics displayMetrics = new DisplayMetrics();
////        //将当前窗口的一些信息放在DisplayMetrics类中
////        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
////        //得到屏幕的宽度
////        int screenW = displayMetrics.widthPixels;
////
//        }
//
//
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.Alarm_clock:
//                    //点击时切换页面
//                    viewPager.setCurrentItem(0);
//                    break;
//                case R.id.Stopwatch:
//                    viewPager.setCurrentItem(1);
//                    break;
//                case R.id.Timer:
//                    viewPager.setCurrentItem(2);
//                    break;
//                case R.id.world_time:
//                    viewPager.setCurrentItem(3);
//                    break;
//            }
//        }
//
//    }



}
