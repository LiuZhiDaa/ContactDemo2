package com.example.administrator.contactdemo;

import android.animation.ValueAnimator;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tablayout)
    TabLayout tablayout;

    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    int tablayoutHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme_NoActionBar);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);

        tablayout.post(new Runnable() {
            @Override
            public void run() {
                tablayoutHeight = tablayout.getHeight();

            }
        });




    }

    private void animationChangeTablayout(int oldValue,int newValue){
        ValueAnimator animator = ValueAnimator.ofInt(oldValue,newValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = tablayout.getLayoutParams();
                layoutParams.height = height;
                tablayout.setLayoutParams(layoutParams);
            }
        });
        animator.setDuration(200L);
        animator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setSearchableInfo(searchableInfo);


        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                animationChangeTablayout(tablayoutHeight,0);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                animationChangeTablayout(0, tablayoutHeight);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    class MyAdapter extends FragmentPagerAdapter{

        String[] titles;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            titles = getResources().getStringArray(R.array.tab_titles);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FrequentlyFragment();
                case 1:
                    return new ContactFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}
