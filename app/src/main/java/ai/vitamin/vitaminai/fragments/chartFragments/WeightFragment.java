package ai.vitamin.vitaminai.fragments.chartFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
        return inflater.inflate(R.layout.fragment_weight, container, false); //main view of the layout
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LineChart chart = view.findViewById(R.id.weight_line_chart); //line chart for weight
        ArrayList<Entry> entries = new ArrayList<>(); //list of all the coordinates

        for (int i = 0;  i < 10; i++)
        {
            entries.add(new Entry(i, i));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Weight-Label");
        chart.setData(new LineData(dataSet));
        chart.invalidate();
    }
}
