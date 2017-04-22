package com.group.attract.pooshechka;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TableLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[] imageResId = {
            R.drawable.ic_status_144,
            R.drawable.ic_water_144,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Set the Tabs icons
        tabLayout.getTabAt(0).setIcon(imageResId[0]);
        tabLayout.getTabAt(1).setIcon(imageResId[1]);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView waterImageView = (ImageView) findViewById(R.id.water_image_view);
                ImageView statusImageView = (ImageView) findViewById(R.id.status_image_view);
                ListView listForStatuses = (ListView) findViewById(R.id.list_for_statuses);

                Log.v("posity", String.valueOf(tab.getPosition()));

                if (tab.getPosition() == 0){

                    statusImageView.setBackgroundResource(R.drawable.what_about_statuses_800x536px);
                    statusImageView.setVisibility(View.VISIBLE);
                    listForStatuses.setVisibility(View.INVISIBLE);

                }
                else {
                    waterImageView.setBackgroundResource(R.drawable.problemo_800x536px);
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


}
