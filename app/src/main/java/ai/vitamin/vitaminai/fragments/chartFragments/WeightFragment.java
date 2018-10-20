package ai.vitamin.vitaminai.fragments.chartFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import ai.vitamin.vitaminai.R;


public class WeightFragment extends Fragment {

    public WeightFragment() { }

    public static WeightFragment newInstance() {
        return new WeightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false); //main view of the layout
        LineChart chart = view.findViewById(R.id.weight_line_chart); //line chart for weight
        ArrayList<Entry> entries = new ArrayList<>();

        return view;
    }
}
