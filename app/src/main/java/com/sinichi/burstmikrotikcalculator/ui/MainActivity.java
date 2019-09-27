package com.sinichi.burstmikrotikcalculator.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinichi.burstmikrotikcalculator.R;
import com.sinichi.burstmikrotikcalculator.fragments.BurstLimitFragment;
import com.sinichi.burstmikrotikcalculator.fragments.BurstTimeFragment;
import com.sinichi.burstmikrotikcalculator.fragments.IntervalFragment;
import com.sinichi.burstmikrotikcalculator.fragments.ThresholdFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView mButtomNavigation;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mButtomNavigation = findViewById(R.id.bottom_navigation);
        mButtomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_threshold:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_interval:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_burst_limit:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_burst_time:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
        viewPager = findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem preMenuItem = mButtomNavigation.getMenu().getItem(position);
                if (preMenuItem != null)
                    preMenuItem.setChecked(false);
                else
                    mButtomNavigation.getMenu().getItem(position)
                            .setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            Toast.makeText(MainActivity.this, "Exit apps", Toast.LENGTH_SHORT).show();
            finish();
        }
        return true;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ThresholdFragment();
                case 1:
                    return new IntervalFragment();
                case 2:
                    return new BurstLimitFragment();
                case 3:
                    return new BurstTimeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
