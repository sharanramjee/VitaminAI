package ai.vitamin.vitaminai.fragments.chartFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import static java.lang.Math.pow;

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

        LineChart chart = view.findViewById(R.id.calories_line_chart); //line chart for weight
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0;  i < 10; i++)
        {
            entries.add(new Entry(i, (float) (1.0 / pow(10, i)) ));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Calories-Label");
        chart.setData(new LineData(dataSet));
        chart.invalidate();

        return view;
    }
}
