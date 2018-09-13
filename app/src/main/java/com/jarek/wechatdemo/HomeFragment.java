package com.jarek.wechatdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private View view;
    private ViewPager viewPager;
    private ArrayList<View> pageview;
    private ImageView imageView;
    private ImageView[] imageViews;
    // 包裹小圆点的LinearLayout
    private ViewGroup group;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_fragment, container, false);
        init();
        return view;
    }
    public void init(){

        VideoView videoView1=(VideoView)view.findViewById(R.id.video1);
        VideoView videoView2=(VideoView)view.findViewById(R.id.video2);
        VideoView videoView3=(VideoView)view.findViewById(R.id.video3);
        VideoView videoView4=(VideoView)view.findViewById(R.id.video4);
        VideoView videoView5=(VideoView)view.findViewById(R.id.video5);
        VideoView videoView6=(VideoView)view.findViewById(R.id.video6);
        ImageView search=(ImageView)view.findViewById(R.id.search);
        EditText serchcontent=(EditText)view.findViewById(R.id.search_content);
        TextView searchview=(TextView)view.findViewById(R.id.searchView);

        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        //查找布局文件用LayoutInflater.inflate
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item01, null);
        View view2 = inflater.inflate(R.layout.item02, null);
        View view3 = inflater.inflate(R.layout.item03, null);
        //将view装入数组
        pageview =new ArrayList<View>();
        pageview.add(view1);
        pageview.add(view2);
        pageview.add(view3);
        group = (ViewGroup)view.findViewById(R.id.viewGroup);
        //有多少张图就有多少个点点
        imageViews = new ImageView[pageview.size()];
        for(int i =0;i<pageview.size();i++){
            imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20,20));
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;

            //默认第一张图显示为选中状态
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            group.addView(imageViews[i]);
        }

        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //绑定监听事件
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    //数据适配器
    PagerAdapter mPagerAdapter = new PagerAdapter(){

        @Override
        //获取当前窗体界面数
        public int getCount() {
            // TODO Auto-generated method stub
            return pageview.size();
        }

        @Override
        //断是否由对象生成界面
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0==arg1;
        }
        //是从ViewGroup中移出当前View
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(pageview.get(arg1));
        }

        //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
        public Object instantiateItem(View arg0, int arg1){
            ((ViewPager)arg0).addView(pageview.get(arg1));
            return pageview.get(arg1);
        }
    };

    //pageView监听器
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        //如果切换了，就把当前的点点设置为选中背景，其他设置未选中背景
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            for(int i=0;i<imageViews.length;i++){
                imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
                }
            }

        }

    }

}
