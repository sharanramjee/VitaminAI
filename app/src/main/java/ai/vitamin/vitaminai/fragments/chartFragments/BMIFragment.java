package ai.vitamin.vitaminai.fragments.chartFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ai.vitamin.vitaminai.R;

public class BMIFragment extends Fragment {

    public BMIFragment() { }

    public static BMIFragment newInstance() {
        return new BMIFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);
        return view;
    }

}
