package com.do79.SxuMiro.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.do79.SxuMiro.R;
import com.do79.SxuMiro.base.BaseActivity;
import com.do79.SxuMiro.model.news.FreshNews;
import com.do79.SxuMiro.ui.fragment.FreshNewsDetailFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

public class FreshNewsDetailActivity extends BaseActivity {

    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_news_detail);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
    }

    @Override
    protected void initData() {
        ArrayList<FreshNews> FreshNews = (ArrayList<FreshNews>) getIntent().getSerializableExtra
                (DATA_FRESH_NEWS);
        int position = getIntent().getIntExtra(DATA_POSITION, 0);
        viewPager.setAdapter(new FreshNewsDetailAdapter(getSupportFragmentManager(), FreshNews));
        viewPager.setCurrentItem(position);
    }


    private class FreshNewsDetailAdapter extends FragmentPagerAdapter {

        private ArrayList<FreshNews> freshNewses;

        public FreshNewsDetailAdapter(FragmentManager fm, ArrayList<FreshNews> freshNewses) {
            super(fm);
            this.freshNewses = freshNewses;
        }

        @Override
        public Fragment getItem(int position) {
            return FreshNewsDetailFragment.getInstance(freshNewses.get(position));
        }

        @Override
        public int getCount() {
            return freshNewses.size();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
