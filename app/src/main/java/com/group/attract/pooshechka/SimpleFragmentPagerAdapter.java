package com.group.attract.pooshechka;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by paul on 22.04.17.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Water", "Status"};
    private Context context;

    private int[] imageResId = {
            R.drawable.ic_status_144,
            R.drawable.ic_water_144,
    };

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return new StatusFragment();
            }
             else {
                return new WaterFragment();
            }
        }


    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }


}
