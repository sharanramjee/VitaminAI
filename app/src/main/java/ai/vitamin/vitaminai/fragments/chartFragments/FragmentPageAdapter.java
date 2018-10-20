package ai.vitamin.vitaminai.fragments.chartFragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    private static final String[] HEADINGS = new String[]{"WEIGHT", "BMI", "CALORIES"};

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return WeightFragment.newInstance();
            case 1: return BMIFragment.newInstance();
        }
        return CaloriesFragment.newInstance();
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
