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

import ai.vitamin.vitaminai.R;

public class BMIFragment extends Fragment {

    public BMIFragment() { }

    public static BMIFragment newInstance() {
        return new BMIFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineChart chart = view.findViewById(R.id.bmi_line_chart); //line chart for weight
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0;  i < 10; i++)
        {
            entries.add(new Entry(i, i * i));
        }

        LineDataSet dataSet = new LineDataSet(entries, "BMI-Label");
        chart.setData(new LineData(dataSet));
        chart.invalidate();
    }
}
