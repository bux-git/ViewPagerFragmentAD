package com.dqr.www.viewpagerfragmentad;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button mBtnDelete;
    private Button mBtnAdd;
    private ViewPager mViewPager;
    private SparseArray<TestFragment> mTestFragments;
    private PagerAdapter mPagerAdapter;

    private int key;
    private int mCurPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnAdd = (Button) findViewById(R.id.btn_add);

        mTestFragments = new SparseArray<>();
        mTestFragments.put(key++,TestFragment.newInstance("第一"));
        mTestFragments.put(key++,TestFragment.newInstance("第二"));
        mTestFragments.put(key++,TestFragment.newInstance("第三"));
        mTestFragments.put(key++,TestFragment.newInstance("第四"));
        mTestFragments.put(key++,TestFragment.newInstance("第五"));
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTestFragments);

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurPos=position;
                Log.d("sort:", "onPageSelected: "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestFragments.removeAt(mCurPos);
                mPagerAdapter.notifyDataSetChanged();
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestFragments.put(key++,TestFragment.newInstance("第"+key));
                mPagerAdapter.notifyDataSetChanged();
            }
        });
    }
}
