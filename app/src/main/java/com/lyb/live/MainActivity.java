package com.lyb.live;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lyb.lib.LybLiveViewPager;

public class MainActivity extends AppCompatActivity {

    private LybLiveViewPager liveViewPager;
    private LivePagerAdapter livePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        liveViewPager = findViewById(R.id.view_pager);

        liveViewPager.setOnEvent(new LybLiveViewPager.OnEvent() {
            @Override
            public void onEventUp() {
                Log.v("event", "liveViewPager onEventUp");
            }

            @Override
            public void onEventDown() {
                Log.v("event", "liveViewPager onEventDown");
            }
        });

        livePagerAdapter = new LivePagerAdapter(getSupportFragmentManager());

        liveViewPager.setAdapter(livePagerAdapter);
        liveViewPager.setCurrentItem(0);
    }

    public class LivePagerAdapter extends FragmentStatePagerAdapter {

        private LiveFragment currFrgment;



        public LivePagerAdapter(FragmentManager fm) {
            super(fm);

        }




        @Override
        public int getCount() {
            return 5;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return LiveFragment.newInstance(position+"", position+"");
        }


        @Override
        public Object instantiateItem(ViewGroup container, int actPosition) {
            LiveFragment liveFragment = (LiveFragment)super.instantiateItem(container, actPosition);
//            liveFragment.setImplFragment(LivePagerActivity.this);
            return liveFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            currFrgment = (LiveFragment) object;
            super.setPrimaryItem(container, position, object);
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 覆写destroyItem并且空实现,这样每个Fragment中的视图就不会被销毁
            // super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        public LiveFragment getCurrentFragment(){
            return currFrgment;
        }
    }
}