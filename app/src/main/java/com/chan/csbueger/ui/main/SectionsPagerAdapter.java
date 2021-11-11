package com.chan.csbueger.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chan.csbueger.Menu1;
import com.chan.csbueger.Menu2;
import com.chan.csbueger.Menu3;
import com.chan.csbueger.R;

//import com.chan.csbueger.ui.main.SectionsPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //if (position)

        switch (position) {
            case 0:
                return new Menu1();
            case 1:
                return new Menu2();
            case 2:
                return new Menu3();
            default:
                return PlaceholderFragment.newInstance(position + 0);
        }

        //return PlaceholderFragment.newInstance(position + 2);
        //return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
                return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}