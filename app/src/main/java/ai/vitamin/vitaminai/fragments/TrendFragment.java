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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.fragments.chartFragments.FragmentPageAdapter;

import static java.lang.Math.pow;


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
        return inflater.inflate(R.layout.fragment_trend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LineChart weight = view.findViewById(R.id.weight_line_chart);
        LineChart bmi = view.findViewById(R.id.bmi_line_chart);
        LineChart calories = view.findViewById(R.id.calories_line_chart);

        //weight
        ArrayList<Entry> entriesw = new ArrayList<>(); //list of all the coordinates
        for (int i = 0;  i < 10; i++)
        {
            entriesw.add(new Entry(i, i));
        }
        LineDataSet dataSet = new LineDataSet(entriesw, "Weight-Label");
        weight.setData(new LineData(dataSet));
        weight.invalidate();

        //BMI
        ArrayList<Entry> entriesbmi = new ArrayList<>();
        for (int i = 0;  i < 10; i++)
        {
            entriesbmi.add(new Entry(i, i * i));
        }
        LineDataSet dataSetbmi = new LineDataSet(entriesbmi, "BMI-Label");
        bmi.setData(new LineData(dataSet));
        bmi.invalidate();

        //calories
        ArrayList<Entry> entriesc = new ArrayList<>();
        for (int i = 0;  i < 10; i++)
        {
            entriesc.add(new Entry(i, (float) (1.0 / pow(10, i)) ));
        }
        LineDataSet dataSetc = new LineDataSet(entriesc, "Calories-Label");
        calories.setData(new LineData(dataSetc));
        calories.invalidate();





    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
