package ai.vitamin.vitaminai.fragments.chartFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ai.vitamin.vitaminai.R;

public class CaloriesFragment extends Fragment {

    public CaloriesFragment() { }

    public static CaloriesFragment newInstance() {
        return new CaloriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calories, container, false);
        return view;
    }
}
