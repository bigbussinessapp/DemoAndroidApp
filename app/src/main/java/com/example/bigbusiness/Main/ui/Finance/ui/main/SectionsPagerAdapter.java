package com.example.bigbusiness.Main.ui.Finance.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bigbusiness.Main.ui.Finance.RemindersAndDues.RemindersAndDuesFragment;
import com.example.bigbusiness.Main.ui.Finance.TransactionHistory.TransactionHistoryFragment;
import com.example.bigbusiness.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.finance_tab1, R.string.finance_tab2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragmentSelected = null;
        switch(position)
        {
            case 0:
                fragmentSelected = new TransactionHistoryFragment();
                break;
            case 1:
                fragmentSelected = new RemindersAndDuesFragment();
                break;
        }
        return fragmentSelected;
//        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}