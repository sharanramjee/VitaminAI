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
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.data.DataMethod;
import ai.vitamin.vitaminai.fragments.chartFragments.FragmentPageAdapter;
import ai.vitamin.vitaminai.objects.Food;
import ai.vitamin.vitaminai.objects.Weight;

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
        ArrayList<Weight> allWeight = DataMethod.getAllWeight(getContext());
        long timeStart = allWeight.get(0).getDate();
        for (int i = 0;  i < 10; i++)
        {
            long time = allWeight.get(i).getDate();
            entriesw.add(new Entry((float) (time-timeStart), (float) (allWeight.get(i).getWeight())));
        }
        if (entriesw.size() != 0) {
            LineDataSet dataSet = new LineDataSet(entriesw, "Weight-Label");
            weight.setData(new LineData(dataSet));
            weight.invalidate();
        }

        //BMI
        ArrayList<Entry> entriesbmi = new ArrayList<>();
        for (int i = 0;  i < 10; i++)
        {
            long time = allWeight.get(i).getDate();
            entriesw.add(new Entry((float) (time-timeStart), (float) (allWeight.get(i).getBMI())));
        }
        if (entriesbmi.size() != 0) {
            LineDataSet dataSetbmi = new LineDataSet(entriesbmi, "BMI-Label");
            bmi.setData(new LineData(dataSetbmi));
            bmi.invalidate();
        }

        //calories
        ArrayList<Entry> entriesc = new ArrayList<>();
        ArrayList<Food> allFood = DataMethod.getAllFood(getContext(), new Date());
        double sum = 0;
        for (int i = 0;  i < allFood.size(); i++)
        {
            sum += allFood.get(i).getTotalCalories() * allFood.get(i).getPortion();
            entriesc.add(new Entry(i, (float) sum));
        }
        if (entriesc.size() != 0) {
            LineDataSet dataSetc = new LineDataSet(entriesc, "Calories-Label");
            calories.setData(new LineData(dataSetc));
            calories.invalidate();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
