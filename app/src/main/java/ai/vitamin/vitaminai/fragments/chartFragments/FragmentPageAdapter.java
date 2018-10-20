package ai.vitamin.vitaminai.fragments.chartFragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    public static final String[] HEADINGS = new String[]{"WEIGHT", "BMI", "CALORIES"};

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new WeightFragment();
            case 1: return new BMIFragment();
        }
        return new WeightFragment();
    }

    @Override
    public int getCount() {
        return HEADINGS.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return HEADINGS[position];
    }
}
