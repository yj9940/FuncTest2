package com.example.lenovo.functest2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private TextView mChatTextView;
    private TextView mFriendView;
    private TextView mContactView;

    private ImageView mTabline;
    private int mScreen1_3;

    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initTabLine();

        initView();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        if (id ==1) {
            //fragment的切换采用的是viewpage的形式,然后1是指底部第2个Fragment
            mViewPager.setCurrentItem(1);
        }
    }

    private void initTabLine() {
        mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
        Display display =  getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;

        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mChatTextView = (TextView) findViewById(R.id.id__tv_chat);
        mFriendView = (TextView) findViewById(R.id.id_tv_frd);
        mContactView = (TextView) findViewById(R.id.id__tv_contact);

        mDatas = new ArrayList<Fragment>();

        ContactMainTabFragment tab01 = new ContactMainTabFragment();
        FriendMainTabFragment tab02 = new FriendMainTabFragment();
        ChatMainTabFragment tab03 = new ChatMainTabFragment();

        mDatas.add(tab01);
        mDatas.add(tab02);
        mDatas.add(tab03);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mDatas.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx)
            {
                Log.e("TAG",position+","+positionOffset+","+positionOffsetPx);

                LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) mTabline.getLayoutParams();

                if(mCurrentPageIndex == 0 && position==0)//0-->1
                {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3
                                        + mCurrentPageIndex * mScreen1_3);
                }else if (mCurrentPageIndex == 1 && position==0) //1-->0
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
                                        + (positionOffset - 1) * mScreen1_3);
                }else if (mCurrentPageIndex == 1 && position==1)    //1-->2
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
                            + positionOffset * mScreen1_3);
                }else if (mCurrentPageIndex==2&&position==1)        //2-->1
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
                            + (positionOffset - 1) * mScreen1_3);
                }

                mTabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position)
                {
                    case 0:
                        mChatTextView.setTextColor(Color.GREEN);
                        break;
                    case 1:
                        mFriendView.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        mContactView.setTextColor(Color.GREEN);
                        break;

                }
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void resetTextView() {
        mChatTextView.setTextColor(Color.BLACK);
        mFriendView.setTextColor(Color.BLACK);
        mContactView.setTextColor(Color.BLACK);
    }


}
