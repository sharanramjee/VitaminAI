package ai.vitamin.vitaminai.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.fragments.chartFragments.FragmentPageAdapter;


public class TrendFragment extends Fragment {
    public TrendFragment() {
    }

    public static TrendFragment newInstance() {
        return new TrendFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trend, container, false);

        final ViewPager pager = view.findViewById(R.id.charts_vp);
        FragmentPageAdapter pagerAdapter = new FragmentPageAdapter((Objects.requireNonNull(getActivity())).getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.weight_bmi_cal_tL);
        int[] color = {getResources().getColor(R.color.main_activity_tab_Indicator_color),
                getResources().getColor(R.color.main_activity_tab_selected_text_color),
                getResources().getColor(R.color.main_activity_tab_not_selected_text_color)};
        tabLayout.setSelectedTabIndicatorColor(color[0]);
        tabLayout.setTabTextColors(color[2], color[1]);
        tabLayout.setupWithViewPager(pager);

        //pager.setCurrentItem(0);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onResume() {
        super.onResume();
    }
}
